package decide;

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
     * Calculates the length of Point p (if you think of it as a 2d-vector).
     * 
     * @param p: Point
     * @return the length of p
     *         This can potentially be refactored to the Point class.
     */
    public double length(Point p) {
        return Math.sqrt(p.X * p.X + p.Y * p.Y);
    }

    /**
     * Checks if two points coincide
     * 
     * @param p1: First point
     * @param p2: Second point
     * @return true if both the x-value and the y-value of p1 is equal to the
     *         x-value and the y-value of p2
     */
    public boolean pointsEqual(Point p1, Point p2) {
        return p1.X == p2.X && p1.Y == p2.Y;
    }

    /**
     * Calculates dot product of two points
     * 
     * @param p1: First Point
     * @param p2: Second point
     * @return the dot product of p1 and p2
     */
    public double dot_prod(Point p1, Point p2) {
        return p1.X * p2.X + p1.Y * p2.Y;
    }

    /**
     * Calculates the projection of one point on another (if you think of them as 2d-vectors)
     * @param p1: First point (to be projected)
     * @param p2: Second point (to be projected on)
     * @return the projection of p1 on point p2
     */
    public Point projection(Point p1, Point p2) {
        double proj_factor = dot_prod(p1, p2)/(Math.pow(length(p2),2));
        return new Point(proj_factor*p2.X, proj_factor*p2.Y);
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

    /**
     * Loops over all sets of three consecutive data points and checks if LIC2 is
     * satisfied.
     * 
     * @return true if there exists at least one set of three consecutive data
     *         points which form an
     *         angle such that: angle < (PI − EPSILON) or angle > (PI + EPSILON)
     *
     *         The second of the three consecutive points is always the vertex of
     *         the angle.
     *         If either the first point or the last point (or both) coincides with
     *         the vertex,
     *         the angle is undefined and the LIC is not satisfied by those three
     *         points.
     *         (0 ≤ EPSILON < PI)
     */
    public boolean LIC2() {
        double EPSILON = parameters.EPSILON;

        assert EPSILON >= 0 && EPSILON <= Math.PI;
        for (int i = 0; i < numPoints - 2; i++) {
            // Checking that the first and last points are not equal to the second
            if (!pointsEqual(points[i], points[i + 1]) && !pointsEqual(points[i + 1], points[i + 2])) {
                // The "arms" of the angle
                Point arm1 = new Point(points[i].X - points[i + 1].X, points[i].Y - points[i + 1].Y);
                Point arm2 = new Point(points[i + 2].X - points[i + 1].X, points[i + 2].Y - points[i + 1].Y);
                // Calculation of angle using dot-product formula
                // v1 * v2 = ||v1||*||v2||*cos(theta)
                double angle = Math.acos(dot_prod(arm1, arm2) / (length(arm1) * length(arm2)));
                if (angle < Math.PI - EPSILON || angle > Math.PI + EPSILON)
                    return true;
            }
        }
        return false;
    }

    public boolean LIC3() {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean LIC4() {
        int Q_PTS = parameters.Q_PTS;
        int QUADS = parameters.QUADS;
        assert Q_PTS >= 2 && Q_PTS <= numPoints;
        assert QUADS >= 1 && QUADS <= 3;
        for (int i = 0; i <= numPoints - Q_PTS; i++) {
            boolean[] quadrants = new boolean[4];
            for (int j = 0; j < Q_PTS; j++) {
                // Set true if point lies in quadrant 1
                if (points[i + j].X >= 0 && points[i + j].Y >= 0) {
                    quadrants[0] = true;
                }
                // Set true if point lies in quadrant 2
                if (points[i + j].X < 0 && points[i + j].Y >= 0) {
                    quadrants[1] = true;
                }
                // Set true if point lies in quadrant 3
                if (points[i + j].X <= 0 && points[i + j].Y < 0) {
                    quadrants[2] = true;
                }
                // Set true if point lies in quadrant 4
                if (points[i + j].X > 0 && points[i + j].Y < 0) {
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

    /**
     * Loops over all sets of N_PTS consecutive data points and checks if LIC6 is satisfied.
     * @return true if there exists at least one set of N PTS consecutive data points
     * such that at least one of the points lies a distance greater than DIST from the
     * line joining the first and last of these N PTS points. If the first and last points of these
     * N PTS are identical, then the calculated distance to compare with DIST will be the distance
     * from the coincident point to all other points of the N PTS consecutive points.
     * The condition is not met when NUMPOINTS < 3.
     *
     * (3 ≤ N_PTS ≤ NUMPOINTS), (0 ≤ DIST)
     */
    public boolean LIC6() {
        int N_PTS = parameters.N_PTS;
        double DIST = parameters.DIST;
        // If numPoints < 3 the condition is not met
        if (numPoints < 3) {
            return false;
        }
        assert N_PTS >= 3 && N_PTS <= numPoints;
        assert DIST >= 0;
        for (int i = 0; i < numPoints - (N_PTS - 1); i++) {
            Point[] currentPoints = new Point[N_PTS];
            for (int j = 0; j < N_PTS; j++) {
                currentPoints[j] = points[i + j];
            }
            // Special case when first and last point coincide
            if (pointsEqual(currentPoints[0], currentPoints[N_PTS - 1])) {
                for (int j = 1; j < N_PTS - 1; j++) {
                    double dist = distance(currentPoints[0], currentPoints[j]);
                    if (dist > DIST) return true;
                }
            }
            // General case
            else {
                Point line = new Point(currentPoints[N_PTS - 1].X - currentPoints[0].X, currentPoints[N_PTS - 1].Y - currentPoints[0].Y);
                for (int j = 0; j < N_PTS; j++) {
                    // Distance between point and its projection on the line is the shortest distance.
                    if (distance(currentPoints[j], projection(currentPoints[j], line)) > DIST) return true;
                }
            }
        }
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
