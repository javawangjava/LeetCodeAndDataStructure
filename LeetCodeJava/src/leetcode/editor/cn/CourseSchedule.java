/**
 * <p>你这个学期必须选修 <code>numCourses</code> 门课程，记为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>numCourses - 1</code> 。</p>
 *
 * <p>在选修某些课程之前需要一些先修课程。 先修课程按数组&nbsp;<code>prerequisites</code> 给出，其中&nbsp;<code>prerequisites[i] = [a<sub>i</sub>,
 * b<sub>i</sub>]</code> ，表示如果要学习课程&nbsp;<code>a<sub>i</sub></code> 则 <strong>必须</strong> 先学习课程&nbsp;
 * <code>b<sub>i</sub></code><sub> </sub>。</p>
 *
 * <ul>
 * <li>例如，先修课程对&nbsp;<code>[0, 1]</code> 表示：想要学习课程 <code>0</code> ，你需要先完成课程 <code>1</code> 。</li>
 * </ul>
 *
 * <p>请你判断是否可能完成所有课程的学习？如果可以，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numCourses = 2, prerequisites = [[1,0]]
 * <strong>输出：</strong>true
 * <strong>解释：</strong>总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numCourses = 2, prerequisites = [[1,0],[0,1]]
 * <strong>输出：</strong>false
 * <strong>解释：</strong>总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= numCourses &lt;= 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= prerequisites.length &lt;= 5000</code></li>
 * <li><code>prerequisites[i].length == 2</code></li>
 * <li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
 * <li><code>prerequisites[i]</code> 中的所有课程对 <strong>互不相同</strong></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li><li>拓扑排序</li></div></div><br><div><li
 * >👍 1492</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 207
 * 课程表
 *
 * @author wangweizhou
 * @date 2023-01-29 16:41:06
 */

public class CourseSchedule {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new CourseSchedule().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：本题可简化为： 课程安排图是否是有向无环图。 可以参考210
        // 若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序。
        // 换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 0。
        // 因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。


        // 没有图那么就建图，没有图中每个节点的邻接表，那么就遍历图来建立每个节点的入度表。
        // 首先遍历数组建图，统计课程安排图中每个节点的入度，生成 入度表 indegrees。然后再拓扑排序。
        // 借助一个队列 queue，将所有入度为 0 的节点入队。对图进行拓扑排序。
        // 当 queue 非空时，依次将队首节点出队，在课程安排图中删除此节点 pre；
        // 并不是真正从邻接表中删除此节点 pre，而是将此节点对应所有邻接节点 cur 的入度 −1，即 indegrees[cur] -= 1。
        // 当入度 -1 后邻接节点 cur 的入度为 0，说明 cur 所有的前驱节点已经被 “删除”，此时将 cur 入队。
        // 在每次 pre 出队时，执行 numCourses--；【每次从队列中出队一个元素，那么就表明该元素已经被拿去排序了】
        // 若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序。换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 0。
        // 因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。



