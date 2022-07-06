package com.shchmax;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Frame extends JFrame {
    private final Container contentPane;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int SCREEN_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int SCREEN_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    public static final double SCREEN_COEFFICIENT = Math.floor(Math.min(SCREEN_WIDTH / (double )(WIDTH), SCREEN_HEIGHT / (double) (HEIGHT)) * 2) / 2.0;
    public boolean isAlternian = false;

    public static void main(String[] args) {
        new Frame();
    }

    public Frame() {
        this.setLocation((int) (SCREEN_WIDTH - WIDTH * SCREEN_COEFFICIENT) / 2, (int) (SCREEN_HEIGHT - HEIGHT * SCREEN_COEFFICIENT) / 2);
        this.setResizable(false);
        this.setUndecorated(true);

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
            gr.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Hiveswap Alternian.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    public void changeLanguage() {
        this.isAlternian ^= true;
        if (isAlternian) {
            UIManager.put("OptionPane.messageFont", new Font("Alternian", Font.PLAIN, (int) (14 * SCREEN_COEFFICIENT)));
            UIManager.put("OptionPane.buttonFont", new Font("Alternian", Font.PLAIN, (int) (12 * SCREEN_COEFFICIENT)));
        } else {
            UIManager.put("OptionPane.messageFont", UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.BOLD, (int) (14 * SCREEN_COEFFICIENT)));
            UIManager.put("OptionPane.buttonFont", UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, (int) (12 * SCREEN_COEFFICIENT)));
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
        this.setTitle("Input text");
        deletePanels();
        HardPanel panel = new HardPanel(this);
        contentPane.add(panel);
    }

    public void infoFrame() {
        JFrame infoFrame = new JFrame();
        infoFrame.setLocation((int)(SCREEN_WIDTH - 400 * SCREEN_COEFFICIENT) / 2, (int) (SCREEN_HEIGHT - 300 * SCREEN_COEFFICIENT) / 2);
        infoFrame.setResizable(false);
        infoFrame.setAlwaysOnTop(true);
        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension((int) (400 * SCREEN_COEFFICIENT), (int) (300 * SCREEN_COEFFICIENT)));
        infoPanel.setLocation(0, 0);
        infoPanel.setLayout(null);
        JLabel infoLabel = new JLabel();
        infoLabel.setBounds((int) (25 * SCREEN_COEFFICIENT), (int) (25 * SCREEN_COEFFICIENT), (int) (350 * SCREEN_COEFFICIENT), (int) (250 * SCREEN_COEFFICIENT));
        infoLabel.setHorizontalAlignment(JLabel.LEFT);
        infoLabel.setVerticalAlignment(JLabel.TOP);
        if (isAlternian) {
            infoLabel.setFont(new Font("Alternian", Font.PLAIN, (int) (25 * SCREEN_COEFFICIENT)));
        } else {
            infoLabel.setFont(UIManager.getLookAndFeelDefaults().getFont("TextField.font").deriveFont(Font.PLAIN, (float) (18 * SCREEN_COEFFICIENT)));
        }
        String infoText = "This application helps you to learn alternian language.<br>In easy mode, you need to choose correct symbol from 6 symbols in bottom side.<br>In medium mode, you need to press the correct button on the keyboard.<br>In hard mode, you need to write text in the field.";
        infoLabel.setText("<html>" + infoText + "</html>");
        infoPanel.add(infoLabel);
        infoFrame.getContentPane().add(infoPanel);
        infoFrame.pack();
        infoFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                infoFrame.dispose();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        infoFrame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                infoFrame.dispose();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        infoFrame.setVisible(true);
    }

    private void deletePanels() {
        contentPane.removeAll();
        SwingUtilities.updateComponentTreeUI(this);
    }
}
