package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RequestStatus {
    // Request just created
    WAITING(1, "Waiting"),
    // Request confirmed between division and education
    CONFIRMED(2, "Confirmed"),
    // Scheduling for interview
    IN_PROGRESS(3, "In progress"),
    // Interviewing
    INTERVIEW(4, "Interview"),
    // Finished interview
    DONE(5, "Done"),
    // Education doesn't has trainees or division has no need
    PENDING(6, "Pending"),
    // Education reject request
    REJECT(7, "Reject");

    private String value;
    private Integer code;

    RequestStatus(Integer code, String value) {
        this.value = value;
        this.code = code;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

}
