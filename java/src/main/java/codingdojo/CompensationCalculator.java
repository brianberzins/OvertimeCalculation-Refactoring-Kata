package codingdojo;

import java.math.BigDecimal;
import java.time.Duration;

public class CompensationCalculator {

    public final static BigDecimal MAX_OVERTIME_HOURS_RATE_1 = BigDecimal.TEN;
    public static final int THRESHOLD_OVERTIME_HOURS_RATE_2 = 6;

    public static Overtime calculateOvertime(BigDecimal hoursOvertimeTotal, Assignment assignment, Briefing briefing) {
        if (
                (! briefing.watcode() && ! briefing.z3() && !assignment.isUnionized())
                        || (briefing.hbmo() && assignment.isUnionized())
                        || briefing.watcode() && !assignment.isUnionized() && briefing.foreign()
                        || briefing.watcode() && assignment.isUnionized()
                        || (briefing.foreign() && !assignment.isUnionized())
        ) {
            BigDecimal hoursOvertimeRate1 = BigDecimal.ZERO;
            BigDecimal hoursOvertimeRate2 = BigDecimal.ZERO;
            hoursOvertimeRate1 = hoursOvertimeTotal;
            return new Overtime(hoursOvertimeRate1, hoursOvertimeRate2);
        } else {
            if (hoursOvertimeTotal.compareTo(BigDecimal.ZERO) < 1) {
                BigDecimal hoursOvertimeRate1 = BigDecimal.ZERO;
                BigDecimal hoursOvertimeRate2 = BigDecimal.ZERO;
                return new Overtime(hoursOvertimeRate1, hoursOvertimeRate2);
            } else if (hoursOvertimeTotal.compareTo(MAX_OVERTIME_HOURS_RATE_1) < 1) {
                BigDecimal hoursOvertimeRate1 = BigDecimal.ZERO;
                BigDecimal hoursOvertimeRate2 = BigDecimal.ZERO;
                hoursOvertimeRate1 = hoursOvertimeTotal;
                return new Overtime(hoursOvertimeRate1, hoursOvertimeRate2);
            } else if (assignment.isUnionized()) {
                if (assignment.duration().minusHours(THRESHOLD_OVERTIME_HOURS_RATE_2).isNegative()) {
                    BigDecimal hoursOvertimeRate1 = BigDecimal.ZERO;
                    BigDecimal hoursOvertimeRate2 = BigDecimal.ZERO;
                    hoursOvertimeRate1 = MAX_OVERTIME_HOURS_RATE_1;
                    hoursOvertimeRate2 = hoursOvertimeTotal.subtract(MAX_OVERTIME_HOURS_RATE_1);
                    BigDecimal threshold;
                    threshold = BigDecimal.valueOf(assignment.duration().toSeconds() / 3600);
                    hoursOvertimeRate2 = hoursOvertimeRate2.min(threshold);
                    return new Overtime(hoursOvertimeRate1, hoursOvertimeRate2);
                } else {
                    BigDecimal hoursOvertimeRate1 = BigDecimal.ZERO;
                    BigDecimal hoursOvertimeRate2 = BigDecimal.ZERO;
                    hoursOvertimeRate1 = MAX_OVERTIME_HOURS_RATE_1;
                    hoursOvertimeRate2 = hoursOvertimeTotal.subtract(MAX_OVERTIME_HOURS_RATE_1);
                    BigDecimal threshold;
                    threshold = BigDecimal.valueOf(THRESHOLD_OVERTIME_HOURS_RATE_2);
                    hoursOvertimeRate2 = hoursOvertimeRate2.min(threshold);
                    return new Overtime(hoursOvertimeRate1, hoursOvertimeRate2);
                }
            } else {
                BigDecimal hoursOvertimeRate1 = BigDecimal.ZERO;
                BigDecimal hoursOvertimeRate2 = BigDecimal.ZERO;
                hoursOvertimeRate1 = MAX_OVERTIME_HOURS_RATE_1;
                hoursOvertimeRate2 = hoursOvertimeTotal.subtract(MAX_OVERTIME_HOURS_RATE_1);
                return new Overtime(hoursOvertimeRate1, hoursOvertimeRate2);
            }
        }
    }

}
