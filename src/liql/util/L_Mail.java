package liql.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import nosubmit.L_Security;

public class L_Mail {
	public static void sendMail(String file) throws AddressException, MessagingException, UnsupportedEncodingException {
		L_LOG.OUT_Nece("start mail...");
		Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		// Get a Properties object
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", L_Security.MAIL_HOST);
		props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", "465");
		props.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(L_Security.MAIL_FROM, L_Security.MAIL_PWD);
			}
		});

		// -- Create a new message --
		Message msg = new MimeMessage(session);
		msg.setSubject(L_Security.MAIL_SUBJECT);

		Multipart multipart = new MimeMultipart();
		// 添加邮件正文
		BodyPart contentPart = new MimeBodyPart();
		contentPart.setContent(L_Security.MAIL_CONTENT.toString(), "text/html;charset=UTF-8");
		multipart.addBodyPart(contentPart);

		File attachment = new File(file);

		BodyPart attachmentBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(attachment);
		attachmentBodyPart.setDataHandler(new DataHandler(source));

		// MimeUtility.encodeWord可以避免文件名乱码
		attachmentBodyPart.setFileName(MimeUtility.encodeWord(attachment.getName()));
		multipart.addBodyPart(attachmentBodyPart);
		msg.setContent(multipart);

		// -- Set the FROM and TO fields --
		int x = new Random().nextInt(L_Security.MAIL_FROM_LIST.size());
		L_Security.MAIL_FROM = L_Security.MAIL_FROM_LIST.get(x);
		L_LOG.OUT_NoneNece("curr mail from ==>"+L_Security.MAIL_FROM);
		msg.setFrom(new InternetAddress(L_Security.MAIL_FROM));
		msg.setRecipients(Message.RecipientType.TO, L_Security.MAIL_TOLIST);
		msg.setRecipients(Message.RecipientType.CC, L_Security.MAIL_CCLIST);
		msg.setRecipients(Message.RecipientType.BCC, L_Security.MAIL_BCCLIST);
		msg.setSentDate(new Date());
		msg.saveChanges();

		Transport.send(msg);
		L_LOG.OUT_Nece("end mail...");
	}
}
