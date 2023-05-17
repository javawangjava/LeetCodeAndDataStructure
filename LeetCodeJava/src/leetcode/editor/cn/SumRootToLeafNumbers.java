/**
 * 给你一个二叉树的根节点 <code>root</code> ，树中每个节点都存放有一个 <code>0</code> 到 <code>9</code> 之间的数字。
 * <div class="original__bRMd">
 * <div>
 * <p>每条从根节点到叶节点的路径都代表一个数字：</p>
 *
 * <ul>
 * <li>例如，从根节点到叶节点的路径 <code>1 -> 2 -> 3</code> 表示数字 <code>123</code> 。</li>
 * </ul>
 *
 * <p>计算从根节点到叶节点生成的 <strong>所有数字之和</strong> 。</p>
 *
 * <p><strong>叶节点</strong> 是指没有子节点的节点。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/num1tree.jpg" style="width: 212px; height: 182px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,2,3]
 * <strong>输出：</strong>25
 * <strong>解释：</strong>
 * 从根到叶子节点路径 <code>1->2</code> 代表数字 <code>12</code>
 * 从根到叶子节点路径 <code>1->3</code> 代表数字 <code>13</code>
 * 因此，数字总和 = 12 + 13 = <code>25</code></pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/num2tree.jpg" style="width: 292px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>root = [4,9,0,5,1]
 * <strong>输出：</strong>1026
 * <strong>解释：</strong>
 * 从根到叶子节点路径 <code>4->9->5</code> 代表数字 495
 * 从根到叶子节点路径 <code>4->9->1</code> 代表数字 491
 * 从根到叶子节点路径 <code>4->0</code> 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = <code>1026</code>
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点的数目在范围 <code>[1, 1000]</code> 内</li>
 * <li><code>0 <= Node.val <= 9</code></li>
 * <li>树的深度不超过 <code>10</code></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 572</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 129    参见112  两个做法类似
 * 求根节点到叶节点数字之和
 *
 * @author wangweizhou
 * @date 2022-09-05 10:51:25
 */

public class SumRootToLeafNumbers {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SumRootToLeafNumbers().new Solution();
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(1);
        TreeNode node6 = new TreeNode(2);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.right = node6;
        int ans = solution.sumNumbers(node1);
        System.out.println(ans);
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


        // 如何计算路径表示的数字：顺着指向子节点的指针路径向下遍历二叉树，每到达一个节点，相当于在路径表示的数字末尾添加一位数字。
        // 例如，在最开始到达根节点时，它表示数字3。然后到达节点9，此时路径表示数字39（3×10+9=39）。然后向下到达节点5，此时路径表示数字395（39×10+5=395）。
        // 这就是说，每当遍历到一个节点时都计算从根节点到当前节点的路径表示的数字。
        // 如果这个节点还有子节点，就把这个值传下去继续遍历它的子节点。先计算到当前节点为止的路径表示的数字，再计算到它的子节点的路径表示的数字，这实质上就是典型的二叉树前序遍历。
        // 路径的定义是从根节点开始到叶节点结束，因此上述代码中只有遇到叶节点才返回路径表示的数字（代码中的变量path）。
        // 如果在遇到叶节点之前就结束的路径，由于不符合题目要求，因此应该返回0。

        // 这是辅助函数dfs的第1条if语句（root==null）为true时返回0的原因。
        // 例如，在图8.4中，当路径到达节点0时路径表示的数字为30，此时如果顺着指向左子节点的指针将前往null节点，
        // 这时路径已经终止但终点并不是叶节点，因此不符合题目的条件，只能返回0。


        //// 解法1：深度优先遍历  写法1  有返回值的前序遍历  注意和后面没有返回值的方法对比
        //public int sumNumbers(TreeNode root) {
        //    if (root == null) {
        //        return 0;
        //    }
        //    return dfs(root, 0);
        //}
        //
        //
        //// 具有返回值的前序遍历,   这个起始不是很好理解
        //private int dfs(TreeNode root, int sum) {
        //    if (root == null) {// 如果在遇到叶节点之前就结束的路径，由于不符合题目要求，因此应该返回0。
        //        return 0;
        //    }
        //    // 处理当前节点
        //    // 每当遍历到一个节点时都计算从根节点到当前节点的路径表示的数字。
        //    // 如果这个节点还有子节点，就把这个值传下去继续遍历它的子节点。先计算到当前节点为止的路径表示的数字，再计算到它的子节点的路径表示的数字，这实质上就是典型的二叉树前序遍历。
        //    sum = sum * 10 + root.val;// 每当遍历到一个节点时都计算从根节点到当前节点的路径表示的数字
        //    if (root.left == null && root.right == null) { // 路径的定义是从根节点开始到叶节点结束，因此上述代码中只有遇到叶节点才返回路径表示的数字（代码中的变量path）。
        //        return sum;
        //    }
        //    return dfs(root.left, sum) + dfs(root.right, sum);// 返回左右子树路径数字的和
        //}




