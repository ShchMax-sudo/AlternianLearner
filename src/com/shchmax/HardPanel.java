package com.shchmax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class HardPanel extends JPanel {
    private String text;

    public HardPanel(Frame parentFrame) {
        this.setBounds(0, 0, (int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT));
        this.setPreferredSize(new Dimension((int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT)));
        this.setLayout(null);

        generateText();

        JButton menuButton = new JButton();
        JLabel questionLabel = new JLabel();
        JTextField answerField = new JTextField();

        menuButton.addActionListener(e -> parentFrame.openMenu());
        answerField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                lowerise();
                boolean correct = true;
                String typedText = answerField.getText();
                if (typedText.equals(text)) {
                    answerField.setBackground(Color.GREEN);
                    parentFrame.hardMode();
                } else if (typedText.length() > text.length()) {
                    correct = false;
                } else {
                    for (int i = 0; i < typedText.length(); ++i) {
                        if (typedText.charAt(i) != text.charAt(i)) {
                            correct = false;
                            break;
                        }
                    }
                }
                if (correct) {
                    answerField.setBackground(Color.WHITE);
                } else {
                    answerField.setBackground(Color.RED);
                }
            }

            private void lowerise() {
                answerField.setText(answerField.getText().toLowerCase());
            }
        });

        Font buttonAlternianFont = new Font("Alternian", Font.PLAIN, (int) (20 * Frame.SCREEN_COEFFICIENT));
        Font buttonEnglishFont = UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, (float) (20 * Frame.SCREEN_COEFFICIENT));
        Font labelAlternianFont = new Font("Alternian", Font.PLAIN, (int) (50 * Frame.SCREEN_COEFFICIENT));
        if (!parentFrame.isAlternian) {
            menuButton.setFont(buttonEnglishFont);
        } else {
            menuButton.setFont(buttonAlternianFont);
        }
        menuButton.setText("Menu");
        questionLabel.setFont(labelAlternianFont);
        questionLabel.setText("<html><center>" + text + "</center></html>");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setVerticalAlignment(JLabel.CENTER);

        final int MENU_BUTTON_WIDTH = (int) (200 * Frame.SCREEN_COEFFICIENT);
        final int MENU_BUTTON_HEIGHT = (int) (50 * Frame.SCREEN_COEFFICIENT);
        menuButton.setBounds(0, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        menuButton.setFocusPainted(false);
        this.add(menuButton);
        questionLabel.setBounds(0, 0, (int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT));
        this.add(questionLabel);
        final int TEXT_AREA_WIDTH = (int) (600 * Frame.SCREEN_COEFFICIENT);
        final int TEXT_AREA_HEIGHT = (int) (50 * Frame.SCREEN_COEFFICIENT);
        answerField.setBounds((int) ((Frame.WIDTH * Frame.SCREEN_COEFFICIENT - TEXT_AREA_WIDTH) / 2), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT - (Frame.WIDTH * Frame.SCREEN_COEFFICIENT - TEXT_AREA_WIDTH) / 2 - TEXT_AREA_HEIGHT), TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        answerField.setFont(buttonEnglishFont);
        this.add(answerField);
    }

    void generateText() {
        final int THRESHOLD = 30;
        StringBuilder sb = new StringBuilder();
        boolean connected = true;
        while (sb.length() < THRESHOLD) {
            try {
                URL url = new URL("https://random-word-api.herokuapp.com/word");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("accept", "application/json");
                InputStream responseStream;
                try {
                    responseStream = connection.getInputStream();
                } catch (UnknownHostException e) {
                    connected = false;
                    break;
                }
                Scanner in = new Scanner(responseStream);
                String word = in.next();
                word = word.substring(2, word.length() - 2);
                sb.append(word);
                sb.append(' ');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!connected) {
            text = "no internet";
            return;
        }
        text = sb.toString();
        text = text.substring(0, text.length() - 1);
    }
}
