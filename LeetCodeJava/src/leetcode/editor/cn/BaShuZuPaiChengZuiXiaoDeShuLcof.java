/**
 * <p>输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> <code>[10,2]</code>
 * <strong>输出:</strong> &quot;<code>102&quot;</code></pre>
 *
 * <p><strong>示例&nbsp;2:</strong></p>
 *
 * <pre><strong>输入:</strong> <code>[3,30,34,5,9]</code>
 * <strong>输出:</strong> &quot;<code>3033459&quot;</code></pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>0 &lt; nums.length &lt;= 100</code></li>
 * </ul>
 *
 * <p><strong>说明: </strong></p>
 *
 * <ul>
 * <li>输出结果可能非常大，所以你需要返回一个字符串而不是整数</li>
 * <li>拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>贪心</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 541</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 45
 * 把数组排成最小的数
 *
 * @author wangweizhou
 * @date 2022-09-13 22:24:51
 */

public class BaShuZuPaiChengZuiXiaoDeShuLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BaShuZuPaiChengZuiXiaoDeShuLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public String minNumber(int[] nums) {
            if (nums == null || nums.length == 0) {
                return null;
            }
            String[] strs = new String[nums.length];
            // 遍历数组将整数型数组转换为字符串数组，这样字符串数组元素的字符串就可以拼接
            for (int i = 0; i < nums.length; i++) {// 遍历数组将数组值转换成对应的字符串形式
                strs[i] = String.valueOf(nums[i]);
            }
            // 调用内置排序函数
            // 按照字典序判断字符串拼接后的大小
            Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
            //// 匿名内部类实现排序比较
            //Arrays.sort(strs, new Comparator<String>() {
            //    @Override
            //    public int compare(String o1, String o2) {
            //        return (o1 + o2).compareTo(o2 + o1);
            //    }
            //});

            // 将字符串数组转换为可变字符串
            StringBuilder res = new StringBuilder();// 可变字符串
            for (String s : strs) {
                res.append(s);
            }
            return res.toString();
        }




        //// 解法1：排序
        //// 拼接数组内所有元素使结果最小，本质上是排序
        ////  此题求拼接起来的最小数字，本质上是一个排序问题。设数组 nums 中任意两数字的字符串为 x 和 y ，则规定排序判断规则为：
        ////  若拼接字符串 x+y>y+x ，则 x “大于” y ；反之，若 x+y<y+x ，则 x “小于” y ；
        ////  x “小于” y 代表：排序完成后，数组中 x 应在 y 左边；“大于” 则反之。

        //public String minNumber(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return null;
        //    }
        //    String[] strs = new String[nums.length];
        //    // 遍历数组将整数型数组转换为字符串数组，这样字符串数组元素的字符串就可以拼接
        //    for (int i = 0; i < nums.length; i++) {// 遍历数组将数组值转换成对应的字符串形式
        //        strs[i] = String.valueOf(nums[i]);
        //    }
        //    // 调用内置排序函数
        //    // 按照字典序判断字符串拼接后的大小。（x + y）表示字符串拼接
        //    Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));//Lambda表达式
        //    //// 匿名内部类实现排序比较
        //    //Arrays.sort(strs, new Comparator<String>() {
        //    //    @Override
        //    //    public int compare(String o1, String o2) {
        //    //        return (o1 + o2).compareTo(o2 + o1);
        //    //    }
        //    //});
        //
        //    // 将字符串数组转换为可变字符串
        //    StringBuilder res = new StringBuilder();// 可变字符串
        //    for (String s : strs) {
        //        res.append(s);
        //    }
        //    return res.toString();
        //}





    /*
    //	 解法2：小根堆排序

	public String minNumber(int[] nums) {
		Queue<String> queue = new PriorityQueue<>(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				//字典序列小的放在堆顶
				return (o1 + o2).compareTo(o2 + o1);
			}
		});
		for (int num : nums) {// 将数组元素转换成字符串并存储到队列中
			queue.add("" + num);
		}
		// 将队列元素注意连接到可变字符串后面
		StringBuilder res = new StringBuilder();
		while (! queue.isEmpty()){
			res.append(queue.poll());
		}
		return res.toString();
	}

	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