        ////// 解法1： 前序遍历 写法2  没有返回值的前序遍历  注意和上面右返回值的写法区别
        //private int sum;// 全局变量用来实时的累计路径数字之和
        //public int sumNumbers(TreeNode root) {
        //    if (root == null) {
        //        return 0;
        //    }
        //    dfs(root, 0);
        //    return sum;
        //}
        //
        //
        //// 没有返回值的深度遍历，当遍历到叶子节点时，将从根节点到叶子节点的数添加到sum中，
        //// num表示从根节点到当前节点的数值
        //private void dfs(TreeNode node, int num) {
        //    if (node == null) {
        //        return;
        //    }
        //    // 处理当前节点
        //    // 每当遍历到一个节点时都计算从根节点到当前节点的路径表示的数字。
        //    // 如果这个节点还有子节点，就把这个值传下去继续遍历它的子节点。先计算到当前节点为止的路径表示的数字，再计算到它的子节点的路径表示的数字，这实质上就是典型的二叉树前序遍历。
        //    num = num * 10 + node.val;
        //    // 每遍历完一个节点，需要判断当前节点是否是叶子节点，将遍历到的数字累加到结果sum中
        //    if (node.left == null && node.right == null) {
        //        sum += num;
        //    }
        //    dfs(node.left, num);// 递归左子树
        //    dfs(node.right, num);// 递归右子树
        //}




        //// 解法1： 前序遍历 写法2的改写，使用数组元素作为参数进行传递  没有返回值的前序遍历  注意和上面右返回值的写法区别
        public int sumNumbers(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int[] totalSum = {0};
            dfs(root, 0, totalSum);
            return totalSum[0];
        }


        // num表示从根节点到当前节点的路径表示的数字，totalSum[0]表示路径总额和
        private void dfs(TreeNode root, int num, int[] totalSum) {
            if (root == null) {
                return;
            }
            // 处理当前节点
            num = num * 10 + root.val;
            if (root.left == null && root.right == null) {
                totalSum[0] += num;
            }
            dfs(root.left, num, totalSum);
            dfs(root.right, num, totalSum);
        }




        //// 解法2：BFS   建立一个队列表示从根节点到每一个节点的路径数字，这个相当于建立一个和原二叉树对应的路径和节点队列   这个解法和112一样
        //public int sumNumbers(TreeNode root) {
        //    if (root == null) {
        //        return 0;
        //    }
        //    Queue<TreeNode> nodeQueue = new ArrayDeque<>();// 队列用来辅助遍历二叉树
        //    Queue<Integer> numQueue = new ArrayDeque<>();// 建立一个与二叉树对应的路径和的队列,存储从根节点到当前节点的路径和
        //    int sum = 0;// 所有路径之和
        //    // 注意队列和路径和队列是同步操作的
        //    nodeQueue.add(root);// 将根节点加入到队列中
        //    numQueue.add(root.val);// 将根节点的值加入到队列中
        //    while (!nodeQueue.isEmpty()) {
        //        // 注意队列和路径和队列是同步操作的
        //        TreeNode node = nodeQueue.poll();
        //        int num = numQueue.poll();// num表示从根节点到当前节点的路径表示的数字。
        //        // 路径的定义是从根节点开始到叶节点结束，因此只有遇到叶节点才获得路径表示的数字
        //        if (node.left == null && node.right == null) {
        //            sum += num;
        //        }
        //
        //        // 顺着指向子节点的指针路径向下遍历二叉树，每到达一个节点，相当于在路径表示的数字末尾添加一位数字
        //        if (node.left != null) {// 当左子节点不空时，
        //            // nodeQueue.add(node.left);
        //            // numQueue.add(num * 10 + node.left.val);// 每当遍历到一个节点时都计算从根节点到当前节点的路径表示的数字。
        //            int temp=num * 10 + node.left.val;// 注意这里使用了一个临时变量，没有使用num=num * 10 + node.left.val,
        //            // 如果写成num=num * 10 + node.left.val会影响下面的语句。
        //            nodeQueue.add(node.left);
        //            numQueue.add(temp);// 每当遍历到一个节点时都计算从根节点到当前节点的路径表示的数字。
        //        }
        //        if (node.right != null) {
        //            nodeQueue.add(node.right);
        //            numQueue.add(num * 10 + node.right.val);
        //        }
        //    }
        //    return sum;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
