package ua.step.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * InetAddress
 *
 */
public class Task01 {
	public static void main(String[] args) throws UnknownHostException {
			InetAddress adress = InetAddress.getLocalHost();
			System.out.println(adress);
			System.out.println(adress.getCanonicalHostName());
			System.out.println(adress.getHostName());
			System.out.println(adress.getHostAddress());
			System.out.println();
			
			adress = InetAddress.getByName("google.com");
			System.out.println(adress.getCanonicalHostName());
			System.out.println(adress.getHostName());
			System.out.println(adress.getHostAddress());

	}
}
