package test;

public class Demo {
	public int test(int a, int b)
	{
		int c;
		if(a < 2 ) {
			c = a | b;
		}
		else if (b == 0) {
			c = a & b;
		}
		else
		{
			c = a + b;
		}
		
		return c;
	}
}
