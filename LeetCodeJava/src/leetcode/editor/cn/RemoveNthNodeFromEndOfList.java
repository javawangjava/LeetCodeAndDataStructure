/**
 * <p>给你一个链表，删除链表的倒数第&nbsp;<code>n</code><em>&nbsp;</em>个结点，并且返回链表的头结点。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg" style="width: 542px; height:
 * 222px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5], n = 2
 * <strong>输出：</strong>[1,2,3,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1], n = 1
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1,2], n = 1
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中结点的数目为 <code>sz</code></li>
 * <li><code>1 &lt;= sz &lt;= 30</code></li>
 * <li><code>0 &lt;= Node.val &lt;= 100</code></li>
 * <li><code>1 &lt;= n &lt;= sz</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你能尝试使用一趟扫描实现吗？</p>
 * <div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 2083</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 19
 * 删除链表的倒数第 N 个结点
 */

public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new RemoveNthNodeFromEndOfList().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        //node1.next=node2;
        //node2.next=node3;
        //node3.next=node4;
        //node4.next=node5;
        //node5.next=node6;

        Solution solution = new RemoveNthNodeFromEndOfList().new Solution();

        ListNode ans = solution.removeNthFromEnd(node1, 5);
        while (ans != null) {
            System.out.println(ans.val);
            ans = ans.next;
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


        // 为了实现只遍历链表一次就能找到倒数第k+1个节点，可以定义两个指针。
        // 第1个指针P1从链表的头节点开始向前走k步，第2个指针P2保持不动；从第k+1步开始指针P2也从链表的头节点开始和指针P1以相同的速度遍历。
        // 由于两个指针的距离始终保持为k，当指针P1指向链表的尾节点时指针P2正好指向倒数第k+1个节点。


        // 解法3：用双指针--快慢指针
        // 单链表删除节点，找到倒数第K个节点的前一个节点，这样能避免删除最后一个节点时空节点的处理。
        // 1.首先遍历链表，使得右指针与左指针差n个位置；
        // 2.左右指针一起移动，直至右指针移动到链表末尾，那么左指针就是链表倒数第n个节点的前一个节点。


        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null || n <= 0) {// 链表判空和k的规范性，这个是自己约定的处理方式
                return head;
            }
            ListNode dummyHead = new ListNode(-1, head);//将原始链表【数据节点】添加到哨兵节点的后面
            ListNode slow = dummyHead;// 慢指针
            ListNode fast = dummyHead;// 快指针

            for (int i = 0; i < n; i++) {// 快指针比慢指针提前n个位置
                if (fast.next != null) {
                    fast = fast.next;
                } else {
                    // 题干并没有说n与链表节点个数的问题，所以这里自定义处理方式。
                    // 当n大于链表节点数目时，自己约定返回头节点
                    return head;
                }
            }

            // 判断右指针是否遍历到链表尾部，最后一个数据节点的指针域为空，也就是没有后续节点了
            // while循环结束，fast就移动到链表最后一个节点，slow就移动到待删除节点的前一个【也就是倒数第（n+1）个节点】
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }
            //根据前面说明，slow至少是倒数第2个节点，所以 slow.next一定非空，不需要判空
            slow.next = slow.next.next;
            return dummyHead.next;
        }




        // 其他解法：用栈或者扫描两遍用计数器都可以

        ////解法1：遍历两次用计数器
        //// 如果可以遍历链表两次，那么这个问题就会变得简单一些。
        //// 在第1次遍历链表时，可以得出链表的节点总数n。在第2次遍历链表时，可以找出链表的第n-k个节点（即倒数第k+1个节点）。
        //// 然后把倒数第k+1个节点的next指针指向倒数第k-1个节点，这样就可以把倒数第k个节点从链表中删除。

        //public ListNode removeNthFromEnd(ListNode head, int n) {
        //     ListNode dummy = new ListNode(0, head);//是添加一个哑节点（dummy node），它的 next 指针指向链表的头节点
        //     int length = getLength(head);
        //     ListNode curr = dummy;//curr遍历辅助指针
        //     //哑节点（dummy node）是无效节点，只是为了便于处理引入的。从第一个数据节点开始进行遍历
        //     for (int i = 1; i < length - n + 1; i++) {//(length-n+1就是倒数第n个元素，循环结束就找到待删除元素的前一个元素
        //         curr = curr.next;
        //     }
        //    //根据前面说明，slow至少是倒数第2个节点，所以 slow.next一定非空，不需要判空
        //     curr.next = curr.next.next;
        //     return dummy.next;
        // }
        //
        //
        // // 获取链表长度，下面这个链表没有哑节点（dummy node），
        // // 做题时要看清单链表有没有哑节点（dummy node），处理方法的细节有些不一样
        //
        // private int getLength(ListNode head) {
        //     int length = 0;
        //     ListNode curr = head;//遍历辅助指针
        //     while (curr != null) {
        //         length++;
        //         curr = curr.next;//后移
        //     }
        //     return length;
        // }




     /*
        //解法2：用栈空间换时间
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(0, head);
            ListNode curr = dummy;
            Deque<ListNode> stack = new LinkedList<>();
            // 将链表中所有节点压入栈中
            while (curr != null) {
                stack.push(curr);
                curr = curr.next;
            }

            // 从栈中弹出节点，直到倒数前n个节点，结束时栈顶元素就是倒数第n+1个节点
            for (int i = 0; i < n; i++) {
                stack.pop();
            }
            stack.peek().next=stack.peek().next.next;//stack.peek()栈顶元素就是待删除元素的前驱
            return dummy.next;
        }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}

/*
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}*/

