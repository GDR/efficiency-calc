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
    private double gravity = 9.8066;
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
        return     density
                * (consumption / 3600)
                * ((pressureOutcome - pressureIncome) / (consumption * gravity)
                * 1000000
                )
                ;
    }

    private double calcSyncDivider() {
        return 102 * (power - (88.3) + 3 * Math.pow(amperage, 2) * 0.335 * 0.001 + Math.pow(amperage, 2) * 0 * 0.001);
    }

    private double calcASyncDivider() {
        return 1;
    }

    private double calcAggregateDivider() {
        return 102 * power;
    }

    public double calcSync() {
        System.out.println(calcDivisor());
        System.out.println(calcSyncDivider());
        return calcDivisor() / calcSyncDivider();
    }

    public double calcASync() {
        return calcDivisor() / calcASyncDivider();
    }

    public double calcAggregate() {
        return calcDivisor() / calcAggregateDivider();
    }
}
