package nowcoder;

import java.util.Scanner;

public class HJ4 {
     /*
    // 解法1：分割字符串
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            String str1=str+"0000000";
            int count=str1.length()/8;
            for (int i = 0; i < count; i++) {
                System.out.println(str1.substring(i*8,8+i*8));
            }
        }
    }

*/


    // 解法1：循环分割字符串
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            String str = sc.nextLine();
            String str1=str+"0000000";
            int count=str1.length()/8;
            for (int i = 0; i < count; i++) {
                StringBuilder sb=new StringBuilder();
                for (int j = i*8; j < 8+i*8; j++) {
                    sb.append(str1.charAt(j));
                }
                System.out.println(sb.toString());
            }
        }
    }
}
