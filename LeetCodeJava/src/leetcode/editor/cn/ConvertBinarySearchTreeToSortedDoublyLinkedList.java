/**
 * <p>将一个 <strong>二叉搜索树</strong> 就地转化为一个 <strong>已排序的双向循环链表</strong> 。</p>
 *
 * <p>对于双向循环列表，你可以将左右孩子指针作为双向循环链表的前驱和后继指针，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。</p>
 *
 * <p>特别地，我们希望可以 <strong>就地</strong> 完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中最小元素的指针。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [4,2,5,1,3]
 *
 * <img src="https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png" style="width: 400px;" />
 * <strong>输出：</strong>[1,2,3,4,5]
 *
 * <strong>解释：</strong>下图显示了转化后的二叉搜索树，实线表示后继关系，虚线表示前驱关系。
 * <img src="https://assets.leetcode.com/uploads/2018/10/12/bstdllreturnbst.png" style="width: 400px;" />
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [2,1,3]
 * <strong>输出：</strong>[1,2,3]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * <strong>解释：</strong>输入是空树，所以输出也是空链表。
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-1000 <= Node.val <= 1000</code></li>
 * <li><code>Node.left.val < Node.val < Node.right.val</code></li>
 * <li><code>Node.val</code> 的所有值都是独一无二的</li>
 * <li><code>0 <= Number of Nodes <= 2000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉搜索树</li><li>链表</li><li>二叉树</li><li
 * >双向链表</li></div></div><br><div><li>👍 177</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 426
 * 将二叉搜索树转化为排序的双向链表
 *
 * @author wangweizhou
 * @date 2022-07-23 18:40:08
 */

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ConvertBinarySearchTreeToSortedDoublyLinkedList().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/


    class Solution {


        //// ⽅法⼀：DFS 递归中序遍历（推荐使⽤）
        Node first = null;// 二叉搜索树的最⼩值，先定为null
        Node prev = null;// 二叉搜索树中中序遍历当前值的上⼀位，初值为最⼩值，先定为null

        public Node treeToDoublyList(Node root) {
            if (root == null) {
                return null;
            }
            inorderDfs(root);
            // 将双向链表成环
            prev.right = first;
            first.left = prev;
            return first;
        }


        // 中序递归遍历将二叉排序树转换成双向链表
        // 中序遍历会一直向左遍历找到最左的节点
        private void inorderDfs(Node node) {
            if (node == null) {// 中序递归，叶⼦为空则返回
                return;
            }
            inorderDfs(node.left);// 递归左子树，⾸先递归到最左边最⼩值
            // 处理当前节点，这里需要转换为双向链表，需要保存上一个节点
            // 当prev或者first为null时，表明这时候当前节点node是二叉搜索树的最小节点，也就是最左侧的节点。这时候需要初始化first与last
            //if (prev == null) {// 当prev是空，找到最⼩值，也就是递归到最左边，初始化first与last
            if (first == null) {// 当first是空，找到最⼩值，也就是递归到最左边，初始化first与last
                first = node;
                prev = node;
            } else {// prev != null,这时候当前节点node不是二叉搜索树的最小节点
                prev.right = node;// 前一个节点的右指针域指向当前节点
                node.left = prev;// 当前节点的左指针域指向前一个节点
                prev = node;//pre后移
            }
            inorderDfs(node.right);// 递归右子树
        }






        //// ⽅法⼆：迭代  中序遍历（扩展思路）
        //public Node treeToDoublyList(Node root) {
        //    if (root == null) {
        //        return root;
        //    }
        //    Deque<Node> stack = new LinkedList<>(); //设置栈⽤于遍历
        //    Node node = root;
        //    Node first = null;// first表示二叉搜索树中最小的元素
        //    Node pre = null;// pre表示二叉搜索树中上一个元素
        //
        //    while (node != null || !stack.isEmpty()) {
        //        while (node != null) {//直到没有左节点，向左遍历到最左的叶子节点的子节点。
        //            stack.push(node);
        //            node = node.left;
        //        }
        //
        //        node = stack.pop();
        //        // 处理当前节点值，这里pre也相当于标志符
        //        if (pre == null) {// 找到二叉树最左边的节点，也就是递归到最左边，初始化first与pre
        //            first = node;// 最左元素即链表的第一个数据元素节点
        //            pre = node;
        //        } else {//
        //            pre.right = node;// 前一个节点的右指针域指向当前节点
        //            node.left = pre;// 当前节点的左指针域指向前一个节点
        //            pre = node;//pre后移
        //        }
        //
        //        // // 下面这种使用标识符思路也很好
        //        //if(firstRound){//最左元素即链表的第一个数据元素。//找到最⼩值，也就是递归到最左边，初始化first与pre
        //        //    first=node;//最左元素即链表的第一个数据元素节点
        //        //    pre=node;
        //        //    firstRound=false;
        //        //} else{//
        //        //    pre.right=node;// 前一个节点的右指针域指向当前节点
        //        //    node.left=pre;// 当前节点的左指针域指向前一个节点
        //        //    pre=node;//pre后移
        //        //}
        //
        //        node = node.right;
        //    }
        //
        //    // 上面循环外层循环结束时,node是空指针。将链表成环
        //    first.left = pre;
        //    pre.right = first;
        //    return first;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}


/*

class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val,Node left,Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

*/
