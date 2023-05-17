/**
 * <p>给你一个长度为 <code>n</code> 的链表，每个节点包含一个额外增加的随机指针 <code>random</code> ，该指针可以指向链表中的任何节点或空节点。</p>
 *
 * <p>构造这个链表的&nbsp;<strong><a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin" target="_blank">深拷贝</a>
 * </strong>。&nbsp;深拷贝应该正好由 <code>n</code> 个 <strong>全新</strong> 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 <code>next</code>
 * 指针和 <code>random</code> 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。<strong>复制链表中的指针都不应指向原链表中的节点 </strong>。</p>
 *
 * <p>例如，如果原链表中有 <code>X</code> 和 <code>Y</code> 两个节点，其中 <code>X.random --&gt; Y</code> 。那么在复制链表中对应的两个节点
 * <code>x</code> 和 <code>y</code> ，同样有 <code>x.random --&gt; y</code> 。</p>
 *
 * <p>返回复制链表的头节点。</p>
 *
 * <p>用一个由&nbsp;<code>n</code>&nbsp;个节点组成的链表来表示输入/输出中的链表。每个节点用一个&nbsp;<code>[val, random_index]</code>&nbsp;表示：</p>
 *
 * <ul>
 * <li><code>val</code>：一个表示&nbsp;<code>Node.val</code>&nbsp;的整数。</li>
 * <li><code>random_index</code>：随机指针指向的节点索引（范围从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>）；如果不指向任何节点，则为&nbsp;
 * &nbsp;<code>null</code>&nbsp;。</li>
 * </ul>
 *
 * <p>你的代码 <strong>只</strong> 接受原链表的头节点 <code>head</code> 作为传入参数。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png" style="height:
 * 142px; width: 700px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * <strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png" style="height:
 * 114px; width: 700px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [[1,1],[2,1]]
 * <strong>输出：</strong>[[1,1],[2,1]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png"
 * style="height: 122px; width: 700px;" /></strong></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
 * <strong>输出：</strong>[[3,null],[3,0],[3,null]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= n &lt;= 1000</code><meta charset="UTF-8" /></li>
 * <li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li>
 * <li><code>Node.random</code>&nbsp;为&nbsp;<code>null</code> 或指向链表中的节点。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>链表</li></div></div><br><div><li>👍 929</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 138
 * 复制带随机指针的链表
 *
 * @author wangweizhou
 * @date 2022-07-06 02:52:27
 */


