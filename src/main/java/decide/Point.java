package main.java.decide;

public class Point {
    double X;
    double Y;

    public Point() {
        this.X = 0;
        this.Y = 0;
    }

    public Point(double x, double y) {
        this.X = x;
        this.Y = y;
    }

    public void initialize(double x, double y) {
        this.X = x;
        this.Y = y;
    }
}
