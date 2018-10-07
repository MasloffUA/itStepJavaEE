package ua.step.example;

public class User {
	private int id;
	private String login;
	private String password;
	private boolean admin;
	
	public User(int id, String login, String password, boolean admin) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.admin = admin;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return admin;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", ");
		if (login != null) {
			builder.append("login=");
			builder.append(login);
			builder.append(", ");
		}
		if (password != null) {
			builder.append("password=");
			builder.append(password);
			builder.append(", ");
		}
		builder.append("admin=");
		builder.append(admin);
		builder.append("]");
		return builder.toString();
	}
	
}
