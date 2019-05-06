package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TitleInterview {
    HR_INTERVIEW(1, "HR Interview"),
    DIV_INTERVIEW(2, "DIV Interview"),
    EDU_INTERVIEW(2, "EDU Interview");

    private Integer code;
    private String value;

    TitleInterview(Integer code, String value) {
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
