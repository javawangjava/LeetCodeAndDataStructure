/**
 * <p>给你二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>层序遍历</strong> 。 （即逐层地，从左到右访问所有节点）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>[[3],[9,20],[15,7]]
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
 * <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1384</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 102
 * 二叉树的层序遍历
 *
 * @author wangweizhou
 * @date 2022-07-10 01:10:00
 */

public class BinaryTreeLevelOrderTraversal {

    public static void main(String[] args) {

        //测试代码
        //Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();

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

        Solution solution = new BinaryTreeLevelOrderTraversal().new Solution();
        List<List<Integer>> list = solution.levelOrder(rootnode);
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


        //// 解法3：用两个队列，每一个队列各保存一层
        //public List<List<Integer>> levelOrder(TreeNode root) {
        //    List<List<Integer>> lists = new ArrayList<>();// 输出的是二维数组,集合中套集合
        //    if (root == null) {// 如果是空
        //        return lists;
        //    }
        //    Deque<TreeNode> queue1 = new LinkedList<>();
        //    Deque<TreeNode> queue2 = new LinkedList<>();
        //    List<Integer> list = new ArrayList<>();
        //    queue1.add(root);
        //    while (!queue1.isEmpty()) {
        //        TreeNode node = queue1.poll();
        //        list.add(node.val);
        //        if (node.left != null) {
        //            queue2.add(node.left);
        //        }
        //        if (node.right != null) {
        //            queue2.add(node.right);
        //        }
        //        if (queue1.isEmpty()) {
        //            lists.add(list);
        //            list = new ArrayList<>();
        //            queue1 = queue2;
        //            queue2 = new LinkedList<>();
        //        }
        //    }
        //    return lists;
        //}




        //// 解法1：BFS 的应用一：层序遍历
        ////step1：首先判断二叉树是否为空，空树没有遍历结果。
        ////step2:建立辅助队列，根节点首先进入队列。不管层次怎么访问，根节点一定是第一个，那它肯定排在队伍的最前面。
        ////step3:每次进入一层，统计队列中元素的个数。因为每当访问完一层，下一层作为这一层的子节点，一定都加入队列，
        //// 而再下一层还没有加入，因此此时队列中的元素个数就是这一层的元素个数。
        ////step4:每次遍历这一层这么多的节点数，将其依次从队列中弹出，然后加入这一行的一维数组中，如果它们有子节点，依次加入队列排队等待访问。
        ////step5：访问完这一层的元素后，将这个一维数组加入二维数组中，再访问下一层。

        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> lists = new ArrayList<>();//输出的是二维数组,集合中套集合
            if (root == null) {//如果是空
                return lists;
            }
            //队列存储，进⾏层次遍历
            Queue<TreeNode> queue = new LinkedList<>();//队列保证每一层是先进先出,注意  数据类型是Queue
            //将根节点放入队列中，然后不断遍历队列
            TreeNode node=root;
            queue.add(node);

            while (!queue.isEmpty()) {//queue.size()>0都可以
                //因先进入的是根节点，故每层结点多少，队列大小就是多少
                int levelSize = queue.size();//获取当前队列的长度，这个长度相当于当前这一层的节点个数
                List<Integer> level = new ArrayList<>();//层序遍历的每一层
                //将队列中的元素都拿出来(也就是获取这一层的节点)，放到临时List的level中

                for (int i = 0; i < levelSize; i++) {
                    node = queue.poll();//队首元素出队
                    level.add(node.val);//处理当前节点，也就是对当前节点的语句就在这个位置写
                    //若是左右孩⼦存在，则存⼊左右孩⼦作为下⼀个层次
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                lists.add(level);//遍历完一层就把一层加入集合res,
            }
            return lists;
        }





        /*

        //⽅法⼆：递归（扩展思路）  没看明白
        //step1：首先判断二叉树是否为空，空树没有遍历结果。
        //step2:使用递归进行层次遍历输出，每次递归记录当前二叉树的深度，每当遍历到一个节点，如果为空直接返回。
        //step3:如果遍历的节点不为空，输出二维数组中一维数组的个数（即代表了输出的行数）小于深度，说明这个节点应该是新的一层，我们在二维数组中增加一个一维数组，然后再加入二叉树元素。
        //step4:如果不是step3的情况说明这个深度我们已经有了数组，直接根据深度作为下标取出数组，将元素加在最后就可以了。
        //step5:处理完这个节点，再依次递归进入左右节点，同时深度增加。因为我们进入递归的时候是先左后右，那么遍历的时候也是先左后右，正好是层次遍历的顺序。

        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> lists =new ArrayList<>();
            if(root==null){//如果是空，则直接返回
                return lists;
            }
            levelOrderFunc(lists,root,1);//递归层次遍历
            return lists;
        }


        private void levelOrderFunc(List<List<Integer>> lists, TreeNode root, int depth){
            if(root==null){
                return;
            }
            List<Integer> levelList=new ArrayList<>();//因为这个每次只保存一个节点
            if(lists.size()<depth){//层数小于深度，则开辟新的一层
                //List<Integer> levelList=new ArrayList<>();
                levelList.add(root.val);
                lists.add(levelList);
            }else{//其实是层数大于等于深度，那意味着本层的节点没有遍历完，递归一次层数会加1
                //List<Integer> levelList=ans.get(depth-1);
                levelList= lists.get(depth-1);
                levelList.add(root.val);
            }
            levelOrderFunc(lists,root.left,depth+1);
            levelOrderFunc(lists,root.right,depth+1);
        }

        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
