import java.awt.*;
import javax.swing.*;

// Bank Account Class
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// ATM GUI Class
public class ATMInterface {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(1000); // Initial Balance

        JFrame frame = new JFrame("ATM Machine");
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("ATM INTERFACE", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(Color.BLUE);
        frame.add(title, BorderLayout.NORTH);

        // Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton checkBtn = new JButton("Check Balance");
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton exitBtn = new JButton("Exit");

        panel.add(checkBtn);
        panel.add(depositBtn);
        panel.add(withdrawBtn);
        panel.add(exitBtn);

        frame.add(panel, BorderLayout.CENTER);

        // Result Area
        JTextArea output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Arial", Font.BOLD, 14));
        output.setBackground(new Color(230, 240, 255));
        frame.add(output, BorderLayout.SOUTH);

        // Button Actions

        checkBtn.addActionListener(e -> {
            output.setText("Current Balance: ₹" + account.getBalance());
        });

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter amount to deposit:");
            try {
                double amount = Double.parseDouble(input);
                if (account.deposit(amount)) {
                    output.setText("Deposit Successful!\nNew Balance: ₹" + account.getBalance());
                } else {
                    output.setText("Invalid Amount!");
                }
            } catch (Exception ex) {
                output.setText("Please enter valid number!");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter amount to withdraw:");
            try {
                double amount = Double.parseDouble(input);
                if (account.withdraw(amount)) {
                    output.setText("Withdrawal Successful!\nRemaining Balance: ₹" + account.getBalance());
                } else {
                    output.setText("Insufficient Balance or Invalid Amount!");
                }
            } catch (Exception ex) {
                output.setText("Please enter valid number!");
            }
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you for using ATM!");
            System.exit(0);
        });

        frame.setVisible(true);
    }
}
