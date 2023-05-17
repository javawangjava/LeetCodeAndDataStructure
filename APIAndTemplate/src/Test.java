import java.util.*;

public class Test {
    public static void main(String[] args) {
        String[] nums={"eat","tea","tan","ate", "nat", "bat"};
        //String[] nums = {"abc", "bca", "cba", "123", "321", "213"};
        //String[] nums={"a"};
        //String[] nums={""};
        List<List<String>> lists = test(nums);
        System.out.print("[");

        for (int j=0;j<lists.size();j++) {
            System.out.print("[");
            for (int i = 0; i < lists.get(j).size(); i++) {
                List list=lists.get(j);
                System.out.print("\"" + list.get(i) + "\"");
                if (i != list.size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("]");
            if (j!= lists.size() - 1) {
                System.out.print(",");
            }
        }
        //for (List<String> list : lists) {
        //    System.out.print("[");
        //    for (int i = 0; i < list.size(); i++) {
        //        System.out.print("\"" + list.get(i) + "\"");
        //        if (i != list.size() - 1) {
        //            System.out.print(",");
        //        }
        //    }
        //    System.out.print("]"+",");
        //}
        System.out.print("]");

    }

    private static List<List<String>> test(String[] nums) {
        List<List<String>> lists = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return lists;
        }
        Map<String, List<String>> map = new HashMap<>();

        String[] copy = Arrays.copyOf(nums, nums.length);
        for (String num : copy) {
            char[] arr = num.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
        }

        for (String num : nums) {

            char[] arr2 = num.toCharArray();
            Arrays.sort(arr2);
            String key = new String(arr2);
            if (map.containsKey(key)) {
                List list = map.get(key);
                list.add(num);
            }
        }

        for (String key : map.keySet()) {
            lists.add(map.get(key));
        }
        return lists;

    }


}
