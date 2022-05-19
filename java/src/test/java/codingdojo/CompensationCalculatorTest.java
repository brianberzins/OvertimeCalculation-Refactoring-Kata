package codingdojo;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;
import org.lambda.query.Queryable;

import java.math.BigDecimal;
import java.time.Duration;

public class CompensationCalculatorTest {

    @Test
    void combinations() {
        BigDecimal[] overtimes = Queryable.as(-1.0, 8.0, 12.0, 20.0).select(BigDecimal::new).asArray();
        Duration[] durations =  Queryable.as(5, 10).select(Duration::ofHours).asArray();
        Boolean[] booleans = {true, false};
        CombinationApprovals.verifyAllCombinations((a, b, c, d, e, f, g) -> {
            var assignment = new Assignment(b, c);
            var briefing = new Briefing(d, e, f, g);
            return CompensationCalculator.calculateOvertime(a, assignment, briefing);
        }, overtimes, booleans, durations, booleans, booleans, booleans, booleans);
    }

    @Test
    void combinationsFromPrevious() {
        var overtimes = Queryable.as(0.0, 0.5, 1.0, 5.0, 13.0, 15.0, 17.0, 20.0).select(BigDecimal::new).asArray();
        Boolean[] booleans = new Boolean[]{true, false};
        Duration[] durations = Queryable.as(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10).select(Duration::ofHours).asArray();
        CombinationApprovals.verifyAllCombinations(
                this::callOvertime,
                overtimes, booleans, durations, booleans, booleans, booleans, booleans);
    }

    private Object callOvertime(BigDecimal bigDecimal, boolean isUnionized, Duration duration, boolean watcode, boolean z3, boolean foreign, boolean hbmo) {
        return CompensationCalculator.calculateOvertime(
                bigDecimal,
                new Assignment(isUnionized, duration),
                new Briefing(watcode, z3, foreign, hbmo)
        );
    }

}
