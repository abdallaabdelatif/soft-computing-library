package softcomputing.NN.activation;

public class Linear implements ActivationFunction {

    @Override
    public double activate(double x) {
        return x;
    }

    @Override
    public double derivative(double x) {
        return 1.0;
    }
}
