/**
 * <p>给定一个二叉搜索树 <code>root</code> 和一个目标结果 <code>k</code>，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 <code>true</code>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/21/sum_tree_1.jpg" style="height: 229px; width:
 * 400px;" />
 * <pre>
 * <strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 9
 * <strong>输出:</strong> true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/09/21/sum_tree_2.jpg" style="height: 229px; width:
 * 400px;" />
 * <pre>
 * <strong>输入:</strong> root = [5,3,6,2,4,null,7], k = 28
 * <strong>输出:</strong> false
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li>二叉树的节点个数的范围是&nbsp;&nbsp;<code>[1, 10<sup>4</sup>]</code>.</li>
 * <li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li>
 * <li>题目数据保证，输入的 <code>root</code> 是一棵 <strong>有效</strong> 的二叉搜索树</li>
 * <li><code>-10<sup>5</sup>&nbsp;&lt;= k &lt;= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉搜索树</li><li>哈希表</li><li>双指针</li
 * ><li>二叉树</li></div></div><br><div><li>👍 425</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 653
 * 两数之和 IV - 输入二叉搜索树
 *
 * @author wangweizhou
 * @date 2022-09-05 17:55:19
 */

public class TwoSumIvInputIsABst {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new TwoSumIvInputIsABst().new Solution();
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


        // 最直观的思路是利用哈希表保存节点的值。可以采用任意遍历算法遍历输入的二叉搜索树，每遍历到一个节点（节点的值记为v），就在哈希表中查看是否存在值为k-v的节点。
        // 如果存在，就表示存在值之和等于k的两个节点。

        //// 解法1：写法3深度遍历 前序递归+哈希表  存在性问题，所以这里深度遍历方法有返回值，最后递归左右子树的结果用的逻辑或。
        //Set<Integer> set = new HashSet<>();// 全局变量
        //public boolean findTarget(TreeNode root, int k) {//
        //    if (root == null) {// 判空
        //        return false;
        //    }
        //    // 处理当前节点，注意要先检查哈希表中是否有（k - root.val），然后再将当前节点加入哈希表中
        //    if (set.contains(k - root.val)) {
        //        return true;
        //    }
        //    set.add(root.val);
        //    // 注意函数有返回值，所以左右子树也需要返回
        //    return (findTarget(root.left, k) || findTarget(root.right, k));// 处理左右子树，注意这种有返回值的递归形式的写法
        //}




        //// 解法1：写法2  广度优先搜索 + 哈希表
        //public boolean findTarget(TreeNode root, int k) {
        //    if (root == null) {
        //        return false;
        //    }
        //    Set<Integer> set = new HashSet<>();
        //    Queue<TreeNode> queue = new LinkedList<>();
        //    queue.add(root);
        //    while (queue.size() > 0) {
        //        // 处理当前节点
        //        TreeNode node = queue.poll();
        //        if (set.contains(k - node.val)) {// 每遍历到一个节点（节点的值记为v），就在哈希表中查看是否存在值为k-v的节点。
        //            return true;
        //        }
        //        set.add(node.val);
        //        if (node.left != null) {
        //            queue.add(node.left);
        //        }
        //        if (node.right != null) {
        //            queue.add(node.right);
        //        }
        //    }
        //    return false;
        //}



        // 思路2：深度优先搜索 递归写法+节点数据记忆化+双指针
        // 二叉排序树的中序遍历是递增的，所以对二叉排序树做中序遍历并将遍历的结果保存在有序集合中，然后对有序集合使用双指针进行遍历。

        public boolean findTarget(TreeNode root, int k) {
        	if(root==null){
        		return false;
        	}
        	List<Integer> lists=new ArrayList<>();
        	inorder(root,lists);
            // 二分法遍历有序集合
        	int left=0;
        	int right=lists.size()-1;
        	while(left<right){
        		int sum=lists.get(left)+lists.get(right);
        		if(sum==k){
        			return true;
        		}else if(sum>k){
        			right--;
        		}else{
        			left++;
        		}
        	}
        	return false;
        }


        // DFS 中序遍历递归
        // 按照二叉排序树的中序遍历，每遍历到一个节点就在链表中插入与树中节点的值相同的节点。
        private void inorder(TreeNode node, List<Integer> lists){
        	if(node==null){
        		return;
        	}
        	inorder(node.left,lists);
        	lists.add(node.val);
        	inorder(node.right,lists);
        }




        //// 解法2：双指针+迭代器重写
        //public boolean findTarget(TreeNode root, int k) {
        //    if (root == null) {
        //        return false;
        //    }
        //
        //    BSTIterator iterNext = new BSTIterator(root);
        //    BSTIteratorReversed iterPrev = new BSTIteratorReversed(root);
        //    int next = iterNext.next();
        //    int prev = iterPrev.prev();
        //
        //    while (next != prev) {
        //        int sum = next + prev;
        //        if (sum == k) {
        //            return true;
        //        } else if (sum > k) {
        //            prev = iterPrev.prev();
        //        } else {
        //            next = iterNext.next();
        //        }
        //    }
        //    return false;
        //}
    }



    //// 二叉搜索树的反向迭代器  从大到小遍历  其实就是将中序遍历的左根右调整为右根左来遍历。
    //// 二叉搜索树的右根左遍历迭代遍历的改写
    //class BSTIteratorReversed {//
    //    TreeNode curr;
    //    Deque<TreeNode> stack;
    //
    //    public BSTIteratorReversed(TreeNode root) {// 构造器
    //        curr = root;
    //        stack = new LinkedList<>();
    //    }
    //
    //    // 判断是否有前一个
    //    public boolean hasPrev() {
    //        return curr != null || !stack.isEmpty();
    //    }
    //
    //    // 返回二叉排序树的前一个
    //    public int prev() {
    //        while (curr != null) {
    //            stack.push(curr);
    //            curr = curr.right;
    //        }
    //        curr = stack.pop();
    //        int val = curr.val;
    //        curr = curr.left;
    //        return val;
    //    }
    //}
    //
    //
    //
    //// 二叉搜索树的正向迭代器  从小到大遍历  就是二叉搜索树的中序遍历的改写
    //class BSTIterator {
    //    TreeNode curr;
    //    Deque<TreeNode> stack;
    //
    //    public BSTIterator(TreeNode root) {
    //        curr = root;
    //        stack = new LinkedList<>();
    //    }
    //
    //    // 判断是否有后一个
    //    public boolean hasNext() {
    //        return curr != null || !stack.isEmpty();
    //    }
    //
    //    // 返回二叉排序树的后一个
    //    public int next() {
    //        while (curr != null) {
    //            stack.push(curr);
    //            curr = curr.left;
    //        }
    //        curr = stack.pop();
    //        int val = curr.val;
    //        curr = curr.right;
    //        return val;
    //    }
    //}


//leetcode submit region end(Prohibit modification and deletion)

}
