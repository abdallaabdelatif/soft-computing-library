package softcomputing.fuzzy.rules;

import java.util.ArrayList;
import java.util.List;

public class RuleBase {
    private List<FuzzyRule> rules = new ArrayList<>();

    
    public void addRule(FuzzyRule rule) {
        rules.add(rule);
    }

    public List<FuzzyRule> getRules() {
        return rules;
    }

    // RuleBase Editor API
    public void enableRule(FuzzyRule rule) {
        rule.setEnabled(true);
    }

    public void disableRule(FuzzyRule rule) {
        rule.setEnabled(false);
    }

    public void setRuleWeight(FuzzyRule rule, double weight) {
        rule.setWeight(weight);
    }

    public void editRule(FuzzyRule oldRule, FuzzyRule newRule) {
        rules.remove(oldRule);
        rules.add(newRule);
    }

    public void removeRule(FuzzyRule rule) {
        rules.remove(rule);
    }
}
