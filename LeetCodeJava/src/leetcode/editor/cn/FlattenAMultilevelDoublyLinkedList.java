/**
 * <p>你会得到一个双链表，其中包含的节点有一个下一个指针、一个前一个指针和一个额外的 <strong>子指针</strong>
 * 。这个子指针可能指向一个单独的双向链表，也包含这些特殊的节点。这些子列表可以有一个或多个自己的子列表，以此类推，以生成如下面的示例所示的 <strong>多层数据结构</strong> 。</p>
 *
 * <p>给定链表的头节点&nbsp;<font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span
 * style="font-size:12.6px"><span style="background-color:#f9f2f4">head</span></span></font></font>&nbsp;，将链表
 * <strong>扁平化</strong> ，以便所有节点都出现在单层双链表中。让 <code>curr</code> 是一个带有子列表的节点。子列表中的节点应该出现在<strong>扁平化列表</strong>中的&nbsp;
 * <code>curr</code> <strong>之后</strong> 和&nbsp;<code>curr.next</code>&nbsp;<strong>之前</strong> 。</p>
 *
 * <p>返回 <em>扁平列表的 <code>head</code>&nbsp;。列表中的节点必须将其 <strong>所有</strong> 子指针设置为&nbsp;<code>null</code>&nbsp;。</em></p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/11/09/flatten11.jpg" style="height:339px; width:700px" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * <strong>输出：</strong>[1,2,3,7,8,11,12,9,10,4,5,6]
 * <strong>解释：</strong>输入的多级列表如上图所示。
 * 扁平化后的链表如下图：
 * <img src="https://assets.leetcode.com/uploads/2021/11/09/flatten12.jpg" style="height:69px; width:1000px" />
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/11/09/flatten2.1jpg" style="height:200px; width:200px" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1,2,null,3]
 * <strong>输出：</strong>[1,3,2]
 * <strong>解释：</strong>输入的多级列表如上图所示。
 * 扁平化后的链表如下图：
 * <img src="https://assets.leetcode.com/uploads/2021/11/24/list.jpg" style="height:87px; width:300px" />
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = []
 * <strong>输出：</strong>[]
 * <strong>说明：</strong>输入中可能存在空列表。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>节点数目不超过 <code>1000</code></li>
 * <li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>如何表示测试用例中的多级链表？</strong></p>
 *
 * <p>以 <strong>示例 1</strong> 为例：</p>
 *
 * <pre>
 * 1---2---3---4---5---6--NULL
 * |
 * 7---8---9---10--NULL
 * |
 * 11--12--NULL</pre>
 *
 * <p>序列化其中的每一级之后：</p>
 *
 * <pre>
 * [1,2,3,4,5,6,null]
 * [7,8,9,10,null]
 * [11,12,null]
 * </pre>
 *
 * <p>为了将每一级都序列化到一起，我们需要每一级中添加值为 null 的元素，以表示没有节点连接到上一级的上级节点。</p>
 *
 * <pre>
 * [1,2,3,4,5,6,null]
 * [null,null,7,8,9,10,null]
 * [null,11,12,null]
 * </pre>
 *
 * <p>合并所有序列化结果，并去除末尾的 null 。</p>
 *
 * <pre>
 * [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
 * </pre>
 *
 * <ul>
 * </ul>
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍
 * 354</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 430
 * 扁平化多级双向链表
 *
 * @author wangweizhou
 * @date 2022-07-06 02:46:52
 */

public class FlattenAMultilevelDoublyLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FlattenAMultilevelDoublyLinkedList().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

    class Solution {


        // 由此可知，展平的规则是一个节点的子链展平之后将插入该节点和它的下一个节点之间。
        // 由于子链表中的节点也可能有子链表，因此这里的链表是一个递归的结构。
        // 在展平子链表时，如果它也有自己的子链表，那么它嵌套的子链表也要一起展平。
        // 嵌套子链表和外层子链表的结构类似，可以用同样的方法展平，因此可以用递归函数来展平链表。

        // 写法1:链表要断开，可以用临时变量保留下一个节点
        public Node flatten(Node head) {
            if (head == null) {
                return head;
            }
            flattenGetTail(head);
            return head;
        }


        // 递归函数flattenGetTail在展平以head为头节点的链表之后返回链表的尾节点。
        // 在该函数中需要逐一扫描链表中的节点。
        // 如果一个节点node有子链表，由于子链表也可能有嵌套的子链表，因此先递归调用flattenGetTail函数展平子链表，
        // 子链表展平之后的头节点是child，尾节点是childTail。
        // 最后将展平的子链表插入节点node和它的下一个节点next之间，即把展平的子链表的头节点child插入节点node之后，并将尾节点childTail插入节点next之前。


        //private Node flattenGetTail(Node head) {
        //    if (head == null) {
        //        return head;
        //    }
        //    Node node = head;
        //    Node tail = null;
        //    while (node != null) {
        //        Node next = node.next;
        //        if (node.child != null) {
        //            Node childHead = node.child;
        //
        //            Node childTail = flattenGetTail(childHead);
        //            node.child = null;
        //            node.next = childHead;
        //            childHead.prev = node;
        //
        //            childTail.next = next;
        //            if(next!=null){
        //                next.prev = childTail;
        //            }
        //            tail = childTail;
        //        } else {
        //            tail = node;
        //        }
        //        node = next;
        //    }
        //    return tail;
        //}




        // 展开以head为头节点的链表，展开之后头节点是head,尾节点是tail。

        // flattenGetTail(Node head)返回值是展开的链表的尾节点。
        private Node flattenGetTail(Node head) {
            Node curr = head;// 遍历链表的节点指针
            Node tail = null;// 当前遍历到的链表的尾节点
            while (curr != null) {
                Node next = curr.next;// 保留当前节点的下一个节点，方便后面将子链连接到主链上
                //  如果有子节点，那么首先处理子节点
                if (curr.child != null) {// 当前节点有子链，展开之后头节点是child,尾节点是childTail
                    // 下面获得了当前子链的头节点和尾节点
                    Node child = curr.child;// 保留子链的头节点
                    Node childTail = flattenGetTail(curr.child);// 展开子链，并获得子链的尾节点

                    curr.child = null;// 递归展开子链之后，将子链从父节点断开

                    // 将整个子链插入到节点node和node的下一个节点之间
                    // 将子链头节点插入node之后
                    curr.next = child;
                    child.prev = curr;
                    // 将尾节点和node的下一个节点连接起来
                    childTail.next = next;
                    if (next != null) {// 因为要调用next.prev，所以要判空，有可能next是空节点。
                        next.prev = childTail;
                    }
                    tail = childTail;// 子链插入主链之后更新当前已经遍历的链表的尾节点，遍历到的链表的尾节点就是子链的尾节点
                } else {
                    tail = curr;// 当前节点没有子链，则当前节点就是已经遍历到的尾节点。更新当前遍历到的链表的尾节点
                }
                curr = next;// 遍历节点后移
            }
            return tail;// 递归函数flattenGetTail在展平以head为头节点的链表之后返回链表的尾节点。
        }






    }
//leetcode submit region end(Prohibit modification and deletion)

}


class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node left;
    public Node right;

    public Node(int val) {
        this.val = val;
    }


    public Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }


    public Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public Node(Node prev, Node next) {
        this.prev = prev;
        this.next = next;
    }

    public Node(Node child) {
        this.child = child;
    }

    public Node(int val, Node prev, Node next, Node child) {
        this.val = val;
        this.prev = prev;
        this.next = next;
        this.child = child;
    }
}
