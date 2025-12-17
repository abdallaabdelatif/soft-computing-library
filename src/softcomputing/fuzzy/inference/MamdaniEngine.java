package softcomputing.fuzzy.inference;

import softcomputing.fuzzy.membership.MembershipFunction;
import softcomputing.fuzzy.rules.FuzzyRule;
import softcomputing.fuzzy.rules.RuleAntecedent;
import softcomputing.fuzzy.rules.RuleBase;
import softcomputing.fuzzy.rules.RuleConsequent;
import softcomputing.fuzzy.variable.FuzzySet;
import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.LinkedHashMap;
import java.util.Map;

public class MamdaniEngine implements InferenceEngine {

    private static final int NUM_SAMPLES = 121;

    @Override
    public Map<String, double[]> infer(
            Map<String, Map<String, Double>> fuzzifiedInputs,
            RuleBase ruleBase,
            LinguisticVariable outputVar)
    {
        Map<String, double[]> ruleOutputs = new LinkedHashMap<>();

        if (ruleBase == null || ruleBase.getRules().isEmpty()) {
            return ruleOutputs;
        }

        double rangeStart = outputVar.getDomainMin();
        double rangeEnd = outputVar.getDomainMax();

        if (rangeEnd <= rangeStart) {
            throw new IllegalArgumentException("Output variable domain must have rangeEnd > rangeStart.");
        }

        double step = (rangeEnd - rangeStart) / (NUM_SAMPLES - 1);

        int ruleIndex = 0;
        for (FuzzyRule rule : ruleBase.getRules()) {
            if (rule == null || !rule.isEnabled()) {
                continue;
            }

            RuleAntecedent antecedent = rule.getAntecedent();
            RuleConsequent consequent = rule.getConsequent();

            if (antecedent == null || consequent == null) {
                continue;
            }

            double evaluation = ruleEvaluation(fuzzifiedInputs, antecedent);

            if (evaluation <= 0.0) {
                continue;
            }

            evaluation *= rule.getWeight();

            if (evaluation <= 0.0) {
                continue;
            }

            FuzzySet outputSet = outputVar.getFuzzySet(consequent.getOutputFuzzySet());
            if (outputSet == null) {
                continue;
            }

            MembershipFunction mf = outputSet.getMembershipFunction();


            double[] curve = new double[NUM_SAMPLES];
            for (int i = 0; i < NUM_SAMPLES; i++) {
                double x = rangeStart + i * step;
                double baseMu = mf.compute(x);
                curve[i] = Math.min(evaluation, baseMu);
            }

            String key = "rule_" + ruleIndex + "_" + consequent.getOutputFuzzySet();
            ruleOutputs.put(key, curve);
            ruleIndex++;
        }
xg
        return ruleOutputs;
    }

    private double ruleEvaluation(
            Map<String, Map<String, Double>> fuzzifiedInputs,
            RuleAntecedent antecedent) {

        Map<String, String> conditions = antecedent.getConditions();
        if (conditions == null || conditions.isEmpty()) {
            return 0.0;
        }

        double evaluation = 1.0;

        for (Map.Entry<String, String> entry : conditions.entrySet()) {
            String varName = entry.getKey();
            String setName = entry.getValue();

            Map<String, Double> membershipsForVar = fuzzifiedInputs.get(varName);
            if (membershipsForVar == null) {
                return 0.0;
            }

            Double mu = membershipsForVar.get(setName);
            if (mu == null) {
                return 0.0;
            }

            evaluation = Math.min(evaluation, mu);

            if (evaluation <= 0.0) {
                return 0.0;
            }
        }

        return evaluation;
    }
}
