/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mathgame;

/**
 *
 * @author Raminullah Afghanyar
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MathGame extends JFrame implements ActionListener {
    private JLabel questionLabel;
    private JTextField answerEntry;
    private JButton submitButton;
    private JLabel resultLabel;
    
    private int num1, num2;
    private String operator;
    private double answer;
    
    
    
    

    public MathGame() {
        setTitle("Math Game");
        setSize(300, 200);
        setLayout(new FlowLayout());

        generateQuestion();

        questionLabel = new JLabel(getFormattedQuestion());
        add(questionLabel);

        answerEntry = new JTextField(10);
        add(answerEntry);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);

        resultLabel = new JLabel("");
        add(resultLabel);
    }

    
    
    
    
    private String getFormattedQuestion() {
        return "What is " + num1 + " " + operator + " " + num2 + "?";
    }

    private void generateQuestion() {
        Random random = new Random();
        num1 = random.nextInt(10) + 1;
        num2 = random.nextInt(10) + 1;
        String[] operators = {"+", "-", "*", "/"};
        operator = operators[random.nextInt(operators.length)];
        calculateAnswer();
    }

   
    private void calculateAnswer() {
        switch (operator) {
            case "+":
                answer = num1 + num2;
                break;
            case "-":
                answer = num1 - num2;
                break;
            case "*":
                answer = num1 * num2;
                break;
            case "/":
                answer = (double) num1 / num2;
                break;
        }
    }

    @Override
public void actionPerformed(ActionEvent e) {
    String userAnswer = answerEntry.getText();
    double userDoubleAnswer;
    try {
        userDoubleAnswer = Double.parseDouble(userAnswer);
    } catch (NumberFormatException ex) {
        resultLabel.setText("Invalid input. Please enter a valid number.");
        return;
    }

    double tolerance = 0.0001; // Adjust tolerance as needed for your game

    if (Math.abs(userDoubleAnswer - answer) < tolerance) {
        resultLabel.setText("Correct!");
        generateQuestion();
        questionLabel.setText(getFormattedQuestion());
        answerEntry.setText("");
    } else {
        resultLabel.setText("Incorrect. Try again.");
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MathGame mathGame = new MathGame();
            mathGame.setVisible(true);
        });
    }}
