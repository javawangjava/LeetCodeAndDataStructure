/**
 * <p>小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;。</p>
 *
 * <p>除了<meta charset="UTF-8" />&nbsp;<code>root</code>&nbsp;
 * 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 <strong>两个直接相连的房子在同一天晚上被打劫</strong> ，房屋将自动报警。</p>
 *
 * <p>给定二叉树的&nbsp;<code>root</code>&nbsp;。返回&nbsp;<em><strong>在不触动警报的情况下</strong>&nbsp;，小偷能够盗取的最高金额</em>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/10/rob1-tree.jpg" /></p>
 *
 * <pre>
 * <strong>输入: </strong>root = [3,2,3,null,3,null,1]
 * <strong>输出:</strong> 7
 * <strong>解释:</strong>&nbsp;小偷一晚能够盗取的最高金额 3 + 3 + 1 = 7</pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2021/03/10/rob2-tree.jpg" /></p>
 *
 * <pre>
 * <strong>输入: </strong>root = [3,4,5,1,3,null,1]
 * <strong>输出:</strong> 9
 * <strong>解释:</strong>&nbsp;小偷一晚能够盗取的最高金额 4 + 5 = 9
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <p><meta charset="UTF-8" /></p>
 *
 * <ul>
 * <li>树的节点数在&nbsp;<code>[1, 10<sup>4</sup>]</code> 范围内</li>
 * <li><code>0 &lt;= Node.val &lt;= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>动态规划</li><li>二叉树</li></div></div><br><div><li>👍
 * 1397</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 337
 * 打家劫舍 III
 *
 * @author wangweizhou
 * @date 2022-08-16 03:53:30
 */

public class HouseRobberIii {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new HouseRobberIii().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    class Solution {

/*

	Map<TreeNode,Integer> select =new HashMap<>();
	Map<TreeNode,Integer> unselect =new HashMap<>();

    public int rob(TreeNode root) {
		dfs(root);
		return Math.max(select.getOrDefault(root,0), unselect.getOrDefault(root,0));
    }

	// 递归实现深度遍历
	private void dfs(TreeNode node){
		if(node==null){
			return;
		}
		dfs(node.left);
		dfs(node.right);
		select.put(node,node.val+ unselect.getOrDefault(node.left,0)+ unselect.getOrDefault(node.right,0));
		unselect.put(node,Math.max(select.getOrDefault(node.left,0), unselect.getOrDefault(node.left,0)
				+Math.max(select.getOrDefault(node.right,0), unselect.getOrDefault(node.right,0))));
	}

	*/




        // 解法2：
        // 每个节点可选择偷或者不偷两种状态，根据题目意思，相连节点不能一起偷。
        //任何一个节点能偷到的最大钱的状态可以定义为:
        // 1.选择不偷当前节点时，两个孩子节点只需要拿最多的钱出来就行(向父节点贡献出最多的钱)：当前节点能偷到的最大钱数 = 左孩子能贡献的最多的钱 + 右孩子能贡献的最多的钱。
        // 2.选择偷当前节点时，那么两个孩子节点就不能选择偷了。：当前节点能偷到的最大钱数 = 左孩子选择自己不偷时能得到的钱 + 右孩子选择不偷时能得到的钱 + 当前节点的钱数。

        //使用一个大小为 2 的数组来表示 int[] res = new int[2] 0 代表不偷，1 代表偷。

        public int rob(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int[] rootStatus = dfs(root);
            return Math.max(rootStatus[0], rootStatus[1]);
        }


        // 递归的后续遍历实现  因为要先处理了左右子树，才可以计算截至父节点能偷到的最大金额。
        //使用一个大小为 2 的数组来表示 int[] res = new int[2]。 0 代表不偷，1 代表偷。
        // dfs()返回值表示在以node 为根节点的二叉树中能偷到的最高金额。
        private int[] dfs(TreeNode node) {
            if (node == null) {
                return new int[2];
            }
            int[] result = new int[2];// 这里其实就是两个dp函数表示不同的状态，只是递归的时候需要使用引用变量来传递参数。
            int[] left = dfs(node.left);// 左子树
            int[] right = dfs(node.right);// 右子树

            // 当前节点 有偷和不偷两种状态
            // 选择不偷当前节点时，那么从当前节点向下能偷到的最大金额：是该节点的左右子树能贡献的最大金额的和。
            result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
            // 选择偷当前节点时，那么两个孩子节点就不能选择偷了。
            result[1] = left[0] + right[0] + node.val;
            return result;
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
