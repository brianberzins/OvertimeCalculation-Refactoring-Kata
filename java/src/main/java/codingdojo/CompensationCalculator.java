package codingdojo;

import java.math.BigDecimal;

public class CompensationCalculator {

    public final static BigDecimal MAX_OVERTIME_HOURS_RATE_1 = BigDecimal.TEN;
    public static final int THRESHOLD_OVERTIME_HOURS_RATE_2 = 6;

    public static Overtime calculateOvertime(BigDecimal hoursOvertimeTotal, Assignment assignment, Briefing briefing) {
        if (!briefing.watcode() && !briefing.z3() && !assignment.isUnionized()) {
            return new Overtime(hoursOvertimeTotal, BigDecimal.ZERO);
        } else if (briefing.hbmo() && assignment.isUnionized()) {
            return new Overtime(hoursOvertimeTotal, BigDecimal.ZERO);
        } else if (briefing.watcode() && !assignment.isUnionized() && briefing.foreign()) {
            return new Overtime(hoursOvertimeTotal, BigDecimal.ZERO);
        } else if (briefing.watcode() && assignment.isUnionized()) {
            return new Overtime(hoursOvertimeTotal, BigDecimal.ZERO);
        } else if (briefing.foreign() && !assignment.isUnionized()) {
            return new Overtime(hoursOvertimeTotal, BigDecimal.ZERO);
        } else if (hoursOvertimeTotal.compareTo(BigDecimal.ZERO) < 1) {
            return new Overtime(BigDecimal.ZERO, BigDecimal.ZERO);
        } else if (hoursOvertimeTotal.compareTo(MAX_OVERTIME_HOURS_RATE_1) < 1) {
            return new Overtime(hoursOvertimeTotal, BigDecimal.ZERO);
        } else if (assignment.isUnionized() && assignment.duration().minusHours(THRESHOLD_OVERTIME_HOURS_RATE_2).isNegative()) {
            BigDecimal hoursOvertimeRate2 = hoursOvertimeTotal.subtract(MAX_OVERTIME_HOURS_RATE_1);
            BigDecimal threshold = BigDecimal.valueOf(assignment.duration().toSeconds() / 3600);
            hoursOvertimeRate2 = hoursOvertimeRate2.min(threshold);
            return new Overtime(MAX_OVERTIME_HOURS_RATE_1, hoursOvertimeRate2);
        } else if (assignment.isUnionized() && !(assignment.duration().minusHours(THRESHOLD_OVERTIME_HOURS_RATE_2).isNegative())) {
            BigDecimal hoursOvertimeRate2 = hoursOvertimeTotal.subtract(MAX_OVERTIME_HOURS_RATE_1);
            BigDecimal threshold = BigDecimal.valueOf(THRESHOLD_OVERTIME_HOURS_RATE_2);
            hoursOvertimeRate2 = hoursOvertimeRate2.min(threshold);
            return new Overtime(MAX_OVERTIME_HOURS_RATE_1, hoursOvertimeRate2);
        } else {
            BigDecimal hoursOvertimeRate2 = hoursOvertimeTotal.subtract(MAX_OVERTIME_HOURS_RATE_1);
            return new Overtime(MAX_OVERTIME_HOURS_RATE_1, hoursOvertimeRate2);
        }
    }

}
