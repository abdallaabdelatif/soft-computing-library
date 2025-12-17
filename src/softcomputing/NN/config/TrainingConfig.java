package softcomputing.NN.config;

public class TrainingConfig {
    public int epochs = 200;
    public double learningRate = 0.01;

    public TrainingConfig() {}

    public TrainingConfig(int epochs, double learningRate) {
        this.epochs = epochs;
        this.learningRate = learningRate;
    }
}
