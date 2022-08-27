public class Caught{
	public static void main(String[] srgs){
		int a=0, b;
		try{
			b=100/a;
			System.out.println("Is this printed?");
		}catch(ArithmeticException e){
			b=1;
			System.out.println("Division by 0: I will set the b by 1");
		}
		System.out.println("This will be printed");
	}
}

