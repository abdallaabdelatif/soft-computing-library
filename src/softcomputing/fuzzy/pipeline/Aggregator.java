package softcomputing.fuzzy.pipeline;

import softcomputing.fuzzy.operators.MaxOr;
import softcomputing.fuzzy.operators.OrOperator;
import softcomputing.fuzzy.rules.RuleBase;

import java.util.Map;

public class Aggregator {

    public double[] aggregateMamdani(RuleBase ruleBase, Map<String, double[]> fuzzyOutputs) {

        OrOperator orOperator = new MaxOr();

        if (fuzzyOutputs == null || fuzzyOutputs.isEmpty()) {
            return new double[0];
        }

        int n = fuzzyOutputs.values().iterator().next().length;
        double[] finalAggregation = new double[n];

        for (double[] curve : fuzzyOutputs.values()) {
            for (int i = 0; i < n; i++) {
                finalAggregation[i] = orOperator.apply(finalAggregation[i], curve[i]);

            }
        }

        return finalAggregation;
    }

    public double[] aggregateSugeno() {
        return null;
    }
}
