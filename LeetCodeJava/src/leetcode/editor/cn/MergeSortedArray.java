/**
 * <p>给你两个按 <strong>非递减顺序</strong> 排列的整数数组&nbsp;<code>nums1</code><em> </em>和 <code>nums2</code>，另有两个整数
 * <code>m</code> 和 <code>n</code> ，分别表示 <code>nums1</code> 和 <code>nums2</code> 中的元素数目。</p>
 *
 * <p>请你 <strong>合并</strong> <code>nums2</code><em> </em>到 <code>nums1</code> 中，使合并后的数组同样按 <strong>非递减顺序</strong>
 * 排列。</p>
 *
 * <p><strong>注意：</strong>最终，合并后数组不应由函数返回，而是存储在数组 <code>nums1</code> 中。为了应对这种情况，<code>nums1</code> 的初始长度为 <code>m +
 * n</code>，其中前 <code>m</code> 个元素表示应合并的元素，后 <code>n</code> 个元素为 <code>0</code> ，应忽略。<code>nums2</code> 的长度为
 * <code>n</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * <strong>输出：</strong>[1,2,2,3,5,6]
 * <strong>解释：</strong>需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [<em><strong>1</strong></em>,<em><strong>2</strong></em>,2,<em><strong>3</strong></em>,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [1], m = 1, nums2 = [], n = 0
 * <strong>输出：</strong>[1]
 * <strong>解释：</strong>需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums1 = [0], m = 0, nums2 = [1], n = 1
 * <strong>输出：</strong>[1]
 * <strong>解释：</strong>需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>nums1.length == m + n</code></li>
 * <li><code>nums2.length == n</code></li>
 * <li><code>0 &lt;= m, n &lt;= 200</code></li>
 * <li><code>1 &lt;= m + n &lt;= 200</code></li>
 * <li><code>-10<sup>9</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你可以设计实现一个时间复杂度为 <code>O(m + n)</code> 的算法解决此问题吗？</p>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 1459</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * 合并两个有序数组
 *
 * @author wangweizhou
 * @date 2022-06-27 20:15:58
 */

public class MergeSortedArray {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MergeSortedArray().new Solution();
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 5, 6};
        int m = 0;
        int n = 3;
        solution.merge(nums1, m, nums2, n);
        for (int i = 0; i < m + n; i++) {
            System.out.println(nums1[i]);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法2：三指针  直接存储在nums1数组中
        // 从后向前合并两个数组
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int curr1=m-1;//指向数组A的结尾
            int curr2=n-1;//指向数组B的结尾
            int curr=m+n-1;//指向数组A空间的结尾处
            while(curr1>=0&&curr2>=0){//从两个数组最⼤的元素开始，直到某⼀个数组遍历完
                //将较⼤的元素放到数组A空间的结尾处
                if(nums1[curr1]>nums2[curr2]){
                    nums1[curr]=nums1[curr1];
                    curr--;
                    curr1--;
                }else{
                    nums1[curr]=nums2[curr2];
                    curr--;
                    curr2--;
                }
            }
            if(curr1<0){ //数组A遍历完了，数组B还有，则还需要添加到数组A前⾯
               while(curr2>=0){
                   nums1[curr]=nums2[curr2];
                   curr--;
                   curr2--;
               }
            }
            //数组B遍历完了，数组A前⾯剩下的刚好是，不⽤再添加
        }




        /*

        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int curr1 = m - 1;
            int curr2 = n - 1;
            int curr = m + n - 1;
            while (curr >= 0) {//数组A空间没有遍历完
                if(curr1==-1){//数组nums1有效元素已经完了
                    nums1[curr--]=nums2[curr2--];
                }else if(curr2==-1){////数组B遍历完了，数组A前⾯正好有，不⽤再添加
                    break;
                }else if(nums1[curr1]<nums2[curr2]){// 将两数组元素进行比较，将较⼤的元素放到最后
                    nums1[curr--]=nums2[curr2--];
                }else if(nums1[curr1]>=nums2[curr2]){
                    nums1[curr--]=nums1[curr1--];
                }
            }
        }
        */





        //	解法1的写法2：新建一个数组保存合并后的数组然后再复制到nums1中

       /*
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] arr=new int[m+n];
            int curr1=0;
            int curr2=0;
            int index=0;
            while(curr1<m&&curr2<n){
                if(nums1[curr1]<nums2[curr2]){
                    arr[index++]=nums1[curr1++];
                }else{
                    arr[index++]=nums2[curr2++];
                }
            }
            while(curr1==m&&curr2<n){
                arr[index++]=nums2[curr2++];
            }

            while(curr2==n&&curr1<m){
                arr[index++]=nums1[curr1++];
            }
            //将排序好的数组复制到nums1中
            for (int i = 0; i < m+n; i++) {
                nums1[i]=arr[i];
            }
        }*/



        //	解法1：新建一个数组保存合并后的数组然后再复制到nums1中
       /*
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            int[] arr=new int[m+n];
            int curr1=0;// curr1遍历数组nums1
            int curr2=0;// curr2遍历数组nums2
            int index=0;// index遍历数组arr
            while(curr1<m||curr2<n){// 这么处理两个数组就可以遍历完
                if(curr1==m){//数组nums1有效元素已经完了
                    arr[index++]=nums2[curr2++];
                }else if(curr2==n){//数组nums2有效元素已经完了
                    arr[index++]=nums1[curr1++];
                }else if(nums1[curr1]<nums2[curr2]){// 将两数组元素进行比较，将较小的元素放入新建数组中
                    arr[index++]=nums1[curr1++];
                }else if(nums1[curr1]>=nums2[curr2]){
                    arr[index++]=nums2[curr2++];
                }
            }
            //将排序好的数组复制到nums1中
            for (int i = 0; i < m+n; i++) {
                nums1[i]=arr[i];
            }
        }*/




        //  解法3：直接合并后排序
        //  最直观的方法是先将数组nums2放进数组nums1的尾部，然后直接对整个数组进行排序。
        /*
        public void merge(int[] nums1, int m, int[] nums2, int n) {
            for (int i = 0; i != n; ++i) {
                nums1[m + i] = nums2[i];
            }
            Arrays.sort(nums1);
        }*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}
