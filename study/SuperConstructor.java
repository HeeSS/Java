class C{
	int a;
	C(){ a=1; System.out.println("Default constructor in superclass C"); }
	C(int a){ this.a=a; } 
}
class D extends C{
	int b;
	D(){ b=2; System.out.println("Default constructor in subclass D"); }
	//D(int a, int b){ super(a); this.b=b; }
	//D(int a, int b){ super(); this.b=b; }
	D(int a, int b){ this.b=b; }
}
class SuperConstructor{
	public static void main(String[] args){
		D obj1 = new D();
		System.out.println("a= "+obj1.a+", b= "+obj1.b);
		D obj2 = new D(4, 5);
		System.out.println("a= "+obj2.a+", b= "+obj2.b);
	}
}