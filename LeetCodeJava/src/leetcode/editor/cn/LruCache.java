/**
 * <div class="title__3Vvk">请你设计并实现一个满足&nbsp;
 * <a href="https://baike.baidu.com/item/LRU" target="_blank">LRU (最近最少使用) 缓存</a> 约束的数据结构。</div>
 *
 * <div class="title__3Vvk">实现 <code>LRUCache</code> 类：</div>
 *
 * <div class="original__bRMd">
 * <div>
 * <ul>
 * <li><code>LRUCache(int capacity)</code> 以 <strong>正整数</strong> 作为容量&nbsp;<code>capacity</code> 初始化 LRU 缓存</li>
 * <li><code>int get(int key)</code> 如果关键字 <code>key</code> 存在于缓存中，则返回关键字的值，否则返回 <code>-1</code> 。</li>
 * <li><code>void put(int key, int value)</code>&nbsp;如果关键字&nbsp;<code>key</code> 已经存在，则变更其数据值&nbsp;
 * <code>value</code> ；如果不存在，则向缓存中插入该组&nbsp;<code>key-value</code> 。如果插入操作导致关键字数量超过&nbsp;<code>capacity</code> ，则应该
 * <strong>逐出</strong> 最久未使用的关键字。</li>
 * </ul>
 *
 * <p>函数 <code>get</code> 和 <code>put</code> 必须以 <code>O(1)</code> 的平均时间复杂度运行。</p>
 * </div>
 * </div>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
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
 * <div><div>Related Topics</div><div><li>设计</li><li>哈希表</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍
 * 2361</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 146
 * LRU 缓存
 *
 * @author wangweizhou
 * @date 2022-08-27 21:33:10
 */

