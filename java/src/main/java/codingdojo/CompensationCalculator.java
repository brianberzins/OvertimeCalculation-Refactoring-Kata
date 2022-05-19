package codingdojo;

import codingdojo.overtimecalculator.DefaultOvertimeCalculator;
import codingdojo.overtimecalculator.OvertimeCalculator;

import java.math.BigDecimal;

public class CompensationCalculator {

    public static Overtime calculateOvertime(BigDecimal hoursOvertime, Assignment assignment, Briefing briefing) {
        var u = assignment.isUnionized();
        var w = briefing.watcode();
        var z = briefing.z3();
        var f = briefing.foreign();
        var h = briefing.hbmo();



        // is there even overtime?
        // does this quality for double overtime?
        // what is the maximum for double overtime?


        // non union, non z3, watcode
        // union hmbo
        // union watcode
        // not union foreign
        //
        if ((!w && !z && !u) || (h && u) || (w && u) || (f && !u) || hoursOvertime.compareTo(BigDecimal.TEN) < 1) {
            // pass
        } else {
            if (u) {
                return overtime(hoursOvertime, BigDecimal.TEN, BigDecimal.valueOf(6).min(BigDecimal.valueOf(assignment.duration().toHours())));
            } else {
                return overtime(hoursOvertime, BigDecimal.TEN, hoursOvertime);
            }
        }

        OvertimeCalculator[] overtimeCalculators = {
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
