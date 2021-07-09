package ca.anmolbrar.queue;

import java.util.Arrays;

public enum QueueStatus {

    OPEN,
    PAUSED,
    CLOSED;

    public static boolean isValidStatus(String name) {
        return Arrays.stream(values()).map(Enum::name).anyMatch(value -> value.equalsIgnoreCase(name));
    }
}
