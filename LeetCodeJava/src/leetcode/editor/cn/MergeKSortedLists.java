/**
 * <p>给你一个链表数组，每个链表都已经按升序排列。</p>
 *
 * <p>请你将所有链表合并到一个升序链表中，返回合并后的链表。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入：</strong>lists = [[1,4,5],[1,3,4],[2,6]]
 * <strong>输出：</strong>[1,1,2,3,4,4,5,6]
 * <strong>解释：</strong>链表数组如下：
 * [
 * 1-&gt;4-&gt;5,
 * 1-&gt;3-&gt;4,
 * 2-&gt;6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1-&gt;1-&gt;2-&gt;3-&gt;4-&gt;4-&gt;5-&gt;6
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入：</strong>lists = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre><strong>输入：</strong>lists = [[]]
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>k == lists.length</code></li>
 * <li><code>0 &lt;= k &lt;= 10^4</code></li>
 * <li><code>0 &lt;= lists[i].length &lt;= 500</code></li>
 * <li><code>-10^4 &lt;= lists[i][j] &lt;= 10^4</code></li>
 * <li><code>lists[i]</code> 按 <strong>升序</strong> 排列</li>
 * <li><code>lists[i].length</code> 的总和不超过 <code>10^4</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li><li>分治</li><li>堆（优先队列）</li><li>归并排序</li></div></div><br><div><li>👍
 * 2008</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 23
 * 合并K个升序链表
 */
public class MergeKSortedLists {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new MergeKSortedLists().new Solution();
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


        // 结合148一起看
        //// 方法二：分治合并
        //// 输入的k个排序链表可以分成两部分，前k/2个链表和后k/2个链表。
        //// 如果将前k/2个链表和后k/2个链表分别合并成两个排序的链表，再将两个排序的链表合并，那么所有链表都合并了。
        //// 合并k/2个链表与合并k个链表是同一个问题，可以调用递归函数解决。

        //public ListNode mergeKLists(ListNode[] lists) {
        //    if (lists == null || lists.length == 0) {
        //        return null;
        //    }
        //    return mergeLists(lists, 0, lists.length - 1);
        //}
        //
        //
        //// 递归实现分治  双闭区间[left,right]
        //public ListNode mergeLists(ListNode[] lists, int left, int right) {
        //    if (left == right) { // 终止条件1：左右重合，即只有一个链表，链表本来就是有序的，直接返回就行
        //        return lists[left];
        //    }
        //    if (left > right) { // 终止条件2：没有链表需要合并
        //        return null;
        //    }
        //    int mid = (left + right) / 2;
        //    ListNode leftLists = mergeLists(lists, left, mid);// 递归完成左半边合并
        //    ListNode rightLists = mergeLists(lists, mid + 1, right);// 递归完成右半边合并
        //    return mergeTwoLists(leftLists, rightLists);// 左右半边合并
        //}
        //
        //
        //// 合并两个有序链表  链表合并只需要连接在一起就可以
        //private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //    ListNode dummyHead = new ListNode(-1);//合并后链表的哨兵节点
        //    ListNode curr = dummyHead;//辅助遍历合并后链表的指针curr
        //    ListNode curr1 = list1;//辅助遍历链表list1的指针curr1
        //    ListNode curr2 = list2;
        //    while (curr1 != null && curr2 != null) {//两个链表都没有遍历完
        //        //选取两个链表中数值小的节点连接到合并后的链表中，这里并没有取下该节点，只是不断的更新
        //        if (curr1.val <= curr2.val) {
        //            curr.next = curr1;// 将curr1连接到合并后链表中
        //            curr1 = curr1.next;// curr1指针后移
        //        } else {
        //            curr.next = curr2;
        //            curr2 = curr2.next;
        //        }
        //        curr = curr.next;// curr指针后移
        //    }
        //
        //    //循环结束，则至少有一个链表已经遍历完了，curr1或者curr2指向空指针
        //    if (curr1 == null) {
        //        curr.next = curr2;
        //    }
        //    if (curr2 == null) {
        //        curr.next = curr1;
        //    }
        //    //curr.next = curr1 == null ? curr2 : curr1;//三目运算符和上面if-else语句作用一样
        //    return dummyHead.next;
        //}




