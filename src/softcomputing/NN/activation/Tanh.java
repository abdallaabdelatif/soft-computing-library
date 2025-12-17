package softcomputing.NN.activation;

public class Tanh implements ActivationFunction {

    @Override
    public double activate(double x) {
        return Math.tanh(x);
    }

    @Override
    public double derivative(double x) {
        double t = Math.tanh(x);
        return 1 - (t * t);
    }
}
