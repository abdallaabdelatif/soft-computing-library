package softcomputing.NN.utils;


public class Metrics {

    public static double mse(double[][] yTrue, double[][] yPred) {
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
    public static double toleranceAccuracy(
            double[][] yTrue,
            double[][] yPred,
            double tolerance
    ) {
        int correct = 0;
        int total = yTrue.length;

        for (int i = 0; i < total; i++) {
            if (Math.abs(yPred[i][0] - yTrue[i][0]) <= tolerance) {
                correct++;
            }
        }
        return (double) correct / total;
    }


}
