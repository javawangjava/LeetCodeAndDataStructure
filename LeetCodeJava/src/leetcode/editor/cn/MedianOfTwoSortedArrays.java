/**
<p>给定两个大小分别为 <code>m</code> 和 <code>n</code> 的正序（从小到大）数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code>。请你找出并返回这两个正序数组的 <strong>中位数</strong> 。</p>

<p>算法的时间复杂度应该为 <code>O(log (m+n))</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,3], nums2 = [2]
<strong>输出：</strong>2.00000
<strong>解释：</strong>合并数组 = [1,2,3] ，中位数 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2], nums2 = [3,4]
<strong>输出：</strong>2.50000
<strong>解释：</strong>合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
</pre>

<p>&nbsp;</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums1.length == m</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m &lt;= 1000</code></li>
	<li><code>0 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= m + n &lt;= 2000</code></li>
	<li><code>-10<sup>6</sup> &lt;= nums1[i], nums2[i] &lt;= 10<sup>6</sup></code></li>
</ul>
<div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>分治</li></div></div><br><div><li>👍 5792</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

/**
 * 4
 * 寻找两个正序数组的中位数
 * @author wangweizhou
 * @date 2022-08-31 21:05:25
 */

public class MedianOfTwoSortedArrays{

	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new MedianOfTwoSortedArrays().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {


        // 解法1：直接将两个有序数组合并，再找出合并后的数字的中位数
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1 == null && nums2 == null) {
                return Integer.MAX_VALUE;
            }
            int length1 = nums1.length;
            int length2 = nums2.length;
            int[] arr = new int[length1 + length2];//定义一个数组存储合并后的有序数组
            //有序数组nums1是空数组，则有序数组nums2就是合并后的数组
            if (length1 == 0) {
                if (length2 % 2 == 0) {//nums2数组个数是偶数个,
                    return (nums2[length2 / 2 - 1] + nums2[length2 / 2]) / 2.0;
                } else {
                    return nums2[length2 / 2];
                }
            }
            //有序数组nums2是空数组，则有序数组nums1就是合并后的数组
            if (length2 == 0) {
                if (length1 % 2 == 0) {
                    return (nums1[length1 / 2 - 1] + nums1[length1 / 2]) / 2.0;
                } else {
                    return nums1[length1 / 2];
                }
            }
            // 下面这个和合并有序链表的思路一样
            //	有序数组nums1和nums2都非空且至少都有一个元素，先合并完两个有序数组
            int count = 0;//定义合并后数组的下标，其实也是一个计数器
            int i1 = 0;//定义数组nums1的下标，遍历数组nums1.可以理解为指针
            int i2 = 0;//定义数组nums2的下标，
            while (count < (length1 + length2)) {
                while (i1 < length1 && i2 < length2) {//数组nums1和nums2都没有遍历完
                    //有序合并时，把两个数中较小的有序放入到合并后的数组中
                    if (nums1[i1] < nums2[i2]) {
                        //arr[count++] = nums1[i++]; //和后面三行分开写是等价的
                        arr[count] = nums1[i1];
                        i1++;//指针后移
                        count++;//指针后移
                    } else {
                        arr[count] = nums2[i2];
                        i2++;
                        count++;
                    }
                }
                // 执行到这里，至少一个已经遍历完了，
                //数组nums1已经空了，数组nums2还没有空
                while(i1 == length1 && i2 < length2) {
                    arr[count] = nums2[i2];
                    i2++;
                    count++;
                }
                //数组nums2已经空了，数组nums2还没有空
                while(i2 == length2 && i1 < length1) {
                    arr[count] = nums1[i1];
                    i1++;
                    count++;
                }
            }

            if (count % 2 == 0) {//nums2数组个数是偶数个,
                return (arr[count / 2 - 1] + arr[count / 2]) / 2.0;
            } else {
                return arr[count / 2];
            }
        }



	////
	////*
	////* 解法2：思路和上面类似，但是并不创建新的数组来存储合并后的数组
	////*
	////* 返回中位数的话，奇数需要最后⼀次遍历的结果就可以了，偶数需要最后⼀次和上⼀次遍历的结果。
	////* 所以我们⽤两个变量 left 和 right ， right 保存当前循环的结果，在每次循环前将 right 的值赋给 left 。
	////* 这样在最后⼀次循环的时候， left 将得到 right 的值，也就是上⼀次循环的结果，接下来 right 更新为最后⼀次的结果。
	////* 如果 aStart 还没有到最后并且此时 A 位置的数字⼩于 B 位置的数组，那么就可以后移了。
	////* 也就是aStart ＜ m && A[aStart] < B[bStart]。
	////* 如果 B 数组此刻已经没有数字了，继续取数字B [ bStart ]，则会越界，所以判断下 bStart 是否⼤于数组⻓度了，
	////* 这样 || 后边的就不会执⾏了，也就不会导致错误了，所以增加为 aStart ＜ m && ( bStart >= n ||A [ aStart ] < B [ bStart ] )
	////*
	////*
    //
	//public double findMedianSortedArrays(int[] nums1, int[] nums2) {
	//	int length1 = nums1.length;
	//	int length2 = nums2.length;
	//	int length = length1 + length2;//length表示两个数组长度和
    //
	//	int i = 0;//定义数组nums1的下标，遍历数组nums1.可以理解为指针
	//	int j = 0;//定义数组nums2的下标，
	//	int left = -1;//前一次循环的结果
	//	int right = -1;//当前循环的结果
	//	for (int k = 0; k <=length/2; k++) {
	//		left=right;
	//		if(i<length1&&(j>=length2||nums1[i]<nums2[j])){//数组nums1没有遍历完，且（数组nums2没有越界或者nums1[i]<nums2[j]连个数里面取较小的）
	//			right=nums1[i];//当前循环的结果
	//			i++;
	//		}else{
	//			right=nums2[j];
	//			j++;
	//		}
	//	}
	//	if(length%2==0){
	//		return (left+right)/2.0;
	//	}else{
	//		return right;
	//	}
	//}


}
//leetcode submit region end(Prohibit modification and deletion)

}



