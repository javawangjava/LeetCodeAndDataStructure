/**
 * <p>将两个升序链表合并为一个新的 <strong>升序</strong> 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 </p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/merge_ex1.jpg" style="width: 662px; height: 302px;
 * " />
 * <pre>
 * <strong>输入：</strong>l1 = [1,2,4], l2 = [1,3,4]
 * <strong>输出：</strong>[1,1,2,3,4,4]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [], l2 = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>l1 = [], l2 = [0]
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>两个链表的节点数目范围是 <code>[0, 50]</code></li>
 * <li><code>-100 <= Node.val <= 100</code></li>
 * <li><code>l1</code> 和 <code>l2</code> 均按 <strong>非递减顺序</strong> 排列</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 2474</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 合并两个有序链表
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new MergeTwoSortedLists().new Solution();
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


        //  解法1：两个指针迭代解决
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            // 后面的写法包含链表为空的情况，这里只是单独写出来了，
            if (list1 == null) {
                return list2;
            }
            if (list2 == null) {
                return list1;
            }

            ListNode dummyHead = new ListNode(-1);//合并后链表的哨兵节点
            ListNode curr = dummyHead;//辅助遍历合并后链表的指针cur
            ListNode curr1 = list1;//辅助遍历链表list1的指针curr1
            ListNode curr2 = list2;

            while (curr1 != null && curr2 != null) {//两个链表都没有遍历完
                //选取两个链表中数值小的节点连接到合并后的链表中，这里并没有取下该节点，只是不断的更新
                if (curr1.val <= curr2.val) {
                    curr.next = curr1;// 将curr1连接到合并后链表中
                    curr1 = curr1.next;// curr1指针后移
                } else {
                    curr.next = curr2;
                    curr2 = curr2.next;
                }
                curr = curr.next;// curr指针后移
            }

            //循环结束，则至少有一个链表已经遍历完了，curr1或者curr2指向空指针
            if (curr1 == null) {
                curr.next = curr2;
            } else if (curr2 == null) {
                curr.next = curr1;
            }
            //curr.next = curr1 == null ? curr2 : curr1;//三目运算符和上面if-else语句作用一样
            return dummyHead.next;
        }



       /*

        //解法2：递归
        // 终止条件：当两个链表都为空时，表示我们对链表已合并完成。
        // 如何递归：我们判断 l1 和 l2 头结点哪个更小，然后较小结点的 next 指针指向其余结点的合并结果。（调用递归）
        // 递归需要终止条件，递归要向着终止条件这边调用


        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

            if (list1 == null) {//list1为空，直接返回List2
                return list2;
            } else if (list2 == null) {
                return list1;
            } else if (list1.val < list2.val) {
            //我们判断 l1 和 l2 头结点哪个更小，然后较小结点的 next 指针指向其余结点的合并结果。（调用递归）
                list1.next = mergeTwoLists(list1.next, list2);// 递归合并List1剩余链表和list2链表，并连接到list1之后
                return list1;//
            } else {
                list2.next = mergeTwoLists(list1, list2.next);
                return list2;
            }
        }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}

