/**
 * 实现一个二叉搜索树迭代器类<code>BSTIterator</code> ，表示一个按中序遍历二叉搜索树（BST）的迭代器：
 * <div class="original__bRMd">
 * <div>
 * <ul>
 * <li><code>BSTIterator(TreeNode root)</code> 初始化 <code>BSTIterator</code> 类的一个对象。BST 的根节点 <code>root</code>
 * 会作为构造函数的一部分给出。指针应初始化为一个不存在于 BST 中的数字，且该数字小于 BST 中的任何元素。</li>
 * <li><code>boolean hasNext()</code> 如果向指针右侧遍历存在数字，则返回 <code>true</code> ；否则返回 <code>false</code> 。</li>
 * <li><code>int next()</code>将指针向右移动，然后返回指针处的数字。</li>
 * </ul>
 *
 * <p>注意，指针初始化为一个不存在于 BST 中的数字，所以对 <code>next()</code> 的首次调用将返回 BST 中的最小元素。</p>
 * </div>
 * </div>
 *
 * <p>你可以假设 <code>next()</code> 调用总是有效的，也就是说，当调用 <code>next()</code> 时，BST 的中序遍历中至少存在一个下一个数字。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2018/12/25/bst-tree.png" style="width: 189px; height: 178px;" />
 * <pre>
 * <strong>输入</strong>
 * ["BSTIterator", "next", "next", "hasNext", "next", "hasNext", "next", "hasNext", "next", "hasNext"]
 * [[[7, 3, 15, null, null, 9, 20]], [], [], [], [], [], [], [], [], []]
 * <strong>输出</strong>
 * [null, 3, 7, true, 9, true, 15, true, 20, false]
 *
 * <strong>解释</strong>
 * BSTIterator bSTIterator = new BSTIterator([7, 3, 15, null, null, 9, 20]);
 * bSTIterator.next();    // 返回 3
 * bSTIterator.next();    // 返回 7
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 9
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 15
 * bSTIterator.hasNext(); // 返回 True
 * bSTIterator.next();    // 返回 20
 * bSTIterator.hasNext(); // 返回 False
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点的数目在范围 <code>[1, 10<sup>5</sup>]</code> 内</li>
 * <li><code>0 <= Node.val <= 10<sup>6</sup></code></li>
 * <li>最多调用 <code>10<sup>5</sup></code> 次 <code>hasNext</code> 和 <code>next</code> 操作</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>你可以设计一个满足下述条件的解决方案吗？<code>next()</code> 和 <code>hasNext()</code> 操作均摊时间复杂度为 <code>O(1)</code> ，并使用 <code>O(h)
 * </code> 内存。其中 <code>h</code> 是树的高度。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>设计</li><li>二叉搜索树</li><li>二叉树</li><li>迭代器</li></div
 * ></div><br><div><li>👍 651</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 173
 * 二叉搜索树迭代器
 *
 * @author wangweizhou
 * @date 2022-11-17 15:31:39
 */
public class BinarySearchTreeIterator {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new BinarySearchTreeIterator().new Solution();
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

    class BSTIterator {

        // 思路1：
        // 如果允许修改输入的二叉搜索树，则可以在初始化迭代器时将它展平成除叶节点之外其他节点只有一个右子节点。
        // 这时二叉搜索树看起来像一个链表。然后用一个指针P指向展平后的二叉搜索树的根节点，如果指针P指向的节点不为null，那么函数hasNext将返回true。
        // 每次函数next被调用时都返回指针P指向节点的值，并将指针P朝着指向右子节点的指针向前移动一次。



        //// 思路3：
        //// 不修改输入的二叉排序树的同时优化空间效率。不储存二叉排序树的节点，利用遍历变量记忆遍历到达的位置。
        //// 注意到中序遍历的迭代代码中有一个外层while循环，循环的条件为true时循环体每执行一次就遍历二叉树的一个节点。
        //// 当外层while循环的条件为false时，二叉树中的所有节点都已遍历完。
        //// 因此，中序遍历的迭代代码中的外层while循环可以看成迭代器hasNext的判断条件，
        //// 而外层while循环体内执行的操作就是函数next执行的操作。

        // 起始这个就类似于写了一个类，类中包含全局变量，构造器，成员方法
        // 解法3：基于二叉树的中序排列实现二叉排序树的迭代器。  和下面的中序遍历对照
        //TreeNode curr;// 全局变量，表示遍历到的当前节点
        //Deque<TreeNode> stack;//
        //
        //// 中序遍历的初始化 构造器创建对象的同时初始化栈和二叉树的根节点
        //public BSTIterator(TreeNode root) {// 构造器中初始化栈和二叉树的根节点
        //    curr = root;// 初始化当前节点
        //    stack = new LinkedList<>();// 初始化栈
        //}
        //
        //
        //// 返回二叉排序树的后一个  中序遍历外层循环的循环体，就是遍历下一个节点代码
        //public int next() {
        //    while (curr != null) {
        //        stack.push(curr);
        //        curr = curr.left;
        //    }
        //    curr = stack.pop();
        //    int val = curr.val;// 处理当前节点
        //    curr = curr.right;
        //    return val;// 返回当前节点的值，也就是下一个节点的值
        //}
        //
        //
        //// 判断是否有后一个  中序遍历的外层循环，当前节点不空或者栈不空，那么就有下一个节点
        //public boolean hasNext() {
        //    return curr != null || !stack.isEmpty();
        //}



        // 思路2：
        // 可以先遍历记忆化形成链表，然后再遍历链表
        // 如果不允许修改输入的二叉排序树，那么可以在迭代器初始化时另外创建一个链表保存二叉树的所有节点。
        // 还是按照二叉排序树的中序遍历，每遍历到一个节点就在链表中插入与树中节点的值相同的节点。
        // 在完成迭代器初始化之后，这个链表中将包含n个节点。这个链表在初始化完成之后一直存在。
        TreeNode node;
        Deque<TreeNode> stack;
        int index;
        List<TreeNode> list;

        public BSTIterator(TreeNode root) {
            node = root;
            stack = new LinkedList<>();
            index = 0;
            list = new LinkedList<>();
            while (!stack.isEmpty() || node != null) {
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                node = stack.pop();
                list.add(node);
                node = node.right;
            }
        }


        public boolean hasNext() {
            return index < list.size();
        }

        public int next() {
            //int val = list.get(index).val;
            //index++;
            //return val;
            return list.get(index++).val;
        }


        //// 写法3： 二叉树的中序遍历
        //public List<Integer> inorderTraversalIter(TreeNode root){
        //    List<Integer> lists=new ArrayList<>();
        //    if(root==null){
        //        return lists;
        //    }
        //    Deque<TreeNode> stack=new ArrayDeque<>();
        //    TreeNode node=root;
        //    while (node!=null||!stack.isEmpty()){
        //        while (node!=null){
        //            stack.push(node);
        //            node=node.left;
        //        }
        //        node=stack.pop();
        //        lists.add(node.val);
        //        node=node.right;
        //    }
        //    return lists;
        //}


    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

//leetcode submit region end(Prohibit modification and deletion)

}
