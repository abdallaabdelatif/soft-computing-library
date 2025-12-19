package softcomputing.NN.config;

public class TrainingConfig {
    public int epochs = 50;
    public double learningRate = 0.01;
    public int batchSize = 32;

    public TrainingConfig() {}

    public TrainingConfig(int epochs, double learningRate, int batchSize) {
    this.epochs = epochs;
    this.learningRate = learningRate;
    this.batchSize = batchSize;
  }
}
