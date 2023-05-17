/**
 * <p>你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： <code>'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'</code> 。每个拨轮可以自由旋转：例如把
 * <code>'9'</code> 变为&nbsp;<code>'0'</code>，<code>'0'</code> 变为 <code>'9'</code> 。每次旋转都只能旋转一个拨轮的一位数字。</p>
 *
 * <p>锁的初始数字为 <code>'0000'</code> ，一个代表四个拨轮的数字的字符串。</p>
 *
 * <p>列表 <code>deadends</code> 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。</p>
 *
 * <p>字符串 <code>target</code> 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 <code>-1</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * <strong>输出：</strong>6
 * <strong>解释：</strong>
 * 可能的移动序列为 "0000" -&gt; "1000" -&gt; "1100" -&gt; "1200" -&gt; "1201" -&gt; "1202" -&gt; "0202"。
 * 注意 "0000" -&gt; "0001" -&gt; "0002" -&gt; "0102" -&gt; "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> deadends = ["8888"], target = "0009"
 * <strong>输出：</strong>1
 * <strong>解释：</strong>把最后一位反向旋转一次即可 "0000" -&gt; "0009"。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * <strong>输出：</strong>-1
 * <strong>解释：</strong>无法旋转到目标数字且不被锁定。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;=&nbsp;deadends.length &lt;= 500</code></li>
 * <li><code><font face="monospace">deadends[i].length == 4</font></code></li>
 * <li><code><font face="monospace">target.length == 4</font></code></li>
 * <li><code>target</code> <strong>不在</strong> <code>deadends</code> 之中</li>
 * <li><code>target</code> 和 <code>deadends[i]</code> 仅由若干位数字组成</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍
 * 576</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 752
 * 打开转盘锁
 * @author wangweizhou
 * @date 2022-12-12 18:40:56
 */

// 对比 127
public class OpenTheLock {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new OpenTheLock().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 一般而言，如果一个问题是关于某事物状态的改变，那么可以考虑把问题转换成图搜索的问题。
        // 事物的每个状态是图中的一个节点，如果一个状态能够转变到另一个状态，那么这两个状态对应的节点之间有一条边相连。

        // 由于题目要求的是找出节点"0000"到密码的对应节点的最短路径的长度，因此应该采用广度优先搜索。
        // 这是因为广度优先搜索是从起始节点开始首先达到所有距离为1的节点，接着到达所有距离为2节点。
		// 广度优先搜索一定是从起始节点沿着最短路径到达目标节点的。
        // 搜索密码锁对应的图时还要注意避开死锁状态对应的节点，因为一旦到达这些节点之后就不能继续向下搜索。

        // 用两个队列实现广度优先搜索。队列queue1中存放的是需要转动n次到达的节点，队列queue2中存放的是和队列queue1中的节点相连但是还没有搜索到的节点。
        // 当队列queue1中的节点都删除之后，接着遍历需要转动n+1次到达的节点，也就是队列queue2中的节点，此时变量steps加1。

        //// 127和752 类似可以一起看
        //public int openLock(String[] deadends, String target) {
        //    Set<String> dead = new HashSet<>(Arrays.asList(deadends));// 哈希表dead记录死锁的状态，哈希表O（1）查询
        //    Set<String> isVisited = new HashSet<>();// 哈希表isVisited存储已经访问过的字符串
        //    String init = "0000";
        //    if (dead.contains(init) || dead.contains(target)) {// 初始状态或者目标状态属于死锁状态,无法到达
        //        return -1;
        //    }
        //    Queue<String> queue1 = new LinkedList<>();
        //    Queue<String> queue2 = new LinkedList<>();
        //    int steps = 0;// 记录最短路径
        //    queue1.add(init);// 将起始状态添加到队列queue1中
        //    isVisited.add(init);// 将已经访问过的节点加入标记已经访问的哈希表中
        //    while (!queue1.isEmpty()) {
        //        String curr = queue1.remove();// 每一步从队列queue1中取出一个节点curr访问。
        //        if (curr.equals(target)) {// 如果curr就是目标节点，则搜索结束
        //            return steps;
        //        }
        //        List<String> neighbors = getNeighbors(curr);// 找出所有与curr相邻的节点
        //        for (String next : neighbors) {// 遍历所有相邻的节点，将相邻的节点中没有访问的并且不属于死锁状态的加入到队列2中
        //            if (!dead.contains(next) && !isVisited.contains(next)) {
        //                queue2.add(next);
        //                isVisited.add(next);
        //            }
        //        }
        //        // 每次交换队列queue1和queue2时都意味着距离初始节状态距离为d的节点都访问完毕，接下来将访问距离为d+1的节点，因此距离值增加1。
        //        if (queue1.isEmpty()) {
        //            steps++;
        //            queue1 = queue2;
        //            queue2 = new LinkedList<>();
        //        }
        //    }
        //    return -1;
        //}
        //
        //
        //
        //// 找出当前状态的下一个状态   这里是使用替换可变字符串中的字符实现的
        //// 和面试题127相比，这个题目要求获得与某一密码锁状态相连的状态的方法。可以向上或向下转动4个转轮中的任意一个转轮，因此1个状态与8个状态相连。
        //// 下面的函数getNeighbors用来得到与某个状态相连的8个状态：
        //private List<String> getNeighbors(String curr) {
        //    List<String> nexts = new LinkedList<>();
        //    for (int i = 0; i < curr.length(); i++) {// 注意这里每次修改四位密码中的一位，每一位可以上下转动，所以有8个状态
        //        char old = curr.charAt(i);// 获取4位密码中的某一位
        //        StringBuilder builder = new StringBuilder(curr);
        //        // 以原字符串新建一个可变字符串，并将该可变字符串的相应位置变换的字符更换为变换后的，
        //        // 然后再将变换后的可变字符串转换为新的字符串并将该字符串添加到相邻元素的集合
        //
        //        // 位置i的旋钮可以向两个方向旋转，一个方向是该位置的数字变大，另一个方向是该位置的数字变小
        //        char newCh = old == '0' ? '9' : (char) (old - 1);// 这里是将该位置旋钮向小数方向转
        //        builder.setCharAt(i, newCh);
        //        nexts.add(builder.toString());// 将新状态添加到下一个状态的集合nexts中
        //
        //        newCh = old == '9' ? '0' : (char) (old + 1);// 这里是将该位置旋钮向小数方向转
        //        builder.setCharAt(i, newCh);
        //        nexts.add(builder.toString());
        //    }
        //    return nexts;
        //}





