
public class Polynomial {
	double[] coefficients;
	
	public Polynomial() {
		coefficients = new double[] {0.0};
	}
	
	public Polynomial(double[] coeff) {
		int len = coeff.length;
		coefficients = new double[len];
		for (int i = 0; i < len; i++) {
			coefficients[i] = coeff[i];
		}
	}
	
	public Polynomial add(Polynomial poly) {
		int len1 = poly.coefficients.length;
		int len2 = coefficients.length;
		int len = Math.max(len1, len2);
		double[] resultco = new double[len]; 
		for (int i = 0; i < len1; i++) {
			resultco[i] = poly.coefficients[i];	
		}
		for (int i = 0; i < len2; i++) {
			resultco[i] = resultco[i] + coefficients[i];
		}
		return new Polynomial(resultco);
	}
	
	public double evaluate(double x) {
		double result = 0;
		for ( int i = 0; i < coefficients.length; i++) {
			result = result + coefficients[i] * (Math.pow(x, i));
		}
		return result;
	}
	
	public boolean hasRoot(double x) {
		double result = evaluate(x);
		return result == 0.0;
	}
	
	
}