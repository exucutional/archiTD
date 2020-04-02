package objects;

import utility.Vector2D;

abstract public class Object {

    protected Vector2D position = new Vector2D(0, 0);
    protected Vector2D velocity = new Vector2D(0, 0);
    protected Vector2D acceleration = new Vector2D(0, 0);

    public Object() {
        ;
    }

    public Object(double x, double y) {
        position = new Vector2D(x, y);
    }

    public Object(Vector2D v) {
        position = new Vector2D(v);
    }

    public void setPosition(double x, double y) {
        position.setVector(x, y);
    }

    public void setPosition(Vector2D v) {
        setPosition(v.getX(), v.getY());
    }

    public void setVelocity(double x, double y) {
        velocity.setVector(x, y);
    }

    public void setVelocity(Vector2D v) {
        setVelocity(v.getX(), v.getY());
    }

    public void setAcceleration(double x, double y) {
        acceleration.setVector(x, y);
    }

    public void setAcceleration(Vector2D vec) {
        setAcceleration(vec.getX(), vec.getY());
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public Vector2D getAcceleration() {
        return acceleration;
    }

    public void update(double dt) {
        velocity.add(acceleration.getX() * dt, acceleration.getY() * dt);
        position.add(velocity.getX() * dt, velocity.getY() * dt);
        acceleration.setVector(0, 0);
    }

    public String toString()
    {
        return "Position: [" + position.getX() + "," + position.getY() + "]" 
        + " Velocity: [" + velocity.getX() + "," + velocity.getY() + "]";
    }
}