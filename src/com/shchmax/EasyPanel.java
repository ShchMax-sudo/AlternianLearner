package com.shchmax;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;

public class EasyPanel extends JPanel {
    private final char[] letters = new char[6];
    private int correct;

    public EasyPanel(Frame parentFrame) {
        this.setBounds(0, 0, (int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT));
        this.setPreferredSize(new Dimension((int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT)));
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

        Font buttonAlternianFont = new Font("Alternian", Font.PLAIN, (int) (20 * Frame.SCREEN_COEFFICIENT));
        Font buttonEnglishFont = UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, (float) (18 * Frame.SCREEN_COEFFICIENT));
        Font labelAlternianFont = new Font("Alternian", Font.PLAIN, (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT / 6));
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

        final int MENU_BUTTON_WIDTH = (int) (200 * Frame.SCREEN_COEFFICIENT);
        final int MENU_BUTTON_HEIGHT = (int) (50 * Frame.SCREEN_COEFFICIENT);
        menuButton.setBounds(0, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        menuButton.setFocusPainted(false);
        this.add(menuButton);
        for (int i = 0; i < answerButtons.length; ++i) {
            JButton button = answerButtons[i];
            int x = i / (answerButtons.length / 2);
            int y = i % (answerButtons.length / 2);
            final int BUTTON_WIDTH = (int) (200 * Frame.SCREEN_COEFFICIENT);
            int cornerMargin = (int) ((Frame.WIDTH * Frame.SCREEN_COEFFICIENT - (answerButtons.length / 2) * BUTTON_WIDTH) / 2);
            button.setBounds(cornerMargin + y * BUTTON_WIDTH, (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT - x * MENU_BUTTON_HEIGHT - cornerMargin), BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
            button.setFocusPainted(false);
            this.add(button);
        }
        questionLabel.setBounds(0, 0, (int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT));
        this.add(questionLabel);
    }

    private void generateLetters() {
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < letters.length;) {
            char ch = 'a';
            ch += (char)(rand.nextInt(26));
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
        correct = rand.nextInt(letters.length);
    }
}
