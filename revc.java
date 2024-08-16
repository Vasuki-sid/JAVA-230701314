import java.util.*;
public class revc{
	public static void main(String[] args){
		Scanner sc = new Scanner (System.in);
		int n = sc.nextInt();
		int temp = n;
		int rev =0;
		while(temp>0){
			int rem=temp%10;
			temp/=10;
			rev=(rev*10)+rem;
		}
		if(rev==n)
			System.out.println("Same : " + rev);
		else	
			System.out.println("Not Same : "+ rev);
	}
} 