/**
 * Created by sriramg96 on 3/20/2015.
 */
public class CustomVector {

    protected final int size;
    protected double[] data;

    public CustomVector(int size) {
        data = new double[size];
        this.size = size;
    }

    public CustomVector(double[] data) {
        size = data.length;
        this.data = new double[size];
        for (int i = 0; i < size; i++) {
            this.data[i] = data[i];
        }
    }

    public CustomVector add(CustomVector B) {
        if (this.size != B.size) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        CustomVector C = new CustomVector(size);
        for (int i = 0; i < size; i++) {
            C.data[i] = data[i] + B.data[i];
        }
        return C;
    }

    public CustomVector subtract(CustomVector B) {
        if (this.size != B.size) {
            throw new RuntimeException("Illegal matrix dimensions.");
        }
        CustomVector C = new CustomVector(size);
        for (int i = 0; i < size; i++) {
            C.data[i] = data[i] - B.data[i];
        }
        return C;
    }

    public double dotproduct(CustomVector B) {
        if (this.size != B.size) {
            throw new RuntimeException("Illegal Vector dimensions");
        }
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += data[i] * B.data[i];
        }
        return sum;
    }

    public CustomVector multiply(double c) {
        CustomVector ret = new CustomVector(size);
        for (int i = 0; i < size; i++) {
            ret.data[i] = data[i] * c;
        }
        return ret;
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < size; i++) {
            s += data[i] + " ";
        }
        return s;
    }

    public double norm() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += data[i] * data[i];
        }
        sum = Math.sqrt(sum);
        return sum;
    }

    public Matrix square() {
        Matrix ret = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ret.data[i][j] = data[i] * data[j];
            }
        }
        return ret;
    }
}
