/**
 * 
 */
package org.vialle.carl.iaservice.services.speech;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * @author Eric
 *
 */
@Named
@ApplicationScoped
public class CarlSpeech {
	
	private static final Logger LOG = LogManager.getLogger(CarlSpeech.class);
	
	private static final String VOICE = "kevin16";

	private Voice syntheticVoice;

    @PostConstruct
	private void init() {
		LOG.info("Initiating Carl's vocal");

		VoiceManager voiceManager = VoiceManager.getInstance();
		syntheticVoice = voiceManager.getVoice(VOICE);
		syntheticVoice.allocate();
	}
	
	
	/**
	 * @param sentence read the sentence
	 */
	public void speak(String sentence) {
		LOG.debug("Reading: " + sentence);
		syntheticVoice.speak(sentence);
	}
	
	public void speak(StringBuilder sentence) {
		this.speak(sentence.toString());
	}
}
