package codingdojo;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompensationCalculatorTest {

    @Test
    void unionizedWatcode() {
        var briefing = new Briefing(true, false, false, false);
        var assignment = new Assignment(true, Duration.ofHours(8));
        var overtime = CompensationCalculator.calculateOvertime(new BigDecimal("5.0"), assignment, briefing);
        assertEquals(new Overtime(new BigDecimal("5.0"), BigDecimal.ZERO), overtime);
    }

}
