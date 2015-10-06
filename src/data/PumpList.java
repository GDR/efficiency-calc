package data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by gdr on 10/6/15.
 */
public class PumpList {
    private List<Pump> set = null;
    private Set<Pump> diff = null;

    double efficiency;
    Pump average;

    public double getEfficiency() {
        return efficiency;
    }

    public Pump getAverage() {
        return average;
    }

    public Pump getCalcError() {
        return calcError;
    }

    public Set<Pump> getDiff() {
        return diff;
    }

    public List<Pump> getSet() {
        return set;
    }

    public int size() {
        return set.size();
    }

    public Pump get(int index) {
        return set.get(index);
    }

    private Pump calcError = null;

    public PumpList() {
        set = new ArrayList<>();
        diff = new HashSet<>();
    }

    public PumpList(List<Pump> set) {
        this.set = set;
    }

    public void addPump(Pump pump) {
        set.add(pump);
    }

    public void removePump(Pump pump) {
        set.remove(pump);
    }

    public void calcAverage() {
        average = new Pump();
        for (Pump pump : set) {
            average.setPressureIncome(average.getPressureIncome() + pump.getPressureIncome());
            average.setPressureOutcome(average.getPressureOutcome() + pump.getPressureOutcome());
            average.setConsumption((average.getConsumption() + pump.getConsumption()));
            average.setDensity((average.getDensity() + pump.getDensity()));
            average.setAmperage((average.getAmperage() + pump.getAmperage()));
            average.setPower((average.getPower() + pump.getPower()));
        }
        average.setPressureIncome((average.getPressureIncome() / set.size()));
        average.setPressureOutcome((average.getPressureOutcome() / set.size()));
        average.setAmperage((average.getAmperage() / set.size()));
        average.setPower((average.getPower() / set.size()));
        average.setDensity((average.getDensity() / set.size()));
        average.setConsumption((average.getConsumption() / set.size()));

        diff = new HashSet<>();

        for (Pump pump: set) {
            Pump diffPump = new Pump();
            diffPump.setPressureIncome( (average.getPressureIncome() - pump.getPressureIncome()));
            diffPump.setPressureOutcome( (average.getPressureOutcome() - pump.getPressureOutcome()));
            diffPump.setConsumption( (average.getConsumption() - pump.getConsumption()));
            diffPump.setDensity( (average.getDensity() - pump.getDensity()));
            diffPump.setAmperage( (average.getAmperage() - pump.getAmperage()));
            diffPump.setPower( (average.getPower() - pump.getPower()));
            diff.add(diffPump);
        }
        calcError = new Pump();
        for (Pump pump: diff) {
            calcError.addPressureIncome(Math.pow(pump.getPressureIncome(), 2));
            calcError.addPressureOutcome(Math.pow(pump.getPressureOutcome(), 2));
            calcError.addConsumption(Math.pow(pump.getConsumption(), 2));
            calcError.addDensity(Math.pow(pump.getDensity(), 2));
            calcError.addPower(Math.pow(pump.getPower(), 2));
            calcError.addAmperage(Math.pow(pump.getAmperage(), 2));
            System.out.printf("%f %f\n", Math.pow(pump.getPressureIncome(), 2), calcError.getPressureIncome());
        }
        System.out.println("###" + calcError.getPressureIncome());
        System.out.printf("###%f\n", calcError.getPressureIncome());
        calcError.setPressureIncome( (Math.sqrt(calcError.getPressureIncome() / (diff.size() - 1))));
        calcError.setPressureOutcome( (Math.sqrt(calcError.getPressureOutcome() / (diff.size() - 1))));
        calcError.setAmperage( (Math.sqrt(calcError.getAmperage() / (diff.size() - 1))));
        calcError.setConsumption( (Math.sqrt(calcError.getConsumption() / (diff.size() - 1))));
        calcError.setPower( (Math.sqrt(calcError.getPower() / (diff.size() - 1))));
        calcError.setDensity( (Math.sqrt(calcError.getDensity() / (diff.size() - 1))));

        efficiency = ((average.getPressureOutcome() - average.getPressureIncome()) * (average.getConsumption() / 3600) * Math.pow(10, 8))
                / ((Constants.gravity ) * (102 * average.getPower() * 97.6));
        System.out.printf("eff%f\n", efficiency);
    }
}
