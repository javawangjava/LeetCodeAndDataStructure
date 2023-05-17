package nowcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HJ59 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=null;
        while (sc.hasNext()){
            str=sc.next();
        }
        Map<Character,Integer> map=new HashMap<>();
        int len=str.length();
        char[] charArr=str.toCharArray();
        for (int i = 0; i <len ; i++) {
            map.put(charArr[i],map.getOrDefault(charArr[i],0)+1);
        }
        for (int i = 0; i < len; i++) {
            if(map.get(charArr[i])==1){
                System.out.println(charArr[i]);
                return;
            }
        }
        System.out.println(-1);
    }

}
