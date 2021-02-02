package app.model.requestresponse;

public class JobApplicationResponse {
	
	private String message;
	
	public JobApplicationResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
