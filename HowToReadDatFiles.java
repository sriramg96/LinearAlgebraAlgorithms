/**
 * Created by sriramg96 on 3/28/2015.
 */
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class HowToReadDatFiles {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner file = new Scanner(new File("C:/Users/sriramg96/Documents/Gatech/Homework/cs1332/Calc3 Work/src/a.dat"));
        ArrayList<String> count = new ArrayList();
        while (file.hasNext()) {
            String k = file.next();
            count.add(k);
        }
        int size = (int) Math.sqrt(count.size());
        Matrix m = new Matrix(size, size);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                m.data[i][j] = Double.parseDouble(count.remove(0));
            }
        }
        for (int i = 0; i < m.M; i++) {
            for (int j = 0; j < m.M; j++) {
                System.out.print(m.data[i][j] + " ");
            }
            System.out.println();
        }
    }
}
