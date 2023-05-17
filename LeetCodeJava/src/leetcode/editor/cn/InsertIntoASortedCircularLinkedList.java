/**
 * <p>给定<strong>循环单调非递减列表</strong>中的一个点，写一个函数向这个列表中插入一个新元素&nbsp;<code>insertVal</code> ，使这个列表仍然是循环非降序的。</p>
 *
 * <p>给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。</p>
 *
 * <p>如果有多个满足条件的插入位置，你可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。</p>
 *
 * <p>如果列表为空（给定的节点是 <code>null</code>），你需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2019/01/19/example_1_before_65p.jpg" style="height: 149px;
 * width: 250px;" /><br />
 * &nbsp;
 * <pre>
 * <strong>输入：</strong>head = [3,4,1], insertVal = 2
 * <strong>输出：</strong>[3,4,1,2]
 * <strong>解释：</strong>在上图中，有一个包含三个元素的循环有序列表，你获得值为 3 的节点的指针，我们需要向表中插入元素 2 。新插入的节点应该在 1 和 3 之间，插入之后，整个列表如上图所示，最后返回节点 3 。
 *
 * <img alt="" src="https://assets.leetcode.com/uploads/2019/01/19/example_1_after_65p.jpg" style="height: 149px; width: 250px;" />
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [], insertVal = 1
 * <strong>输出：</strong>[1]
 * <strong>解释：</strong>列表为空（给定的节点是 <code>null</code>），创建一个循环有序列表并返回这个节点。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1], insertVal = 0
 * <strong>输出：</strong>[1,0]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= Number of Nodes &lt;= 5 * 10<sup>4</sup></code></li>
 * <li><code>-10<sup>6</sup> &lt;= Node.val, insertVal &lt;= 10<sup>6</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li></div></div><br><div><li>👍 71</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 708
 * 循环有序列表的插入
 *
 * @author wangweizhou
 * @date 2022-08-25 09:29:19
 */

public class InsertIntoASortedCircularLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new InsertIntoASortedCircularLinkedList().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
};
*/


    class Solution {

        // 总结在排序的循环链表中插入新节点的规则。先试图在链表中找到相邻的两个节点，如果这两个节点的前一个节点的值比待插入的值小并且后一个节点的值比待插入的值大，那么就将新节点插入这两个节点之间。
        // 如果找不到符合条件的两个节点，即待插入的值大于链表中已有的最大值或小于已有的最小值，那么新的节点将被插入值最大的节点和值最小的节点之间。
        // 在上面的规则中，总是先试图从链表中找到符合条件的相邻的两个节点。
        // 如果开始的时候链表中的节点数小于2，那么应该有两种可能。
        // 第1种可能是开始的时候链表是空的，一个节点都没有。此时插入一个新的节点，该节点成为循环链表中的唯一节点，那么next指针指向节点自己。
        // 第2种可能是开始的时候链表中只有一个节点，插入一个新的节点之后，两个节点的next指针互相指向对方。


        // 解法1：遍历 循环单调非递减列表
        // 如果循环链表中的节点数大于 1，则需要从头节点开始遍历循环链表，寻找插入新节点的位置，使得插入新节点之后的循环链表仍然保持有序。
        // 待插入位置，情形1：单调递增中间段， 情形2：最大值和最小值之间
        //
        //public Node insert(Node head, int insertVal) {
        //    Node insert = new Node(insertVal);
        //    if (head == null) {//循环链表为空，则插入一个新节点并将新节点的 next 指针指向自身
        //        insert.next = insert;
        //        return insert;
        //    }
        //
        //    //循环链表的头节点的 next 指针指向自身，则循环链表中只有一个节点，在头节点之后插入新节点，将头节点的 next 指针指向新节点，将新节点的 next指针指向头节点
        //    if (head.next == head) {
        //        insert.next = head;
        //        head.next = insert;
        //        return head;
        //    }
        //
        //    Node curr = head;//链表遍历指针
        //
        //    while (curr.next!= head) {//循环遍历整个链表，找到待插入位置
        //        if (curr.val <=insertVal&& insertVal <= curr.next.val) {//在单调递增段中间找到待插入位置
        //            break;
        //        }
        //        // curr.val>curr.next.val 表示两最值中间。链表环本身是递增的环
        //        // curr.val <=insertVal表示比最大值大；insertVal <= curr.next.val表示比最小值小
        //        if (curr.val>curr.next.val&&(curr.val <=insertVal|| insertVal <= curr.next.val)) {// 待插入位置在递增突然下降的位置
        //            break;
        //        }
        //        curr = curr.next;//遍历指针后移
        //    }
        //    insert.next = curr.next;//将待插入位置之后的部分连接到待插入节点上
        //    curr.next = insert;//将待插入节点连接到待插入节点之前的节点的next域
        //    return head;
        //}



        // 解法2：遍历找到待插入位置
        public Node insert(Node head, int insertVal) {
            Node insertNode = new Node(insertVal);
            if (head == null) {// 链表是空的，一个节点都没有。此时插入一个新的节点，该节点成为循环链表中的唯一节点，那么next指针指向节点自己。
                head = insertNode;
                head.next = head;
            } else if (head.next == head) {// 链表中只有一个节点，插入一个新的节点之后，两个节点的next指针互相指向对方。
                head.next = insertNode;
                insertNode.next = head;
            } else {
                insertCore(head, insertNode);
            }
            return head;
        }


        // 当链表的节点数大于2时，找到待插入位置
        private void insertCore(Node head, Node insertNode) {
            Node curr = head;
            Node nextNode = head.next;
            Node biggest = head;
            // !(curr.val<= insertNode.val&& insertNode.val <= nextNode.val) 待插入节点位置不是中间节点
            // nextNode !=head  没有遍历完整个环
            // 循环结束条件找到环中最大的节点或者待插入位置是中间节点
            while (!(curr.val <= insertNode.val && insertNode.val <= nextNode.val) && nextNode != head) {
                curr = nextNode;
                nextNode = nextNode.next;
                if (curr.val >= biggest.val) {// 遍历到环状节点得到最大节点
                    biggest = curr;
                }
            }

            if (curr.val <= insertNode.val && insertNode.val <= nextNode.val) {// 待插入节点为上升区间的中间节点
                curr.next = insertNode;
                insertNode.next = nextNode;
            } else {// 待插入位置在最值中间
                insertNode.next = biggest.next;
                biggest.next = insertNode;
            }
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
