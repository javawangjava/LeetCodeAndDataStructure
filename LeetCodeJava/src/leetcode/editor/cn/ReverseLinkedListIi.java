/**
 * 给你单链表的头指针 <code>head</code> 和两个整数 <code>left</code> 和 <code>right</code> ，其中 <code>left <= right</code> 。请你反转从位置
 * <code>left</code> 到位置 <code>right</code> 的链表节点，返回 <strong>反转后的链表</strong> 。
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/rev2ex2.jpg" style="width: 542px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5], left = 2, right = 4
 * <strong>输出：</strong>[1,4,3,2,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [5], left = 1, right = 1
 * <strong>输出：</strong>[5]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点数目为 <code>n</code></li>
 * <li><code>1 <= n <= 500</code></li>
 * <li><code>-500 <= Node.val <= 500</code></li>
 * <li><code>1 <= left <= right <= n</code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>进阶：</strong> 你可以使用一趟扫描完成反转吗？</p>
 * <div><div>Related Topics</div><div><li>链表</li></div></div><br><div><li>👍 1340</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Objects;

/**
 * 92
 * 反转链表 II
 *
 * @author wangweizhou
 * @date 2022-07-16 23:30:11
 */
public class ReverseLinkedListIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ReverseLinkedListIi().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode ans = solution.reverseBetween(node1, 2, 7);
        print(ans);
    }

    private static void print(ListNode head) {
        //   判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //遍历打印节点不包含头结点，要从真正的第一个元素节点开始遍历
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + ",");//  输出节点信息
            temp = temp.next;//curr后移，遍历当前链表
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    class Solution {


        // 方法1：写法3一次遍历+反转指定区间列表
        // 注意题干没有约束left和right一定小于链表节点数，自己也没有单独处理
        public ListNode reverseBetween(ListNode head, int left, int right) {
            if (head == null || head.next == null || left < 1) {
                return head;
            }
            ListNode dummy = new ListNode(-1, head);// 哨兵节点
            ListNode node = dummy;
            int count = 0;
            ListNode prev = dummy;// 待反转链表子串的前一个节点
            ListNode end = dummy;// 反转链表子串的最后一个节点
            ListNode next = dummy;// 反转链表子串的后一个节点

            while (count != left - 1 && node != null) {// 遍历链表直到待反转链表的前一个节点
                count++;
                node = node.next;
            }
            prev = node;// 待反转链表子串的前一个节点

            while (count != right && node != null) {// 遍历链表直到待反转链表的最后一个节点
                count++;
                node = node.next;
            }
            end = node;// 待反转链表子串的最后一个节点
            next = end.next;// 待反转链表子串的最后一个节点的后一个节点

            ListNode[] revList = reverseList(prev.next, end);
            prev.next = revList[0];
            revList[1].next = next;
            return dummy.next;
        }



        // 反转链表  没有哨兵节点
        private ListNode[] reverseList(ListNode head, ListNode tail) {
            if (head == null || tail == null) {
                return null;
            }
            ListNode node = head;
            ListNode newHead = null;
            while (node != null) {
                ListNode next = node.next;
                node.next = newHead;
                newHead = node;
                node = next;
                if (newHead == tail) {
                    break;
                }
            }
            return new ListNode[]{tail, head};
        }



        // 方法1：写法2一次遍历+反转指定区间列表
        // 注意题干没有约束left和right一定小于链表节点数，自己也没有单独处理
        //public ListNode reverseBetween(ListNode head, int left, int right) {
        //    if (head == null || head.next == null) {
        //        return head;
        //    }
        //    ListNode dummy=new ListNode(-1,head);// 哨兵节点
        //    ListNode node=dummy;
        //    int count=0;
        //    ListNode prev=null;// 反转链表子串的前一个节点
        //    ListNode next=null;// 反转链表子串的后一个节点
        //    ListNode end=null;// 反转链表子串的最后一个节点
        //    while(node!=null){
        //        if(count==left-1){
        //            prev=node;
        //        }
        //        if(count==right){
        //            end=node;
        //            next=end.next;
        //            break;
        //        }
        //        count++;
        //        node=node.next;
        //    }
        //
        //    ListNode[] revList=reverseList(prev.next,end);
        //
        //    prev.next=revList[0];
        //    revList[1].next=next;
        //    return dummy.next;
        //}


        //// 反转链表  没有哨兵节点
        //private ListNode[] reverseList(ListNode head,ListNode tail){
        //    if(head==null||tail==null){
        //        return null;
        //    }
        //    ListNode node=head;
        //    ListNode newHead =null;
        //    while(node!=null){
        //        ListNode next=node.next;
        //        node.next= newHead;
        //        newHead=node;
        //        node=next;
        //        if(newHead==tail){
        //            break;
        //        }
        //    }
        //    return new ListNode[]{tail,head};
        //}




        //// 写法1：一次遍历，使用头插法
        ////curr：永远指向待反转区域的第一个节点 left，left的位置随着反转而发生变化，但是curr指向的元素不会发生变化；
        ////next：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
        ////pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变。
        //// 核心思想就是把next头插法插入到pre节点之后

        //public ListNode reverseBetween(ListNode head, int left, int right) {
        //    if (head == null||head.next==null) {
        //        return head;
        //    }
        //
        //    ListNode dummyHead = new ListNode(-1); // 设置哨兵结点避免单独处理第一个节点
        //    dummyHead.next=head;
        //    // pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变。
        //    ListNode pre=dummyHead;
        //    // 找到待反转区间的前一个节点
        //    for (int i = 0; i < left-1; i++) {
        //        pre=pre.next;
        //    }
        //    // curr指向当前待反转节点，初始化待反转区域的第一个节点 left；
        //    ListNode curr=pre.next;
        //
        //    // 每一轮原地头插法反转
        //    for (int i = 0; i < right-left; i++) {
        //        //nextNode：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
        //        ListNode nextNode = curr.next;// 保存下一个节点nextNode
        //        curr.next=nextNode.next;// 将nextNode后的部分连接到待反转节点之后，同时也将curr和nextNode断开了
        //        nextNode.next=pre.next;// 将已经反转的部分pre.next【pre之后到curr部分表示已经反转的部分】连接到nextNode之后
        //        pre.next=nextNode;// 将反转后的链表的头结点重新连接到pre后面
        //    }
        //    return dummyHead.next;
        //}



    }
//leetcode submit region end(Prohibit modification and deletion)

}
