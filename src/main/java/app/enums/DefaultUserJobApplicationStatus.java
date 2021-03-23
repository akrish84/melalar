package app.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum DefaultUserJobApplicationStatus {
	WISHLIST("Wishlist", 1),
	APPLIED("Applied", 2),
	CODING("Coding", 3),
	INTERVIEW("Interview", 4),
	OFFER("Offer", 5);
	
	private String status;
	private int rank;
	
	private static final Map<String, DefaultUserJobApplicationStatus> LOOKUP = new HashMap<>();
	
	static {
		for (DefaultUserJobApplicationStatus activityEnum : EnumSet.allOf(DefaultUserJobApplicationStatus.class)) {
			LOOKUP.put(activityEnum.getStatus(), activityEnum);
		}
	}
	
	DefaultUserJobApplicationStatus(String status, int rank) {
		this.status = status;
		this.rank = rank;
	}
	
	public String getStatus(){
		return status;
	}
	
	public int getRank() {
		return rank;
	}
	
	
}