        //// 方法一：逐个合并升序链表  每次将合并后的链表和新的链表合并
        //// 用一个变量 ans 来维护以及合并的链表，第 i 次循环把第 i 个链表和 ans 合并，答案保存到 ans 中。
        //
        //public ListNode mergeKLists(ListNode[] lists) {
        //    if (lists == null || lists.length == 0) {
        //        return null;
        //    }
        //    ListNode ans=null;
        //    int length=lists.length;
        //    for (int i = 0; i < length; i++) {
        //        ans=mergeTwoLists(ans,lists[i]);
        //    }
        //    return ans;
        //}
        //
        //
        //// 两两合并数组
        //private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //    ListNode dummyHead=new ListNode(-1);//合并后链表的哨兵节点
        //    ListNode curr=dummyHead;//辅助遍历合并后链表的指针curr
        //    ListNode curr1=list1;//辅助遍历链表list1的指针curr1
        //    ListNode curr2=list2;
        //    while(curr1!=null&&curr2!=null){//两个链表都没有遍历完
        //        //选取两个链表中数值小的节点连接到合并后的链表中，这里并没有取下该节点，只是不断的更新
        //        if(curr1.val<=curr2.val){
        //            curr.next=curr1;// 将curr1连接到合并后链表中
        //            curr1=curr1.next;// curr1指针后移
        //        }else{
        //            curr.next = curr2;
        //            curr2=curr2.next;
        //        }
        //        curr=curr.next;// curr指针后移
        //    }
        //
        //    //循环结束，则至少有一个链表已经遍历完了，curr1或者curr2指向空指针
        //    if(curr1==null) {
        //        curr.next = curr2;
        //    }
        //    if(curr2==null){
        //        curr.next=curr1;
        //    }
        //    //curr.next = curr1 == null ? curr2 : curr1;//三目运算符和上面if-else语句作用一样
        //    return dummyHead.next;
        //}





        //// 解法3：最小堆实现
        //// 用k个指针分别指向这k个链表的头节点，每次从这k个节点中选取值最小的节点。
        //// 然后将指向值最小的节点的指针向后移动一步，再比较k个指针指向的节点并选取值最小的节点。
        //// 重复这个过程，直到所有节点都被选取出来。
        //// 这种思路需要反复比较k个节点并选取值最小的节点。
        //// 既可以每次都用一个for循环用O（k）的时间复杂度比较k个节点的值，也可以将k个节点放入一个最小堆中，位于堆顶的节点就是值最小的节点。
        //// 每当选取某个值最小的节点之后，将它从堆中删除并将它的下一个节点添加到堆中。
        //
        //// 利用最小堆选取最小的节点。k个指针分别指向这k个链表的头节点，每次从这k个节点中选取值最小的节点。
        //// 然后将指向最小的节点的指针向后移动一步，再比较k个指针指向的节点并选取值最小的节点。重复这个过程。


        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            ListNode dummy = new ListNode(-1);// 合并后链表的哨兵节点
            ListNode curr = dummy;// 合并后链表的遍历指针
            PriorityQueue<ListNode> minHeap = new PriorityQueue<>((node1,node2)->node1.val-node2.val);// Lambda表达式来实现最小堆
            // 匿名内部类实现最小堆
            //PriorityQueue<ListNode> minHeap=new PriorityQueue<>((new Comparator<ListNode>() {
            //    @Override
            //    public int compare(ListNode o1, ListNode o2) {
            //        return o1.val-o2.val;
            //
            //    }
            //}));
            for (ListNode list : lists) {// 将k个链表的第一个数据节点放入最小堆中，每个链表中的第一个节点形成最小堆
                if (list != null) {
                    minHeap.offer(list);
                }
            }

            while (!minHeap.isEmpty()) {
                ListNode min = minHeap.poll();// 最小堆的堆顶元素弹出,位于堆顶的节点就是值最小的节点。
                curr.next = min;// 将当前最小的节点连接到新链表尾部
                curr = curr.next;// 合并后链表的指针后移
                if (min.next != null) {// 将指向最小的节点的指针向后移动一步，将弹出的堆顶元素的下一个节点入堆
                    minHeap.offer(min.next);
                }
            }
            return dummy.next;
        }




    }
//leetcode submit region end(Prohibit modification and deletion)

}
