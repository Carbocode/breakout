package it.unibo.view;

import javax.swing.*;
import java.util.*;
import java.io.*;

import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.controller.GameLoop;
import it.unibo.controller.Match;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the menu frame, where you can choose between the play, scoreboard,
 * exit button
 * if you choose play it will open the game
 * 
 * @author Sohail Mama
 */
public class Menu extends JFrame {
    private static JPanel mainPanel;
    private JLabel titleLabel;
    private JButton playButton;
    private JButton scoreboardButton;
    private JButton exitButton;
    private Font font;

    private SoundManager sound = new SoundManagerImpl();
    private Measures measure = new Measures();

    public Menu() {
        setTitle("Breakout");
        setSize(measure.getGameAreaWidth(), measure.getGameAreaHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // setting of an external font
        InputStream fontStream;
        try {
            fontStream = new BufferedInputStream(getClass().getClassLoader().getResourceAsStream("font/8-bit-hud.ttf"));
            font = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // creation of the panel of the menu
        mainPanel = new JPanel(new GridLayout(2, 1));
        titleLabel = new JLabel("BREAKOUT", SwingConstants.CENTER);
        titleLabel.setFont(font.deriveFont(55.0f));

        // these are the buttons of the menu with their panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 10));
        buttonPanel.setOpaque(false);

        playButton = new JButton("PLAY");
        playButton.setBackground(Color.ORANGE);
        playButton.setPreferredSize(new Dimension(100, 50));

        scoreboardButton = new JButton("SCOREBOARD");
        scoreboardButton.setBackground(Color.ORANGE);
        scoreboardButton.setPreferredSize(new Dimension(100, 50));

        exitButton = new JButton("EXIT");
        exitButton.setBackground(Color.ORANGE);
        exitButton.setPreferredSize(new Dimension(100, 50));

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame game = new JFrame();
                GameView gamePanel = new GameView();
                gamePanel = new GameView();
                game.add(gamePanel);
                Match.init(gamePanel);
                game.pack();
                game.setVisible(true);
                // it close the other window
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();

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

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        mainPanel.setVisible(true);
        setFocusable(true);
        setVisible(true);
    }

}
