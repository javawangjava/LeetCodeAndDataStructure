package nowcoder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class HJ9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        while(sc.hasNext()){
            int num = sc.nextInt();
            Set<Integer> set = new HashSet<>();
            while(num!=0){
                int cur=num%10;
                if(set.add(cur)){
                    sum = sum * 10 + cur;
                }
                num/=10;
            }
            System.out.println(sum);
        }
    }



    //public static void main(String[] args) {
    //    Scanner sc = new Scanner(System.in);
    //    int sum = 0;
    //    while(sc.hasNext()){
    //        int num = sc.nextInt();
    //        String str = String.valueOf(num);
    //        Set<Integer> set = new HashSet<>();
    //
    //        for (int i = str.length() - 1; i >= 0; i--) {
    //            int cur = str.charAt(i) - '0';
    //            if (!set.contains(cur)) {
    //                set.add(cur);
    //                sum = sum * 10 + cur;
    //            }
    //        }
    //        System.out.println(sum);
    //    }
    //}

}
