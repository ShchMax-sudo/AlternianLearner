package com.shchmax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.SecureRandom;

public class MediumPanel extends JPanel {
    private final char letter;

    public MediumPanel(Frame parentFrame) {
        this.setBounds(0, 0, (int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT));
        this.setPreferredSize(new Dimension((int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT)));
        this.setLayout(null);

        letter = (char)('a' + (char)(new SecureRandom().nextInt(26)));

        JButton menuButton = new JButton();
        JLabel questionLabel = new JLabel();

        menuButton.addActionListener(e -> parentFrame.openMenu());

        Font buttonAlternianFont = new Font("Alternian", Font.PLAIN, (int) (20 * Frame.SCREEN_COEFFICIENT));
        Font buttonEnglishFont = UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, (float) (18 * Frame.SCREEN_COEFFICIENT));
        Font labelAlternianFont = new Font("Alternian", Font.PLAIN, (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT / 6));
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

        final int MENU_BUTTON_WIDTH = (int) (200 * Frame.SCREEN_COEFFICIENT);
        final int MENU_BUTTON_HEIGHT = (int) (50 * Frame.SCREEN_COEFFICIENT);
        menuButton.setBounds(0, 0, MENU_BUTTON_WIDTH, MENU_BUTTON_HEIGHT);
        menuButton.setFocusPainted(false);
        this.add(menuButton);
        questionLabel.setBounds(0, 0, (int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT));
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
