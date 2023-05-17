/**
 * 存在一个 <strong>无向图</strong> ，图中有 <code>n</code> 个节点。其中每个节点都有一个介于 <code>0</code> 到 <code>n - 1</code>
 * 之间的唯一编号。给你一个二维数组 <code>graph</code> ，其中 <code>graph[u]</code> 是一个节点数组，由节点 <code>u</code> 的邻接节点组成。形式上，对于
 * <code>graph[u]</code> 中的每个 <code>v</code> ，都存在一条位于节点 <code>u</code> 和节点 <code>v</code> 之间的无向边。该无向图同时具有以下属性：
 *
 * <ul>
 * <li>不存在自环（<code>graph[u]</code> 不包含 <code>u</code>）。</li>
 * <li>不存在平行边（<code>graph[u]</code> 不包含重复值）。</li>
 * <li>如果 <code>v</code> 在 <code>graph[u]</code> 内，那么 <code>u</code> 也应该在 <code>graph[v]</code> 内（该图是无向图）</li>
 * <li>这个图可能不是连通图，也就是说两个节点 <code>u</code> 和 <code>v</code> 之间可能不存在一条连通彼此的路径。</li>
 * </ul>
 *
 * <p><strong>二分图</strong> 定义：如果能将一个图的节点集合分割成两个独立的子集 <code>A</code> 和 <code>B</code> ，并使图中的每一条边的两个节点一个来自
 * <code>A</code> 集合，一个来自 <code>B</code> 集合，就将这个图称为 <strong>二分图</strong> 。</p>
 *
 * <p>如果图是二分图，返回 <code>true</code><em> </em>；否则，返回 <code>false</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/21/bi2.jpg" style="width: 222px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * <strong>输出：</strong>false
 * <strong>解释：</strong><span><code>不能将节点分割成两个独立的子集，</code></span>以使每条边都连通一个子集中的一个节点与另一个子集中的一个节点。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/21/bi1.jpg" style="width: 222px; height: 222px;" />
 * <pre>
 * <strong>输入：</strong>graph = [[1,3],[0,2],[1,3],[0,2]]
 * <strong>输出：</strong>true
 * <strong>解释：</strong><span><code>可以将节点分成两组: {0, 2} 和 {1, 3} 。</code></span></pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>graph.length == n</code></li>
 * <li><code>1 &lt;= n &lt;= 100</code></li>
 * <li><code>0 &lt;= graph[u].length &lt; n</code></li>
 * <li><code>0 &lt;= graph[u][i] &lt;= n - 1</code></li>
 * <li><code>graph[u]</code> 不会包含 <code>u</code></li>
 * <li><code>graph[u]</code> 的所有值 <strong>互不相同</strong></li>
 * <li>如果 <code>graph[u]</code> 包含 <code>v</code>，那么 <code>graph[v]</code> 也会包含 <code>u</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>图</li></div></div><br><div><li>👍
 * 428</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 785
 * 判断二分图
 *
 * @author wangweizhou
 * @date 2022-12-12 10:48:20
 */
public class IsGraphBipartite {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new IsGraphBipartite().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        //// 二分图的节点可以分成两种不同的类型，任意一条边的两个节点分别属于两种不同的类型。
        //// 可以为图中的所有节点着色，两种不同类型的节点分别涂上不同的颜色。如果任意一条边的两个节点都能被涂上不同的颜色，那么整个图就是一个二分图。
        //// 如果节点i还没有被着色，那么colors[i]的值为-1；如果节点i已经被着色，那么colors[i]的值为0或1。
        //// 本题用一个二维数组graph表示图，graph实际上是图的邻接表，与节点i相邻的节点保存在graph[i]中。

        // 解法1：染色法
        public boolean isBipartite(int[][] graph) {
            if (graph == null || graph.length == 0 ) {
                return false;
            }
            int size = graph.length;// 节点个数
            int[] colors = new int[size];// 创建了一个长度为size的数组colors记录每个节点的颜色，节点i的颜色保存在colors[i]中。
            Arrays.fill(colors, -1);// 如果节点i还没有被着色，那么colors[i]的值为-1；
            for (int i = 0; i < size; i++) {// 循环遍历无向图中的每一个节点
                if (colors[i] == -1) {// 如果当前节点没有染色，
                    if (!setColor(graph, colors, i, 0)) {
                        return false;
                    }
                }
            }
            return true;
        }


        // 解法1：可以用广度优先搜索算法搜索与节点i连通的所有节点。
        // 函数setColor用来对以节点i为起始节点的一个连通子图着色，它的返回值用来表示能否按照二分图的规则对子图的所有节点进行着色。
        // 为了能够给所有节点着色，需要搜索所有与节点i连通的节点，每搜索到一个尚未着色的节点就按照二分图的规则给它涂上颜色。

        // 可以用广度优先搜索算法搜索与节点i连通的所有节点。广度优先搜索需要一个队列，先将起始节点i添加到队列中。
        // 接下来每次从队列中取出一个节点，如果与该节点相邻的节点之前没有访问过，那么相邻的节点被添加到队列中。
        // 本题用一个二维数组graph表示图，graph实际上是图的邻接表，与节点i相邻的节点保存在graph[i]中。
        // 重复这个过程，直到队列为空，此时与起始节点i连通的所有节点已经搜索完毕。

