package softcomputing.fuzzy.pipeline;

import softcomputing.fuzzy.defuzzification.Defuzzifier;
import softcomputing.fuzzy.inference.InferenceEngine;
import softcomputing.fuzzy.rules.RuleBase;
import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.HashMap;
import java.util.Map;

public class FuzzySystem implements IFuzzySystem {

    private final Map<String, LinguisticVariable> inputVariables = new HashMap<>();
    private LinguisticVariable outputVariable;

    private final RuleBase ruleBase;
    private final Fuzzifier fuzzifier;
    private final InferenceEngine inferenceEngine;
    private final Aggregator aggregator;
    private final Defuzzifier defuzzifier;

    public FuzzySystem(Fuzzifier fuzzifier,
                       InferenceEngine inferenceEngine,
                       Aggregator aggregator,
                       Defuzzifier defuzzifier,
                       RuleBase ruleBase) {

        this.fuzzifier = fuzzifier;
        this.inferenceEngine = inferenceEngine;
        this.aggregator = aggregator;
        this.defuzzifier = defuzzifier;
        this.ruleBase = ruleBase;
    }

    @Override
    public void addInputVariable(LinguisticVariable var) {
        inputVariables.put(var.getName(), var);
    }

    @Override
    public void setOutputVariable(LinguisticVariable var) {
        this.outputVariable = var;
    }

    @Override
    public Map<String, LinguisticVariable> getInputVariables() {
        return inputVariables;
    }

    @Override
    public LinguisticVariable getOutputVariable() {
        return outputVariable;
    }

    @Override
    public double evaluate(Map<String, Double> crispInputs) {

        Map<String, Map<String, Double>> fuzzyInputs =
                fuzzifier.fuzzify(crispInputs, inputVariables);

        Map<String, double[]> ruleOutputs =
                inferenceEngine.infer(fuzzyInputs, ruleBase, outputVariable);

        double[] aggregated =
                aggregator.aggregateMamdani(ruleBase, ruleOutputs);

        return defuzzifier.defuzzify(
                aggregated,
                outputVariable.getDomainMin(),
                outputVariable.getDomainMax()
        );
    }

    @Override
    public double evaluate(double in1, double in2) {
        String[] keys = inputVariables.keySet().toArray(new String[0]);

        Map<String, Double> map = new HashMap<>();
        map.put(keys[0], in1);
        map.put(keys[1], in2);

        return evaluate(map);
    }

    @Override
    public double evaluate(double input) {
        String key = inputVariables.keySet().iterator().next();

        Map<String, Double> map = new HashMap<>();
        map.put(key, input);

        return evaluate(map);
    }
}
