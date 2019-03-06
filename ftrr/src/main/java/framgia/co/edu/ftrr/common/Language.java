package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
    JAVA(1, "JAVA"),
    PHP(2, "PHP"),
    RUBY(3, "RUBY"),
    FRONTEND(4, "FRONTEND"),
    ANDROID(5, "ANDROID"),
    NONE(6, "None");

    private String value;
    private Integer code;

    Language(Integer code, String value) {
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
