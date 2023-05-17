/**
<p>无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。</p>

<p> <strong>示例1:</strong></p>

<pre>
<strong> 输入</strong>：S = "qwe"
<strong> 输出</strong>：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
</pre>

<p> <strong>示例2:</strong></p>

<pre>
<strong> 输入</strong>：S = "ab"
<strong> 输出</strong>：["ab", "ba"]
</pre>

<p> <strong>提示:</strong></p>

<ol>
<li>字符都是英文字母。</li>
<li>字符串长度在[1, 9]之间。</li>
</ol>
<div><div>Related Topics</div><div><li>字符串</li><li>回溯</li></div></div><br><div><li>👍 77</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;
import java.util.*;

/**
 * 面试题 08.07
 * 无重复字符串的排列组合
 * @author wangweizhou
 * @date 2022-08-04 00:40:42
 */

public class PermutationILcci{

	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new PermutationILcci().new Solution();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

	// 解法1：回溯法+标记数组
    public String[] permutation(String S) {
		if(S==null||S.length()==0){//判空
			return new String[0];
		}
		List<String> list =new ArrayList<>();//可变数组用来保存字符串数组所有的全排列
		StringBuilder strBu=new StringBuilder();//可变字符串来保存字符串数组的一个全排列
		boolean[] isVisited=new boolean[S.length()];//标记每个位置的字符是否被使⽤过
		backtrace(list,strBu,S,isVisited);
		// 创建返回数组，并将list中元素保存到返回数组中
		String[] ans=new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ans[i]= list.get(i);
		}
		return ans;
    }

	// 回溯法
	private void backtrace(List<String> list,StringBuilder strBu,String S,boolean[] isVisited){
		//当可变字符串strBu长度等于字符串长度，即字符串的所有字符已经加入到可变字符串中
		if(S.length()==strBu.length()){
			list.add(strBu.toString());
			return;
		}


		for (int i = 0; i < S.length(); i++) {//遍历所有元素选取⼀个加⼊
			if(!isVisited[i]){ //如果该元素没有被加⼊时
				strBu.append(S.charAt(i));//加⼊临时字符串
				isVisited[i]=true;//标记为使⽤过
				backtrace(list,strBu,S,isVisited);
				//回溯
				strBu.deleteCharAt(strBu.length()-1);//删除临时字符串的最后一个字母
				isVisited[i]=false;//标记为未使⽤过
			}

			//下面语句和上面语句作用相同。
			//if(isVisited[i]){ //如果该元素没有被加⼊时
			//	continue;
			//}
			//strBu.append(S.charAt(i));//加⼊临时字符串
			//isVisited[i]=true;//标记为使⽤过
			//backtrace(list,strBu,S,isVisited);
			////回溯
			//strBu.deleteCharAt(strBu.length()-1);//删除临时字符串的最后一个字母
			//isVisited[i]=false;//标记为未使⽤过
		}
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}
