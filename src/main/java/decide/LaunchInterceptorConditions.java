package main.java.decide;

import main.java.decide.Parameters;

public class LaunchInterceptorConditions implements Decide.LaunchInterceptorConditions {
    public Parameters parameters;
    public Point[] points;
    public int numPoints;

    public LaunchInterceptorConditions() {
        // TODO Auto-generated constructor stub
    }

    public void initialize(int numPoints, Point[] points, Parameters parameters) {
        this.numPoints = numPoints;
        this.points = points;
        this.parameters = parameters;
    }

    public double distance(Point p1, Point p2) {
        double x = p2.X - p1.X;
        double y = p2.Y - p1.Y;
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Loops over all sets of two consecutive data points and checks if LIC0 is
     * satisfied.
     * 
     * @return true if at least one set is separated by a distance of more than
     *         LENGTH1 (0 ≤ LENGTH1)
     */
    public boolean LIC0() {
        double LENGTH1 = parameters.LENGTH1;

        for (int i = 0; i < numPoints - 1; i++) {
            double dist = distance(points[i], points[i + 1]);
            if (dist > LENGTH1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Loop over all sets of three consecutive data points and check if LIC1 is
     * satisfied.
     * 
     * @return true if at least one set can not be contained within or on a circle
     *         of radius RADIUS1 (0 ≤ RADIUS1).
     */
    public boolean LIC1() {
        double RADIUS1 = parameters.RADIUS1;

        if (numPoints < 3) {
            return false;
        }
        assert RADIUS1 >= 0;
        double[] distances = new double[3];
        for (int i = 0; i < numPoints - 2; i++) {
            distances[0] = distance(points[i], points[i + 1]);
            distances[1] = distance(points[i + 1], points[i + 2]);
            distances[2] = distance(points[i], points[i + 2]);
            for (double distance : distances) {
                if (distance / 2 > RADIUS1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean LIC2() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC3() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC4() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC5() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC6() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC7() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC8() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC9() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC10() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC11() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC12() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC13() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC14() {
        // TODO Auto-generated method stub
        return false;
    }
}
