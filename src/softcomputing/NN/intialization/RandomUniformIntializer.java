package softcomputing.NN.intialization;

import java.util.Random;

public class RandomUniformIntializer implements WeightIntializer {

    @Override
    public double[][] initialize(int rows, int cols) {
        Random rand = new Random();
        double[][] W = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                W[i][j] = (rand.nextDouble() * 0.02) - 0.01; // [-0.01, 0.01]
            }
        }
        return W;
    }
}
