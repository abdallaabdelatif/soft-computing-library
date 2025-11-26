package softcomputing.fuzzy.pipeline;

import java.util.Map;
import softcomputing.fuzzy.operators.MaxOr;
import softcomputing.fuzzy.operators.OrOperator;

public class Aggregator {

    public double[] aggregateMamdani(Map<String, double[]> ruleOutputCurves) {

    OrOperator orOperator = new MaxOr();

    if (ruleOutputCurves == null || ruleOutputCurves.isEmpty()) {
        System.out.println("Warning: No rule output curves found for aggregation. Returning default output.");
        return new double[100]; 
    }

    int n = ruleOutputCurves.values().iterator().next().length;
    double[] finalAggregation = new double[n];

    for (double[] curve : ruleOutputCurves.values()) {
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
