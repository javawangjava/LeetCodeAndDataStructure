/**
 * <p>给你一个字符串数组，请你将 <strong>字母异位词</strong> 组合在一起。可以按任意顺序返回结果列表。</p>
 *
 * <p><strong>字母异位词</strong> 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> strs = <code>["eat", "tea", "tan", "ate", "nat", "bat"]</code>
 * <strong>输出: </strong>[["bat"],["nat","tan"],["ate","eat","tea"]]</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> strs = <code>[""]</code>
 * <strong>输出: </strong>[[""]]
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> strs = <code>["a"]</code>
 * <strong>输出: </strong>[["a"]]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= strs.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= strs[i].length &lt;= 100</code></li>
 * <li><code>strs[i]</code>&nbsp;仅包含小写字母</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>哈希表</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍
 * 1176</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


import java.util.*;

/**
 * 49
 * 字母异位词分组
 *
 * @author wangweizhou
 * @date 2022-07-01 00:57:13
 */

public class GroupAnagrams {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new GroupAnagrams().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 把一组变位词映射到同一个单词。由于互为变位词的单词的字母出现的次数分别相同，因此如果把单词中的字母排序就会得到相同的字符串。
        // 这里采用遍历到每一个字符串，将字符串转换为字符数组，然后将字符数组排序，那么互为变位词的单词对应的字符数组排序结果一样，
        // 因此，可以定义一个哈希表，哈希表的键是把单词字母排序得到的字符串，而值为一组变位词。


        // 解法1：哈希表  同组的字母异位词排序后是同一个字符顺序。
        // 1.将字符串数组的每个元素转换为字符数组并按照字母顺序进行排序。
        // 2.异位词排序后的结果相同，故可以作为哈希表的key值，将字母异位词组成的集合作为哈希表的value值。
        // 哈希表的键key是排序后的异位词，哈希表的值value是具有相同排序的字母异位词组成的集合。
        // 写法123只是细节有一点不一样，就是调用的API不同


        //// 解法1：哈希表 写法1
        //public List<List<String>> groupAnagrams(String[] strs) {
        //	if(strs == null || strs.length == 0){// 判断是否为空字符串数组
        //		return new ArrayList();
        //	}
        //	Map<String, List<String>> map = new HashMap<>();// 哈希表的键key是排序后的异位词，哈希表的值value是具有相同排序的字母异位词组成的集合
        //	for (String str : strs) {// 遍历字符串数组
        //		char[] charArray = str.toCharArray();//将字符数组的单个字符串转化为字符数组
        //		Arrays.sort(charArray);//对字符串对应的字符数组按照字母顺序排序
        //		String key = new String(charArray);//将异位词排序后的字符数组转换成字符串，并将该字符串作为哈希表中的key值
        //
        //		// 异位词排序后的字符串对应的value值，若哈希表中没有该key值，则新建一个list,若有该key值，则返回已经有的value值即该key值对应的list,
        //		List<String> list = map.getOrDefault(key, new ArrayList<>());// 获取当期排序后的字符串的对应的集合
        //		list.add(str);//将原来的单个字符串添加到存储字母异位词的集合list中
        //		map.put(key, list);// 将该键值对添加到map中
        //	}
        //	// Collection<V> values()  Returns a Collection view of the values contained in this map.返回这个哈希表的值的集合。
        //	// ArrayList(Collection<? extends E> c) 构造一个包含指定集合的元素的列表，
        //	// Constructs a list containing the elements of the specified collection, in the order they are returned
        //	// by the collection's iterator.
        //	return new ArrayList<>(map.values());//返回以map的value域为参数创建的集合
        //}


        public List<List<String>> groupAnagrams(String[] nums) {
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



        //// 解法1：哈希表+排序映射
        //public List<List<String>> groupAnagrams(String[] strs) {
        //    List<List<String>> lists = new ArrayList<>();
        //    if (strs == null || strs.length == 0) {
        //        return lists;
        //    }
        //    Map<String, List<String>> map = new HashMap<>();
        //    for (String str : strs) {
        //        char[] cs = str.toCharArray();
        //        Arrays.sort(cs);
        //        String key = new String(cs);
        //        List<String> list = map.getOrDefault(key, new ArrayList<>());
        //        list.add(str);
        //        map.put(key, list);
        //    }
        //    for (String key : map.keySet()) {
        //        lists.add(map.get(key));
        //    }
        //    return lists;
        //}




        //// 解法1：哈希表  写法3和写法1   调用的API不同
        //public List<List<String>> groupAnagrams(String[] strs) {
        //    if (strs == null || strs.length == 0) {// 判断是否为空字符串数组
        //        return new ArrayList();
        //    }
        //    Map<String, List<String>> groups = new HashMap<>();// 哈希表的键key是排序后的异位词，哈希表的值value是具有相同排序的字母异位词组成的集合
        //    for(String str:strs){// 遍历字符串数组中的所有字符串
        //        char[] charArray=str.toCharArray();//将字符数组的单个字符串转化为字符数组
        //        Arrays.sort(charArray);// 字符数组进行排序
        //        String sorted=new String(charArray);// 排序后的字符数组创建一个字符串
        //
        //        // default V putIfAbsent(K key, V value)
        //        // If the specified key is not already associated with a value (or is mapped to null) associates it
        //        // with the given value and returns null, else returns the current value.
        //        groups.putIfAbsent(sorted,new ArrayList<>());
        //        // groups.get(sorted) 获取排序后的字符串对应的集合，将原字符串加入到对应集合中
        //        groups.get(sorted).add(str);// 获取排序后的单词对应的集合并将互为变位词的单词加入对应集合
        //    }
        //    return new ArrayList<>(groups.values());
        //}




        //// 解法1：哈希表 写法2  和写法1的区别在于list的获取方式不一样
        //public List<List<String>> groupAnagrams(String[] strs) {
        //	if(strs == null || strs.length == 0){// 判断是否为空字符串数组
        //		return new ArrayList();
        //	}
        //
        //	Map<String, List<String>> map = new HashMap<>();// 1.创建一个哈希表
        //	for (String str : strs) {// 遍历字符串数组
        //		char[] charArray = str.toCharArray();//将字符数组的单个字符串转化为字符数组
        //		Arrays.sort(charArray);//对字符串对应的字符数组按照字母顺序排序
        //		String key = new String(charArray);//将异位词排序后的字符数组转换成字符串，并将该字符串作为哈希表中的key值
        //		List<String> list=null;
        //		if(map.containsKey(key)){// 当map中含有异位词排序后的键值对，获取该键值对的值list，list是用来存储同组字母异位词。
        //			list =map.get(key);
        //		}else{
        //			list =new ArrayList<>();// 当map中不含有异位词排序后的键值对，创建新的list是用来存储同组字母异位词。
        //		}
        //		list.add(str);// 将原字符串添加到储存同组字母异位词的list中
        //		map.put(key, list);// 将该键值对添加到map中
        //	}
        //	// Collection<V> values()  返回一个 Collection视图的值包含在这个Map。
        //	// 这里调用有参的构造函数
        //	return new ArrayList<>(map.values());//返回以map的value域为参数创建的集合
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
