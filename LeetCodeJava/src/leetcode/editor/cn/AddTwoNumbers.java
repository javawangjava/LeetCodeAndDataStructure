/**
 * <p>给你两个 <strong>非空</strong> 的链表，表示两个非负的整数。它们每位数字都是按照 <strong>逆序</strong> 的方式存储的，并且每个节点只能存储 <strong>一位</strong>
 * 数字。</p>
 *
 * <p>请你将两个数相加，并以相同形式返回一个表示和的链表。</p>
 *
 * <p>你可以假设除了数字 0 之外，这两个数都不会以 0 开头。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/01/02/addtwonumber1.jpg"
 * style="width: 483px; height: 342px;" />
 * <pre>
 * <strong>输入：</strong>l1 = [2,4,3], l2 = [5,6,4]
 * <strong>输出：</strong>[7,0,8]
 * <strong>解释：</strong>342 + 465 = 807.
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [0], l2 = [0]
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * <strong>输出：</strong>[8,9,9,9,0,0,0,1]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>每个链表中的节点数在范围 <code>[1, 100]</code> 内</li>
 * <li><code>0 <= Node.val <= 9</code></li>
 * <li>题目数据保证列表表示的数字不含前导零</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li><li>数学</li></div></div><br><div><li>👍 8177</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 两数相加
 *
 * @author wangweizhou
 * @date 2022-06-10 17:26:05
 */


public class AddTwoNumbers {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new AddTwoNumbers().new Solution();
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


        //将两个链表看成是相同长度的进行遍历，如果一个链表较短则在前面补 00，比如 987 + 23 = 987 + 023 = 1010
        //每一位计算的同时需要考虑上一位的进位问题，而当前位计算结束后同样需要更新进位值
        //如果两个链表全部遍历完毕后，进位值为 1，则在新链表最前方添加节点 1

        //小技巧：对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针pre，该指针的下一个节点指向真正的头结点head。
        // 使用预先指针的目的在于链表初始化时无可用节点值，而且链表构造过程需要指针移动，进而会导致头指针丢失，无法返回结果。

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            //形参l1和l2链表是不带头结点的链表
            ListNode curr1 = l1;//curr1辅助遍历链表l1的指针,初始化curr1为链表l1的首节点【第一个数据节点】。
            ListNode curr2 = l2;
            //创建带头结点【头结点不存储数据信息】的链表dummyHead来存储结果，头结点不存储数据。
            // 因为链表节点是自定义的，所有应该是创建节点，然后往链表中插入
            ListNode dummyHead = new ListNode(-1);
            ListNode curr = dummyHead;//curr指向结果链表的头结点，结果链表的辅助遍历变量

            int carry = 0;//每⼀位相加肯定会产⽣进位0或者1，我们⽤ carry 表示。进位最⼤会是 1
            while (curr1 != null || curr2 != null) {//两个多位数的位数可能不相同
                //digit1是curr1对应节点的值，如果已经遍历到null,则对应的数字是0.
                int digit1 = curr1 == null ? 0 : curr1.val;
                int digit2 = curr2 == null ? 0 : curr2.val;

                int sum = carry + digit1 + digit2;// 对应位的和3
                carry = sum / 10;//计算出应该进位的数
                int currDigit = sum % 10;
                curr.next = new ListNode(currDigit);//
                curr = curr.next;//curr指针后移

                if (curr1 != null) {// 链表l1没有遍历完的时候，curr1不断后移
                    curr1 = curr1.next;
                }
                if (curr2 != null) {
                    curr2 = curr2.next;
                }
            }
            //最后加完之后有没有出现新的最高位
            if (carry == 1) {
                curr.next = new ListNode(carry);
            }
            return dummyHead.next;
        }

    }

//leetcode submit region end(Prohibit modification and deletion)

    class ListNode{
        int val;
        ListNode next;
        public ListNode(){}
        public ListNode(int val){
            this.val=val;
        }
        public ListNode(int val,ListNode next){
            this.val=val;
            this.next=next;
        }
    }

}

