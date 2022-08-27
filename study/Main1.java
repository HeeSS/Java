class Rectangle2{
	double x, y;
	Rectangle2(){ 
		x=10.0; y=20.0;
		System.out.println("This is a constructor");
	 }
	Rectangle2(double j){
		x=j; y=5.0;
		System.out.println("This is a (매개변수J)constructor");
	}
	Rectangle2(double j, double k){
		x=j; y=k;
		System.out.println("This is a (매개변수J,K)constructor");
	}
	double area(){ return x*y; }
}
class Main1{
	public static void main(String[] s){
		Rectangle2 c1=new Rectangle2();
		Rectangle2 c2=new Rectangle2(10.0, 30.0);
		Rectangle2 c3=new Rectangle2(10.0);
		double k1, k2, k3;
		k1=c1.area(); 
		System.out.println(k1);
		k2=c2.area();
		System.out.println(k2);
		k3=c3.area();
		System.out.println(k3);
	}
}