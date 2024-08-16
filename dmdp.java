/*import java.util.*;
public class dmdp{
	public static void main(String[] args){
		Scanner sc = new Scanner (System.in);
		int n= sc.nextInt();
		for(int i=0;i<n;i++){
			for(int j=0;j<n-i-1;j++)
				System.out.print(" ");
			for(int j=0;j<=i;j++)
				System.out.print(" "+(fact(i)/fact(j)*fact(i-j)));
			System.out.println();
		}
		for(int i=n-2;i>=0;i--){
			for(int j=n-1;j>0;j--)
				System.out.print(" ");
			for(int j=i-1;j>0;j--)
				System.out.print(" "+(fact(i)/fact(j)*fact(i-j)));
			System.out.println();
		}
	}
	public static int fact(int n){
		if(n==0)
			return 1;
		return(n*fact(n-1));
	}
}
*/
import java.util.*;
public class dmdp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++)
                System.out.print(" ");
            for (int j = 0; j <= i; j++)
                System.out.print(" " + binomialCoefficient(i, j));
            System.out.println();
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < n - i - 1; j++)
                System.out.print(" ");
            for (int j = 0; j <= i; j++)
                System.out.print(" " + binomialCoefficient(i, j));
            System.out.println();
        }
        sc.close();
    }
    public static int binomialCoefficient(int n, int k) {
        if (k > n) return 0;
        if (k == 0 || k == n) return 1;
        return fact(n) / (fact(k) * fact(n - k));
    }
    public static int fact(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
} 