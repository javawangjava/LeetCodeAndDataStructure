/**
 * <p>现在你总共有 <code>numCourses</code> 门课需要选，记为&nbsp;<code>0</code>&nbsp;到&nbsp;<code>numCourses -
 * 1</code>。给你一个数组&nbsp;<code>prerequisites</code> ，其中 <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code>
 * ，表示在选修课程 <code>a<sub>i</sub></code> 前 <strong>必须</strong> 先选修&nbsp;<code>b<sub>i</sub></code> 。</p>
 *
 * <ul>
 * <li>例如，想要学习课程 <code>0</code> ，你需要先完成课程&nbsp;<code>1</code> ，我们用一个匹配来表示：<code>[0,1]</code> 。</li>
 * </ul>
 *
 * <p>返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 <strong>任意一种</strong> 就可以了。如果不可能完成所有课程，返回 <strong>一个空数组</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numCourses = 2, prerequisites = [[1,0]]
 * <strong>输出：</strong>[0,1]
 * <strong>解释：</strong>总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 <span><code>[0,1] 。</code></span>
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * <strong>输出：</strong>[0,2,1,3]
 * <strong>解释：</strong>总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是&nbsp;<span><code>[0,1,2,3]</code></span> 。另一个正确的排序是&nbsp;<span><code>[0,2,1,3]</code></span> 。</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>numCourses = 1, prerequisites = []
 * <strong>输出：</strong>[0]
 * </pre>
 *
 * <p>&nbsp;</p>
 * <strong>提示：</strong>
 *
 * <ul>
 * <li><code>1 &lt;= numCourses &lt;= 2000</code></li>
 * <li><code>0 &lt;= prerequisites.length &lt;= numCourses * (numCourses - 1)</code></li>
 * <li><code>prerequisites[i].length == 2</code></li>
 * <li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
 * <li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
 * <li>所有<code>[a<sub>i</sub>, b<sub>i</sub>]</code> <strong>互不相同</strong></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>图</li><li>拓扑排序</li></div></div><br><div><li
 * >👍 731</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 210
 * 课程表 II
 *
 * @author wangweizhou
 * @date 2022-12-13 00:27:15
 */
