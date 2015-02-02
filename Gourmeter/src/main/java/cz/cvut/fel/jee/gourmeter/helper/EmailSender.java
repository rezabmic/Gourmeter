package cz.cvut.fel.jee.gourmeter.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.cvut.fel.jee.gourmeter.ejb.FacilitySessionBean;

@Stateless
public class EmailSender {
	private static final Logger log = LoggerFactory
			.getLogger(FacilitySessionBean.class);

	@Resource(mappedName = "java:jboss/mail/Default")
	private Session mailSession;
	
	/**
	 * Odesle zpravu, dle zvolene sablony a se zadanymi parametry, na zadanou
	 * emailovou adresu
	 * 
	 * @param emailAddress
	 *            - emailova adresa
	 * @param params
	 *            - parametry, ktere se maji aplikovat na sablonu. Klic je nazev
	 *            parametru bez < a >. Hodnota se bere jako Object a aplikuje se
	 *            na ni metoda toString()
	 * @param templateFile
	 * @see TemplateType
	 */
	public void sendMessage(String emailAddress, Map<String, Object> params,
			TemplateType templateFile) {
		String address = null;

		// vyber sablony
		if (templateFile != null)
			address = templateFile.getPath()
					+ templateFile.getTemplateFileName();

		// kontrola dostupnosti souboru

		URL url = this.getClass().getResource(address);
		if (url != null) {
			address = url.getPath();
		}

		File file = new File(address);

		String template = null;
		if ((file.exists()) && (file.isFile()) && (file.canRead())) {
			template = readTemplateFromFile(file);
		} else {
			log.warn("Can't access the file!" + address);
		}

		if (template == null) {
			log.warn("Read method returned empty template String! The email could not be sent!");
			return;
		}

		// substituce parametru
		for (Map.Entry<String, Object> entrySet : params.entrySet()) {
			template = template.replaceAll("<" + entrySet.getKey() + ">",
					entrySet.getValue().toString());
		}

		// odeslani emailu
		this.sendEmail(emailAddress, templateFile.getSubject(), template);
	}

	public void sendMessage(EmailMessage message) {
		this.sendMessage(message.getReceiverEmail(), message.getParams(),
				TemplateType.valueOf(message.getTemplateName()));
	}

	private String readTemplateFromFile(File file) {
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e) {
			log.warn("Template file not found! " + e.getMessage().toString());
			// e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);

		// nacteni souboru a vraceni stringu
		StringBuilder result = new StringBuilder();

		String pom = null;
		try {
			pom = br.readLine();
			while (pom != null) {
				result.append(pom);
				pom = br.readLine();
			}
		} catch (IOException e) {
			log.warn("Error while reading file!");
			// e.printStackTrace();
		}

		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				log.warn("Unable to close BufferedReader for file!");
				// e.printStackTrace();
			}
		}
		if (fr != null) {
			try {
				fr.close();
			} catch (IOException e) {
				log.warn("Unable to close FileReader for file!");
				// e.printStackTrace();
			}
		}
		return result.toString();
	}

	private void sendEmail(String emailAddress, String subject, String text) {

		// TODO
		//final String SMTP_SERVER = "smtp.gmail.com";
		//final Integer PORT = 465;
		//final String USERNAME = "username";
		//final String PASSWORD = "password";
		final String FROM_FIELD = "Gourmeter";

		
		try    {
            MimeMessage m = new MimeMessage(mailSession);
            Address from = new InternetAddress(FROM_FIELD);
            Address[] to = new InternetAddress[] {new InternetAddress(emailAddress) };
 
            m.setFrom(from);
            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject("WildFly Mail");
            m.setSentDate(new java.util.Date());
            m.setContent(text,"text/plain");
            Transport.send(m);             
        }
        catch (javax.mail.MessagingException e)
        {
        	log.error("Could not send an email: " + emailAddress + " "
					+ subject + " " + text + " " + e.getMessage());
            //e.printStackTrace();             
        } 
		
		
		
		
		/*Email email = new SimpleEmail();
		email.setHostName(SMTP_SERVER);
		email.setSmtpPort(PORT);
		email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		// email.setSSLOnConnect(true);
		try {
			email.setFrom(FROM_FIELD);
			email.setSubject("[Gourmeter] - " + subject);
			email.setMsg(text);
			email.addTo(emailAddress);
			email.send();
			log.info("Email sended");
		} catch (Exception e) {
			log.error("Could not send an email: " + emailAddress + " "
					+ subject + " " + text + " " + e.getMessage());
		}/**/
	}
}
