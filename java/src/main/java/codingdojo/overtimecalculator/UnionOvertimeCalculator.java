package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

import java.math.BigDecimal;

public class UnionOvertimeCalculator implements OvertimeCalculator {

    public static final BigDecimal MAX_HOURS_RATE_1 = BigDecimal.valueOf(10);
    public static final BigDecimal MAX_HOURS_RATE_2 = BigDecimal.valueOf(6);

    @Override
    public Overtime calculate(BigDecimal hours, Assignment assignment, Briefing briefing) {
        if (briefing.watcode() || briefing.hbmo()) {
            return new Overtime(hours, BigDecimal.ZERO);
        }
        var hoursRate1 = hours.min(MAX_HOURS_RATE_1);
        var hoursRate2 = hours.subtract(hoursRate1)
                .min(MAX_HOURS_RATE_2)
                .min(assignment.inHours());
        return new Overtime(hoursRate1, hoursRate2);
    }
}
