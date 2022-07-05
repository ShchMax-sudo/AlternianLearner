package com.shchmax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MediumPanel extends JPanel {
    private final char letter;

    public MediumPanel(Frame parentFrame) {
        this.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        this.setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT));
        this.setLayout(null);

        letter = (char)('a' + (char)(Math.abs((new Random()).nextInt()) % 26));

        JButton menuButton = new JButton();
        JLabel questionLabel = new JLabel();

        menuButton.addActionListener(e -> parentFrame.openMenu());

        Font buttonAlternianFont = new Font("Alternian", Font.PLAIN, 20);
        Font buttonEnglishFont = UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, 18);
        Font labelAlternianFont = new Font("Alternian", Font.PLAIN, Frame.HEIGHT / 6);
        if (!parentFrame.isAlternian) {
            menuButton.setFont(buttonEnglishFont);
        } else {
            menuButton.setFont(buttonAlternianFont);
        }
        menuButton.setText("Menu");
        questionLabel.setFont(labelAlternianFont);
        questionLabel.setText(letter + "");
        questionLabel.setHorizontalAlignment(JLabel.CENTER);
        questionLabel.setVerticalAlignment(JLabel.CENTER);

        int MENU_BUTTON_WIDTH = 200;
        int MENU_BUTTON_HEIGHT = 50;
        menuButton.setBounds(0, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        menuButton.setFocusPainted(false);
        this.add(menuButton);
        questionLabel.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        this.add(questionLabel);

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == letter) {
                    parentFrame.mediumMode();
                } else if (e.getKeyChar() >= 'a' && e.getKeyChar() <= 'z') {
                    JOptionPane.showMessageDialog(null, "Incorrect letter", "", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
}
