/**
 * <p>设计一个接收整数流的数据结构，该数据结构支持检查是否存在两数之和等于特定值。</p>
 *
 * <p>实现 <code>TwoSum</code> 类：</p>
 *
 * <ul>
 * <li><code>TwoSum()</code> 使用空数组初始化 <code>TwoSum</code> 对象</li>
 * <li><code>void add(int number)</code> 向数据结构添加一个数 <code>number</code></li>
 * <li><code>boolean find(int value)</code> 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 <code>true</code> ；否则，返回
 * <code>false</code> 。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["TwoSum", "add", "add", "add", "find", "find"]
 * [[], [1], [3], [5], [4], [7]]
 * <strong>输出：</strong>
 * [null, null, null, null, true, false]
 *
 * <strong>解释：</strong>
 * TwoSum twoSum = new TwoSum();
 * twoSum.add(1);   // [] --&gt; [1]
 * twoSum.add(3);   // [1] --&gt; [1,3]
 * twoSum.add(5);   // [1,3] --&gt; [1,3,5]
 * twoSum.find(4);  // 1 + 3 = 4，返回 true
 * twoSum.find(7);  // 没有两个整数加起来等于 7 ，返回 false</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-10<sup>5</sup> &lt;= number &lt;= 10<sup>5</sup></code></li>
 * <li><code>-2<sup>31</sup> &lt;= value &lt;= 2<sup>31</sup> - 1</code></li>
 * <li>最多调用 <code>10<sup>4</sup></code> 次 <code>add</code> 和 <code>find</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>数组</li><li>哈希表</li><li>双指针</li><li>数据流</li></div></div><br><div
 * ><li>👍 76</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 170
 * 两数之和 III - 数据结构设计
 *
 * @author wangweizhou
 * @date 2022-07-06 15:51:21
 */
public class TwoSumIiiDataStructureDesign {
    public static void main(String[] args) {
        //测试代码
        TwoSum solution = new TwoSumIiiDataStructureDesign().new TwoSum();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class TwoSum {

        // 方法二：哈希表   哈希表的键key表示数值，值value表示值的出现次数
        // 用哈希表存储值到索引的映射关系。给定一个目标值 S，对于每个数字 a，我们只需要验证哈希表中是否存在 S - a。

        private HashMap<Integer, Integer> num_counts;


        public TwoSum() {//构造器使用空的HashMap来初始化TwoSum 对象
            this.num_counts = new HashMap<Integer, Integer>();
        }

        // 在 add(number) 函数中：在哈希表中添加 number 到 number 频数之间的映射关系。
        public void add(int number) {
            if (this.num_counts.containsKey(number)) {//当hashmap中有该数值时，修改出现的次数
                this.num_counts.replace(number, this.num_counts.get(number) + 1);
            } else {
                this.num_counts.put(number, 1);
            }
        }

        // 在 find(value) 函数中：遍历哈希表，对于每个键值（number），我们检查哈希表中是否存在 value - number。
		// 如果存在，我们终止循环并返回结果。

        public boolean find(int value) {
			// static interface  Map.Entry<K,V> 映射条目（键值对）。
			// Set<Map.Entry<K,V>> entrySet() 返回此映射中包含的映射的Set视图。

            for (Map.Entry<Integer, Integer> entry : this.num_counts.entrySet()) {//foreach循环的格式
                int complement = value - entry.getKey();
                if (complement != entry.getKey()) {//差值在哈希表中是唯一的
                    if (this.num_counts.containsKey(complement)) {//差值是唯一的，且哈希表中有该差值
                        return true;
                    }
                } else {//差值在哈希表中不是唯一的
                    if (entry.getValue() > 1) {//差值在哈希表中不是唯一的，则至少应该有两个
                        return true;
                    }
                }
            }
            return false;
        }





/*

	//	方法1：将输入的数字全部存放到列表中，再排序使用二分查找

	private ArrayList<Integer> nums;
	private boolean is_sorted;//标志位

	// TwoSum() 使用空数组初始化 TwoSum 对象
    public TwoSum() {//构造器
		this.nums = new ArrayList<Integer>();
		this.is_sorted = false;
    }

    //void add(int number) 向数据结构添加一个数 number
    public void add(int number) {
		this.nums.add(number);
		this.is_sorted = false;//只要每次添加一个数据，就要重置标志位为false.新加入的数字可能会使得ArrayList无序
    }

    //boolean find(int value) 寻找数据结构中是否存在一对整数，使得两数之和与给定的值相等。如果存在，返回 true ；否则，返回 false
    public boolean find(int value) {
		// 在使用二分查找的时候首先要排序
		if (!this.is_sorted) {//ArrayList没排序，则排序，并修改标志位
			Collections.sort(this.nums);
			this.is_sorted = true;
		}
		// 排序后二分查找
		int low = 0, high = this.nums.size() - 1;
		while (low < high) {
			int twosum = this.nums.get(low) + this.nums.get(high);
			if (twosum < value)
				low += 1;
			else if (twosum > value)
				high -= 1;
			else
				return true;
		}
		return false;
    }
	*/

    }

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
