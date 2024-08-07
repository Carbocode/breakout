package it.unibo.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test class for the ScoreManagerImpl class.
 */
class ScoreManagerImplTest {

    private final ScoreManagerImpl scoreManager;

    /**
     * Set up the test environment before each test case.
     */
    ScoreManagerImplTest() {
        scoreManager = new ScoreManagerImpl();
    }

    /**
     * Test the constructor of the ScoreManagerImpl class.
     */
    @Test
    void testConstructor() {
        assertEquals(0, scoreManager.getScore());
    }

    /**
     * Test the increment method of the ScoreManagerImpl class.
     */
    @Test
    void testIncrement() {
        final int points1 = 100;
        scoreManager.increment(points1);
        assertEquals(points1, scoreManager.getScore());

        final int points2 = 50;
        final int totalPoints = 150;
        scoreManager.increment(points2);
        assertEquals(totalPoints, scoreManager.getScore());
    }

    /**
     * Test the getScore method of the ScoreManagerImpl class.
     */
    @Test
    void testGetScore() {
        assertEquals(0, scoreManager.getScore());
        final int points1 = 50;
        scoreManager.increment(points1);
        assertEquals(points1, scoreManager.getScore());

        final int points2 = 50;
        final int totalPoints2 = 100;
        scoreManager.increment(points2);
        assertEquals(totalPoints2, scoreManager.getScore());
    }
}
