public class WhileCompare2{
	public static void main(String[] args){
		int start=0, sum=0;
		System.out.println("while�� ������");
		while(++start <=100){
			sum+=start;
			if(start==50) break;
		}
		System.out.println(sum);
		start=0; sum=0;
		System.out.println("do-while�� ������");
		do{
			if(start%2==0) continue;
			sum+=start;
		}while(++start <=100);
		System.out.println(sum);
	}
}