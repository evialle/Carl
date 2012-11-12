/**
 * 
 */
package org.vialle.ia.brain;

import java.io.FileNotFoundException;
import java.net.URL;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.aitools.programd.Core;
import org.aitools.programd.server.BotAccess;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;
import org.vialle.ia.speech.CarlSpeech;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author Eric
 * 
 */
@Named
@ApplicationScoped
public class CarlBrain {

	private static final Logger LOG = LogManager.getLogger(CarlBrain.class);

	private static final String ID_ALICEBOT = "Carl";

	@Inject
	private CarlAnswerProcessBrain processBrain;

	@Inject
	private CarlSpeech carlSpeech;

	@Inject
	private MongoOperations mongoTemplate;

	private BotAccess bot;

	private Gson gson;

	/**
	 * @return
	 * @throws FileNotFoundException
	 * @throws Exception
	 */
	public CarlBrain() throws FileNotFoundException {
		LOG.info("Initiating Carl's brain");

		initGson();

		URL baseURL = CarlBrain.class.getResource("/");
		URL propertiesUrl = CarlBrain.class.getResource("/core.xml");

		Core coreToUse = new Core(baseURL, propertiesUrl);
		bot = new BotAccess(coreToUse, ID_ALICEBOT, "Eric");
	}

	private void initGson() {
		this.gson = new Gson();
	}

	/**
	 * 
	 * @param inputSentence
	 * @return
	 */
	public boolean think(final String inputSentence) {
		CarlAiAnswer answer = null;
		String cleanInputSpeechForBot = null;

		try {
			cleanInputSpeechForBot = cleanInputSpeechForBot(inputSentence);
			String content = bot.getResponse(cleanInputSpeechForBot);

			try {
				answer = this.gson.fromJson(content, CarlAiAnswer.class);
				if (answer == null) {
					answer = new CarlAiAnswer();
					answer.setSpeak("");
					answer.setAction("");
				}
			} catch (JsonSyntaxException jsonException) {
				answer = new CarlAiAnswer();
				answer.setSpeak(content.replaceAll("<br(.*)/>", "").trim());
				answer.setAction("");
			}

			processBrain.processAnswer(answer);

			return true;
		} catch (Throwable t) {
			LOG.warn("An unknwon error has occured: " + t.getMessage(), t);
			carlSpeech
					.speak("Hum, an error has occured, this is very strange: "
							+ t.getMessage());
			return false;
		} finally {
			CarlBrainLog carlBrainLog = new CarlBrainLog(cleanInputSpeechForBot, answer);
			mongoTemplate.save(carlBrainLog, "carlbrainlog");
		}
	}

	/**
	 * @param resultText
	 * @return
	 */
	private String cleanInputSpeechForBot(String resultText) {
		return resultText.replaceFirst("carl", "");
	}

	/**
	 * Tells to the brain that it is ready.
	 */
	public void ready() {
		this.carlSpeech
				.speak("Hello! My name is Carl, I'm waiting your command.");
	}

	/**
	 * Log an entry in the Brain of Carl.
	 * 
	 * @author Eric Vialle
	 */
	private static class CarlBrainLog {
		private String question;
		private CarlAiAnswer answer;

		public CarlBrainLog() {
		}

		public CarlBrainLog(String question, CarlAiAnswer answer) {
			this.answer = answer;
			this.question = question;
		}

		/**
		 * @return the question
		 */
		public String getQuestion() {
			return question;
		}

		/**
		 * @param question
		 *            the question to set
		 */
		public void setQuestion(String question) {
			this.question = question;
		}

		/**
		 * @return the answer
		 */
		public CarlAiAnswer getAnswer() {
			return answer;
		}

		/**
		 * @param answer
		 *            the answer to set
		 */
		public void setAnswer(CarlAiAnswer answer) {
			this.answer = answer;
		}
	}

}
