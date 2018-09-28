package ua.step.net.model;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerTask extends Thread {

	private static final short BYTE_IN_KB = 1024; 
	private final Socket socket;

	public ServerTask(Socket socket) {
		this.socket = socket;
		setPriority(NORM_PRIORITY);
		start();
	}

	public void run() {
		try {
			// из сокета клиента берём поток входящих данных
			InputStream is = socket.getInputStream();
			// и оттуда же - поток данных от сервера к клиенту
			OutputStream os = socket.getOutputStream();

			// буффер данных в 64 килобайта
			byte buf[] = new byte[64 * BYTE_IN_KB];
			// читаем 64кб от клиента, результат - кол-во реально принятых
			// данных
			int r = is.read(buf);

			// создаём строку, содержащую полученную от клиента информацию
			String data = new String(buf, 0, r);

			// записываем данные в поток:
			os.write(data.getBytes());

			// завершаем соединение
			socket.close();
		} catch (Exception e) {
			System.out.println("init error: " + e);
		} 
	}
}
