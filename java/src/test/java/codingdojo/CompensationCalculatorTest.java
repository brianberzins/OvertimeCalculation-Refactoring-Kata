package codingdojo;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;
import org.lambda.query.Queryable;

import java.time.Duration;

public class CompensationCalculatorTest {

    @Test
    void combinations() {
        Double[] overtimes = {-1d, 8d, 12d, 20d};
        Duration[] durations =  Queryable.as(5, 10).select(Duration::ofHours).asArray();
        Boolean[] booleans = {true, false};
        CombinationApprovals.verifyAllCombinations((a, b, c, d, e, f, g) -> {
            var assignment = new Assignment(b, c);
            var briefing = new Briefing(d, e, f, g);
            return CompensationCalculator.calculateOvertime(a, assignment, briefing);
        }, overtimes, booleans, durations, booleans, booleans, booleans, booleans);
    }

}
