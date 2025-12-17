package softcomputing.NN.losses;

public class CrossEntropyLoss implements LossFunction {

    @Override
    public double compute(double[][] yTrue, double[][] yPred) {
        double eps = 1e-12;
        double sum = 0;
        int n = yTrue.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < yTrue[0].length; j++) {
                sum += -yTrue[i][j] * Math.log(yPred[i][j] + eps);
            }
        }
        return sum / n;
    }

    @Override
    public double[][] derivative(double[][] yTrue, double[][] yPred) {
        double eps = 1e-12;
        int rows = yTrue.length;
        int cols = yTrue[0].length;

        double[][] grad = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grad[i][j] = -(yTrue[i][j] / (yPred[i][j] + eps));
            }
        }
        return grad;
    }
}
