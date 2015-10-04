import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created by gdr on 9/5/15.
 */
public class Pump {
    // Variables
    private double pressureIncome;
    private double pressureOutcome;
    private double consumption;
    private double density;
    private double amperage;
    private double power;
    // Constants
    private static double gravity = 9.8066;
    private static double z = 0.6;
    private static double d = 0;

    private static double a0 = 310.15539;
    private static double a1 = -0.008866;
    private static double a2 = -4.477719 * 1e-8;
    private static double a3 = -1.26612 * 1e-10;

    private static double c0 = 3172.6442;
    private static double c1 = 0.231524;
    private static double c2 = 5.325688 * 1e-6;
    private static double c3 = -1.816669 * 1e-9;

    public Pump() {
        this.pressureIncome = 0;
        this.pressureOutcome = 0;
        this.consumption = 0;
        this.density = 0;
        this.amperage = 0;
        this.power = 0;
    }

    public Pump(double pressureIncome,
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

    public double getPressureIncome() {
        return pressureIncome;
    }

    public double getPressureOutcome() {
        return pressureOutcome;
    }

    public double getConsumption() {
        return consumption;
    }

    public double getDensity() {
        return density;
    }

    public double getAmperage() {
        return amperage;
    }

    public double getPower() {
        return power;
    }

    public void setPressureIncome(double pressureIncome) {
        this.pressureIncome = pressureIncome;
    }

    public void setPressureOutcome(double pressureOutcome) {
        this.pressureOutcome = pressureOutcome;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public void setAmperage(double amperage) {
        this.amperage = amperage;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void addPressureIncome(double pressureIncome) {
        this.pressureIncome += pressureIncome;
    }

    public void addPressureOutcome(double pressureOutcome) {
        this.pressureOutcome += pressureOutcome;
    }

    public void addConsumption(double consumption) {
        this.consumption += consumption;
    }

    public void addAmperage(double amperage) {
        this.amperage += amperage;
    }

    public void addPower(double power) {
        this.power += power;
    }

    public void addDensity(double density) {
        this.density += density;
    }

    @Override
    public String toString() {
        return "Pump{" +
                "pressureIncome=" + pressureIncome +
                ", pressureOutcome=" + pressureOutcome +
                ", consumption=" + consumption +
                ", density=" + density +
                ", amperage=" + amperage +
                ", power=" + power +
                '}';
    }
}
