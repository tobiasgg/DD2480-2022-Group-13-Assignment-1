import org.junit.jupiter.api.Test;

import decide.*;

import static org.junit.jupiter.api.Assertions.*;

public class LaunchInterceptorConditionsTest {

    /**
     * There exists at least one set of two consecutive data points that are 
     * a distance greater than the length, LENGTH1, apart.
     */
    @Test
    void LIC0_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();

        int numPoints = 3;
        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        points[1] = new Point(2, 0);
        points[2] = new Point(1, 0);

        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);

        p.LENGTH1 = 1.9;
        assertTrue(LIC.LIC0());

        p.LENGTH1 = 2.0;
        assertFalse(LIC.LIC0());

        points[0] = new Point(-1, 0);
        points[1] = new Point(-2, 0);
        points[2] = new Point(0, 0);

        p.LENGTH1 = 1.9;
        assertTrue(LIC.LIC0());

        p.LENGTH1 = 2.0;
        assertFalse(LIC.LIC0());

        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);

        p.LENGTH1 = 0.0;
        assertFalse(LIC.LIC0());

        p.LENGTH1 = -1;
        assertThrows(AssertionError.class, () -> LIC.LIC0());
    }

    /**
     * There exists at least one set of three consecutive data points that cannot all 
     * be contained within or on a circle of radius RADIUS1.
     */
    @Test
    void LIC1_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);

        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);

        p.RADIUS1 = 0.95;
        assertTrue(LIC.LIC1());

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        p.RADIUS1 = 1;
        assertFalse(LIC.LIC1());
        p.RADIUS1 = -1;
        assertThrows(AssertionError.class, () -> LIC.LIC1());
    }

    /**
     * There exists at least one set of three consecutive data points which form an angle 
     * such that: angle < (PI − EPSILON) or angle > (PI + EPSILON). 
     * The second of the three consecutive points is always the vertex of the angle. 
     * If either the first point or the last point (or both) coincides with the vertex, 
     * the angle is undefined and the LIC is not satisfied by those three points.
     */
    @Test
    void LIC2_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];
        points[0] = new Point(1.5, 0.5);
        points[1] = new Point(0.5, -0.5);
        points[2] = new Point(3.5, -0.5);
        LIC.numPoints = numPoints;
        LIC.points = points;

        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);

        p.EPSILON = Math.PI / 2;
        assertTrue(LIC.LIC2());

        p.EPSILON = 3 * Math.PI / 4;
        assertFalse(LIC.LIC2());

        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);

        p.EPSILON = 0.1;
        assertFalse(LIC.LIC2());

        points[0] = new Point(1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        p.EPSILON = 0.1;
        assertFalse(LIC.LIC2());

        p.EPSILON = -1.0;
        assertThrows(AssertionError.class, () -> LIC.LIC2());

        p.EPSILON = 4.0;
        assertThrows(AssertionError.class, () -> LIC.LIC2());
    }

    /**
     * Exists atleast one set of three consecutive points that are
     * the vertices of a triangle with an area greater than AREA1.
     * AREA1 >= 0
     */
    @Test
    void LIC3_test(){
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0, 1);
        points[1] = new Point(3, 0);
        points[2] = new Point(0, 6);
        //These 3 points generates an area of 7.5. Test should be true.
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.AREA1 = 4.0;
        assertTrue(LIC.LIC3());

        points[0] = new Point(0, 1);
        points[1] = new Point(0, 1);
        points[2] = new Point(0, 1);
        //These 3 points generates an area of 0. Test should be false.
        LIC.numPoints = numPoints;
        LIC.points = points;
        p.AREA1 = 5.0;
        assertFalse(LIC.LIC3());
        //Area cannot be negative.
        p.AREA1 = -1;
        assertThrows(AssertionError.class, () -> LIC.LIC3());

    }

    /**
     * There exists at least one set of Q PTS consecutive data points that lie in 
     * more than QUADS quadrants. Where there is ambiguity as to which quadrant contains 
     * a given point, priority of decision will be by quadrant number, i.e., I, II, III, IV. 
     */
    @Test
    void LIC4_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.Q_PTS = 2;
        p.QUADS = 1;
        assertTrue(LIC.LIC4());
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        p.Q_PTS = 2;
        p.QUADS = 3;
        assertFalse(LIC.LIC4());
        p.Q_PTS = 1;
        p.QUADS = 0;
        assertThrows(AssertionError.class, () -> LIC.LIC4());
    }

    /**
     * Exists atleast on set of consecutive datapoints where 
     * X[j] - X[i] < 0, where i = j - 1.
     */

    @Test
    void LIC5_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        //points[1] - points[0] < 0. Test should be true.
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        assertTrue(LIC.LIC5());

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(2, 0);
        //No set of consecutive points fulfills X[j] - X[i] < 0. Test should be false.
        assertFalse(LIC.LIC5());
    }

    /**
     * There exists at least one set of N PTS consecutive data points such that at least 
     * one of the points lies a distance greater than DIST from the line joining the first 
     * and last of these N PTS points. If the first and last points of these N PTS are 
     * identical, then the calculated distance to compare with DIST will be the distance 
     * from the coincident point to all other points of the N PTS consecutive points.
     */
    @Test
    void LIC6_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 1.1);
        points[2] = new Point(1, 0);
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.N_PTS = 3;
        p.DIST = 1;
        assertTrue(LIC.LIC6());
        p.DIST = 2;
        assertFalse(LIC.LIC6());
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 0);
        p.DIST = 0.9;
        assertTrue(LIC.LIC6());
        p.DIST = 1.0;
        assertFalse(LIC.LIC6());
        p.N_PTS = 2;
        assertThrows(AssertionError.class, () -> LIC.LIC6());
        p.DIST = -1.0;
        assertThrows(AssertionError.class, () -> LIC.LIC6());
        LIC.numPoints = 2;
        assertFalse(LIC.LIC6());
    }

    /**
     * There exists at least one set of two data points separated by exactly K PTS
     * consecutive points that are a distance greater than the length, LENGTH1, apart. 
     */
    @Test
    void LIC7_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 4;
        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(2, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.K_PTS = 2;
        p.LENGTH1 = 1.9;
        assertTrue(LIC.LIC7());
        p.K_PTS = 1;
        assertFalse(LIC.LIC7());
        p.K_PTS = 2;
        p.LENGTH1 = 3;
        assertFalse(LIC.LIC7());
        p.K_PTS = 5;
        assertThrows(AssertionError.class, () -> LIC.LIC7());
        p.K_PTS = 2;
        p.LENGTH1 = -1.0;
        assertThrows(AssertionError.class, () -> LIC.LIC7());
        LIC.numPoints = 2;
        assertFalse(LIC.LIC7());
    }

    /**
     * Exists atleast one set of three consecutive datapoints separated by
     * A_BTS and B_PTS respectively that cannot be contained within or on
     * a circle of radius RADIUS1.
     */
    @Test
    void LIC8_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 7;
        Point[] points = new Point[numPoints];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, 0);
        points[4] = new Point(1, 0);
        points[5] = new Point(0, 0);
        points[6] = new Point(0, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.RADIUS1 = 0.95;
        p.A_PTS = 1;
        p.B_PTS = 1;
        assertTrue(LIC.LIC8());
        /**
         * A_PTS = B_PTS = 1. points[0], points[2] and points[4] are separated by A_PTS
         * and B_PTS and cannot be contained by a circle with radius 0.95(needs to be
         * atleast 1). Test should be true.
         */

        points[0] = new Point(0, 0);
        points[1] = new Point(4, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(4, 0);
        points[4] = new Point(0, 0);
        points[5] = new Point(4, 0);
        points[6] = new Point(0.5, 0);
        p.RADIUS1 = 0.95;
        p.A_PTS = 1;
        p.B_PTS = 1;
        assertFalse(LIC.LIC8());
        /**
         * A_PTS = B_PTS 1. There exist no consecutive datapoints separated by A_PTS and
         * B_PTS that cannot be contained by a circle with radius 0.95. Test should be false.
         */

        p.RADIUS1 = -1;
        p.A_PTS = 0;
        p.B_PTS = 0;
        assertThrows(AssertionError.class, () -> LIC.LIC8());
        //RADIUS1 cannot be negative. A_PTS >= 1 && B_PTS >= 1. 
    }

    /**
     * There exists at least one set of three data points separated by exactly C PTS 
     * and D PTS consecutive intervening points, respectively, that form an angle 
     * such that: angle < (PI − EPSILON) or angle > (PI + EPSILON)
     */
    @Test
    void LIC9_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 7;
        Point[] points = new Point[7];

        points[0] = new Point(0, 3);
        points[1] = new Point(1, 0);
        points[2] = new Point(2, 0);
        points[3] = new Point(0, 0);
        points[4] = new Point(4, 0);
        points[5] = new Point(5, 0);
        points[6] = new Point(3, 0);
        Parameters p = new Parameters();
        p.C_PTS = 2;
        p.D_PTS = 2;
        p.EPSILON = 1;
        LIC.initialize(numPoints, points, p);
        assertTrue(LIC.LIC9());

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(2, 0);
        points[3] = new Point(3, 0);
        points[4] = new Point(4, 0);
        points[5] = new Point(5, 0);
        points[6] = new Point(0, 0);
        p.C_PTS = 2;
        p.D_PTS = 2;
        p.EPSILON = 1;
        assertTrue(LIC.LIC9());

        points[0] = new Point(1, 0);
        points[1] = new Point(2, 0);
        points[2] = new Point(3, 0);
        points[3] = new Point(2, 0);
        points[4] = new Point(4, 0);
        points[5] = new Point(5, 0);
        points[6] = new Point(2, 0);
        p.C_PTS = 2;
        p.D_PTS = 2;
        p.EPSILON = 0;
        assertFalse(LIC.LIC9());

        points[0] = new Point(1, 0);
        points[1] = new Point(2, 0);
        points[2] = new Point(3, 0);
        points[3] = new Point(1, 0);
        points[4] = new Point(4, 0);
        points[5] = new Point(5, 0);
        points[6] = new Point(2, 0);
        p.C_PTS = 2;
        p.D_PTS = 2;
        p.EPSILON = 0;
        assertFalse(LIC.LIC9());

        p.C_PTS = 0;
        p.D_PTS = 0;
        assertThrows(AssertionError.class, () -> LIC.LIC9());
    }

    /**
     * Exists atleast on set of three consecutive datapoints separated by E_PTS and
     * F_PTS respectively that are the vertices of a triangle with area greater than
     * AREA1. 
     */
    @Test
    void LIC10_test(){
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 5;
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 2);
        points[3] = new Point(0, 0);
        points[4] = new Point(2, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.AREA1 = 1.95;
        p.E_PTS = 1;
        p.F_PTS = 1;
        assertTrue(LIC.LIC10());
        /**
         * points[0], [2], [4] forms a triangle with area 2.0, which is larger than AREA1(1.95).
         * Test should be true.
         */

        points[0] = new Point(-2, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, -2);
        points[3] = new Point(0, 0);
        points[4] = new Point(0, 0);
        p.AREA1 = 2;
        p.E_PTS = 1;
        p.F_PTS = 1;
        assertFalse(LIC.LIC10());
        /**
         * points[0], [2], [4] forms a triangle with area 2, which is the same as AREA1(2.0).
         * Test should be false.
         */
        p.AREA1 = -1;
        p.E_PTS = 0;
        p.F_PTS = 0;
        assertThrows(AssertionError.class, () -> LIC.LIC10());
        // AREA1 > 0. E_PTS, F_PTS >= 1.
    }

    /**
     * There exists at least one set of two data points, (X[i],Y[i]) and (X[j],Y[j]), 
     * separated by exactly G PTS consecutive intervening points, 
     * such that X[j] - X[i] < 0. (where i < j).
     */
    @Test
    void LIC11_test(){
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];

        points[0] = new Point(1, 1);
        points[1] = new Point(1, 2);
        points[2] = new Point(0, 0);
        Parameters p = new Parameters();
        p.G_PTS = 1;
        LIC.initialize(numPoints, points, p);
        assertTrue(LIC.LIC1());

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 2);
        points[2] = new Point(2, 3);
        p.G_PTS = 1;
        LIC.initialize(numPoints, points, p);
        assertFalse(LIC.LIC11());

        p.G_PTS = 0;
        assertThrows(AssertionError.class, () -> LIC.LIC11());

    }

    /**
     * There exists at least one set of two data points, separated by exactly K PTS 
     * points, which are a distance greater than the length, LENGTH1, apart. 
     * In addition, there exists at least one set of two data points (which can be the 
     * same or different from the previous two), separated by exactly K PTS intervening
     * points, that are a distance less than the length, LENGTH2, apart.
     */
    @Test
    void LIC12_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 5;
        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 1);
        points[3] = new Point(2, 0);
        points[4] = new Point(4, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.K_PTS = 3;
        p.LENGTH1 = 3;
        p.LENGTH2 = 5;
        assertTrue(LIC.LIC12());
        p.K_PTS = 2;
        assertFalse(LIC.LIC12());
        p.K_PTS = 3;
        p.LENGTH2 = 4;
        assertFalse(LIC.LIC12());
        p.LENGTH2 = 5;
        p.LENGTH1 = 4;
        assertFalse(LIC.LIC12());
        p.K_PTS = 6;
        assertThrows(AssertionError.class, () -> LIC.LIC12());
        p.K_PTS = 2;
        p.LENGTH1 = -1.0;
        assertThrows(AssertionError.class, () -> LIC.LIC12());
        p.LENGTH1 = 1.0;
        p.LENGTH2 = -1.0;
        assertThrows(AssertionError.class, () -> LIC.LIC12());
        LIC.numPoints = 2;
        assertFalse(LIC.LIC12());
        LIC.numPoints = 5;
        p.K_PTS = 1;
        p.LENGTH1 = 3.0;
        p.LENGTH2 = 1.0;
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(0, 4);
        points[3] = new Point(0, 3.5);
        points[4] = new Point(0, 4.5);
        assertTrue(LIC.LIC12());
        p.K_PTS = 2;
        assertFalse(LIC.LIC12());
        p.LENGTH2 = 3.5;
        points[4] = new Point(4.4, 0);
        assertTrue(LIC.LIC12());
    }

    /**
     * There exists at least one set of three data points, separated by exactly A PTS 
     * and B PTS consecutive intervening points, respectively, that cannot be contained 
     * within or on a circle of radius RADIUS1. In addition, there exists at least one 
     * set of three data points (which can be the same as the previous three) separated 
     * by exactly A PTS and B PTS consecutive intervening points, respectively, that 
     * can be contained in or on a circle of radius RADIUS2.
     */
    @Test
    void LIC13_test(){
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 5;
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        points[3] = new Point(0, -1);
        points[4] = new Point(0, 1);
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);

        p.A_PTS = 0;
        p.B_PTS = 0;

        p.RADIUS1 = 0.9;
        p.RADIUS2 = 1;
        assertTrue(LIC.LIC13());

        p.RADIUS1 = 1.1;
        p.RADIUS2 = 0.9;
        assertFalse(LIC.LIC13());

        p.RADIUS1 = 1.0;
        p.RADIUS2 = 1.0;
        assertFalse(LIC.LIC13());

        p.RADIUS1 = -1;
        p.RADIUS2 = -1;
        assertThrows(AssertionError.class, () -> LIC.LIC13());

    }

    /**
     * Exists atleast one set of three data points, separated E_PTS, F_PTS 
     * respectively, that are the vertices of a triangle with area greater than AREA1. 
     * In addition, there exist three data points (which can be the same or different 
     * from the three data points just mentioned) separated by exactly E PTS and F PTS 
     * that are the vertices of a triangle with area less than AREA2. 
     * Both parts must be true for the LIC to be true.
     */
    @Test
    void LIC14_test(){
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 5;
        Point[] points = new Point[5];
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(4, 0);
        points[3] = new Point(0, 0);
        points[4] = new Point(0, 5);
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.AREA1 = 5;
        p.AREA2 = 100;
        p.E_PTS = 1;
        p.F_PTS = 1;
        assertTrue(LIC.LIC14());
        /**
         * points[0], [2], [4] forms a triangle with area = 10, which is larger than
         * AREA1(5) but smaller than AREA2(100). Test should be true.
         */

        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(4, 0);
        points[3] = new Point(0, 0);
        points[4] = new Point(0, 500);
        p.AREA1 = 5;
        p.AREA2 = 100;
        p.E_PTS = 1;
        p.F_PTS = 1;
        assertFalse(LIC.LIC14());
        /**
         * points[0], [2], [4] forms a triangle with area = 1000, which is larger than
         * AREA1(5) and larger than AREA2(100). Test should be false.
         */
        p.AREA1 = 0;
        p.AREA2 = -1;
        p.E_PTS = 0;
        p.F_PTS = 0;
        assertThrows(AssertionError.class, () -> LIC.LIC14());
        //AREA2 >= 0
    }

}
