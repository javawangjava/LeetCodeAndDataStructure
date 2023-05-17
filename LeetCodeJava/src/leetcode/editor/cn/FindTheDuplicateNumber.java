/**
 * <p>给定一个包含&nbsp;<code>n + 1</code> 个整数的数组&nbsp;<code>nums</code> ，其数字都在&nbsp;<code>[1, n]</code>&nbsp;范围内（包括
 * <code>1</code> 和 <code>n</code>），可知至少存在一个重复的整数。</p>
 *
 * <p>假设 <code>nums</code> 只有 <strong>一个重复的整数</strong> ，返回&nbsp;<strong>这个重复的数</strong> 。</p>
 *
 * <p>你设计的解决方案必须 <strong>不修改</strong> 数组 <code>nums</code> 且只用常量级 <code>O(1)</code> 的额外空间。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,3,4,2,2]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,1,3,4,2]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
 * <li><code>nums.length == n + 1</code></li>
 * <li><code>1 &lt;= nums[i] &lt;= n</code></li>
 * <li><code>nums</code> 中 <strong>只有一个整数</strong> 出现 <strong>两次或多次</strong> ，其余整数均只出现 <strong>一次</strong></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>进阶：</b></p>
 *
 * <ul>
 * <li>如何证明 <code>nums</code> 中至少存在一个重复的数字?</li>
 * <li>你可以设计一个线性级时间复杂度 <code>O(n)</code> 的解决方案吗？</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li><li>双指针</li><li>二分查找</li></div></div><br><div><li>👍
 * 1790</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 寻找重复数
 *
 * @author wangweizhou
 * @date 2022-06-24 21:28:08
 */

public class FindTheDuplicateNumber {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FindTheDuplicateNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //  从理论上讲，数组中如果有重复的数，那么就会产生多对一的映射，这样，形成的链表就一定会有环路了，
        //  综上
        //  1.数组中有一个重复的整数 <==> 链表中存在环
        //  2.找到数组中的重复整数 <==> 找到链表的环入口

        //其实，快慢指针法，就是一种映射操作，链表里面的一次映射操作，就是求next，且将位置更新到这里；数组这里，就是根据下标i求nums[i]这个元素值，且将下标更新到这里。
        //链表里面有环，即一个节点被不同的节点指向（映射）；而这里说的数组有环，即数组中的一个元素值被不同的index指向（映射）；所以，求解方法一样可以使用快慢指针法。


        // 解法1：快慢指针
        public int findDuplicate(int[] nums) {
            int slow = 0;
            int fast = 0;

            slow = nums[slow];
            fast = nums[nums[fast]];
            while (slow != fast) {
                slow = nums[slow];//慢指针每次移动一步
                fast = nums[nums[fast]];//快指针每次移动两步
            }

            slow = 0;//慢指针移动到起点
            while (slow != fast) {
                //快慢指针距离环形入口距离相等
                slow = nums[slow];//这里注意快慢指针都每次移动一步，
                fast = nums[fast];
            }
            return slow;
        }



/*
        //解法2：双循环遍历，超时
        public int findDuplicate(int[] nums) {
            int length = nums.length;
            for (int left = 0; left < length - 1; left++) {
                for (int right = left + 1; right < length; right++) {
                    if (nums[left] == nums[right]) {
                        return nums[left];
                    }
                }
            }
            return -1;
        }

        */
    }
//leetcode submit region end(Prohibit modification and deletion)

}
