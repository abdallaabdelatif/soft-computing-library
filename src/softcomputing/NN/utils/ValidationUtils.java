package softcomputing.NN.utils;

public class ValidationUtils {

    public static boolean checkDimensions(double[][] a, double[][] b) {
    return a.length == b.length && a[0].length == b[0].length;
  }
}
