package softcomputing.EnergyConsumptionProblem;

import softcomputing.NN.activation.*;
import softcomputing.NN.data.*;
import softcomputing.NN.intialization.*;
import softcomputing.NN.layers.*;
import softcomputing.NN.losses.*;
import softcomputing.NN.utils.*;

public class EnergyConsumptionMain {

    public static void main(String[] args) throws Exception {

        //Load raw dataset
        double[][] rawData = CSVLoader.load("household_power_consumption.csv");

        //Normalization
        MinMaxNormalizer normalizer = new MinMaxNormalizer();
        double[][] normalized = normalizer.normalize(rawData);

        //Time-windowing (10 steps)
        int windowSize = 10;
        double[][] X = TimeWindowBuilder.buildX(normalized, windowSize);
        double[][] y = TimeWindowBuilder.buildY(normalized, windowSize);

        DataSet fullData = new DataSet(X, y);

        //Split: 70% train, 15% val, 15% test
        DataSet[] trainTest = DataSplitter.split(fullData, 0.85);
        DataSet trainVal = trainTest[0];
        DataSet test = trainTest[1];

        DataSet[] trainValSplit = DataSplitter.split(trainVal, 0.82);
        DataSet train = trainValSplit[0];
        DataSet val = trainValSplit[1];

        //Neural Network Architecture
        NeuralNetwork nn = new NeuralNetwork();
        nn.setLearningRate(0.01);
        nn.setLoss(new MeanSquaredError());

        nn.addLayer(new DenseLayer(
                X[0].length, 32,
                new ReLU(),
                new XavierIntializer()
        ));

        nn.addLayer(new DenseLayer(
                32, 16,
                new Tanh(),
                new XavierIntializer()
        ));

        nn.addLayer(new DenseLayer(
                16, 1,
                new Linear(),
                new XavierIntializer()
        ));

        //Training
        nn.fit(train.X, train.y, 50, 32);

        //Evaluation
        double trainMSE = Metrics.mse(train.y, nn.predict(train.X));
        double valMSE = Metrics.mse(val.y, nn.predict(val.X));
        double testMSE = Metrics.mse(test.y, nn.predict(test.X));
        LossLogger.save(nn.getLossHistory(), "loss_curve.csv");
        System.out.println("Loss curve saved to loss_curve.csv");
        System.out.println("Training MSE: " + trainMSE);
        System.out.println("Validation MSE: " + valMSE);
        System.out.println("Test MSE: " + testMSE);
    }
}
