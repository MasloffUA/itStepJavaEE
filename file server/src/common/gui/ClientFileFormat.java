package common.gui;

import java.io.File;
import java.util.Date;

public class ClientFileFormat {
	private int code;
	private String fileName;
	private int size;
	private boolean canDeleted;
	private String description;
	
	private String userName; // имя пользователя, загрузившего файл
	private Date uploadDate; // дата/время загрузки
	private Integer totalDownloads; // количество скачиваний
	private Date lastDownloadDate; // время последней загрузки
	
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
