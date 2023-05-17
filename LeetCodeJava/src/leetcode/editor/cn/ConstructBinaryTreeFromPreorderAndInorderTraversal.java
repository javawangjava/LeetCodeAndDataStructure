/**
 * <p>给定两个整数数组&nbsp;<code>preorder</code> 和 <code>inorder</code>&nbsp;，其中&nbsp;<code>preorder</code>
 * 是二叉树的<strong>先序遍历</strong>， <code>inorder</code>&nbsp;是同一棵树的<strong>中序遍历</strong>，请构造二叉树并返回其根节点。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" style="height: 302px; width: 277px;" />
 * <pre>
 * <strong>输入</strong><strong>:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * <strong>输出:</strong> [3,9,20,null,null,15,7]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> preorder = [-1], inorder = [-1]
 * <strong>输出:</strong> [-1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= preorder.length &lt;= 3000</code></li>
 * <li><code>inorder.length == preorder.length</code></li>
 * <li><code>-3000 &lt;= preorder[i], inorder[i] &lt;= 3000</code></li>
 * <li><code>preorder</code>&nbsp;和&nbsp;<code>inorder</code>&nbsp;均 <strong>无重复</strong> 元素</li>
 * <li><code>inorder</code>&nbsp;均出现在&nbsp;<code>preorder</code></li>
 * <li><code>preorder</code>&nbsp;<strong>保证</strong> 为二叉树的前序遍历序列</li>
 * <li><code>inorder</code>&nbsp;<strong>保证</strong> 为二叉树的中序遍历序列</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>数组</li><li>哈希表</li><li>分治</li><li>二叉树</li></div></div><br><div
 * ><li>👍 1659</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 105
 * 从前序与中序遍历序列构造二叉树
 *
 * @author wangweizhou
 * @date 2022-07-12 03:15:01
 */

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = solution.buildTree(preorder, inorder);
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


        // 使用哈希表在中序遍历中快速地定位根节点。
        // 对于哈希映射中的每个键值对，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置【数组下标】。

        // 前序遍历：根左右；中序遍历：左根右。
        //step1:前序遍历数组的第一个节点是根节点，先根据前序遍历第一个节点建立根节点。
        //step2:在中序遍历找到根节点【前序遍历的第一个节点】在中序遍历数组中的位置。
        // 通过中序遍历知道左右子树的节点个数【中序遍历根节点左侧就是左子树的节点】，
        // 然后再根据左右子树的节点个数【中序遍历根节点右侧就是右子树的节点】，将前序遍历数组分割成根节点和左右子树。
        //step3:再按照子树的节点数将两个遍历的序列分割成子数组，将子数组送入函数建立子树。
        //step4:直到子树的序列长度为0，结束递归。


        // 若根节点在中序遍历数组中的位置inRoot，利用该位置inRoot将中序遍历数组和前序遍历数组分成左右两部分。
        // 中序遍历的左右子树[inLeft,inRoot-1],【inRoot】,[inRoot+1,inRight]。
        // 父节点的左子树的节点个数：leftTreeSize = inRoot - inLeft
        // 前序遍历的左右子树【preLeft】[preLeft+1,preLeft+leftTreeSize],[preLeft+leftTreeSize+1,preRight]，

        // 这里中序遍历的作用就是将前序遍历分成左右子树，所以我们其实只要知道前序遍历中根节点在中序遍历中的位置就可以。
        // 前序遍历的根节点是每颗子树的最左侧的节点

        // 方法一：递归+哈希表  写法1  这个写法更好思考   归并思路
        //private Map<Integer, Integer> inorderMap;// 全局变量，也可以作为一个引用参数来传递
        //// 利用前序和中序遍历构造二叉树
        //public TreeNode buildTree(int[] preorder, int[] inorder) {
        //    // 判空
        //    if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length
        //     != inorder.length) {
        //        return null;
        //    }
        //    int length = preorder.length;// 同一个树，前序遍历和中序遍历的节点数相同
        //    // 利用哈希表快速确定中序遍历中根节点的位置，进而确定二叉树的左子树和右子树中节点的个数
        //    // 构造中序遍历的哈希映射：哈希表中键key是中序遍历数组中的元素，值value是该元素在中序遍历数组中对应的下标
        //    inorderMap = new HashMap<>();
        //    for (int i = 0; i < length; i++) {
        //        inorderMap.put(inorder[i], i);// 注意这里是节点的值和对应的下标，不是节点和对应的下标。
        //    }
        //    return myBuildTree(preorder, 0, length - 1, inorder, 0, length - 1);
        //}
        //
        //
        //
        //// 利用前序和中序递归构造二叉树，设计的是双闭区间，前后对应即可
        //private TreeNode myBuildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft,
        //                             int inRight) {
        //    if (preLeft > preRight) {// 前序遍历中没有元素了，也就是子树的长度序列为0，结束递归
        //        return null;
        //    }
        //    int rootVal = preorder[preLeft];// 前序遍历中的第一个节点就是根节点：preorder[preLeft]
        //    TreeNode root = new TreeNode(rootVal); // 以前序遍历的第一个节点建立根节点
        //    int inRootIndex = inorderMap.get(rootVal); // 在中序遍历中定位根节点,获取根节点在中序遍历中的下标
        //    // 从中序遍历中得到左子树的节点数目，以preroot为父节点的左子树【inLeft，inRoot-1】，
        //    int leftTreeSize = inRootIndex - inLeft;// [inLeft,inRoot)左子树不包括inRoot，所以这里没有+1
        //
        //    // 递归地构造左子树，并连接到根节点。起始位置数学归纳法
        //    // 递归地构造右子树，并连接到根节点。
        //    // 父节点在中序遍历数组中的位置inRoot，利用该位置inRoot将中序遍历数组和前序遍历数组分成左右两部分。
        //    // 中序遍历的左右子树[inLeft,inRoot-1],【inRoot】,[inRoot+1,inRight]。
        //    // 父节点的左子树的节点个数：leftTreeSize = inRoot - inLeft
        //    // 前序遍历的左右子树【preLeft】[preLeft+1,preLeft+leftTreeSize],[preLeft+leftTreeSize+1,preRight]，
        //    root.left = myBuildTree(preorder, preLeft + 1, preLeft + leftTreeSize, inorder, inLeft, inRootIndex - 1);
        //    root.right = myBuildTree(preorder, preLeft + leftTreeSize + 1, preRight, inorder, inRootIndex + 1,
        //    inRight);
        //    return root;
        //}




        // [left,right]区间的长度：len=right-left+1; right=len-1+left;
        // 方法一：递归+哈希表  写法2 这个写法更好思考   归并思路
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) {
                return null;
            }
            int len = inorder.length;// 同一个树，前序遍历和中序遍历的节点数相同
            // 利用哈希表快速确定中序遍历中根节点的位置，进而确定二叉树的左子树和右子树中节点的个数
            // 构造中序遍历的哈希映射：哈希表中键key是中序遍历数组中的元素，值value是该元素在中序遍历数组中对应的下标
            Map<Integer, Integer> inorderMap = new HashMap<>();
            for (int i = 0; i < len; i++) {
                inorderMap.put(inorder[i], i);// 注意这里是节点的值和对应的下标，不是节点和对应的下标。
            }
            return buildTreeFunc(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderMap);
        }


        private TreeNode buildTreeFunc(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft,
                                       int inRight,
                                       Map<Integer, Integer> integerMap) {
            if (preLeft > preRight) {// 前序遍历中没有元素了，也就是子树的长度序列为0，结束递归。注意这里要有递归的结束条件
                return null;
            }
            int rootVal = preorder[preLeft];// 前序遍历中的第一个节点就是根节点：preorder[preLeft]
            TreeNode root = new TreeNode(rootVal);// 以前序遍历的第一个节点建立根节点
            int rootInorderIndex = integerMap.get(rootVal);// 在中序遍历中定位根节点,获取根节点在中序遍历中的下标
            // 从中序遍历中得到左子树的节点数目，以preroot为父节点的左子树【inLeft，inRoot-1】，
            int leftLen = rootInorderIndex - inLeft;// [inLeft,inRoot)左子树不包括inRoot，所以这里没有+1

            // 递归地构造左子树，并连接到根节点。起始位置数学归纳法
            // 递归地构造右子树，并连接到根节点。
            // 父节点在中序遍历数组中的位置inRoot，利用该位置inRoot将中序遍历数组和前序遍历数组分成左右两部分。
            // 中序遍历的左右子树[inLeft,inRoot-1],【inRoot】,[inRoot+1,inRight]。
            // 父节点的左子树的节点个数：leftTreeSize = inRoot - inLeft
            // 前序遍历的左右子树【preLeft】[preLeft+1,preLeft+leftTreeSize],[preLeft+leftTreeSize+1,preRight]，

            // [left,right]区间的长度：len=right-left+1; right=len-1+left;
            root.left = buildTreeFunc(preorder, preLeft + 1, preLeft + leftLen, inorder, inLeft, rootInorderIndex - 1
                    , integerMap);// preorder的子区间的右边界：(leftLen-1+(preLeft + 1)=preLeft + leftLen)
            root.right = buildTreeFunc(preorder, preLeft + leftLen + 1, preRight, inorder, rootInorderIndex + 1,
                    inRight, integerMap);
            return root;
        }




        //⽅法⼀：递归  写法3
        //int rootIndex = 0;// 二叉树的根节点在前序遍历中的索引
        //public TreeNode buildTree(int[] preorder, int[] inorder) {
        //    if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder
        //    .length != inorder.length) {
        //        return null;
        //    }
        //    int length = preorder.length;// 同一个树，前序遍历和中序遍历的节点数相同
        //    Map<Integer, Integer> inorderMap = new HashMap<>();
        //    // 利用哈希表快速确定中序遍历中根节点的位置，进而确定二叉树的左子树和右子树中节点的个数
        //    // 构造中序遍历的哈希映射：哈希表中键key是中序遍历数组中的元素，值value是该元素在中序遍历数组中对应的下标
        //    for (int i = 0; i < length; i++) {
        //        inorderMap.put(inorder[i], i);
        //    }
        //    return buildTree(inorderMap, preorder, 0, length - 1);
        //}
        //
        //
        //// left表示前序遍历中数组的起始下标，right表示前序遍历中数组的最后下标。前序遍历数组[left，right]
        //public TreeNode buildTree(Map<Integer, Integer> map, int[] preorder, int left, int right) {
        //    if (left > right) {// 前序遍历中没有元素了，也就是子树的长度序列为0，结束
        //        return null;
        //    }
        //    int rootVal = preorder[rootIndex];// 从前序遍历中依次获取父节点的数据值
        //    TreeNode root = new TreeNode(rootVal);// 以前序遍历的第一个节点建立根节点
        //    rootIndex++;// 前序遍历中指针后移，子树的父节点后移
        //    // 这个要画图理解 map.get(rootVal) 是根节点在中序遍历中的数组下标，map.get(rootVal) - 1对应的是以rootVal为根节点的左子树的最右边的节点
        //    root.left = buildTree(map, preorder, left, map.get(rootVal) - 1);
        //    root.right = buildTree(map, preorder, map.get(rootVal) + 1, right);
        //    return root;
        //}


        ////解法2：栈
        //// 没看明白不管了
        ////step1：首先前序遍历第一个数字依然是根节点，并建立栈辅助遍历。
        ////step2:然后我们就开始判断，在前序遍历中相邻的两个数字必定是只有两种情况：要么前序遍历中后一个是前一个的左节点；要么前序遍历中后一个是前一个的右节点或者其祖先的右节点。
        ////step3:我们可以同时顺序遍历pre和in两个序列，判断是否是左节点，如果是左节点则不断向左深入，用栈记录祖先，如果不是需要弹出栈回到相应的祖先，然后进入右子树，整个过程类似非递归前序遍历。
        //
        //public TreeNode buildTree(int[] preorder, int[] inorder) {
        //    // 判空
        //    if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length
        //            != inorder.length) {
        //        return null;
        //    }
        //    Stack<TreeNode> roots = new Stack<>();
        //    int pre = 0;//前序遍历的遍历指针
        //    int in = 0;//中序遍历的遍历指针
        //
        //    TreeNode curRoot = new TreeNode(preorder[pre]);  //前序遍历第一个值作为根节点
        //    TreeNode root = curRoot;
        //    roots.push(curRoot);
        //    pre++;
        //    //遍历前序遍历的数组
        //    while (pre < preorder.length) {
        //        //出现了当前节点的值和中序遍历数组的值相等，寻找是谁的右子树
        //        if (curRoot.val == inorder[in]) {
        //            //每次进行出栈，实现倒着遍历
        //            while (!roots.isEmpty() && roots.peek().val == inorder[in]) {
        //                curRoot = roots.peek();
        //                roots.pop();
        //                in++;
        //            }
        //            //设为当前的右孩子
        //            curRoot.right = new TreeNode(preorder[pre]);
        //            //更新 curRoot
        //            curRoot = curRoot.right;
        //            roots.push(curRoot);
        //            pre++;
        //        } else {
        //            //否则的话就一直作为左子树
        //            curRoot.left = new TreeNode(preorder[pre]);
        //            curRoot = curRoot.left;
        //            roots.push(curRoot);
        //            pre++;
        //        }
        //    }
        //    return root;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
