/**
 * <p>给定一个已排序的链表的头&nbsp;<code>head</code> ，&nbsp;<em>删除原始链表中所有重复数字的节点，只留下不同的数字</em>&nbsp;。返回 <em>已排序的链表</em>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/linkedlist1.jpg" style="height: 142px; width:
 * 500px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,3,4,4,5]
 * <strong>输出：</strong>[1,2,5]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/linkedlist2.jpg" style="height: 164px; width:
 * 400px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,1,1,2,3]
 * <strong>输出：</strong>[2,3]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点数目在范围 <code>[0, 300]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * <li>题目数据保证链表已经按升序 <strong>排列</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 944</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 82
 * 删除排序链表中的重复元素 II
 *
 * @author wangweizhou
 * @date 2022-07-19 17:16:55
 */

public class RemoveDuplicatesFromSortedListIi {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new RemoveDuplicatesFromSortedListIi().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        //node5.next = node6;
        //node6.next = node7;

        ListNode ans = solution.deleteDuplicates(node1);
        print(ans);
    }


    private static void print(ListNode head) {
        //   判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        //遍历打印节点不包含头结点，要从真正的第一个元素节点开始遍历
        ListNode temp = head;
        while (temp != null) {
            System.out.println(temp.val);//  输出节点信息
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



        public ListNode deleteDuplicates(ListNode head) {
            if(head==null||head.next==null){
                return head;
            }
            ListNode dummy=new ListNode(-1,head);
            ListNode prev=dummy;
            ListNode left=head;
            ListNode right=head;
            while (right!=null&&right.next!=null){
                while (right!=null&&right.val==left.val) {
                    right=right.next;
                }
                if(left.next==right){
                    prev=left;
                    left=right;
                }else {
                    prev.next=right;
                    //prev=right;
                    left=right;
                }
            }
            return dummy.next;
        }



        //
        //// 解法1：一遍遍历 写法3
        //public ListNode deleteDuplicates(ListNode head) {
        //    if (head == null || head.next == null) {// 判空和只有一个节点
        //        return head;
        //    }
        //    ListNode dummy = new ListNode(-1, head);
        //    // 这里node和prev的初始值的设定是同一个，指向待判断区间[left,right]的左边界left前面的节点
        //    ListNode node = dummy;// node 指向待判断区间[left,right]的左边界left前面的节点
        //    ListNode prev = dummy;// prev指向区间[left,right]的左边界left前面的节点
        //    // 到这里链表至少有两个节点  判断区间[left,right]中是否有重复节点
        //    while (node.next != null) {
        //        // 注意这里区间是[left,right]开始时是[left,left]。
        //        ListNode left = node.next;
        //        ListNode right = left;
        //        // 区间的遍历指针right不为空，区间的遍历指针right的值等于left的值。表明区间中有重复的值
        //        while (right != null && right.val == left.val) {
        //            right = right.next;
        //        }
        //
        //        //上面while循环结束：right指向空节点或者指向第一个数据域不同于left的地方
        //        if (left.next != right) {// left和right不相连，也就是[left,right]中有相同的数据，删除相同数据
        //            prev.next = right;
        //        } else {// left.next == right 表示left和right相连，也就是[left,right]中没有相同的数据
        //            prev = left;
        //        }
        //
        //        // 执行到这里，prev 指向待去重区间[left，right]的左边界left。
        //        // 这里node和prev的初始值的设定是同一个，指向待判断区间[left,right]的左边界left前面的节点
        //        node = prev;
        //    }
        //    return dummy.next;
        //}


        //// 解法1：一次遍历  用计数器实现是否有重复数据元素
        //public ListNode deleteDuplicates(ListNode head) {
        //    if (head == null||head.next==null) {// 判空和只有一个节点
        //        return head;
        //    }
        //
        //    ListNode dummyHead = new ListNode(-1);// 哨兵节点
        //    dummyHead.next = head;
        //
        //    ListNode pre = dummyHead;// pre记录链表中没有重复数值节点区间的前一个
        //    ListNode right = head;// 链表遍历指针
        //    // 检查区间[left,right]中是否有相同数值节点，若left=right时就只有一个节点，一次删除说有有相同数字的节点
        //    while (right != null) {
        //        int count = 0;// 相同重复数字节点个数计数器
        //        ListNode left=right;// 动态窗口的第一个节点需要保存，方便删除相同数值节点时使用
        //        while (right != null&&left.val == right.val) {// 这里left和right开始位置相同，所以right肯定会至少移动一次
        //            right = right.next;
        //            count++;
        //        }
        //        // 上面循环结束条件：right == null||left.val == right.val,遍历到链表末尾或者区间[left,right]中没有重复重复数据
        //        if(right==null&&count==1){// 链表遍历到最后一个节点且没有重复数字的节点
        //            pre.next=left;
        //        } else if (count == 1) {// 链表区间[left,right]中没有重复节点，pre后移
        //            pre = left;
        //        } else {// 链表区间[left,right]中有重复节点，删除链表中有重复数字的节点
        //            pre.next = right;
        //        }
        //    }
        //    return dummyHead.next;
        //}




/*
        //	解法2：哈希表
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null) {
                return head;
            }
            ListNode dummyHead = new ListNode(-1, head);// 哨兵节点
            ListNode curr = dummyHead.next;
            Map<Integer, Integer> map = new HashMap<>();

            while (curr != null) {//遍历链表统计每个结点值出现的次数
                map.put(curr.val,map.getOrDefault(curr.val,0)+1);
                curr=curr.next;
            }
            curr = dummyHead; //遍历指针重置, 因为要删除节点，所以这里curr从哨兵节点开始，也就是待删除节点的前一个节点
            while(curr.next!=null){//再次遍历链表
                if(map.get(curr.next.val)!=1){//如果结点值计数不为1
                    curr.next=curr.next.next;//删去该结点
                }else{
                    curr=curr.next;
                }
            }
            return dummyHead.next;
        }
*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
