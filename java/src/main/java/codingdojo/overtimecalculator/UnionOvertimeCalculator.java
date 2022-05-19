package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

public class UnionOvertimeCalculator implements OvertimeCalculator {

    @Override
    public Overtime calculate(double hours, Assignment assignment, Briefing briefing) {
        if (briefing.watcode() || briefing.hbmo()) {
            return new Overtime(hours, 0d);
        }

        return twoRateOvertime(hours, assignment.inHours(), 6d);
    }

    private Overtime twoRateOvertime(double hours, double assignmentHours, double maxHoursRate2) {
        var hoursRate1 = Double.min(hours, 10d);
        var hoursRate2 = Double.min(Double.min(hours - hoursRate1, maxHoursRate2), assignmentHours);
        return new Overtime(hoursRate1, hoursRate2);
    }

}