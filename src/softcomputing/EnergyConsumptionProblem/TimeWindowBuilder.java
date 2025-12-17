package softcomputing.EnergyConsumptionProblem;

public class TimeWindowBuilder {
    public static double[][] buildX(double[][] data, int windowSize) {
        int samples = data.length - windowSize;
        int features = data[0].length;
        double[][] X = new double[samples][windowSize * features];
        for (int i = 0; i < samples; i++) {
            int idx = 0;
            for (int t = 0; t < windowSize; t++) {
                for (int f = 0; f < features; f++) {
                    X[i][idx++] = data[i + t][f];
                }
            }
        }
        return X;
    }
    public static double[][] buildY(double[][] data, int windowSize) {
        int samples = data.length - windowSize;
        double[][] y = new double[samples][1];
        for (int i = 0; i < samples; i++) {
            y[i][0] = data[i + windowSize][0]; // Global_active_power
        }
        return y;
    }
}
