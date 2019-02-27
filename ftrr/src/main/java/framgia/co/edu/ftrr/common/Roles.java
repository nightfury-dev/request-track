package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
    SM("Section Manager"),
    DM("Division Manager"),
    HR("Human Resource"),
    EC("Education Coordinator"),
    TRAINER("Trainer"),
    GL("Group leader"),
    OTHER("Employee");

    private String value;

    Roles(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
