package app.model.requestresponse;

import lombok.Builder;

@Builder
public class UserJobApplicationStatusResponse {

	private long id;
	
	private String statusName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
