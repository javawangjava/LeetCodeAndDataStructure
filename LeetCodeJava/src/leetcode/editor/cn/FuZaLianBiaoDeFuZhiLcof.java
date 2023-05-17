/**
 * <p>请实现 <code>copyRandomList</code> 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 <code>next</code> 指针指向下一个节点，还有一个
 * <code>random</code> 指针指向链表中的任意节点或者 <code>null</code>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png"></p>
 *
 * <pre><strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png"></p>
 *
 * <pre><strong>输入：</strong>head = [[1,1],[2,1]]
 * <strong>输出：</strong>[[1,1],[2,1]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png"></strong></p>
 *
 * <pre><strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
 * <strong>输出：</strong>[[3,null],[3,0],[3,null]]
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre><strong>输入：</strong>head = []
 * <strong>输出：</strong>[]
 * <strong>解释：</strong>给定的链表为空（空指针），因此返回 null。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>-10000 &lt;= Node.val &lt;= 10000</code></li>
 * <li><code>Node.random</code>&nbsp;为空（null）或指向链表中的节点。</li>
 * <li>节点数目不超过 1000 。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>注意：</strong>本题与主站 138 题相同：
 * <a href="https://leetcode-cn.com/problems/copy-list-with-random-pointer/">https://leetcode-cn.com/problems/copy-list-with-random-pointer/</a></p>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>链表</li></div></div><br><div><li>👍 594</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 35
 * 复杂链表的复制
 *
 * @author wangweizhou
 * @date 2022-09-23 16:44:33
 */

public class FuZaLianBiaoDeFuZhiLcof {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FuZaLianBiaoDeFuZhiLcof().new Solution();
    }

//leetcode submit region begin(Prohibit modification and deletion)

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

    class Solution {

      /*  // 解法2：
        public Node copyRandomList(Node head) {
            if (head == null) {
                return head;
            }

            // 第一步：将复制的节点连接到原节点之后
            Node node = head;
            while (node != null) {
                Node temp = new Node(node.val);// 以当前节点值创建新的节点
                temp.next = node.next;// 将当前节点的后续节点连接到新复制的节点之后
                node.next = temp;// 将新复制的节点连接到当前节点之后
                node=node.next.next;// 当前节点后移至原链表中当前节点的后续节点
            }

            // 第二步：复制节点的随机指针域
            node=head;
            while(node!=null){
                // 因为复制的节点的随机指针域是对应原节点的随机指针域的下一个，即node.random.next，
                // 也就是要访问一个节点（node.random）的指针域（node.random.next），所以这里这里要对node.random判空
                // 根据题意，空节点没有下一个指针域和随机指针域
                if(node.random!=null){
                    node.next.random=node.random.next;
                }else{
                    node.next.random=null;
                }
               node=node.next.next;
            }

            // 第三步：拆分链表为原链表和复制后的链表
            node=head;
            Node dummy=new Node(-1);// 复制的链表的哨兵节点
            Node node2=dummy;// 复制链表的遍历指针
            while(node!=null){
                node2.next=node.next;// 复制的节点连接到赋值链表上
                node2=node2.next;// 复制链表的指针后移
                node.next=node.next.next;//将原链表的节点链接起来，移除赋值的节点
                node=node.next;// 原链表指针后移
            }
            return dummy.next;
        }
*/


        /*

        // 解法1：哈希表  写法1
        public Node copyRandomList(Node head) {
            if (head == null) {// 判空
                return head;
            }
            Map<Node, Node> map = new HashMap<>();// 哈希表存储原节点和复制的节点
            Node node = head;// 原链表的辅助遍历变量
            Node newHead = new Node(-1);// 复制后链表的哨兵节点也是遍历赋值变量

            while (node != null) {
				Node temp = new Node(node.val);// 以当前节点值创建新节点
				map.put(node, temp);// 将原节点和复制的节点存储到哈希表中
				newHead.next=temp;// 将复制的节点连接到新链表上
				node=node.next;// 原链表指针后移
				newHead = newHead.next;// 复制的链表的指针后移
            }

            node = head;// 遍历节点重置
            while (node != null) {
                //   map.get(node)：获取与node 对应的复制后节点。node.random：node节点的随机指针域
                map.get(node).random=map.get(node.random);// 这里利用的map直接得到与node对应的复制后节点
                node = node.next;
            }
			return map.get(head);
        }

        */




        /*

        // 解法1：哈希表  写法2
        public Node copyRandomList(Node head) {
            if (head == null) {
                return head;
            }
            Map<Node, Node> map = new HashMap<>();// 哈希表存储原节点和复制的节点
            Node node = head;
            Node dummy=new Node(-1);
            Node node2=dummy;

            while (node != null) {
                Node temp = new Node(node.val);
                node2.next=temp;
                map.put(node, temp);
                node=node.next;
                node2 = node2.next;
            }

            // 这里是利用遍历两个链表，连接对应的节点
            node = head;
            node2=dummy.next;
            while (node != null) {
                node2.random=map.get(node.random);// 这里利用的map直接得到与node对应的复制后节点
                node = node.next;
                node2=node2.next;
            }
            return dummy.next;
        }

        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}

/*

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}*/
