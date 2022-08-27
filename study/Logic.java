public class Logic{
	public static void main(String[] args){
		int i=10, j=20;
		boolean a,b,c;
		a=(i>=5 && j<=25);
		b=(i<10 || j==20);
		c=(i!=10);
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("c="+c);
	}
}