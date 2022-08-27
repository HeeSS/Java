class Stack {
	private int top;
	private int[] stk = new int [10];
	Stack(){ top = -1; }
	void push(int data){
		if(top == 9)	System.out.println("Stack full");
		else		stk[++top] = data;
	}
	int pop(){
		if(top<0)	return -999;
		else	return stk[top--];
	}
}
class UseStack{
	public static void main(String s[]){
		Stack stk1 = new Stack();
		int k1;
		stk1.push(3);
		stk1.push(5);
		k1 = stk1.pop(); System.out.println(k1);
		k1 = stk1.pop(); System.out.println(k1);
		k1 = stk1.pop(); System.out.println(k1);
	}
}