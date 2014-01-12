/**
 *
 */
package org.vialle.carl.iaservice.services;


import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.vialle.carl.iaservice.services.date.CarlDate;
import org.vialle.carl.iaservice.services.lastnews.CarlLastNews;
import org.vialle.carl.iaservice.services.mails.CarlMails;
import org.vialle.carl.iaservice.services.speech.CarlSpeech;
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

    @Inject
    ApplicationContext applicationContext;

    public void processAnswer(final CarlAiAnswer answer) throws Exception {

        final String action = answer.getAction();
        if (StringUtils.isNotEmpty(action)) {
            CarlService carlService = applicationContext.getBean(action, CarlService.class);
            if (carlService != null) {
                carlService.process(answer);
            } else {
                speech.speak(answer.getSpeak());
            }

        } else {
            speech.speak(answer.getSpeak());

        }


    }
}
