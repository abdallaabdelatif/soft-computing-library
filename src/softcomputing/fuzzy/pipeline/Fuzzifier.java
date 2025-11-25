package softcomputing.fuzzy.pipeline;

import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.LinkedHashMap;
import java.util.Map;

public class Fuzzifier {

    public Map<String, Map<String, Double>> fuzzify(
            Map<String, Double> crispInputs,
            Map<String, LinguisticVariable> inputs) {

        Map<String, Map<String, Double>> result = new LinkedHashMap<>();

        for (Map.Entry<String, Double> entry : crispInputs.entrySet()) {
            String varName = entry.getKey();
            double value = entry.getValue();

            LinguisticVariable variable = inputs.get(varName);
            if (variable != null) {
                result.put(varName, variable.fuzzify(value));
            }
        }

        return result;
    }
}
