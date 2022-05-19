package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

public class NonUnionCalculator implements OvertimeCalculator {

    @Override
    public Overtime calculate(double hours, Assignment assignment, Briefing briefing) {
        if (briefing.foreign() || (!briefing.watcode() && !briefing.z3())) {
            return new Overtime(hours, 0d);
        }
        var maxHoursRate1 = 10d;
        return new Overtime(Double.min(hours, maxHoursRate1), hours - Double.min(hours, maxHoursRate1));
    }
}
