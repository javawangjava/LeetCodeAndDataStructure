/**
<p>给定一个 24 小时制（小时:分钟 <strong>&quot;HH:MM&quot;</strong>）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>timePoints = [&quot;23:59&quot;,&quot;00:00&quot;]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>timePoints = [&quot;00:00&quot;,&quot;23:59&quot;,&quot;00:00&quot;]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= timePoints &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>timePoints[i]</code> 格式为 <strong>&quot;HH:MM&quot;</strong></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 539&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/minimum-time-difference/">https://leetcode-cn.com/problems/minimum-time-difference/</a></p>
<div><div>Related Topics</div><div><li>数组</li><li>数学</li><li>字符串</li><li>排序</li></div></div><br><div><li>👍 34</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.List;

/**
 * 剑指 Offer II 035
 * 最小时间差
 * @author wangweizhou
 * @date 2022-11-11 01:22:01
 */
public class Five69nqc{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new Five69nqc().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	//
	public int findMinDifference(List<String> timePoints){
		// 一天最多只有1440分钟，如果输入的时间数组的长度超过1440，那么至少有两个时间是相同的。时间相同那么最小时间差一定是0。
		if(timePoints.size()>1440){// 当时间点个数多于1440时，肯定有重合的时间点
			return 0;
		}

		// 数组minuteFlags的长度是1440，某个位置的值是true,则表明对应的时间出现在输入的时间列表中。
		boolean[] minuteFlags=new boolean[1440];
		// 分割时间点
		for(String time:timePoints){
			String[] t=time.split(":");
			int min=Integer.parseInt(t[0])*60+Integer.parseInt(t[1]);// 将分割的字符串转换成分数表示的整数

			// 在扫描输入的时间数组时如果发现相同的时间，也可以直接返回最小的时间差，即0.
			if(minuteFlags[min]){// 当前时间点第二次出现，那么最小时间差是0。
				return 0;
			}
			minuteFlags[min]=true;// 该时间点第一次出现时，将该时间点设置为true。
		}
		return helper(minuteFlags);
	}



	// 顺序扫描这个数组，相邻的两个为true的值表示它们对应输入的两个相邻的时间。比较所有相邻的时间差就能得出最小的时间差。但是最后要把第1个时间点加上1440之后表示第二天的同一时间，求出它与最后一个时间的时间差。
	private int helper(boolean minuteFlags[]){
		int minDiff=minuteFlags.length-1;// minDiff表示参数数组的最小时间差
		int prev=-1;// prev是上一个访问的时间点，默认值设置为负数。那么当prev是非负数时，表示已经有访问过的上一个时间点
		int first=minuteFlags.length-1;// first表示参数数组最小的时间点
		int last=-1;// last表示参数数组最大的时间点


		for (int i = 0; i < minuteFlags.length; i++) {
			if(minuteFlags[i]){// 输入的时间点
				if(prev>=0){// 表示不是第一个数据时间点
					minDiff=Math.min(i-prev,minDiff);// 更新最小时间差
				}
				prev=i;// 更新上一个访问的时间点
				first=Math.min(i,first);// 更新时间点里面最小的
				last=Math.max(i,last);// 更新时间点里面最大的
			}
		}
		// first+minuteFlags.length-last：将第一个时间加1440转换成第二天的同一时间点，然后和最大的时间点做差
		minDiff=Math.min(first+minuteFlags.length-last,minDiff);
		return minDiff;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
