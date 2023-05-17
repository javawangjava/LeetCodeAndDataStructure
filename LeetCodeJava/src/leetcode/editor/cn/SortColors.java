/**
 * <p>给定一个包含红色、白色和蓝色、共&nbsp;<code>n</code><em> </em>个元素的数组<meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;
 * ，<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a>
 * </strong>对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。</p>
 *
 * <p>我们使用整数 <code>0</code>、&nbsp;<code>1</code> 和 <code>2</code> 分别表示红色、白色和蓝色。</p>
 *
 * <ul>
 * </ul>
 *
 * <p>必须在不使用库的sort函数的情况下解决这个问题。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,0,2,1,1,0]
 * <strong>输出：</strong>[0,0,1,1,2,2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [2,0,1]
 * <strong>输出：</strong>[0,1,2]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == nums.length</code></li>
 * <li><code>1 &lt;= n &lt;= 300</code></li>
 * <li><code>nums[i]</code> 为 <code>0</code>、<code>1</code> 或 <code>2</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>你可以不使用代码库中的排序函数来解决这道题吗？</li>
 * <li>你能想出一个仅使用常数空间的一趟扫描算法吗？</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 1315</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 75
 * 颜色分类
 *
 * @author wangweizhou
 * @date 2022-06-28 23:05:42
 */

public class SortColors {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new SortColors().new Solution();
        int[] nums = {2, 0, 1, 2, 0, 1, 1, 1, 1};
        solution.sortColors(nums);

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //public void sortColors(int[] nums) {
        //    if (nums == null || nums.length < 2) {
        //        return;
        //    }
        //    int len = nums.length;
        //    int left = 0;
        //    int right = len - 1;
        //    int index = 0;
        //    while (index <= right) {
        //        if (nums[index] == 0) {
        //            swap(nums, index, left);
        //            index++;
        //            left++;
        //        } else if (nums[index] == 1) {
        //            index++;
        //        } else if (nums[index] == 2) {
        //            swap(nums, index, right);
        //            right--;
        //        }
        //    }
        //}
        //
        //
        //private void swap(int[] nums, int i, int j) {
        //    if (nums[i] != nums[j]) {
        //        int temp = nums[i];
        //        nums[i] = nums[j];
        //        nums[j] = temp;
        //    }
        //}




        // 解法3：双指针+循环（三指针 ）写法1   和75类似
        // left左侧表示等于0的数
        // right右侧表示等于2的数

        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2

        // 根据题意，数组中0，1，2都有。
        public void sortColors(int[] nums) {
            if (nums == null || nums.length < 2) {// 题目说了至少3种颜色
                return;
            }
            int len = nums.length;
            int left = 0;// left左侧(不包含left)表示全部是0的子数组
            int right = len - 1;// right右侧(不包含right)表示全部是2的子数组
            //for (int i = left; i <= right; i++) { // i一直在[left,right]范围内
            for (int i = 0; i <= right; ) { // i一直在[left,right]范围内,因为right右边的已经排好序了
                if (nums[i] == 0) {
                    swap(nums, i, left);
                    left++;
                    i++;
                } else if (nums[i] == 1) {
                    i++;// 因为在排序1的时候不知道0在那个地方结束，2在那个地方开始。也就是不知道1在那个范围。
                } else if (nums[i] == 2) {
                    // nums[i]与nums[right]交换，那么nums[right]肯定是0或者1，
                    // 那么交换到位置i的时候，就需要判断0或者1是不是在合适的位置，也就是位置i需要再次判断，所以这里不需要（i++)
                    swap(nums, i, right);
                    right--;
                }
            }
        }


        // 交换i,j位置
        private void swap(int[] nums, int i, int j) {
            if (nums[i] != nums[j]) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }




