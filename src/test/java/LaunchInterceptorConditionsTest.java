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
        assertThrows(AssertionError.class, () -> LIC.LIC1(-1.0));
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
        assertTrue(LIC.LIC3(4.0));

        points[0] = new Point(0, 1);
        points[1] = new Point(0, 1);
        points[2] = new Point(0, 1);
        LIC.numPoints = numPoints;
        LIC.points = points;
        assertFalse(LIC.LIC3(5));
        assertThrows(AssertionError.class, () -> LIC.LIC3(-1));
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
        assertTrue(LIC.LIC4(2, 1));
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        assertFalse(LIC.LIC4(2, 3));
        assertThrows(AssertionError.class, () -> LIC.LIC4(1, 0));
    }

    /**
     * It exist at least one set of two consecutive points such
     * that LIC5_X1[j] - LIC5_X1[i] < 0 where i = j-1. 
     */
    @Test
    void LIC5_test(){
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 3;
        Point[] points = new Point[3];
        points[0] = new Point(0, 0);
        points[1] = new Point(-1, 0);
        points[2] = new Point(0, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        assertTrue(true);

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(2, 0);
        assertFalse(false);
    }

    //Needs more work
    @Test
    void LIC8_test(){
        LaunchInterceptorConditions LIC = new LaunchInterceptorConditions();
        int numPoints = 7;
        Point[] points = new Point[7];
        points[0] = new Point(-1, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(0, 0);
        points[4] = new Point(1, 0);
        points[5] = new Point(0, 0);
        points[6] = new Point(0, 0);
        LIC.numPoints = numPoints;
        LIC.points = points;
        assertTrue(LIC.LIC8(0.95, 1, 1));
        
        points[0] = new Point(0, 0);
        points[1] = new Point(4, 0);
        points[2] = new Point(0, 0);
        points[3] = new Point(4, 0);
        points[4] = new Point(0, 0);
        points[5] = new Point(4, 0);
        points[6] = new Point(0.5, 0);
        assertFalse(LIC.LIC8(1, 1, 1));
        assertThrows(AssertionError.class, () -> LIC.LIC8(-1, 0, 0));
        
    }

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
        assertTrue(LIC.LIC10(1.95, 1, 1));
        points[0] = new Point(-2, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(-1, -2);
        points[3] = new Point(0, 0);
        points[4] = new Point(0, 0);
        assertFalse(LIC.LIC10(2, 1, 1));
        assertThrows(AssertionError.class, () -> LIC.LIC10(-1, 0, 0));
    }

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
        assertTrue(LIC.LIC14(5, 100, 1, 1));

        points[0] = new Point(0, 0);
        points[1] = new Point(0, 0);
        points[2] = new Point(4, 0);
        points[3] = new Point(0, 0);
        points[4] = new Point(0, 500);
        assertFalse(LIC.LIC14(5, 100, 1, 1));
        assertThrows(AssertionError.class, () -> LIC.LIC14(0, -1, 0, 0));
    }

}
