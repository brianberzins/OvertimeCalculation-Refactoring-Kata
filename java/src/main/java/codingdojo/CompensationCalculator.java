package codingdojo;

import codingdojo.overtimecalculator.NonUnionCalculator;
import codingdojo.overtimecalculator.OvertimeCalculator;
import codingdojo.overtimecalculator.UnionOvertimeCalculator;

import java.math.BigDecimal;

public class CompensationCalculator {

    public static Overtime calculateOvertime(BigDecimal hoursOvertime, Assignment assignment, Briefing briefing) {
        hoursOvertime = BigDecimal.ZERO.max(hoursOvertime);
        OvertimeCalculator overtimeCalculator = assignment.isUnionized() ? new UnionOvertimeCalculator() : new NonUnionCalculator();
        return overtimeCalculator.calculate(hoursOvertime, assignment, briefing);
    }

}
