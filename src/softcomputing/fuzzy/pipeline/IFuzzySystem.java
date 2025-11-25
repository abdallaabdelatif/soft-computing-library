package softcomputing.fuzzy.pipeline;

import softcomputing.fuzzy.variable.LinguisticVariable;

import java.util.Map;

public interface IFuzzySystem {

    void addInputVariable(LinguisticVariable var);

    void setOutputVariable(LinguisticVariable var);

    double evaluate(Map<String, Double> crispInputs);

    double evaluate(double in1, double in2);

    double evaluate(double input);

    Map<String, LinguisticVariable> getInputVariables();

    LinguisticVariable getOutputVariable();
}
