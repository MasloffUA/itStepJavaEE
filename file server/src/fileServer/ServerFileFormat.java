package fileServer;

import java.io.File;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerFileFormat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AtomicInteger atomicInteger = new AtomicInteger(1100);
	private int code;
	private File file;
	private String name;
	private int size;
	private boolean canDeleted;
	private String description;
	
	public ServerFileFormat(File file) {
		this.file = file;
		name = file.getName();
		size = (int) (file.length() / 1024);
		code = atomicInteger.incrementAndGet();
		description="";
	}
	
	public ServerFileFormat(File file, boolean canDeleted) {
		this.file = file;
		name = file.getName();
		size = (int) (file.length() / 1024);
		this.canDeleted = canDeleted;
	}

	public boolean isCanDeleted() {
		return canDeleted;
	}

	public void setCanDeleted(boolean canDeleted) {
		this.canDeleted = canDeleted;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	


	public File getFile() {
		return file;
	}
	
	
	
	

	
}
