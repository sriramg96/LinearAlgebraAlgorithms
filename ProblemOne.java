/**
 * Created by sriramg96 on 3/28/2015.
 */
import java.io.*;
public class ProblemOne {

    public static double rowreduce(Matrix A, int row1, int row2) {
        int col = row1;
        if (A.data[col][row2] == 0) {
            return 0;
        }
        double k = -(A.data[col][row2] / A.data[col][row1]);
        for (int i = 0; i < A.N; i++) {
            A.data[i][row2] += k * A.data[i][row1];
        }
        return k;
    }

    public static CustomVector solverb(Matrix A, CustomVector b) {
        if (A.M != b.size) {
            throw new RuntimeException("Cannot solve");
        }
        //Matrix m = new Matrix(A.M, A.N + 1);
        CustomVector ans = new CustomVector(b.size);
        for (int i = A.M - 1; i >= 0; i--) {
            ans.data[i] = b.data[i] / A.data[i][i];
            for (int j = 1; i - j >= 0; j++) {
                double k = -A.data[i][i-j] / A.data[i][i];
                A.data[i][i-j] += k * A.data[i][i];
                b.data[i-j] += k * b.data[i];
            }
        }
        return ans;
    }

    public static CustomVector solveqb(Matrix A, CustomVector b) {
        if (A.M != b.size) {
            throw new RuntimeException("Cannot solve");
        }
        //Matrix m = new Matrix(A.M, A.N + 1);
        CustomVector ans = new CustomVector(b.size);
        for (int i = 0; i < A.M; i++) {
            ans.data[i] = b.data[i] / A.data[i][i];
            for (int j = 1; i + j < A.M; j++) {
                double k = -A.data[i][i+j] / A.data[i][i];
                A.data[i][i+j] += k * A.data[i][i];
                b.data[i+j] += k * b.data[i];
            }
        }
        return ans;
    }

    public static Matrix[] lu_fact(Matrix A) {
        Matrix U = new Matrix(A.data);
        Matrix L = Matrix.identity(A.M);
        for (int i = 0; i < A.N; i++) {
            for (int k = i + 1; k < A.M; k++) {
                L.data[i][k] = -rowreduce(U, i, k);
            }
        }
        for (int i = 0; i < L.M; i++) {
            for (int j = 0; j < L.M; j++) {
                if (L.data[i][j] < Math.pow(10, -(A.M + 4))) {
                   L.data[i][j] = 0;
                }
                if (U.data[i][j] < Math.pow(10, -(A.M + 4))) {
                    U.data[i][j] = 0;
                }
            }
        }
        Matrix[] ret = {L, U};
        return ret;
    }

    public static Matrix hilbert(int n) {
        Matrix ret = new Matrix(n, n);
        for (int i = 0; i < ret.M; i++) {
            for (int j = 0; j < ret.N; j++) {
                ret.data[i][j] = 1.0 / (1.0+i+j);
            }
        }
        return ret;
    }

    public static Matrix[] qr_fact_househ(Matrix A) {
        Matrix R = new Matrix(A.data);
        Matrix Q = Matrix.identity(A.M);
        for (int i = 0; i < A.N - 1; i++) {
            Matrix k = new Matrix(A.M - i, A.M - i);
            for (int m = 0; m < k.M; m++) {
                for (int n = 0; n < k.N; n++) {
                    k.data[m][n] = A.data[m+i][n+i];
                }
            }
            CustomVector a = new CustomVector(k.M);
            for (int j = 0; j < A.M - i; j++) {
                a.data[j] = R.data[i][j+i];
            }
            CustomVector e = new CustomVector(k.M);
            e.data[0] = 1;
            CustomVector u = a.add(e.multiply(a.norm()));
            u = u.multiply(1.0 / u.norm());
            Matrix sub = u.square();
            sub = Matrix.identity(sub.M).subtract(sub.multiply(2));
            Matrix H = Matrix.identity(A.M);
            for (int z = i; z < A.M; z++) {
                for (int zz = i; zz < A.M; zz++) {
                    H.data[z][zz] = sub.data[z-i][zz-i];
                }
            }
            R = H.multiply(R);
            Q = Q.multiply(H);
            //Q = Q.multiply(-1);
            //R = R.multiply(-1);
        }
        for (int i = 0; i < Q.M; i++) {
            for (int j = 0; j < Q.M; j++) {
                if (Math.abs(Q.data[i][j]) < Math.pow(10, -(A.M + 4))) {
                    Q.data[i][j] = 0;
                }
                if (Math.abs(R.data[i][j]) < Math.pow(10, -(A.M + 4))) {
                    R.data[i][j] = 0;
                }
            }
        }
        Matrix[] ret = {Q, R};
        return ret;
    }

    public static Matrix[] qr_fact_givens(Matrix A) {
        double cos = 0;
        double sin = 0;
        Matrix Q = Matrix.identity(A.M);
        Matrix R = new Matrix(A.data);
        for (int i = 0; i < A.N; i++) {
            for (int j = i + 1; j < A.M; j++) {
                double x = R.data[i][i];
                double y = R.data[i][j];
                if (y == 0) {
                    continue;
                }
                cos = x / Math.sqrt((x*x) + (y*y));
                sin = (-y) / Math.sqrt((x*x) + (y*y));
                Matrix G = new Matrix(A.M, A.N);
                G.data[i][i] = cos;
                G.data[j][j] = cos;
                G.data[i][j] = sin;
                G.data[j][i] = -sin;
                for (int k = 0; k < G.M; k++) {
                    if (k != i && k != j) {
                        G.data[k][k] = 1;
                    }
                }
                R = G.multiply(R);
                Q = Q.multiply(G.transpose());
            }
        }
        for (int i = 0; i < Q.M; i++) {
            for (int j = 0; j < Q.M; j++) {
                if (Math.abs(Q.data[i][j]) < Math.pow(10, -(A.M + 4))) {
                    Q.data[i][j] = 0;
                }
                if (Math.abs(R.data[i][j]) < Math.pow(10, -(A.M + 4))) {
                    R.data[i][j] = 0;
                }
            }
        }
        Matrix[] ret = {Q, R};
        return ret;
    }

    public static CustomVector solve_lu_b(Matrix A, CustomVector b) {
        System.out.println("A is ");
        System.out.println(A);
        Matrix[] ans = lu_fact(A);
        System.out.println("L is ");
        System.out.println(ans[0]);
        System.out.println("U is ");
        System.out.println(ans[1]);
        CustomVector y = solveqb(ans[0], b); // Ly =b, Ux = y
        return solverb(ans[1], y);
    }

    public static CustomVector solve_qr_b(Matrix A, CustomVector b  ) {
        Matrix[] ans = qr_fact_givens(A);
        Matrix transpose = ans[0].transpose();
        CustomVector k = transpose.downmultiply(b); // Rx = QT*b
        return solverb(ans[1], k);
    }

    public static void main(String[] args) {
        Matrix matrix = hilbert(4);
        //Matrix[] ret = qr_fact_househ(matrix);
        CustomVector b = new CustomVector(4);
        for (int i = 0; i < 4; i++) {
            b.data[i] = 0.0464159;
        }
        Matrix[] ret = lu_fact(matrix);
        System.out.println("ret[0] is ");
        System.out.println(ret[0]);
        System.out.println(solve_lu_b(matrix, b));
        System.out.println(solve_qr_b(matrix, b));
        System.out.println();
        Matrix lol = ret[0].multiply(ret[1]);
        lol = lol.subtract(matrix);
        System.out.println(lol.norm());
    }
}
