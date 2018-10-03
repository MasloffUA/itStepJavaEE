package client;

public class ServerLimits {
	private int totalUploadLimit = -1;
	private int fileUploadLimit = -1;
	private boolean canUpload = false;
	
	public ServerLimits(ServerLimits serverLimits) {
		this.totalUploadLimit = serverLimits.getTotalUploadLimit();
		this.fileUploadLimit = serverLimits.getFileUploadLimit();
		this.canUpload = serverLimits.canUpload;
	}
	
	public int getTotalUploadLimit() {
		return totalUploadLimit;
	}
	public void setTotalUploadLimit(int totalUploadLimit) {
		this.totalUploadLimit = totalUploadLimit;
	}
	public int getFileUploadLimit() {
		return fileUploadLimit;
	}
	
	// ���������� ��� �������� ����������� �� ������
	// ��������� ����� � Server �����
	public void setFileUploadLimit(int fileUploadLimit) {
		this.fileUploadLimit = fileUploadLimit;
	}
	public boolean isCanUpload() {
		return canUpload;
	}
	public void setCanUpload(boolean canUpload) {
		this.canUpload = canUpload;
	}
	
	
}
