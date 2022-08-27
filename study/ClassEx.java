class Rectangle{
	double x, y;
	double area(){ return x*y; }
	void setvalue(double v1, double v2){ x=v1; y=v2; }
}
class ClassEx{
	public static void main(String[] args){
		Rectangle c1, c2;
		double k1, k2;
		c1=new Rectangle();
		c2=new Rectangle();
		c1.x=3.0; c1.y=4.0;
		k1=c1.x*c1.y;
		System.out.println(k1);
		c2.setvalue(4.0, 5.0);
		k2=c2.area();
		System.out.println(k2);
	}
}