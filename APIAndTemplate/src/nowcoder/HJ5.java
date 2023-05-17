package nowcoder;

import java.util.Scanner;

public class HJ5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            str2Num(str);
        }
    }

    private static void str2Num(String str){
        if(str==null||str.length()==0){
            return;
        }
        int len=str.length();
        int num=0;
        for (int i = 2; i < len; i++) {
            char ch=str.charAt(i);
            int cur=0;
            if(ch>='0'&&ch<='9'){
                cur=ch-'0';
            }else if(ch>='A'&&ch<='F'){
                cur=10+(ch-'A');
            }else if(ch>='a'&&ch<='f'){
                cur=10+(ch-'a');
            }
            num=num*16+cur;
        }
        System.out.println(num);
    }

}
