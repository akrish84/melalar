package app.log;

public class LogMessageBuilder {

	private static final String MESSAGE_TEMPALTE = "[Operation: %s][%s][UserId: %s][Message: %s]";
	private static final String SUCCESS_TAG = "SUCCESS";
	private static final String EXCEPTION_TAG = "EXCEPTION";
	
	private String operation;
	private long userId;
	
	public LogMessageBuilder(String operation, long userId) {
		this.operation = operation;
		this.userId = userId;
		
	}
	
	public String buildRequestStartMessage() {
		return String.format(MESSAGE_TEMPALTE, operation, SUCCESS_TAG, userId, "Request Start");
	}

	public String buildSuccessMessage(String message) {
		return String.format(MESSAGE_TEMPALTE, operation, SUCCESS_TAG, userId, message);
	}
	
	public String buildExceptionMessage(String message) {
		return String.format(MESSAGE_TEMPALTE, operation, EXCEPTION_TAG, userId, message);
	}

}
