import sun.net.idn.Punycode;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.util.IllegalFormatException;

/**
 * Created by gdr on 9/5/15.
 */
public class Main extends JPanel {
    private JPanel MainPanel;
    private JTextField pressureIncomeField;
    private JTextField pressureOutcomeField;
    private JTextField consumptionField;
    private JTextField densityField;
    private JTextField amperageField;
    private JTextField powerField;
    private JTextField syncResultField;
    private JButton resetButton;
    private JButton calcButton;
    private JComboBox<String> typeComboBox;
    private JTextField aggregateReusltField;
    private JPanel Bottom;
    private JPanel Results;
    private JPanel Buttons;
    private JPanel Top;
    private JPanel Inputs;
    private JPanel Labels;
    private JPanel Results2;
    private JTextField pumpResultFIeld;
    private JPanel Table;
    private JTable table1;
    private JButton addButton;

    public Main() {
        resetButton.addActionListener(e -> {
            pressureIncomeField.setText("");
            pressureOutcomeField.setText("");
            densityField.setText("");
            consumptionField.setText("");
            amperageField.setText("");
            powerField.setText("");
            syncResultField.setText("");
            aggregateReusltField.setText("");
            pumpResultFIeld.setText("");
        });

        calcButton.addActionListener(e -> {

            Pump calcKPI = null;
            try {
                calcKPI = generatePump();
            } catch (Exception ignored) {
                return;
            }
            syncResultField.setText(
                    String.valueOf(Math.floor(calcKPI.calcSync() * 10000) / 100)
            );
            aggregateReusltField.setText(
                    String.valueOf(Math.floor(calcKPI.calcAggregate() * 10000) / 100)
            );
            pumpResultFIeld.setText(
                    String.valueOf(Math.floor(calcKPI.calcEfficiencyOfPump() * 10000) / 100)
            );
        });
        PumpTableModel tableModel = new PumpTableModel();
        addButton.addActionListener(e -> {
            Pump pump = null;
            try {
                pump = generatePump();
            } catch (Exception e1) {
                return;
            }
            tableModel.addPump(pump);
            table1.updateUI();
        });
        tableModel.addPump(new Pump(0.506, 2.27, 6912, 841.16, 1, 4148.62));
        tableModel.addPump(new Pump(0.508, 2.27, 6912, 841.14, 1, 4139.51));
        tableModel.addPump(new Pump(0.508, 2.273, 6912, 841.19, 1, 4145.12));
        tableModel.addPump(new Pump(0.506, 2.273, 6912, 841.14, 1, 4158.42));
        tableModel.addPump(new Pump(0.506, 2.27, 6912, 841.1, 1, 4160.52));
        tableModel.addPump(new Pump(0.508, 2.273, 6912, 841.08, 1, 4138.11));
        tableModel.addPump(new Pump(0.509, 2.273, 6912, 841.1, 1, 4133.21));
        tableModel.addPump(new Pump(0.505, 2.268, 6912, 841.1, 1, 4150.02));
        tableModel.addPump(new Pump(0.505, 2.27, 6912, 841.16, 1, 4168.92));
        tableModel.addPump(new Pump(0.505, 2.273, 6912, 841.14, 1, 4165.42));
        table1.setModel(tableModel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Pump generatePump() throws Exception {
        double pressureIncome;
        double pressureOutcome;
        double density;
        double consumption;
        double amperage;
        double power;
        try {
            pressureIncome = Double.parseDouble(pressureIncomeField.getText());
            pressureOutcome = Double.parseDouble(pressureOutcomeField.getText());
            density = Double.parseDouble(densityField.getText());
            consumption = Double.parseDouble(consumptionField.getText());
            amperage = Double.parseDouble(amperageField.getText());
            power = Double.parseDouble(powerField.getText());
        } catch (NumberFormatException exception) {
            syncResultField.setText("Ошибка ввода данных");
            throw new Exception();
        }
        Pump pump = new Pump(
                pressureIncome,
                pressureOutcome,
                consumption,
                density,
                amperage,
                power
        );
        return pump;
    }

}
