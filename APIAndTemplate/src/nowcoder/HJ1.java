package nowcoder;

import java.util.Scanner;

// HJ1 字符串最后一个单词的长度
public class HJ1 {


    // 解法2： 从后向前遍历
    // 题干说明：（注：字符串末尾不以空格为结尾）
    public  static void  main(String [] args) throws Exception {

        Scanner sc = new Scanner(System.in);//标准输入
        String str = sc.nextLine();   //键盘输入字符串
        int length = str.length();
        int count = 0;
        for (int i = length - 1; i >= 0; i--) {   //从后往前第一个空格的位置
            if (str.charAt(i)==' ') { // 或者 if (str.substring(i, i + 1).equals(" ")) {
                break;
            }
            count++;
        }
        System.out.println(count);

    }


  /*
    // 解法1： 分割字符串
    public  static void  main(String [] args) throws Exception{
        Scanner sc=new Scanner(System.in);//标准输入
        while(sc.hasNext()){
            String input=sc.nextLine();//获取整行输入的字符串；
            if(input.length()>5000){
                continue;
            }
            String[] splits=input.split(" ");// 将字符串以空格【“ ”】进行分割，得到字符串数组；
            // 获取数组最后一个单词：split[split.length -1]，并获得它的长度：split[split.length - 1].length()
            int lastWordLength=splits[splits.length-1].length();//
            System.out.println(lastWordLength);
        }
    }

    */
}
