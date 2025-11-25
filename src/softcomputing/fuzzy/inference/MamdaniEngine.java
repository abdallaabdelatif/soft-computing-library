package softcomputing.fuzzy.inference;

import softcomputing.fuzzy.rules.RuleBase;
import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.Map;

public class MamdaniEngine implements InferenceEngine {
    @Override
    public Map<String, double[]> infer(
            Map<String, Map<String, Double>> fuzzifiedInputs,
            RuleBase ruleBase,
            LinguisticVariable outputVar)
    {
        return null;
    }
}
