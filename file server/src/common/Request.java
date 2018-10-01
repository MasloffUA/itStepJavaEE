package common;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import common.gui.ServerFileFormat;

// Объект-запрос, который гоняется от сервера к клиенту и обратно и может содержать, всё что необходимо.
// Распознаётся по по типу запроса (как делал в чате тип сообщений).
public class Request implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RequestType type;
	private String message; // дополнительная информация (возможно для дилоговых окон)
	private ServerFileFormat serverFileFormat; // для передачи файла в запросе

	private boolean accepted;
	private byte[] fileInBytes;
	private static AtomicInteger item = new AtomicInteger(0);
	private int numberOfRequest;
	
	private Map<Integer, ServerFileFormat> fileList; // для передачи списка файлов
	private Set<Integer> fileOperationList;
	private Set<Integer> canDeleted;
	private File file;

	public Request(RequestType type) {
		super();
		this.numberOfRequest = item.incrementAndGet();
		this.type = type;
	}

	public Set<Integer> getFileOperationList() {
		return fileOperationList;
	}

	
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileOperationList(Set<Integer> fileOperationList) {
		this.fileOperationList = fileOperationList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ServerFileFormat getServerFileFormat() {
		return serverFileFormat;
	}

	public void setServerFileFormat(ServerFileFormat serverFileFormat) {
		this.serverFileFormat = serverFileFormat;
	}

	public Map<Integer, ServerFileFormat> getFileList() {
		return fileList;
	}

	public void setFileList(Map<Integer, ServerFileFormat> sharedFileList) {
		this.fileList = sharedFileList;
	}

	public RequestType getType() {
		return type;
	}

	public byte[] getFileInBytes() {
		return fileInBytes;
	}

	public void setFileInBytes(byte[] fileInBytes) {
		this.fileInBytes = fileInBytes;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public Set<Integer> getCanDeleted() {
		return canDeleted;
	}

	public void setCanDeleted(Set<Integer> canDeleted) {
		this.canDeleted = canDeleted;
	}
	
	
	
	
}
