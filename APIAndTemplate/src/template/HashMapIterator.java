package template;

import java.util.*;

public class HashMapIterator {
    public static void Test(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 10);
        map.put(2, 20);

        // 通过ForEach循环进行遍历
        // Iterating entries using a For Each loop
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        //  ForEach迭代键值对方式
        Iterator<Map.Entry<Integer, Integer>> entries = map.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }

        // 使用带泛型的迭代器进行遍历
        Iterator<Map.Entry<Integer, Integer>> entries1 = map.entrySet().iterator();
        while (entries1.hasNext()) {
            Map.Entry<Integer, Integer> entry = entries1.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

            //Integer key = (Integer) entry.getKey();
            //Integer value = (Integer) entry.getValue();
            //System.out.println("Key = " + key + ", Value = " + value);
        }

        //通过Java8 Lambda表达式遍历
        map.forEach((k, v) -> System.out.println("key: " + k + " value:" + v));





        //
        Set<String> set = new HashSet<>();
        set.add("a1");
        set.add("b2");


        // 初始化一个HashSet集合
        System.out.println("--------原HashSet集合----------");
        System.out.println(set);
        System.out.println();


        // 方法一：
        Iterator<String> iterator = set.iterator();
        System.out.println("--------迭代器遍历HashSet----------");
        while(iterator.hasNext()){
            System.out.print(iterator.next()+",");
        }


        // 方法二：
        System.out.println("--------加强for循环遍历---------");
        for (String item : set) {
            System.out.print(item+",");
        }

    }

}
