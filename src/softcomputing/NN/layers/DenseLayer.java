package softcomputing.NN.layers;

import softcomputing.NN.activation.ActivationFunction;
import softcomputing.NN.intialization.WeightIntializer;

public class DenseLayer implements Layer {

    private double[][] weights;
    private double[] bias;
    private double[][] inputCache;

    private ActivationFunction activation;
    private WeightIntializer initializer;

    public DenseLayer(int inputSize, int outputSize,
                      ActivationFunction activation,
                      WeightIntializer initializer) {
        this.activation = activation;
        this.initializer = initializer;
    }

    @Override
    public double[][] forward(double[][] input) {
        return null;
    }

    @Override
    public double[][] backward(double[][] gradOutput) {
        return null;
    }
}
