package utility;

public class Vector2D {

    private double x = 0;
    private double y = 0;

    public Vector2D() {
        ;
    }

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(final Vector2D vec) {
        x = vec.x;
        y = vec.y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setVector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(final Vector2D vec) {
        x = vec.x;
        y = vec.y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void add(final Vector2D vec) {
        x += vec.x;
        y += vec.y;
    }

    public void multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    public void multiply(final Vector2D vec) {
        x *= vec.x;
        y *= vec.y;
    }
}
