/**
 * <p>给你一个有&nbsp;<code>n</code>&nbsp;个节点的 <strong>有向无环图（DAG）</strong>，请你找出所有从节点 <code>0</code>&nbsp;到节点
 * <code>n-1</code>&nbsp;的路径并输出（<strong>不要求按特定顺序</strong>）</p>
 *
 * <p>
 * <meta charset="UTF-8" />&nbsp;<code>graph[i]</code>&nbsp;是一个从节点 <code>i</code> 可以访问的所有节点的列表（即从节点 <code>i</code>
 * 到节点&nbsp;<code>graph[i][j]</code>存在一条有向边）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2020/09/28/all_1.jpg" /></p>
 *
 * <pre>
 * <strong>输入：</strong>graph = [[1,2],[3],[3],[]]
 * <strong>输出：</strong>[[0,1,3],[0,2,3]]
 * <strong>解释：</strong>有两条路径 0 -&gt; 1 -&gt; 3 和 0 -&gt; 2 -&gt; 3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2020/09/28/all_2.jpg" /></p>
 *
 * <pre>
 * <strong>输入：</strong>graph = [[4,3,1],[3,2,4],[3],[4],[]]
 * <strong>输出：</strong>[[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>n == graph.length</code></li>
 * <li><code>2 &lt;= n &lt;= 15</code></li>
 * <li><code>0 &lt;= graph[i][j] &lt; n</code></li>
 * <li><code>graph[i][j] != i</code>（即不存在自环）</li>
 * <li><code>graph[i]</code> 中的所有元素 <strong>互不相同</strong></li>
 * <li>保证输入为 <strong>有向无环图（DAG）</strong></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li><li>回溯</li></div></div><br><div><li>👍
 * 357</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 797
 * 所有可能的路径
 *
 * @author wangweizhou
 * @date 2022-12-12 18:41:12
 */
public class AllPathsFromSourceToTarget {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new AllPathsFromSourceToTarget().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 这个题目要求找出有向无环图中从节点0到节点n-1的所有路径，自然需要搜索图中的所有节点。
        // 通常可以用广度优先搜索或深度优先搜索完成图的搜索。由于这个题目要求列出从节点0到节点n-1的所有路径，因此深度优先搜索是更合适的选择。

        // 深度优先搜索通常用递归实现。从节点0出发开始搜索。每当搜索到节点i时，先将该节点添加到路径中去。
        // 如果该节点正好是节点n-1，那么就找到了一条从节点0到节点n-1的路径。
        // 如果不是，则从graph[i]找到每个相邻的节点并用同样的方法进行搜索。
        // 当从节点i出发能够抵达的所有节点都搜索完毕之后，将回到前一个节点搜索其他与之相邻的节点。在回到前一个节点之前，需要将节点i从路径中删除。
        // 代码中没有判断一个节点是否已经访问过。在做图搜索的时候通常需要判断一个节点是否已经访问过，这样可以避免反复访问环中的节点。
        // 由于这个题目已经明确图是一个有向无环图，因此没有必要担心重复访问环中的节点。
        // 代码和实现回溯法的代码很相像，这是因为回溯法从本质上来说就是深度优先搜索。

        // graph是用二维数组表示的邻接表,图用一个数组graph表示，数组的graph[i]包含所有从节点i能直接到达的节点。
        public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
            List<List<Integer>> lists = new LinkedList<>();
            if (graph == null || graph.length == 0) {
                return lists;
            }
            List<Integer> path = new LinkedList<>();
            dfs(graph, 0, path, lists);
            return lists;
        }



        // 代码中的path记录当前路径中的所有节点，lists记录所有已经找到的路径。
        // source是递归开始的节点
        private void dfs(int[][] graph, int index, List<Integer> path, List<List<Integer>> lists) {
            path.add(index);// 处理当前节点
            if (index == graph.length - 1) {// 遍历到了最后一个节点n-1，则将当前路径加入到保存路径的集合中
                lists.add(new LinkedList<>(path));
            } else {// 当没有遍历到最后一个节点，则递归遍历与index相连的节点graph[index]
                // 处理子节点
                for (int next : graph[index]) {
                    dfs(graph, next, path, lists);
                }
            }
            //回溯，当从节点i出发能够抵达的所有节点都搜索完毕之后，将回到前一个节点搜索其他与之相邻的节点。
            // 在回到前一个节点之前，需要将节点i从路径中删除。
            path.remove(path.size() - 1);
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
