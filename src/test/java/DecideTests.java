import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;

import decide.Decide;
import decide.FinalUnlockingVectorCalculator;
import decide.LaunchCalculator;
import decide.LaunchInterceptorConditions;
import decide.PreliminaryUnlockingMatrixCalculator;

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
}