        //// 方法一：单指针 （其实是双指针） 写法1  两遍遍历
        //// 我们可以考虑对数组进行两次遍历。在第一次遍历中，我们将数组中所有的 0 交换到数组的头部。
        //// 在第二次遍历中，我们将数组中所有的 1 交换到头部的 0 之后。此时，所有的 2都出现在数组的尾部，这样我们就完成了排序。
        //// 或者第二次遍历时，将数组中所有的2都交换到数组的尾部
        //
        //public void sortColors(int[] nums) {
        //    if (nums == null || nums.length == 0) {// 判空
        //        return;
        //    }
        //    int len = nums.length;
        //    int curr = 0;//curr表示已经排序好的部分的指针
        //    for (int i = 0; i < len; i++) {// 遍历数组[0,len-1]找到值为0的元素并交换至数组头部
        //        if (nums[i] == 0) {
        //            swap(nums, curr, i);
        //            ++curr;// 交换之后，curr指向下一个待交换的位置
        //        }
        //    }
        //    for (int i = curr; i < len; i++) {// 遍历数组[curr,len-1]找到值为0的元素并交换至数组中所有0的后面
        //        if (nums[i] == 1) {
        //            swap(nums, curr, i);
        //            ++curr;
        //        }
        //    }
        //}
        //
        //// 交换i,j位置
        //public void swap(int[] nums, int i, int j) {
        //    int temp = nums[i];
        //    nums[i] = nums[j];
        //    nums[j] = temp;
        //}




        //// 解法1：单指针 写法2  这个相当于将交换合并在方法中   两边遍历
        //public void sortColors(int[] nums) {
        //    if (nums == null || nums.length == 0) {// 判空
        //        return;
        //    }
        //    int len = nums.length;
        //    int curr = 0;// curr表示已经排序好的部分的指针
        //    for (int i = 0; i < len; i++) {// 遍历数组[0,len-1]找到值为0的元素并交换至数组头部
        //        if (nums[i] == 0) {//因为nums[i]等于0，所以这里直接交换没有使用临时变量。
        //            // 因为nums[i] == 0，所以先将nums[curr]赋值给nums[i]，然后再将0赋值给nums[curr]，这样其实就是省略了临时变量的交换。
        //            nums[i] = nums[curr];
        //            nums[curr] = 0;
        //            ++curr;// 交换之后，curr指向下一个待交换的位置
        //
        //            // 上面就相当于下面，只是nums[i] == 0,不需要再使用临时变量了。这样可以完成交换。
        //            //int temp=nums[i]=0;
        //            //nums[i]=nums[curr];
        //            //nums[curr]=temp;
        //            //curr++;
        //        }
        //    }
        //    for (int i = curr; i < len; i++) {
        //        if (nums[i] == 1) {
        //            nums[i] = nums[curr];
        //            nums[curr] = 1;
        //            ++curr;
        //        }
        //    }
        //}





        /*
        // 解法2：计数器 写法1
        public void sortColors(int[] nums) {
            if (nums == null || nums.length == 0) {
                return;
            }
            int length = nums.length;
            int zeroNum=0;
            int oneNum=0;
            for (int i = 0; i < length; i++) {
                if(nums[i]==0){
                    zeroNum++;
                }else if(nums[i]==1){
                    oneNum++;
                }
            }
            for (int i = 0; i < length; i++) {
                if(zeroNum>0){
                    nums[i]=0;
                    zeroNum--;
                }else if(oneNum>0){
                    nums[i]=1;
                    oneNum--;
                }else{
                    nums[i]=2;
                }
            }
        }
        */




        /*
        // 解法2：计数器 写法2  其实就是计数排序
        public void sortColors(int[] nums) {
            if (nums == null || nums.length == 0) {// 判空
                return;
            }
            int length = nums.length;
            int zeroNum=0;
            int oneNum=0;
            for (int i = 0; i < length; i++) {
                if(nums[i]==0){
                    zeroNum++;
                }else if(nums[i]==1){
                    oneNum++;
                }
            }
            int index=0;
            while(zeroNum>0){
                nums[index]=0;
                index++;
                zeroNum--;
            }
            while(oneNum>0){
                nums[index]=1;
                index++;
                oneNum--;
            }
            while(index<length){
                nums[index]=2;
                index++;
            }
        }
        */



    }
//leetcode submit region end(Prohibit modification and deletion)

}
