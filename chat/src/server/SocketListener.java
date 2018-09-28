package server;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;

public class SocketListener extends Thread implements Closeable{
	private Socket socket;
	private String userName;
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private Map<String, SocketListener> users = Server.users;
	private Window frame = Server.getFrame();
	private ArrayList<String> messageHistory = Server.getMessageHistory();
	
	SocketListener (Socket socket){
		this.socket = socket;
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void run() {
		try {
			outputStream.writeObject(new Message(Server.getCurrentTime() + " Соединение с сервером установлено.", RequestType.MESSAGE));
			outputStream.flush();
			frame.apendAllText(String.format("Установлено новое соединение: %s ", socket.getRemoteSocketAddress()));
			nameRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true) {
			try {
				Message message = (Message) inputStream.readObject();
				//System.out.println("Получен запрос от клиента" + message.getRequestType());
				switch(message.getRequestType()) {
				case MESSAGE:
					publicMessage(message.getMessage());
					break;
				case NAME_ANSWER:
					checkName(message.getMessage());
					break;
				case GET_LAST_MESSAGES:
					sendLastMessages();
					break;
				default:
					break;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				users.remove(userName);
				try {
					exitUser();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				interrupt();
				break;
				
				//e.printStackTrace();
			}
			//System.out.println("Запрос обработан");
		}
		
		

	}
	
	
	public void checkName(String name) {
		if (!Server.users.containsKey(name)) {
			try {
				//System.out.println("Сработало одобрение имени");
				userName = name;
				outputStream.writeObject(new Message(Server.getCurrentTime() + " <SERVER BOT>: " + userName + ", добро пожаловать в чат", RequestType.NAME_ACCEPT));
				outputStream.flush();
				Server.users.put(name, this);
				newUser();
				//System.out.println("Сработало одобрение имени-2");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			try {
				outputStream.writeObject(new Message(Server.getCurrentTime() +  " <SERVER BOT>: Пользователь с таким именем уже есть в чате, выберите другое имя:", RequestType.NAME_REQUEST));
				outputStream.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void nameRequest() {
		try {
			outputStream.writeObject(new Message(Server.getCurrentTime() + " <SERVER BOT>: Выберите себе имя:", RequestType.NAME_REQUEST));
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void newUser() throws IOException {
		Message message = new Message(Server.getCurrentTime() + " <SERVER BOT>: " + userName + " вошёл в чат.", RequestType.MESSAGE);
		String userList = "Онлайн (" + users.size() + "):\n";
		for (String name : users.keySet()) {
			userList+=name + "\n";
		}
		Message refUsers = new Message(userList, RequestType.REFRESH_USER_LIST);
		for (SocketListener socket : users.values()) {
			if (socket!=this) {
				socket.outputStream.writeObject(message);
				socket.outputStream.flush();
			}
			socket.outputStream.writeObject(refUsers);
			socket.outputStream.flush();
		}
		frame.apendAllText(Server.getCurrentTime() + " " + socket.getRemoteSocketAddress() + " выбрал себе имя \"" + userName + "\"");
		frame.refreshUserList(refUsers.getMessage());
	}
	
	public void exitUser() throws IOException {
		Message message;
		Message refUsers;
		if (userName!=null) {
			message = new Message(Server.getCurrentTime() + " <SERVER BOT>: " + userName + " покинул чат.", RequestType.MESSAGE);
			String userList = "Онлайн (" + users.size() + "):\n";
			for (String name : users.keySet()) {
				userList+=name + "\n";
			}
			refUsers = new Message(userList, RequestType.REFRESH_USER_LIST);
			for (SocketListener socket : users.values()) {
				if (socket!=this) {
					socket.outputStream.writeObject(message);
					socket.outputStream.flush();
					socket.outputStream.writeObject(refUsers);
					socket.outputStream.flush();
				}
			}
			addHistory(message.getMessage());
			frame.apendAllText(message.getMessage());
			frame.refreshUserList(refUsers.getMessage());
		}
		else if (userName == null) {
			message = new Message(Server.getCurrentTime() + " <SERVER BOT>: " + socket.getRemoteSocketAddress() + " отсоединился, не вошедши в чат.", RequestType.MESSAGE);
			frame.apendAllText(message.getMessage());
		}
		
		
	}
	
	public void publicMessage(String text) throws IOException {
		Message message = new Message(Server.getCurrentTime() + " " + userName + ": " + text, RequestType.MESSAGE);
		for (SocketListener socket : users.values()) {
			socket.outputStream.writeObject(message);
			socket.outputStream.flush();
		}
		frame.apendAllText(message.getMessage());
		addHistory(message.getMessage());
	}
	
	public void sendLastMessages() throws IOException {
		String text = "";
		//System.out.println("try send history");
		if (messageHistory.size()>0) {
			for (String s : messageHistory){
				text+=s+"\n";
			}
			
		}
		Message message =  new Message(text + Server.getCurrentTime() + " <SERVER BOT>: Вы присоединились к беседе." , RequestType.HISTORY);
		outputStream.writeObject(message);
		outputStream.flush();
		

	}
	
	public void addHistory(String message) {
		synchronized(messageHistory) {
			messageHistory.add(message);
			if (messageHistory.size()>10) {
				messageHistory.remove(0);
			}
		}
	}

	@Override
	public void close() throws IOException {
		inputStream.close();
		outputStream.close();
		socket.close();
		
	}
	

	
	
	
}
