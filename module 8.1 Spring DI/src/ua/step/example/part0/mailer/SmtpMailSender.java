package ua.step.example.part0.mailer;

/**
 * 
 * Класс отправляет сообщения по SMTP протоколу
 *
 */
public class SmtpMailSender implements MailSender {
	public void send(String message) {
		System.out.printf("SMTP: %s", message);
	}
}