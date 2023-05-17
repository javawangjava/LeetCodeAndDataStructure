package nowcoder;

import java.util.Scanner;

public class HJ15 {
    // 除二取余
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int count=0;
        while(num!=1){
            if(num%2==1){
                count++;
            }
            num/=2;
        }
        System.out.println(count+1);
    }

    // 转换成二进制字符串
    //public static void main(String[] args) {
    //    Scanner sc = new Scanner(System.in);
    //    int num = sc.nextInt();
    //    String str = Integer.toBinaryString(num);
    //    int length = str.length();
    //    String newStr = str.replaceAll("1", "");
    //    System.out.println(length - newStr.length());
    //}

}
