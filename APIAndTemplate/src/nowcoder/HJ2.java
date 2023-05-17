package nowcoder;

import java.util.Scanner;

public class HJ2 {


   /*
    // 解法1：全部转为小写+替换
    public static void main(String[] args)  {
        Scanner input = new Scanner(System.in);
        // 完整字符串
        String str1 = input.nextLine().toLowerCase();
        // 单个字符串
        String str2 = input.nextLine().toLowerCase();
        // 完整字符的长度-单个字符长度 = 出现的次数
        int num = str1.length() - str1.replaceAll(str2,"").length();
        System.out.println(num);
    }

    */



//     解法2： 全部转为小写+计数器
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);//标准输入
        String s=sc.nextLine().toLowerCase();//输入字符串
        char c=sc.next().toLowerCase().charAt(0);//输入指定字母
        int count=0;//计数
        for(int i=0;i<s.length();i++){
            //如果是指定字母，或者其大小写，则计数加一
            if(s.charAt(i)==c){
                count++;
            }
        }
        System.out.println(count);
    }


}
