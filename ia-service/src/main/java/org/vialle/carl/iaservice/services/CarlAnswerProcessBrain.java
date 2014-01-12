/**
 * 
 */
package org.vialle.carl.iaservice.services;


import org.vialle.carl.iaservice.services.lastnews.CarlLastNews;
import org.vialle.carl.iaservice.services.mails.CarlMails;
import org.vialle.carl.iaservice.services.speech.CarlSpeech;
import org.vialle.carl.iaservice.services.date.CarlDate;
import org.vialle.carl.iaservice.services.weather.CarlWeather;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Eric
 */
@Named
@ApplicationScoped
public class CarlAnswerProcessBrain {
	
	@Inject
	private CarlSpeech speech;
	
	@Inject
	private CarlLastNews carlLastNews;
	
	@Inject
	private CarlDate carlDate;
	
	@Inject
	private CarlMails carlMails;
	
	@Inject
	private CarlWeather carlWeather;
	
	public final static String LASTNEWS_ACTION = "lastNews";
	public final static String DATE_ACTION = "date";
	public final static String MAIL_ACTION = "mails";

	private static final String WEATHER_ACTION = "weather";


	public void processAnswer(final CarlAiAnswer answer) throws Exception {
		
		switch (answer.getAction()) {
		case LASTNEWS_ACTION:
			carlLastNews.process();
			break;
		case DATE_ACTION:
			carlDate.process();
			break;
		case MAIL_ACTION:
			carlMails.process();
			break;
		case WEATHER_ACTION:
			carlWeather.process();
			break;

		default:
			speech.speak(answer.getSpeak());
			break;
		}
		
		
	}
}
