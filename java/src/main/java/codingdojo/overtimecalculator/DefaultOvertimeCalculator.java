package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

import java.math.BigDecimal;

public class DefaultOvertimeCalculator implements OvertimeCalculator {

    @Override
    public boolean appliesTo(BigDecimal hours, Assignment assignment, Briefing briefing) {
        return true;
    }

    @Override
    public Overtime calculate(BigDecimal hours, Assignment assignment, Briefing briefing) {
        return new Overtime(hours, BigDecimal.ZERO);
    }
}
