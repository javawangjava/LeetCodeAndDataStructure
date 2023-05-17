package nowcoder;

import java.util.Scanner;

public class HJ6 {
    // 解法1：质因数从小到大逐个除，并且包含相同的质因数
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            long num = sc.nextLong();
            solution(num);
        }
    }

    private static void solution(long num){
        if(num<=0){
            return;
        }
        for (long i = 2; i*i <= num; ++i) {//
            while (num % i == 0) {// 找出尽可能小并且相同的质因数
                System.out.print(i + " ");
                num /= i;
            }
        }
        System.out.println(num == 1 ? "": num+" ");// 确定最后一个质因数
    }

}
