/**
 * Created by sriramg96 on 3/24/2015.
 */
public class ProblemTwo {

    protected static CustomVector x;
    protected static CustomVector y0;
    protected static CustomVector y1;
    private static final int NUMBER_OF_ITERATION = 50;

    public static void calculate_y0() {
        y0 = new CustomVector(x.size);
        for (int i = 0; i < x.size; i++) {
            if (i >= 3) {
                y0.data[i] = x.data[i] + x.data[i-2] + x.data[i-3];
            } else if (i >= 2) {
                y0.data[i] = x.data[i] + x.data[i-2];
            } else {
                y0.data[i] = x.data[i];
            }
            y0.data[i]%=2;
        }
    }

    public static void calculate_y1() {
        y1 = new CustomVector(x.size);
        for (int i = 0; i < x.size; i++) {
            if (i >= 3) {
                y1.data[i] = x.data[i] + x.data[i-1] + x.data[i-3];
            } else if (i >= 1) {
                y1.data[i] = x.data[i] + x.data[i-1];
            } else {
                y1.data[i] = x.data[i];
            }
            y1.data[i]%=2;
        }
    }

    public static Matrix formmatrix(CustomVector x, CustomVector y) {
        Matrix ret = new Matrix(y.size, x.size);
        for (int i = 0; i < y.size; i++) {
            if (y.data[i] == 0) {
                for (int j = 0; j < x.size; j++) {
                    if (x.data[j] == 0) {
                        ret.data[i][j] = (int) (Math.random() * 10) + 1;
                    }
                }
            } else {
                boolean chk = true;
                for (int j = 0; j < x.size; j++) {
                    if (x.data[j] == 0) {
                        ret.data[i][j] = (int) (Math.random() * 10) + 1;
                    } else {
                        if (chk) {
                            ret.data[i][j] = 1;
                            chk = !chk;
                        }
                    }
                }
            }
        }
        return ret;
    }

    public static CustomVector jacobi(Matrix A, CustomVector y, CustomVector x, double error) {
        Matrix L = A.lower(A);
        Matrix U = A.upper(A);
        Matrix D = A.diagonal(A);
        Matrix sum = L.add(U);
        sum = sum.multiply(-1);
        CustomVector c = sum.downmultiply(x);
        c.add(y);
        CustomVector xx = new CustomVector(c.size);
        for (int i = 0; i < c.size; i++) {
            xx.data[i] = c.data[i] / D.data[i][i];
        }
        int count = 1;
        while (xx.subtract(x).norm() > error && count < NUMBER_OF_ITERATION) {
            x = xx;
            c = sum.downmultiply(x);
            c.add(y);
            for (int i = 0; i < c.size; i++) {
                xx.data[i] = c.data[i] / D.data[i][i];
            }
            count++;
        }
        return xx;
    }

    public static void main(String[] args) {
        double[] data = {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0};
        double[] dat = new double[data.length + 3];
        for (int i = 0; i < data.length; i++) {
            dat[i] = data[i];
        }
        x = new CustomVector(dat);
        calculate_y0();
        calculate_y1();
        System.out.println(y0);
        System.out.println(y1);
        System.out.println();
        Matrix a0 = formmatrix(x, y0);
        Matrix a1 = formmatrix(x, y1);
        for (int i = 0; i < a0.M; i++) {
            for (int j = 0; j < a0.N; j++) {
                System.out.print(a0.data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(a0.M + " " + a0.N);
        System.out.println();
        for (int i = 0; i < a1.M; i++) {
            for (int j = 0; j < a1.N; j++) {
                System.out.print(a1.data[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(a1.M + " " + a1.N);
        System.out.println();
        System.out.println(a0.downmultiply(x));
        System.out.println(a1.downmultiply(x));
        CustomVector guess = new CustomVector(a0.M);
        for (int i = 0; i < guess.size; i++) {
            guess.data[i] = (int) (Math.random() * 2);
        }
        CustomVector jacob1 = jacobi(a0, y0, guess, 0.01);
        CustomVector jacob2 = jacobi(a1, y1, guess, 0.01);
        System.out.println();
        System.out.println(jacob1);
        System.out.println();
        System.out.println(jacob2);
    }
}
