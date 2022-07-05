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

    public Frame() {
        this.setLocation(0, 0);
        this.setTitle("A.B.C.");
        this.setResizable(false);

        contentPane = this.getContentPane();
//        registerFonts();
        openMenu();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void registerFonts() {
        GraphicsEnvironment gr = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            gr.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Hiveswapalternian.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage() {
        this.isAlternian ^= true;
    }

    public void openMenu() {
        deletePanels();
        MenuPanel panel = new MenuPanel(this);
        contentPane.add(panel);
    }

    public void easyMode() {
        deletePanels();
        EasyPanel panel = new EasyPanel(this);
        contentPane.add(panel);
    }

    public void mediumMode() {
        hardMode();
    }

    public void hardMode() {
        JOptionPane.showMessageDialog(null, "In Development.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void deletePanels() {
        contentPane.removeAll();
        SwingUtilities.updateComponentTreeUI(this);
    }
}
