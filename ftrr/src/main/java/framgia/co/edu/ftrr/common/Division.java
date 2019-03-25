package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum Division {
    DIV_1(1, "Division 1"),
    DIV_2(2, "Division 2"),
    DIV_3(3, "Division 3"),
    BO(4, "BackOffice"),
    HD(5, "Human Development Division"),
    NONE(6, "None"),
    HR(7, "Human Resource");
    private Integer code;
    private String value;

    Division(Integer code, String value) {
        this.value = value;
        this.code = code;
    }

    public static Optional<Division> valueOf(int code) {
        return Arrays.stream(values())
                .filter(division -> division.code == code)
                .findFirst();
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @JsonValue
    public Integer getCode() {
        return this.code;
    }

    public Boolean isDiv() {
        return this == DIV_1 || this == DIV_2 || this == DIV_3;
    }

    public Boolean isHr() {
        return this == HR;
    }

    public Boolean isHd() {
        return this == HD;
    }
}
