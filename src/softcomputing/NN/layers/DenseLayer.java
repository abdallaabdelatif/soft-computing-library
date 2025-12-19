package softcomputing.NN.layers;

import softcomputing.NN.activation.ActivationFunction;
import softcomputing.NN.intialization.WeightIntializer;
import softcomputing.NN.utils.Matrix;

public class DenseLayer implements Layer {

    private double[][] weights;
    private double[] bias;

    private double[][] inputCache;
    private double[][] zCache;

    private final ActivationFunction activation;

    public DenseLayer(int inputSize, int outputSize,
                      ActivationFunction activation,
                      WeightIntializer initializer) {

        this.weights = initializer.initialize(inputSize, outputSize);
        this.bias = new double[outputSize];
        this.activation = activation;
    }

    @Override
    public double[][] forward(double[][] input) {
        inputCache = input;

        double[][] z = Matrix.add(Matrix.multiply(input, weights), bias);
        zCache = z;

        double[][] output = new double[z.length][z[0].length];
        for (int i = 0; i < z.length; i++) {
            for (int j = 0; j < z[0].length; j++) {
                output[i][j] = activation.activate(z[i][j]);
            }
        }
        return output;
    }

    @Override
    public double[][] backward(double[][] gradOutput, double learningRate) {
        double[][] gradZ = new double[zCache.length][zCache[0].length];
        for (int i = 0; i < zCache.length; i++) {
            for (int j = 0; j < zCache[0].length; j++) {
                gradZ[i][j] = gradOutput[i][j] * activation.derivative(zCache[i][j]);
            }
        }

        double[][] gradW = Matrix.multiply(Matrix.transpose(inputCache), gradZ);
        double[] gradB = Matrix.sumRows(gradZ);

        // store old weights before update
        double[][] oldWeights = weights;

        // update
        weights = Matrix.subtract(weights, Matrix.scalarMultiply(gradW, learningRate));
        bias = Matrix.subtract(bias, Matrix.scalarMultiply(gradB, learningRate));

        // gradInput uses old weights
        return Matrix.multiply(gradZ, Matrix.transpose(oldWeights));
   }


    public double[][] getLastZ() {
        return zCache;
    }

    public double[][] getLastInput() {
        return inputCache;
   }
}
