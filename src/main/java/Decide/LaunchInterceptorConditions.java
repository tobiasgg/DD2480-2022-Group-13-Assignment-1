package Decide;

public class LaunchInterceptorConditions {
    public Parameters parameters;
    public Point[] points;
    public int numPoints;

    public LaunchInterceptorConditions () {
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
        return Math.sqrt(x*x + y*y);
    }

    public boolean LIC0() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Loop over all sets of three consecutive data points and check if LIC1 is satisfied.
     * @return true if at least one set can not be contained within or on a circle of radius RADIUS1 (0 ≤ RADIUS1).
     */
    public boolean LIC1(double RADIUS1) {
        double[] distances = new double[3];
        for (int i = 0; i < numPoints - 2; i++) {
            distances[1] = distance(points[i], points[i+1]);
            distances[2] = distance(points[i+1], points[i+2]);
            distances[0] = distance(points[i], points[i+2]);
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
