package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

import java.math.BigDecimal;

public class NonUnionCalculator implements OvertimeCalculator {

    public static final BigDecimal MAX_HOURS_RATE_1 = BigDecimal.valueOf(10);

    @Override
    public boolean appliesTo(BigDecimal hours, Assignment assignment, Briefing briefing) {
        return !assignment.isUnionized();
    }

    @Override
    public Overtime calculate(BigDecimal hours, Assignment assignment, Briefing briefing) {
        if (briefing.foreign() || (!briefing.watcode() && !briefing.z3())) {
            return new Overtime(hours, BigDecimal.ZERO);
        }
        var hoursRate1 = hours.min(MAX_HOURS_RATE_1);
        var hoursRate2 = hours.subtract(hoursRate1);
        return new Overtime(hoursRate1, hoursRate2);
    }
}
