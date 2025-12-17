package softcomputing.NN.losses;

public interface LossFunction {
    double compute(double[][] yTrue, double[][] yPred);
    double[][] derivative(double[][] yTrue, double[][] yPred);
}
