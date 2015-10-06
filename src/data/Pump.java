package data;

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
                * ((pressureOutcome - pressureIncome) * 1e6) / (Constants.gravity * density)
                + 0.08257 * (consumption / 3600) * (consumption / 3600) * ((1 / Math.pow(0.6, 4) - 1 / Math.pow(0.6, 4)) )
                ;
    }

    private double calcSyncDivider() {
        return 102 * (power - (88.3 + 3 * Math.pow(amperage, 2) * (0.0984 * (1 + 0.0041 * (75 - 17))) * 0.001
                + Math.pow(amperage, 2) * 0 * 0.001));
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
        double temp = Constants.a0 + Constants.a1 * consumption + Constants.a2 * consumption * consumption + Constants.a3 * consumption * consumption * consumption;
        System.out.println(temp);
        return temp;
    }

    private double calcPowerOfPump() {
        System.out.println(consumption);
        double temp = Constants.c0 + Constants.c1 * consumption + Constants.c2 * consumption * consumption + Constants.c3 * consumption * consumption * consumption;
        System.out.println(temp);
        return temp;
    }

    public double calcEfficiencyOfPump() {
        return (998.2 * calcPressure() * consumption * 1e4) / (calcPowerOfPump() * 3600 * 102 * 97.6);
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
        return "data.Pump{" +
                "pressureIncome=" + pressureIncome +
                ", pressureOutcome=" + pressureOutcome +
                ", consumption=" + consumption +
                ", density=" + density +
                ", amperage=" + amperage +
                ", power=" + power +
                '}';
    }
}
