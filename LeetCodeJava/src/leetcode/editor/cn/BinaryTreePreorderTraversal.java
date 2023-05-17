/**
 * <p>给你二叉树的根节点 <code>root</code> ，返回它节点值的 <strong>前序</strong><em> </em>遍历。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" style="width: 202px; height: 324px;
 * " />
 * <pre>
 * <strong>输入：</strong>root = [1,null,2,3]
 * <strong>输出：</strong>[1,2,3]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_5.jpg" style="width: 202px; height: 202px;
 * " />
 * <pre>
 * <strong>输入：</strong>root = [1,2]
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_4.jpg" style="width: 202px; height: 202px;
 * " />
 * <pre>
 * <strong>输入：</strong>root = [1,null,2]
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[0, 100]</code> 内</li>
 * <li><code>-100 <= Node.val <= 100</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 860</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 144
 * 二叉树的前序遍历
 *
 * @author wangweizhou
 * @date 2022-07-07 08:51:26
 */

public class BinaryTreePreorderTraversal {
    public static void main(String[] args) {
        //测试代码

        Solution solution = new BinaryTreePreorderTraversal().new Solution();
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

        List<Integer> list = solution.preorderTraversal(rootnode);
        System.out.println(list);

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



        //  方法二：迭代   这个迭代是从下到上迭代
        //  迭代的时候需要显式地将这个栈模拟出来，其余的实现与细节都相同

            public List<Integer> preorderTraversal(TreeNode root) {
                List<Integer> res=new ArrayList<>();//创建ArrayLists对象res来存储结果
                if(root==null){ // 递归终止的条件为碰到空节点。
                    return res;
                }
                Deque<TreeNode> stack=new LinkedList<>();//创建ArrayLists对象stack来模拟栈
                TreeNode node=root;//辅助遍历二叉树的指针node

                while (!stack.isEmpty() || node != null) {//栈不空或者当前节点不是空节点
                    // 前序遍历，根节点->左子树->右子树，迭代执行，内层while是遍历完整的左子树
                    // 当前节点node不空时，通过循环将每个节点当做父节点进行数据处理，然后不断将该节点【后续访问有节点，需要有根节点】入栈，并访问该节点的左子树。
                    while (node != null) {
                        res.add(node.val);// 处理当前节点【也就是父节点】，有关处理当前节点的方法就可以写在这里
                        stack.push(node);//将当前节点压入栈，需要保留父节点这样才能方便遍历左子树和右子树
                        node = node.left;//更新指针指向当前栈的左子树，遍历左子树
                    }
                    // 上面循环结束时，node最终指向刚开始时以node为根节点的左子树的最左侧叶子节点的左指针域【最左侧叶子节点的左指针域其实是空指针】
                    node = stack.pop();//弹出栈顶元素，其实就是返回上一层的根节点。其实就是上面循环结束时空指针的父节点。
                    node = node.right;// 通过当前节点【根节点】去访问当前节点右子树。
                }

                return res;
            }




        /*

        // 二叉树的前序遍历
        //  方法二：迭代写法1    这个迭代是从上到下扫描，
        //  从上到下每次形成一个由父节点，左子节点，右子节点构成的树结构


        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> ans=new ArrayList<>();//添加遍历结果的数组
            if(root==null){//空树
                return ans;
            }
            Deque<TreeNode> stack=new LinkedList<>();
            // 从上到下扫描，这里要进栈
            stack.push(root); //根节点先进栈
            // 需要从栈中弹出父节点，由父节点可以得到左右子节点。所以首先压入父节点。
            // 栈是先进后出 入栈顺序是：右节点->左节点，出栈顺序是：左节点->右节点
            while (!stack.isEmpty()) {//while循环其实就是通过迭代来实现递归调用那个过程
                // 处理当前节点
                TreeNode node = stack.pop();// 弹出当前节点【也就是父节点，任何一个节点都可以看做其子树的父节点】。node就是辅助节点帮助遍历二叉树,通过父节点去访问左右子节点
                ans.add(node.val);// 处理当前节点【也就是父节点】，有关处理当前节点的方法就可以写在这里

                // 当前节点不空，处理当前节点的右节点
                if (node.right != null) {
                    stack.push(node.right);
                }
                // 当前节点不空，处理当前节点的左节点
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
            return ans;
        }

        */





        /*

        // 方法一：递归 写法1
        // 在递归的过程中使用了系统栈【递归的时候隐式地维护了一个栈】
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();//创建ArrayLists对象res来存储结果
            preorder(root, res);
            return res;
        }

        // public void preorder(TreeNode node, List<Integer> res) 表示遍历到以node节点为根节点的答案。
        public void preorder(TreeNode node, List<Integer> res) {
            if (node == null) {// 递归终止的条件为碰到空节点。
                return;
            }
            res.add(node.val);// 首先将 root 节点的值加入答案
            preorder(node.left, res);// 递归调用 preorder(root.left) 来遍历 root 节点的左子树
            preorder(node.right, res);// 递归调用 preorder(root.right) 来遍历 root 节点的右子树
        }
        */




        /*

        // 方法一：递归 写法3  函数调用
        // 在递归的过程中使用了系统栈【递归的时候隐式地维护了一个栈】
        public List<Integer> preorderTraversal(TreeNode root) {
            List<Integer> lists = new ArrayList<>();//创建ArrayLists对象res来存储结果
            return preorder(root, lists);
        }

        // public void preorder(TreeNode node, List<Integer> lists) 表示遍历到以node节点为根节点的答案。
        public List<Integer> preorder(TreeNode node, List<Integer> lists) {
            if (node == null) {// 递归终止的条件为碰到空节点。
                return lists;
            }
            lists.add(node.val);// 首先将 root 节点的值加入答案
            preorder(node.left, lists);// 递归调用 preorder(root.left) 来遍历 root 节点的左子树
            preorder(node.right, lists);// 递归调用 preorder(root.right) 来遍历 root 节点的右子树
            return lists;
        }

        */




        /*
        // 方法一：递归 写法2
        List<Integer> lists = new ArrayList<>();// 这里声明了一个全局变量使用
        public List<Integer> preorderTraversal(TreeNode root) {
            if (root == null) {
                return lists;
            }
            lists.add(root.val);
            preorderTraversal(root.left);
            preorderTraversal(root.right);
            return lists;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}



