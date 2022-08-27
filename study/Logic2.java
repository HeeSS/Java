public class SwitchStat{
	public static void main(String[] args){
		int na=10;
		int nb=5;
		char noperator='*';
		switch(noperator){
		case'+': System.out.println(na+"+"+nb+"="+(na+nb); break;
		case'-': System.out.println(na+"-"+nb+"="+(na-nb); break;
		case'*': System.out.println(na+"*"+nb+"="+(na*nb); break;
		case'/': System.out.println(na+"/"+nb+"="+(na/nb); break;
		default: System.out.println("¿¡·¯");
		}
	}
}