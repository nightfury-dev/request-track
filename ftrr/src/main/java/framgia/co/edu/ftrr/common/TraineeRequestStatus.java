package framgia.co.edu.ftrr.common;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum TraineeRequestStatus {
    REJECT(1, "Reject"),
    WAITING_CONFIRM(2, "Waiting confirm"),
    PROCESSING(3, "Processing"),
    FINISH(4, "Finish"),
    OTHER(5, "Other");

    private Integer value;
    private String name;

    TraineeRequestStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public static Optional<TraineeRequestStatus> valueOf(int code) {
        return Arrays.stream(values())
                .filter(traineeRequestStatus -> traineeRequestStatus.value == code)
                .findFirst();
    }

    @JsonValue
    public Integer getValue() {
        return this.value;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }
}
