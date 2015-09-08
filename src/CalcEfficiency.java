import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by gdr on 9/5/15.
 */
public class CalcEfficiency {
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

    private double a0 = 279.446886;
    private double a1 = -0.008687;
    private double a2 = 1.173418 * 1e-6;
    private double a3 = -2.225793 * 1e-9;

    private double c0 = 802.703297;
    private double c1 = 0.185075;
    private double c2 = 0.000183;
    private double c3 = -3.935709 * 1e-8;

    public CalcEfficiency(double pressureIncome,
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
                * ((pressureOutcome - pressureIncome) * 1e6) / (gravity * density)
                + 0.08257 * (consumption / 3600) * (consumption / 3600) * ((1 / Math.pow(0.35, 4) - 1 / Math.pow(0.51, 4)) )
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

    private double calcPressure() {
        System.out.println(consumption);
        double temp = a0 + a1 * consumption + a2 * consumption * consumption + a3 * consumption * consumption * consumption;
        System.out.println(temp);
        return temp;
    }

    private double calcPowerOfPump() {
        System.out.println(consumption);
        double temp = c0 + c1 * consumption + c2 * consumption * consumption + c3 * consumption * consumption * consumption;
        System.out.println(temp);
        return temp;
    }

    public double calcEfficiencyOfPump() {
        return (density * consumption * calcPressure()) / (102 * calcPowerOfPump() * 3600);
    }
}
