/**
 * <p>从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>例如:<br>
 * 给定二叉树:&nbsp;<code>[3,9,20,null,null,15,7]</code>,</p>
 *
 * <pre>    3
 * / \
 * 9  20
 * /  \
 * 15   7
 * </pre>
 *
 * <p>返回：</p>
 *
 * <pre>[3,9,20,15,7]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ol>
 * <li><code>节点总数 &lt;= 1000</code></li>
 * </ol>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 232</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 32 - I
 * 从上到下打印二叉树
 *
 * @author wangweizhou
 * @date 2022-09-15 01:09:01
 */

public class CongShangDaoXiaDaYinErChaShuLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new CongShangDaoXiaDaYinErChaShuLcof().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */

    class Solution {


        // 每次打印一个节点的时候，如果该节点有子节点，则把该节点的子节点放到一个队列的末尾。
        // 接下来到队列的头部取出最早进入队列的节点，重复前面的打印操作，直至队列中所有的节点都被打印出来。
        // 解法1：BFS

        public int[] levelOrder(TreeNode root) {
            if (root == null) {
                return new int[0];
            }
            Deque<TreeNode> queue = new LinkedList<>();
            List<Integer> lists = new ArrayList<>();// 可变数组存储节点数据值
            queue.add(root);
            while (queue.size() > 0) {
                TreeNode node = queue.poll();
                lists.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            int[] ans = new int[lists.size()];
            for (int i = 0; i < lists.size(); i++) {
                ans[i] = lists.get(i);
            }
            return ans;
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
