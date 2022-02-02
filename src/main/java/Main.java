import decide.*;
import decide.Decide.Output;

public class Main {
    public static void main(String[] args) {
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

        try {
            Output result = d.decide(numPoints, points, parameters, lcm, puv);

            if (result.launch) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
