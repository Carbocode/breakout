package it.unibo.controller;

import java.util.concurrent.TimeUnit;


import it.unibo.api.BrickWall;
import it.unibo.api.CollisionManager;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.model.Ball;
import it.unibo.model.BarImpl;
import it.unibo.view.SoundManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.util.Set;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import it.unibo.view.*;

public class GameLoop implements ActionListener {
    private static final long UPDATE_INTERVAL = 1000 / GameInfo.REFRESH_RATE;

    private CollisionManager manager;
    private SoundManager soundPlayer;
    private BrickWall brickWall;
    private Set<Ball> balls;
    private BarImpl paddle;


    private long lastUpdateTime;
    private int frames;
    private Timer timer;

    private TEST t;

    public GameLoop(TEST coso) {
        soundPlayer = new SoundManagerImpl();
        brickWall = new BrickWallImpl(GameInfo.GAME_WIDTH, GameInfo.GAME_HEIGHT / 5);
        balls = new HashSet<Ball>();
        balls.add(new Ball());
        brickWall.generateLayout();
        paddle = new BarImpl(new Point(GameInfo.GAME_WIDTH/2,GameInfo.GAME_HEIGHT), new Dimension(30,5), 0, new Color(0));
        manager = new CollisionManager(balls,brickWall,paddle);
        t = coso;


        lastUpdateTime = System.nanoTime();
        timer = new Timer(1000 / GameInfo.REFRESH_RATE, this);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        long currentTime = System.nanoTime();
        long elapsedTime = currentTime - lastUpdateTime;

        if (elapsedTime >= GameInfo.REFRESH_RATE) {
            update();
            lastUpdateTime = currentTime;
            frames++;
        }
    }

    /**
     * Updates the gamestate
     */
    private void update() {

        manager.checkAll();
        for (var b : balls) {
            b.update();
            // System.out.println(b.toString());
        }
        t.repaint();
    }

    public void multiplyBall(Ball old) {

    }


}
