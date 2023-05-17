/**
 * <p>请你为 <a href="https://baike.baidu.com/item/%E7%BC%93%E5%AD%98%E7%AE%97%E6%B3%95">最不经常使用（LFU）</a>缓存算法设计并实现数据结构。</p>
 *
 * <p>实现 <code>LFUCache</code> 类：</p>
 *
 * <ul>
 * <li><code>LFUCache(int capacity)</code> - 用数据结构的容量&nbsp;<code>capacity</code> 初始化对象</li>
 * <li><code>int get(int key)</code>&nbsp;- 如果键&nbsp;<code>key</code> 存在于缓存中，则获取键的值，否则返回 <code>-1</code> 。</li>
 * <li><code>void put(int key, int value)</code>&nbsp;- 如果键&nbsp;<code>key</code>
 * 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量&nbsp;<code>capacity</code>
 * 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 <strong>最近最久未使用</strong> 的键。</li>
 * </ul>
 *
 * <p>为了确定最不常使用的键，可以为缓存中的每个键维护一个 <strong>使用计数器</strong> 。使用计数最小的键是最久未使用的键。</p>
 *
 * <p>当一个键首次插入到缓存中时，它的使用计数器被设置为 <code>1</code> (由于 put 操作)。对缓存中的键执行 <code>get</code> 或 <code>put</code>
 * 操作，使用计数器的值将会递增。</p>
 *
 * <p>函数 <code>get</code> 和 <code>put</code> 必须以 <code>O(1)</code> 的平均时间复杂度运行。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * <strong>输出：</strong>
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * <strong>解释：</strong>
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // 返回 1
 * // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 * // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 * // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 * // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 * // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // 返回 4
 * // cache=[3,4], cnt(4)=2, cnt(3)=3</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= capacity&nbsp;&lt;= 10<sup>4</sup></code></li>
 * <li><code>0 &lt;= key &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= value &lt;= 10<sup>9</sup></code></li>
 * <li>最多调用 <code>2 * 10<sup>5</sup></code> 次 <code>get</code> 和 <code>put</code> 方法</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>哈希表</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍
 * 587</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;


/**
 * 460
 * LFU 缓存
 * @author wangweizhou
 * @date 2022-08-27 22:48:55
 */

