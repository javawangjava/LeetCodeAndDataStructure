/**
 * <p>给定一个链表的头节点 &nbsp;<code>head</code>&nbsp;，返回链表开始入环的第一个节点。&nbsp;<em>如果链表无环，则返回&nbsp;<code>null</code>。</em></p>
 *
 * <p>如果链表中有某个节点，可以通过连续跟踪 <code>next</code> 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 <code>pos</code>
 * 来表示链表尾连接到链表中的位置（<strong>索引从 0 开始</strong>）。如果 <code>pos</code> 是
 * <code>-1</code>，则在该链表中没有环。<strong>注意：<code>pos</code> 不作为参数进行传递</strong>，仅仅是为了标识链表的实际情况。</p>
 *
 * <p><strong>不允许修改 </strong>链表。</p>
 *
 * <ul>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [3,2,0,-4], pos = 1
 * <strong>输出：</strong>返回索引为 1 的链表节点
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
 * <strong>输出：</strong>返回索引为 0 的链表节点
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
 * <strong>输出：</strong>返回 null
 * <strong>解释：</strong>链表中没有环。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目范围在范围 <code>[0, 10<sup>4</sup>]</code> 内</li>
 * <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
 * <li><code>pos</code> 的值为 <code>-1</code> 或者链表中的一个有效索引</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你是否可以使用 <code>O(1)</code> 空间解决此题？</p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 1636</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 142
 * 环形链表 II
 *
 * @author wangweizhou
 * @date 2022-06-23 23:04:20
 */

public class LinkedListCycleIi {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new LinkedListCycleIi().new Solution();
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

        //fast 指针走过链表末端，说明链表无环，直接返回 null；
        //若有环，两指针一定会相遇。因为每走 1 轮，fast 与 slow 的间距 +1，fast 终会追上 slow；

        // 当fast == slow时， 两指针在环中第一次相遇
        // 根据：f=2s （快指针每次2步，路程刚好2倍）
        // f = s + nb (相遇时，刚好多走了n圈）
        // 推出：s = nb
        //从head结点走到入环点需要走 ： a + nb， 而slow已经走了nb，那么slow再走a步就是入环点了。
        //fast从head开始每次也只走一步，和slow指针一起走，相遇时刚好就是a步。a步就是从头节点head到入环口所走的节点。


