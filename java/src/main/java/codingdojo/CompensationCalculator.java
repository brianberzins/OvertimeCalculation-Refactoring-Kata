package codingdojo;

import codingdojo.overtimecalculator.OvertimeCalculator;

import java.math.BigDecimal;

public class CompensationCalculator {

    public static Overtime calculateOvertime(BigDecimal hoursOvertime, Assignment assignment, Briefing briefing) {
        var u = assignment.isUnionized();
        var w = briefing.watcode();
        var z = briefing.z3();
        var f = briefing.foreign();
        var h = briefing.hbmo();

        OvertimeCalculator[] overtimeCalculators = {};
        for (OvertimeCalculator overtimeCalculator : overtimeCalculators) {
            if (overtimeCalculator.appliesTo(hoursOvertime, assignment, briefing)) {
                return overtimeCalculator.calculate(hoursOvertime, assignment, briefing);
            }
        }

        // is there even overtime?
        // does this quality for double overtime?
        // what is the maximum for double overtime?

        if ((!w && !z && !u) || (h && u) || (w && u) || (f && !u) || hoursOvertime.compareTo(BigDecimal.TEN) < 1) {
            return singleRateOvertime(hoursOvertime);
        } else if (!u) {
            return twoRateOvertime(hoursOvertime);
        } else if (assignment.duration().minusHours(6).isNegative()) {
            return twoRateOvertime(hoursOvertime, BigDecimal.valueOf(assignment.duration().toHours()));
        } else {
            return twoRateOvertime(hoursOvertime, BigDecimal.valueOf(6));
        }
    }

    private static Overtime twoRateOvertime(BigDecimal hoursOvertime) {
        return twoRateOvertime(hoursOvertime, hoursOvertime);
    }

    private static Overtime twoRateOvertime(BigDecimal hoursOvertime, BigDecimal maximumDoubleOvertime) {
        return new Overtime(BigDecimal.TEN, hoursOvertime.subtract(BigDecimal.TEN).min(maximumDoubleOvertime));
    }

    private static Overtime singleRateOvertime(BigDecimal hoursOvertimeTotal) {
        return new Overtime(BigDecimal.ZERO.max(hoursOvertimeTotal), BigDecimal.ZERO);
    }

}
