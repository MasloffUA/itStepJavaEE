package fileServer;

public enum RequestType {
	// Запросы, отправляемые клиентом
	GET_FILE_LIST,
	GET_FILE, //unused
	DELETE_FILE,//unused
	UPLOAD_FILE,//unused
	
	// Запросы, отправляемые сервером
	SEND_FILE_DESCRIPTION,
	ACCEPTED,//unused
	SEND_FILE_LIST,
	SEND_FILE,//unused
	CAN_UPLOAD,
	FORBID_UPLOAD,
	CONNECTED,
	FORBIDED_UPLOAD_MAX_SIZE,//unused
	FORBIDED_UPLOAD_TOTAL_SPACE,//unused
	
	// Оба направления
	FILE; //unused
}
