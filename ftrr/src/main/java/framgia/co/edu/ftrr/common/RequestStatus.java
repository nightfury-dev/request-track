package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RequestStatus {

	WAITING("Waiting"), CONFIRMED("Confirmed"), IN_PROGRESS("In progress"), INTERVIEW("Interview"), DONE("Done"),
	PENDING("Pending"), REJECT("Reject");

	private String value;

	RequestStatus(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return this.value;
	}

}
