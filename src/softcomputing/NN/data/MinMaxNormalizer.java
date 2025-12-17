package softcomputing.NN.data;

import java.util.Arrays;

public class MinMaxNormalizer {

    public double[][] normalize(double[][] data) {
    int rows = data.length;
    int cols = data[0].length;

    double[] min = new double[cols];
    double[] max = new double[cols];

    Arrays.fill(min, Double.POSITIVE_INFINITY);
    Arrays.fill(max, Double.NEGATIVE_INFINITY);

    // Compute min/max
    for (double[] row : data) {
        for (int j = 0; j < cols; j++) {
            min[j] = Math.min(min[j], row[j]);
            max[j] = Math.max(max[j], row[j]);
        }
    }

    // Normalize
    double[][] norm = new double[rows][cols];
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < cols; j++) {
            norm[i][j] = (data[i][j] - min[j]) / (max[j] - min[j] + 1e-12);
        }
    }

    return norm;
}

}
