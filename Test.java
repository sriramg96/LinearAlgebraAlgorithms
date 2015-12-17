/**
 * Created by sriramg96 on 3/24/2015.
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Test {

    public static void main(String[] args) throws FileNotFoundException {
        Matrix m = new Matrix(2, 2);
        m.data[0][0] = 0.8944271909999159;
        m.data[0][1] = -0.4472135954999579;
        m.data[1][0] = 0.4472135954999579;
        m.data[1][1] = 0.8944271909999159 ;
        System.out.println(m);
        Matrix n = new Matrix(2, 2);
        n.data[0][0] = 1;
        n.data[0][1] = 0.5;
        n.data[1][0] = 0.5;
        n.data[1][1] = 1.0/3;
        System.out.println(n);
        Matrix mm = new Matrix(2, 2);
        mm.data[0][0] = 1;
        mm.data[1][0] = 2;
        mm.data[0][1] = 3;
        mm.data[1][1] = 4;
        Matrix nn = new Matrix(2, 2);
        nn.data[0][0] = 5;
        nn.data[1][0] = 6;
        nn.data[0][1] = 7;
        nn.data[1][1] = 8;
        System.out.println(mm.multiply(nn));
    }
}
