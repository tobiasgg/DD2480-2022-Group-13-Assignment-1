package decide;

public class PreliminaryUnlockingMatrixCalculator implements Decide.PreliminaryUnlockingMatrixCalculator {

    public boolean[][] calculate(LogicalConnectorOperator[][] lcm, boolean[] cmv) {
        boolean[][] pum = new boolean[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                LogicalConnectorOperator lco = lcm[i][j];
                switch (lco) {
                    case ANDD:
                        // If LCM[i,j] is ANDD, PUM[i,j] should be set to true only if (CMV[i] AND
                        // CMV[j]) is true.
                        pum[i][j] = cmv[i] && cmv[j];
                        break;
                    case ORR:
                        // If LCM[i,j] is ORR, PUM[i,j] should be set to true if (CMV[i] OR CMV[j]) is
                        // true
                        pum[i][j] = cmv[i] || cmv[j];
                        break;
                    case NOTUSED:
                        // If LCM[i,j] is NOTUSED, then PUM[i,j] should be set to true.
                        pum[i][j] = true;
                        break;
                }
            }
        }

        return pum;
    }

}
