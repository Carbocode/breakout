package it.unibo.model;

import it.unibo.api.Direction;
import it.unibo.api.GameEntityImpl;


import java.awt.Dimension;
import java.awt.Point;

public class Ball extends GameEntityImpl{
    private Point pos;
    private Direction dir;

    private boolean alive;
    public Ball(){
        super(new Point(50,50), new Dimension(5,5),1);
        this.dir = new Direction(1, 1);
        this.alive = true;
    }
    //duplicating a ball
    public Ball(Ball orig){
        super(orig.getPosition(), orig.getSize(), 1);
        // we invert the direction so they dont overlap.
        this.dir = new Direction(-orig.getDirection().getHorizontalVelocity(), orig.getDirection().GetVerticalVelocity());
        this.alive = true;
    }
    
    private Direction getDirection() {
        return dir;
    }
    public void update(){
        Point candidate = new Point(pos.x + dir.getHorizontalVelocity(), pos.y + dir.GetVerticalVelocity());
        //we validate it. if its out of bounds, we reverse direction
        if (candidate.getX()<= 0 || candidate.getX() >= 500){
            dir = new Direction(dir.getHorizontalVelocity(), dir.GetVerticalVelocity());
        }
        // if the ball fell
        if(candidate.getY() > 500){
            die();
        }
        pos = candidate;
        pos = candidate;
    }
    private void die(){
        this.alive = false;
    }
    public boolean isAlive(){
        return this.alive;
    }
    @Override
    public void onCollision() {
        // much code
    }
    @Override
    public Point getPosition() {
        return this.pos;
    }
    @Override
    public Dimension getSize() {
        return size;
    }

}
