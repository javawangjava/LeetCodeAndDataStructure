/**
 * <p>给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。</p>
 *
 * <p>ref="https://baike.baidu.com/item/%E6%9C%80%E8%BF%91%E5%85%AC%E5%85%B1%E7%A5%96%E5%85%88/8918834?fr=aladdin"
 * target="_blank">百度百科</a>中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x
 * 的深度尽可能大（<strong>一个节点也可以是它自己的祖先</strong>）。”
 * </p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height:
 * 190px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * <strong>输出：</strong>3
 * <strong>解释：</strong>节点 <code>5 </code>和节点 <code>1 </code>的最近公共祖先是节点 <code>3 。</code>
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2018/12/14/binarytree.png" style="width: 200px; height:
 * 190px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * <strong>输出：</strong>5
 * <strong>解释：</strong>节点 <code>5 </code>和节点 <code>4 </code>的最近公共祖先是节点 <code>5 。</code>因为根据定义最近公共祖先节点可以为节点本身。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1,2], p = 1, q = 2
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数目在范围 <code>[2, 10<sup>5</sup>]</code> 内。</li>
 * <li><code>-10<sup>9</sup> <= Node.val <= 10<sup>9</sup></code></li>
 * <li>所有 <code>Node.val</code> <code>互不相同</code> 。</li>
 * <li><code>p != q</code></li>
 * <li><code>p</code> 和 <code>q</code> 均存在于给定的二叉树中。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 1863</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 236
 * 二叉树的最近公共祖先
 * @author wangweizhou
 * @date 2022-07-26 10:48:20
 */


public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {

        //测试代码
        // Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();


        //创建需要的结点
        TreeNode rootnode = new TreeNode(3);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(0);
        TreeNode node7 = new TreeNode(8);
        TreeNode node8 = new TreeNode(7);
        TreeNode node9 = new TreeNode(4);


        // 手动创建二叉树
        rootnode.left=node2;
        rootnode.right=node3;
        node2.left=node4;
        node2.right=node5;
        node3.left=node6;
        node3.right=node7;
        node5.left=node8;
        node5.right=node9;

        Solution solution = new LowestCommonAncestorOfABinaryTree().new Solution();
        TreeNode node= solution.lowestCommonAncestor(rootnode,node2,node9);
        System.out.println(node);


    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    class Solution {


        /*

        //⽅法⼆：递归（扩展思路）  前序递归遍历
        //祖先的定义： 若节点 p 在节点 root 的左（右）子树中，或 p=root ，则称 root 是 p 的祖先。
        //最近公共祖先的定义： 设节点 root 为节点 p,q 的某公共祖先，若其左子节点 root.left 和右子节点 root.right 都不是 p,q 的公共祖先，则称 root 是 “最近的公共祖先” 。

        //若 root 是 p,q 的 最近公共祖先 ，则只可能为以下情况之一：
        //1.p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
        //2.p=root ，且 q 在 root 的左或右子树中；
        //3.q=root ，且 p 在 root 的左或右子树中；


        //step1:如果o1和o2中的任一个和root匹配，那么root就是最近公共祖先。
        //step2:如果都不匹配，则分别递归左、右子树。
        //step3:如果有一个节点出现在左子树，并且另一个节点出现在右子树，则root就是最近公共祖先
        //step4:如果两个节点都出现在左子树，则说明最低公共祖先在左子树中，否则在右子树。

        // 前序递归遍历
        // 该方法是在以root为根节点的二叉树中找到节点p和q的最近公共祖先
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null||p==null||q==null) {// p，q为空指针结果为空，根节点root为空，也就是遍历完了整个子树没找到
                return null;
            }
            // 处理当前节点，当前节点就是节点p或者q，那么节点p或者q就是两者的最近公共祖先
            if (root.val == p.val || root.val == q.val) {// 在二叉树中找到了节点p,或者q。
                return root;
            }
            // 当前节点不是节点p或者q，那么在当前节点的左右子树中找
            TreeNode left = lowestCommonAncestor(root.left, p, q);//左⼦树寻找公共祖先
            TreeNode right = lowestCommonAncestor(root.right, p, q);//右⼦树寻找公共祖先
            if (left == null) {//在当前节点的左⼦树为没找到最近公共祖先，那么看当前节点的右子树中有没有最近公共祖先
                return right;
            }
            if (right == null) {//
                return left;
            }
            return root;// 左右都找到了
        }

        */





        // 解法1： 哈希表+记忆化存储+DFS   这个好理解记忆
        // 用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，
        // 再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先

        // 哈希表map的键key表示当前节点的值，value表示当前节点的父节点
        Map<Integer, TreeNode> parent = new HashMap<>();// 存储当前节点的父节点
        Set<Integer> visited = new HashSet<>();// 记录已经访问过的节点

        // DFS遍历并存储二叉树中每个节点的父节点
        // 注意在哈希表map中没有添加二叉树根节点的父节点，所以map.get()得到的是null,也就是二叉树根节点的父节点是null
        public void dfs(TreeNode node) {

            // 因为要在map中添加当前节点的左子节点的父节点，那么首先要对当前节点的左子节点判空，只研究数据节点的父节点
            if (node.left != null) {// 当前节点的左子节点不为空
                parent.put(node.left.val, node);// 将当期节点的左子节点的父节点添加到map中
                dfs(node.left);// 递归遍历当前节点的左子树
            }
            if (node.right != null) {// 遍历右子树
                parent.put(node.right.val, node);
                dfs(node.right);
            }
        }



        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null||p==null||q==null) {// 父节点或者p，q为空指针， 判空
                return null;
            }
            dfs(root);// 深度遍历存储二叉树每个节点的父节点
            // 循环遍历将节点p的所有父节点添加到哈希表set中
            while (p != null) {
                visited.add(p.val);
                p = parent.get(p.val);
            }
            // 循环遍历查找节点q的父节点，看q的父节点是否已经访问过，
            while (q != null) {
                if (visited.contains(q.val)) {// 当记录已经访问过的节点的哈希表set中已经有q节点，则说明q是最近的公共祖先
                    return q;
                }
                q = parent.get(q.val);
            }
            return null;//p和q没有公共祖先
        }




         /*

        // 解法3：前序遍历DFS  看着很乱
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root == null) return null;
            // 如果p,q为根节点，则公共祖先为根节点
            if (root.val == p.val || root.val == q.val) return root;
            // 如果p,q在左子树，则公共祖先在左子树查找
            if (find(root.left, p) && find(root.left, q)) {
                return lowestCommonAncestor(root.left, p, q);
            }
            // 如果p,q在右子树，则公共祖先在右子树查找
            if (find(root.right, p) && find(root.right, q)) {
                return lowestCommonAncestor(root.right, p, q);
            }
            // 如果p,q分属两侧，则公共祖先为根节点
            return root;
        }



        // 前序遍历DFS  在二叉树中找到节点c
        private boolean find(TreeNode root, TreeNode target) {
            if (root == null) {
                return false;
            }
            if (root.val == target.val) {// 处理当前节点
                return true;
            }
            return find(root.left, target) || find(root.right, target);// 后续遍历当前节点的左右子树
        }
        */



    }
//leetcode submit region end(Prohibit modification and deletion)

}
