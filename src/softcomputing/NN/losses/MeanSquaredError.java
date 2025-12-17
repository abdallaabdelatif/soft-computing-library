package softcomputing.NN.losses;

public class MeanSquaredError implements LossFunction {

    @Override
    public double compute(double[][] yTrue, double[][] yPred) {
        double sum = 0;
        int n = yTrue.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < yTrue[0].length; j++) {
                double diff = yPred[i][j] - yTrue[i][j];
                sum += diff * diff;
            }
        }
        return sum / n;
    }

    @Override
    public double[][] derivative(double[][] yTrue, double[][] yPred) {
        int rows = yTrue.length;
        int cols = yTrue[0].length;

        double[][] grad = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grad[i][j] = 2 * (yPred[i][j] - yTrue[i][j]) / rows;
            }
        }
        return grad;
    }
}
