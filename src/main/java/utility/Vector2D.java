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

    public static Vector2D add(Vector2D vec1, Vector2D vec2) {
        return new Vector2D(vec1.getX() + vec2.getX(), vec1.getY() + vec2.getY());
    }

    public void sub(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    public void sub(final Vector2D vec) {
        x -= vec.x;
        y -= vec.y;
    }

    public static Vector2D sub(Vector2D vec1, Vector2D vec2) {
        return new Vector2D(vec1.getX() - vec2.getX(), vec1.getY() - vec2.getY());
    }

    public void mul(double n) {
        this.x *= n;
        this.y *= n;
    }

    public static Vector2D mul(Vector2D vec, double n) {
        return new Vector2D(vec.getX() * n, vec.getY() * n);
    }

    public void div(double n) {
        x /= n;
        y /= n;
    }

    public static Vector2D div(Vector2D vec, double n) {
        return new Vector2D(vec.getX() / n, vec.getY() / n);
    }

    public double magnitude() {
        return (double) Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        double m = magnitude();
        if (m != 0 && m != 1) {
            div(m);
        }
    }

    public static Vector2D normalize(Vector2D vec) {
        double m = vec.magnitude();
        if (m != 0 && m != 1) {
            return Vector2D.div(vec, m);
        }
        return vec;
    }

}
