package nowcoder;

import java.util.Scanner;

public class HJ60 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num=sc.nextInt();
        if(num<4||num>1000){
            return;
        }
        int left=num/2;
        int right=num/2;
        while (left>0){
            if(isPrime(left)&&isPrime(right)){
                break;
            }
            left--;
            right++;
        }
        System.out.println(left);
        System.out.println(right);

    }

    private static boolean isPrime(int num){
        for (int i = 2; i < num/2; i++) {
            if(num%i==0){
                return false;
            }
        }
        return true;
    }
}
