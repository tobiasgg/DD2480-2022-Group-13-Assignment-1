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
     * Loop over all sets of three consecutive data points
     * @param RADIUS1
     * @return true if at least one set can not be contained
     * within or on a circle of radius RADIUS1 (0 ≤ RADIUS1).
     */
    public boolean LIC1(double RADIUS1) {
        if (numPoints < 3) {
            return false;
        }
        assert RADIUS1 >= 0;
        double[] distances = new double[3];
        for (int i = 0; i < numPoints - 2; i++) {
            distances[0] = distance(points[i], points[i+1]);
            distances[1] = distance(points[i+1], points[i+2]);
            distances[2] = distance(points[i], points[i+2]);
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

    /**
     * Loop over all sets of Q_PTS consecutive data points
     * @param Q_PTS
     * @param QUADS
     * @return true if at least on set of points lie in more than QUADS quadrants
     */
    public boolean LIC4(int Q_PTS, int QUADS) {
        assert Q_PTS >= 2 && Q_PTS <= numPoints;
        assert QUADS >= 1 && QUADS <= 3;
        for (int i = 0; i <= numPoints - Q_PTS; i++) {
            boolean[] quadrants = new boolean[4];
            for (int j = 0; j < Q_PTS; j++) {
                // Set true if point lies in quadrant 1
                if (points[i+j].X >= 0 && points[i+j].Y >= 0) {
                    quadrants[0] = true;
                }
                // Set true if point lies in quadrant 2
                if (points[i+j].X < 0 && points[i+j].Y >= 0) {
                    quadrants[1] = true;
                }
                // Set true if point lies in quadrant 3
                if (points[i+j].X <= 0 && points[i+j].Y < 0) {
                    quadrants[2] = true;
                }
                // Set true if point lies in quadrant 4
                if (points[i+j].X > 0 && points[i+j].Y < 0) {
                    quadrants[3] = true;
                }
            }
            int quadrantsCount = 0;
            for (boolean quadrant : quadrants) {
                if (quadrant) {
                    quadrantsCount++;
                }
            }
            if (quadrantsCount > QUADS) {
                return true;
            }
        }

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
