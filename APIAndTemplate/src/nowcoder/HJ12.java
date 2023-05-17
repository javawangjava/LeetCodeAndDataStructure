package nowcoder;

import java.util.Scanner;

public class HJ12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb=new StringBuilder();
        while(sc.hasNext()) {
            String str = sc.next();
            for (int i = str.length() - 1; i >= 0; i--) {
                sb.append(str.charAt(i));
            }
            System.out.println(new String(sb));
        }
    }

}
