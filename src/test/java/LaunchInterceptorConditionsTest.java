import org.junit.jupiter.api.Test;

import main.java.decide.*;

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
}
