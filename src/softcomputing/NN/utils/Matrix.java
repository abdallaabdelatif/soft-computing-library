package softcomputing.NN.utils;

public class Matrix {

    // Matrix multiplication: (m x n) * (n x p) = (m x p)
    public static double[][] multiply(double[][] A, double[][] B) {
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;

        double[][] result = new double[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                double sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += A[i][k] * B[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    // Transpose matrix
    public static double[][] transpose(double[][] A) {
        int rows = A.length;
        int cols = A[0].length;

        double[][] T = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                T[j][i] = A[i][j];
            }
        }
        return T;
    }

    // Add bias vector to each row of matrix
    public static double[][] add(double[][] A, double[] b) {
        double[][] result = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                result[i][j] = A[i][j] + b[j];
            }
        }
        return result;
    }

    // Sum rows (used for bias gradient)
    public static double[] sumRows(double[][] A) {
        int cols = A[0].length;
        double[] result = new double[cols];

        for (double[] row : A) {
            for (int j = 0; j < cols; j++) {
                result[j] += row[j];
            }
        }
        return result;
    }

    // Matrix subtraction
    public static double[][] subtract(double[][] A, double[][] B) {
        double[][] result = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    // Vector subtraction
    public static double[] subtract(double[] A, double[] B) {
        double[] result = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] - B[i];
        }
        return result;
    }

    // Scalar multiply matrix
    public static double[][] scalarMultiply(double[][] A, double scalar) {
        double[][] result = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                result[i][j] = A[i][j] * scalar;
            }
        }
        return result;
    }

    // Scalar multiply vector
    public static double[] scalarMultiply(double[] A, double scalar) {
        double[] result = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            result[i] = A[i] * scalar;
        }
        return result;
    }
}
