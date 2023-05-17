/**
 * <p>给你一个单链表的头节点 <code>head</code> ，请你判断该链表是否为回文链表。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/03/03/pal1linked-list.jpg" style="width: 422px; height:
 * 62px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,2,1]
 * <strong>输出：</strong>true
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/03/03/pal2linked-list.jpg" style="width: 182px; height:
 * 62px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2]
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点数目在范围<code>[1, 10<sup>5</sup>]</code> 内</li>
 * <li><code>0 &lt;= Node.val &lt;= 9</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你能否用&nbsp;<code>O(n)</code> 时间复杂度和 <code>O(1)</code> 空间复杂度解决此题？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>递归</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍
 * 1423</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 234
 * 回文链表
 *
 * @author wangweizhou
 * @date 2022-06-27 17:12:41
 */

public class PalindromeLinkedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new PalindromeLinkedList().new Solution();
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

        // 回文链表的一个特性是对称性，也就是说，如果把链表分为前后两半，那么前半段链表反转之后与后半段链表是相同的。
        // 如果链表的节点总数是奇数，那么把链表分成前后两半时不用包括中间节点。


        //	解法2：快慢指针  反转链表
        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {
                return true;
            }
            // 链表从中间断开：偶数个节点找前半段的最后一个节点，奇数个节点找中间的节点
            ListNode fast = head.next;
            ListNode slow = head;//偶数个节点找前半段的最后一个节点，奇数个节点找中间的节点
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            ListNode secondHalf = slow.next;// 后半段
            slow.next=null;
            secondHalf = reverseList(secondHalf);
            ListNode firstHalf = head;// 前半段

            // 对比前半段和后半段的节点
            //while (secondHalf!=null&&firstHalf!=null) {
            while (secondHalf!=null) {
                if(secondHalf.val==firstHalf.val){
                    secondHalf=secondHalf.next;
                    firstHalf=firstHalf.next;
                }else{
                    return false;
                }
            }
            return true;
        }



        private ListNode reverseList(ListNode node) {
            if (node == null || node.next == null) {
                return node;
            }
            ListNode newHead = null;
            while (node != null) {
                ListNode next = node.next;
                node.next = newHead;
                newHead = node;
                node = next;
            }
            return newHead;
        }




        //	解法2：快慢指针
        // fast每次移动两步，若链表有偶数个节点，则fast最后指向最后一个节点；若链表有奇数个节点，则fast最后指向最后一个节点后面的指针域，也就是null.
        // slow每次移动一步，若链表有偶数（2n）个节点，则slow最后指向第（n+1）个节点【也就是下半部分刚开始的地方】；若链表有奇数（2n+1）个节点，则slow最后指向中间(第n+1)
        // 的节点；【也就是下半部分刚开始的地方的前一个】
        //public boolean isPalindrome(ListNode head) {
        //    if (head == null || head.next == null) {//判空
        //        return true;
        //    }
        //    ListNode fast = head;
        //    ListNode slow = head;
        //    ListNode newHead = null;//前半部分链表第一次反转后的头结点
        //    // 快慢指针，并同时反转链表前半部分
        //    while (fast != null && fast.next != null) {
        //        fast = fast.next.next;//快指针移动两步
        //        // 反转
        //        ListNode nextNode = slow.next;//临时变量保存下一个节点
        //
        //        // 头插法反转，每次把反转后链表连接到新加入的节点后面。
        //        // prev指向反转后链表的头结点
        //        slow.next = newHead;//每次把反转后的节点连接到新加入节点的后面
        //        newHead = slow;//newHead指向头结点
        //        slow = nextNode;//slow后移一位
        //    }
        //
        //    ListNode firstHalfEnd = slow;//临时变量保存slow的最后位置，也就是链表的中间节点（一共奇数个节点），或者链表前半部分的最后一个节点（一共偶数个节点）
        //    if (fast != null) {//fast若不为空，则表明链表节点时奇数个节点，slow后移一位，到达下半截开始的地方
        //        slow = slow.next;
        //    }
        //
        //    // 比较值并反转还原前半部分
        //    boolean isPalindrome = true;
        //    //对比前半部分和后半部分，并且逐步还原前半部分链表
        //    while (newHead != null) {
        //        if (slow.val != newHead.val) {
        //            isPalindrome = false;
        //        }
        //        slow = slow.next;//后半部分遍历指针slow指针后移
        //        // 前半部分再次反转
        //        ListNode nextNode = newHead.next;//临时变量保存下一个节点
        //        //头插法反转
        //        newHead.next = firstHalfEnd;
        //        firstHalfEnd = newHead;
        //        newHead = nextNode;//前半部分遍历指针newHead后移
        //    }
        //    return isPalindrome;
        //}



 /*
        // 解法1：将链表的数据域复制到数组中，然后用双指针。
        // 回文链表指的是链表的数据域是对称的，而不是链表的节点是对称的，因为单链表的指针域没办法一样

        public boolean isPalindrome(ListNode head) {
            if (head == null || head.next == null) {//题干中并没有约定空引用如何处理,这里处理成是回文
                return true;
            }
            List<Integer> dataList=new ArrayList<>();//不知道链表长度所以用动态链表来表示
            ListNode curr=head;
            //	将链表的数据域复制到数组中
            while(curr!=null){
                dataList.add(curr.val);//这里链表节点里面只有数值，所以直接赋值链表节点的数值
                curr=curr.next;
            }

            // 使用反向双指针来判断数组是回文数组，其实也就是使用双指针判断数据域是否为回文
            int left=0;
            int right=dataList.size()-1;
            while(left<right){
                // 这里使用的equals()方法，要是链表的数据域比较复杂，可以重写equals()方法来判断。（==）值适用于基本数据类型
                //if(!dataList.get(left).equals(dataList.get(right))){
                if(dataList.get(left)!=(dataList.get(right))){
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }
*/

    }
//leetcode submit region end(Prohibit modification and deletion)

}
