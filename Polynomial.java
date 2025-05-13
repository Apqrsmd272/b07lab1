public class Polynomial
{
	double[] num;
	
	public Polynomial()
	{
		this.num = new double[] { 0.0 };
	}

	public Polynomial(double[] x)
	{
		this.num = new double[x.length];
		int a = x.length;
		for(int i =0; i<a;i++)
		{
			this.num[i] = x[i];
		}

	}
	
	public Polynomial add(Polynomial x){
		
		int len1 = this.num.length;
		int len2 = x.num.length;
		
		int max;
		if (len1 > len2) {
			max = len1;
		} else {
			max = len2;
		}
		
		double[] sum = new double[max];
		double a;
		double b;

		for (int i = 0; i < max; i++) {
			
			if (i < len1) {
				a = this.num[i];
			} else {
				a = 0.0;
			}
			
			if (i < len2) {
				b = x.num[i];
			} else {
				b = 0.0;
			}
			sum[i] = a + b;
		}
		Polynomial result = new Polynomial(sum);
		return result;
	}
	
	public double evaluate(double x)
	{
		int a = this.num.length;
		double result = 0.0;
		
		for(int i =0;i<a;i++)
		{
			double b = 1.0;
			for(int j =0;j<i;j++){
				b = b*x;
			}

			result = result + (this.num[i])*(b);
		}
		return result;
	}
	
	public boolean hasRoot(double x)
	{
		if(this.evaluate(x) ==0)
		{
			return true;
		}
		return false;
	}
	
	
}