package softcomputing.NN.layers;

public interface Layer {
    double[][] forward(double[][] input);
    double[][] backward(double[][] gradOutput, double learningRate);

}
