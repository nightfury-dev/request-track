package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum  RequestStatus {
    // Request just created
    WAITING("Waiting"),
    // Request confirmed between division and education
    CONFIRMED("Confirmed"),

    // Scheduling for interview
    IN_PROGRESS("In progress"),
    // Interviewing
    INTERVIEW("Interview"),
    // Finished interview
    DONE("Done"),
    // Education doesn't has trainees or division has no need
    PENDING("Pending"),
    // Education reject request
    REJECT("Reject");

	private String value;

	RequestStatus(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return this.value;
	}

}
