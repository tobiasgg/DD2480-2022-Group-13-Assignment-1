import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.lang.reflect.Field;


import decide.*;
import org.junit.jupiter.api.Test;

public class DecideTests {
    @Test
    public void testDefaultUseImplementedClasses()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Decide d = Decide.DEFAULT();

        Field lics = d.getClass().getDeclaredField("lics");
        lics.setAccessible(true);
        assertTrue(lics.get(d) instanceof LaunchInterceptorConditions);

        Field pumCalculator = d.getClass().getDeclaredField("pumCalculator");
        pumCalculator.setAccessible(true);
        assertTrue(pumCalculator.get(d) instanceof PreliminaryUnlockingMatrixCalculator);

        Field fuvCalculator = d.getClass().getDeclaredField("fuvCalculator");
        fuvCalculator.setAccessible(true);
        assertTrue(fuvCalculator.get(d) instanceof FinalUnlockingVectorCalculator);

        Field launchCalculator = d.getClass().getDeclaredField("launchCalculator");
        launchCalculator.setAccessible(true);
        assertTrue(launchCalculator.get(d) instanceof LaunchCalculator);
    }

    @Test
    public void inputPositiveTest() throws Exception {
        Decide d = Decide.DEFAULT();
        int numPoints = 5;
        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        points[3] = new Point(0, 1);
        points[4] = new Point(0, -1);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 0.9;
        parameters.LENGTH2 = 3.0;
        parameters.RADIUS1 = 0.9;
        parameters.RADIUS2 = 0.1;
        parameters.EPSILON = 0.1;
        parameters.AREA1 = 0.45;
        parameters.AREA2 = 0.1;
        parameters.QUADS = 2;
        parameters.DIST = 0.9;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.G_PTS = 1;
        parameters.K_PTS = 1;
        parameters.N_PTS = 5;
        parameters.Q_PTS = 5;
        LogicalConnectorOperator[][] lcm = new LogicalConnectorOperator[15][15];
        // LCM 0: NOTUSED, 1: ANDD, 2: ORR
        int[][] lcm_values = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        };
        // Loop over the two-dimensional int array and generate the logical
        // connectors
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (lcm_values[i][j] == 0) {
                    lcm[i][j] = LogicalConnectorOperator.NOTUSED;
                } else if (lcm_values[i][j] == 1) {
                    lcm[i][j] = LogicalConnectorOperator.ANDD;
                } else {
                    lcm[i][j] = LogicalConnectorOperator.ORR;
                }
            }
        }
        boolean[] puv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            puv[i] = true;
        }
        assertTrue(d.decide(numPoints, points, parameters, lcm, puv).launch);
    }

    @Test
    public void inputNegativeTest() throws Exception {
        Decide d = Decide.DEFAULT();
        int numPoints = 5;
        Point[] points = new Point[numPoints];
        points[0] = new Point(0, 0);
        points[1] = new Point(1, 0);
        points[2] = new Point(-1, 0);
        points[3] = new Point(0, 1);
        points[4] = new Point(0, -1);
        Parameters parameters = new Parameters();
        parameters.LENGTH1 = 1.0;
        parameters.LENGTH2 = 0.0;
        parameters.RADIUS1 = 1.0;
        parameters.RADIUS2 = 0.1;
        parameters.EPSILON = 2.0;
        parameters.AREA1 = 0.45;
        parameters.AREA2 = 0.1;
        parameters.QUADS = 2;
        parameters.DIST = 0.9;
        parameters.A_PTS = 1;
        parameters.B_PTS = 1;
        parameters.C_PTS = 1;
        parameters.D_PTS = 1;
        parameters.E_PTS = 1;
        parameters.F_PTS = 1;
        parameters.G_PTS = 1;
        parameters.K_PTS = 1;
        parameters.N_PTS = 5;
        parameters.Q_PTS = 5;
        LogicalConnectorOperator[][] lcm = new LogicalConnectorOperator[15][15];
        // LCM 0: NOTUSED, 1: ANDD, 2: ORR
        int[][] lcm_values = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        };
        // Loop over the two-dimensional int array and generate the logical
        // connectors
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (lcm_values[i][j] == 0) {
                    lcm[i][j] = LogicalConnectorOperator.NOTUSED;
                } else if (lcm_values[i][j] == 1) {
                    lcm[i][j] = LogicalConnectorOperator.ANDD;
                } else {
                    lcm[i][j] = LogicalConnectorOperator.ORR;
                }
            }
        }
        boolean[] puv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            puv[i] = true;
        }
        assertFalse(d.decide(numPoints, points, parameters, lcm, puv).launch);
    }

    @Test
    public void testNumPointsNotMatchPointsLength() {
        Decide d = Decide.DEFAULT();
        Parameters parameters = new Parameters();
        int numPoints = 5;
        Point[] points = new Point[numPoints - 1];
        LogicalConnectorOperator[][] lcm = new LogicalConnectorOperator[15][15];
        boolean[] puv = new boolean[15];
        try {
            d.decide(numPoints, points, parameters, lcm, puv);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "numPoint != points.length");
            return;
        }

        assertFalse(true, "no exception thrown when numPoint != points.length");
    }

}
