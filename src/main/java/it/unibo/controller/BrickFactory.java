package it.unibo.controller;

import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import it.unibo.model.Brick;
import it.unibo.model.BrickColors;
import it.unibo.model.BrickTypes;

/**
 * Factory pattern that creates all kinds of brick the game has
 */
public class BrickFactory {

    static private long seed = System.currentTimeMillis();
    static private final Random rand = new Random(BrickFactory.seed);

    /**
     * Choses a Color for the Brick
     * 
     * @return a Color for the Brick
     */
    static public Color getRandomColor() {
        BrickColors[] colors = BrickColors.values();
        return colors[rand.nextInt(colors.length)].getColor();
    }

    /**
     * Choses a Health for the Brick
     * 
     * @return a Health for the Brick
     */
    static public int getRandomHealth() {
        BrickTypes[] colors = BrickTypes.values();
        return colors[rand.nextInt(colors.length)].getHealth();
    }

    /**
     * Creates a brick with defined health
     * 
     * @param position position of the brick
     * @param size     size of the brick
     * 
     * @return Brick
     */
    static public Brick createRandomBrick(Point position, Dimension size) {
        return new Brick(position, size, getRandomHealth(), getRandomColor());
    }

    static public void setSeed(long seed) {
        BrickFactory.seed = seed;
        BrickFactory.rand.setSeed(seed);
    }

    static public long getSeed() {
        return BrickFactory.seed;
    }

}