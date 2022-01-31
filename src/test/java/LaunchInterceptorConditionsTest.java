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
}
