import java.util.*;

public class dcc{
	public static void main(String[] args){
		Scanner sc = new Scanner (System.in);
		int n = sc.nextInt();
		double d = n/100.0;
		System.out.println(String.format("%.2f",d)+"$");
	}
} 