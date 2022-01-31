package decide;

public class FinalUnlockingVectorCalculator implements Decide.FinalUnlockingVectorCalculator {

    public boolean[] calculate(boolean[][] pum, boolean[] puv) {
        boolean[] fuv = new boolean[15];

        for (int i = 0; i < 15; i++) {
            if (puv[i]) {
                boolean flag = true;

                for (int j = 0; j < 15; j++) {
                    if (i == j) {
                        continue;
                    }

                    flag = flag && pum[i][j];
                }

                // if all elements in PUM row i are true
                fuv[i] = flag;
            } else {
                // FUV[i] should be set to true if PUV[i] is false
                fuv[i] = true;
            }
        }

        return fuv;
    }

}
