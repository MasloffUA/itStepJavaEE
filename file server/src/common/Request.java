package common;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import common.gui.ServerFileFormat;

// ������-������, ������� �������� �� ������� � ������� � ������� � ����� ���������, �� ��� ����������.
// ����������� �� �� ���� ������� (��� ����� � ���� ��� ���������).
public class Request implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private RequestType type;
	private String messageHead;
	private String messageData; // �������������� ���������� (�������� ��� ��������� ����)
	private ServerFileFormat serverFileFormat; // ��� �������� ����� � �������

	private boolean accepted;
	private byte[] fileInBytes;
	private static AtomicInteger item = new AtomicInteger(0);
	private int numberOfRequest;
	
	private Map<Integer, ServerFileFormat> fileList; // ��� �������� ������ ������
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

	public String getMessageHead() {
		return messageHead;
	}

	public void setMessageHead(String messageHead) {
		this.messageHead = messageHead;
	}

	public String getMessageData() {
		return messageData;
	}

	public void setMessageData(String messageData) {
		this.messageData = messageData;
	}
	
	
	
	
}
