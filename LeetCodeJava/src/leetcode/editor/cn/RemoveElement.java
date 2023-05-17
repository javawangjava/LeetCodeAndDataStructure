/**
 * <p>给你一个数组 <code>nums</code><em> </em>和一个值 <code>val</code>，你需要 <strong>
 * <a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a>
 * </strong> 移除所有数值等于 <code>val</code><em> </em>的元素，并返回移除后数组的新长度。</p>
 *
 * <p>不要使用额外的数组空间，你必须仅使用 <code>O(1)</code> 额外空间并 <strong>
 * <a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地 </a>修改输入数组
 * </strong>。</p>
 *
 * <p>元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。</p>
 *
 * <p> </p>
 *
 * <p><strong>说明:</strong></p>
 *
 * <p>为什么返回数值是整数，但输出的答案是数组呢?</p>
 *
 * <p>请注意，输入数组是以<strong>「引用」</strong>方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。</p>
 *
 * <p>你可以想象内部操作如下:</p>
 *
 * <pre>
 * // <strong>nums</strong> 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
 * int len = removeElement(nums, val);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中<strong> 该长度范围内</strong> 的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,2,2,3], val = 3
 * <strong>输出：</strong>2, nums = [2,2]
 * <strong>解释：</strong>函数应该返回新的长度 <strong>2</strong>, 并且 nums<em> </em>中的前两个元素均为 <strong>2</strong>。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1,2,2,3,0,4,2], val = 2
 * <strong>输出：</strong>5, nums = [0,1,4,0,3]
 * <strong>解释：</strong>函数应该返回新的长度 <strong><code>5</code></strong>, 并且 nums 中的前五个元素为 <strong><code>0</code></strong>, <strong><code>1</code></strong>, <strong><code>3</code></strong>, <strong><code>0</code></strong>, <strong>4</strong>。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= nums.length <= 100</code></li>
 * <li><code>0 <= nums[i] <= 50</code></li>
 * <li><code>0 <= val <= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 1365</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 移除元素
 */
public class RemoveElement {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RemoveElement().new Solution();

        //int[] nums = {3, 2, 2, 3};
        //int ans = solution.removeElement(nums, 3);
        //
        //for (int i = 0; i < ans; i++) {
        //    System.out.println(nums[i]);
        //}
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //public int removeElement(int[] nums, int val) {
        //    if (nums == null || nums.length == 0) {
        //        return 0;
        //    }
        //    int length = nums.length;
        //    int left = 0;
        //    int right = 0;
        //
        //    while(right<length){
        //        while(nums[right]==val&&right<length){
        //            right++;
        //        }
        //
        //    }
        //
        //
        //
        //
        //}


        // 方法1：快慢指针

        // 右指针 right 指向当前将要处理的元素，左指针 left 指向下一个将要赋值的位置。
        // right指向第一个不等于val的指针
        // left左侧[0,left)表示已经移除元素的数组，right右侧(right,length-1]表示还没有遍历的数组，
        // [left,right-1]表示其中的数值等于val.
        // 如果右指针指向的元素不等于 val，它一定是输出数组的一个元素，我们就将右指针指向的元素复制到左指针位置，然后将左右指针同时右移；
        // 如果右指针指向的元素等于 val，它不能在输出数组里，此时左指针不动，右指针右移一位。
        // 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。即左指针左边均为非零数；右指针左边直到左指针处均为零。
        // 右指针不断向右移动，每次右指针指向不等于val的数，则每次将right指向的数赋值给left指向的数，同时左指针右移。


/*
        public int removeElement(int[] nums, int val) {
            if(nums==null){
                return -1;
            }
            if(nums.length==0){
                return 0;
            }
            int left=0;
            int right=0;
            int length=nums.length;
            while(right<length){
                if(nums[right]!=val){
                    nums[left++]=nums[right];
                }
                right++;
            }
            return left;
        }*/




      /*  // 方法2：双指针，从两侧开始
        // 如果左指针 left 指向的元素等于 val，此时将右指针 right 指向的元素复制到左指针 left 的位置，然后右指针 right 左移一位。
        // 这时候左指针不右移，因为不知道移动过来的值是不是等于val.
        // 当左指针 left 指向的元素不等于val时，此时将左指针 left 右移。
        // 最终当left>right时结束循环。这是left指向已经去重后的结果数组最后一个元素。

        public int removeElement(int[] nums, int val) {
            if (nums == null) {
                return -1;
            }
            if (nums.length == 0) {
                return 0;
            }
            int length = nums.length;
            int left = 0;
            int right = length - 1;
            while (left <= right) {
                if (nums[left] == val) {
                    // 左指针 left 指向的元素等于 val，此时将右指针 right 指向的元素复制到左指针 left 的位置，然后右指针 right 左移一位
                    // 注意左指针没有移动
                    nums[left] = nums[right];
                    right--;
                } else {
                    left++;
                }
            }
            return left;
        }
        */
    }
//leetcode submit region end(Prohibit modification and deletion)

}
