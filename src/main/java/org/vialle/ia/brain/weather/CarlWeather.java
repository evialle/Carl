package org.vialle.ia.brain.weather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.vialle.ia.speech.CarlSpeech;

import com.google.common.collect.Lists;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.jdom.Element;

@Named
@ApplicationScoped
public class CarlWeather {

	private static long PARIS_W = 615702;

	private static char CELSIUS_INDICATOR = 'c';

	private static final String WEATHER_RSS_FEED = "http://weather.yahooapis.com/forecastrss?w="
			+ PARIS_W + "&u=" + CELSIUS_INDICATOR;

	private static final SimpleDateFormat SDF_YAHOO_FORMAT = new SimpleDateFormat(
			"d MMM yyyy", Locale.ENGLISH);

	private static final SimpleDateFormat SDF_TALK = new SimpleDateFormat(
			"EEEE d", Locale.ENGLISH);

	@Inject
	private CarlSpeech carlSpeech;

	public void process() throws MalformedURLException, IOException,
			IllegalArgumentException, FeedException, ParseException {
		XmlReader reader = new XmlReader(new URL(WEATHER_RSS_FEED));
		SyndFeed feed = new SyndFeedInput().build(reader);

		StringBuilder answerSpeech = new StringBuilder();

		List<SyndEntry> list = feed.getEntries();
		List<WeatherForecast> forecastList = Lists.newArrayListWithCapacity(2);

		if (list.size() >= 1) {
			String temperature = null;
			String text = null;

			SyndEntry syndEntry = list.get(0);
			List<Element> listFM = (List) syndEntry.getForeignMarkup();
			for (Element element : listFM) {
				String elementName = element.getNamespacePrefix() + ":"
						+ element.getName();

				switch (elementName) {
				case "yweather:condition":
					temperature = element.getAttribute("temp").getValue();
					text = element.getAttribute("text").getValue();

					break;

				case "yweather:forecast":
					// date="9 Sep 2012" low="63" high="84" text="Partly Cloudy"
					// code="29"
					WeatherForecast w = new WeatherForecast();
					forecastList.add(w);
					w.code = Integer.parseInt(element.getAttribute("code")
							.getValue());
					w.date = SDF_YAHOO_FORMAT.parse(element
							.getAttribute("date").getValue());
					w.low = Integer.parseInt(element.getAttribute("low")
							.getValue());

					w.high = Integer.parseInt(element.getAttribute("high")
							.getValue());
					w.text = element.getAttribute("text").getValue();

					break;
				}

			}

			answerSpeech.append("Today, we have a temperature of ")
					.append(temperature).append(" degrees, with ").append(text)
					.append(" condition. ");
			for (WeatherForecast forecast : forecastList) {
				answerSpeech.append("The ")
						.append(SDF_TALK.format(forecast.date))
						.append(", we will have a temperature of ")
						.append(forecast.low).append(" to ")
						.append(forecast.high).append(" degrees, with ")
						.append(forecast.text).append(" conditions. ");
			}
		} else {
			answerSpeech.append("Sorry! No weathers forecast are available.");
		}

		carlSpeech.speak(answerSpeech);
	}

	private static class WeatherForecast {
		Date date;
		int low, high;
		String text;
		int code;
	}
}
