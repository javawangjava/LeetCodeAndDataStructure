/**
 * <div class="title__3Vvk">
 * <p>运用所掌握的数据结构，设计和实现一个&nbsp;
 * <a href="https://baike.baidu.com/item/LRU" target="_blank">LRU (Least Recently Used，最近最少使用) 缓存机制</a> 。</p>
 *
 * <p>实现 <code>LRUCache</code> 类：</p>
 *
 * <ul>
 * <li><code>LRUCache(int capacity)</code> 以正整数作为容量&nbsp;<code>capacity</code> 初始化 LRU 缓存</li>
 * <li><code>int get(int key)</code> 如果关键字 <code>key</code> 存在于缓存中，则返回关键字的值，否则返回 <code>-1</code> 。</li>
 * <li><code>void put(int key, int value)</code>&nbsp;
 * 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * [&quot;LRUCache&quot;, &quot;put&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;put&quot;, &quot;get&quot;, &quot;get&quot;, &quot;get&quot;]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * <strong>输出</strong>
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * <strong>解释</strong>
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= capacity &lt;= 3000</code></li>
 * <li><code>0 &lt;= key &lt;= 10000</code></li>
 * <li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
 * <li>最多调用 <code>2 * 10<sup>5</sup></code> 次 <code>get</code> 和 <code>put</code></li>
 * </ul>
 * </div>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶</strong>：是否可以在&nbsp;<code>O(1)</code> 时间复杂度内完成这两种操作？</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><meta charset="UTF-8" />注意：本题与主站 146&nbsp;题相同：
 * <a href="https://leetcode-cn.com/problems/lru-cache/">https://leetcode-cn.com/problems/lru-cache/</a>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>设计</li><li>哈希表</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍
 * 71</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer II 031
 * 最近最少使用缓存
 *
 * @author wangweizhou
 * @date 2022-11-10 15:47:55
 */
public class OrIXps {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new OrIXps().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        // 解法1： 哈希表 + 双向链表
        // LRU 缓存机制可以通过哈希表辅以双向链表实现，添加删除这些操作哈希表和双向链表要同步变化。
        //   双向链表中存储元素，哈希表的键key存储元素，哈希表的值value存储该元素在双向链表中的节点。
        //   同一个数据存储在两个地方，利用各自的优势进行互补操作。因此操作数据在数组和哈希表中要同时处理。

        // 双向链表按照被使用的顺序存储了这些键值对，靠近头部的键值对是最近使用的，而靠近尾部的键值对是最久未使用的。

        // 哈希表的键key存储双向链表中的数据域也就是数值；值value存储该数值在双向链表中对应的节点
        private Map<Integer, DLinkedNode> cache = new HashMap<>();
        private int size;// 缓存中的实际元素个数
        private int capacity;// 缓存的容量
        private DLinkedNode dummyHead, dummyTail;// 双向链表的哨兵节点


        public LRUCache(int capacity) {
            this.size = 0;// 缓存为空，缓存中的元素个数为0
            this.capacity = capacity;
            // 双向链表的哨兵节点
            dummyHead = new DLinkedNode();
            dummyTail = new DLinkedNode();
            // 空双向链表的初始化
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }


        // int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1
        public int get(int key) {
            DLinkedNode node = cache.get(key);// 从哈希表中获取该数值在双向链表中的对应节点
            if (node == null) {// 双向链表中没有该元素对应的节点，也就是缓存中没有该节点
                return -1;
            }
            // 如果 key 存在，先通过哈希表定位该节点，然后再将该节点移到头部
            moveToHead(node);
            return node.value;
        }


        // void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。
        // 如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
        public void put(int key, int value) {
            DLinkedNode node = cache.get(key);// 从哈希表中获取该数值在双向链表中的对应节点
            if (node == null) {
                // 如果 key 不存在，创建一个新的节点
                DLinkedNode newNode = new DLinkedNode(key, value);
                // 添加进哈希表
                cache.put(key, newNode);
                // 添加至双向链表的头部
                addToHead(newNode);
                ++size;
                if (size > capacity) {
                    // 如果超出容量，删除双向链表的尾部节点
                    DLinkedNode tail = removeTail();
                    // 删除哈希表中对应的项
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
                node.value = value;
                moveToHead(node);
            }
        }


        // 将节点添加到双向链表的头部, 头插法 画图  断开链接时使用临时遍历保存下一个节点，便于理解。

        private void addToHead(DLinkedNode node) {
            DLinkedNode next = dummyHead.next;// 断开链接时使用临时遍历保存下一个节点
            node.prev = dummyHead;//
            dummyHead.next = node;//
            node.next = next;//
            next.prev = node;//
        }


        // 将节点从双链表中移除
        private void removeNode(DLinkedNode node) {
            node.prev.next = node.next;// 待删除节点的前一个节点的后一个节点是待删除节点的后一个节点
            node.next.prev = node.prev;// 待删除节点的后一个节点的前一个节点是待删除节点的前一个节点
        }


        // 将节点移动到双链表的头部
        private void moveToHead(DLinkedNode node) {
            removeNode(node);// 先从双向链表中移除该节点
            addToHead(node);// 再将链表添加到双向链表的头部
        }


        // 删除双向链表的最后一个数据节点
        private DLinkedNode removeTail() {
            DLinkedNode res = dummyTail.prev;// 获取双向链表的最后一个数据节点
            removeNode(res);// 删除最后一个数据节点
            return res;
        }


        class DLinkedNode {
            int key;
            int value;
            DLinkedNode prev;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int _key, int _value) {
                key = _key;
                value = _value;
            }
        }
    }



    /**
     * Your LRUCache object will be instantiated and called as such:
     * LRUCache obj = new LRUCache(capacity);
     * int param_1 = obj.get(key);
     * obj.put(key,value);
     */
//leetcode submit region end(Prohibit modification and deletion)

}