        // 每次从队列中取出一个节点v，该节点在添加到队列的时候已经被涂上颜色，它的颜色保存在colors[v]中。
        // 如果相邻的节点还没有着色（颜色值等于-1），就按照二分图的着色规律给相邻的节点涂上不同的颜色，即“1-colors[v]”。
        // 如果相邻的节点已经被涂上颜色，则判断它是否与节点v的颜色相同。如果节点v的颜色与它相邻的节点的颜色相同，那么违背了二分图的要求，因此返回false。

        // 如果节点i已经被着色，那么colors[i]的值为0或1。
        // 第三个参数i表示数组colors中的第i个元素【其实就是第几个节点】，第四个参数color表示数组colors[i]将要染的颜色。

        private boolean setColor(int[][] graph, int[] colors, int i, int color) {
            Queue<Integer> queue = new LinkedList<>();// 广度搜索使用队列，队列中保存没有访问过的节点
            queue.add(i);// 先将起始节点i添加到队列中
            colors[i] = color;// 给当前节点染色
            while (!queue.isEmpty()) {
                int curr = queue.remove();// 每次从队列中取出一个节点
                for (int neighbor : graph[curr]) {// 遍历当前节点的邻接节点
                    // 当前节点的邻接节点可能已经染色或者还没有染色
                    if (colors[neighbor] >= 0) {// 若当前节点的邻接节点已经染色
                        // 当邻接节点已经染色后，需要判断同一边两端的节点颜色是否相同。即判断当前节点和相邻节点的颜色是否相同
                        if (colors[neighbor] == colors[curr]) {// 即如果当前节点和相邻节点的颜色相同，则不符合二分图
                            return false;
                        }
                    } else {// 如果当前节点的相邻节点之前没有访问过【当前节点的相邻节点还没有染色】，那么相邻的节点被添加到队列中，并同时给相邻节点染色。
                        queue.add(neighbor);// 邻接节点入队
                        colors[neighbor] = 1 - colors[curr];// 将当前节点的邻接节点染成与当前节点不同的颜色
                    }
                }
            }
            return true;
        }





        //// 解法2：染色法 写法2   没访问过的节点颜色是0，访问过的节点颜色是1或者2。
        //public boolean isBipartite(int[][] graph) {
        //    if(graph==null||graph.length==0){
        //        return false;
        //    }
        //    int size=graph.length;
        //    int[] colors=new int[size];
        //    for (int i = 0; i < size; i++) {
        //        if(colors[i]==0){
        //            if(!setColor(graph,colors,i,1)) {
        //                return false;
        //            }
        //        }
        //    }
        //    return true;
        //}
        //
        //
        //
        //private boolean setColor(int[][] graph,int[] colors,int i,int color){
        //    Queue<Integer> queue=new LinkedList<>();
        //    queue.offer(i);
        //    colors[i]=color;
        //    while (queue.size()>0){
        //        int curr=queue.poll();
        //        for(int neighbor:graph[curr]){
        //            if(colors[neighbor]!=0){
        //                if(colors[curr]==colors[neighbor]){
        //                    return false;
        //                }
        //            }else {
        //                queue.offer(neighbor);
        //                colors[neighbor]=colors[curr]==1?2:1;
        //            }
        //        }
        //    }
        //    return true;
        //}





        //// 解法2：利用栈实现深度搜索
        //private boolean setColor(int[][] graph,int[] colors,int i,int color){
        //	Deque<Integer> stack =new LinkedList<>();
        //	stack.add(i);
        //	colors[i]=color;
        //	while(!stack.isEmpty()){
        //		int curr= stack.remove();
        //		for (int neighbor:graph[curr]) {
        //			if(colors[neighbor]>=0){
        //				if(colors[neighbor]==colors[curr]){
        //					return false;
        //				}
        //			}else {
        //				stack.add(neighbor);
        //				colors[neighbor]=1-colors[curr];
        //			}
        //		}
        //	}
        //	return true;
        //}



        // 用深度优先搜索来搜索图中的所有节点并进行着色。深度优先搜索可以用递归代码实现。函数setColor将节点i的颜色设为color。
        // 如果该节点在此之前已经着色，并且它的颜色不是color，那么意味着不能按照二分图的规则对图中的节点进行着色，直接返回false。
        // 如果此时节点i还没有着色，则将它的颜色设为color，然后给与它相邻的节点涂上颜色1-color。给相邻的节点着色与给节点i着色是相同的问题，可以递归调用函数setColor解决。

        //private boolean setColor(int[][] graph,int[] colors,int i,int color){
        //	if(colors[i]>=0){
        //		return colors[i]==color;// 如果该节点在此之前已经着色，并且它的颜色不是color，那么意味着不能按照二分图的规则对图中的节点进行着色，直接返回false。
        //	}
        //	// 如果此时节点i还没有着色，则将它的颜色设为color，
        //	colors[i]=color;
        //	for(int neighbor:graph[i]){// 遍历节点i的相邻节点，
        //		// 然后给与它相邻的节点涂上颜色1-color
        //		if(!setColor(graph,colors,neighbor,1-color)){
        //			return false;
        //		}
        //	}
        //	return true;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
