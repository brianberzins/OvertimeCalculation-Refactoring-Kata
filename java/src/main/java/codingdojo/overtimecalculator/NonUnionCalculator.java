package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

public class NonUnionCalculator implements OvertimeCalculator {

    public static final double MAX_HOURS_RATE_1 = 10d;

    @Override
    public Overtime calculate(double hours, Assignment assignment, Briefing briefing) {
        if (briefing.foreign() || (!briefing.watcode() && !briefing.z3())) {
            return new Overtime(hours, 0d);
        }
        return new Overtime(Double.min(hours, MAX_HOURS_RATE_1), hours - Double.min(hours, MAX_HOURS_RATE_1));
    }
}
