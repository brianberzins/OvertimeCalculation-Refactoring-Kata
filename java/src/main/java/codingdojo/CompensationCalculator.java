package codingdojo;

import codingdojo.overtimecalculator.NonUnionCalculator;
import codingdojo.overtimecalculator.OvertimeCalculator;
import codingdojo.overtimecalculator.UnionOvertimeCalculator;

public class CompensationCalculator {

    public static Overtime calculateOvertime(double hoursOvertime, Assignment assignment, Briefing briefing) {
        hoursOvertime = Double.max(0.d, hoursOvertime);
        OvertimeCalculator overtimeCalculator = assignment.isUnionized() ? new UnionOvertimeCalculator() : new NonUnionCalculator();
        return overtimeCalculator.calculate(hoursOvertime, assignment, briefing);
    }

}
