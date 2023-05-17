/**
 * <p>给你一个链表的头节点 <code>head</code> ，判断链表中是否有环。</p>
 *
 * <p>如果链表中有某个节点，可以通过连续跟踪 <code>next</code> 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 <code>pos</code>
 * 来表示链表尾连接到链表中的位置（索引从 0 开始）。<strong>注意：<code>pos</code> 不作为参数进行传递&nbsp;</strong>。仅仅是为了标识链表的实际情况。</p>
 *
 * <p><em>如果链表中存在环</em>&nbsp;，则返回 <code>true</code> 。 否则，返回 <code>false</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [3,2,0,-4], pos = 1
 * <strong>输出：</strong>true
 * <strong>解释：</strong>链表中有一个环，其尾部连接到第二个节点。
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2
 * .png" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1,2], pos = 0
 * <strong>输出：</strong>true
 * <strong>解释：</strong>链表中有一个环，其尾部连接到第一个节点。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3
 * .png" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1], pos = -1
 * <strong>输出：</strong>false
 * <strong>解释：</strong>链表中没有环。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目范围是 <code>[0, 10<sup>4</sup>]</code></li>
 * <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
 * <li><code>pos</code> 为 <code>-1</code> 或者链表中的一个 <strong>有效索引</strong> 。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你能用 <code>O(1)</code>（即，常量）内存解决此问题吗？</p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 1523</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 141
 * 环形链表
 *
 * @author wangweizhou
 * @date 2022-06-27 19:26:54
 */

public class LinkedListCycle {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LinkedListCycle().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */

    public class Solution {

        // 解法3：快慢指针优化 写法2
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {//链表为空或者链表只有一个节点，则表明链表中没有环
                return false;
            }
            ListNode slow = head;
            ListNode fast = head;
            // 因为fast要移动两步，所以要两次判空。只有某个链表节点不为空时，才可以调用该节点的指针域。
            // fast每次移动两步 若fast=null,则fast.next就会是空引用异常。
            // 若fast.next=null,则fast.next.next就会是空引用异常。

            while (fast != null && fast.next != null) {
                // 先移动fast，然后移动slow，之后再比较
                fast = fast.next.next;
                slow = slow.next;//slow每次只移动一步且在fast之后移动,两步的可以移动，则一步的肯定可以移动
                if (slow == fast) {//结束条件：链表fast==slow说明有环
                    return true;
                }
            }
            //循环结束但方法没有结束则说明fast遍历到链表末尾了
            return false;
        }



   /*
        // 解法3：快慢指针优化 写法3
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {//链表为空或者链表只有一个节点，则表明链表中没有环
                return false;
            }
            ListNode slow = head;
            ListNode fast = head;

            while(true){
                if(fast!=null&&fast.next!=null){
                    fast=fast.next.next;
                }else{
                    return false;
                }
                slow=slow.next;
                if(fast==slow){
                    return true;
                }
            }
        }

*/




        //	解法2：快慢指针 写法1
        //  假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。
        //  当「乌龟」和「兔子」从链表上的同一个节点开始移动时，如果该链表中没有环，那么「兔子」将一直处于「乌龟」的前方；如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。
        //  等到「乌龟」进入环时，由于「兔子」的速度快，它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈。
        // 具体地，我们定义两个指针，一快一满。慢指针每次只移动一步，而快指针每次移动两步。初始时，慢指针在位置 head，而快指针在位置 head.next。
        // 这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表。

/*
       public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) {
                return false;
            }
            ListNode slow = head;
            ListNode fast = head.next;
            //当slow==fast时结束循环
            while (slow != fast) {//先判断再执行，所以初始时，慢指针在位置 head，而快指针在位置 head.next。
                if (fast == null || fast.next == null) {//fast移动的快，结束条件：链表可以遍历到末尾就说明没有环
                    return false;
                }
                slow = slow.next;//slow每次只移动一步
                // fast每次移动两步 若fast=null,则fast.next就会是空引用异常。fast.next=null,那么fast.next.next就会是空引用异常，所有下面这个和上面对应
                // 上面if (fast == null || fast.next == null) 没有结束方法，那么就可以执行下面的语句。
                fast = fast.next.next;
            }
            //while循环结束但方法没有结束就说明slow追上fast了，那就说明有环
            return true;
        }*/





  /*
        // 解法1：哈希表
        // 具体地，使用哈希表来存储所有已经访问过的节点。
        // 每次我们到达一个节点，如果该节点已经存在于哈希表中，则说明该链表是环形链表，否则就将该节点加入哈希表中。
        // 重复这一过程，直到我们遍历完整个链表即可。

        public boolean hasCycle(ListNode head) {
            // if (head == null || head.next == null)这句没必要，后面while (curr != null)已经包含这种情况了。
            // 如果后面的while循环中是while (curr.next != null)则下面这句单独处理需要

            if (head == null || head.next == null) {//链表为空或者链表只有一个节点，则表明链表中没有环
                return false;
            }
            Set<ListNode> set = new HashSet<>();
            ListNode curr = head;
            while (curr != null) {
                if(!set.add(curr)){ //boolean add(E e) 如果指定的元素尚不存在，则将其添加到此集合中。存在就返回false
                    return true;
                }

                curr=curr.next;
            }
            return false;

            //if(!set.add(curr)){ //boolean add(E e) 如果指定的元素尚不存在，则将其添加到此集合中。存在就返回false
            //    return true;
            //}

            //if (set.contains(curr)) {// 该if-else语句与上面if语句作用相同
            //    return true;
            //} else {
            //    set.add(curr);
            //}
        }

*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
