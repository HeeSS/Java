interface Stack1{
	int DEFAULT = 100;
	
	void push(int value);
	int pop();
}

abstract class Partial implements Stack1{
	int stk[];
	int top;
	
	public void push(int k){
		if(top==stk.length)
			System.out.println("Stack full");
		else
			stk[top++]=k;
	}
}

class ImpStack2 extends Partial{
	ImpStack2(){
		stk=new int[DEFAULT];
		top=0;
	}
	ImpStack2(int size){
		stk=new int[size];
		top=0;
	}
	public int pop(){
		if(top<=0){
			System.out.println("Stack empty");
			return -1;
		}
		else
			return stk[--top];
	}
}

class UseStack2{
	public static void main(String[] args){
		int x;
		ImpStack2 obj = new ImpStack2(50);

		obj.push(5);
		x=obj.pop();
		System.out.println("x= " + x);
		x=obj.pop();
		System.out.println("x= " + x);
	}
}