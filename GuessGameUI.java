import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GuessGameUI extends JFrame implements ActionListener {

    Random random = new Random();
    int randomNumber = random.nextInt(100) + 1;
    int attempts = 0;
    int maxAttempts = 5;
    int score = 0;

    JTextField guessField;
    JLabel messageLabel;
    JLabel attemptsLabel;
    JLabel scoreLabel;
    JButton guessButton;
    JButton resetButton;

    GuessGameUI() {

        setTitle("Number Guessing Game");
        setSize(400,300);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("Guess Number (1-100)");
        title.setFont(new Font("Arial",Font.BOLD,18));

        guessField = new JTextField(10);

        guessButton = new JButton("Guess");
        resetButton = new JButton("Restart Game");

        messageLabel = new JLabel("Enter your guess");
        attemptsLabel = new JLabel("Attempts: 0 / 5");
        scoreLabel = new JLabel("Score: 0");

        guessButton.addActionListener(this);
        resetButton.addActionListener(this);

        add(title);
        add(guessField);
        add(guessButton);
        add(resetButton);
        add(messageLabel);
        add(attemptsLabel);
        add(scoreLabel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==guessButton){

            int guess = Integer.parseInt(guessField.getText());
            attempts++;

            if(guess == randomNumber){

                messageLabel.setText("Correct! 🎉");
                score++;
                scoreLabel.setText("Score: "+score);
                guessButton.setEnabled(false);

            }
            else if(guess > randomNumber){

                messageLabel.setText("Too High!");

            }
            else{

                messageLabel.setText("Too Low!");
            }

            attemptsLabel.setText("Attempts: "+attempts+" / "+maxAttempts);

            if(attempts >= maxAttempts && guess != randomNumber){

                messageLabel.setText("Game Over! Number was "+randomNumber);
                guessButton.setEnabled(false);
            }
        }

        if(e.getSource()==resetButton){

            randomNumber = random.nextInt(100)+1;
            attempts = 0;

            messageLabel.setText("Enter your guess");
            attemptsLabel.setText("Attempts: 0 / 5");
            guessField.setText("");
            guessButton.setEnabled(true);
        }
    }

    public static void main(String[] args) {

        new GuessGameUI();
    }
}