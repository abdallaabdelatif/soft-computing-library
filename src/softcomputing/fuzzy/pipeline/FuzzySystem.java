package softcomputing.fuzzy.pipeline;

import softcomputing.fuzzy.defuzzification.Defuzzifier;
import softcomputing.fuzzy.inference.InferenceEngine;
import softcomputing.fuzzy.rules.RuleBase;
import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.Map;

public class FuzzySystem {
    private Map<String, LinguisticVariable> inputVariables;
    private LinguisticVariable outputVariable;

    private InferenceEngine inferenceEngine;
    private Defuzzifier defuzzifier;

    private RuleBase ruleBase;

    public double evaluate(Map<String, Double> crispInputs) {
        return 0;
    }
}
