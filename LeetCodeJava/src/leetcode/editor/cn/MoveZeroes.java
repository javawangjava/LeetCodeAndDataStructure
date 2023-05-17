/**
 * <p>给定一个数组 <code>nums</code>，编写一个函数将所有 <code>0</code> 移动到数组的末尾，同时保持非零元素的相对顺序。</p>
 *
 * <p><strong>请注意</strong>&nbsp;，必须在不复制数组的情况下原地对数组进行操作。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = <code>[0,1,0,3,12]</code>
 * <strong>输出:</strong> <code>[1,3,12,0,0]</code>
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> nums = <code>[0]</code>
 * <strong>输出:</strong> <code>[0]</code></pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示</strong>:</p>
 * <meta charset="UTF-8" />
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>-2<sup>31</sup>&nbsp;&lt;= nums[i] &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><b>进阶：</b>你能尽量减少完成的操作次数吗？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li></div></div><br><div><li>👍 1631</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 283
 * 移动零
 *
 * @author wangweizhou
 * @date 2022-06-25 01:00:23
 */

public class MoveZeroes {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new MoveZeroes().new Solution();
        int[] nums = {1, 2, 3, 4};
        solution.moveZeroes(nums);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public void moveZeroes(int[] nums) {
            if(nums==null||nums.length==0){
                return;
            }
            int len=nums.length;
            int left=0;// left左侧(不包含left)表示全部是0的子数组
            int index=0;// index用来遍历数组nums
            while (index<len){
                if(nums[index]==0){
                    swap(nums,index,left);
                    index++;
                    left++;
                }else {
                    index++;
                }
            }
        }


        private void swap(int[] nums, int i, int j){
            if(nums[i]!=nums[j]){
                int temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
            }
        }

        // 非零元素和其前面的零元素交换
        // right用来遍历整个数组，[0,left)也就是[0,left-1]全部用来存储非0的数。
        // 右指针左边直到左指针处均为零。
        // 右指针不断向右移动，每次右指针指向非零数，则每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变，同时左指针右移。
        // 若第一个数为0，好理解。若第一个数不为0，则是自己和自己交换。

        // 方法1：双指针 【快慢指针】 写法1没有简化代码的时候。好理解
        // right用来遍历整个数组，[0,left)也就是[0,left-1]全部用来存储非0的数。
        //public void moveZeroes(int[] nums) {
        //    if (nums == null || nums.length == 0) {
        //        return;
        //    }
        //    int length = nums.length;
        //    int left = 0;// 指向数组nums中非0元素后面的第一个0元素。
        //    int right = 0;// right指向数组nums中第一个非0元素
        //    while (right < length) {
        //        if(nums[right]==0){
        //            right++;
        //        }else{//遇到非0元素，则和前面的0元素交换位置
        //            int temp=nums[left];
        //            nums[left]=nums[right];
        //            nums[right]=temp;
        //            left++;
        //            right++;
        //        }
        //    }
        //}




        /*

        // 解法1：双指针   写法2


        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int length = nums.length;
            int left = 0;
            int right = 0;
            while (right < length) {
                if (nums[right] != 0) {
                    swap(nums, left, right);
                    left++;
                }
                right++;
            }
        }

        public void swap(int[] nums, int left, int right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }

        */






/*
        // 方法2：双指针  写法1// 把后面的非0元素往前移动，最后把后面已经移动的元素置0.
        // right用来遍历整个数组，[0,left)也就是[0,left-1]全部用来存储非0的数。

        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int length = nums.length;
            int left = 0;
            int right = 0;
            while (right < length) {//right用来遍历整个数组
                if (nums[right] != 0) {
                    nums[left] = nums[right];//把非零元素移动到前面，
                    left++;//指向下一个待替换的位置
                }
                right++;
            }
            // 上面循环结束，left左侧全部是数组中的非0数，left及其后面的位置要设置为0
            while (left < length) {
                nums[left] = 0;
                left++;
            }
        }
        */




     /*
        // 方法2：双指针  写法1没有简化代码的时候。好理解
          // right用来遍历整个数组，[0,left)也就是[0,left-1]全部用来存储非0的数。
        public void moveZeroes(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int length = nums.length;
            int left = 0;
            int right = 0;
            while (right < length) {
                if(nums[right]==0){
                    right++;
                }else{
                    nums[left]=nums[right];
                    left++;
                    right++;
                }
            }
            while(left<length){
                nums[left]=0;
                left++;
            }
        }
        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
