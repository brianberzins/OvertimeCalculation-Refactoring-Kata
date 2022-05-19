package codingdojo;

import org.approvaltests.combinations.CombinationApprovals;
import org.approvaltests.reporters.AutoApproveReporter;
import org.approvaltests.reporters.UseReporter;
import org.junit.jupiter.api.Test;
import org.lambda.query.Queryable;

import java.math.BigDecimal;
import java.time.Duration;

public class CompensationCalculatorTest {

    @Test
    @UseReporter(AutoApproveReporter.class)
    void combinations() {
        BigDecimal[] overtimes = Queryable.as(8.0).select(BigDecimal::new).asArray();
        Duration[] durations =  Queryable.as(10).select(Duration::ofHours).asArray();
        Boolean[] booleans = {true, false};
        CombinationApprovals.verifyAllCombinations((a, b, c, d, e, f, g) -> {
            var assignment = new Assignment(b, c);
            var briefing = new Briefing(d, e, f, g);
            return CompensationCalculator.calculateOvertime(a, assignment, briefing);
        }, overtimes, booleans, durations, booleans, booleans, booleans, booleans);

    }

}
