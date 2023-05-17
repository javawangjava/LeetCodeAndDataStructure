/**
 * <div class="original__bRMd">
 * <div>
 * <p>有 <code>n</code> 个城市，其中一些彼此相连，另一些没有相连。如果城市 <code>a</code> 与城市 <code>b</code> 直接相连，且城市 <code>b</code> 与城市
 * <code>c</code> 直接相连，那么城市 <code>a</code> 与城市 <code>c</code> 间接相连。</p>
 * </div>
 * </div>
 *
 * <p><strong>省份</strong> 是一组直接或间接相连的城市，组内不含其他没有相连的城市。</p>
 *
 * <p>给你一个 <code>n x n</code> 的矩阵 <code>isConnected</code> ，其中 <code>isConnected[i][j] = 1</code> 表示第 <code>i</code>
 * 个城市和第 <code>j</code> 个城市直接相连，而 <code>isConnected[i][j] = 0</code> 表示二者不直接相连。</p>
 *
 * <p>返回矩阵中 <strong>省份</strong> 的数量。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/24/graph1.jpg" style="width: 222px; height: 142px;" />
 * <pre>
 * <strong>输入：</strong>isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/12/24/graph2.jpg" style="width: 222px; height: 142px;" />
 * <pre>
 * <strong>输入：</strong>isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 200</code></li>
 * <li><code>n == isConnected.length</code></li>
 * <li><code>n == isConnected[i].length</code></li>
 * <li><code>isConnected[i][j]</code> 为 <code>1</code> 或 <code>0</code></li>
 * <li><code>isConnected[i][i] == 1</code></li>
 * <li><code>isConnected[i][j] == isConnected[j][i]</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>图</li></div></div><br><div><li>👍
 * 905</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 547
 * 省份数量
 *
 * @author wangweizhou
 * @date 2022-12-14 15:02:14
 */

public class NumberOfProvinces {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new NumberOfProvinces().new Solution();
        int[][] arr = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int ans = solution.findCircleNum(arr);
        System.out.println(ans);

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 朋友关系是对称的，也就是说，A和B是朋友，那么B和A自然也是朋友。
        // 因此，输入的矩阵M是沿着对角线对称的。一个人和他自己是朋友，也就是说矩阵M中对角线上的所有数字都是1。
        // 朋友的关系可以用图表示，每个学生就是图中的一个节点，而直接朋友就是图中的边。
        // 如果学生i和学生j是直接朋友，就在节点i和节点j之间添加一条边。输入的矩阵是图的邻接矩阵。


        // 解法1：广度优先搜索
        // 一个班级可以包含一个或多个朋友圈，对应的图中可能包含一个或多个子图，每个朋友圈对应一个子图。
        // 因此，这个问题可以转化为如何求图中子图的数目。
        // 图的搜索算法可以用来计算图中子图的数目。扫描图中所有的节点,如果某个节点v之前没有访问过，就搜索它所在的子图。
        // 当所有节点都访问完之后，就可以知道图中有多少个子图。
        // 广度优先搜索和深度优先搜索都可以用来计算图中子图的数目。

        // 如果某个学生i对应的节点之前没有访问过，则调用函数findCircle访问他所在朋友圈对应子图的所有节点。
        // 变量result记录朋友圈的数目，每访问一个朋友圈对应的子图，result加1。
        // 函数findCircle基于广度优先搜索算法搜索某学生i所在朋友圈对应子图的所有节点。
        // 这个题目的图用邻接矩阵M表示，如果学生i和学生j是直接朋友，那么M[i][j]等于1，它们在图中对应的节点之间有一条边相连。


        //  本质也是图的遍历，只是这里的图不需要建立，只要认为是假象的图就可以，每一个城市都可能和其他城市相连，所以这里就类似邻接表。
        public int findCircleNum(int[][] isConnected) {
            if (isConnected == null || isConnected.length == 0 || isConnected[0].length == 0) {// 判空
                return 0;
            }
            int len=isConnected.length;// 学生人数
            boolean[] isVisited = new boolean[len];// 标记数组标记学生是否有访问过
            int num = 0;// 子图的个数
            for (int i = 0; i < len; i++) {// 遍历每一个学生
                if (!isVisited[i]) {// 当学生i没有访问过时，则以学生i为起点进行遍历子图，每遍历完一个子图，则子图的个数加1
                    findCircle(isConnected, isVisited, i);
                    num++;
                }
            }
            return num;
        }



