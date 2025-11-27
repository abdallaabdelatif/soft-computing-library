package softcomputing.trafficlightProblem;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

public class TrafficLightMain {

    public static void main(String[] args) throws IOException {

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
        File txtFile = new File("rules.txt");

        if (txtFile.exists()) {
            ruleBase = RuleBaseTXT.loadFromTxt("rules.txt");
        } else {
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

            RuleBaseTXT.saveToTxt(ruleBase, "rules.txt");
        }


        Fuzzifier fuzzifier = new Fuzzifier();
        Aggregator aggregator = new Aggregator();
        InferenceEngine engine = new MamdaniEngine();
        Defuzzifier defuzzifier = new CentroidDefuzzifier();

        FuzzySystem system = new FuzzySystem(
                fuzzifier, engine, aggregator, defuzzifier, ruleBase
        );

        system.addInputVariable(density);
        system.addInputVariable(waiting);
        system.setOutputVariable(green);

        boolean running = true;


        while (running) {

            System.out.println("\n=== Traffic Light Fuzzy System ===");
            System.out.println("1) Evaluate system");
            System.out.println("2) View rules");
            System.out.println("3) Add new rule");
            System.out.println("4) Edit rule (enable / disable / weight)");
            System.out.println("5) Delete rule");
            System.out.println("6) Exit");
            System.out.print("Choice: ");

            int choice = scanner.nextInt();

            switch (choice) {


                case 1:
                    System.out.print("Enter Traffic Density (0–100): ");
                    double d = scanner.nextDouble();

                    System.out.print("Enter Waiting Time (0–180): ");
                    double w = scanner.nextDouble();

                    System.out.println("Choose Inference Engine:");
                    System.out.println("1) Mamdani (default)");
                    System.out.println("2) Sugeno");
                    int engineChoice = scanner.nextInt();

                    if (engineChoice == 2)
                        system.setInferenceEngine(new SugenoEngine());
                    else
                        system.setInferenceEngine(new MamdaniEngine());

                    if (system.getInferenceEngine() instanceof SugenoEngine) {
                        system.setDefuzzifier(null);
                    } else {
                        System.out.println("Choose Defuzzification Method:");
                        System.out.println("1) Centroid (default)");
                        System.out.println("2) Mean of Maximum");
                        int defuzzChoice = scanner.nextInt();

                        if (defuzzChoice == 2)
                            system.setDefuzzifier(new MeanOfMaximumDefuzzifier());
                        else
                            system.setDefuzzifier(new CentroidDefuzzifier());
                    }

                    Map<String, Double> input = new LinkedHashMap<>();
                    input.put("TrafficDensity", d);
                    input.put("WaitingTime", w);

                    double output = system.evaluate(input);
                    System.out.println("Green Duration = " + output + " seconds");
                    break;


                case 2:
                    int idx = 1;
                    for (FuzzyRule r : ruleBase.getRules()) {
                        System.out.println(idx + ") " +
                                r.getAntecedent().getConditions() +
                                " -> " + r.getConsequent().getOutputVariable() +
                                "=" + r.getConsequent().getOutputFuzzySet() +
                                " | enabled=" + r.isEnabled() +
                                " | weight=" + r.getWeight());
                        idx++;
                    }
                    break;


                case 3:
                    scanner.nextLine();
                    System.out.println("=== Add New Rule ===");

                    Map<String, String> newConditions = new LinkedHashMap<>();

                    while (true) {
                        System.out.print("Enter variable (TrafficDensity / WaitingTime) or 'done': ");
                        String var = scanner.nextLine().trim();

                        if (var.equalsIgnoreCase("done")) break;

                        if (!var.equalsIgnoreCase("TrafficDensity") &&
                                !var.equalsIgnoreCase("WaitingTime")) {
                            System.out.println("❌ Invalid variable name.");
                            continue;
                        }


                        if (var.equalsIgnoreCase("TrafficDensity"))
                            System.out.print("Enter fuzzy set (Low / Medium / High): ");
                        else
                            System.out.print("Enter fuzzy set (Short / Medium / Long): ");

                        String set = scanner.nextLine().trim();

                        set = set.substring(0,1).toUpperCase() + set.substring(1).toLowerCase();

                        boolean ok = false;

                        if (var.equalsIgnoreCase("TrafficDensity") &&
                                (set.equals("Low") || set.equals("Medium") || set.equals("High")))
                            ok = true;

                        if (var.equalsIgnoreCase("WaitingTime") &&
                                (set.equals("Short") || set.equals("Medium") || set.equals("Long")))
                            ok = true;

                        if (!ok) {
                            System.out.println(" Invalid fuzzy set.");
                            continue;
                        }

                        newConditions.put(var, set);
                    }

                    if (newConditions.isEmpty()) {
                        System.out.println(" Rule must have at least one condition.");
                        break;
                    }


                    System.out.print("Enter output fuzzy set (Short / Medium / Long): ");
                    String outSet = scanner.nextLine().trim();

                    // Normalize
                    outSet = outSet.substring(0,1).toUpperCase() + outSet.substring(1).toLowerCase();

                    if (!outSet.equals("Short") &&
                            !outSet.equals("Medium") &&
                            !outSet.equals("Long")) {
                        System.out.println("Invalid output fuzzy set.");
                        break;
                    }


                    System.out.print("Enable rule? (true / false) [default = true]: ");
                    String enabledInput = scanner.nextLine().trim();
                    boolean enabled;

                    if (enabledInput.isEmpty()) {
                        enabled = true; // default
                    } else if (enabledInput.equalsIgnoreCase("true")) {
                        enabled = true;
                    } else if (enabledInput.equalsIgnoreCase("false")) {
                        enabled = false;
                    } else {
                        System.out.println("⚠ Invalid input. Defaulting to true.");
                        enabled = true;
                    }


                    System.out.print("Enter weight (0.0 - 1.0) [default = 1.0]: ");
                    String weightInput = scanner.nextLine().trim();
                    double weight;

                    if (weightInput.isEmpty()) {
                        weight = 1.0;
                    } else {
                        try {
                            weight = Double.parseDouble(weightInput);
                            if (weight < 0 || weight > 1) {
                                System.out.println("⚠ Invalid weight. Using default 1.0.");
                                weight = 1.0;
                            }
                        } catch (Exception e) {
                            System.out.println("⚠ Invalid number. Using default 1.0.");
                            weight = 1.0;
                        }
                    }


                    FuzzyRule newRule = new FuzzyRule(
                            new RuleAntecedent(newConditions),
                            new RuleConsequent("GreenDuration", outSet)
                    );
                    newRule.setEnabled(enabled);
                    newRule.setWeight(weight);

                    ruleBase.addRule(newRule);

                    RuleBaseTXT.saveToTxt(ruleBase, "rules.txt");

                    System.out.println("✅ Rule added and saved successfully!");
                    break;


                case 4:
                    System.out.print("Enter rule number to edit: ");
                    int rnum = scanner.nextInt();
                    if (rnum < 1 || rnum > ruleBase.getRules().size()) break;

                    FuzzyRule r = ruleBase.getRules().get(rnum - 1);

                    System.out.println("1) Enable rule");
                    System.out.println("2) Disable rule");
                    System.out.println("3) Set weight");
                    int action = scanner.nextInt();

                    if (action == 1) ruleBase.enableRule(r);
                    else if (action == 2) ruleBase.disableRule(r);
                    else if (action == 3) {
                        System.out.print("Enter new weight (0–1): ");
                        double wnew = scanner.nextDouble();
                        ruleBase.setRuleWeight(r, wnew);
                    }

                    RuleBaseTXT.saveToTxt(ruleBase, "rules.txt");
                    System.out.println("Rule updated and saved.");
                    break;



                case 5:
                    System.out.println("=== Delete Rule ===");

                    int i = 1;
                    for (FuzzyRule rr : ruleBase.getRules()) {
                        System.out.println(i + ") " +
                                rr.getAntecedent().getConditions() + " -> " +
                                rr.getConsequent().getOutputFuzzySet());
                        i++;
                    }

                    System.out.print("Enter rule number to delete: ");
                    int delNum = scanner.nextInt();

                    if (delNum < 1 || delNum > ruleBase.getRules().size()) {
                        System.out.println("Invalid rule number.");
                        break;
                    }

                    FuzzyRule toDelete = ruleBase.getRules().get(delNum - 1);
                    ruleBase.removeRule(toDelete);

                    RuleBaseTXT.saveToTxt(ruleBase, "rules.txt");
                    System.out.println("Rule deleted and saved.");
                    break;


                case 6:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        System.out.println("System terminated.");
    }
}
