package softcomputing.NN.data;

public class DataSplitter {

    public static DataSet[] split(DataSet data, double trainRatio) {
    int n = data.X.length;
    int trainSize = (int) (n * trainRatio);

    // Shuffle indices
    int[] indices = new int[n];
    for (int i = 0; i < n; i++) indices[i] = i;
    shuffle(indices);

    double[][] Xtrain = new double[trainSize][];
    double[][] ytrain = new double[trainSize][];
    double[][] Xtest = new double[n - trainSize][];
    double[][] ytest = new double[n - trainSize][];

    for (int i = 0; i < trainSize; i++) {
        Xtrain[i] = data.X[indices[i]];
        ytrain[i] = data.y[indices[i]];
    }
    for (int i = trainSize; i < n; i++) {
        Xtest[i - trainSize] = data.X[indices[i]];
        ytest[i - trainSize] = data.y[indices[i]];
    }

    return new DataSet[] {
        new DataSet(Xtrain, ytrain),
        new DataSet(Xtest, ytest)
    };
}

private static void shuffle(int[] array) {
    java.util.Random rand = new java.util.Random();

    for (int i = array.length - 1; i > 0; i--) {
        int j = rand.nextInt(i + 1);

        // swap array[i] and array[j]
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
}
