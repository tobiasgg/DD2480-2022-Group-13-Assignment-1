import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import decide.LogicalConnectorOperator;
import decide.PreliminaryUnlockingMatrixCalculator;

public class PreliminaryUnlockingMatrixCalculatorTests {

    @Test
    public void testANDDConditions() {
        PreliminaryUnlockingMatrixCalculator pumc = new PreliminaryUnlockingMatrixCalculator();

        Random rand = new Random();

        boolean[] cmv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            cmv[i] = rand.nextBoolean();
        }

        LogicalConnectorOperator[][] lcm = new LogicalConnectorOperator[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = LogicalConnectorOperator.ANDD;
            }
        }

        boolean[][] pum = pumc.calculate(lcm, cmv);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (cmv[i] && cmv[j]) {
                    assertTrue(pum[i][j], String.format("%s %s", cmv[i], cmv[j]));
                } else {
                    assertFalse(pum[i][j], String.format("%s %s", cmv[i], cmv[j]));
                }
            }
        }
    }

    @Test
    public void testORRConditions() {
        PreliminaryUnlockingMatrixCalculator pumc = new PreliminaryUnlockingMatrixCalculator();

        Random rand = new Random();

        boolean[] cmv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            cmv[i] = rand.nextBoolean();
        }

        LogicalConnectorOperator[][] lcm = new LogicalConnectorOperator[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = LogicalConnectorOperator.ORR;
            }
        }

        boolean[][] pum = pumc.calculate(lcm, cmv);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (cmv[i] || cmv[j]) {
                    assertTrue(pum[i][j], String.format("%s %s", cmv[i], cmv[j]));
                } else {
                    assertFalse(pum[i][j], String.format("%s %s", cmv[i], cmv[j]));
                }
            }
        }
    }

    @Test
    public void testNOTUSEDConditions() {
        PreliminaryUnlockingMatrixCalculator pumc = new PreliminaryUnlockingMatrixCalculator();

        Random rand = new Random();

        boolean[] cmv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            cmv[i] = rand.nextBoolean();
        }

        LogicalConnectorOperator[][] lcm = new LogicalConnectorOperator[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                lcm[i][j] = LogicalConnectorOperator.NOTUSED;
            }
        }

        boolean[][] pum = pumc.calculate(lcm, cmv);
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                assertTrue(pum[i][j], String.format("%s %s", cmv[i], cmv[j]));
            }
        }
    }

}
