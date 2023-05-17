/**
 * <p>给定一个已排序的链表的头<meta charset="UTF-8" />&nbsp;<code>head</code>&nbsp;，&nbsp;<em>删除所有重复的元素，使每个元素只出现一次</em>&nbsp;。返回
 * <em>已排序的链表</em>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list1.jpg" style="height: 160px; width: 200px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,1,2]
 * <strong>输出：</strong>[1,2]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list2.jpg" style="height: 123px; width: 300px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,1,2,3,3]
 * <strong>输出：</strong>[1,2,3]
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
 * <div><div>Related Topics</div><div><li>链表</li></div></div><br><div><li>👍 824</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 83
 * 删除排序链表中的重复元素
 * @author wangweizhou
 * @date 2022-07-19 10:04:27
 */

public class RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RemoveDuplicatesFromSortedList().new Solution();
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
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */

    class Solution {


        // 保留相同元素的第一个元素
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode left = head;
            ListNode right = head;
            while (right != null && right.next != null) {
                while (right != null && right.val == left.val) {
                    right = right.next;
                }
                left.next = right;
                left = right;
            }
            return head;
        }



        //// 解法1：一遍遍历 写法3
        //public ListNode deleteDuplicates(ListNode head) {
        //	if (head == null||head.next==null) {// 判空和只有一个节点
        //		return head;
        //	}
        //	ListNode right=head;// right 指向待判断区间的第一个节点
        //	// 到这里链表至少有两个节点  判断区间[left,right]中是否有重复节点
        //	while(right!=null&&right.next!=null){//右指针遍历整个链表
        //		ListNode left=right;//左指针和右指针起点相同，开始区间是[left,right]。
        //		while(right!=null&&right.val==left.val){// 遇到相同的元素则right指针右移，直至right指向空指针或者下一个不同的数据元素位置
        //			right=right.next;
        //		}
        //		left.next=right;
        //	}
        //	return head;
        //}
        //



/*
	// 解法1：
	public ListNode deleteDuplicates(ListNode head) {
		if(head==null){//空链表
			return head;
		}
		ListNode curr=head;//遍历指针
		while(curr!=null&&curr.next!=null){//指针当前和下⼀位不为空
			// 相同数值的节点一次只保留一个，其他相同数值的节点一次只移除一位
			if(curr.val==curr.next.val){
				// 链表可以直接指向下一个节点
				curr.next=curr.next.next; //如果当前与下⼀位相等则忽略下⼀位
			}else{
				curr=curr.next;//否则指针正常遍历
			}
		}
		return head;
	}

*/




	/*
	// 解法2：双指针 写法1
    public ListNode deleteDuplicates(ListNode head) {
		if(head==null){
			return head;
		}
		ListNode slow=head;// slow保存每次相同元素的第一个
		ListNode fast=head;// fast遍历指针
		while(fast.next!=null){
			if(fast.val==slow.val){// 当前指针与slow指针的值相同，fast后移
				fast=fast.next;
			}
			//在上面移动之后，fast和slow指向的值可能不相等
			if(fast.val!=slow.val){// 当fast指向的值与slow指向的值不相等时，将fast连接到slow后面，
				slow.next=fast;
				slow=slow.next;//slow后移
			}
		}
		//为了应对链表后面几位都是相同的数，slow之后的指针置空
		slow.next=null;
		return head;
    }
	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
