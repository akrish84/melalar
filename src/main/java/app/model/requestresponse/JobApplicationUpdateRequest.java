package app.model.requestresponse;

public class JobApplicationUpdateRequest extends JobApplicationRequest {
	
	private long id;
	private int rank;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
