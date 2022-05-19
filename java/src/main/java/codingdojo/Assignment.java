package codingdojo;

import java.time.Duration;

public record Assignment(boolean isUnionized, Duration duration) {
    public double inHours() {
        return duration().toHours();
    }
}
