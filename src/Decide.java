public class Decide {
    private LaunchInterceptorConditions lics;
    private PreliminaryUnlockingMatrixCalculator pumCalculator;
    private FinalUnlockingVectorCalculator fuvCalculator;
    private LaunchCalculator launchCalculator;

    public Decide(LaunchInterceptorConditions lics, PreliminaryUnlockingMatrixCalculator pumCalculator,
            FinalUnlockingVectorCalculator fuvCalculator, LaunchCalculator launchCalculator) {
        this.lics = lics;
        this.pumCalculator = pumCalculator;
        this.fuvCalculator = fuvCalculator;
        this.launchCalculator = launchCalculator;
    }

    public interface LaunchInterceptorConditions {
        public void initialize(int numPoints, Point[] points, Parameters parameters);

        public boolean LIC0();

        public boolean LIC1();

        public boolean LIC2();

        public boolean LIC3();

        public boolean LIC4();

        public boolean LIC5();

        public boolean LIC6();

        public boolean LIC7();

        public boolean LIC8();

        public boolean LIC9();

        public boolean LIC10();

        public boolean LIC11();

        public boolean LIC12();

        public boolean LIC13();

        public boolean LIC14();
    }

    private boolean[] calculateConditionsMetVector() {
        return new boolean[] {
                lics.LIC0(),
                lics.LIC1(),
                lics.LIC2(),
                lics.LIC3(),
                lics.LIC4(),
                lics.LIC5(),
                lics.LIC6(),
                lics.LIC7(),
                lics.LIC8(),
                lics.LIC9(),
                lics.LIC10(),
                lics.LIC11(),
                lics.LIC12(),
                lics.LIC13(),
                lics.LIC14(),
        };
    }

    public interface PreliminaryUnlockingMatrixCalculator {
        public boolean[][] calculate(LogicalConnectorOperator[][] lcm, boolean[] cmv);
    }

    public interface FinalUnlockingVectorCalculator {
        public boolean[] calculate(boolean[][] pum, boolean[] puv);
    }

    public interface LaunchCalculator {
        public boolean calculate(boolean[] fuv);
    }

    public class Output {
        boolean launch;
        boolean[] cmv;
        boolean[][] pum;
        boolean[] fuv;

        public Output(boolean launch, boolean[] cmv, boolean[][] pum, boolean[] fuv) {
            this.launch = launch;
            this.cmv = cmv;
            this.pum = pum;
            this.fuv = fuv;
        }
    }

    public Output decide(int numPoints, Point[] points, Parameters parameters, LogicalConnectorOperator[][] lcm,
            boolean[] puv) {
        lics.initialize(numPoints, points, parameters);

        boolean[] cmv = calculateConditionsMetVector();
        boolean[][] pum = pumCalculator.calculate(lcm, cmv);
        boolean[] fuv = fuvCalculator.calculate(pum, puv);
        boolean launch = launchCalculator.calculate(fuv);

        return new Output(launch, cmv, pum, fuv);
    }
}
