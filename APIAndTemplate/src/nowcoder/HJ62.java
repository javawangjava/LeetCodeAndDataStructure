package nowcoder;

import java.util.Scanner;

public class HJ62 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            int a=sc.nextInt();
            System.out.println(get1(a));
        }
    }
    private static int get1(int num){
        int count=0;
        for (int i = 0; i < 31; i++) {
            if((num>>i&1)==1){
                count++;
            }
        }
        return count;
    }
}
