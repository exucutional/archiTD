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

    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2D add(final Vector2D vec) {
        x += vec.x;
        y += vec.y;
        return this;
    }

    public static Vector2D add(Vector2D vec1, Vector2D vec2) {
        return new Vector2D(vec1.getX() + vec2.getX(), vec1.getY() + vec2.getY());
    }

    public Vector2D sub(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2D sub(final Vector2D vec) {
        x -= vec.x;
        y -= vec.y;
        return this;
    }

    public static Vector2D sub(Vector2D vec1, Vector2D vec2) {
        return new Vector2D(vec1.getX() - vec2.getX(), vec1.getY() - vec2.getY());
    }

    public Vector2D mul(double n) {
        this.x *= n;
        this.y *= n;
        return this;
    }

    public static Vector2D mul(Vector2D vec, double n) {
        return new Vector2D(vec.getX() * n, vec.getY() * n);
    }

    public Vector2D div(double n) {
        x /= n;
        y /= n;
        return this;
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

    public static double dotProduct(Vector2D vec1, Vector2D vec2) {
        return vec1.getX() * vec2.getX() + vec1.getY() * vec2.getY();
    }

    public static double getAngle(Vector2D vec1, Vector2D vec2) {
        return Math.acos(dotProduct(vec1, vec2) / (vec1.magnitude() * vec2.magnitude()));
    }

}