public class LfuCache {
    public static void main(String[] args) {
        //测试代码
        //LFUCache solution = new LfuCache().new LFUCache(10);


    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LFUCache {


/*

	// 方法一：哈希表 + 平衡二叉树
	// 缓存容量，时间戳
	int capacity, time;
	Map<Integer, Node> key_table;
	TreeSet<Node> S;

	public LFUCache(int capacity) {
		this.capacity = capacity;
		this.time = 0;
		key_table = new HashMap<Integer, Node>();
		S = new TreeSet<Node>();
	}

	public int get(int key) {
		if (capacity == 0) {
			return -1;
		}
		// 如果哈希表中没有键 key，返回 -1
		if (!key_table.containsKey(key)) {
			return -1;
		}
		// 从哈希表中得到旧的缓存
		Node cache = key_table.get(key);
		// 从平衡二叉树中删除旧的缓存
		S.remove(cache);
		// 将旧缓存更新
		cache.cnt += 1;
		cache.time = ++time;
		// 将新缓存重新放入哈希表和平衡二叉树中
		S.add(cache);
		key_table.put(key, cache);
		return cache.value;
	}

	public void put(int key, int value) {
		if (capacity == 0) {
			return;
		}
		if (!key_table.containsKey(key)) {
			// 如果到达缓存容量上限
			if (key_table.size() == capacity) {
				// 从哈希表和平衡二叉树中删除最近最少使用的缓存
				key_table.remove(S.first().key);
				S.remove(S.first());
			}
			// 创建新的缓存
			Node cache = new Node(1, ++time, key, value);
			// 将新缓存放入哈希表和平衡二叉树中
			key_table.put(key, cache);
			S.add(cache);
		} else {
			// 这里和 get() 函数类似
			Node cache = key_table.get(key);
			S.remove(cache);
			cache.cnt += 1;
			cache.time = ++time;
			cache.value = value;
			S.add(cache);
			key_table.put(key, cache);
		}
	}
}

	class Node implements Comparable<Node> {
		int cnt, time, key, value;

		Node(int cnt, int time, int key, int value) {
			this.cnt = cnt;
			this.time = time;
			this.key = key;
			this.value = value;
		}

		public boolean equals(Object anObject) {
			if (this == anObject) {
				return true;
			}
			if (anObject instanceof Node) {
				Node rhs = (Node) anObject;
				return this.cnt == rhs.cnt && this.time == rhs.time;
			}
			return false;
		}

		public int compareTo(Node rhs) {
			return cnt == rhs.cnt ? time - rhs.time : cnt - rhs.cnt;
		}

		public int hashCode() {
			return cnt * 1000000007 + time;
		}

*/




        // 方法二：双哈希表

        // 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
        // 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。

        int minfreq, capacity;
        Map<Integer, Node> keyTable;
        Map<Integer, DoublyLinkedList> freqTable;


        // LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
        public LFUCache(int capacity) {
            this.minfreq = 0;
            this.capacity = capacity;
            keyTable = new HashMap<Integer, Node>();
            freqTable = new HashMap<Integer, DoublyLinkedList>();
        }


        // int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
        public int get(int key) {
            if (capacity == 0) {
                return -1;
            }
            if (!keyTable.containsKey(key)) {
                return -1;
            }
            Node node = keyTable.get(key);
            int val = node.val, freq = node.freq;
            freqTable.get(freq).remove(node);
            // 如果当前链表为空，我们需要在哈希表中删除，且更新minFreq
            if (freqTable.get(freq).size == 0) {
                freqTable.remove(freq);
                if (minfreq == freq) {
                    minfreq += 1;
                }
            }
            // 插入到 freq + 1 中
            DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
            list.addFirst(new Node(key, val, freq + 1));
            freqTable.put(freq + 1, list);
            keyTable.put(key, freqTable.get(freq + 1).getHead());
            return val;
        }



        // void put(int key, int value)- 如果键key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量capacity时，则应该在插入新项之前，移除最不经常使用的项。
        // 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。

        public void put(int key, int value) {
            if (capacity == 0) {
                return;
            }
            if (!keyTable.containsKey(key)) {
                // 缓存已满，需要进行删除操作
                if (keyTable.size() == capacity) {
                    // 通过 minFreq 拿到 freqTable[minFreq] 链表的末尾节点
                    Node node = freqTable.get(minfreq).getTail();
                    keyTable.remove(node.key);
                    freqTable.get(minfreq).remove(node);
                    if (freqTable.get(minfreq).size == 0) {
                        freqTable.remove(minfreq);
                    }
                }
                DoublyLinkedList list = freqTable.getOrDefault(1, new DoublyLinkedList());
                list.addFirst(new Node(key, value, 1));
                freqTable.put(1, list);
                keyTable.put(key, freqTable.get(1).getHead());
                minfreq = 1;
            } else {
                // 与 get 操作基本一致，除了需要更新缓存的值
                Node node = keyTable.get(key);
                int freq = node.freq;
                freqTable.get(freq).remove(node);
                if (freqTable.get(freq).size == 0) {
                    freqTable.remove(freq);
                    if (minfreq == freq) {
                        minfreq += 1;
                    }
                }
                DoublyLinkedList list = freqTable.getOrDefault(freq + 1, new DoublyLinkedList());
                list.addFirst(new Node(key, value, freq + 1));
                freqTable.put(freq + 1, list);
                keyTable.put(key, freqTable.get(freq + 1).getHead());
            }
        }
    }


    class Node {
        int key, val, freq;
        Node prev, next;

        Node() {
            this(-1, -1, 0);
        }

        Node(int key, int val, int freq) {
            this.key = key;
            this.val = val;
            this.freq = freq;
        }
    }

    class DoublyLinkedList {
        Node dummyHead, dummyTail;
        int size;

        DoublyLinkedList() {
            dummyHead = new Node();
            dummyTail = new Node();
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
            size = 0;
        }

        public void addFirst(Node node) {
            Node prevHead = dummyHead.next;
            node.prev = dummyHead;
            dummyHead.next = node;
            node.next = prevHead;
            prevHead.prev = node;
            size++;
        }

        public void remove(Node node) {
            Node prev = node.prev, next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        public Node getHead() {
            return dummyHead.next;
        }

        public Node getTail() {
            return dummyTail.prev;
        }


    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)

}
