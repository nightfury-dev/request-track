package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FinalResultStatus {
    JOIN(1, "JoinDIV"),
    WAITING(2, "Waiting"),
    EXTEND(3, "Extend"),
    FAIL(4, "Fail");

    private String value;
    private Integer code;

    FinalResultStatus(Integer code, String value) {
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
