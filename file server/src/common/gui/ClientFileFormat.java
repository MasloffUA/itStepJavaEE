package common.gui;

import java.io.File;
import java.util.Date;

public class ClientFileFormat {
	private int code;
	private String fileName;
	private int size;
	private boolean canDeleted;
	private String description;
	
	private String userName; // ��� ������������, ������������ ����
	private Date uploadDate; // ����/����� ��������
	private Integer totalDownloads; // ���������� ����������
	private Date lastDownloadDate; // ����� ��������� ��������
	
	public ClientFileFormat(ServerFileFormat serverFileFormat) {
		this.code = serverFileFormat.getCode();
		this.fileName = serverFileFormat.getFileName();
		this.size = serverFileFormat.getSize();
		this.description = serverFileFormat.getDescription();
		this.userName = serverFileFormat.getUserName();
		this.uploadDate = serverFileFormat.getUploadDate();
		this.totalDownloads = serverFileFormat.getTotalDownloads();
		this.lastDownloadDate = serverFileFormat.getLastDownloadDate();
	}
	
	
}
