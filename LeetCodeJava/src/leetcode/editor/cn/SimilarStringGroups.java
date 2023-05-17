/**
 * <p>如果交换字符串&nbsp;<code>X</code> 中的两个不同位置的字母，使得它和字符串&nbsp;<code>Y</code> 相等，那么称 <code>X</code> 和 <code>Y</code>
 * 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。</p>
 *
 * <p>例如，<code>"tars"</code> 和 <code>"rats"</code> 是相似的 (交换 <code>0</code> 与 <code>2</code> 的位置)；&nbsp;
 * <code>"rats"</code> 和 <code>"arts"</code> 也是相似的，但是 <code>"star"</code> 不与
 * <code>"tars"</code>，<code>"rats"</code>，或 <code>"arts"</code> 相似。</p>
 *
 * <p>总之，它们通过相似性形成了两个关联组：<code>{"tars", "rats", "arts"}</code> 和 <code>{"star"}</code>。注意，<code>"tars"</code> 和
 * <code>"arts"</code> 是在同一组中，即使它们并不相似。形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。</p>
 *
 * <p>给你一个字符串列表 <code>strs</code>。列表中的每个字符串都是 <code>strs</code> 中其它所有字符串的一个字母异位词。请问 <code>strs</code> 中有多少个相似字符串组？</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>strs = ["tars","rats","arts","star"]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>strs = ["omv","ovm"]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= strs.length &lt;= 300</code></li>
 * <li><code>1 &lt;= strs[i].length &lt;= 300</code></li>
 * <li><code>strs[i]</code> 只包含小写字母。</li>
 * <li><code>strs</code> 中的所有单词都具有相同的长度，且是彼此的字母异位词。</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>数组</li><li>字符串</li></div></div
 * ><br><div><li>👍 161</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 839
 * 相似字符串组
 * @author wangweizhou
 * @date 2022-12-14 16:16:36
 */
public class SimilarStringGroups {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new SimilarStringGroups().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 把输入数组中的每个字符串看成图中的一个节点。如果两个字符串相似，那么它们对应的节点之间有一条边相连，也就属于同一个子图。
        // 本质上来看是同一类问题，都是求图中子图的数目
        // 初始化的时候n个字符串对应的n个节点分别属于n个子集，每个子集只有一个节点，因此每个fathers[i]的值都是i，即任意节点在并查集中的父节点指针都指向自己。
        // 接着逐一判断每组两个单词是否相似，如果相似就将它们所在的子集合并，这是经典的并查集的应用。每当两个子集合并时，子集的数目就减1。
        // 函数similar用来判断两个字符串是否相似。由于题目假设输入的字符串为一组变位词，因此只要两个字符串之间对应位置不同字符的个数不超过两个，那么它们一定相似。


		//// 解法1：并查集
        //public int numSimilarGroups(String[] strs) {
        //    if (strs == null || strs.length == 0) {
        //        return 0;
        //    }
        //    int len = strs.length;// 字符串的个数，也就是初始化是子图的个数
        //    int[] fathers = new int[len];// 创建长度为n的数组fathers存储n个节点的父节点。
        //    for (int i = 0; i < len; i++) {// 每个fathers[i]的值都是i，即任意节点在并查集中的父节点指针都指向自己。
        //        fathers[i] = i;
        //    }
        //
        //    int groupNum = len;// 子图的个数，初始化为字符串的个数，即每一个子图都只有一个节点
		//	// 依次遍历字符串数组中的两个字符串
        //    for (int i = 0; i < len; i++) {
        //        for (int j = i + 1; j < len; j++) {
        //            if (similar(strs[i], strs[j]) && union(fathers, i, j)) {
        //                groupNum--;
        //            }
        //        }
        //    }
        //    return groupNum;
        //
        //}
        //
        //// 我们真正关心的是节点i的根节点是谁而不是它的父节点，因此可以在fathers[i]中存储它的根节点。
        //// 当第1次找节点i的根节点时，还需要沿着指向父节点的边遍历直到找到根节点。一旦找到了它的根节点，就把根节点存放到fathers[i]中。
        //// 函数findFather用来查找一个节点的根节点。一旦得知节点i的根节点，就记录到fathers[i]中，相当于压缩了路径。
        //
		//// 并查集中的每个子集是一棵树，每个元素是某棵树中的一个节点。树中的每个节点有一个指向父节点的指针，树的根节点的指针指向它自己。
		//// 注意findFather是找出并查集中节点的根节点。
        //private int findFather(int[] fathers, int i) {
		//	  // 在并查集中只有根节点的指针指向它自身，所以当根节点的指针指向它自身时【fathers[i] == i】，说明找到了并查集的根节点
        //    // 当节点i的根节点不是自身时，递归找到节点i的父节点，也就是在并查集中沿着指向父节点的指针一直找到树的根节点
        //    if (fathers[i] != i) {
        //        fathers[i] = findFather(fathers, fathers[i]);
        //    }
        //    return fathers[i];
        //}
        //
        //// 函数union合并两个子集
        //// 若两个子集的根节点相同，它们已经位于同一个子集中，则不用合并，返回false。
        //// 若两个子集的根节点不同，那么它们位于不同的子集中，将一个子集的根节点的指向父节点的指针指向另一个子集的根节点，这就合并了两个子集时，函数union在合并两个子集时返回true
        //private boolean union(int[] fathers, int i, int j) {
        //    int fatherOfI = findFather(fathers, i);
        //    int fatherOfJ = findFather(fathers, j);
        //    // 如果它们的根节点不同，那么它们位于不同的子集中，将一个子集的根节点的指向父节点的指针指向另一个子集的根节点，这就合并了两个子集时。
        //    if (fatherOfI != fatherOfJ) {
        //        fathers[fatherOfI] = fatherOfJ;
        //        return true;
        //    }
        //    return false;
        //}
        //
        //
        //
        //// 函数similar用来判断两个字符串是否相似。由于题目假设输入的字符串为一组变位词，因此只要两个字符串之间对应位置不同字符的个数不超过两个，那么它们一定相似。
        //// 逐个对比两个字符串中的字符，用计数器来记录不同的字符个数，当不同的字符个数超过两个时，他们一定相似
        //private boolean similar(String str1, String str2) {
        //    int diffCount = 0;
        //    for (int i = 0; i < str1.length(); i++) {
        //        if (str1.charAt(i) != str2.charAt(i)) {
        //            diffCount++;
        //        }
        //        // 这里没必要找出所有不同的字符的个数，只要不同的字符个数大于2，就可以结束
        //        if (diffCount > 2) {
        //            return false;
        //        }
        //    }
        //    return true;
        //}
        //
        //
        ////private boolean similar(String str1,String str2){
        ////	int diffCount=0;
        ////	for (int i = 0; i < str1.length(); i++) {
        ////		if(str1.charAt(i)!=str2.charAt(i)){
        ////			diffCount++;
        ////		}
        ////	}
        ////	return diffCount<=2;
        ////}
        //



    }
//leetcode submit region end(Prohibit modification and deletion)

}
