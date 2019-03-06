package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Division {
    DIV_1(1, "Division 1"),
    DIV_2(2, "Division 2"),
    DIV_3(3, "Division 3"),
    BO(4, "BackOffice"),
    HD(5, "Human Development Division"),
    NONE(6, "None");
    private Integer code;
    private String value;

    Division(Integer code, String value) {
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
