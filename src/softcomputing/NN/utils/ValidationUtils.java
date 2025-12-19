package softcomputing.NN.utils;

public class ValidationUtils {

    public static boolean checkDimensions(double[][] a, double[][] b) {
       return a.length == b.length; 
    }

    public static void validateInputs(double[][] X, double[][] y) {
        if (X == null || y == null) {
            throw new IllegalArgumentException("Input or labels are null");
        }
        if (!checkDimensions(X, y)) {
            throw new IllegalArgumentException("Mismatched sample sizes");
        }
        if (X.length == 0) {
            throw new IllegalArgumentException("Empty dataset");
        }
    }
}