        // 在图中以学生index为起点进行子图的遍历
        private void findCircle(int[][] isConnected, boolean[] isVisited, int index) {
            Queue<Integer> queue = new LinkedList<>();// 广度遍历使用队列实现
            // 每次将遍历到的当前节点加入到队列中，并将当前节点设置为已经访问
            queue.add(index);// 将当前遍历的节点加入到队列中
            isVisited[index] = true;
            while (!queue.isEmpty()) {
                int curr = queue.remove();
                // 因为朋友关系是使用邻接表来表示的，所以要判断当前元素与其他元素【包括自己】的关系
                // 这里每一个城市都有可能与其他所有的城市相连，而且这里并没有实际建立邻接表或者图。
                // 那么这里所有的城市其实就可以看作是在临界表中保存的。
                for (int neighbor = 0; neighbor < isConnected.length; neighbor++) {
                    if (isConnected[curr][neighbor] == 1 && !isVisited[neighbor]) {
                        queue.add(neighbor);
                        isVisited[neighbor] = true;
                    }
                }
            }
        }




        //
        //public int findCircleNum(int[][] isConnected) {
        //    if (isConnected == null || isConnected.length == 0 || isConnected[0].length == 0) {
        //        return 0;
        //    }
        //    int len = isConnected.length;
        //    int[] fathers = new int[len];
        //    for (int i = 0; i < len; i++) {
        //        fathers[i] = i;
        //    }
        //    int count = len;
        //    for (int i = 0; i < len; i++) {
        //        for (int j = 0; j < len; j++) {
        //            if (isConnected[i][j] == 1 && union(fathers, i, j)) {
        //                count--;
        //            }
        //        }
        //    }
        //    return count;
        //}
        //
        //
        //private int findFather(int[] fathers, int i) {
        //    if (fathers[i] != i) {
        //        fathers[i] = findFather(fathers, fathers[i]);
        //    }
        //    return fathers[i];
        //}
        //
        //
        //private boolean union(int[] fathers, int i, int j) {
        //    int fatherOfI = findFather(fathers, i);
        //    int fatherOfJ = findFather(fathers, j);
        //    if (fatherOfI != fatherOfJ) {
        //        fathers[fatherOfI] = fatherOfJ;
        //        return true;
        //    }
        //    return false;
        //}


        // 解法2：并查集
        // 判断两个节点是不是连通，也就是判断它们是不是属于同一个子集，只需要看它们的根节点是不是相同就可以。

        // 一个表示n个学生的朋友关系的图中有n个节点。在初始化时这个图有n个子图，每个子图都只包含一个节点。
        // 接下来一步步连接彼此是朋友的两个学生对应的节点，逐步形成朋友圈。
        // 朋友关系用矩阵M表示。当M[i][j]=1时，学生i和学生j是直接朋友，因此他们在同一个朋友圈中。
        // 这个时候要解决两个问题：第一，如何判断学生i和学生j是不是已经在同一个朋友圈（即子图）中，也就是判断节点i和节点j是否连通；
        // 第二，如果学生i和学生j之前不连通（不在同一个子图中），那么应该如何合并他们所在的两个子图使他们位于同一个子图（即同一个朋友圈）中。
        // 并查集正好能完美地解决这两个问题。接下来介绍如何使用并查集。
        // 并查集的子集和图中的子图对应，并查集中的子集用树形结构表示。
        // 子集的节点都有父节点，根节点的父节点就是它自身。同一个子集中不同节点的根节点一定相同。
        // 判断两个节点是不是连通，也就是判断它们是不是属于同一个子集，只需要看它们的根节点是不是相同就可以。

        // 创建长度为n的数组fathers存储n个节点的父节点。有了这个数组fathers，如果想知道节点i所在的子集的根节点，
        // 就可以从节点i开始沿着指向父节点的指针搜索，时间复杂度看起来是O（n），但可以将从节点i到根节点的路径压缩，从而优化时间效率。

        // 我们真正关心的是节点i的根节点是谁而不是它的父节点，因此可以在fathers[i]中存储它的根节点。
        // 当第1次找节点i的根节点时，还需要沿着指向父节点的边遍历直到找到根节点。一旦找到了它的根节点，就把根节点存放到fathers[i]中。
        // 不仅如此，还可以一起更新从节点i到根节点的路径上所有节点的根节点。
        // 以后只需要O（1）的时间就能知道这些节点的根节点。这种优化叫作路径压缩，因为从节点i到根节点的路径被压缩成若干长度为1的路径。

