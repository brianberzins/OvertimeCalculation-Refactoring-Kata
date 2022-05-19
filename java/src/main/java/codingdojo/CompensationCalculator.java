package codingdojo;

import codingdojo.overtimecalculator.DefaultOvertimeCalculator;
import codingdojo.overtimecalculator.NonUnionCalculator;
import codingdojo.overtimecalculator.OvertimeCalculator;
import codingdojo.overtimecalculator.UnionOvertimeCalculator;

import java.math.BigDecimal;

public class CompensationCalculator {

    public static Overtime calculateOvertime(BigDecimal hoursOvertime, Assignment assignment, Briefing briefing) {
        hoursOvertime = BigDecimal.ZERO.max(hoursOvertime);
        OvertimeCalculator[] overtimeCalculators = {
                new UnionOvertimeCalculator(),
                new NonUnionCalculator(),
                new DefaultOvertimeCalculator(),
        };
        for (OvertimeCalculator overtimeCalculator : overtimeCalculators) {
            if (overtimeCalculator.appliesTo(hoursOvertime, assignment, briefing)) {
                return overtimeCalculator.calculate(hoursOvertime, assignment, briefing);
            }
        }
        throw new RuntimeException("unhandled overtime calculation");
    }

    private static Overtime overtime(BigDecimal hours, BigDecimal maxRate1Overtime, BigDecimal maxRate2Overtime) {
        var hoursRate1 = BigDecimal.ZERO.max(hours).min(maxRate1Overtime);
        var hoursRate2 = hours.subtract(hoursRate1).min(maxRate2Overtime);
        return new Overtime(hoursRate1, hoursRate2);
    }

}
