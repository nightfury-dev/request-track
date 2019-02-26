package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Language {
    JAVA("JAVA"), PHP("PHP"), RUBY("RUBY"), FRONTEND("FRONTEND"), ANDROID("ANDROID"), NONE("None");

    private String value;

    Language(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }
}
