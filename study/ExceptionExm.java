public class ExceptionExm{
	static int count=0;
	public static void main(String[] srgs){
		int result;
		while(true){
			try{
				result=3/count++;
				if(count ==3) break;
				System.out.println(count + ") No Exception");
			}catch(ArithmeticException e){
				System.out.println(count + ") Error: 0���� ������ ����");
			}finally{
				System.out.println(count+ ") finally block Executed");
			}
		}
	}
}

