package com.shchmax;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class EasyPanel extends JPanel {
    private final char[] letters = new char[6];
    private int correct;

    public EasyPanel(Frame parentFrame) {
        this.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        this.setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT));
        this.setLayout(null);

        generateLetters();

        JButton menuButton = new JButton();
        JButton[] answerButtons = new JButton[letters.length];
        for (int i = 0; i < answerButtons.length; ++i) {
            answerButtons[i] = new JButton();
        }
        JLabel questionLabel = new JLabel();

        menuButton.addActionListener(e -> parentFrame.openMenu());
        for (int i = 0; i < answerButtons.length; ++i) {
            JButton button = answerButtons[i];
            if (i == correct) {
                button.addActionListener(e -> parentFrame.easyMode());
            } else {
                button.addActionListener(e -> button.setBackground(Color.RED));
            }
        }

        Font buttonAlternianFont = new Font("Alternian", Font.PLAIN, 20);
        Font buttonEnglishFont = UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, 18);
        Font labelAlternianFont = new Font("Alternian", Font.PLAIN, Frame.HEIGHT / 6);
        if (!parentFrame.isAlternian) {
            menuButton.setFont(buttonEnglishFont);
        } else {
            menuButton.setFont(buttonAlternianFont);
        }
        menuButton.setText("Menu");
        for (int i = 0; i < answerButtons.length; ++i) {
            JButton button = answerButtons[i];
            button.setFont(buttonEnglishFont);
            button.setText(letters[i] + "");
        }
        questionLabel.setFont(labelAlternianFont);
        questionLabel.setText(letters[correct] + "");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setVerticalAlignment(JLabel.CENTER);

        int MENU_BUTTON_WIDTH = 200;
        int MENU_BUTTON_HEIGHT = 50;
        menuButton.setBounds(0, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        menuButton.setFocusPainted(false);
        this.add(menuButton);
        for (int i = 0; i < answerButtons.length; ++i) {
            JButton button = answerButtons[i];
            int x = i / (answerButtons.length / 2);
            int y = i % (answerButtons.length / 2);
            int width = 200;
            button.setBounds((Frame.WIDTH - (answerButtons.length / 2) * width) / 2 + y * width, Frame.HEIGHT - x * MENU_BUTTON_HEIGHT - (Frame.WIDTH - (answerButtons.length / 2) * width) / 2, width, MENU_BUTTON_HEIGHT);
            button.setFocusPainted(false);
            this.add(button);
        }
        questionLabel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        this.add(questionLabel);
    }

    private void generateLetters() {
        Random rand = new Random();
        for (int i = 0; i < letters.length;) {
            char ch = 'a';
            ch += (char)(Math.abs(rand.nextInt()) % 26);
            boolean f = true;
            for (int j = 0; j < i; ++j) {
                if (letters[j] == ch) {
                    f = false;
                    break;
                }
            }
            if (f) {
                letters[i] = ch;
                i++;
            }
        }
        correct = Math.abs(rand.nextInt()) % letters.length;
    }
}
