/**
 * <p>给你两个单链表的头节点&nbsp;<code>headA</code> 和 <code>headB</code> ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回
 * <code>null</code> 。</p>
 *
 * <p>图示两个链表在节点 <code>c1</code> 开始相交<strong>：</strong></p>
 *
 * <p>ref="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" target="_blank"><img
 * alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" style="height:
 * 130px; width: 400px;" /></a>
 * </p>
 *
 * <p>题目数据 <strong>保证</strong> 整个链式结构中不存在环。</p>
 *
 * <p><strong>注意</strong>，函数返回结果后，链表必须 <strong>保持其原始结构</strong> 。</p>
 *
 * <p><strong>自定义评测：</strong></p>
 *
 * <p><strong>评测系统</strong> 的输入如下（你设计的程序 <strong>不适用</strong> 此输入）：</p>
 *
 * <ul>
 * <li><code>intersectVal</code> - 相交的起始节点的值。如果不存在相交节点，这一值为 <code>0</code></li>
 * <li><code>listA</code> - 第一个链表</li>
 * <li><code>listB</code> - 第二个链表</li>
 * <li><code>skipA</code> - 在 <code>listA</code> 中（从头节点开始）跳到交叉节点的节点数</li>
 * <li><code>skipB</code> - 在 <code>listB</code> 中（从头节点开始）跳到交叉节点的节点数</li>
 * </ul>
 *
 * <p>评测系统将根据这些输入创建链式数据结构，并将两个头节点 <code>headA</code> 和 <code>headB</code> 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被
 * <strong>视作正确答案</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p>ref="https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png" target="_blank"><img alt=""
 * src="https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png" style="height: 130px; width: 400px;" /></a>
 * </p>
 *
 * <pre>
 * <strong>输入：</strong>intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
 * <strong>输出：</strong>Intersected at '8'
 * <strong>解释：</strong>相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
 * 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <p>ref="https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png" target="_blank"><img alt=""
 * src="https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png" style="height: 136px; width: 350px;" /></a>
 * </p>
 *
 * <pre>
 * <strong>输入：</strong>intersectVal&nbsp;= 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * <strong>输出：</strong>Intersected at '2'
 * <strong>解释：</strong>相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
 * 从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
 * 在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * </pre>
 *
 * <p><strong>示例&nbsp;3：</strong></p>
 *
 * <p>ref="https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png" target="_blank"><img alt=""
 * src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png" style="height: 126px;
 * width: 200px;" /></a>
 * </p>
 *
 * <pre>
 * <strong>输入：</strong>intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * <strong>输出：</strong>null
 * <strong>解释：</strong>从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
 * 由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 这两个链表不相交，因此返回 null 。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>listA</code> 中节点数目为 <code>m</code></li>
 * <li><code>listB</code> 中节点数目为 <code>n</code></li>
 * <li><code>1 &lt;= m, n &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= skipA &lt;= m</code></li>
 * <li><code>0 &lt;= skipB &lt;= n</code></li>
 * <li>如果 <code>listA</code> 和 <code>listB</code> 没有交点，<code>intersectVal</code> 为 <code>0</code></li>
 * <li>如果 <code>listA</code> 和 <code>listB</code> 有交点，<code>intersectVal == listA[skipA] == listB[skipB]</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你能否设计一个时间复杂度 <code>O(m + n)</code> 、仅用 <code>O(1)</code> 内存的解决方案？</p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 1742</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

/**
 * 160
 * 相交链表
 *
 * @author wangweizhou
 * @date 2022-06-30 00:41:15
 */

