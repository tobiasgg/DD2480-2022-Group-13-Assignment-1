import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;

import org.junit.jupiter.api.Test;

import decide.LaunchCalculator;

public class LaunchCalculatorTests {

    @Test
    public void testLaunchIsTrueWhenAllFUVAreTrue() {
        boolean[] fuv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            fuv[i] = true;
        }

        LaunchCalculator lc = new LaunchCalculator();
        assertTrue(lc.calculate(fuv));
    }

    @Test
    public void testLaunchIsFalseWhenAnyFUVEntryAreFalse() {
        boolean[] fuv = new boolean[15];
        for (int i = 0; i < 15; i++) {
            fuv[i] = true;
        }

        Random rand = new Random();
        fuv[rand.nextInt(15)] = false;

        LaunchCalculator lc = new LaunchCalculator();
        assertFalse(lc.calculate(fuv));
    }

    @Test
    public void testLaunchBasedOnRandomizedFUV() {
        Random rand = new Random();

        for (int itr = 0; itr < 5; itr++) {
            boolean[] fuv = new boolean[15];
            for (int i = 0; i < 15; i++) {
                fuv[i] = rand.nextBoolean();
            }

            LaunchCalculator lc = new LaunchCalculator();
            if (lc.calculate(fuv)) {
                for (int i = 0; i < 15; i++) {
                    assertTrue(fuv[i]);
                }
            } else {
                boolean flag = true;
                for (int i = 0; i < 15; i++) {
                    flag = flag && fuv[i];
                }

                assertFalse(flag);
            }
        }

    }
}
