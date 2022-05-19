package codingdojo;

import java.math.BigDecimal;
import java.time.Duration;

public record Assignment(boolean isUnionized, Duration duration) {
    public BigDecimal inHours() {
        return BigDecimal.valueOf(duration().toHours());
    }
}
