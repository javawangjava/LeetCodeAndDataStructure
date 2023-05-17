package nowcoder;

import java.util.*;


public class HJ8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 因为TreeMap继承SortedMap类，是基于红黑树实现的。
        // 键值对根据key值自动排序
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (sc.hasNextInt()) {
            //取出第一个值，赋值给n
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                //一次取出key值和value的值
                int key = sc.nextInt();
                int value = sc.nextInt();
                //将值放入map中，如果遇到key值相等的情况，将value进行累加，进而实现去重的效果
                map.put(key, map.getOrDefault(key, 0) + value);
            }
            //依次输出去重后的map
            for (Integer i : map.keySet()) {
                System.out.println(i + " " + map.get(i));
            }
        }
    }
}
