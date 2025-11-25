package softcomputing.fuzzy.variable;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinguisticVariable {
    private String name;
    private double rangeStart;
    private double rangeEnd;

    private Map<String, FuzzySet> fuzzySets = new LinkedHashMap<>();

    public LinguisticVariable(String name, double rangeStart, double rangeEnd) {
        this.name = name;
        this.rangeStart = rangeStart;
        this.rangeEnd = rangeEnd;
    }

    public void addFuzzySet(FuzzySet set) {
        fuzzySets.put(set.getName(), set);
    }

    public FuzzySet getFuzzySet(String name) {
        return fuzzySets.get(name);
    }

    public Map<String, FuzzySet> getFuzzySets() {
        return fuzzySets;
    }
}
