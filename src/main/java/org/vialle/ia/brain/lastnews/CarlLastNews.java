package org.vialle.ia.brain.lastnews;

import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.vialle.ia.speech.CarlSpeech;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@Named
@ApplicationScoped
public class CarlLastNews {

	@Inject
	private CarlSpeech speech;

	private final static String RSS_FEED_URL = "http://rss.cnn.com/rss/edition.rss";

	public void process() throws Exception {
		
		XmlReader reader = new XmlReader(new URL(RSS_FEED_URL));
		SyndFeed feed = new SyndFeedInput().build(reader);
		
		StringBuilder answerSpeech = new StringBuilder("Actually on ");
		answerSpeech.append(feed.getTitle()).append(". ");
			
		List<SyndEntry> list = feed.getEntries();
		
		if (list.size() >= 1) {
			answerSpeech.append(list.get(0).getTitle());
			
			if (list.size() >= 2) {
				answerSpeech.append(". And ");
				answerSpeech.append(list.get(1).getTitle());
			}
			
		} else {
			answerSpeech.append("There are no news");
		}
		
		//And tell the result
		speech.speak(answerSpeech);
		
		
	}
}
