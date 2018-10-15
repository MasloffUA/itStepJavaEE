package ua.step.example.mailer;

public class SmtpMailSender implements MailSender {
	public void send(String message) {
		System.out.printf("SMTP: %s", message);
	}
}
