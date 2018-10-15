package ua.step.example;

import ua.step.example.mailer.ImapMailSender;
import ua.step.example.mailer.Mailer;

/**
 * 
 * Inversion of Control (IoC)
 *
 */
public class Task01 {

	public static void main(String[] args) {
		Mailer mailer = new Mailer(new ImapMailSender());
		mailer.send("Hello");
	}
}