        //
        //// 解法2：两个队列实现图的层序遍历
        //public int openLock(String[] deadends, String target) {
        //    Set<String> dead=new HashSet<>(Arrays.asList(deadends));
        //    Set<String> isVisited=new HashSet<>();
        //    String init="0000";
        //    if(dead.contains(init)||dead.contains(target)){
        //        return -1;
        //    }
        //    Queue<String> queue1=new LinkedList<>();
        //    Queue<String> queue2=new LinkedList<>();
        //    queue1.offer(init);
        //    isVisited.add(init);
        //    int step=0;
        //    while (!queue1.isEmpty()){
        //        String curr=queue1.poll();
        //        if(curr.equals(target)){
        //            return step;
        //        }
        //        List<String> neighbors=getNeighbors(curr);
        //        for(String neighbor:neighbors){
        //            if(!dead.contains(neighbor)&&!isVisited.contains(neighbor)){
        //                queue2.offer(neighbor);
        //                isVisited.add(neighbor);
        //            }
        //        }
        //        if(queue1.isEmpty()){
        //            queue1=queue2;
        //            queue2=new LinkedList<>();
        //            step++;
        //        }
        //    }
        //    return -1;
        //}
        //
        //
        //// 找出当前状态的下一个状态   这里是使用替换可变字符串中的字符实现的
        //// 和面试题127相比，这个题目要求获得与某一密码锁状态相连的状态的方法。可以向上或向下转动4个转轮中的任意一个转轮，因此1个状态与8个状态相连。
        //// 下面的函数getNeighbors用来得到与某个状态相连的8个状态：
        //private List<String> getNeighbors(String str){
        //    List<String> neighbors=new ArrayList<>();
        //    char[] charArr=str.toCharArray();
        //    for (int i = 0; i < charArr.length; i++) {
        //        char old=charArr[i];
        //        char newCh=old=='0'?'9':(char)(old-1);
        //        charArr[i]= newCh;
        //        neighbors.add(new String(charArr));
        //        charArr[i]= old;
        //        newCh=old=='9'?'0':(char)(old+1);
        //        charArr[i]= newCh;
        //        neighbors.add(new String(charArr));
        //        charArr[i]= old;
        //    }
        //    return neighbors;
        //}



        public int openLock(String[] deadends, String target) {
            Set<String> dead=new HashSet<>(Arrays.asList(deadends));
            Set<String> isVisited=new HashSet<>();
            Queue<String> queue=new LinkedList<>();
            if(dead.contains("0000")||dead.contains(target)){
                return -1;
            }
            queue.offer("0000");
            int len=0;
            while (queue.size()>0){
                int levelSize=queue.size();
                for (int i = 0; i < levelSize; i++) {
                    String curr=queue.poll();
                    if(curr.equals(target)){
                        return len;
                    }
                    List<String> lists=getNext(curr);
                    for (int j = 0; j < lists.size(); j++) {
                        if(!isVisited.contains(lists.get(j))&&!dead.contains(lists.get(j))){
                            queue.add(lists.get(j));
                            isVisited.add(lists.get(j));
                        }
                    }
                }
                len++;
            }
            return -1;
        }



        private List<String> getNext(String str){
            List<String> lists=new ArrayList<>();
            char[] strCh=str.toCharArray();
            for (int i = 0; i < str.length(); i++) {

                char old=strCh[i];
                char newCh=old=='0'?'9':(char)(old-1);
                strCh[i]=newCh;
                lists.add(new String(strCh));

                newCh=old=='9'?'0':(char)(old+1);
                strCh[i]=newCh;
                lists.add(new String(strCh));
                strCh[i]=old;
            }
            return lists;
        }




    }
//leetcode submit region end(Prohibit modification and deletion)

}