public class CopyListWithRandomPointer {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new CopyListWithRandomPointer().new Solution();
    }

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
        // 在网站提交

        //
        //// 解法1：哈希表  写法2是将写法1的简化了，
        //// 创建哈希表，在哈希表中保存和原链表数据一样的每一个单独的数据节点。哈希表的key是原节点，value是以该节点的数据域新建立的复制节点。
        //public Node copyRandomList(Node head) {
        //    if (head == null) {
        //        return null;
        //    }
        //    // 创建一个哈希表，哈希表的key是原节点，value是以原节点的数据值建立的新节点
        //    Map<Node, Node> map = new HashMap<>();
        //    Node curr = head;// 遍历指针
        //
        //    // 遍历原链表将每个节点和对应复制的节点加入到哈希表中
        //    // 将原节点和与原节点对应的新节点放入哈希表中，
        //    while (curr != null) {// 循环遍历将原节点存储到map中
        //        map.put(curr, new Node(curr.val));
        //        curr = curr.next;
        //    }
        //
        //    // 遍历原链表，按照原链表的顺序连接复制的节点，并连接复制后链表的随机指针
        //    curr = head;// 遍历指针重置
        //    while (curr != null) {//循环遍历建立新的链表并连接
        //        // 以当前节点curr的数值复制建立的复制节点的下一个节点 map.get(curr).next 是以当前节点的下一个节点的数值域复制建立的节点map.get(curr.next)
        //        map.get(curr).next = map.get(curr.next);// 对应新节点连接next指针，当前节点指针域的下一个是下一个节点的指针域
        //        map.get(curr).random = map.get(curr.random);// 连接random指针
        //        curr = curr.next;
        //    }
        //    //返回头结点，即原节点对应的value(新节点)
        //    return map.get(head);
        //}


        //// 解法1：哈希表 写法1
        //public Node copyRandomList(Node head) {
        //    if (head == null) {
        //        return null;
        //    }
        //    // 创建一个哈希表，哈希表的key是原节点，value是以原节点的数据值建立的新节点
        //    Map<Node, Node> map = new HashMap<>();
        //    Node curr = head;// 遍历指针
        //
        //    // 将原节点和新节点放入哈希表中
        //    while (curr != null) {
        //        map.put(curr, new Node(curr.val));
        //        curr = curr.next;
        //    }
        //
        //    curr = head;
        //    // 遍历原链表，设置新节点的next和random
        //    while (curr != null) {
        //        Node newNode = map.get(curr);
        //        //p是原节点，map.get(p)是对应的新节点，p.next是原节点的下一个
        //        //map.get(p.next)是原节点下一个节点p对应的新节点
        //        if (curr.next != null) {// 哈希表中存放的都是非空节点，当哈希表中不存在时会返回null。所以这里没必要判空。
        //            newNode.next = map.get(curr.next);
        //        }
        //        //p.random是原节点随机指向的节点
        //        //map.get(p.random)是原节点随机指向的节点  对应的新节点
        //        if (curr.random != null) {
        //            newNode.random = map.get(curr.random);
        //        }
        //        curr = curr.next;
        //    }
        //    //返回头结点，即原节点对应的value(新节点)
        //    return map.get(head);
        //}




        //// 方法二：迭代 + 节点拆分
        //// 第一步，根据遍历到的原节点创建对应的新节点，每个新创建的节点是在原节点后面
        //// 第二步，原节点i的随机指针(如果有的话)，指向的是原节点j，那么新节点i的随机指针，指向的是原节点j的next，
        //// 第三步，只要将两个链表分离开，再返回新链表

        //public Node copyRandomList(Node head) {
        //    if (head == null) {// 判空
        //        return head;
        //    }
        //
        //    Node curr = head;// curr遍历链表的辅助指针
        //
        //    // 复制节点但不带random指针域，并插入到原节点的后面
        //    while (curr != null) {
        //        Node newNode = new Node(curr.val);//创建一个数据域一样的节点
        //        //将新创建的节点插入到原节点后面
        //        newNode.next = curr.next;
        //        curr.next = newNode;
        //        curr = curr.next.next;//原节点指针后移，注意是curr=curr.next.next，因为赋值的新节点连接在了原节点的后面
        //    }
        //
        //    //	复制random指针域
        //    curr = head;//curr遍历链表的辅助指针重置
        //    while (curr != null) {
        //        // 根据示例，链表节点的random指针可能指向null,那么null没有下一个节点，所以这里需要判空。
        //        if (curr.random != null) {// 当前节点的指针域不空，复制指针域
        //            // 在curr.next.random中：(curr.next)表示当前节点的下一个节点，【也就是复制的新节点】
        //            // （curr.next.random）表示当前节点的下一个节点的random指针域指向的节点【也就是复制的节点的random指针域】
        //            // 在curr.random.next：（curr.random）表示当前节点的random指针域指向的节点
        //            // （curr.random.next）表示当前节点的random指针域指向的节点的下一个节点
        //            // 【当前节点的random指针域指向的节点的next指针域，也就是原节点random指针域的下一个节点其实也就是和原random指针域数据值相同的节点】
        //            curr.next.random = curr.random.next;//
        //        }
        //        curr = curr.next.next;
        //    }
        //
        //    // 交替拆分两个链表，并复原原链表   双指针拆分
        //    Node dummyHead = new Node(-1);// 复制的链表的哨兵节点
        //    Node currNew = dummyHead;// 新复制链表的遍历指针
        //    curr = head;
        //    while (curr != null) {
        //        currNew.next = curr.next;// 复制的节点连接到复制的链表的节点后面
        //        currNew = currNew.next;// 新复制节点指针后移
        //
        //        curr.next = curr.next.next;// 原节点连接到原节点后面
        //        curr = curr.next;// 原节点遍历指针后移
        //    }
        //    return dummyHead.next;
        //}


    }
}


//// 类Node声明冲突。和其他题目的的声明冲突

//class Node {// 插件提交不知道哪里冲突了
//    int val;
//    Node next;
//    Node random;
//    public Node(int val) {
//        this.val = val;
//        this.next = null;
//        this.random = null;
//    }
//}
