package ua.step.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 
 * Рассылка пакетов без контроля доставки
 *
 */
public class Task05 {
	public static void main(String[] args) throws IOException {
		DatagramSocket socket = new DatagramSocket();
		InetAddress adress = InetAddress.getLocalHost();
		byte[] data = "привет".getBytes();
		int port = 9995;
		DatagramPacket packet = new DatagramPacket(data , data.length, adress, port);
		socket.send(packet);
	}
}
