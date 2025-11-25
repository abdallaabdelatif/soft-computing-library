package softcomputing.fuzzy.inference;

import softcomputing.fuzzy.rules.RuleBase;
import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.Map;

public interface InferenceEngine {
    Map<String, double[]> infer(
            Map<String, Map<String, Double>> fuzzifiedInputs,
            RuleBase ruleBase,
            LinguisticVariable outputVariable
    );}
