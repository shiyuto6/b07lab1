import java.util.Arrays;
import java.io.*;


public class Driver {
	public static void main(String [] args) {
		Polynomial p = new Polynomial();
		double [] c1 = {6,-2,5};
		int [] e1 = {0, 1, 4};
		Polynomial p1 = new Polynomial(c1, e1);
		double [] c2 = {-3,-6};
		int[] e2= {3,4};
		Polynomial p2 = new Polynomial(c2,e2);
		//Test add
		Polynomial s = p1.add(p2);
		System.out.println("addition coefficients:"+Arrays.toString(s.coefficients));
		System.out.println("addition exponents:"+ Arrays.toString(s.exponents));
		//Test evaluate
		double a=s.evaluate(2);
		System.out.println("evaluate at 2 is "+ a);
		//Test hasRoot
		System.out.println("s has root at 1: "+ s.hasRoot(1));
		//Test multiply
		double [] c4 = {1,1};
		int [] e4 = {0, 1};
		Polynomial p4 = new Polynomial(c4, e4);
		double [] c5 = {-1,1};
		int [] e5 = {0, 1};
		Polynomial p5 = new Polynomial(c5, e5);
		Polynomial s2 = p1.multiply(p2);
		System.out.println("multiplication test 1 coefficients:"+Arrays.toString(s2.coefficients));
		System.out.println("multiplication test 1 exponents:"+ Arrays.toString(s2.exponents));
		Polynomial s3 = p4.multiply(p5);
		System.out.println("multiplication test 2 coefficients:"+Arrays.toString(s3.coefficients));
		System.out.println("multiplication test 2 exponents:"+ Arrays.toString(s3.exponents));
		try{
			//Test file constructor
			File file= new File("D:\\2023Fall\\CSCB07\\111\\file.txt");
			Polynomial p3 = new Polynomial(file);
			System.out.println("file coefficients:"+Arrays.toString(p3.coefficients));
			System.out.println("file exponents:"+ Arrays.toString(p3.exponents));
			
			//Test saveToFile
			s.saveToFile("D:\\2023Fall\\CSCB07\\111\\output.txt");
		}catch(IOException e) {	
		}
	}
	
}