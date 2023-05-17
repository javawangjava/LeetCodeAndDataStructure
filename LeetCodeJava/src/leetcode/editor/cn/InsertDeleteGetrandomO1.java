/**
 * <p>实现<code>RandomizedSet</code> 类：</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <ul>
 * <li><code>RandomizedSet()</code> 初始化 <code>RandomizedSet</code> 对象</li>
 * <li><code>bool insert(int val)</code> 当元素 <code>val</code> 不存在时，向集合中插入该项，并返回 <code>true</code> ；否则，返回
 * <code>false</code> 。</li>
 * <li><code>bool remove(int val)</code> 当元素 <code>val</code> 存在时，从集合中移除该项，并返回 <code>true</code> ；否则，返回
 * <code>false</code> 。</li>
 * <li><code>int getRandom()</code> 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 <strong>相同的概率</strong> 被返回。</li>
 * </ul>
 *
 * <p>你必须实现类的所有函数，并满足每个函数的 <strong>平均</strong> 时间复杂度为 <code>O(1)</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * <strong>输出</strong>
 * [null, true, false, true, 2, true, false, 2]
 *
 * <strong>解释</strong>
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // 向集合中插入 1 。返回 true 表示 1 被成功地插入。
 * randomizedSet.remove(2); // 返回 false ，表示集合中不存在 2 。
 * randomizedSet.insert(2); // 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
 * randomizedSet.getRandom(); // getRandom 应随机返回 1 或 2 。
 * randomizedSet.remove(1); // 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
 * randomizedSet.insert(2); // 2 已在集合中，所以返回 false 。
 * randomizedSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-2<sup>31</sup> &lt;= val &lt;= 2<sup>31</sup> - 1</code></li>
 * <li>最多调用 <code>insert</code>、<code>remove</code> 和 <code>getRandom</code> 函数 <code>2 *&nbsp;
 * </code><code>10<sup>5</sup></code> 次</li>
 * <li>在调用 <code>getRandom</code> 方法时，数据结构中 <strong>至少存在一个</strong> 元素。</li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>数学</li><li>随机化</li></div></div><br><div
 * ><li>👍 569</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 380
 * O(1) 时间插入、删除和获取随机元素
 *
 * @author wangweizhou
 * @date 2022-07-06 20:43:14
 */

public class InsertDeleteGetrandomO1 {

