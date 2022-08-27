public class ThisConstructor {
	int x, y;
	ThisConstructor(){
		x=1;
		System.out.println("Default Constructor");
	}
	ThisConstructor(int y){
		//this();
		this.y = y;
		System.out.println("Constructor with one paramiter");
	}
	public static void main(String args[]){
		ThisConstructor obj = new ThisConstructor(10);
		System.out.println("obj.x = " + obj.x + ", obj.y = " + obj.y);
	}
}