package com.shchmax;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private final Container contentPane;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public boolean isAlternian = false;

    public static void main(String[] args) {
        new Frame();
    }

    public Frame() {
        this.setLocation(0, 0);
        this.setResizable(false);

        contentPane = this.getContentPane();
        registerFonts();
        openMenu();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void registerFonts() {
        GraphicsEnvironment gr = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            gr.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Alternian.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage() {
        this.isAlternian ^= true;
        if (isAlternian) {
            UIManager.put("OptionPane.messageFont", new Font("Alternian", Font.PLAIN, 14));
            UIManager.put("OptionPane.buttonFont", new Font("Alternian", Font.PLAIN, 12));
        } else {
            UIManager.put("OptionPane.messageFont", UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.BOLD, 14));
            UIManager.put("OptionPane.buttonFont", UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, 12));
        }
    }

    public void openMenu() {
        this.setTitle("A.B.C.");
        deletePanels();
        MenuPanel panel = new MenuPanel(this);
        contentPane.add(panel);
    }

    public void easyMode() {
        this.setTitle("Press right button");
        deletePanels();
        EasyPanel panel = new EasyPanel(this);
        contentPane.add(panel);
    }

    public void mediumMode() {
        this.setTitle("Press right keyboard key");
        deletePanels();
        MediumPanel panel = new MediumPanel(this);
        contentPane.add(panel);
        panel.setFocusable(true);
        panel.requestFocus();
    }

    public void hardMode() {
        JOptionPane.showMessageDialog(null, "In development", "", JOptionPane.ERROR_MESSAGE);
    }

    private void deletePanels() {
        contentPane.removeAll();
        SwingUtilities.updateComponentTreeUI(this);
    }
}
