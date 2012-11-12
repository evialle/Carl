/**
 * 
 */
package org.vialle.ia.brain;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.vialle.ia.brain.lastnews.CarlLastNews;
import org.vialle.ia.brain.mails.CarlMails;
import org.vialle.ia.brain.time.CarlDate;
import org.vialle.ia.brain.weather.CarlWeather;
import org.vialle.ia.speech.CarlSpeech;

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
