package softcomputing.fuzzy.defuzzification;

public interface Defuzzifier {
    double defuzzify(double[] aggregatedOutput, double rangeStart, double rangeEnd);
}
