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
     * Calculates the angle formed by p1, p2 and p3,
     *  where p2 is the vertex
     */
    public double angle(Point p1, Point p2, Point p3) {
        double p12 = distance(p1, p2);
        double p23 = distance(p2, p3);
        double p13 = distance(p1, p3);
        // https://www.mathsisfun.com/algebra/trig-cosine-law.html
        return Math.acos((p12*p12 + p23*p23 - p13*p13) / (2*p12*p23));
    }

    public boolean angleOutOfRange(Point p1, Point p2, Point p3, double EPSILON) {
        assert EPSILON >= 0 && EPSILON < Math.PI;
        if (pointsEqual(p1, p2) || pointsEqual(p2, p3)) {
            return false;
        }
        return angle(p1, p2, p3) < Math.PI - EPSILON || angle(p1, p2, p3) > Math.PI + EPSILON;
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
     * @return true if p1, p2, and p3 can be contained with in a circle of radius r
     * https://math.stackexchange.com/questions/2234478/given-a-set-of-3-2d-points-how-to-find-if-they-lie-within-a-circle-of-a-given-r
     */
    public boolean pointsContainedInCircle(Point p1, Point p2, Point p3, double r) {
        double side12 = distance(p1, p2);
        double side23 = distance(p2, p3);
        double side13 = distance(p1, p3);
        // if any of the three sides > 2 * r,
        // then the points cannot be contained in a circle of radius r
        if (side12 > 2 * r || side23 > 2 * r || side13 > 2 * r) {
            return false;
        }
        // if they form an obtuse or a right triangle,
        // the smallest circle enclosing it would have a diameter of the longest edge
        if (side12*side12 + side13*side13 <= side23*side23) {
            return 2 * r >= side23;
        }
        else if (side23*side23 + side13*side13 <= side12*side12) {
            return 2 * r >= side12;
        }
        else if (side12*side12 + side23*side23 <= side13*side13) {
            return 2 * r >= side13;
        }
        // if they form an acute triangle,
        // calculate the circumcircle radius
        // https://en.wikipedia.org/wiki/Circumscribed_circle
        else {
            double d = 2*(p1.X*(p2.Y - p3.Y) + p2.X*(p3.Y - p1.Y) + p3.X*(p1.Y - p2.Y));
            double ux = 1/d*( length(p1)*(p2.Y - p3.Y) + length(p2)*(p3.Y-p1.Y) + length(p3)*(p1.Y-p2.Y) );
            double uy = 1/d*( length(p1)*(p3.X - p2.X) + length(p2)*(p1.X-p3.X) + length(p3)*(p2.X-p1.X) );
            return distance(new Point(ux, uy), p1) <= r;
        }
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
        for (int i = 0; i < numPoints - 2; i++) {
            if (!pointsContainedInCircle(points[i], points[i + 1], points[i + 2], RADIUS1)) {
                return true;
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
        double AREA1 = parameters.AREA1;

        double x1, y1, x2, y2, x3, y3, area;
        assert AREA1 > 0;

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
    
            if(area > AREA1){
                return true;
            }
        }
        return false;
    }

    /**
     * Loop over all sets of Q_PTS consecutive data points
     *
     * @return true if at least one of these sets of points
     * lie in more than QUADS quadrants
     */
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
        for(int i = 0; i < numPoints - 1; i++){
            if(points[i + 1].X - points[i].X < 0 ){
                return true;
            }
        }
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

    /**
     * Loops over all sets of two data points separated by K_PTS points and checks if LIC7 is
     * satisfied.
     * @return true if there exists at least one set of two data points
     * separated by exactly K_PTS consecutive intervening points that are
     * a distance greater than the length, LENGTH1, apart.
     * The condition is not met when NUMPOINTS < 3.
     *
     * 1 ≤ K_PTS ≤ (NUMPOINTS − 2)
     */
    public boolean LIC7() {
        int K_PTS = parameters.K_PTS;
        double LENGTH1 = parameters.LENGTH1;
        if (numPoints < 3) return false;
        assert K_PTS >= 1 && K_PTS <= numPoints - 2;
        assert LENGTH1 >= 0;
        for (int i = 0; i < numPoints - (K_PTS + 1); i++) {
            Point p1 = points[i];
            Point p2 = points[i + K_PTS + 1];
            if (distance(p1, p2) > LENGTH1) return true;
        }
        return false;
    }

    public boolean LIC8() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Loop over all sets of three data points
     * separated by C_PTS and D_PTS consecutive data points
     *
     * @return true if at least one of these sets of points
     * form an angle greater than (PI − EPSILON) or less than (PI + EPSILON)
     */
    public boolean LIC9() {
        int C_PTS = parameters.C_PTS;
        int D_PTS = parameters.D_PTS;
        double EPSILON = parameters.EPSILON;
        assert C_PTS >= 1 && D_PTS >= 1: "C_PTS and D_PTS must be positive integers, but are " + C_PTS + " and " + D_PTS;
        assert C_PTS + D_PTS <= numPoints - 3: "C_PTS + D_PTS must be less than or equal to numPoints - 3";
        if (numPoints < 5) {
            return false;
        }
        for (int i = 0; i < numPoints - C_PTS - D_PTS - 2; i++) {
            Point p1 = points[i];
            Point p2 = points[i + C_PTS + 1];
            Point p3 = points[i + C_PTS + D_PTS + 2];
            if (angleOutOfRange(p1, p2, p3, EPSILON)) {
                return true;
            }
        }
        return false;
    }

    public boolean LIC10() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * Loop over all sets of two data points separated by G_PTS points
     * @return true if at least one of these sets of points,
     * Point 1 and Point 2, such that Point2.X - Point1.X < 0
     */
    public boolean LIC11() {
        int G_PTS = parameters.G_PTS;
        assert G_PTS >= 1: "G_PTS must be a positive integer, but is " + G_PTS;
        assert G_PTS <= numPoints - 2: "G_PTS must be less than or equal to numPoints - 2";
        if (numPoints < 3) {
            return false;
        }
        for (int i = 0; i < numPoints - G_PTS - 1; i++) {
            Point p1 = points[i];
            Point p2 = points[i + G_PTS + 1];
            if (p2.X - p1.X < 0) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return true if there exists at least one set of two data points,
     * separated by exactly K_PTS consecutive intervening points,
     * which are a distance greater than the length, LENGTH1, apart.
     * In addition, there exists at least one set of two data points
     * (which can be the same or different from the two data points just mentioned),
     * separated by exactly K_PTS consecutive intervening points,
     * that are a distance less than the length, LENGTH2, apart.
     * Both parts must be true for the LIC to be true.
     * The condition is not met when NUMPOINTS < 3.
     *
     * 0 ≤ LENGTH2
     */
    public boolean LIC12() {
        int K_PTS = parameters.K_PTS;
        double LENGTH1 = parameters.LENGTH1;
        double LENGTH2 = parameters.LENGTH2;
        if (numPoints < 3) return false;
        assert K_PTS >= 1 && K_PTS <= numPoints - 2;
        assert LENGTH1 >= 0;
        assert LENGTH2 >= 0;
        // Condition one: There exist a set of two points with K_PTS between them
        // that is at a distance larger than LENGTH1 Apart
        boolean cond1 = false;
        // Condition two: There exist a set of two points with K_PTS between them
        // that is at a distance shorter than LENGTH2 Apart
        boolean cond2 = false;
        for (int i = 0; i < numPoints - (K_PTS + 1); i++) {
            Point p1 = points[i];
            Point p2 = points[i + K_PTS + 1];
            if (distance(p1, p2) > LENGTH1) {
                cond1 = true;
                if (cond2) return true;
            }
            if (distance(p1, p2) < LENGTH2) {
                cond2 = true;
                if (cond1) return true;
            }
        }
        return false;
    }

    /**
     * Loop over all sets of three data points separated by A_PTS and B_PTS consecutive points
     * @return true if at least one of these sets of points cannot be contained within or on a circle
     * of radius RADIUS1, and at least one of these sets of points can be contained within or on a circle
     * of radius RADIUS2.
     */
    public boolean LIC13() {
        int A_PTS = parameters.A_PTS;
        int B_PTS = parameters.B_PTS;
        double RADIUS1 = parameters.RADIUS1;
        double RADIUS2 = parameters.RADIUS2;
        assert RADIUS1 >= 0: "RADIUS1 must be non-negative, but is " + RADIUS1;
        assert RADIUS2 >= 0: "RADIUS2 must be non-negative, but is " + RADIUS2;
        if (numPoints < 5) {
            return false;
        }
        boolean flag1 = false;
        boolean flag2 = false;
        for (int i = 0; i < numPoints - A_PTS - B_PTS - 2; i++) {
            Point p1 = points[i];
            Point p2 = points[i + A_PTS + 1];
            Point p3 = points[i + A_PTS + B_PTS + 2];
            if (!pointsContainedInCircle(p1, p2, p3, RADIUS1)) {
                flag1 = true;
            }
            if (pointsContainedInCircle(p1, p2, p3, RADIUS2)) {
                flag2 = true;
            }
        }
        return flag1 && flag2;
    }

    public boolean LIC14() {
        // TODO Auto-generated method stub
        return false;
    }
}
