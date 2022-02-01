import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import decide.FinalUnlockingVectorCalculator;

public class FinalUnlockingVectorCalculatorTests {

    void assertExistFalse(boolean[] list) {
        boolean flag = true;
        for (int i = 0; i < list.length; i++) {
            flag = flag && list[i];
        }

        assertFalse(flag);
    }

    @Test
    public void testWhenPUV_I_isFalseReturnFUVIsTrue() {
        FinalUnlockingVectorCalculator fuvc = new FinalUnlockingVectorCalculator();

        boolean[] puv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            puv[i] = false;
        }

        Random rand = new Random();

        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                pum[i][j] = rand.nextBoolean();
            }
        }

        boolean[] fuv = fuvc.calculate(pum, puv);
        for (int i = 0; i < 15; i++) {
            assertTrue(fuv[i], String.format("%s", fuv[i]));
        }
    }

    @Test
    public void testWhenPUV_I_isTrueReturnFUVBasedOnPUM() {
        FinalUnlockingVectorCalculator fuvc = new FinalUnlockingVectorCalculator();

        boolean[] puv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            puv[i] = true;
        }

        Random rand = new Random();

        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                pum[i][j] = rand.nextBoolean();
            }
        }

        boolean[] fuv = fuvc.calculate(pum, puv);
        for (int i = 0; i < 15; i++) {
            if (fuv[i]) {
                for (int j = 0; j < 15; j++) {
                    if (i == j) {
                        continue;
                    }

                    assertTrue(pum[i][j]);
                }
            } else {
                pum[i][i] = true; // this shall not affect the result of FUV
                assertExistFalse(pum[i]);
            }
        }

        // set a random row to all true (except the i == j cell), and assert FUV[i] ==
        // True
        int i = rand.nextInt(15);
        for (int j = 0; j < 15; j++) {
            pum[i][j] = true;
        }
        pum[i][i] = false;
        fuv = fuvc.calculate(pum, puv);
        assertTrue(fuv[i]);
    }
}
