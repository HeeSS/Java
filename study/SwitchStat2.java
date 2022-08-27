public class SwitchStat2{
	public static void main(String[] args){
		int na=10;
		int nb=5;
		char noperator[]= {'+', '-', '*', '/', '%'};
		for(int i=0; i<noperator.length; i++){
			switch(noperator[i]){
			case'+': System.out.println(na+"+"+nb+"="+(na+nb)); 				break;
			case'-': System.out.println(na+"-"+nb+"="+(na-nb)); 				break;
			case'*': System.out.println(na+"*"+nb+"="+(na*nb)); 				break;
			case'/': System.out.println(na+"/"+nb+"="+(na/nb)); 				break;
			default: System.out.println("¿¡·¯");
			}
		}
	}
}