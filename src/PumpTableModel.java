import data.Pump;
import data.PumpList;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PumpTableModel implements TableModel {
    PumpList pumpSet;
    List<Pump> deviation;
    String[] columnNames = {"№ п/п", "Pвх, МПа", "Pвых, МПа", "Q, м3/ч", "ρ, кг/м3", "I, А", "N, кВт"};
    Set<TableModelListener> listenerSet = new HashSet<>();
    Pump average = new Pump();
    Pump calcError = new Pump();

    @Override
    public int getRowCount() {
        return pumpSet.size();
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
        Pump pump = pumpSet.get(rowIndex);
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
        pumpSet = new PumpList();
    }



    public void addPump(Pump pump) {
        pumpSet.addPump(pump);
        pumpSet.calcAverage();
        System.out.println(pumpSet.getAverage());
        System.out.println("Diffs:\n" + pumpSet.getDiff());
        System.out.println(pumpSet.getCalcError());
    }

    public double getAverageEfficiency() {
        return pumpSet.getEfficiency();
    }

    private Pump getAverage() {
        return pumpSet.getAverage();
    }

    public double getSync() {
        return getAverage().calcSync();
    }

    public double getAggregate() {
        return getAverage().calcAggregate();
    }

    public double getEfficiencyOfPump() {
        return getAverage().calcEfficiencyOfPump();
    }
}