package softcomputing.fuzzy.defuzzification;

public class CentroidDefuzzifier implements Defuzzifier {

    @Override
    public double defuzzify(double[] aggregatedOutput, double rangeStart, double rangeEnd) {
        if (aggregatedOutput.length == 0 || aggregatedOutput == null) {
            throw new IllegalArgumentException("Aggregated curve is empty.");
        }

        int n = aggregatedOutput.length;
        double step = (rangeEnd - rangeStart) / (n - 1);

        double numerator = 0.0;
        double denominator = 0.0;

        for (int i = 0; i < n; i++) {
            double x = rangeStart + i * step;
            double mu = aggregatedOutput[i];

            numerator += x * mu;
            denominator += mu;
        }

        if (denominator == 0.0) {
            return (rangeStart + rangeEnd) / 2;
        }

        return (numerator / denominator);
    }
}
