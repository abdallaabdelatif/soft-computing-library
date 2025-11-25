package softcomputing.fuzzy.rules;

public class FuzzyRule {
    private RuleAntecedent antecedent;
    private RuleConsequent consequent;
    private boolean enabled = true;
    private double weight = 1.0;

    public FuzzyRule(RuleAntecedent antecedent, RuleConsequent consequent) {
        this.antecedent = antecedent;
        this.consequent = consequent;
    }

    public RuleAntecedent getAntecedent() {
        return antecedent;
    }

    public RuleConsequent getConsequent() {
        return consequent;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double w) {
        this.weight = w;
    }
}
