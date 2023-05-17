/**
 * <p>请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。</p>
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
 * <p>返回其层次遍历结果：</p>
 *
 * <pre>[
 * [3],
 * [20,9],
 * [15,7]
 * ]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ol>
 * <li><code>节点总数 &lt;= 1000</code></li>
 * </ol>
 * <div><div>Related Topics</div><div><li>树</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 253</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 32 - III
 * 从上到下打印二叉树 III
 *
 * @author wangweizhou
 * @date 2022-09-15 01:24:36
 */

public class CongShangDaoXiaDaYinErChaShuIiiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new CongShangDaoXiaDaYinErChaShuIiiLcof().new Solution();
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


        //// 解法1：层次遍历 注意奇偶层的遍历顺序不一样
        //public List<List<Integer>> levelOrder(TreeNode root) {
        //    List<List<Integer>> lists = new ArrayList<>();
        //    if (root == null) {
        //        return lists;
        //    }
        //    Deque<TreeNode> queue = new LinkedList<>();
        //    queue.add(root);
        //    boolean odd = true;//奇偶行标志符
        //    while (queue.size() > 0) {
        //        int levelSize = queue.size();
        //        List<Integer> list = new ArrayList<>();
        //        while (levelSize > 0) {
        //            //处理当前节点
        //            TreeNode node = queue.poll();
        //            levelSize--;
        //            // 这个只是将当前节点添加到每一层的list时的位置有不同
        //            if (odd) {// 当前层是奇数层，从左到右，则将当前节点添加到队列末尾
        //                list.add(node.val);
        //            } else {// 当前层是偶数层，从右到左，则将当前节点添加到队列头部
        //                list.add(0, node.val);
        //            }
        //            // 都是先遍历左子树后遍历右子树
        //            if (node.left != null) {
        //                queue.add(node.left);
        //            }
        //            if (node.right != null) {
        //                queue.add(node.right);
        //            }
        //        }
        //        // 每层结束，奇偶行标注符异号，将每行添加到结果lists中
        //        odd = !odd;
        //        lists.add(list);
        //    }
        //    return lists;
        //}




        //// 解法2：两个队列分别保存二叉树不同的层
        //public List<List<Integer>> levelOrder(TreeNode root) {
        //	List<List<Integer>> lists = new ArrayList<>();
        //	if (root == null) {
        //		return lists;
        //	}
        //	Deque<TreeNode> queue1 = new LinkedList<>();
        //	Deque<TreeNode> queue2 = new LinkedList<>();
        //	List<Integer> list=new ArrayList<>();
        //	queue1.add(root);
        //	boolean odd=true;
        //	while(!queue1.isEmpty()){
        //		TreeNode node=queue1.poll();
        //		if(odd){
        //			list.add(node.val);
        //		}else {
        //			list.add(0,node.val);
        //		}
        //		if(node.left!=null){
        //			queue2.add(node.left);
        //		}
        //		if(node.right!=null){
        //			queue2.add(node.right);
        //		}
        //		if(queue1.isEmpty()){
        //			queue1=queue2;
        //			queue2=new LinkedList<>();
        //			lists.add(list);
        //			list=new ArrayList<>();
        //			odd=!odd;
        //		}
        //	}
        //	return lists;
        //}



        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> lists=new ArrayList<>();
            List<Integer> list=new LinkedList<>();
            if(root==null){
                return lists;
            }
            Deque<TreeNode> nodeDeque=new ArrayDeque<>();
            TreeNode node=root;
            nodeDeque.offer(node);
            boolean odd=true;
            while (nodeDeque.size()>0){
                int levelSize=nodeDeque.size();
                for (int i = 0; i < levelSize; i++) {
                    node=nodeDeque.poll();
                    if(odd){
                        list.add(node.val);
                    }else {
                        list.add(0,node.val);
                    }
                    if(node.left!=null){
                        nodeDeque.offer(node.left);
                    }
                    if(node.right!=null){
                        nodeDeque.offer(node.right);
                    }
                }
                lists.add(list);
                list=new LinkedList<>();
                odd=!odd;
            }
            return lists;
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
