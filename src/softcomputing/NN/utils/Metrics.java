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

}
