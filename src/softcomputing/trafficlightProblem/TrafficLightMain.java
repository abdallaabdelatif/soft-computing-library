package softcomputing.trafficlightProblem;

import softcomputing.fuzzy.defuzzification.CentroidDefuzzifier;
import softcomputing.fuzzy.inference.MamdaniEngine;
import softcomputing.fuzzy.membership.TrapezoidalMF;
import softcomputing.fuzzy.membership.TriangularMF;
import softcomputing.fuzzy.pipeline.Aggregator;
import softcomputing.fuzzy.pipeline.Fuzzifier;
import softcomputing.fuzzy.pipeline.FuzzySystem;
import softcomputing.fuzzy.rules.FuzzyRule;
import softcomputing.fuzzy.rules.RuleAntecedent;
import softcomputing.fuzzy.rules.RuleBase;
import softcomputing.fuzzy.rules.RuleConsequent;
import softcomputing.fuzzy.variable.FuzzySet;
import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrafficLightMain {
    public static void main(String[] args) {

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
        ruleBase.addRule(new FuzzyRule(new RuleAntecedent(cond1), new RuleConsequent("GreenDuration","Long")));


        Map<String,String> cond2 = new LinkedHashMap<>();
        cond2.put("TrafficDensity","Medium");
        cond2.put("WaitingTime","Short");
        ruleBase.addRule(new FuzzyRule(new RuleAntecedent(cond2), new RuleConsequent("GreenDuration","Medium")));


        Map<String,String> cond3 = new LinkedHashMap<>();
        cond3.put("TrafficDensity","Low");
        cond3.put("WaitingTime","Short");
        ruleBase.addRule(new FuzzyRule(new RuleAntecedent(cond3), new RuleConsequent("GreenDuration","Short")));

        Fuzzifier fuzzifier = new Fuzzifier();
        MamdaniEngine engine = new MamdaniEngine();
        Aggregator aggregator = new Aggregator();
        CentroidDefuzzifier defuzzifier = new CentroidDefuzzifier();

        FuzzySystem system = new FuzzySystem(fuzzifier, engine, aggregator, defuzzifier, ruleBase);
        system.addInputVariable(density);
        system.addInputVariable(waiting);
        system.setOutputVariable(green);

        Map<String, Double> input1 = new LinkedHashMap<>();
        input1.put("TrafficDensity", 80.0);
        input1.put("WaitingTime", 150.0);

        double out1 = system.evaluate(input1);
        System.out.println("High density, Long wait -> GreenDuration = " + out1 + " sec");
        System.out.println("-----------------------------------------------------------------");

        Map<String, Double> input2 = new LinkedHashMap<>();
        input2.put("TrafficDensity", 30.0);
        input2.put("WaitingTime", 40.0);
        double out2 = system.evaluate(input2);
        System.out.println("Low density, Short wait -> GreenDuration = " + out2 + " sec");
        System.out.println("-----------------------------------------------------------------");

        double out3 = system.evaluate(50.0, 40.0);
        System.out.println("Density 50, Waiting 40 -> GreenDuration = " + out3 + " sec");
        System.out.println("-----------------------------------------------------------------");

    }
}

