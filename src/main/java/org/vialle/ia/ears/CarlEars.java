package org.vialle.ia.ears;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.vialle.ia.brain.CarlBrain;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

@Named
@ApplicationScoped
public class CarlEars extends Thread {

	private static final Logger LOG = LogManager.getLogger(CarlEars.class);

	private static final String SPHINX_CONFIGURATION_FILE = "carl.config.xml";

	@Inject
	private CarlBrain brain;

	private ConfigurationManager cm;

	private Microphone microphone;

	private Recognizer recognizer;

	private boolean enableRecognizerLoop = true;

	/** Constructor. */
	public CarlEars() {
		super("CarlEars");
	}

	@PostConstruct
	public void startThread() {
		this.start();
	}

	@Override
	public void run() {
		initSphinx();

		recognitionLoop();
	}

	/**
	 * Main loop of the service.
	 */
	private void recognitionLoop() {
		this.brain.ready();

		startMicrophone();

		do {
			Result result = this.recognizer.recognize();

			if (result != null) {

				String resultText = result.getBestFinalResultNoFiller();
				if (resultText.isEmpty() == false) {
					stopMicrophoneAndPrepare();

					processInputSpeech(resultText);

					startMicrophone();

				}
			}
		} while (this.enableRecognizerLoop);

	}

	@Async
	private void stopMicrophoneAndPrepare() {
		stopMicrophone();
		prepareMicrophone();
	}

	/**
	 * 
	 */
	private void stopMicrophone() {
		synchronized (this) {

			LOG.debug("Stop microphone");
			this.microphone.stopRecording();

			this.recognizer.deallocate();
		}
	}

	/** Start the microphone. */
	private void startMicrophone() {

		prepareMicrophone();

		LOG.debug("ReStart microphone");
		this.microphone.startRecording();
	}

	/**
	 * Prepare the microphone to be started.
	 */
	private void prepareMicrophone() {
		LOG.debug("Prepare Microphone");
		synchronized (this) {
			this.cm = new ConfigurationManager(CarlEars.class.getResource(SPHINX_CONFIGURATION_FILE));

			this.recognizer = (Recognizer) this.cm.lookup("recognizer");
			this.recognizer.allocate();

			this.microphone = (Microphone) this.cm.lookup("microphone");
		}
	}

	private void initSphinx() {
		LOG.info("Initiating Carl's ears");
	}

	/**
	 * @param resultText
	 */
	private void processInputSpeech(final String resultText) {
		LOG.info("You: " + resultText);

		synchronized (this) {
			this.brain.think(resultText);
		}

	}

}
