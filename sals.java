import java.util.*;

public class sals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            double n = sc.nextDouble(); // Current salary
            double percentage = sc.nextDouble(); // Percentage increase
            double increasedSalary = ((percentage * n) / 100.00) + n;
            System.out.println("Increased Salary: " + String.format("%.2f", increasedSalary));
        }
        sc.close();
    }
}