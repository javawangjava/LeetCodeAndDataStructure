/**
 * <p>给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。</p>
 *
 * <p>返回删除后的链表的头节点。</p>
 *
 * <p><strong>注意：</strong>此题对比原题有改动</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre><strong>输入:</strong> head = [4,5,1,9], val = 5
 * <strong>输出:</strong> [4,1,9]
 * <strong>解释: </strong>给定你链表中值为&nbsp;5&nbsp;的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 1 -&gt; 9.
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre><strong>输入:</strong> head = [4,5,1,9], val = 1
 * <strong>输出:</strong> [4,5,9]
 * <strong>解释: </strong>给定你链表中值为&nbsp;1&nbsp;的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -&gt; 5 -&gt; 9.
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>说明：</strong></p>
 *
 * <ul>
 * <li>题目保证链表中节点的值互不相同</li>
 * <li>若使用 C 或 C++ 语言，你不需要 <code>free</code> 或 <code>delete</code> 被删除的节点</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li></div></div><br><div><li>👍 232</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 删除链表的节点
 * @author wangweizhou
 * @date 2022-06-25 23:59:27
 */
public class ShanChuLianBiaoDeJieDianLcof {

    public static void main(String[] args) {

        //测试代码
        Solution solution = new ShanChuLianBiaoDeJieDianLcof().new Solution();
        ListNode node1=new ListNode(1);
        ListNode node2=new ListNode(2);
        ListNode node3=new ListNode(3);
        ListNode node4=new ListNode(4);
        ListNode node5=new ListNode(5);

        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;

        ListNode ans=solution.deleteNode(null,1);
        if(ans==null){
            System.out.println("空链表");
        }
        while(ans!=null){
            System.out.println(ans.val);
            ans=ans.next;
        }


    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */

    class Solution {

        // 解法1： 写法2 遍历查找  删除链表中第一个节点值等于目标值的节点，找到待删除节点的前一个
        public ListNode deleteNode(ListNode head, int val) {
            if (head == null) {
                return null;
            }
            ListNode dummy = new ListNode(-1, head);
            ListNode prev = dummy;// 待删除节点的前一个节点
            // 找到待删除节点的前一个
            while(prev.next!=null){
                if(prev.next.val==val){// 找到了待删除节点的前一个节点
                    prev.next= prev.next.next;//一旦删除之后就停止循环
                    break;
                }
                prev = prev.next;
            }
            return dummy.next;
        }




        // 解法1： 写法2 遍历查找  删除链表中所有节点值等于目标值的所有节点，找到待删除节点的前一个
        // 如果链表中没有等于目标值的节点，那么就没有删除节点
        //public ListNode deleteNode(ListNode head, int val) {
        //    if (head == null) {
        //        return null;
        //    }
        //    ListNode dummy = new ListNode(-1, head);
        //    ListNode curr = dummy;
        //    // 找到待删除节点的前一个
        //    while(curr.next!=null){
        //        if(curr.next.val==val){// 每找到链表中等于目标值的节点，就删除该节点
        //            curr.next=curr.next.next;
        //        }
        //        // 后面curr要后移，所以这里要对curr.next判空
        //        if (curr.next == null) {
        //            break;
        //        }
        //        curr=curr.next;
        //    }
        //    return dummy.next;
        //}







    }
//leetcode submit region end(Prohibit modification and deletion)

}
