package softcomputing.fuzzy.inference;

import softcomputing.fuzzy.rules.RuleBase;

import java.util.Map;

public interface InferenceEngine {
    Map<String, double[]> infer(Map<String, Double> fuzzifiedInputs, RuleBase ruleBase);
}
