package softcomputing.NN.data;


public class DataSet {
    public double[][] X;
    public double[][] y;

    public DataSet(double[][] X, double[][] y) {
        this.X = X;
        this.y = y;
    }
}
