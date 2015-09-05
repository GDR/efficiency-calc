import javax.swing.*;
import java.math.BigDecimal;

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
    private JTextField resultField;
    private JButton resetButton;
    private JButton calcButton;
    private JComboBox<String> typeComboBox;

    public Main() {
        resetButton.addActionListener(e -> {
            pressureIncomeField.setText("");
            pressureOutcomeField.setText("");
            densityField.setText("");
            consumptionField.setText("");
            amperageField.setText("");
            powerField.setText("");
        });

        calcButton.addActionListener(e -> {
            double pressureIncome;
            double pressureOutcome;
            double density;
            double consumption;
            double amperage;
            double power;
            try {
                pressureIncome  = Double.parseDouble(pressureIncomeField.getText());
                pressureOutcome = Double.parseDouble(pressureOutcomeField.getText());
                density         = Double.parseDouble(densityField.getText());
                consumption     = Double.parseDouble(consumptionField.getText());
                amperage        = Double.parseDouble(amperageField.getText());
                power           = Double.parseDouble(powerField.getText());
            } catch (NumberFormatException exception) {
                resultField.setText("Ошибка ввода данных");
                return;
            }
            resultField.setText(
                    String.valueOf(
                            Math.round(
                                    new CalcKPI(
                                        pressureIncome,
                                        pressureOutcome,
                                        density,
                                        consumption,
                                        amperage,
                                        power
                                    ).calcSync() * 10000
                            ) / 10000
                    )
            );
        });
        typeComboBox.addItem("Синхронный");
        typeComboBox.addItem("Асинхронный");
        typeComboBox.addItem("Агрегированный");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
