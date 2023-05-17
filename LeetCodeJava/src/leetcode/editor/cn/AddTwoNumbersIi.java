/**
 * <p>给你两个 <strong>非空 </strong>链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。</p>
 *
 * <p>你可以假设除了数字 0 之外，这两个数字都不会以零开头。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例1：</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626420025-fZfzMX-image.png" style="width: 302px; " /></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [7,2,4,3], l2 = [5,6,4]
 * <strong>输出：</strong>[7,8,0,7]
 * </pre>
 *
 * <p><strong>示例2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [2,4,3], l2 = [5,6,4]
 * <strong>输出：</strong>[8,0,7]
 * </pre>
 *
 * <p><strong>示例3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [0], l2 = [0]
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表的长度范围为<code> [1, 100]</code></li>
 * <li><code>0 &lt;= node.val &lt;= 9</code></li>
 * <li>输入数据保证链表代表的数字无前导 0</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>如果输入链表不能翻转该如何解决？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>链表</li><li>数学</li></div></div><br><div><li>👍 548</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 445
 * 两数相加 II
 *
 * @author wangweizhou
 * @date 2022-08-24 16:37:48
 */

public class AddTwoNumbersIi {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new AddTwoNumbersIi().new Solution();
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


        // 当链表较长时，表示的整数很大，可能会超出int甚至long的范围，如果根据链表求出整数就有可能会溢出。
        // 通常，两个整数相加都是先加个位数，再加十位数，然后依次相加更高位数字。
        // 把表示整数的链表反转。反转之后的链表的头节点表示个位数，尾节点表示最高位数。
        // 此时从两个链表的头节点开始相加，就相当于从整数的个位数开始相加。
        // 在做加法时还需要注意的是进位。如果两个整数的个位数相加的和超过10，就会往十位数产生一个进位。
        // 在下一步做十位数相加时就要把这个进位考虑进去。


        // 解法1：反转链表  写法1
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            // 把表示整数的链表反转。反转之后的链表的头节点表示个位数，尾节点表示最高位数。
            ListNode reverse1 = reverseList(l1);
            ListNode reverse2 = reverseList(l2);
            // 此时从两个链表的头节点开始相加，就相当于从整数的个位数开始相加。
            // 在做加法时还需要注意的是进位。如果两个整数的个位数相加的和超过10，就会往十位数产生一个进位。
            // 在下一步做十位数相加时就要把这个进位考虑进去。
            ListNode dummy = new ListNode(-1);//和链表的哨兵节点
            ListNode curr = dummy;//和链表的遍历节点
            int carry = 0;//进位
            int currNum = 0;//当前位的数字

            while (reverse1 != null || reverse2 != null || carry != 0) {// 注意这里只要一个链表或者进位不空就需要向加计算
                int val1 = reverse1 == null ? 0 : reverse1.val;// 反转链表reverse1的当前位
                int val2 = reverse2 == null ? 0 : reverse2.val;
                int valSum = val1 + val2 + carry;
                int val = valSum % 10;//和链表的当前位
                carry = valSum / 10;//和链表的进位
                curr.next = new ListNode(val);//和节点连接到和链表上
                curr = curr.next;//和链表的遍历指针后移
                reverse1 = (reverse1 != null ? reverse1.next : null);// 更新反转链表reverse1的遍历指针后移
                reverse2 = (reverse2 != null ? reverse2.next : null);
            }
            return reverseList(dummy.next);
        }



        // 反转链表
        private ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode curr = head;
            ListNode dummy = new ListNode(-1);
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = dummy.next;
                dummy.next = curr;
                curr = next;
            }
            return dummy.next;
        }




        //// 解法1：反转链表  写法2
        //public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //    if (l1 == null) {
        //        return l2;
        //    } else if (l2 == null) {
        //        return l1;
        //    }
        //
        //    // 把表示整数的链表反转。反转之后的链表的头节点表示个位数，尾节点表示最高位数。
        //    ListNode revL1 = reverseList(l1);
        //    ListNode revL2 = reverseList(l2);
        //
        //    // 此时从两个链表的头节点开始相加，就相当于从整数的个位数开始相加。
        //    // 在做加法时还需要注意的是进位。如果两个整数的个位数相加的和超过10，就会往十位数产生一个进位。
        //    // 在下一步做十位数相加时就要把这个进位考虑进去。
        //    ListNode dummy = new ListNode(-1);// 和链表的哨兵节点
        //    ListNode sumNode = dummy;//和链表的遍历节点
        //    int carry = 0;//进位
        //
        //    while (revL1 != null || revL2 != null) {// 注意这里时两个链表至少一个不空，那么循环结束的时候两个链表只有一个为空，这时候只要处理最后一个进位就可以。
        //        int val1 = revL1 == null ? 0 : revL1.val;// 反转链表reverse1的当前位
        //        int val2 = revL2 == null ? 0 : revL2.val;
        //        int valSum = val1 + val2 + carry;// 和链表的当前位
        //        carry = valSum >= 10 ? 1 : 0;// 和链表的进位
        //        int val = valSum >= 10 ? valSum - 10 : valSum;
        //        // 创建和链表的对应节点并连接到链表上
        //        sumNode.next = new ListNode(val);
        //        sumNode = sumNode.next;
        //
        //        // 反转链表的当前位后移
        //        revL1 = revL1 == null ? null : revL1.next;
        //        revL2 = revL2 == null ? null : revL2.next;
        //    }
        //    // 两个链表遍历完了，最高位进位大于0
        //    if (carry > 0) {
        //        sumNode.next = new ListNode(carry);
        //    }
        //    return reverseList(dummy.next);
        //}
        //
        //
        //// 原地反转链表
        //private ListNode reverseList(ListNode head) {
        //    if (head == null) {
        //        return head;
        //    }
        //    ListNode curr = head;
        //    ListNode prev = null;
        //    while (curr != null) {
        //        ListNode next = curr.next;
        //        curr.next = prev;
        //        prev = curr;
        //        curr = next;
        //    }
        //    return prev;
        //}
        //




        /*
        // 解法2：栈   先进后出，将链表的头尾顺序颠倒
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            Deque<Integer> stack1=new LinkedList<>();
            Deque<Integer> stack2=new LinkedList<>();
            ListNode head =null;
            //ListNode curNode= dummyHead;
            while(l1!=null){
                stack1.push(l1.val);
                l1=l1.next;
            }
            while(l2!=null){
                stack2.push(l2.val);
                l2=l2.next;
            }
            int carry=0;
            int curr=0;
            while(!stack1.isEmpty()||!stack2.isEmpty()||carry!=0){
                int curr1=stack1.isEmpty()?0:stack1.pop();
                int curr2=stack2.isEmpty()?0:stack2.pop();
                int currSum=curr1+curr2+carry;
                carry=currSum/10;
                curr=currSum%10;
                ListNode currNode=new ListNode(curr);
                currNode.next=head;
                head=currNode;
            }
            return head;
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}
