import javax.swing.*;

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
        });

        calcButton.addActionListener(e -> {
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
                return;
            }
            CalcEfficiency calcKPI = new CalcEfficiency(
                    pressureIncome,
                    pressureOutcome,
                    consumption,
                    density,
                    amperage,
                    power
            );
            syncResultField.setText(
                    String.valueOf(calcKPI.calcSync() * 100)
            );
            aggregateReusltField.setText(
                    String.valueOf(calcKPI.calcAggregate() * 100)
            );
            pumpResultFIeld.setText(
                    String.valueOf(calcKPI.calcEfficiencyOfPump() * 100)
            );
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
