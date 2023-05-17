/**
 * <p>给定一个&nbsp;<strong>完美二叉树&nbsp;</strong>，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：</p>
 *
 * <pre>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }</pre>
 *
 * <p>填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 <code>NULL</code>。</p>
 *
 * <p>初始状态下，所有&nbsp;next 指针都被设置为 <code>NULL</code>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2019/02/14/116_sample.png" style="height: 171px; width:
 * 500px;" /></p>
 *
 * <pre>
 * <b>输入：</b>root = [1,2,3,4,5,6,7]
 * <b>输出：</b>[1,#,2,3,#,4,5,6,7,#]
 * <b>解释：</b>给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 * </pre>
 *
 * <p><meta charset="UTF-8" /></p>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <b>输入：</b>root = []
 * <b>输出：</b>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点的数量在<meta charset="UTF-8" />&nbsp;<code>[0, 2<sup>12</sup>&nbsp;- 1]</code>&nbsp;范围内</li>
 * <li><code>-1000 &lt;= node.val &lt;= 1000</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong></p>
 *
 * <ul>
 * <li>你只能使用常量级额外空间。</li>
 * <li>使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>链表</li><li>二叉树</li></div></div><br
 * ><div><li>👍 830</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 116
 * 填充每个节点的下一个右侧节点指针
 *
 * @author wangweizhou
 * @date 2022-07-15 15:06:39
 */

//
//public class PopulatingNextRightPointersInEachNode {
//    public static void main(String[] args) {
//        //测试代码
//        Solution solution = new PopulatingNextRightPointersInEachNode().new Solution();
//    }
//
////leetcode submit region begin(Prohibit modification and deletion)
//
///*
//// Definition for a Node.
//class Node {
//    public int val;
//    public Node left;
//    public Node right;
//    public Node next;
//
//    public Node() {}
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, Node _left, Node _right, Node _next) {
//        val = _val;
//        left = _left;
//        right = _right;
//        next = _next;
//    }
//};
//*/
//
//
//    class Solution {
//
//
//        /*
//
//        public Node connect(Node root) {
//            if (root == null) {
//                return root;
//            }
//            // 初始化队列同时将第一层节点加入队列中，即根节点
//            Queue<Node> queue = new LinkedList<>();
//            queue.add(root);
//            // 外层的 while 循环迭代的是层数
//            while (queue.size() > 0) {
//                int levelSize = queue.size();//获取当前队列的长度，这个长度相当于二叉树当前这一层的节点个数
//                for (int i = 0; i < levelSize; i++) { // 遍历这一层的所有节点
//                    Node node = queue.poll();  // 从队首取出元素
//                    if (i < levelSize - 1) {    // 连接
//                        node.next = queue.peek();// queue.peek()就是本层栈中右侧节点
//                    }
//
//                    // 拓展下一层节点
//                    if (node.left != null) {
//                        queue.add(node.left);
//                    }
//                    if (node.right != null) {
//                        queue.add(node.right);
//                    }
//                }
//            }
//            return root;
//        }
//
//        */
//
//
//        //第一种 是这两个串联的节点都有一个共同的父节点，通过父节点就可以将这两个子节点串联起来
//        //第二种 是这两个串联的节点的父节点不同，对于这种情况，如果我们能将这一层的上一层串联好。那么可以通过父节点的next找到邻居，完成串联。
//        //root.right.next => root.next.left
//
//        public Node connect(Node root) {
//            if (root == null) {
//                return root;
//            }
//            Node node = root;
//            //循环条件是当前节点的left不为空，当只有根节点或所有叶子节点都出串联完后循环就退出了
//            while (node.left != null) {
//                Node curr = node;
//                while (curr != null) {
//                    //将tmp的左右节点都串联起来
//                    //注:外层循环已经判断了当前节点的left不为空
//                    curr.left.next = curr.right;//这两个串联的节点都有一个共同的父节点
//                    //下一个不为空说明上一层已经帮我们完成串联了
//                    if (curr.next != null) {
//                        curr.right.next = curr.next.left;//这两个串联的节点的父节点不同
//                    }
//                    //继续右边遍历
//                    curr = curr.next;//内层控制着层内的遍历
//                }
//                //从下一层的最左边开始遍历
//                node = node.left;//外层控制着层的遍历
//            }
//            return root;
//        }
//    }
////leetcode submit region end(Prohibit modification and deletion)
//
//}
//
//
//
//class Node {//和其他题目的Node定义冲突
//    public int val;
//    public Node left;
//    public Node right;
//    public Node next;
//
//    public Node() {
//    }
//
//    public Node(int _val) {
//        val = _val;
//    }
//
//    public Node(int _val, Node _left, Node _right) {
//        val = _val;
//        left = _left;
//        right = _right;
//    }
//    public Node(int _val, Node _left, Node _right, Node _next) {
//        val = _val;
//        left = _left;
//        right = _right;
//        next = _next;
//    }
//}
