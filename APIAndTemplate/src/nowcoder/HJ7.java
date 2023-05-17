package nowcoder;

import java.util.Scanner;

public class HJ7 {
    // 解法1：质因数从小到大逐个除，并且包含相同的质因数
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            //int num = sc.nextLong();
            //long num = sc.nextLong();
            float num = sc.nextFloat();
            //double num = sc.nextLong();
            solution(num);
        }
    }

    private static void solution( float  num){
        if(num<=0){
            return;
        }
        System.out.println((int)(num+0.5));
    }
}
