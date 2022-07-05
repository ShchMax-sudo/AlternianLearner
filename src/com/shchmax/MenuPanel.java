package com.shchmax;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private final Frame parentFrame;
    private final JButton languageSwitchButton;
    private final JButton easyModeButton;
    private final JButton mediumModeButton;
    private final JButton hardModeButton;

    public MenuPanel(Frame parentFrame) {
        this.parentFrame = parentFrame;
        this.setBounds(0, 0, Frame.WIDTH, Frame.HEIGHT);
        this.setPreferredSize(new Dimension(Frame.WIDTH, Frame.HEIGHT));
        this.setLayout(null);

        languageSwitchButton = new JButton();
        easyModeButton = new JButton();
        mediumModeButton = new JButton();
        hardModeButton = new JButton();

        languageSwitchButton.addActionListener(e -> {
            parentFrame.changeLanguage();
            reloadButtonsText();
        });
        easyModeButton.addActionListener(e -> parentFrame.easyMode());
        mediumModeButton.addActionListener(e -> parentFrame.mediumMode());
        hardModeButton.addActionListener(e -> parentFrame.hardMode());

        reloadButtonsText();

        JButton[] buttons = new JButton[]{
                easyModeButton,
                mediumModeButton,
                hardModeButton,
                languageSwitchButton,
        };
        for (int i = 0; i < buttons.length; ++i) {
            JButton button = buttons[i];
            int BUTTON_WIDTH = 200;
            int BUTTON_HEIGHT = 50;
            button.setBounds((Frame.WIDTH - BUTTON_WIDTH) / 2, (Frame.HEIGHT - buttons.length * BUTTON_HEIGHT) / 2 + i * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
            button.setFocusPainted(false);
            this.add(button);
        }
    }

    private void reloadButtonsText() {
        Font buttonAlternianFont = new Font("Alternian", Font.PLAIN, 20);
        Font buttonEnglishFont = UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, 18);
        if (!parentFrame.isAlternian) {
            languageSwitchButton.setFont(buttonEnglishFont);
            easyModeButton.setFont(buttonEnglishFont);
            mediumModeButton.setFont(buttonEnglishFont);
            hardModeButton.setFont(buttonEnglishFont);
        } else {
            languageSwitchButton.setFont(buttonAlternianFont);
            easyModeButton.setFont(buttonAlternianFont);
            mediumModeButton.setFont(buttonAlternianFont);
            hardModeButton.setFont(buttonAlternianFont);
        }
        languageSwitchButton.setText("<html><center>Switch<br>Language</center></html>");
        easyModeButton.setText("Easy");
        mediumModeButton.setText("Medium");
        hardModeButton.setText("Hard");
    }
}
