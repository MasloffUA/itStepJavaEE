package ua.step.example.part0.mailer;

/**
 * 
 * Класс предназначен для отправки сообщений используя определенный протокол
 *
 */
public class Mailer {
	/**
	 * Композиция
	 */
	private MailSender sender = new SmtpMailSender();
	
	public void send(String string) {
		sender.send(string);
	}
}