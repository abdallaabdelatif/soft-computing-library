package softcomputing.NN.layers;

import java.util.ArrayList;
import java.util.List;
import softcomputing.NN.losses.LossFunction;

public class NeuralNetwork {

    private final List<Layer> layers;
    private LossFunction loss;
    private double learningRate;
    private java.util.List<Double> lossHistory = new java.util.ArrayList<>();

    public NeuralNetwork() {
        layers = new ArrayList<>();
        learningRate = 0.01;
    }

    public void addLayer(Layer layer) {
        layers.add(layer);
    }

    public void setLoss(LossFunction loss) {
        this.loss = loss;
    }

    private final List<Double> lossHistory = new ArrayList<>();

    public List<Double> getLossHistory() {
        return lossHistory;
    }

    public void setLearningRate(double lr) {
        this.learningRate = lr;
    }

    public void fit(double[][] X, double[][] y, int epochs, int batchSize) {

    int n = X.length;

    for (int epoch = 0; epoch < epochs; epoch++) {

        // Shuffle indices
        int[] indices = new int[n];
        for (int i = 0; i < n; i++) indices[i] = i;

        java.util.Random rand = new java.util.Random();
        for (int i = n - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        // Loop over batches
        for (int start = 0; start < n; start += batchSize) {

            int end = Math.min(start + batchSize, n);
            int size = end - start;

            // Create batch arrays
            double[][] batchX = new double[size][X[0].length];
            double[][] batchY = new double[size][y[0].length];

            for (int i = 0; i < size; i++) {
                batchX[i] = X[indices[start + i]];
                batchY[i] = y[indices[start + i]];
            }

            // Forward pass
            double[][] output = batchX;
            for (Layer layer : layers) {
                output = layer.forward(output);
            }

            // Compute loss + gradient
            double[][] grad = loss.derivative(batchY, output);

            // Backward pass
            for (int i = layers.size() - 1; i >= 0; i--) {
                grad = layers.get(i).backward(grad, learningRate);
            }
        }

        double[][] fullOutput = predict(X);
        double lossValue = loss.compute(y, fullOutput);

        lossHistory.add(lossValue);

        if (epoch % 10 == 0) {
            System.out.println("Epoch " + epoch + " Loss = " + lossValue);
        }
    }
}

    public double[][] predict(double[][] X) {
        double[][] output = X;
        for (Layer layer : layers) {
            output = layer.forward(output);
        }
        return output;
    }
    
    public java.util.List<Double> getLossHistory() {
        return lossHistory;
    }

}
