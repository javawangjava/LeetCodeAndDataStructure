/**
 * 给你一个链表的头节点 <code>head</code> 和一个整数 <code>val</code> ，请你删除链表中所有满足 <code>Node.val == val</code> 的节点，并返回
 * <strong>新的头节点</strong> 。
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/03/06/removelinked-list.jpg" style="width: 500px;
 * height: 142px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,6,3,4,5,6], val = 6
 * <strong>输出：</strong>[1,2,3,4,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [], val = 1
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [7,7,7,7], val = 7
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>列表中的节点数目在范围 <code>[0, 10<sup>4</sup>]</code> 内</li>
 * <li><code>1 <= Node.val <= 50</code></li>
 * <li><code>0 <= val <= 50</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 945</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 203
 * 移除链表元素
 *
 * @author wangweizhou
 * @date 2022-06-30 11:16:01
 */
public class RemoveLinkedListElements {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RemoveLinkedListElements().new Solution();

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


        // 解法2：单指针迭代
        // 单链表只有next指针域，所以，要删除节点就需要找到待删除节点的前一个节点。
        // 由于链表的头节点 head 有可能需要被删除，因此创建哑节点 dummyHead，令dummyHead.next=head，
        // 初始化pre=dummyHead，然后遍历链表进行删除操作。 pre表示待删除节点的前一个节点。
        // 如果pre.next!=null且pre.next.val==val，则pre.next就是要删除的节点。删除节点操作：pre.next=pre.next.next;
        // 如果pre.next!=null且pre.next.val!=val，则pre指针后移。pre=pre.next;

        // 当pre.next==null，循环结束，此时所有节点值等于 val 的节点都被删除。
        // 最终返回 dummyHead.next 即为删除操作后的头节点。

        public ListNode removeElements(ListNode head, int val) {
            //if (head == null) {//不需要这个if语句，因为若head==null,循环不会执行，直接返回dummyHead.next【也就是head】
            //    return null;
            //}
            ListNode dummyHead=new ListNode(-1);
            dummyHead.next=head;
            ListNode pre=dummyHead;//pre是待删除节点的前一个节点
            while(pre.next!=null){
                if(pre.next.val==val){
                    pre.next=pre.next.next;
                }else{
                    pre=pre.next;
                }
            }
            return dummyHead.next;
        }



        /*
        // 解法1：双指针
        // pre指向待删除节点前一个节点，curr表示待删除节点

        public ListNode removeElements(ListNode head, int val) {

            ListNode dummyHead=new ListNode(-1,head);
            ListNode pre=dummyHead;// pre指向待删除节点前一个节点，
            ListNode curr=head;// curr表示待删除节点
            while(curr!=null){
                if(curr.val==val){
                    pre.next=curr.next;
                }else{
                    pre=curr;
                }
                curr=curr.next;
            }
            return dummyHead.next;
        }
        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
