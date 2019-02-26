package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Division {
    DIV_1("Division 1"), DIV_2("Division 2"), DIV_3("Division 3"), BO("BackOffice"), HD("Human Development Division"), NONE("None");

    private String value;

    Division(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

}