    public static void main(String[] args) {
        //测试代码
        //Solution solution = new InsertDeleteGetrandomO1().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class RandomizedSet {


        // 由于题目要求插入和删除（包括判断数据集中是否包含一个数值）的时间复杂度都是O（1），能够同时满足这些时间效率要求的只有哈希表，因此这个数据结构要用到哈希表。
        // 但是如果只用哈希表，则不能等概率地返回其中的每个数值。
        // 如果数值是保存在数组中的，那么很容易实现等概率返回数组中的每个数值。
        // 假设数组的长度是n，那么等概率随机生成从0到n-1的一个数字。如果生成的随机数是i，则返回数组中下标为i的数值。
        // 由此可以发现，需要结合哈希表和数组的特性来设计这个数据容器。
        // 由于数值保存在数组中，因此需要知道每个数值在数组中的位置，否则在删除的时候就必须顺序扫描整个数组才能找到待删除的数值，那就需要O（n）的时间。
        // 通常把每个数值在数组中的位置信息保存到一个HashMap中，HashMap的键是数值，而对应的值为它在数组中的位置。
        // 数组定义时是固定容量的，而且固定数组的先后顺序不好处理。所以下面使用链表+哈希表组合来实现数据结构。


        // 在数据容器RandomizedSet中，数值保存在用ArrayList实现的动态数组nums中，
        // 而用HashMap实现的哈希表map中存储了每个数值及其在数组nums中的下标【可变链表是实际存储数据的容器，哈希表是】。
        // 在添加新的数值之前需要先判断数据集中是否已经包含该数值。
        // 如果数据集中之前已经包含该数值，则不能再添加，直接返回false即可。
        // 如果之前没有该数值，则把它添加到数组nums的尾部，并把它和它在数组中的下标添加到哈希表numToLocation中。
        // 在HashMap和ArrayList的尾部添加数据的操作的时间复杂度都是O（1）。
        // 同样，在删除一个数值之前需要先判断数据集中是否已经包含该数值。如果数据集中没有包含该数值，则不能删除，直接返回false。
        // 如果数据集中已经包含该数值，就需要把它从哈希表numToLocation和数组nums中删除。
        // 从哈希表中用O（1）的时间删除一个数字比较简单，直接调用HashMap的函数remove即可。
        // 从数组中用O（1）的时间删除一个数字要稍微麻烦一点。需要先从哈希表中得到待删除的数字的下标，但不能直接把该数字删除。
        // 这是因为待删除的数字不一定位于数组的尾部。当数组中间的数字被删除之后，为了确保数组内存的连续性，被删除的数字后面的数字会向前移动以填补被删除的内容空缺。
        // 由于被删除的数字后面的所有数字都需要移动，因此删除的时间复杂度就是O（n）。
        // 为了避免在数组中删除数字的时候移动数据，可以把被删除的数字和数组尾部的数字交换，再删除数组最后的数字。
        // 由于被删除的数字已经位于数组的尾部，此时删除就不会引起数据移动，因此时间复杂度仍然是O（1）。
        // 函数getRandom等概率地返回数据集中的每个数字。
        // 如果数组nums的长度为n，函数random.nextInt随机生成从0到n-1的一个整数，把这个整数当作下标从数组中读取一个数字即可，只需要O（1）的时间。



        // 方法一：ArrayList+随机数相同概率返回+哈希表
        //   可变数组ArrayList中存储元素，哈希表的键key存储元素，哈希表的值value存储该元素在可变数组ArrayList中的下标。
        //   数据集是由两个基本容器组成。同一个数据存储在两个地方，利用各自的优势进行互补操作。因此操作数据在可变数组和哈希表中要同时处理。
        //   利用哈希表来快速判断是否存在，若存在可以使用哈希表快速获取在可变数组中下标。利用下标可以在可变数组中快速获取该元素，然后就可以完成插入和删除。

        private List<Integer> list;// 在数据容器RandomizedSet中，数值保存在用ArrayList实现的动态数组nums中
        // 用HashMap实现的哈希表map中存储了每个数值及其在数组nums中的下标。
        private Map<Integer, Integer> map;// 哈希表中存储每个元素及其在变长数组中的下标。哈希表中key是元素的值，value是对应元素在边长数组中的下标

        public RandomizedSet() {// 构造器初始化对象
            list = new ArrayList<>();
            map = new HashMap<>();
        }



        // bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false
        public boolean insert(int val) {
            // 在添加新的数值之前需要先判断数据集中是否已经包含该数值。
            // 如果数据集中之前已经包含该数值，则不能再添加，直接返回false即可。
            if (map.containsKey(val)) {// 插入前先判断是否已经存在在哈希表中，已经有了就不能再插入。没有的话就可以再插入。
                //
                return false;
            }else {
                // 如果之前没有该数值，则把它添加到数组list的尾部，并把它和它在数组中的下标添加到哈希表map中。
                // 同一个数据存储在两个地方，利用各自的优势进行互补操作。因此操作数据在可变数组和哈希表中要同时处理。
                // 当哈希表中没有该元素时，插入到可变数组的最后一个位置,并添加到哈希表中
                // 数组下标从0开始，所以数组元素的个数就是数组中新添加元素的位置，注意这里给哈希表和可变数组添加元素时的对应关系
                map.put(val, list.size());// 哈希表的键key存储元素，哈希表的值value存储该元素在可变数组ArrayList中的下标。
                list.add(val);//将元素插入到可变数组的最后。List中add加入数据就会加入到末尾
                return true;
            }
        }



        // bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
        // 从数组中用O（1）的时间删除一个数字要稍微麻烦一点。需要先从哈希表中得到待删除的数字的下标，但不能直接把该数字删除。
        // 这是因为待删除的数字不一定位于数组的尾部。当数组中间的数字被删除之后，为了确保数组内存的连续性，被删除的数字后面的数字会向前移动以填补被删除的内容空缺。
        // 由于被删除的数字后面的所有数字都需要移动，因此删除的时间复杂度就是O（n）。
        // 为了避免在数组中删除数字的时候移动数据，将变长数组的最后一个元素移动到待删除元素的下标处，然后删除变长数组的最后一个元素。
        // 由于被删除的数字已经位于数组的尾部，此时删除就不会引起数据移动，因此时间复杂度仍然是O（1）。
        // 且可以保证在删除操作之后变长数组中的所有元素的下标都连续，方便插入操作和获取随机元素操作。

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                // 在删除一个数值之前需要先判断数据集中是否已经包含该数值。如果数据集中没有包含该数值，则不能删除，直接返回false。
                return false;
            }else {
                // 如果数据集中已经包含该数值，就需要把它从哈希表map和数组list中删除。
                // 如果直接从可变数组list中删除元素val，那么可能可变数组已经删除的元素后面的元素数组下标会变化，但是对应的哈希表中的下标没有变化，这时会出错。
                // 删除操作的重点在于将变长数组的最后一个元素移动到待删除元素的下标处，然后删除变长数组的最后一个元素。
                // 该操作的时间复杂度是 O(1)，且可以保证在删除操作之后变长数组中的所有元素的下标都连续，方便插入操作和获取随机元素操作。
                // 为了避免在数组中删除数字的时候移动数据，可以把被删除的数字和数组尾部的数字交换，再删除数组最后的数字。

                // 当元素 val 存在时，利用哈希表快速得到待删除元素在数组中的下标
                int removeIndex = map.get(val);// 从哈希表中获取待删除元素在ArrayList中的索引
                int lastValue = list.get(list.size() - 1);//获取可变数组ArrayList中最后一个元素

                //  将变长数组ArrayList的最后一个元素移动到待删除元素的下标处，并更新对应的map集合
                list.set(removeIndex, lastValue);// 将可变数组最后位置的元素复制到待删除位置
                map.put(lastValue, removeIndex);// 哈希表中存储每个元素及其在变长数组中的下标。

                // 因为已经将可变数组最后位置的一个元素移动到了待删除位置，那么删除可变数组最后一个位置的元素不影响。
                // 在变长数组ArrayList中删除最后一个元素，在哈希表中删除 val；
                list.remove(list.size() - 1);
                map.remove(val);
                return true;
            }
        }




        // int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
        // int nextInt(int bound) 返回从该随机数生成器的序列中提取的伪随机，均匀分布的值在0（包括）和指定值（不包括）[0,指定值）之间的 int 。
        public int getRandom() {
            Random random = new Random();
            //int randomIndex = random.nextInt(list.size());//调用API
            int randomIndex = random.nextInt(map.size());//调用API
            return list.get(randomIndex);
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
