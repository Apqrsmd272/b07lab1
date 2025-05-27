import java.io.File;
import java.util.Scanner;

public class Polynomial
{
	double[] num;
	int[] exponents;
	
	public Polynomial()
	{
		this.num = new double[] { 0.0 };
		this.exponents = new int[]{0};
	}

	public Polynomial(double[] x, int[] y)
	{
		this.num = new double[x.length];
		this.exponents = new int[y.length];
		int a = x.length;
		int b= y.length;
		for(int i =0; i<a;i++)
		{
			this.num[i] = x[i];
		}
		for(int i=0;i<b;i++)
		{
			this.exponents[i]= y[i];
		}
	}
	
	public Polynomial(File file){
		
		Scanner input = new Scanner(file);
		String line = "";
		while (input.hasNextLine()){
			line = input.nextLine().trim();
				if(!line.isEmpty()){
					break;
				}
			}
		input.close();

		if (!line.startsWith("+") && !line.startsWith("-")) {
			line = "+" + line;
		}
		int a = line.length();
		
		int[] en = new int[a];
		int e =0;
		double[] cn = new double[a];
		int c=0;
		
		char[] part = new char[a];
		int b=0;
		
		for(int i=0;i<a;i++)
		{
			char ch = line.charAt(i);
			if(ch!='x')
			{
				if(b>0 && (ch=='+' || ch=='-'))
				{
					String coe = new String(part, 0, b);
					double d= Double.parseDouble(coe);
					cn[c] =d;
					en[e]=0;
					e++;
					c++;
					b=0;
					continue;
				}
				part[b]=ch;
				b++;
			}
			else{
				int exp;
				if(i+1<a && line.charAt(i+1)>='0' && line.charAt(i+1)<='9')
				{
					i++;
					int start = i;
					while(i<a && line.charAt(i)>='0' && line.charAt(i)<='9'){
						i+=1;//count on the digits of exponents
					}
					exp = Integer.parseInt(line.substring(start, i));
					i-=1;
				}
				else{
					exp = 1;//exp == 1 for those not shown
				}
				
				String coe = new String(part, 0, b);
                if(b==1 &&  coe.equals("+")) coe = "1";
                if(b==1&& (coe.equals("-"))) coe = "-1";
                double d = Double.parseDouble(coe);
				
				cn[c] = d;
				en[e] = exp;
				c++;
				e++;
				b= 0;
			}
		}
		if (b > 0) {
			String coe = new String(part, 0, b);
			cn[c] = Double.parseDouble(coe);
			en[e] = 0;
			c++;
			e++;
		}
		this.num = new double[c];
		this.exponents = new int[c];
		for (int i = 0; i < c; i++) {
			this.num[i] = cn[i];
			this.exponents[i] = en[i];
		}
	}
	
	public Polynomial multiply(Polynomial x){
		int a=0;
		int b = x.num.length * this.num.length;
		double[] array = new double[b];
		int[] nums = new int[b];
		
		for(int i=0;i<x.num.length;i++){
			for(int j=0;j<this.num.length;j++){
				array[a]=x.num[i]*this.num[j];
				nums[a] = x.exponents[i]+this.exponents[j];
				a++;
			}
		}
		
		for (int i=0;i<nums.length-1;i++){
			if(nums[i]>nums[i+1]){
				int tmp=nums[i];
				nums[i]=nums[i+1];
				nums[i+1]=tmp;
				
				double tmpC=array[i];
				array[i]=array[i+ 1];
				array[i+1] = tmpC;
				i= -1;//so the loops makes it back to i=0
			}
		}
		int[] result1 = new int[b];
		double[] result2 = new double[b];
		
		int c=0;
		for (int i =0; i<b-1;i++){
			if (nums[i] != nums[i + 1]){
				result1[c] = nums[i];
				c++;
			}
		}
		result1[c] = nums[b - 1];
		c++;
		
		for(int i=0;i<result2.length;i++){
			result2[i]=0;
		}
		
		int d=0;
		for(int i=0;i<b;i++){
			if(nums[i]==result1[d]){
				result2[d] += array[i];
			}
			else{
				d++;
				result2[d] += array[i];
			}
		}
		double[] finalNums = new double[c];
		int[] finalExps = new int[c];
		for (int i=0;i<c;i++) {
			finalNums[i]=result2[i];
			finalExps[i]=result1[i];
		}
			
		Polynomial result = new Polynomial(finalNums, finalExps);
		return result;
	}	
	
	
	public Polynomial add(Polynomial x){
		
		int len1 = this.num.length;
		int len2 = x.num.length;
		
		int max = len1+len2;
		
		double[] array = new double[max];
		int[] nums  = new int[max];
		int a = 0;  // count of terms in array/nums

		for (int i=0;i<len1;i++) {
			array[a]=this.num[i];
			nums[a]=this.exponents[i];
			a++;
		}
		

		for(int i=0;i<len2;i++)
		{
			double cn = x.num[i];
			int en = x.exponents[i];
			int found =0;
			
			for(int j=0;j<a;j++)
			{
				if(nums[j]==en)
				{
					array[j]+=cn;
					found =1;
					break;
				}
			}
			if(found==0)
			{
				array[a] = cn;
				nums[a] = en;
				a++;
			}

		}
		for (int i=0;i< a-1;i++){
			for (int j=0;j< a-1-i;j++) {
				if (nums[j]> nums[j+1]) {
					int tmp= nums[j];
					nums[j]= nums[j+1];
					nums[j + 1] =tmp;
					double tmpC =array[j];
					array[j]= array[j+1];
					array[j+1]= tmpC;
				}
			}
		}
		
		double[] finalNums = new double[a];
		int[] finalExps = new int[a];
		for (int i=0;i<a;i++) {
			finalNums[i]=array[i];
			finalExps[i]=nums[i];
		}
		Polynomial result = new Polynomial(finalNums, finalExps);
		return result;

	}
	
	public double evaluate(double x)
	{
		double result =0.0;
		for(int i=0;i<this.exponents.length;i++)
		{
			double pow = 1.0;
			for(int j=0;j<this.exponents[i];j++)
			{
				pow = pow * x;
			}
			result = result+ (pow* this.num[i]);
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
	
	public void saveToFile(String file){
		
		java.io.FileWriter kkk = new java.io.FileWriter(file);
		for(int i=0;i<this.num.length;i++)
		{
			double coe = this.num[i];
			int exp = this.exponents[i];
			
			if(coe>=0)
			{
				kkk.write("+");
			}
			kkk.write(Double.toString(coe));
			if(exp==0) {
				continue;
			}
			kkk.write("x");
			kkk.write(Integer.toString(exp));
		}

		kkk.close();
	}
	
	
}