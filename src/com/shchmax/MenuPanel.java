package com.shchmax;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private final Frame parentFrame;
    private final JButton languageSwitchButton;
    private final JButton easyModeButton;
    private final JButton mediumModeButton;
    private final JButton hardModeButton;
    private final JButton infoButton;
    private final JButton exitButton;
    private final JButton[] buttons;

    public MenuPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;
        this.setBounds(0, 0, (int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT));
        this.setPreferredSize(new Dimension((int) (Frame.WIDTH * Frame.SCREEN_COEFFICIENT), (int) (Frame.HEIGHT * Frame.SCREEN_COEFFICIENT)));
        this.setLayout(null);

        languageSwitchButton = new JButton();
        easyModeButton = new JButton();
        mediumModeButton = new JButton();
        hardModeButton = new JButton();
        infoButton = new JButton();
        exitButton = new JButton();

        languageSwitchButton.addActionListener(e -> {
            parentFrame.changeLanguage();
            reloadButtonsText();
        });
        easyModeButton.addActionListener(e -> parentFrame.easyMode());
        mediumModeButton.addActionListener(e -> parentFrame.mediumMode());
        hardModeButton.addActionListener(e -> parentFrame.hardMode());
        infoButton.addActionListener(e -> parentFrame.infoFrame());
        exitButton.addActionListener(e -> System.exit(0));

         buttons = new JButton[]{
                easyModeButton,
                mediumModeButton,
                hardModeButton,
                infoButton,
                languageSwitchButton,
                exitButton,
        };

        reloadButtonsText();

        for (int i = 0; i < buttons.length; ++i) {
            JButton button = buttons[i];
            final int BUTTON_WIDTH = (int) (200 * Frame.SCREEN_COEFFICIENT);
            final int BUTTON_HEIGHT = (int) (50 * Frame.SCREEN_COEFFICIENT);
            button.setBounds((int) ((Frame.WIDTH * Frame.SCREEN_COEFFICIENT - BUTTON_WIDTH) / 2), (int) ((Frame.HEIGHT * Frame.SCREEN_COEFFICIENT - buttons.length * BUTTON_HEIGHT) / 2 + i * BUTTON_HEIGHT), BUTTON_WIDTH, BUTTON_HEIGHT);
            button.setFocusPainted(false);
            this.add(button);
        }
    }

    private void reloadButtonsText() {
        Font buttonAlternianFont = new Font("Alternian", Font.PLAIN, (int) (20 * Frame.SCREEN_COEFFICIENT));
        Font buttonEnglishFont = UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, (float) (18 * Frame.SCREEN_COEFFICIENT));
        if (parentFrame.isAlternian) {
            for (JButton button : buttons) {
                button.setFont(buttonAlternianFont);
            }
        } else {
            for (JButton button : buttons) {
                button.setFont(buttonEnglishFont);
            }
        }
        languageSwitchButton.setText("<html><center>Switch<br>Language</center></html>");
        easyModeButton.setText("Easy");
        mediumModeButton.setText("Medium");
        hardModeButton.setText("Hard");
        infoButton.setText("Info");
        exitButton.setText("Exit");
    }
}