public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new IntersectionOfTwoLinkedLists().new Solution();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        //node3.next=node4;
        node4.next = node5;
        //node5.next=node6;
        ListNode ans = solution.getIntersectionNode(node1, node4);
        print(ans);

    }

    // 打印链表
    private static void print(ListNode head) {
        //   判断链表是否为空
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        //遍历打印节点不包含头结点，要从真正的第一个元素节点开始遍历
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + ",");//  输出节点信息
            temp = temp.next;//curr后移，遍历当前链表
        }
    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */

    public class Solution {

        // 题干假设一定有交点
        // 经过分析我们发现，如果两个链表有公共节点，那么公共节点出现在两个链表的尾部。如果我们从两个链表的尾部开始往前比较，那么最后一个相同的节点就是我们要找的节点。

        // 方法二：双指针
        // 只有当链表 headA 和 headB 都不为空时，两个链表才可能相交。
        // 因此首先判断链表 headA 和headB 是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，返回null。

        // 当链表 headA 和 headB 都不为空时,如果两个链表相交，那么相交点之后的长度是相同的。
        // 我们需要做的事情是:让两个链表从同距离末尾同等距离的位置开始遍历。这个位置只能是较短链表的头结点位置。

        // 我们必须消除两个链表的长度差
        //1.指针 pA 指向 A 链表，指针 pB 指向 B 链表，依次往后遍历
        //2.如果 pA 到了末尾，则 pA = headB 继续遍历。
        //3.如果 pB 到了末尾，则 pB = headA 继续遍历
        //4.比较长的链表指针指向较短链表head时，长度差就消除了如此，只需要将最短链表遍历两次即可找到位置

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            ListNode curr1 = headA;
            ListNode curr2 = headB;

            // 两个链表每次都向后移动一步，当移动到链表末尾时就转换到另一个链表上继续移动，这样公共的第一个节点就是从开头走的路程一样长了
            // 注意两个空引用是相等的
            while (curr1 != curr2) {// 注意若两个节点没有公共点，则最终两个指针指向空引用，两个空引用==判断相等。
                if (curr1 != null) {
                    curr1 = curr1.next;//后移遍历
                } else {
                    curr1 = headB;// 当curr1遍历完链表A之后，则换到链表B继续遍历
                }
                if (curr2 != null) {
                    curr2 = curr2.next;
                } else {
                    curr2 = headA;
                }
                //curr1=curr1==null?headB:curr1.next;// 三目运算符和if-else语句作用一样
                //curr2=curr2==null?headA:curr2.next;
            }
            return curr1;
        }






        //// 方法3：链表长度差   从距离链表末尾等距离处开始遍历
        //// 首先遍历两个链表得到它们的长度，这样就能知道哪个链表比较长，以及长的链表比短的链表多几个节点。
        //// 在第2次遍历时，第1个指针P1在较长的链表中先移动若干步，再把第2个指针P2初始化到较短的链表的头节点，然后这两个指针按照相同的速度在链表中移动，直到它们相遇。两个指针相遇的节点就是两个链表的第1个公共节点。
        //
        //ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //    if (headA == null || headB == null) {
        //        return null;
        //    }
        //    // 获取两个链表的长度
        //    int lenA = getLength(headA);
        //    int lenB = getLength(headB);
        //    int lenDiff = Math.abs(lenA - lenB);// 两个链表的长度差
        //    // 分出两个链表的长短
        //    ListNode longer = lenA > lenB ? headA : headB;
        //    ListNode shorter = lenA > lenB ? headB : headA;
        //    // 两个链表的遍历指针
        //    ListNode longHead = longer;
        //    ListNode shortHead = shorter;
        //    for (int i = 0; i < lenDiff; i++) {
        //        longHead = longHead.next;// 长链表先移动个长度差
        //    }
        //    while (longHead != shortHead) {// 两个链表同时移动，会在相交处相遇
        //        longHead = longHead.next;
        //        shortHead = shortHead.next;
        //    }
        //    return longHead;
        //}
        //
        //
        //// 获取链表长度
        //private int getLength(ListNode node) {
        //    if (node == null) {
        //        return 0;
        //    }
        //    int len = 0;
        //    while (node != null) {
        //        len++;
        //        node = node.next;
        //    }
        //    return len;
        //}







       // 使用栈

        /*
        // 方法一：哈希表  注意哈希表存储的是数据节点而不是数据节点值
        // 判断两个链表是否相交，可以使用哈希集合存储链表节点。
        // 首先遍历链表headA，并将链表headA 中的每个节点加入哈希集合中。
        // 然后遍历链表headB，对于遍历到的每个节点，判断该节点是否在哈希集合中：如果当前节点不在哈希集合中，则继续遍历下一个节点；
        // 如果当前节点在哈希集合中，则后面的节点都在哈希集合中，即从当前节点开始的所有节点都在两个链表的相交部分，因此在链表 headB 中遍历到的第一个在哈希集合中的节点就是两个链表相交的节点，返回该节点。
        // 如果链表 headB 中的所有节点都不在哈希集合中，则两个链表不相交，返回null。

        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
            Set<ListNode> set = new HashSet<>();
            ListNode curr1 = headA;
            ListNode curr2 = headB;
            while(curr1!=null){
                set.add(curr1);
                curr1=curr1.next;
            }
            while(curr2!=null){
                //只需要判断curr2是否在set中没有必要保存
                if(set.contains(curr2)){
                    return curr2;
                }
                //if(!set.add(curr2)){// 和上面if(set.contains(curr2))目的一样，但是多了存储一步
                //    return curr2;
                //}
                curr2=curr2.next;
            }
            return null;
        }
        */


        }
//leetcode submit region end(Prohibit modification and deletion)

    }
