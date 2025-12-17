package softcomputing.NN.layers;

import java.util.ArrayList;
import java.util.List;
import softcomputing.NN.losses.LossFunction;

public class NeuralNetwork {

    private List<Layer> layers = new ArrayList<>();
    private LossFunction loss;

    public void addLayer(Layer layer) {
    }

    public void setLoss(LossFunction loss) {
    }

    public void fit(double[][] X, double[][] y, int epochs) {
    }

    public double[][] predict(double[][] X) {
        return null;
    }
}
