package it.unibo.api;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;

import it.unibo.model.Ball;
import it.unibo.model.Bar;
import it.unibo.model.Bomb;

public class CollisionManager {
    private BrickWall bricks;
    private Set<Ball> balls;
    private Bar paddle;

    private final int BOMB_SIZE_RATIO = 5;

    public CollisionManager(final Set<Ball> balls, final BrickWall brickWall, final Bar paddle) {
        // TODO
        this.balls = balls;
        this.bricks = brickWall;
        this.paddle = paddle;

    }

    public final void checkAll() {
        for (Ball ball : balls) {
            boolean collision = false;
            if (!ball.isAlive()) {
                continue;
            }
            for (GameEntity brick : bricks.getWall()) {
                if (!brick.isAlive()) {
                    continue;
                }
                if (collides(ball, brick)) {
                    // Sometimes the ball collides with multiple bricks at the same time.
                    // this calls its onCollision twice, thus having no effect
                    if (GameInfo.DEBUG_MODE) {
                        System.out.println("Ball at  (" + ball.getPosition().toString()
                                + ") collides with (" + brick.getPosition().toString() + ")");
                    }
                    collision = true;
                    bomb(ball);
                    //brick.onCollision();
                }
            }
            // then we check with paddle
            if (collides(ball, paddle)) {
                System.out.println("Paddle hit");
                collision = true;
            }
            if (collision) {
                ball.onCollision();
            }

        }

    }

    private void bomb(GameEntity ball){
        
        Bomb bomb = new Bomb(new Point(ball.getPosition().x-GameInfo.GAME_WIDTH/(BOMB_SIZE_RATIO*2),ball.getPosition().y-GameInfo.GAME_WIDTH/(BOMB_SIZE_RATIO*2)),new Dimension(GameInfo.GAME_WIDTH/BOMB_SIZE_RATIO, GameInfo.GAME_WIDTH/BOMB_SIZE_RATIO));

        for (GameEntity brick : bricks.getWall()) {
            if (!brick.isAlive()) {
                continue;
            }
            if (collides(bomb, brick)) {
                // Sometimes the ball collides with multiple bricks at the same time.
                // this calls its onCollision twice, thus having no effect
                if (GameInfo.DEBUG_MODE) {
                    System.out.println("Ball at  (" + ball.getPosition().toString()
                             + ") collides with (" + brick.getPosition().toString() + ")");
                }
                brick.onCollision();
            }
        }

    }

    private boolean collides(final GameEntity a, final GameEntity b) {
        Point posA = a.getPosition();
        Dimension sizeA = a.getSize();
        Point posB = b.getPosition();
        Dimension sizeB = b.getSize();
        Rectangle aR = new Rectangle(posA, sizeA);
        Rectangle bR = new Rectangle(posB, sizeB);
        return aR.intersects(bR);
    }
}