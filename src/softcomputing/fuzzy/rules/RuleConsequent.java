package softcomputing.fuzzy.rules;

public class RuleConsequent {
    private String outputVariable;
    private String outputFuzzySet;

    public RuleConsequent(String outputVariable, String outputFuzzySet) {
        this.outputVariable = outputVariable;
        this.outputFuzzySet = outputFuzzySet;
    }

    public String getOutputVariable() {
        return outputVariable;
    }

    public String getOutputFuzzySet() {
        return outputFuzzySet;
    }
}
