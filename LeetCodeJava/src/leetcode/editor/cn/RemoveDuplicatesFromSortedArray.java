/**
 * <p>给你一个 <strong>升序排列</strong> 的数组 <code>nums</code> ，请你<strong>
 * <a href="http://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a>
 * </strong> 删除重复出现的元素，使每个元素 <strong>只出现一次</strong> ，返回删除后数组的新长度。元素的 <strong>相对顺序</strong> 应该保持 <strong>一致</strong>
 * 。</p>
 *
 * <p>由于在某些语言中不能改变数组的长度，所以必须将结果放在数组nums的第一部分。更规范地说，如果在删除重复项之后有 <code>k</code> 个元素，那么&nbsp;<code>nums</code>&nbsp;的前
 * <code>k</code> 个元素应该保存最终结果。</p>
 *
 * <p>将最终结果插入&nbsp;<code>nums</code> 的前 <code>k</code> 个位置后返回 <code>k</code> 。</p>
 *
 * <p>不要使用额外的空间，你必须在 <strong>
 * <a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地 </a>修改输入数组
 * </strong>并在使用 O(1) 额外空间的条件下完成。</p>
 *
 * <p><strong>判题标准:</strong></p>
 *
 * <p>系统会用下面的代码来测试你的题解:</p>
 *
 * <pre>
 * int[] nums = [...]; // 输入数组
 * int[] expectedNums = [...]; // 长度正确的期望答案
 *
 * int k = removeDuplicates(nums); // 调用
 *
 * assert k == expectedNums.length;
 * for (int i = 0; i &lt; k; i++) {
 * assert nums[i] == expectedNums[i];
 * }</pre>
 *
 * <p>如果所有断言都通过，那么您的题解将被 <strong>通过</strong>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,1,2]
 * <strong>输出：</strong>2, nums = [1,2,_]
 * <strong>解释：</strong>函数应该返回新的长度 <strong><code>2</code></strong> ，并且原数组 <em>nums </em>的前两个元素被修改为 <strong><code>1</code></strong>, <strong><code>2 </code></strong><code>。</code>不需要考虑数组中超出新长度后面的元素。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,0,1,1,1,2,2,3,3,4]
 * <strong>输出：</strong>5, nums = [0,1,2,3,4]
 * <strong>解释：</strong>函数应该返回新的长度 <strong><code>5</code></strong> ， 并且原数组 <em>nums </em>的前五个元素被修改为 <strong><code>0</code></strong>, <strong><code>1</code></strong>, <strong><code>2</code></strong>, <strong><code>3</code></strong>, <strong><code>4</code></strong> 。不需要考虑数组中超出新长度后面的元素。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
 * <li><code>nums</code> 已按 <strong>升序</strong> 排列</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 2682</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 删除有序数组中的重复项
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RemoveDuplicatesFromSortedArray().new Solution();


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 解法1：双指针：快慢指针  下面几种写法的实现细节有所不同
        // slow 表示已经去重的数组的最后一个元素，fast 用来遍历整个数组。
        // 让慢指针 slow 走在后面，快指针 fast 走在前面探路，找到一个不重复的元素就告诉 slow 并让 slow 前进一步。
        // 这样当 fast 指针遍历完整个数组 nums 后，nums[0..slow]就是不重复元素。
        //  首先注意数组是有序的，那么重复的元素一定会相邻。要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。
        //  当fast指向slow的后一个元素时，那么就比较fast和（fast-1）的前一位是否相同，相同的话那么fast后移，
        //  不相同的话，将fast指向的元素赋值给（slow+1）指向的元素。

/*
        public int removeDuplicates(int[] nums) {
            if(nums==null){
                return -1;
            }
            if(nums.length==0){
                return 0;
            }
            int length=nums.length;

            //注意这里细节，这里left从0开始的，和right从1开始的
            // right指向与已经去重数组不同的第一个元素
            int left=0;
            int right=1;

            while(right<length){// 右指针遍历整个数组
             //  首先注意数组是有序的，那么重复的元素一定会相邻。要求删除重复元素，实际上就是将不重复的元素移到数组的左侧。
             //  那么就比较right和（right-1）的前一位是否相同，相同的话那么right后移，不相同的话，将right指向的元素赋值给（left+1）指向的元素
              if(nums[right-1]!=nums[right]){
                  nums[left+1]=nums[right];
                  left++;
              }
                right++;
            }
            return left+1;
        }*/



        public int removeDuplicates(int[] nums) {
            if (nums == null) {
                return -1;
            }
            int length = nums.length;
            if (length == 0) {
                return length;
            }

            //注意这里细节，这里left和right都是从0开始的
            int left = 0;
            int right = 0;
            while (right < length) { // 右指针遍历整个数组
                // slow 表示已经去重的数组的最后一个元素，fast 用来遍历整个数组。

                // 这里是因为left始终保存着相同数的第一个，那就说明[left,right)之间是相同的数
                if (nums[left] != nums[right]) {// 这里是因为left始终保存着相同数的第一个，那就说明[left,right)之间是相同的数
                    if (right - left > 1) {// 当两个指针不是紧挨的.相同元素只保留一个，其余删除
                        nums[left + 1] = nums[right];
                    }
                    left++;// left后移至下一个与nums[left]不同的位置
                }
                right++;//right 是遍历数组的，所以一直需要后移
            }
            return left + 1;//这里left指向已经去重数组的最后一个元素，但是数组是下标是从0开始的，所以去重后的数组新长度是left+1.
        }




/*
        public int removeDuplicates(int[] nums) {
            if (nums == null) {
                return -1;
            }
            int length = nums.length;
            if (length == 0) {
                return length;
            }
            //注意这里细节，这里left和right都是从0开始的
            int left = 0;
            int right = 0;
            while (right < length) { // 右指针遍历整个数组
                // slow 表示已经去重的数组的最后一个元素，fast 用来遍历整个数组。
                // 这里是因为left始终保存着相同数的第一个，那就说明[left,right)之间是相同的数
                if (nums[left] != nums[right]) {//当right指向的数组值不等于left指向的数组值时
                    // 方式1：left 先后移，然后再将right指向的元素赋值给left
                    nums[++left] = nums[right];

                    *//*
                    // 方式2：和方式1效果一样。先将right指向的元素赋值给（left+1），然后将left后移
                    nums[left + 1] = nums[right];
                    left++;
                    *//*
                }
                right++;//right 是遍历数组的，所以一直需要后移
            }
            return left + 1;//这里left指向已经去重数组的最后一个元素，但是数组是下标是从0开始的，所以去重后的数组新长度是left+1.
        }

        */

/*
        // 写法2：注意几个写法的细节区别，思路基本是一样的，实现细节有差异
        public int removeDuplicates(int[] nums) {
            if (nums==null) {
                return -1;
            }
            int length = nums.length;
            if (length <=1) {
                return length;
            }
            int left = 1;
            int right = 1;
            while (right < length) {// 右指针遍历整个数组
                if (nums[left-1] != nums[right]) {// 这里是因为left始终保存着相同数的第一个，那就说明[left,right)之间是相同的数
                    nums[left] = nums[right];
                    left++;// left后移至下一个与nums[left]不同的位置
                }
                right++;
            }
            return left;//left从0开始，所以长度为（left+1）,结束的时候left指向删除完的数组的最后一个元素。right指向了原数组末尾的下一位，越界了
        }

        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
