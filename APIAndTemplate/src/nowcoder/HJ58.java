package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

public class HJ58 {
    public static void main(String[] args) {
        int[] arr;
        Scanner sc=new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n=sc.nextInt();
            arr=new int[n];
            int k=sc.nextInt();
            for (int i = 0; i < n; i++) {
                arr[i]=sc.nextInt();
            }
            Arrays.sort(arr);
            for (int i = 0; i < k; i++) {
                System.out.print(arr[i]+" ");
            }
        }
        //int n=sc.nextInt();
        //arr=new int[n];
        //int k=sc.nextInt();
        //for (int i = 0; i < n; i++) {
        //    arr[i]=sc.nextInt();
        //}
    }
}
