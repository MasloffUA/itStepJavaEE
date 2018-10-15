package ua.step.example;

import ua.step.example.mailer.ImapMailSender;
import ua.step.example.mailer.Mailer;
import ua.step.example.mailer.SmtpMailSender;

/**
 * 
 * Inversion of Control (IoC)
 *
 */
public class Task02 {

	public static void main(String[] args) {
		Mailer mailer = new Mailer(new SmtpMailSender());
		mailer.send("Hello");
		mailer = new Mailer(new ImapMailSender());
		mailer.send("Helllo");
	}
}
