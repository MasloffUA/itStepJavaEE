package common.gui;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import server.gui.ServerTableModel;

public class ServerFileFormat implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static AtomicInteger atomicInteger = new AtomicInteger(1100);
	private int code;
	private File file;
	private String fileName;
	private int size;
	private boolean canDeleted;
	private String description;
	
	private String userName; // имя пользователя, загрузившего файл
	private Date uploadDate; // дата/время загрузки
	private Integer totalDownloads; // количество скачиваний
	private Date lastDownloadDate; // время последней загрузки
	
	public ServerFileFormat(File file) {
		this.file = file;
		fileName = file.getName();
		size = (int) (file.length() / 1024);
		code = atomicInteger.incrementAndGet();
		description="";
	}
	
	public ServerFileFormat(File file, boolean canDeleted) {
		this.file = file;
		fileName = file.getName();
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
		return fileName;
	}

	public int getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = null;
		this.description = description;
	}

	public String getFileName() {
		return fileName;
	}

	public File getFile(ServerTableModel m) {
		return file;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public Integer getTotalDownloads() {
		return totalDownloads;
	}

	public void setTotalDownloads(Integer totalDownloads) {
		this.totalDownloads = totalDownloads;
	}

	public Date getLastDownloadDate() {
		return lastDownloadDate;
	}

	public void setLastDownloadDate(Date lastDownloadDate) {
		this.lastDownloadDate = lastDownloadDate;
	}	
	
	
	
}
