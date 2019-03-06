package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum NotificationStatus {
    UNSEEN(1, "Unseen"),
    SEEN(2, "Seen");

    private String value;
    private Integer code;

    NotificationStatus(Integer code, String value) {
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
