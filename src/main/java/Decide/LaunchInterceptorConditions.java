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

    /**
     * Loops over all sets of two consecutive data points and checks if LIC0 is satisfied.
     * @return true if at least one set is separated by a distance of more than LENGTH1 (0 ≤ LENGTH1)
     */
    public boolean LIC0(double LENGTH1) {
        for (int i = 0; i < numPoints - 1; i++) {
            double dist = distance(points[i], points[i + 1]);
            if (dist > LENGTH1){
                return true;
            }
        }
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

    /**
     * 
     * @param X
     * @param Y
     * @param numpoints
     * @param area1
     * @return
     */
    public boolean LIC3(double area1) {
        double x1, y1, x2, y2, x3, y3, area;
        assert area1 > 0;

        for (int i = 0; i < numPoints - 2; i++){
            x1 = points[i].X;
            y1 = points[i].Y;
            x2 = points[i + 1].X;
            y2 = points[i + 1].Y;
            x3 = points[i + 2].X;
            y3 = points[i + 2].Y;
    
            //Given 3 vertices of a triangle, area is calculated as such:
            area = Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 *(y1 - y2)));
            area = area / 2;
    
            if(area > area1){
                return true;
            }
        }
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
    /**
     * @return true if there exists at least one set of two consecutive data points, 
     *  (X[i],Y[i]) and (X[j],Y[j]), such that X[j] - X[i] < 0. (where i = j-1).
     */
    public boolean LIC5() {
        for(int i = 0; i < numPoints - 1; i++){
            if((points[i + 1].X - points[i].X) > 0 ){
                return true;
            }
        }
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
    
    public boolean LIC8(double RADIUS1, int A_PTS, int B_PTS) {
        
        assert A_PTS >= 1 && B_PTS >= 1;
        assert RADIUS1 >= 0;

        double[] dist = new double[3];
        if(numPoints >= 5){
            for(int i = 0; i < numPoints - A_PTS - B_PTS - 2; i++){
                dist[i] = distance(points[i], points[i + A_PTS + 1]);
                dist[i + 1] = distance(points[i + A_PTS + 1], points[i + A_PTS + B_PTS + 2]);
                dist[i + 2] = distance(points[i], points[i + A_PTS + B_PTS + 2]);

                for(double dists : dist) {
                    if(dists / 2 > RADIUS1){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public boolean LIC9() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC10(double AREA1, int E_PTS, int F_PTS) {
        
        assert E_PTS >= 1 && F_PTS >= 1;
        assert AREA1 >= 0;

        double x1, y1, x2, y2, x3, y3, area;
        if(numPoints >= 5){
            for(int i = 0; i < numPoints - E_PTS - F_PTS - 2; i++){
                x1 = points[i].X;
                y1 = points[i].Y;
                x2 = points[i + E_PTS + 1].X;
                y2 = points[i + E_PTS + 1].Y;
                x3 = points[i + E_PTS + F_PTS + 2].X;
                y3 = points[i + E_PTS + F_PTS + 2].Y;

                area = Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 *(y1 - y2)));
                area = area / 2;

                if(area > AREA1){
                    return true;
                }
            }
        }
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

    public boolean LIC14(double AREA1, double AREA2, int E_PTS, int F_PTS) {

        assert AREA2 >= 0;

        boolean first = false;
        boolean second = false;

        double x1, y1, x2, y2, x3, y3, area;
        if(numPoints >= 5){
            for(int i = 0; i < numPoints - E_PTS - F_PTS - 2; i++){
                x1 = points[i].X;
                y1 = points[i].Y;
                x2 = points[i + E_PTS + 1].X;
                y2 = points[i + E_PTS + 1].Y;
                x3 = points[i + E_PTS + F_PTS + 2].X;
                y3 = points[i + E_PTS + F_PTS + 2].Y;

                area = Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 *(y1 - y2)));
                area = area / 2;

                if(area > AREA1){
                    first = true;
                }
                if(area < AREA2){
                    second = true;
                }
                if(first == true && second == true){
                    return true;
                }
            }
        }
        return false;
    }
}