        // 方法2：快慢指针   写法1    方法2的几种解法只是细节不同而已
        public ListNode detectCycle(ListNode head) {
            if (head == null || head.next == null) {
                return null;
            }
            ListNode fast = head;
            ListNode slow = head;
            // 快指针每次移动两步，慢指针每次移动一步，所以这里判空要判两次
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    break;
                }
            }
            // 上面循环结束时，要么是两个指针相遇，要么是快指针移动到链表末尾。这里要判断fast有没有移动到链表末尾
            if(fast==null||fast.next==null){
                return null;
            }
            // 两个指针一个指向链表的头节点，一个指向前面两个节点相遇的位置，然后两个这两个指针以相同的速度一起朝向下一个节点的指针移动
            fast=head;
            while (fast != slow) {
                fast = fast.next;
                slow = slow.next;
            }
            return slow;
        }





        //// 解法4： 双指针+获取环中节点
        //// 第1步是如何确定一个链表中包含环。如果一个链表中没有环，那么自然不存在环的入口节点，此时应该返回null。
        //// 可以定义两个指针并同时从链表的头节点出发，一个指针一次走一步，另一个指针一次走两步。
        //// 如果链表中不包含环，走得快的指针直到抵达链表的尾节点都不会和走得慢的指针相遇。
        //// 如果链表中包含环，走得快的指针在环里绕了一圈之后将会追上走得慢的指针。
        //// 因此，可以根据一快一慢两个指针是否能够相遇来判断链表中是否包含环。
        //
        //// 第2步是如何找到环的入口节点，可以用两个指针来解决。先定义两个指针P1和P2，指向链表的头节点。
        //// 如果链表中的环有n个节点，第1个指针P1先在链表中向前移动n步，然后两个指针以相同的速度向前移动。
        //// 当第2个指针P2指向环的入口节点时，指针P1已经围绕环走了一圈又回到了入口节点。
        //
        //
        //// 最后一个问题是如何得到环中节点的数目。前面在判断链表中是否有环时用到了一快一慢两个指针。
        //// 如果两个指针相遇，则表明链表中存在环。
        //// 两个指针之所以会相遇是因为快的指针绕环一圈追上慢的指针，因此它们相遇的节点一定是在环中。
        //// 可以从这个相遇的节点出发一边继续向前移动一边计数，当再次回到这个节点时就可以得到环中节点的数目。
        //// 在找到环中任意一个节点之后，绕环一圈就能得出环中节点的数目，接下来再次使用双指针就能找到环的入口节点。
        //
        //
        //public ListNode detectCycle(ListNode head) {
        //    if (head == null || head.next == null) {// 当节点不空，或者只有一个节点时链表中没有环。
        //        return null;
        //    }
        //    ListNode inLoop = getNodeInLoop(head);// 获取环中的节点
        //    if (inLoop == null) {// 若没有环
        //        return null;
        //    }
        //    int loopCount = 1;// 环中的节点数
        //    // 在找到环中任意一个节点之后，绕环一圈就能得出环中节点的数目。
        //    for (ListNode n = inLoop; n.next != inLoop; n = n.next) {// 遍历圆环获取环中的节点数
        //        loopCount++;
        //    }
        //
        //    // 如何找到环的入口节点，可以用两个指针来解决。先定义两个指针P1和P2，都指向链表的头节点。
        //    // 如果链表中的环有n个节点，第1个指针P1先在链表中向前移动n步，然后两个指针以相同的速度向前移动。
        //    // 当第2个指针P2指向环的入口节点时，指针P1已经围绕环走了一圈又回到了入口节点。
        //
        //    // 指针从链表头部提前走环中节点数次
        //    ListNode fast = head;
        //    for (int i = 0; i < loopCount; i++) {
        //        fast = fast.next;
        //    }
        //    // 慢指针从开始位置
        //    ListNode slow = head;
        //    while (fast != slow) {
        //        fast = fast.next;
        //        slow = slow.next;
        //    }
        //    return slow;
        //}
        //
        //
        //
        //// 如何确定一个链表中包含环。如果一个链表中没有环，那么自然不存在环的入口节点，此时应该返回null。
        //// 可以定义两个指针并同时从链表的头节点出发，一个指针一次走一步，另一个指针一次走两步。
        //// 如果链表中不包含环，走得快的指针直到抵达链表的尾节点都不会和走得慢的指针相遇。
        //// 如果链表中包含环，走得快的指针在环里绕了一圈之后将会追上走得慢的指针。
        //// 因此，可以根据一快一慢两个指针是否能够相遇来判断链表中是否包含环。
        //
        //// 获取链表环中的节点   快慢指针 两个指针相交的地方一定是环中的节点
        //private ListNode getNodeInLoop(ListNode head) {
        //    if (head == null || head.next == null) {// 当节点不空，或者只有一个节点时链表中没有环。
        //        return null;
        //    }
        //    ListNode slow = head.next;
        //    ListNode fast = slow.next;
        //    while (slow != null && fast != null) {// 没有遍历到链表末尾
        //        if (slow == fast) {// 快慢指针相遇则找到了环中的节点
        //            return slow;
        //        }
        //        // 这里快慢指针每次移动一次，然后快指针不空的话就再移动一次。
        //        slow = slow.next;
        //        fast = fast.next;
        //        if (fast != null) {// 快指针每次多移动一步
        //            fast = fast.next;
        //        }
        //    }
        //    return null;
        //}





        //// 解法3： 双指针
        //// 如果仔细分析，就会发现其实并没有必要求得环中节点的数目。
        //// 如果链表中有环，快慢两个指针一定会在环中的某个节点相遇。
        //// 慢的指针一次走一步，假设在相遇时慢的指针一共走了k步。由于快的指针一次走两步，因此在相遇时快的指针一共走了2k步。
        //// 因此，到相遇时快的指针比慢的指针多走了k步。另外，两个指针相遇时快的指针比慢的指针在环中多转了若干圈。
        //// 也就是说，两个指针相遇时快的指针多走的步数k一定是环中节点的数目的整数倍，此时慢的指针走过的步数k也是环中节点数的整数倍。
        //
        //// 此时可以让一个指针指向相遇的节点，该指针的位置是之前慢的指针走了k步到达的位置。接着让另一个指针指向链表的头节点，
        //// 然后两个指针以相同的速度一起朝着指向下一个节点的指针移动，当后面的指针到达环的入口节点时，前面的指针比它多走了k步，
        //// 而k是环中节点的数目的整数倍，相当于前面的指针在环中转了k圈后也到达环的入口节点，两个指针正好相遇。
        //// 也就是说，两个指针相遇的节点正好是环的入口节点
        //
        //public ListNode detectCycle(ListNode head) {
        //    if (head == null || head.next == null) {// 当节点不空，或者只有一个节点时链表中没有环。
        //        return null;
        //    }
        //    ListNode inLoop = getNodeInLoop(head);// 获取环中的节点
        //    if (inLoop == null) {// 若没有环
        //        return null;
        //    }
        //    // 执行到这里，则链表有环。环中节点和头节点一起以相同的速度移动，会在环的入口相遇
        //    ListNode node=head;
        //    while (node!=inLoop){
        //        node=node.next;
        //        inLoop=inLoop.next;
        //    }
        //    return node;
        //}
        //
        //
        //
        //// 获取链表环中的节点   快慢指针 两个指针相交的地方一定是环中的节点
        //private ListNode getNodeInLoop(ListNode head) {
        //    if (head == null || head.next == null) {// 当节点不空，或者只有一个节点时链表中没有环。
        //        return null;
        //    }
        //    ListNode slow = head.next;
        //    ListNode fast = slow.next;
        //    while (slow != null && fast != null) {// 没有遍历到链表末尾
        //        if (slow == fast) {// 快慢指针相遇则找到了环中的节点
        //            return slow;
        //        }
        //        // 这里快慢指针每次移动一次，然后快指针不空的话就再移动一次。
        //        slow = slow.next;
        //        fast = fast.next;
        //        if (fast != null) {// 快指针每次多移动一步
        //            fast = fast.next;
        //        }
        //    }
        //    return null;
        //}




        //// 解法2：快慢指针 写法3
        //public ListNode detectCycle(ListNode head) {
        //    if (head == null || head.next == null) {
        //        return null;
        //    }
        //    ListNode fast = head;
        //    ListNode slow = head;
        //    while(fast!=null&&fast.next!=null){
        //        fast=fast.next.next;
        //        slow=slow.next;
        //        if(fast==slow){
        //            fast=head;
        //            // 执行到这里，说明链表有环，那么不需要判空
        //            while(true){
        //                if(fast==slow){
        //                    return fast;
        //                }
        //                fast=fast.next;
        //                slow=slow.next;
        //            }
        //        }
        //    }
        //    return null;
        //}




        // 解法2：快慢指针 写法4   将一次移动两步分开进行移动，
        //public ListNode detectCycle(ListNode head) {
        //    if (head == null || head.next == null) {
        //        return null;
        //    }
        //    ListNode fast = head;
        //    ListNode slow = head;
        //    while (fast != null) {
        //        fast = fast.next;
        //        slow = slow.next;
        //        if (fast != null) {
        //            fast = fast.next;
        //        } else {
        //            return null;
        //        }
        //        if (fast == slow) {
        //            fast = head;
        //            // 执行到这里，说明链表有环，那么不需要判空
        //            while (true) {
        //                if (fast == slow) {
        //                    return fast;
        //                }
        //                fast = fast.next;
        //                slow = slow.next;
        //            }
        //        }
        //    }
        //    return null;
        //}




        //// 方法2：快慢指针   写法2
        //public ListNode detectCycle(ListNode head) {
        // if (head == null || head.next == null) {
        //        return null;
        //    }
        //    ListNode fast = head, slow = head;
        //    // 循环结束但是方法没有结束表明链表中有环
        //    while (true) {
        //        if (fast == null || fast.next == null) {//fast 指针走过链表末端，说明链表无环，直接返回 null；
        //            return null;
        //        }
        //        fast = fast.next.next;
        //        slow = slow.next;
        //        if (fast == slow) {// 有环并且两者相遇
        //            break;
        //        }
        //    }
        //    fast = head;//fast指向链表头结点
        //    while (slow != fast) {
        //        slow = slow.next;
        //        fast = fast.next;
        //    }
        //    return fast;//第二次相遇就是环的入口
        //}




        // 方法一：哈希表
        // 思路与算法: 遍历链表中的每个节点，并将它记录下来；一旦遇到了此前遍历过的节点，就可以判定链表中存在环。

        //public ListNode detectCycle(ListNode head) {
        //    Set<ListNode> set=new HashSet<>();
        //    ListNode curr=head;
        //    while(curr!=null){
        //        //boolean add(E e) 如果指定的元素尚不存在，则将其添加到此集合中。存在就返回false
        //        if(!set.add(curr)){
        //            return curr;
        //        }
        //        //if (set.contains(curr)) {// 该if-else语句与上面if语句作用相同
        //        //    return true;
        //        //} else {
        //        //    set.add(curr);
        //        //}
        //        curr=curr.next;//指针后移
        //    }
        //    return null;
        //}


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
}
*/
