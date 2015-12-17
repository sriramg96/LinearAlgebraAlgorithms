/**
 * Created by sriramg96 on 3/20/2015.
 */
public class Matrix {

    protected final int M;             // number of rows
    protected final int N;             // number of columns
    protected double[][] data;   // M-by-N array

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                this.data[j][i] = data[j][i];
            }
        }
    }

    // copy constructor
    private Matrix(Matrix A) { this(A.data); }

    // create and return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    public void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix add(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - Bd
    public Matrix subtract(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean equals(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    public boolean equals(Object B) {
        if (B instanceof Matrix) {
            return this.equals((Matrix) B);
        }
        return false;
    }

    // return C = A * B
    public Matrix multiply(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (B.data[i][k] * A.data[k][j]);
        return C;
    }

    public CustomVector downmultiply(CustomVector b) {
        if (N != b.size) {
            throw new RuntimeException("Wrong dimensions");
        }
        CustomVector ret = new CustomVector(M);
        for (int i = 0; i < M; i++) {
            double sum = 0;
            for (int j = 0; j < N; j++) {
                sum += data[j][i] * b.data[j];
            }
            ret.data[i] = sum;
        }
        return ret;
    }

    public Matrix multiply(double c) {
        Matrix ret = new Matrix(M, N);
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                ret.data[i][j] = data[i][j] * c;
            }
        }
        return ret;
    }

    public Matrix diagonal(Matrix A) {
        Matrix ret = new Matrix(A.M, A.N);
        for (int i = 0; i < Math.min(A.M, A.N); i++) {
            ret.data[i][i] = A.data[i][i];
        }
        return ret;
    }

    public Matrix lower(Matrix A) {
        Matrix ret = new Matrix(A.M, A.N);
        for (int i = 0; i < A.M; i++) {
            for (int j = i + 1; j < A.N; j++) {
                ret.data[i][j] = A.data[i][j];
            }
        }
        return ret;
    }
    public Matrix upper(Matrix A) {
        Matrix ret = new Matrix(A.M, A.N);
        for (int j = 0; j < A.N; j++) {
            for (int i = j + 1; i < A.M; i++) {
                ret.data[i][j] = A.data[i][j];
            }
        }
        return ret;
    }

    public String toString() {
        String ret = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ret += data[j][i] + " ";
            }
            ret += "\n";
        }
        ret += "\n";
        return ret;
    }

    public double norm() {
        boolean chk = false;
        double max = 0;
        for (int i = 0; i < M; i++) {
            double sum = 0;
            for (int j = 0; j < N; j++) {
                sum += Math.abs(data[j][i]);
            }
            if (!chk) {
                chk = true;
                max = sum;
            } else if (sum > max) {
                max = sum;
            }
        }
        return max;
    }
}
