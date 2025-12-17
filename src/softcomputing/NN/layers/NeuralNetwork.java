package softcomputing.NN.layers;

import java.util.ArrayList;
import java.util.List;
import softcomputing.NN.losses.LossFunction;

public class NeuralNetwork {

    private List<Layer> layers;
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

    public void setLearningRate(double lr) {
        this.learningRate = lr;
    }

    public void fit(double[][] X, double[][] y, int epochs) {

        for (int epoch = 0; epoch < epochs; epoch++) {

            double[][] output = X;
            for (Layer layer : layers) {
                output = layer.forward(output);
            }

            double lossValue = loss.compute(y, output);
            lossHistory.add(lossValue);
            double[][] grad = loss.derivative(y, output);

            for (int i = layers.size() - 1; i >= 0; i--) {
                grad = layers.get(i).backward(grad, learningRate);
            }

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
