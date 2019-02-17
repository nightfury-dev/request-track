package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Roles {
    M("Manager"), SM("Section Manager"), ESM("Section Manager Education"),
    HR("Human Resource"), EC("Education Coordinator"), TN("Trainer");

    private String value;

    Roles(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
