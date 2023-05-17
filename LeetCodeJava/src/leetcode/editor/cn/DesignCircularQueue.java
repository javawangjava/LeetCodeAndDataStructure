/**
 * <p>设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为&ldquo;环形缓冲器&rdquo;。</p>
 *
 * <p>循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。</p>
 *
 * <p>你的实现应该支持如下操作：</p>
 *
 * <ul>
 * <li><code>MyCircularQueue(k)</code>: 构造器，设置队列长度为 k 。</li>
 * <li><code>Front</code>: 从队首获取元素。如果队列为空，返回 -1 。</li>
 * <li><code>Rear</code>: 获取队尾元素。如果队列为空，返回 -1 。</li>
 * <li><code>enQueue(value)</code>: 向循环队列插入一个元素。如果成功插入则返回真。</li>
 * <li><code>deQueue()</code>: 从循环队列中删除一个元素。如果成功删除则返回真。</li>
 * <li><code>isEmpty()</code>: 检查循环队列是否为空。</li>
 * <li><code>isFull()</code>: 检查循环队列是否已满。</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3
 * circularQueue.enQueue(1); &nbsp;// 返回 true
 * circularQueue.enQueue(2); &nbsp;// 返回 true
 * circularQueue.enQueue(3); &nbsp;// 返回 true
 * circularQueue.enQueue(4); &nbsp;// 返回 false，队列已满
 * circularQueue.Rear(); &nbsp;// 返回 3
 * circularQueue.isFull(); &nbsp;// 返回 true
 * circularQueue.deQueue(); &nbsp;// 返回 true
 * circularQueue.enQueue(4); &nbsp;// 返回 true
 * circularQueue.Rear(); &nbsp;// 返回 4</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>所有的值都在 0&nbsp;至 1000 的范围内；</li>
 * <li>操作数将在 1 至 1000 的范围内；</li>
 * <li>请不要使用内置的队列库。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>队列</li><li>数组</li><li>链表</li></div></div><br><div><li>👍
 * 297</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


/**
 * 622
 * 设计循环队列
 * @author wangweizhou
 * @date 2022-07-16 15:02:24
 */

public class DesignCircularQueue {
    public static void main(String[] args) {
        //测试代码
        //Solution solution = new DesignCircularQueue().new Solution();

/*
        MyCircularQueue circleArrayQueue = new DesignCircularQueue().new MyCircularQueue(3);
        circleArrayQueue.enQueue(1);
        circleArrayQueue.enQueue(2);
        circleArrayQueue.enQueue(3);
        circleArrayQueue.showQueue();
        System.out.println("===================================");
        System.out.println(circleArrayQueue.enQueue(4));
        System.out.println(circleArrayQueue.Rear());
        System.out.println("===================================");
        circleArrayQueue.showQueue();
        System.out.println(circleArrayQueue.isFull());
        System.out.println("===================================");
        System.out.println(circleArrayQueue.deQueue());
        System.out.println(circleArrayQueue.enQueue(4));
        System.out.println("===================================");
        System.out.println(circleArrayQueue.Rear());
        circleArrayQueue.showQueue();
        */
    }



    //leetcode submit region begin(Prohibit modification and deletion)
    class MyCircularQueue {

        // 方法一：数组
        // 注意细节，自己举例子很快记住下面的细节差异
        // 环形队尾：tailIndex=(headIndex+count−1)mod capacity，这种是和计数器count先加1后配合使用的
        // tailIndex=(headIndex+count)mod capacity，这种是和计数器count配合使用后再给计数器加1；


        private int capacity=0;// 数组长度
        private int count=0;// 队列长度
        private int front = 0;// 队首指针
        private int rear = 0;// 队尾指针
        private int[] arr;

        //构造器
        public MyCircularQueue(int k) {
            capacity = k;
            arr = new int[capacity];
        }

        public boolean enQueue(int value) {
            if(count==capacity){
                return false;
            }
            // 注意这里是count先自加1，然后再和处理rear=front+count-1。
            // 如果是rear=front+count；那么count++在后面
            count++;
            rear=(front+count-1)%capacity;
            arr[rear]=value;
            return true;
        }

        public boolean deQueue() {
            if(count==0){
                return false;
            }
            // count自减1与front这句没有先后顺序
            count--;
            front=(front+1)%capacity;
            return true;
        }

        public int Front() {
            if(count==0){
                return -1;
            }
            return arr[front];
        }

        public int Rear() {
            if(count==0){
                return -1;
            }
            rear=(front+count-1)%capacity;
            return arr[rear];
        }

        public boolean isEmpty() {
            return count==0;
        }

        public boolean isFull() {
            return count==capacity;
        }

        //    遍历队列有效元素，
        public void showQueue() {
            //    判断队列是否为空
            if (count==0) {
                return;
            }
            //从front开始，
            for (int i = front; i < front + count; i++) {
                System.out.printf("arr[%d]=%d\n", i % capacity, arr[i % capacity]);
            }
        }

    }

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
