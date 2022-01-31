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
        assertTrue(LIC.LIC2(Math.PI/2));
        assertFalse(LIC.LIC2(3*Math.PI/4));
        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(1, 0);
        assertFalse(LIC.LIC2(0.1));
        points[0] = new Point(1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        assertFalse(LIC.LIC2(0.1));
        assertThrows(AssertionError.class, () -> LIC.LIC2(-1.0));
        assertThrows(AssertionError.class, () -> LIC.LIC2(4.0));
    }
}
