package softcomputing.NN.losses;

public class CrossEntropyLoss implements LossFunction {

    @Override
    public double compute(double[][] yTrue, double[][] yPred) {
        return 0;
    }

    @Override
    public double[][] derivative(double[][] yTrue, double[][] yPred) {
        return null;
    }
}
