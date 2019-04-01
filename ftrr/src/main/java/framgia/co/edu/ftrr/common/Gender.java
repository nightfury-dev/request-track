package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    FEMALE(1, "female"),
    MALE(2, "male");

    private String value;
    private Integer code;

    Gender(Integer code, String value) {
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
