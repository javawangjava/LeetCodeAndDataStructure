/**
 * <p>给你二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>锯齿形层序遍历</strong> 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>[[3],[20,9],[15,7]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[[1]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[0, 2000]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 688</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;


/**
 * 103
 * 二叉树的锯齿形层序遍历
 *
 * @author wangweizhou
 * @date 2022-09-03 21:18:58
 */

public class BinaryTreeZigzagLevelOrderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BinaryTreeZigzagLevelOrderTraversal().new Solution();

        //创建需要的结点
        TreeNode rootnode = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        List<List<Integer>> lists = solution.zigzagLevelOrder(rootnode);
        System.out.println(lists);

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


        //// 解法1：层序遍历+注意奇偶层之间转换
        //// List 出队顺序和入队顺序一样，本题分奇偶层，奇数层从左向右，偶数层从右向左，那么添加在List的首位。注意每一层添加完之后标记符转换。
        //
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> lists = new ArrayList<>();
            if (root == null) {
                return lists;
            }
            TreeNode node = root;
            Deque<TreeNode> queue = new LinkedList<>();
            boolean rightToLeft = false;// 标记符
            queue.add(node);
            while (queue.size() > 0) {
                int levelSize = queue.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < levelSize; i++) {
                    node = queue.poll();
                    if (rightToLeft) {
                        list.add(0, node.val);// 偶数层从右向左，那就是新加入的元素加入到队列的头部。
                    } else {
                        list.add(node.val);// 奇数层从左向右，那就是新加入的元素加入到队列的尾部。
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                lists.add(list);
                rightToLeft = !rightToLeft;// 遍历完一层，标记符取反
            }
            return lists;
        }




        //// 解法2: 层序遍历+注意奇偶层之间转换,这里使用奇偶数来分层
        //public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        //    List<List<Integer>> lists = new ArrayList<>();
        //    if (root == null) {
        //        return lists;
        //    }
        //    TreeNode node = root;
        //    Deque<TreeNode> queue = new LinkedList<>();
        //    int count = 1;// 用奇偶数来区分，这里设定根节点为第一层
        //    queue.add(node);
        //    while (queue.size() > 0) {
        //        int levelSize = queue.size();
        //        List<Integer> list = new ArrayList<>();
        //        for (int i = 0; i < levelSize; i++) {
        //            node = queue.poll();
        //            if (count % 2 == 0) {
        //                list.add(0, node.val);// 偶数层从右向左，那就是新加入的元素加入到队列的头部。
        //            } else {
        //                list.add(node.val);// 奇数层从左向右，那就是新加入的元素加入到队列的尾部。
        //            }
        //            if (node.left != null) {
        //                queue.add(node.left);
        //            }
        //            if (node.right != null) {
        //                queue.add(node.right);
        //            }
        //        }
        //        lists.add(list);
        //        count++;// 遍历完一层，标记符取反
        //    }
        //    return lists;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
