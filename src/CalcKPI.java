import java.math.BigDecimal;

/**
 * Created by gdr on 9/5/15.
 */
public class CalcKPI {
    // Variables
    private double pressureIncome;
    private double pressureOutcome;
    private double consumption;
    private double density;
    private double amperage;
    private double power;
    // Constants
    private double gravity = 9.87;
    private double z = 0.6;
    private double d = 0;

    public CalcKPI(double pressureIncome,
                   double pressureOutcome,
                   double consumption,
                   double density,
                   double amperage,
                   double power) {
        this.pressureIncome = pressureIncome;
        this.pressureOutcome = pressureOutcome;
        this.consumption = consumption;
        this.density = density;
        this.amperage = amperage;
        this.power = power;
    }

    private double calcDivisor() {
        double temp1 = density * (consumption / 3600);
        return temp1 * ((pressureOutcome - pressureIncome) / (consumption * gravity));
    }

    private double calcSyncDivider() {
        return 102 * (power * 200 * 100 * 0.001 - (1) + );
    }

    private double calcASyncDivider() {
        return 1;
    }

    private double calcAggregateDivider() {
        return 1;
    }

    public double calcSync() {
        return calcDivisor() / calcSyncDivider();
    }

    public double calcASync() {
        return calcDivisor() / calcASyncDivider();
    }

    public double calcAggregate() {
        return calcDivisor() / calcAggregateDivider();
    }
}
