package org.vialle.ia.brain.time;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.vialle.ia.speech.CarlSpeech;

@Named
@ApplicationScoped
public class CarlDate {
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("EEEE d MMMM, 'it is' H 'hours' m", Locale.ENGLISH);
	
	@Inject
	CarlSpeech speech;
	
	public void process() {
		speech.speak("We are the " + SDF.format(new Date()));
	}

}
