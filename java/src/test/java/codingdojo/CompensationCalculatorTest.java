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
        BigDecimal[] a = Queryable.as(8.0).select(BigDecimal::new).asArray();
        Assignment[] b = {new Assignment(false, Duration.ofHours(10))};
        Briefing[] c = {new Briefing(false, false, true, true)};
        CombinationApprovals.verifyAllCombinations(CompensationCalculator::calculateOvertime, a, b, c);
    }

}
