package codingdojo.overtimecalculator;

import codingdojo.Assignment;
import codingdojo.Briefing;
import codingdojo.Overtime;

public interface OvertimeCalculator {

    Overtime calculate(double hours, Assignment assignment, Briefing briefing);

}
