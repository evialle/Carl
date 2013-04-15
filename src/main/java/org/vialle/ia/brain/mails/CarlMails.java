/**
 * 
 */
package org.vialle.ia.brain.mails;

import org.vialle.ia.speech.CarlSpeech;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * @author Eric
 * 
 */
@Named
@ApplicationScoped
public class CarlMails {

	private static final String IMAP_SERVER = "imap.gmail.com";

	private static final String EMAIL_PASSWORD = "toto";

	private static final String EMAIL_ADDRESS = "toto@gmail.com";

	private static final SimpleDateFormat SDF = new SimpleDateFormat("EEEE d MMMM, 'it is' H 'hours' m", Locale.ENGLISH);

	@Inject
	private CarlSpeech carlSpeech;

	public void process() throws MessagingException {

		StringBuilder answerSpeech = new StringBuilder();

		Properties props = System.getProperties();
		props.setProperty("mail.store.protocol", "imaps");
		Session session = Session.getDefaultInstance(props, null);
		Store store = session.getStore("imaps");
		store.connect(IMAP_SERVER, EMAIL_ADDRESS, EMAIL_PASSWORD);

		Folder inbox = store.getFolder("Inbox");
		inbox.open(Folder.READ_ONLY);
		if (inbox.getMessageCount() > 0) {
			Message message = inbox.getMessage(1);

			String subject = message.getSubject();
			Date receivedDate = message.getReceivedDate();

			answerSpeech.append("You have a message received the").append(SDF.format(receivedDate)).append(" about ").append(subject);
		} else {
			answerSpeech.append("You have no messages");
		}

       carlSpeech.speak(answerSpeech);
	}

}
