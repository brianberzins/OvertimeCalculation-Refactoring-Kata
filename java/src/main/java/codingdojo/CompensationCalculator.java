package codingdojo;

import java.math.BigDecimal;

public class CompensationCalculator {

    public final static BigDecimal MAX_OVERTIME_HOURS_RATE_1 = BigDecimal.TEN;
    public static final int THRESHOLD_OVERTIME_HOURS_RATE_2 = 6;

    public static Overtime calculateOvertime(BigDecimal hoursOvertime, Assignment assignment, Briefing briefing) {
        if (!briefing.watcode() && !briefing.z3() && !assignment.isUnionized()) {
            return singleRateOvertime(hoursOvertime);
        } else if (briefing.hbmo() && assignment.isUnionized()) {
            return singleRateOvertime(hoursOvertime);
        } else if (briefing.watcode() && !assignment.isUnionized() && briefing.foreign()) {
            return singleRateOvertime(hoursOvertime);
        } else if (briefing.watcode() && assignment.isUnionized()) {
            return singleRateOvertime(hoursOvertime);
        } else if (briefing.foreign() && !assignment.isUnionized()) {
            return singleRateOvertime(hoursOvertime);
        } else if (hoursOvertime.compareTo(BigDecimal.ZERO) < 1) {
            return singleRateOvertime(BigDecimal.ZERO);
        } else if (hoursOvertime.compareTo(MAX_OVERTIME_HOURS_RATE_1) < 1) {
            return singleRateOvertime(hoursOvertime);
        } else if (assignment.isUnionized() && assignment.duration().minusHours(THRESHOLD_OVERTIME_HOURS_RATE_2).isNegative()) {
            return new Overtime(MAX_OVERTIME_HOURS_RATE_1, hoursOvertime.subtract(MAX_OVERTIME_HOURS_RATE_1).min(BigDecimal.valueOf(assignment.duration().toSeconds() / 3600)));
        } else if (assignment.isUnionized() && !(assignment.duration().minusHours(THRESHOLD_OVERTIME_HOURS_RATE_2).isNegative())) {
            return new Overtime(MAX_OVERTIME_HOURS_RATE_1, hoursOvertime.subtract(MAX_OVERTIME_HOURS_RATE_1).min(BigDecimal.valueOf(THRESHOLD_OVERTIME_HOURS_RATE_2)));
        } else {
            return new Overtime(MAX_OVERTIME_HOURS_RATE_1, hoursOvertime.subtract(MAX_OVERTIME_HOURS_RATE_1));
        }
    }

    private static Overtime singleRateOvertime(BigDecimal hoursOvertimeTotal) {
        return new Overtime(hoursOvertimeTotal, BigDecimal.ZERO);
    }

}
