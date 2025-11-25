package softcomputing.fuzzy.rules;

import java.util.Map;

public class RuleAntecedent {
    // variableName -> fuzzySetName
    private Map<String, String> conditions;

    public RuleAntecedent(Map<String, String> conditions) {
        this.conditions = conditions;
    }

    public Map<String, String> getConditions() {
        return conditions;
    }
}