        // 代码中，数组fathers用来记录每个节点的根节点。
        // 如果班级中有n个学生，那么n个节点被初始化成n个互不连通的子图，在并查集中每个节点的父节点指针都指向它自己，即fathers[i]=i。
        // 当学生i和学生j互为朋友（M[i][j]等于1）时，调用函数union在必要时合并他们的朋友圈，该函数首先判断节点i和节点j的根节点是否相同。
        // 如果它们的根节点不同，那么它们位于不同的子集中，将一个子集的根节点的指向父节点的指针指向另一个子集的根节点，这就合并了两个子集时。
        // 函数union在合并两个子集时返回true。每当两个子集合并成一个子集，子集数目就减1，相应地，班级中的朋友圈的数目也减1。
        // 如果节点i和节点j的根节点相同，它们已经位于同一个子集中，那么它们对应的两个学生已经在同一个朋友圈中，也就没有必要合并，此时直接返回false。
        // 函数findFather用来查找一个节点的根节点。一旦得知节点i的根节点，就记录到fathers[i]中，相当于压缩了路径。

        // 接下来考虑如何合并两个子图。假设第1个子图的根节点是i，第2个子图的根节点是j。
        // 如果把fathers[i]设为j，就相当于把整个第1个子图挂在节点j的下面，让第1个子图成为第2个子图的一部分，也就是合并两个子图。


        //public int findCircleNum(int[][] isConnected) {
        //    if (isConnected == null || isConnected.length == 0 || isConnected[0].length == 0) {
        //        return 0;
        //    }
        //    int len = isConnected.length;// 学生的个数
        //    int[] fathers = new int[len];// 数组fathers用来记录每个节点的根节点。创建长度为n的数组fathers存储n个节点的父节点。
        //    for (int i = 0; i < len; i++) {
        //        // 如果班级中有n个学生，那么n个节点被初始化成n个互不连通的子图，在并查集中每个节点的父节点指针都指向它自己，即fathers[i]=i。
        //        fathers[i] = i;
        //    }
        //
        //    // 一个表示n个学生的朋友关系的图中有n个节点。在初始化时这个图有n个子图，每个子图都只包含一个节点。
        //    int count = isConnected.length;// 子图数的数量count，初始化为子图数，也就是学生的个数，当子图可以合并时，子图数减少
        //    for (int i = 0; i < len; i++) {
        //        for (int j = i + 1; j < len; j++) {
        //            // 当学生i和学生j互为朋友（M[i][j]等于1）时，调用函数union在必要时合并他们的朋友圈。
        //            // 每当两个子集合并成一个子集，子集数目就减1，相应地，班级中的朋友圈的数目也减1。
        //            if (isConnected[i][j] == 1 && union(fathers, i, j)) {
        //                count--;
        //            }
        //        }
        //    }
        //    return count;
        //}
        //
        //
        //
        //// 我们真正关心的是节点i的根节点是谁而不是它的父节点，因此可以在fathers[i]中存储它的根节点。
        //// 当第1次找节点i的根节点时，还需要沿着指向父节点的边遍历直到找到根节点。一旦找到了它的根节点，就把根节点存放到fathers[i]中。
        //// 函数findFather用来查找一个节点的根节点。一旦得知节点i的根节点，就记录到fathers[i]中，相当于压缩了路径。
        //private int findFather(int[] fathers, int i) {
        //    // 当节点i的根节点不是自身时，递归找到节点i的父节点
        //    if (fathers[i] != i) {
        //        fathers[i] = findFather(fathers, fathers[i]);
        //    }
        //    return fathers[i];
        //}
        //
        //
        //// 函数union合并两个子集
        //// 子集的节点都有父节点，根节点的父节点就是它自身。同一个子集中不同节点的根节点一定相同。
        //// 判断两个节点是不是连通，也就是判断它们是不是属于同一个子集，只需要看它们的根节点是不是相同就可以。
        //
        //// 若两个子集的根节点相同，它们已经位于同一个子集中，则不用合并，返回false。
        //// 若两个子集的根节点不同，那么它们位于不同的子集中，将一个子集的根节点的指向父节点的指针指向另一个子集的根节点，这就合并了两个子集时，函数union在合并两个子集时返回true
        //private boolean union(int[] fathers, int i, int j) {
        //    int fatherOfI = findFather(fathers, i);
        //    int fatherOfJ = findFather(fathers, j);
        //    // 如果它们的根节点不同， ，将一个子集的根节点的指向父节点的指针指向另一个子集的根节点，这就合并了两个子集时。
        //    if (fatherOfI != fatherOfJ) {
        //        fathers[fatherOfI] = fatherOfJ;
        //        return true;
        //    }
        //    return false;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
