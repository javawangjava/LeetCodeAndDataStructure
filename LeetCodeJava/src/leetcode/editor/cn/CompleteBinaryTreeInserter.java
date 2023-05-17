/**
 * <p><strong>完全二叉树</strong> 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。</p>
 *
 * <p>设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。</p>
 *
 * <p>实现 <code>CBTInserter</code> 类:</p>
 *
 * <ul>
 * <li><code>CBTInserter(TreeNode root)</code>&nbsp;使用头节点为&nbsp;<code>root</code>&nbsp;的给定树初始化该数据结构；</li>
 * <li><code>CBTInserter.insert(int v)</code>&nbsp; 向树中插入一个值为&nbsp;<code>Node.val == val</code>的新节点&nbsp;
 * <code>TreeNode</code>。使树保持完全二叉树的状态，<strong>并返回插入节点</strong>&nbsp;<code>TreeNode</code>&nbsp;
 * <strong>的父节点的值</strong>；</li>
 * <li><code>CBTInserter.get_root()</code> 将返回树的头节点。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <ol>
 * </ol>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/08/03/lc-treeinsert.jpg" style="height: 143px; width: 500px;
 * " /></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["CBTInserter", "insert", "insert", "get_root"]
 * [[[1, 2]], [3], [4], []]
 * <strong>输出</strong>
 * [null, 1, 2, [1, 2, 3, 4]]
 *
 * <strong>解释</strong>
 * CBTInserter cBTInserter = new CBTInserter([1, 2]);
 * cBTInserter.insert(3);  // 返回 1
 * cBTInserter.insert(4);  // 返回 2
 * cBTInserter.get_root(); // 返回 [1, 2, 3, 4]</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数量范围为&nbsp;<code>[1, 1000]</code>&nbsp;</li>
 * <li><code>0 &lt;= Node.val &lt;= 5000</code></li>
 * <li><code>root</code>&nbsp;是完全二叉树</li>
 * <li><code>0 &lt;= val &lt;= 5000</code>&nbsp;</li>
 * <li>每个测试用例最多调用&nbsp;<code>insert</code>&nbsp;和&nbsp;<code>get_root</code>&nbsp;操作&nbsp;
 * <code>10<sup>4</sup></code>&nbsp;次</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>设计</li><li>二叉树</li></div></div><br><div><li>👍
 * 146</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.time.temporal.Temporal;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 919
 * 完全二叉树插入器
 * @author wangweizhou
 * @date 2022-11-16 16:01:22
 */

public class CompleteBinaryTreeInserter {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new CompleteBinaryTreeInserter().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

    class CBTInserter {


        // 根据完全二叉树的定义，在完全二叉树中只有最下面一层可能是不满的，其他层都是满的（在二叉树中第n层最多有2^(n-1)个节点）。
        // 如果最下面一层不是满的，则从左到右找到该层的第1个空缺位置并添加新的节点。
        // 如果完全二叉树的最下面一层已经满了，此时再在二叉树中添加新的节点将会在二叉树中添加新的一层，
        // 而且新的节点是新层最左边的节点，也就是说，新节点的父节点是原来最下面一层的最左边节点。

        // 在完全二叉树中添加新节点顺序看起来是从上到下按层从左到右添加的，这就是典型的二叉树广度优先搜索的顺序。
        // 我们可以每次在完全二叉树中按照广度优先搜索的顺序找出第1个左子节点或右子节点还有空缺的节点。
        // 如果它没有左子节点，那么新的节点就作为它的左子节点；如果它没有右子节点，那么新的节点就作为它的右子节点。

        // 接下来考虑效率优化。在完全二叉树中添加节点时需要按照广度优先搜索的顺序找出第1个缺少子节点的节点。
        // 其实没有必要在每次插入新的节点时都从完全二叉树的根节点开始从头进行广度优先搜索。所以在初始化时就可以找出第1个缺少子节点的节点。


        private Deque<TreeNode> queue;
        private TreeNode root;
        public CBTInserter(TreeNode root) {
            queue = new LinkedList<>();
            this.root = root;
            queue.offer(root);
            while (queue.peek().left != null && queue.peek().right != null) {
                TreeNode node = queue.poll();
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }


        public int insert(int v) {
            TreeNode parent = queue.peek();
            TreeNode node = new TreeNode(v);
            if (parent.left == null) {
                parent.left = node;
            } else {
                parent.right = node;
                queue.poll();
                queue.offer(parent.left);
                queue.offer(parent.right);
            }
            return parent.val;
        }


        public TreeNode get_root() {
            return this.root;
        }


        //// 当一个节点有左右两个子节点时，那么该节点就需要出队，并将该节点的左右子节点依次加入队列中。
        //// 解法1：层序遍历的改编
        //private Queue<TreeNode> queue;// 用队列来实现二叉树的层序遍历
        //private TreeNode root;// 二叉树的根节点
        //// 初始化队列，保存二叉树的根，并将有左右子节点的节点全部加入队列中
        //public CBTInserter(TreeNode root) {// 构造器初始化完全二叉树
        //	this.root = root;
        //	queue = new LinkedList<>();
        //	queue.offer(root);
        //
        //	// 从完全二叉树的根节点开始遍历，将有左右子节点的节点全部跳过去，队列中保存最多只有一个子节点的节点。
        //	while (queue.peek().left != null && queue.peek().right != null) {
        //		// 当一个节点有左右两个子节点时，那么该节点就需要出队，并将该节点的左右子节点依次加入队列中。
        //		TreeNode node = queue.poll();
        //		queue.offer(node.left);
        //		queue.offer(node.right);
        //	}
        //}
        //
        //
        //
        //// 进行到这里，队列中的节点都是最多只有一个子节点，也就是队列中的节点最多只有一个左子节点。
        //public int insert(int v) {
        //	// 当前队列的第一个，一定是没有子节点或者只有左子节点，所以只能获取队列前面的节点，但不能将该节点移出队列。
        //	// 如果队首元素没有子节点，那么出队之后还需要将该节点再次放入到队首。这时候需要双端队列可以实现，单端队列不能实现
        //	TreeNode parent = queue.peek();// 获取队列最前面的节点，该节点是待插入节点的父节点
        //	TreeNode node = new TreeNode(v);// 待插入节点
        //	if (parent.left == null) {// 如果当前节点缺少左子节点，则待插入节点连接到当前节点【栈顶节点】的左子节点；
        //		parent.left = node;
        //	} else {// 如果当前节点的左子节点不为空，也就是只缺少右子节点，将该节点连接到父节点的右子节点上，那么该节点的父节点就会有左右两个子节点，父节点需要出队，父节点的两个左右子节点需要入队。
        //		parent.right = node;// 如果当前节点缺少右子节点，则待插入节点连接到当前节点【栈顶节点】的右子节点；
        //		// 当一个节点有左右两个子节点时，那么该节点就需要出队，并将该节点的左右子节点依次加入队列中。
        //		queue.poll();
        //		queue.offer(parent.left);
        //		queue.offer(parent.right);
        //	}
        //	return parent.val;// 返回插入节点的父节点的值
        //}
        //
        //
        //
        //// 获取二叉树的根节点
        //public TreeNode get_root() {
        //	return this.root;
        //}
        //


    }

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(val);
 * TreeNode param_2 = obj.get_root();
 */

//leetcode submit region end(Prohibit modification and deletion)

}
