/**
 * <p>给定一个已按照<strong><em> </em>升序排列&nbsp; </strong>的整数数组&nbsp;<code>numbers</code> ，请你从数组中找出两个数满足相加之和等于目标数&nbsp;
 * <code>target</code> 。</p>
 *
 * <p>函数应该以长度为 <code>2</code> 的整数数组的形式返回这两个数的下标值<em>。</em><code>numbers</code> 的下标 <strong>从 0&nbsp;开始计数</strong>
 * ，所以答案数组应当满足 <code>0&nbsp;&lt;= answer[0] &lt; answer[1] &lt;&nbsp;numbers.length</code>&nbsp;。</p>
 *
 * <p>假设数组中存在且只存在一对符合条件的数字，同时一个数字不能使用两次。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numbers = [1,2,4,6,10], target = 8
 * <strong>输出：</strong>[1,3]
 * <strong>解释：</strong>2 与 6 之和等于目标数 8 。因此 index1 = 1, index2 = 3 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numbers = [2,3,4], target = 6
 * <strong>输出：</strong>[0,2]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numbers = [-1,0], target = -1
 * <strong>输出：</strong>[0,1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>2 &lt;= numbers.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>-1000 &lt;= numbers[i] &lt;= 1000</code></li>
 * <li><code>numbers</code> 按 <strong>递增顺序</strong> 排列</li>
 * <li><code>-1000 &lt;= target &lt;= 1000</code></li>
 * <li>仅存在一个有效答案</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p>注意：本题与主站 167 题相似（下标起点不同）：
 * <a href="https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/">https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/</a></p>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>二分查找</li></div></div><br><div><li>👍 37</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 排序数组中两个数字之和
 */
public class KLl5u1 {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new KLl5u1().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //	解法3：双指针 时间复杂度是O（n）
        public int[] twoSum(int[] numbers, int target) {

            if (numbers == null || numbers.length <= 1) {
                return new int[]{-1,-1};
            }
            int[] ans = {-1, -1};
            int length = numbers.length;

            int left = 0;
            int right = length - 1;
            while (left <= right) {
                int sum = numbers[left] + numbers[right];
                if (sum == target) {
                    ans[0] = left;
                    ans[1] = right;
                    return ans;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
            return ans;
        }


        //	解法1：双循环,  时间复杂度是O（n^2）
   /*
   public int[] twoSum(int[] numbers, int target) {
		int length=numbers.length;
		int[] ans={-1,-1};
		if(numbers==null||length<=1){
			return ans;
		}
		for (int i = 0; i < length-1; i++) {
			for (int j = i+1; j < length; j++) {
				if(numbers[i]+numbers[j]==target){
					ans[0]=i;
					ans[1]=j;
					return ans;
				}
			}
		}
		return ans;
    }
    */


//	解法2：有序数组可以用二分查找找第二个数,时间复杂度是O（nlogn）
/*
	public int[] twoSum(int[] numbers, int target) {
		int length=numbers.length;
		int[] ans={-1,-1};
		if(numbers==null||length<=1){
			return ans;
		}
		for (int i = 0; i < length-1; i++) {
			int temp=binarySearch(numbers,i+1,target-numbers[i]);
			//这里要注意temp可能返回的是-1，所以首先要保证数据有效性用的短路与，temp!=-1必须先执行
			if(temp!=-1&&target-numbers[i]==numbers[temp]){
				ans[0]=i;
				ans[1]=temp;
				return ans;
			}
		}
		return ans;
	}

	private int binarySearch(int[] nums,int start,int target){
		int length=nums.length;
		int left=start;
		int right=length-1;
		int mid;
		while(left<=right){
			mid=(left+right)/2;
			if(nums[mid]==target){
				return mid;
			}else if(nums[mid]<target){
				left=mid+1;
			}else{
				right=mid-1;
			}
		}
		return -1;
	}
	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