        // 这里使用哈希表来建立每一个节点邻接表和入度表
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
                return true;
            }
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            Map<Integer, Integer> inDegrees = new HashMap<>();
            int count = 0;
            for (int i = 0; i < numCourses; i++) {
                graph.put(i, new HashSet<>());
                inDegrees.put(i, 0);
            }
            for (int[] pre : prerequisites) {
                graph.get(pre[1]).add(pre[0]);
                inDegrees.put(pre[0], inDegrees.get(pre[0]) + 1);
            }
            Deque<Integer> queue = new LinkedList<>();
            for (Integer degree : inDegrees.keySet()) {
                if (inDegrees.get(degree) == 0) {
                    queue.offer(degree);
                }
            }
            while (!queue.isEmpty()) {
                int curr = queue.poll();
                count++;
                Set<Integer> nexts = graph.get(curr);
                for (Integer next : nexts) {
                    inDegrees.put(next, inDegrees.get(next) - 1);
                    if (inDegrees.get(next) == 0) {
                        queue.offer(next);
                    }
                }
            }
            return count == numCourses;
        }




        //public boolean canFinish(int numCourses, int[][] prerequisites) {
        //    if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
        //        return true;
        //    }
        //    Map<Integer, List<Integer>> map = new HashMap<>();
        //    int[] inDegrees = new int[numCourses];
        //    Queue<Integer> queue = new LinkedList<>();
        //    for (int i = 0; i < numCourses; i++) {
        //        map.put(i, new ArrayList<>());
        //    }
        //    for (int[] pre : prerequisites) {
        //        map.get(pre[1]).add(pre[0]);
        //        inDegrees[pre[0]]++;
        //    }
        //
        //    for (int i = 0; i < inDegrees.length; i++) {
        //        if (inDegrees[i] == 0) {
        //            queue.offer(i);
        //        }
        //    }
        //
        //    while (queue.size() > 0) {
        //        int course = queue.remove();
        //        numCourses--;
        //        for (int next : map.get(course)) {
        //            inDegrees[next]--;
        //            if (inDegrees[next] == 0) {
        //                queue.offer(next);
        //            }
        //        }
        //    }
        //    return numCourses == 0;
        //}




        //public boolean canFinish(int numCourses, int[][] prerequisites) {
        //    if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {// 判空
        //    注意这个和210的判空不一样
        //        return true;
        //    }
        //    // 根据先修顺序构建出有向图graph，graph用一个HashMap表示邻接表，它的键key是先修课程，它的值value是必须在键key对应的课程之后学习的所有课程。
        //    Map<Integer, List<Integer>> graph = new HashMap<>();
        //    int[] inDegrees = new int[numCourses];// 将每个节点的入度保存到数组inDegrees中，“inDegrees[i]”表示节点i的入度。
        //    // 遍历课程编号建立哈希表
        //    for (int i = 0; i < numCourses; i++) {// 注意这里只是建立了哈希表，哈希表的键与空值对应，还没有添加键对应的值。
        //        // 这里只是方便了后续可以直接根据先修课程直接获得先修课程对应的后续课程的集合，这样直接可以将后修课程直接加入到后修课程的集合中
        //        graph.put(i, new ArrayList<>());
        //    }
        //
        //    // 根据先修顺序构建出有向图graph，graph用一个HashMap表示邻接表，它的键key是先修课程，它的值value是必须在键对应的课程之后学习的所有课程。
        //    // 注意原数组的prerequisites的元素的第一个元素是后修课程，第二个元素是先后课程。
        //    for (int[] pre : prerequisites) {// 遍历数组，根据先修顺序构建有向图并记录每一个节点的入度。
        //        // 注意在原参数数组中prerequisites第2个元素才是先修课程，第1个元素是后修课程。
        //        // graph.get(prereq[1])得到先修课程,.add(prereq[0])将先修课程对应的后修课程加入到对应集合中
        //        graph.get(pre[1]).add(pre[0]);// 将先修课程的后修课程添加到对应的键值对中
        //        inDegrees[pre[0]]++;// 后修课程的入度加1
        //    }
        //    // 对有向图进行拓扑排序的算法是每次找出一个入度为0的节点添加到序列中，然后删除该节点及所有以该节点为起点的边。
        //    // 重复这个过程，直到图为空或图中不存在入度为0的节点。
        //    Queue<Integer> queue = new LinkedList<>(); // 广度优先搜索算法实现拓扑排序。队列中保存的是入度为0的节点。
        //    for (int i = 0; i < inDegrees.length; i++) {
        //        if (inDegrees[i] == 0) {
        //            queue.add(i);
        //        }
        //    }
        //    while (!queue.isEmpty()) {
        //        int course = queue.remove();// 每次从队列中取出入度为0的一个节点
        //        numCourses--;// 剩余课程数减1。
        //        // 遍历course的所有后修课程。graph.get(course)表示在course之后要学习的课程
        //        for (int next : graph.get(course)) {
        //            inDegrees[next]--;// 因为前面从队列中移除了一个节点等于0的节点。找到该课程course的后修课程next并将它们的节点的入度减1，这相当于删除从先修课程到后修课程的边。
        //            if (inDegrees[next] == 0) {// 如果发现新的入度为0的节点，则将其添加到队列中
        //                queue.add(next);
        //            }
        //        }
        //    }
        //    return numCourses == 0;
        //}




        //// 解法2：拓扑排序  和上面的不同点。1.这里使用了计数器；2.使用哈希表来保存先修课程的后修课程
        //public boolean canFinish(int numCourses, int[][] prerequisites) {
        //    if (prerequisites == null || prerequisites.length == 0) {
        //        return false;
        //    }
        //    // 用哈希表类型的变量graph来表示图的邻接表形式
        //    Map<Integer, Set<Integer>> graph = new HashMap<>();
        //    for (int i = 0; i < numCourses; i++) {
        //        graph.put(i, new HashSet<>());
        //    }
        //
        //    int[] inDegrees = new int[numCourses];
        //    for (int[] pre : prerequisites) {
        //        graph.get(pre[1]).add(pre[0]);
        //        inDegrees[pre[0]]++;
        //    }
        //
        //    Queue<Integer> queue = new LinkedList<>();
        //    for (int i = 0; i < numCourses; i++) {
        //        if (inDegrees[i] == 0) {
        //            queue.add(i);
        //        }
        //    }
        //    int count = 0;
        //    while (!queue.isEmpty()) {
        //        int course = queue.remove();
        //        count++;
        //        for (int next : graph.get(course)) {
        //            inDegrees[next]--;
        //            if (inDegrees[next] == 0) {
        //                queue.offer(next);
        //            }
        //        }
        //    }
        //    return count == numCourses;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