public class LruCache {
    public static void main(String[] args) {
        //测试代码
        LRUCache solution = new LruCache().new LRUCache(10);
        solution.put(10,11);
        solution.get(10);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {


        // 双向链表+哈希表

        // 哈希表HashMap的get操作和put操作的时间复杂度都是O（1），但普通的哈希表无法找出最近最少使用的键，因此，需要在哈希表的基础上进行改进。
        // 由于需要知道缓存中最近最少使用的元素，因此可以把存入的元素按照访问的先后顺序存入链表中。
        // 每次访问一个元素（无论是通过get操作还是通过put操作），该元素都被移到链表的尾部。这样，位于链表头部的元素就是最近最少使用的。【注意这里尾部表示最近使用的元素】

        // 下面考虑如何实现把一个节点移到链表的尾部。这实际上包含两个步骤，首先要把节点从原来的位置删除，然后把它添加到链表的尾部。
        // 需要注意的是，在链表中删除一个节点，实际上是把它的前一个节点的next指针指向它的下一个节点。
        // 为了快速找到一个节点的前一个节点从而实现用O（1）的时间删除一个节点，可以用双向链表来存储缓存中的元素。
        // 在双向链表中查找一个节点的前一个节点只需要顺着prev指针向前走一步，时间复杂度为O（1）。


        // 定义最近最少使用缓存的数据结构。缓存中包含一个哈希表，哈希表的键就是缓存的键，哈希表的值是双向链表中的数据节点。
        // 为了便于在双向链表中添加和删除节点，上述代码创建了两个哨兵节点，即head和tail，它们分别位于双向链表的头部和尾部。
        // 函数put所添加的节点将位于这两个节点之间。
        // 接下来分别实现缓存LRUCache的两个成员函数get和put。
        // 当键在缓存中不存在时，函数get直接返回-1。
        // 如果缓存中包含该键，则在返回它对应的值之前先把它在双向链表中对应的节点移到链表的尾部，表示最近访问过该键【注意这里尾部表示最近使用的元素】。

        // 同样，在实现函数put时也要分为两种情形。
        // 当键已经存在时，在修改键对应的值的同时要把它在双向链表中的节点移到链表的尾部，表示最近访问过该键。
        // 如果之前该键在缓存中不存在，则分别在哈希表和双向链表中插入新的节点。
        // 在插入新的节点之前要记得检查缓存是否已经达到它的最大容量。如果已经达到最大容量，则先删除最近最少使用的元素。
        // 由于每次访问一个节点时都把当前访问的节点移到双向链表的尾部，因此双向链表的头节点对应的就是最近最少使用的键，可以把它删除。



        // 设计最近最少使用的缓存需要结合哈希表和双向链表的特点。
        // 双向链表中的每个节点保存缓存数据中一对键值对，也就是双向链表中的每一个节点实际上才是用来缓存键值对的容器。其实就是两个变量保存一组对应的数据值(key，value)。这里的成员变量key就是哈希表的键，也是缓存的键。
        // 缓存中包含一个哈希表，哈希表的键就是缓存数据的键，哈希表的值是该缓存数据在双向链表中对应的节点。
        // 由哈希表和双向链表是一一对应的。可以通过双向链表中节点的成员变量key获取哈希表的键。通过哈希表的键key可以获取双向链表中的数据节点。
        // 添加删除这些操作哈希表和双向链表要同步变化。
        // 在链表中移动节点不需要在哈希表中进行操作。在链表中添加和删除节点要在链表中进行操作。


        // 1.要是链表尾部表示最近使用的节点，那么链表头部就表示最近最少使用的节点。
        // 2.要是链表头部表示最近使用的节点，那么链表尾部就表示最近最少使用的节点。
        // 一旦访问一个元素，那么就要将该元素在双向链表中移动至最近使用的那一端。



        // 解法1：缓存是一个键值对形式的元素。(key-value)形式的。
        // 下面这个是双向链表的头部节点表示最近使用的节点。双向链表的尾部节点表示最近最少使用的节点。

        private Map<Integer, DLinkedNode> map;// 哈希表的键就是缓存的键，哈希表的值为双向链表中的节点。
        private DLinkedNode dummyHead;// 双向链表的头部哨兵节点
        private DLinkedNode dummyTail;// 双向链表的尾部哨兵节点
        private int capacity;// 缓存容量

        public LRUCache(int capacity){// 构造器 初始化缓存容器
            map=new HashMap<>();
            this.capacity=capacity;
            dummyHead=new DLinkedNode();
            dummyTail=new DLinkedNode();
            dummyTail.prev=dummyHead;
            dummyHead.next=dummyTail;
        }


        //// get（key）：如果缓存中存在键key，则返回它对应的值；否则返回-1。
        public int get(int key){
            DLinkedNode node=map.get(key);// 通过哈希表获得当前键key在双向链表中对应的节点
            if(node==null){// 缓存中没有当前键key对应的节点，则返回-1
                return -1;
            }
            // 执行到下面则缓存中有当前键key对应的节点。从双向链表中删除原节点，然后将该节点移动至双向链表的头部。
            // 头部表示最近使用的元素
            deleteNode(node);
            insertToHead(node);
            return node.value;
        }



        //// put（key，value）：如果缓存中之前包含键key，则它的值设为value；否则添加键key及对应的值value。
        //// 在添加一个键时，如果缓存容量已经满了，则在添加新键之前删除最近最少使用的键（缓存中最长时间没有被使用过的元素）。
        public void put(int key,int value){
            DLinkedNode node=map.get(key);// 通过哈希表获得当前键key在双向链表中对应的节点
            if(node!=null){
                // 当缓存中已经存在该键key对应的键值对,修改该键的值，删除原节点，然后将该节点移动至双向链表的头部
                node.value=value;
                deleteNode(node);
                insertToHead(node);
            }else {
                // 如果之前该键在缓存中不存在，则分别在哈希表和双向链表中插入新的节点。
                if(capacity==map.size()){
                    // 在插入新的节点之前要记得检查缓存是否已经达到它的最大容量。如果已经达到最大容量，则先删除最近最少使用的元素。
                    // 由于约定双向链表的头部是最近使用的键，所以双向链表的尾部是最近最少使用的键，可以把它删除。
                    DLinkedNode toBeDeleted=dummyTail.prev;// 这里使用一个临时指针，这样后续删除链表和哈希表的代码顺序可以调整
                    map.remove(toBeDeleted.key);
                    deleteNode(toBeDeleted);
                }
                // 创建该键值对对应的节点，并分别在哈希表和双向链表中插入新的节点。
                DLinkedNode insertNode =new DLinkedNode(key,value);// 创建双向链表的节点来缓存键值对
                map.put(key, insertNode);// 在哈希表中添加缓存的键和其在双向链表中对应的节点
                insertToHead(insertNode);// 在双向链表的头尾插入该节点，
            }
        }


        // 从双向链表中删除节点node
        private void deleteNode(DLinkedNode node){
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }


        // 将当前节点插入到双向链表的头部。注意这里是双向链表
        private void insertToHead(DLinkedNode node){
            // 因为这里没有使用临时变量保存头部哨兵节点的下一个节点，
            // 所以在没有断开连接之前，先用dummyHead.next表示原链表的第一个数据节点。
            dummyHead.next.prev=node;
            node.next=dummyHead.next;

            dummyHead.next=node;
            node.prev=dummyHead;
        }






        //// 解法2：缓存是一个键值对形式的元素。(key-value)形式的。
        //// 下面这个是双向链表的尾部节点表示最近使用的节点。双向链表的头部节点表示最近最少使用的节点。
        //
        //private Map<Integer,DLinkedNode> map;
        //private DLinkedNode dummyHead;
        //private DLinkedNode dummyTail;
        //private int capacity;
        //
        //public LRUCache(int capacity){
        //    map=new HashMap<>();
        //    this.capacity=capacity;
        //    dummyHead=new DLinkedNode();
        //    dummyTail=new DLinkedNode();
        //    dummyHead.next=dummyTail;
        //    dummyTail.prev=dummyHead;
        //}
        //
        //
        //public int get(int key){
        //    DLinkedNode node=map.get(key);
        //    if(node==null){
        //        return -1;
        //    }
        //    deleteNode(node);
        //    insertToTail(node);
        //    return node.value;
        //}
        //
        //
        //public void put(int key,int value){
        //    DLinkedNode node=map.get(key);
        //    if(node!=null){
        //        node.value=value;
        //        deleteNode(node);
        //        insertToTail(node);
        //    }else {
        //        if(capacity==map.size()){
        //            DLinkedNode toBeDeleted=dummyHead.next;
        //            deleteNode(toBeDeleted);
        //            map.remove(toBeDeleted.key);
        //        }
        //
        //        DLinkedNode insertNode=new DLinkedNode(key,value);
        //        map.put(key,insertNode);
        //        insertToTail(insertNode);
        //    }
        //
        //}
        //
        //
        //private void deleteNode(DLinkedNode node){
        //    node.prev.next=node.next;
        //    node.next.prev=node.prev;
        //}
        //
        //
        //private void insertToTail(DLinkedNode node){
        //    dummyTail.prev.next=node;
        //    node.prev=dummyTail.prev;
        //    dummyTail.prev=node;
        //    node.next=dummyTail;
        //}
        //



        // 双向链表的定义
        // 哈希表的键就是缓存的键，哈希表的值为双向链表中的节点。双向链表中的每个节点都是一组键与值的数对。
        class DLinkedNode {
            int key;// key就是缓存中的key
            int value;// 缓存中的值
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
