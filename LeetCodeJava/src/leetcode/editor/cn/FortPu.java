/**
 * <p>设计一个支持在<em>平均&nbsp;</em>时间复杂度 <strong>O(1)</strong>&nbsp;下，执行以下操作的数据结构：</p>
 *
 * <ul>
 * <li><code>insert(val)</code>：当元素 <code>val</code> 不存在时返回 <code>true</code>&nbsp;，并向集合中插入该项，否则返回 <code>false</code>
 * 。</li>
 * <li><code>remove(val)</code>：当元素 <code>val</code> 存在时返回 <code>true</code>&nbsp;，并从集合中移除该项，否则返回
 * <code>false</code>&nbsp;。</li>
 * <li><code>getRandom</code>：随机返回现有集合中的一项。每个元素应该有&nbsp;<strong>相同的概率&nbsp;</strong>被返回。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 :</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>inputs = [&quot;RandomizedSet&quot;, &quot;insert&quot;, &quot;remove&quot;, &quot;insert&quot;, &quot;getRandom&quot;, &quot;remove&quot;, &quot;insert&quot;, &quot;getRandom&quot;]
 * [[], [1], [2], [2], [], [1], [2], []]
 * <strong>输出: </strong>[null, true, false, true, 2, true, false, 2]
 * <strong>解释:
 * </strong>RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
 * randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
 *
 * randomSet.remove(2); // 返回 false，表示集合中不存在 2
 *
 * randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
 *
 * randomSet.getRandom(); // getRandom 应随机返回 1 或 2
 *
 * randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]
 *
 * randomSet.insert(2); // 2 已在集合中，所以返回 false
 *
 * randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong><meta charset="UTF-8" /></p>
 *
 * <ul>
 * <li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
 * <li>最多进行<code> 2 * 10<sup>5</sup></code> 次&nbsp;<code>insert</code> ， <code>remove</code> 和 <code>getRandom</code>
 * 方法调用</li>
 * <li>当调用&nbsp;<code>getRandom</code> 方法时，集合中至少有一个元素</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 380&nbsp;题相同：
 * <a href="https://leetcode-cn.com/problems/insert-delete-getrandom-o1/">https://leetcode-cn.com/problems/insert-delete-getrandom-o1/</a></p>
 * <div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>数学</li><li>随机化</li></div></div><br><div
 * ><li>👍 51</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer II 030
 * 插入、删除和随机访问都是 O(1) 的容器
 * @author wangweizhou
 * @date 2022-11-10 18:50:54
 */
public class FortPu {
    public static void main(String[] args) {
        //测试代码
        // Solution solution = new FortPu().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class RandomizedSet {
        // 方法一：ArrayList+哈希表

        //   可变数组ArrayList中存储元素，哈希表的键key存储元素，哈希表的值value存储该元素在数组中的下标。
        //   同一个数据存储在两个地方，利用各自的优势进行互补操作。因此操作数据在数组和哈希表中要同时处理。
        //   利用哈希表来快速判断是否存在，存在可以快速获取在数组中下标。利用下标可以快速获取该元素，然后就可以完成插入和删除。

        List<Integer> nums;// 变长数组中存储元素，存取有序
        Map<Integer, Integer> map;// 哈希表中存储每个元素及其在变长数组中的下标。，哈希表中key是元素的值，value是对应元素在边长数组中的下标
        Random random;


        public RandomizedSet() {
            nums = new ArrayList<>();
            map = new HashMap<>();
            random = new Random();
        }


        // bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false
        public boolean insert(int val) {
            if (map.containsKey(val)) {// 插入前先判断是否已经存在在哈希表中，已经有了就不能再插入。没有的话就可以再插入。
                return false;
            }
            int index = nums.size();//获取元素在list中的下标
            // 当哈希表中没有该元素时，插入到可变数组的最后一个位置
            nums.add(val);//将元素插入到可变数组的最后。List加入数据就会加入到末尾
            map.put(val, index);//在哈希表中存储该元素和其在数组中的对应下标。将 val 和其在数组中对应下标index 存入哈希表；
            return true;
        }


        // bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
        // 删除操作的重点在于将变长数组的最后一个元素移动到待删除元素的下标处，然后删除变长数组的最后一个元素。
        //  该操作的时间复杂度是 O(1)，且可以保证在删除操作之后变长数组中的所有元素的下标都连续，方便插入操作和获取随机元素操作。
        public boolean remove(int val) {
            if (!map.containsKey(val)) {//删除操作时，首先判断 val 是否在哈希表中，如果不存在则返回 false
                return false;
            }

            //当元素 val 存在时，利用哈希表快速得到待删除元素在数组中的下标
            int index = map.get(val);// 待删除元素在ArrayList中的索引
            int lastValue = nums.get(nums.size() - 1);//获取可变数组ArrayList中最后一个元素

            //  将变长数组ArrayList的最后一个元素移动到待删除元素的下标处，并更新对应的map集合
            //E set(int index, E element) 用指定的元素element替换此列表中指定位置index的元素。
            nums.set(index, lastValue);
            map.put(lastValue, index);// Map集合键key是元素，值value是该元素在双向链表中的位置

            //在变长数组ArrayList中删除最后一个元素，在哈希表中删除 val；
            nums.remove(nums.size() - 1);
            map.remove(val);
            return true;
        }


        // int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
        // int nextInt(int bound) 返回从该随机数生成器的序列中提取的伪随机，均匀分布的值在0（包括）和指定值（不包括）之间的 int 。
        public int getRandom() {
            int randomIndex = random.nextInt(nums.size());//调用API
            return nums.get(randomIndex);
        }


    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
