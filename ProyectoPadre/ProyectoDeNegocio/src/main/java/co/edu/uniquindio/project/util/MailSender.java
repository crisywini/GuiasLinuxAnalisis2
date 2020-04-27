package co.edu.uniquindio.project.util;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	public static void main(String[] args) {
		sendMailWithGMail("luisacotte18@gmail.com", "Holita JAVA MAIL CON HOLITA ", "Holita luisa Fernanda cotte, este correo ha sido enviado con\nJava mail y para que sepas, Java antes tiene un salto de linea.");
	}
	public static void sendMailWithGMail(String recipient, String subject, String bodyMessage) {
		String userS = "analisisdealgoritmosdos@gmail.com";
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); 
		props.put("mail.smtp.user", userS);
		props.put("mail.smtp.clave", "crilu2analisis"); 
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.starttls.enable", "true"); 
		props.put("mail.smtp.port", "587"); 
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(userS));
			
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject(subject);
			message.setText(bodyMessage);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", userS, "crilu2analisis");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); 
		}
	}
}
