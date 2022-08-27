//5���� ���� ����-2 & QUIZ
//1492018 ����
import java.lang.*;
class StackType{
	private int top;
	private String[] stack = new String [100];
	StackType(){
		top = -1;
	}
	boolean is_empty(){
		return (top == -1);
	}
	boolean is_full(){
		return (top == 99);
	}
	void push(String data){
		if(top == 99){
			System.out.println("Stack full");
		}
		else{
			stack[++top] = data;
		}
	}
	String pop(){	//23
		if(top < 0){
			System.out.println("Stack empty");
			return stack[top];
		}
		else{
			return stack[top--];
		} //30
	}
	String peek(){
		if(top < 0){
			System.out.println("Stack empty");
			return "-999";
		}
		else{
			return stack[top];
		}
	}
} //40

class Stack_Hee{
	public static void main(String[] s)throws java.lang.NumberFormatException{
		String input = "2.0+    3.0*    4.0-((2.0-    3.0)*5.0+4.0)-10.0+2.0^3.0^2.0^1.0=";
		String tmp[] = input.split("");
		String num = "";
		int index = 0;
		boolean is_first = true;
		
		String infix[] = new String [100];
		String postfix[] = new String [100];
		double result;
		
		//infix[] ���ϱ�
		for(int i=0; i<tmp.length; i++){
			if(tmp[i].equals("+")||tmp[i].equals("-")||tmp[i].equals("*")||tmp[i].equals("/")||tmp[i].equals("(")||tmp[i].equals(")")||tmp[i].equals("=")||tmp[i].equals("^")){
				if(is_first==false){
					is_first = true;
					infix[index++] = num;
				}
				infix[index++] = tmp[i];
			}
			else if(tmp[i].equals(" ")){
				continue;
			}
			else{
				if(is_first){
					num = tmp[i];
					is_first = false;
				}
				else{
					num = num + tmp[i];
				}
			}
		}

		//postfix[] ���ϱ�
		infix_to_postfix(infix, postfix);

		//evaluation
		result = postfix_eval(postfix);

		//��� ���
		System.out.println("input = " + input);
		System.out.print("infix = ");
		for(int i=0; !infix[i].equals("="); i++)
			System.out.print(infix[i]+ " ");
		System.out.println();

		System.out.print("postfix = ");
		for(int i=0; !postfix[i].equals("="); i++)
			System.out.print(postfix[i]+ " ");
		System.out.println();

		System.out.println("evaluation = " + result);
	}

	//�������� �켱������ ��ȯ
	static int prec(String op){
		if(op.equals("(") || op.equals(")")) return 0;
		else if(op.equals("+") || op.equals("-")) return 1;
		else if(op.equals("*") || op.equals("/")) return 2;
		else if(op.equals("^")) return 3;
		else return -1;
	}

	//������ -> ������
	static void infix_to_postfix(String infix[], String postfix[]){
		String ch, top_op;
		int count=0;
		StackType s = new StackType();
		
		for(int i=0; !infix[i].equals("="); i++){
			ch = infix[i];
			//�������̸�
			if(ch.equals("+") || ch.equals("-") || ch.equals("*") || ch.equals("/")){ 
				//���ÿ� �ִ� �������� �켱������ �� ũ�ų� ������
				while(!s.is_empty() && (prec(ch) <= prec(s.peek()))){
					postfix[count++] = s.pop();
				}
				s.push(ch);
			}
			else if(ch.equals("^")){
				while(!s.is_empty() && (prec(ch) < prec(s.peek()))){
					postfix[count++] = s.pop();
				}
				s.push(ch);
			}
			else if(ch.equals("(")){
				s.push(ch);
			}
			else if(ch.equals(")")){
				top_op = s.pop();
				//���� ��ȣ�� ���������� ���
				while(!top_op.equals("(")){
					postfix[count++] = top_op;
					top_op = s.pop();
				}
			}
			else{ //�ǿ�����
				postfix[count++] = ch;
			}
		}
		
		while(!s.is_empty()){
			postfix[count++] = s.pop();
		}
		postfix[count] = "=";
	}

	//������ -> ���	
	static double postfix_eval(String postfix[]){
		double op1, op2, value;
		String ch;
		StackType s = new StackType();
//System.out.println("1");		
		for(int i=0; !postfix[i].equals("="); i++){
			ch = postfix[i];
//System.out.print("2 ");
//System.out.println(ch);
			if(ch.equals("^")){
					op2 = Double.parseDouble(s.pop());
					op1 = Double.parseDouble(s.pop());
					value = Math.pow(op1, op2);
					ch = value + "";
					s.push(ch);
				}
			else if(!ch.equals("+") && !ch.equals("-") && !ch.equals("*") && !ch.equals("/")){ //�Է��� �ǿ������̸�
				s.push(ch);
			}
			else{ //�������̸� �ǿ����ڸ� ���ÿ��� ����
//System.out.println("1");
				op2 = Double.parseDouble(s.pop());
				op1 = Double.parseDouble(s.pop());

				if(ch.equals("+")){
					value = op1 + op2;
					ch = value + "";
					s.push(ch);
				}
				else if(ch.equals("-")){
					value = op1 - op2;
					ch = value + "";
					s.push(ch);
				}
				else if(ch.equals("*")){
					value = op1 * op2;
					ch = value + "";
					s.push(ch);
				}
				else if(ch.equals("/")){
					value = op1 / op2;
					ch = value + "";
					s.push(ch);
				}
			}
		}
		return Double.parseDouble(s.pop());
	}
}