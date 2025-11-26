package softcomputing.trafficlightProblem;

import softcomputing.fuzzy.defuzzification.*;
import softcomputing.fuzzy.inference.*;
import softcomputing.fuzzy.membership.TrapezoidalMF;
import softcomputing.fuzzy.membership.TriangularMF;
import softcomputing.fuzzy.pipeline.Aggregator;
import softcomputing.fuzzy.pipeline.Fuzzifier;
import softcomputing.fuzzy.pipeline.FuzzySystem;
import softcomputing.fuzzy.rules.*;
import softcomputing.fuzzy.variable.FuzzySet;
import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.*;

public class TrafficLightMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        LinguisticVariable density = new LinguisticVariable("TrafficDensity", 0, 100);
        density.addFuzzySet(new FuzzySet("Low", new TriangularMF(0, 0, 30)));
        density.addFuzzySet(new FuzzySet("Medium", new TrapezoidalMF(20, 35, 65, 80)));
        density.addFuzzySet(new FuzzySet("High", new TriangularMF(60, 100, 100)));

        LinguisticVariable waiting = new LinguisticVariable("WaitingTime", 0, 180);
        waiting.addFuzzySet(new FuzzySet("Short", new TriangularMF(0, 0, 60)));
        waiting.addFuzzySet(new FuzzySet("Medium", new TrapezoidalMF(50, 70, 110, 130)));
        waiting.addFuzzySet(new FuzzySet("Long", new TriangularMF(110, 180, 180)));

        LinguisticVariable green = new LinguisticVariable("GreenDuration", 0, 120);
        green.addFuzzySet(new FuzzySet("Short", new TriangularMF(0, 0, 40)));
        green.addFuzzySet(new FuzzySet("Medium", new TrapezoidalMF(30, 45, 65, 80)));
        green.addFuzzySet(new FuzzySet("Long", new TriangularMF(70, 120, 120)));


        RuleBase ruleBase = new RuleBase();

        Map<String,String> cond1 = new LinkedHashMap<>();
        cond1.put("TrafficDensity","High");
        cond1.put("WaitingTime","Long");
        ruleBase.addRule(new FuzzyRule(new RuleAntecedent(cond1),
                new RuleConsequent("GreenDuration","Long")));

        Map<String,String> cond2 = new LinkedHashMap<>();
        cond2.put("TrafficDensity","Medium");
        cond2.put("WaitingTime","Short");
        ruleBase.addRule(new FuzzyRule(new RuleAntecedent(cond2),
                new RuleConsequent("GreenDuration","Medium")));

        Map<String,String> cond3 = new LinkedHashMap<>();
        cond3.put("TrafficDensity","Low");
        cond3.put("WaitingTime","Short");
        ruleBase.addRule(new FuzzyRule(new RuleAntecedent(cond3),
                new RuleConsequent("GreenDuration","Short")));


        Fuzzifier fuzzifier = new Fuzzifier();
        Aggregator aggregator = new Aggregator();

        // DEFAULTS
        InferenceEngine engine = new MamdaniEngine();
        Defuzzifier defuzzifier = new CentroidDefuzzifier();

        FuzzySystem system = new FuzzySystem(
                fuzzifier, engine, aggregator, defuzzifier, ruleBase
        );

        system.addInputVariable(density);
        system.addInputVariable(waiting);
        system.setOutputVariable(green);


        System.out.println("===== Traffic Light Fuzzy System =====");

        while (true) {

            System.out.print("\nEnter Traffic Density (0–100): ");
            double d = scanner.nextDouble();

            System.out.print("Enter Waiting Time (0–180): ");
            double w = scanner.nextDouble();

            System.out.println("\nChoose Inference Engine:");
            System.out.println("1) Mamdani (default)");
            System.out.println("2) Sugeno");
            System.out.print("Your choice: ");
            int engineChoice = scanner.nextInt();

            String engineName;

            if (engineChoice == 2) {
                system.setInferenceEngine(new SugenoEngine());
                engineName = "Sugeno";
            } else {
                system.setInferenceEngine(new MamdaniEngine());
                engineName = "Mamdani";
            }

            String defuzzName;

            if (engineName.equals("Sugeno")) {
                System.out.println("Sugeno uses weighted average — no defuzzifier required.");
                defuzzName = "Weighted Average (Sugeno)";
            } else {
                System.out.println("\nChoose Defuzzification Method:");
                System.out.println("1) Centroid (default)");
                System.out.println("2) Mean of Maximum");
                System.out.print("Your choice: ");
                int defuzzChoice = scanner.nextInt();

                if (defuzzChoice == 2) {
                    system.setDefuzzifier(new MeanOfMaximumDefuzzifier());
                    defuzzName = "Mean of Maximum";
                } else {
                    system.setDefuzzifier(new CentroidDefuzzifier());
                    defuzzName = "Centroid";
                }
            }

            Map<String, Double> input = new LinkedHashMap<>();
            input.put("TrafficDensity", d);
            input.put("WaitingTime", w);

            double output = system.evaluate(input);

            System.out.println("\n==================== RESULT ====================");
            System.out.println("Inputs:");
            System.out.println("  Traffic Density = " + d);
            System.out.println("  Waiting Time    = " + w);
            System.out.println("Inference Engine  = " + engineName);
            System.out.println("Defuzzification   = " + defuzzName);
            System.out.println("Output:");
            System.out.println("  Green Duration  = " + output + " seconds");
            System.out.println("=================================================");

            // Continue?
            System.out.print("\nEvaluate again? (y/n): ");
            String again = scanner.next().trim().toLowerCase();
            if (!again.equals("y")) break;
        }

        System.out.println("\nSystem terminated.");
    }
}
