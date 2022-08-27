import java.util.*;
public class ImportExm{
	public static void main(String[] args){
		//Date today = new Date();
		int a[] = {90, 60, 27, 95, 33, 7};
		int b[] = Arrays.copyOf(a, a.length);
		Arrays.sort(a);
		for(int i=0; i<b.length; i++){	
			System.out.print(b[i]+" ");
		}
		System.out.println();
		for(int i=a.length-1; i>=0; i--){	
			System.out.print(a[i]+" ");
		}
		//System.out.println("Today is "+today);
		//System.out.print((today.getMonth()+1)+"월"+today.getDate()+"일 ");
		//System.out.println(today.getHours() + "시" +today.getMinutes()+"분");
	}
}
