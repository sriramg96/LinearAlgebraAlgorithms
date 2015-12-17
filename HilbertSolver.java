public class HilbertSolver {
	public static void main(String[] args) {
		for (int i = 2; i <= 2; i++) {
			Matrix hilb = ProblemOne.hilbert(i);
			double k = Math.pow(0.1, ((double) (i) / 3.0));
			CustomVector b = new CustomVector(i);
			for (int j = 0; j < i; j++) {
				b.data[j] = k;
				System.out.println(k);
			}
			CustomVector xlu = ProblemOne.solve_lu_b(hilb, b);
			CustomVector xqrh = ProblemOne.solve_qr_b(hilb, b);
			//CustomVector xqrg = ProblemOne.solve_qr_b(hilb, b);
			Matrix[] luAns, qrhAns, qrgAns;
			luAns = ProblemOne.lu_fact(hilb);
			qrhAns = ProblemOne.qr_fact_househ(hilb);
			qrgAns = ProblemOne.qr_fact_givens(hilb);
			System.out.println("The answer for x using LU factorization is:");
			System.out.println(xlu);
			System.out.println("The error in the LU factorization is:");
			System.out.println(luAns[2].data[0][0]);
			System.out.println();
			System.out.println("The answer for x using QR (householder) factorization is:");
			System.out.println(xqrh);
			System.out.println("The error in the QR (householder) factorization is:");
			System.out.println(qrhAns[2].data[0][0]);
			System.out.println();
			System.out.println("The answer for x using QR (givens) factorization is:");
			//System.out.println(xqrg);
			System.out.println("The error in the QR (givens) factorization is:");
			System.out.println(qrgAns[2].data[0][0]);
			System.out.println();
		}
	}
}