package Decide;

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
    private boolean[] calculateConditionsMetVector() {
        return new boolean[] {
                lics.LIC0(lics.parameters.LENGTH1),
                lics.LIC1(lics.parameters.RADIUS1),
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
