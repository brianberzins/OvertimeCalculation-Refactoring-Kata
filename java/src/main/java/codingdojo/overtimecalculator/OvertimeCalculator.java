package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

import java.math.BigDecimal;

public interface OvertimeCalculator {

    boolean appliesTo(BigDecimal hours, Assignment assignment, Briefing briefing);
    Overtime calculate(BigDecimal hours, Assignment assignment, Briefing briefing);

}
