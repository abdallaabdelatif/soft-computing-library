package softcomputing.fuzzy.defuzzification;

public class MeanOfMaximumDefuzzifier implements Defuzzifier {

    @Override
    public double defuzzify(double[] aggregatedOutput, double rangeStart, double rangeEnd) {
        if (aggregatedOutput.length == 0) {
            throw new IllegalArgumentException("Membership array cannot be empty.");
        }

        int n = aggregatedOutput.length;
        if (n == 1) {
            return rangeStart;
        }
        double rangeSpan = rangeEnd - rangeStart;
        double step = rangeSpan / (n - 1);

        double max = 0.0;
        for (double mu : aggregatedOutput) {
            if (mu > max) {
                max = mu;
            }
        }

        if (max == 0.0) {
            return 0.0;
        }

        double sumX = 0.0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (aggregatedOutput[i] == max) {
                double x = rangeStart + i * step;
                sumX += x;
                count++;
            }
        }
        return sumX / count;
    }
}
