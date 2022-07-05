package com.shchmax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private final Frame parentFrame;
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;
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
            button.setBounds((Frame.WIDTH - BUTTON_WIDTH) / 2, (Frame.HEIGHT - buttons.length * BUTTON_HEIGHT) / 2 + i * BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT);
            button.setFocusPainted(false);
            this.add(button);
        }
    }

    private void reloadButtonsText() {
        Font buttonAlternianFont = new Font("Hiveswapalternian", Font.PLAIN, 20);
        Font buttonEnglishFont = new Font("Times New Roman", Font.PLAIN, 20);
        if (!parentFrame.isAlternian) {
            languageSwitchButton.setFont(buttonEnglishFont);
            languageSwitchButton.setText("<html><center>Switch<br>Language</center></html>");
            easyModeButton.setFont(buttonEnglishFont);
            easyModeButton.setText("Easy");
            mediumModeButton.setFont(buttonEnglishFont);
            mediumModeButton.setText("Medium");
            hardModeButton.setFont(buttonEnglishFont);
            hardModeButton.setText("Hard");
        } else {
            languageSwitchButton.setFont(buttonAlternianFont);
            languageSwitchButton.setText("<html><center>hctiws<br>egaugnal</center></html>");
            easyModeButton.setFont(buttonAlternianFont);
            easyModeButton.setText("ysae");
            mediumModeButton.setFont(buttonAlternianFont);
            mediumModeButton.setText("muidem");
            hardModeButton.setFont(buttonAlternianFont);
            hardModeButton.setText("drah");
        }
    }
}
