package softcomputing.NN.intialization;

import java.util.Random;

public class XavierIntializer implements WeightIntializer {

    @Override
    public double[][] initialize(int rows, int cols) {
        Random rand = new Random();
        double limit = Math.sqrt(6.0 / (rows + cols));

        double[][] W = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                W[i][j] = rand.nextDouble() * 2 * limit - limit;
            }
        }
        return W;
    }
}
