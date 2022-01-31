import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import Decide.*;

public class LaunchInterceptorConditionsTest {

    @Test
    void LIC0_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        points[1] = new Point(2, 0);
        points[2] = new Point(1, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        assertTrue(LIC.LIC0(1.9));
        assertFalse(LIC.LIC0(2.0));
        points[0] = new Point(-1, 0);
        points[1] = new Point(-2, 0);
        points[2] = new Point(0, 0);
        assertTrue(LIC.LIC0(1.9));
        assertFalse(LIC.LIC0(2.0));
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        assertFalse(LIC.LIC0(0.0));
    }

    @Test
    void LIC1_test() {
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        assertTrue(LIC.LIC1(0.95));
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        assertFalse(LIC.LIC1(1.0));
    }
}
