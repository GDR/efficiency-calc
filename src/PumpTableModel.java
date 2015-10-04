import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PumpTableModel implements TableModel {
    List<Pump> list;
    List<Pump> diff;
    List<Pump> deviation;
    String[] columnNames = {"№ п/п", "Pвх, МПа", "Pвых, МПа", "Q, м3/ч", "ρ, кг/м3", "I, А", "N, кВт"};
    Set<TableModelListener> listenerSet = new HashSet<>();
    Pump average = new Pump();
    Pump calcError = new Pump();

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pump pump = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return rowIndex + 1;
            case 1: return pump.getPressureIncome();
            case 2: return pump.getPressureOutcome();
            case 3: return pump.getConsumption();
            case 4: return pump.getDensity();
            case 5: return pump.getAmperage();
            case 6: return pump.getPower();
            default: return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listenerSet.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listenerSet.remove(l);
    }

    public PumpTableModel() {
        list = new ArrayList<>();
    }

    private void calcAverage() {
        average = new Pump();
        for (Pump pump : list) {
            average.setPressureIncome(pleaseRoundIt(average.getPressureIncome() + pump.getPressureIncome()));
            average.setPressureOutcome(pleaseRoundIt(average.getPressureOutcome() + pump.getPressureOutcome()));
            average.setConsumption(pleaseRoundIt(average.getConsumption() + pump.getConsumption()));
            average.setDensity(pleaseRoundIt(average.getDensity() + pump.getDensity()));
            average.setAmperage(pleaseRoundIt(average.getAmperage() + pump.getAmperage()));
            average.setPower(pleaseRoundIt(average.getPower() + pump.getPower()));
        }
        average.setPressureIncome(pleaseRoundIt(average.getPressureIncome() / list.size()));
        average.setPressureOutcome(pleaseRoundIt(average.getPressureOutcome() / list.size()));
        average.setAmperage(pleaseRoundIt(average.getAmperage() / list.size()));
        average.setPower(pleaseRoundIt(average.getPower() / list.size()));
        average.setDensity(pleaseRoundIt(average.getDensity() / list.size()));
        average.setConsumption(pleaseRoundIt(average.getConsumption() / list.size()));

        diff = new ArrayList<>();

        for (Pump pump: list) {
            Pump diffPump = new Pump();
            diffPump.setPressureIncome(pleaseRoundIt(average.getPressureIncome() - pump.getPressureIncome()));
            diffPump.setPressureOutcome(pleaseRoundIt(average.getPressureOutcome() - pump.getPressureOutcome()));
            diffPump.setConsumption(pleaseRoundIt(average.getConsumption() - pump.getConsumption()));
            diffPump.setDensity(pleaseRoundIt(average.getDensity() - pump.getDensity()));
            diffPump.setAmperage(pleaseRoundIt(average.getAmperage() - pump.getAmperage()));
            diffPump.setPower(pleaseRoundIt(average.getPower() - pump.getPower()));
            diff.add(diffPump);
        }
        calcError = new Pump();
        for (Pump pump: diff) {
            calcError.addPressureIncome(pow(pump.getPressureIncome()));
            calcError.addPressureOutcome(pow(pump.getPressureOutcome()));
            calcError.addConsumption(pow(pump.getConsumption()));
            calcError.addDensity(pow(pump.getDensity()));
            calcError.addPower(pow(pump.getPower()));
            calcError.addAmperage(pow(pump.getAmperage()));
            System.out.printf("%f %f\n", pow(pump.getPressureIncome()), calcError.getPressureIncome());
        }
        System.out.println("###" + calcError.getPressureIncome());
        System.out.printf("###%f\n", calcError.getPressureIncome());
        calcError.setPressureIncome(pleaseRoundIt(Math.sqrt(calcError.getPressureIncome() / (diff.size() - 1))));
        calcError.setPressureOutcome(pleaseRoundIt(Math.sqrt(calcError.getPressureOutcome() / (diff.size() - 1))));
        calcError.setAmperage(pleaseRoundIt(Math.sqrt(calcError.getAmperage() / (diff.size() - 1))));
        calcError.setConsumption(pleaseRoundIt(Math.sqrt(calcError.getConsumption() / (diff.size() - 1))));
        calcError.setPower(pleaseRoundIt(Math.sqrt(calcError.getPower() / (diff.size() - 1))));
        calcError.setDensity(pleaseRoundIt(Math.sqrt(calcError.getDensity() / (diff.size() - 1))));

    }

    public void addPump(Pump pump) {
        list.add(pump);
        calcAverage();
        System.out.println(average);
        System.out.println("Diffs:\n" + diff);
        System.out.println(calcError);
    }

    private double pleaseRoundIt(double a) {
        return Math.floor(a * 10000000) / 10000000;
    }

    private double pow(double a) {
        return a * a;
    }
}