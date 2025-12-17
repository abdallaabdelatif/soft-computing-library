package softcomputing.NN.data;

import java.util.Arrays;

public class MinMaxNormalizer {

    private double[][] handleMissing(double[][] data) {
        int rows = data.length;
        int cols = data[0].length;

        double[] colSum = new double[cols];
        int[] colCount = new int[cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double val = data[i][j];
                if (!Double.isNaN(val) && !Double.isInfinite(val)) {
                    colSum[j] += val;
                    colCount[j]++;
                }
            }
        }

        double[] colMean = new double[cols];
        for (int j = 0; j < cols; j++) {
            colMean[j] = (colCount[j] > 0) ? colSum[j] / colCount[j] : 0.0;
        }

        double[][] cleaned = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double val = data[i][j];
                if (Double.isNaN(val) || Double.isInfinite(val)) {
                    cleaned[i][j] = colMean[j];
                } else {
                    cleaned[i][j] = val;
                }
            }
        }

        return cleaned;
    }

    
    public double[][] normalize(double[][] data) {
        // Handle missing values
        double[][] cleaned = handleMissing(data);

        int rows = cleaned.length;
        int cols = cleaned[0].length;

        double[] min = new double[cols];
        double[] max = new double[cols];

        Arrays.fill(min, Double.POSITIVE_INFINITY);
        Arrays.fill(max, Double.NEGATIVE_INFINITY);

        // Compute min/max
        for (double[] row : cleaned) {
            for (int j = 0; j < cols; j++) {
                min[j] = Math.min(min[j], row[j]);
                max[j] = Math.max(max[j], row[j]);
            }
        }

        // Normalize
        double[][] norm = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                norm[i][j] = (cleaned[i][j] - min[j]) / (max[j] - min[j] + 1e-12);
            }
        }

        return norm;
    }
}
