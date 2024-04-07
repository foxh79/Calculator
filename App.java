    import javax.swing.*;
import java.awt.*;
import java.awt.event;

public class App extends JFrame implements ActionListener {
    private JTextField display = new JTextField(15);
    private JButton[] buttons = new JButton[20];
    private String[] labels = {
            "C", "%", "DEL", "/",
            "7", "8", "9", "X",
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "00", "0", ".", "=",
    };

    private double result = 0;
    private String operator = "=";
    private boolean startOfNumber = true;

    public App() {
        setLayout(new BorderLayout());
        display.setEditable(false);
        add(display, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(5, 4));

        // Create buttons and add ActionListener
        for (int i = 0; i < 20; i++) {
            buttons[i] = new JButton(labels[i]);
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
            case "00":
                if (startOfNumber) {
                    display.setText(command);
                    startOfNumber = false;
                } else {
                    display.setText(display.getText() + command);
                }
                break;
            case "+":
            case "-":
            case "X":
            case "/":
            case "%":
                if (!startOfNumber) {
                    calculate(Double.parseDouble(display.getText()));
                    operator = command;
                    startOfNumber = true;
                }
                break;
            case "=":
                if (!startOfNumber) {
                    calculate(Double.parseDouble(display.getText()));
                    operator = "=";
                    startOfNumber = true;
                }
                break;
            case "C":
                display.setText("");
                result = 0;
                operator = "=";
                startOfNumber = true;
                break;
            case "DEL":
                if (!startOfNumber) {
                    String text = display.getText();
                    if (!text.isEmpty()) {
                        display.setText(text.substring(0, text.length() - 1));
                    }
                }
                break;
        }
    }

    private void calculate(double x) {
        switch (operator) {
            case "+":
                result += x;
                break;
            case "-":
                result -= x;
                break;
            case "X":
                result *= x;
                break;
            case "/":
                if (x != 0)
                    result /= x;
                else
                    display.setText("Error");
                break;
            case "%":
                result %= x;
                break;
            case "=":
                result = x;
                break;
        }
        display.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        Jframe frame = new App();
        frame.setTitle("myCalculator");
        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
