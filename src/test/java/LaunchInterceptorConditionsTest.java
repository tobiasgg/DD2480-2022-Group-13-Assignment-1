import org.junit.jupiter.api.Test;

import decide.*;

import static org.junit.jupiter.api.Assertions.*;

public class LaunchInterceptorConditionsTest {

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
    }

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
    }

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

    @Test
    void LIC3_test(){
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0, 1);
        points[1] = new Point(3, 0);
        points[2] = new Point(0, 6);
        LIC.numPoints = numPoints;
        LIC.points = points;
        Parameters p = new Parameters();
        LIC.initialize(numPoints, points, p);
        p.AREA1 = 4.0;
        assertTrue(LIC.LIC3());

        points[0] = new Point(0, 1);
        points[1] = new Point(0, 1);
        points[2] = new Point(0, 1);
        LIC.numPoints = numPoints;
        LIC.points = points;
        p.AREA1 = 5.0;
        assertFalse(LIC.LIC3());
        p.AREA1 = -1;
        assertThrows(AssertionError.class, () -> LIC.LIC3());

    }

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

    @Test
    void LIC5_test() {

    }

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

    @Test
    void LIC8_test() {

    }

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

    @Test
    void LIC10_test(){

    }

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

}
