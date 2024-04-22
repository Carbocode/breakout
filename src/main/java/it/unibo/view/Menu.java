package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Menu extends JFrame {
    private JPanel mainPanel;
    private JPanel gamePanel;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton scoreboardButton;
    private JButton exitButton;
    private Measures measure = new Measures();

    public Menu() {
        setTitle("Breakout");
        setSize(measure.getGameAreaWidth(), measure.getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // creation of the panel of the menu
        mainPanel = new JPanel(new GridLayout(3, 1));
        titleLabel = new JLabel("BREAKOUT", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // creating the background image, it has to be done (TO DO)
        ImageIcon backgroundImage = new ImageIcon("../api/appdata/images/space.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new BorderLayout());

        // these are the buttons of the menu with their panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setOpaque(false);

        playButton = new JButton("PLAY");
        playButton.setPreferredSize(new Dimension(100, 50));

        scoreboardButton = new JButton("SCOREBOARD");
        scoreboardButton.setPreferredSize(new Dimension(100, 50));

        exitButton = new JButton("EXIT");
        exitButton.setPreferredSize(new Dimension(100, 50));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TO DO
                dispose();
                JFrame gameFrame = new Game();
                gameFrame.setVisible(true);
            }
        });

        scoreboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TO DO
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(playButton);
        buttonPanel.add(scoreboardButton);
        buttonPanel.add(exitButton);

        backgroundLabel.add(titleLabel, BorderLayout.NORTH);
        backgroundLabel.add(buttonPanel, BorderLayout.CENTER);

        mainPanel.add(backgroundLabel, BorderLayout.CENTER);
        add(mainPanel);
        setFocusable(true);
    }

    /**
     * Test to try
     * 
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Menu menu = new Menu();
                Measures measure = new Measures();
                System.out.println("screen height func:" + measure.getScreenHeight());
                System.out.println("screen width func:" + measure.getScreenWidth());
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                System.out.println("Dimensioni dello schermo: " + screenSize.getWidth() + "x" + screenSize.getHeight());
                menu.setVisible(true);
            }
        });
    }
}