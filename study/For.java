public class WhileCompare{
	public static void main(String[] args){
		int start=0;
		System.out.println("while�� ������");
		while(++start <10){
			System.out.print(start+" ");
		}
		System.out.println();
		start=0;
		System.out.println("do-while�� ������");
		do{
			System.out.print(start+" ");
		}while(++start <10)
		System.out.println();
	}
}