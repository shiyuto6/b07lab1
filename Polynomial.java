import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.*;
import java.util.*;


public class Polynomial {
	double[] coefficients;
	int[] exponents;
	
	public Polynomial() {
	}
	
	public Polynomial(double[] coeff, int[] exp) {
		int len = coeff.length;
		coefficients = new double[len];
		for (int i = 0; i < len; i++) {
			coefficients[i] = coeff[i];
		}
		int len2 = exp.length;
		exponents = new int[len2];
		for (int i=0; i < len2; i++) {
			exponents[i] = exp[i];
		}
	}
	
	public Polynomial(File x) throws IOException {
		BufferedReader b = new BufferedReader(new FileReader(x));
		String line = b.readLine();
		b.close();
		
		String replaced = line.replaceAll("\\-","\\+\\-");
		
		//System.out.println(replaced);
		
		String[] terms = replaced.split("\\+");
		int len=terms.length;
		
		/*for(int i=0; i<len; i++) {
			System.out.println(terms[i]);
		}*/
		
		int l=len;
		
		if(terms[0].isEmpty()) {
			l=l-1;
		}
		
		double[] resulco=new double[l];
		int[] resulexp=new int[l];
		
		int index = 0;
		
		
		for(int i=0; i<len; i++) {
			if(terms[i].isEmpty()) {
				i++;
			}
			
			String term = terms[i];
			String[] parts = term.split("x");
			
			double coeff = Double.parseDouble(parts[0]);

			int exp=0;
			if(parts.length>1) {
				exp = Integer.parseInt(parts[1]);
			}
			resulco[index] = coeff;
			resulexp[index] = exp;
			index++;
		}
		this.coefficients = resulco;
		this.exponents = resulexp;
		
		//System.out.println(Arrays.toString(coefficients));
		//System.out.println(Arrays.toString(exponents));
	}
	
	public Polynomial add(Polynomial poly) {
		int n=exponents.length+poly.exponents.length;
		for(int i=0; i<coefficients.length; i++) {
			for(int j=0; j<poly.coefficients.length; j++) {
				if(exponents[i]==poly.exponents[j]) {
					n=n-1;
				}
			}
		}
		double[] resulco=new double[n];
		int[] resulexp=new int[n];
		for(int i=0; i<coefficients.length; i++) {
			resulco[i]=coefficients[i];
			resulexp[i]=exponents[i];
		}
		int index=coefficients.length;
		for(int i=0; i<poly.exponents.length; i++) {
			int k=1;
			for(int j=0; j<exponents.length; j++) {
				if(poly.exponents[i]==exponents[j]) {
					resulco[j]=resulco[j]+poly.coefficients[i];
					k=0;
				}
			}
			if(k==1) {
				resulco[index]=poly.coefficients[i];
				resulexp[index]=poly.exponents[i];
				index++;
			}
		}
		int num =0;
		for(int i=0; i<resulco.length; i++) {
			if(resulco[i]==0) {
				num++;
			}
		}
		//System.out.println(num);
		int len=n-num;
		double[] coe = new double[len];
		int[] exp = new int[len];
		index=0;
		for(int i=0; i<resulco.length; i++) {
			if(resulco[i]!=0) {
				coe[index]=resulco[i];
				exp[index]=resulexp[i];
				index++;
			}
		}
		Polynomial result = new Polynomial(coe, exp);
		return result;
	}
	
	public double evaluate(double x) {
		double result = 0;
		for ( int i = 0; i < coefficients.length; i++) {
			result = result + coefficients[i] * (Math.pow(x, exponents[i]));
		}
		return result;
	}
	
	public boolean hasRoot(double x) {
		double result = evaluate(x);
		return result == 0.0;
	}
	
	public Polynomial multiply(Polynomial f) {
		int l = f.exponents.length * exponents.length;
		double[] temco = new double[l];
		int[] temexp = new int[l];
		int index=0;
		for(int i=0; i<exponents.length; i++) {
			for(int j=0; j<f.exponents.length; j++) {
				temco[index]=coefficients[i]*f.coefficients[j];
				temexp[index]=exponents[i]+f.exponents[j];
				index++;
			}
		}
		
		//System.out.println("temporary coefficients:"+Arrays.toString(temco));
		//System.out.println("exponents:"+ Arrays.toString(temexp));
		
		
		int len=l;
		for(int i=0; i<l; i++) {
			for(int j=i+1; j<l; j++) {
				if(temexp[i]==temexp[j]) {
					len--;
					j=l;
				}
			}
		}
		
		//System.out.println(len);
		
		
		index=0;
		double[] resulco=new double[len];
		int[] resulexp=new int[len];
		for(int i=0; i<len; i++) {
			resulexp[i]=-1;
		}
		for(int i=0; i<l; i++) {
			int k=0;
			for(int j=0; j<len; j++) {
				if(temexp[i]==resulexp[j]) {
					resulco[j]=resulco[j]+temco[i];
					j=len;
					k=1;
				}
			}
			if(k==0) {
				resulco[index]=temco[i];
				resulexp[index]=temexp[i];
				index++;
			}
		}
		
		
		int num =0;
		for(int i=0; i<resulco.length; i++) {
			if(resulco[i]==0) {
				num++;
			}
		}
		//System.out.println(num);
		int len1=len-num;
		double[] coe = new double[len1];
		int[] exp = new int[len1];
		index=0;
		for(int i=0; i<resulco.length; i++) {
			if(resulco[i]!=0) {
				coe[index]=resulco[i];
				exp[index]=resulexp[i];
				index++;
			}
		}
		Polynomial result = new Polynomial(coe, exp);
		return result;
	}
	
	
	public void saveToFile(String name) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(name));
		
		for(int i=0; i<coefficients.length; i++) {
			double coeff = coefficients[i];
			int exp = exponents[i];
			
			if(i!=0 && coeff>0) {
				writer.write("+");
			}
			
			writer.write(Double.toString(coeff));
			
			if(exp !=0) {
				writer.write("x");
			}
			
			writer.write(Integer.toString(exp));
		}
		writer.close();
	}
	
}
