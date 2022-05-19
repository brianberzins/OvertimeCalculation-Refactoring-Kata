package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

import java.util.stream.Stream;

public class UnionOvertimeCalculator implements OvertimeCalculator {

    public static final double MAX_HOURS_RATE_1 = 10d;
    public static final double MAX_HOURS_RATE_2 = 6d;

    @Override
    public Overtime calculate(double hours, Assignment assignment, Briefing briefing) {
        if (briefing.watcode() || briefing.hbmo()) {
            return new Overtime(hours, 0d);
        }
        var hoursRate1 = Double.min(hours, MAX_HOURS_RATE_1);
        var hoursRate2 = Stream.of(hours - hoursRate1, MAX_HOURS_RATE_2, assignment.inHours())
                .min(Double::compareTo).get();
        return new Overtime(hoursRate1, hoursRate2);
    }

}