public class CourseScheduleIi {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new CourseScheduleIi().new Solution();
        //int[][] prerequisites={{1,0},{2,0},{3,1},{3,2}};
        int[][] prerequisites = {{1, 0}};
        int[] ans = solution.findOrder(2, prerequisites);
        for (int num : ans) {
            System.out.println(num);
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        // 将课程看成图中的节点，如果两门课程存在先修顺序那么它们在图中对应的节点之间存在一条从先修课程到后修课程的边，因此这是一个有向图。
        // 可行的修课序列实际上是图的拓扑排序序列。
        // 图中的每条边都是从先修课程指向后修课程，而拓扑排序能够保证任意一条边的起始节点一定排在终止节点的前面，
        // 因此拓扑排序得到的序列与先修顺序一定不会存在冲突，于是这个问题转变成如何求有向图的拓扑排序序列。
        // 对有向图进行拓扑排序的算法是每次找出一个入度为0的节点添加到序列中，然后删除该节点及所有以该节点为起点的边。
        // 重复这个过程，直到图为空或图中不存在入度为0的节点。


        // 代码先根据先修顺序构建出有向图graph，graph用一个HashMap表示邻接表，它的键是先修课程，它的值是必须在键对应的课程之后学习的所有课程。
        // 同时，将每个节点的入度保存到数组inDegrees中，“inDegrees[i]”表示节点i的入度。
        // 接下来用广度优先搜索算法实现拓扑排序。队列中保存的是入度为0的节点。
        // 每次从队列中取出一个节点，将该节点添加到拓扑排序序列中，然后找到该课程的后修课程并将它们的节点的入度减1，这相当于删除从先修课程到后修课程的边。
        // 如果发现新的入度为0的节点，则将其添加到队列中。重复这个过程直到队列为空，此时要么图中所有节点都已经访问完毕，已经得到了完整的拓扑排序序列；
        // 要么剩下的还没有搜索到的节点形成一个环，已经不存在入度为0的节点。


        //// 有向图的一种拓扑排序
        //// 注意在原参数数组中prerequisites第二个元素才是先修课程，第一个元素是后修课程
        //public int[] findOrder(int numCourses, int[][] prerequisites) {
        //    if (prerequisites == null) {// 注意这里的判空
        //        return new int[0];
        //    }
        //    // 根据先修顺序构建出有向图graph，graph用一个HashMap表示邻接表，它的键key是先修课程，它的值value是必须在键对应的课程之后学习的所有课程。
        //    // 注意课程编号是i。
        //    Map<Integer, List<Integer>> graph = new HashMap<>();
        //    for (int i = 0; i < numCourses; i++) {// 注意这里只是建立了哈希表，哈希表的键与空值对应，还没有添加键对应的值
        //        graph.put(i, new LinkedList<>());
        //    }
        //
        //    // 根据先修顺序构建出有向图graph，graph用一个HashMap表示邻接表，它的键key是先修课程，它的值value是必须在键对应的课程之后学习的所有课程。
        //    int[] inDegrees = new int[numCourses]; // 将每个节点的入度保存到数组inDegrees中，“inDegrees[i]”表示节点i的入度。
        //    // 注意原数组的prerequisites的元素的第一个元素是后修课程，第二个元素是先后课程。
        //    for (int[] prereq : prerequisites) {// 遍历数组，根据先修顺序构建有向图并记录每一个节点的入度。
        //        // 注意在原参数数组中prerequisites第2个元素才是先修课程，第1个元素是后修课程。
        //        // graph.get(prereq[1])得到先修课程,.add(prereq[0])将先修课程对应的后修课程加入到对应集合中
        //        graph.get(prereq[1]).add(prereq[0]);// 将先修课程的后修课程添加到对应的键值对中
        //        inDegrees[prereq[0]]++;// 后修课程的入度加1
        //    }
        //
        //    // 对有向图进行拓扑排序的算法是每次找出一个入度为0的节点添加到序列中，然后删除该节点及所有以该节点为起点的边。
        //    // 重复这个过程，直到图为空或图中不存在入度为0的节点。
        //    Queue<Integer> queue = new LinkedList<>(); // 广度优先搜索算法实现拓扑排序。队列中保存的是入度为0的节点。
        //    for (int i = 0; i < numCourses; i++) {
        //        if (inDegrees[i] == 0) {
        //            queue.add(i);
        //        }
        //    }
        //
        //    // 每次从队列中取出一个节点，将该节点添加到拓扑排序序列中，然后找到该课程的后修课程并将它们的节点的入度减1，这相当于删除从先修课程到后修课程的边。
        //    // 如果发现新的入度为0的节点，则将其添加到队列中。
        //    // 重复这个过程直到队列为空，此时要么图中所有节点都已经访问完毕，已经得到了完整的拓扑排序序列；要么剩下的还没有搜索到的节点形成一个环，已经不存在入度为0的节点。
        //    List<Integer> order = new LinkedList<>();// order表示拓扑排序序列
        //    while (!queue.isEmpty()) {
        //        int course = queue.remove();// 每次从队列中取出入度为0的一个节点
        //        order.add(course);
        //        // 遍历course的所有后修课程。graph.get(course)表示在course之后要学习的课程
        //        for (int next : graph.get(course)) {
        //            inDegrees[next]--;// 找到该课程的后修课程并将它们的节点的入度减1，这相当于删除从先修课程到后修课程的边。
        //            if (inDegrees[next] == 0) {// 如果发现新的入度为0的节点，则将其添加到队列中
        //                queue.add(next);
        //            }
        //        }
        //    }
        //
        //    //return order.size()==numCourses?order.stream().mapToInt(i->i).toArray():new int[0];
        //    if (order.size() == numCourses) {// 如果队列的长度等于课程长度，那就表明该有向无环图已经排好序了
        //        // 将集合转换成数组
        //        int[] ans = new int[numCourses];
        //        for (int i = 0; i < numCourses; i++) {
        //            ans[i] = order.get(i);
        //        }
        //        return ans;
        //    } else {
        //        return new int[0];
        //    }
        //}




        //public int[] findOrder(int numCourses, int[][] prerequisites) {
        //    if(prerequisites==null){
        //        return new int[0];
        //    }
        //    Map<Integer,List<Integer>> map=new HashMap<>();
        //    int[] inDegrees=new int[numCourses];
        //    for (int i = 0; i < numCourses; i++) {
        //        map.put(i,new ArrayList<>());
        //    }
        //    for(int[] pre:prerequisites){
        //        map.get(pre[1]).add(pre[0]);
        //        inDegrees[pre[0]]++;
        //    }
        //    Queue<Integer> queue=new LinkedList<>();
        //    List<Integer> order=new ArrayList<>();
        //    for (int i = 0; i < numCourses; i++) {
        //        if(inDegrees[i]==0){
        //            queue.offer(i);
        //        }
        //    }
        //    while (queue.size()>0){
        //        int curr=queue.remove();
        //        order.add(curr);
        //        for(int next:map.get(curr)){
        //            inDegrees[next]--;
        //            if(inDegrees[next]==0){
        //                queue.offer(next);
        //            }
        //        }
        //    }
        //    if (order.size() == numCourses) {// 如果队列的长度等于课程长度，那就表明该有向无环图已经排好序了
        //        // 将集合转换成数组
        //        int[] ans = new int[numCourses];
        //        for (int i = 0; i < numCourses; i++) {
        //            ans[i] = order.get(i);
        //        }
        //        return ans;
        //    } else {
        //        return new int[0];
        //    }
        //}
        //



        public int[] findOrder(int numCourses, int[][] prerequisites) {
            if (prerequisites == null) {
                return new int[0];
            }
            Map<Integer, Set<Integer>> graph = new HashMap<>();
            Map<Integer, Integer> inDegrees = new HashMap<>();
            for (int i = 0; i < numCourses; i++) {
                graph.put(i, new HashSet<>());
                inDegrees.put(i, 0);
            }

            for (int[] pre : prerequisites) {
                graph.get(pre[1]).add(pre[0]);
                inDegrees.put(pre[0], inDegrees.get(pre[0]) + 1);
            }

            Queue<Integer> queue = new LinkedList<>();
            List<Integer> lists = new ArrayList<>();
            for (Integer in : inDegrees.keySet()) {
                if (inDegrees.get(in) == 0) {
                    queue.offer(in);
                }
            }

            while (queue.size() > 0) {
                int curr = queue.poll();
                lists.add(curr);
                Set<Integer> nexts = graph.get(curr);
                for (Integer next : nexts) {
                    inDegrees.put(next, inDegrees.get(next) - 1);
                    if (inDegrees.get(next) == 0) {
                        queue.offer(next);
                    }
                }
            }

            int len = lists.size();
            if (len == numCourses) {
                int[] ans = new int[len];
                for (int i = 0; i < len; i++) {
                    ans[i] = lists.get(i);
                }
                return ans;
            } else {
                return new int[0];
            }
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

}
