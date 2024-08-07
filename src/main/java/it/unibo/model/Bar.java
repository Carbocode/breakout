package it.unibo.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Map;
import java.util.function.Consumer;
import it.unibo.api.GameEntityImpl;
import it.unibo.api.GameInfo;
import it.unibo.api.SoundManager;
import it.unibo.view.SoundManagerImpl;

/**
 * Bar class that handle the movements and dynamics of the bar.
 */
public class Bar extends GameEntityImpl {
    public static final long serialVersionUID = 43287403;

    // (-1) left - (1) right - (0) do not move
    private static final int LEFT_VALUE = -1;
    private static final int RIGHT_VALUE = 1;
    private static final int STOP_VALUE = 0;

    private static final float MOVE_VALUE = (float) 0.01 * (float) GameInfo.GAME_WIDTH;

    private int direction;

    // Map to store key pressed actions, associating each key code with a lambda function
    private static final Map<Integer, Consumer<Bar>> KEY_PRESSED_ACTIONS = Map.of(
        KeyEvent.VK_LEFT, bar -> bar.setDirection(LEFT_VALUE),
        KeyEvent.VK_RIGHT, bar -> bar.setDirection(RIGHT_VALUE)
    );

    // Map to store key released actions, associating each key code with a lambda function
    private static final Map<Integer, Consumer<Bar>> KEY_RELEASED_ACTIONS = Map.of(
        KeyEvent.VK_LEFT, bar -> {
            // If left arrow key is released and the direction is currently left, stop the bar
            if (bar.direction == LEFT_VALUE) {
                bar.setDirection(STOP_VALUE);
            }
        },
        KeyEvent.VK_RIGHT, bar -> {
            // If right arrow key is released and the direction is currently right, stop the bar
            if (bar.direction == RIGHT_VALUE) {
                bar.setDirection(STOP_VALUE);
            }
        }
    );

    /**
     * Standard constructor.
     * 
     * @param position starting position of the bar
     * @param size     dimension of the bar
     * @param health   ignored since bar will not be affected by health
     * @param color    colour displayed in the bar
     */
    public Bar(final Point position, final Dimension size, final int health, final Color color) {
        super(position, size, health, color);
    }

    @Override
    public final void onCollision() {
        // play sound
        final SoundManager sound = new SoundManagerImpl();
        sound.playCollisionSound();
    }

    /**
     * This function simulate the key press.
     * 
     * @return direction
     */
    public int getDirection() {
        return this.direction;
    }

    /**
     * This function set the direction of the bar.
     * 
     * @param d
     */
    public void setDirection(final int d) {
        this.direction = d;
    }

    /**
     * This function move the bar every game cycle depending on the button currently
     * being pressed.
     * if none is pressed the bar will stay in position
     */
    public final void move() {
        Point position = getPosition();
        switch (direction) {
            case LEFT_VALUE:
                if (position.x - MOVE_VALUE > 0) {
                    position.x -= MOVE_VALUE;
                }
                break;

            case RIGHT_VALUE:
                if (position.x + getSize().width + MOVE_VALUE < GameInfo.GAME_WIDTH) {
                    position.x += MOVE_VALUE;
                }
                break;
            default:
                break;
        }
        setPosition(position);
    }

    /**
     * Method used to change the width of the bar during game loop.
     * 
     * @param newwidth change width, used to handle power up
     */
    public final void setWidth(final int newwidth) {
        setSize(new Dimension(newwidth, getSize().height));
    }

    /**
     * This method is used to detect a button pressed by the user.
     * if the button match the arrow left or arrow right,
     * the last button pressed is stored and used for the next movement
     * 
     * @param e button pressed
     */
    public final void buttonPressed(final KeyEvent e) {
        KEY_PRESSED_ACTIONS.getOrDefault(e.getKeyCode(), bar -> { }).accept(this);
    }

    /**
     * This method is used to detect a button released by the user.
     * if the button match the arrow left or arrow right the bar will not be moved
     * until next button pressed
     * 
     * @param e button pressed
     */
    public final void buttonReleased(final KeyEvent e) {
        KEY_RELEASED_ACTIONS.getOrDefault(e.getKeyCode(), bar -> { }).accept(this);
    }
}
