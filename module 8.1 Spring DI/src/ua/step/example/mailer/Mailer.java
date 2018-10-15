package ua.step.example.mailer;

public class Mailer{
	MailSender sender;
	
	public Mailer(MailSender ms) {
		sender = ms;
	}
	
	public void send(String string) {	
		sender.send(string);		
	}
}
