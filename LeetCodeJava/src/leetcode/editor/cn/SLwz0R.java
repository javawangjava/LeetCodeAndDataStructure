/**
 * <p>给定一个链表，删除链表的倒数第&nbsp;<code>n</code><em>&nbsp;</em>个结点，并且返回链表的头结点。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/remove_ex1.jpg" style="width: 542px; height:
 * 222px;" /></p>
 *
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
 * <p><strong>进阶：</strong>能尝试使用一趟扫描实现吗？</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 19&nbsp;题相同：&nbsp;
 * <a href="https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/">https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/</a></p>
 * <div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 46</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 删除链表的倒数第 n 个结点
 * @author wangweizhou
 * @date 2022-06-23 22:05:02
 */
public class SLwz0R {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new SLwz0R().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {

        // 解法1：哨兵节点+双指针
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head == null) {// 空链表没有什么可以删除的，这个是自己约定的处理方式
                return head;
            }

            ListNode dummyHead=new ListNode(-1,head);//将原始链表【数据节点】添加到哨兵节点的后面
            ListNode slow=dummyHead;// 慢指针
            ListNode fast=dummyHead;// 快指针
            // 题干并没有说n与链表节点个数的问题，所以这里自定义处理方式。
            // 当n大于链表节点数目时，自己约定返回头节点
            for (int i = 0; i < n; i++) {// 快指针比慢指针提前n个位置
                if(fast.next!=null){
                    fast = fast.next;
                }else{
                    return head;
                }
            }

            // 判断右指针是否遍历到链表尾部，最后一个数据节点的指针域为空，也就是没有后续节点了
            // while循环结束，fast就移动到链表最后一个节点，slow就移动到待删除节点的前一个【也就是倒数第（n+1）个节点】
            while(fast.next!=null) {
                slow = slow.next;
                fast = fast.next;
            }
            //根据前面说明，slow至少是倒数第2个节点，所以 slow.next一定非空，不需要判空
            slow.next=slow.next.next;
            return dummyHead.next;
        }


        //解法2：哨兵节点+遍历两次  第一次遍历得到链表长度

       /*
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode dummy = new ListNode(-1);//dummy 哨兵节点
            ListNode curr = dummy;//遍历指针curr
            dummy.next = head;
            int length = 0;
            while (curr.next != null) {
                length++;
                curr = curr.next;
            }
            if (length < n) {//N大于链表长度，删除第一个数据节点
                return dummy.next.next;
            }
            curr = dummy;
            for (int i = 0; i < length - n; i++) {//遍历找到待删除节点的前一个节点
                curr = curr.next;
            }

            curr.next = curr.next.next;
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
