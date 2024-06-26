package it.unibo.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Dimension;
import java.awt.Point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test case of bomb class.
 */
class BombTest {

    private Bomb bomb;
    private Point position;
    private Dimension size;

    // Definizione delle costanti
    private static final int POSITION_X = 10;
    private static final int POSITION_Y = 20;
    private static final int SIZE_WIDTH = 30;
    private static final int SIZE_HEIGHT = 40;

    /**
     * before each test run set up test enviroment.
     */
    @BeforeEach
    void setUp() {
        position = new Point(POSITION_X, POSITION_Y);
        size = new Dimension(SIZE_WIDTH, SIZE_HEIGHT);
    }

    /**
     * test general constructor.
     */
    @Test
    void testConstructor() {
        bomb = new Bomb(position, size);
        assertNotNull(bomb);
        assertEquals(position, bomb.getPosition());
        assertEquals(size, bomb.getSize());
    }

    /**
     * test on collision (actually do nothing).
     */
    @Test
    void testOnCollision() {
        bomb = new Bomb(position, size);

        final Point initialPosition = bomb.getPosition();
        final Dimension initialSize = bomb.getSize();

        bomb.onCollision();

        assertEquals(initialPosition, bomb.getPosition());
        assertEquals(initialSize, bomb.getSize());
    }
}
