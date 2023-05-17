/**
 * <p>给你一个链表的头节点 <code>head</code> ，旋转链表，将链表每个节点向右移动&nbsp;<code>k</code><em>&nbsp;</em>个位置。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/rotate1.jpg" style="width: 450px;" />
 * <pre>
 * <strong>输入：</strong>head = [1,2,3,4,5], k = 2
 * <strong>输出：</strong>[4,5,1,2,3]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/roate2.jpg" style="width: 305px; height: 350px;" />
 * <pre>
 * <strong>输入：</strong>head = [0,1,2], k = 4
 * <strong>输出：</strong>[2,0,1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目在范围 <code>[0, 500]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * <li><code>0 &lt;= k &lt;= 2 * 10<sup>9</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>链表</li><li>双指针</li></div></div><br><div><li>👍 793</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 61
 * 旋转链表
 * @author wangweizhou
 * @date 2022-06-30 20:43:05
 */
public class RotateList {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RotateList().new Solution();
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


        // 解法2：遍历得到链表长度，链表成环，然后再移动
        public ListNode rotateRight(ListNode head, int k) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode curr = head;
            int length = 1;//注意这里是从1开始的
            //因为后面要链表成环，所以需要链表的最后一个数据节点(curr.next != null)
            // 遍历链表得到链表长度，循环结束curr指向链表的最后一个数据节点
            while (curr.next != null) {//注意这里是(curr.next!=null)，所以节点数就是length
                length++;
                curr = curr.next;
            }

            curr.next = head;//链表成环，curr指向原链表的最后一个数据节点
            int newK = k % length;//移动位置对长度取余就得到实际应该移动的次数
            if(newK==0){//取余是0就表明不需要移动链表节点
                curr.next =null;
                return head;
            }else{
                //for循环结束指向倒数第（newK+1）个节点
                for (int i = 0; i < length - newK; i++) {
                    curr = curr.next;
                }
                ListNode newHead=curr.next;
                curr.next = null;
                return newHead;
            }
        }


	/*

	// 解法1：遍历得到链表长度，双指针确定位置
	public ListNode rotateRight(ListNode head, int k) {
		if(head==null||head.next==null){
			return head;
		}
		ListNode curr=head;
		int length=0;//注意这里是从0开始的
		// 遍历链表得到链表长度
		while(curr!=null){//注意这里是(curr!=null)，所以节点数就是length
			length++;
			curr=curr.next;
		}

		int newK=k%length;//移动位置对长度取余就得到实际应该移动的次数
		if(newK==0){
			return head;
		}else {
			ListNode slow=head;
			ListNode fast=head;
			int index=0;
			//快指针fast比慢指针提前newK个位置
			while(index<newK){
				fast=fast.next;
				index++;
			}
			//因为已经知道了链表长度，所以这里利用链表长度进行遍历
			//循环结束，快指针fast移动到链表最后一个节点，慢指针slow移动到倒数第（newK+1）个位置
			while(index<length-1){//
				fast=fast.next;
				slow=slow.next;
				index++;
			}
			ListNode newHead=slow.next;
			fast.next=head;
			slow.next=null;
			return newHead;
		}
	}
	*/




/*

// 解法1写法2：遍历得到链表长度，双指针确定位置
    public ListNode rotateRight(ListNode head, int k) {
		if(head==null||head.next==null){
			return head;
		}
		ListNode slow =head;
		ListNode fast =head;
		int num=0;//注意这里是从0开始的
		// 遍历链表得到链表长度
		while(fast.next!=null){//注意这里是(right.next!=null)，所以节点数就是(num+1)
			num++;
			fast = fast.next;
		}
		int moveNum=k%(num+1);//动位置对长度取余就得到实际应该移动的次数
		if(moveNum==0){
			return head;
		}else {
			//若总长为5，倒数第2个就是正数第4个.（2+4-1=5）
			//循环结束，快指针fast移动到链表最后一个节点，慢指针slow移动到倒数第（moveNum+1）个位置
			for (int i = 0; i <num-moveNum; i++) {
				slow = slow.next;
			}
			ListNode newHead= slow.next;
			slow.next=null;
			fast.next=head;
			return newHead;
		}
    }*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}
