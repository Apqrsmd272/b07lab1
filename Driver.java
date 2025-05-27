import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
	public static void main(String [] args) {
		Polynomial p0 = new Polynomial();
		System.out.println("p0(3) = " + p0.evaluate(3));

		// test the single‐array constructor
		double[]c1={6,0,0,5};
		int[]e1={3,3,3,0}
		Polynomial p1 = new Polynomial(c1,e1);
		System.out.println("p1(1) = " + p1.evaluate(1));

		// test the file‐based constructor (make sure poly.txt exists in this folder)
		Polynomial p2 = new Polynomial(new File("poly.txt"));
		System.out.println("p2(2) = " + p2.evaluate(2));

		// test add(...)
		Polynomial sum = p1.add(p2);
		System.out.println("sum(0.1) = " + sum.evaluate(0.5));
		if (sum.hasRoot(1)) {
			System.out.println("1 is a root of sum");
		} else {
			System.out.println("1 is not a root of sum");
		}
		// test multiply(...)
		Polynomial prod = p1.multiply(p2);
		System.out.println("prod(0.1) = " + prod.evaluate(0.1));

		// test saveToFile(...)
		sum.saveToFile("sum.txt");
		prod.saveToFile("prod.txt");
		System.out.println("Wrote sum.txt and prod.txt");
}