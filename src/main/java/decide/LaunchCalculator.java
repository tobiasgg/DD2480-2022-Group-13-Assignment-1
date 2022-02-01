package decide;

public class LaunchCalculator implements Decide.LaunchCalculator {

    public boolean calculate(boolean[] fuv) {
        for (int i = 0; i < 15; i++) {
            if (!fuv[i]) {
                return false;
            }
        }

        return true;
    }

}
