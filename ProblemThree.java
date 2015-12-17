/**
 * Created by sriramg96 on 3/20/2015.
 */

import java.util.Scanner;

public class ProblemThree {

    public static double[] power_method(Matrix A, CustomVector b, double error) {
        //CustomVector chk = A.downmultiply(b);
        double value1 = b.data[0]; // the first eigenvalue
        CustomVector chk = A.downmultiply(b.multiply(1.0 / value1));
        //CustomVector chk = A.downmultiply(b);
        int count = 1;
        double value2 = chk.data[0]; // new approximation for largest eigenvalue
        while (Math.abs(value2 - value1) > error) {
            System.out.println(chk);
            double factor = 1.0 / value2;
            chk = A.downmultiply(chk.multiply(factor));
            //chk = A.downmultiply(chk);
            value1 = value2;
            value2 = chk.data[0];
            count++;
            if (count == 2) {
                A.data[1][0] = 0.6;
            }
        }
        double[] ret = new double[11];
        ret[0] = value2; //largest eigenvalue
        for (int i = 1; i < 10; i++) {
            ret[i] = chk.data[i-1]; //values of x
        }
        ret[10] = (double) count; //number of iterations
        return ret;
    }

    public static void main(String[] args) {
        /**Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the 9 fecundity values (f1-f9) separated by spaces: ");
        int i;
        double[] l = new double[9];
        for (i = 0; i < 9; i++) {
            l[i] = scanner.nextDouble();
        }
        System.out.println();
        System.out.print("Enter the 8 fractional survival probability rates (s1-s8) separated by spaces: ");
        double[] s = new double[8];
        for (i = 0; i < 8; i++) {
            s[i] = scanner.nextDouble();
        }
        System.out.println();
        System.out.print("Enter the 9 values of number of individuals in class age x (n1-n9) separated by spaces: ");
        double[] n = new double[9];
        for (i = 0; i < 9; i++) {
            n[i] = scanner.nextDouble();
        }
        System.out.println();
        System.out.print("Enter the maximum error threshold: ");
        double error = scanner.nextDouble();
        CustomVector k = new CustomVector(n);
        double[][] matrix = new double[9][9];
        for (i = 0; i < 9; i++) {
            matrix[i][0] = l[i];
            if (i != 8) {
                matrix[i][i + 1] = s[i];
            }
        }
         */
        double[][] matrix = new double[9][9];
        double[] kk = {0, 1.2, 1.1, 0.9, 0.1, 0, 0, 0, 0};
        matrix[0] = kk;
        matrix[1][0] = 0.7;
        matrix[2][1] = 0.85;
        matrix[3][2] = 0.9;
        matrix[4][3] = 0.9;
        matrix[5][4] = 0.88;
        matrix[6][5] = 0.8;
        matrix[7][6] = 0.77;
        matrix[8][7] = 0.4;
        double[] data = {2.1, 1.9, 1.8, 2.1, 2.0, 1.7, 1.2, 0.9, 0.5};
        double error = Math.pow(10, -8);
        CustomVector k = new CustomVector(data);

        Matrix mat = new Matrix(matrix);
        mat = mat.transpose();
        double[] answer = power_method(mat, k, error);
        System.out.println("The approximated eigenvalue is " + answer[0]);
        System.out.println();
        System.out.println("Shown below is the approximate eigenvector");
        for (int i = 1; i < 10; i++) {
            System.out.println(answer[i]);
        }
        System.out.println();
        System.out.println("The number of iterations required to achieve this answer was " + (int) answer[10]);
    }
}
