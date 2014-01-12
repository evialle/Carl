package org.vialle.carl.iaservice.services.date;


import org.vialle.carl.iaservice.services.CarlService;
import org.vialle.carl.iaservice.services.speech.CarlSpeech;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Named
@ApplicationScoped
public class CarlDate implements CarlService {
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("EEEE d MMMM, 'it is' H 'hours' m", Locale.ENGLISH);
	
	@Inject
    CarlSpeech speech;
	
	public void process() {
		speech.speak("We are the " + SDF.format(new Date()));
	}

}
