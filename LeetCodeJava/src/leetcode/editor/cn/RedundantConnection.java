/**
 * <p>树可以看成是一个连通且 <strong>无环&nbsp;</strong>的&nbsp;<strong>无向&nbsp;</strong>图。</p>
 *
 * <p>给定往一棵&nbsp;<code>n</code> 个节点 (节点值&nbsp;<code>1～n</code>) 的树中添加一条边后的图。添加的边的两个顶点包含在 <code>1</code> 到
 * <code>n</code>&nbsp;中间，且这条附加的边不属于树中已存在的边。图的信息记录于长度为 <code>n</code> 的二维数组 <code>edges</code>&nbsp;，<code>edges[i] =
 * [a<sub>i</sub>, b<sub>i</sub>]</code>&nbsp;表示图中在 <code>ai</code> 和 <code>bi</code> 之间存在一条边。</p>
 *
 * <p>请找出一条可以删去的边，删除后可使得剩余部分是一个有着 <code>n</code> 个节点的树。如果有多个答案，则返回数组&nbsp;<code>edges</code>&nbsp;中最后出现的边。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626676174-hOEVUL-image.png" style="width: 152px; " /></p>
 *
 * <pre>
 * <strong>输入:</strong> edges = [[1,2], [1,3], [2,3]]
 * <strong>输出:</strong> [2,3]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <p><img alt="" src="https://pic.leetcode-cn.com/1626676179-kGxcmu-image.png" style="width: 250px; " /></p>
 *
 * <pre>
 * <strong>输入:</strong> edges = [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * <strong>输出:</strong> [1,4]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>n == edges.length</code></li>
 * <li><code>3 &lt;= n &lt;= 1000</code></li>
 * <li><code>edges[i].length == 2</code></li>
 * <li><code>1 &lt;= ai&nbsp;&lt; bi&nbsp;&lt;= edges.length</code></li>
 * <li><code>ai != bi</code></li>
 * <li><code>edges</code> 中无重复元素</li>
 * <li>给定的图是连通的&nbsp;</li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>深度优先搜索</li><li>广度优先搜索</li><li>并查集</li><li>图</li></div></div><br><div><li>👍
 * 524</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 684
 * 冗余连接
 *
 * @author wangweizhou
 * @date 2022-12-14 17:39:59
 */
public class RedundantConnection {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new RedundantConnection().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 如果将树看成图，那么一棵有n个节点的树有n-1条边。如果再在树中添加一条边连接任意两个节点，那么一定会形成一个环。
        // 如果两个节点分别属于两个不同的子图，添加一条边连接这两个节点，会将它们所在的子图连在一起，但不会形成环。
        // 如果两个节点属于同一个子图，添加一条边连接这两个节点就会形成一个环。
        // 因此，为了找到多余的边需要解决两个问题：一是如何判断两个节点是否属于同一个子图，二是如何合并两个子图。
        // 由于题目指出节点的编号从1到n，逐一扫描边的数组edges得到最大的节点编号确定n的值。
        // 接下来初始化并查集，将n个节点初始化为n个子集，每个节点的根节点都指向它自己，即“fathers[i]=i”。
        // 接下来逐一在图中添加边，直到某条边的两个节点属于同一个子集，此时函数union将返回false。添加这条边将导致图中出现环，对于树而言这条边就是多余的。



        // 解法1：并查集
        public int[] findRedundantConnection(int[][] edges) {
            if (edges == null || edges.length == 0 || edges[0].length == 0) {// 判空
                return new int[0];
            }

            int maxVertex = 0;// 表示节点标号的最大值
            // 二维数组edges的元素就是每一条边的两端字母
            for (int[] edge : edges) {// 由于题目指出节点的编号从1到n，逐一扫描边的数组edges得到最大的节点编号确定n的值。
                int tempMax = Math.max(edge[0], edge[1]);// 获取每一条边中的最大值
                maxVertex = Math.max(tempMax, maxVertex);// 获取无向图中的最大节点编号
            }

        	// 创建长度为n+1的数组fathers存储n个节点的父节点。方便处理
            int[] fathers = new int[maxVertex + 1];// 无向图中保存的是1~n,所以这里右移一个单位
            for (int i = 1; i <= maxVertex; i++) {// 这里右移一位是为了方便处理，类比链表的头节点
                fathers[i] = i;
            }

            for (int[] edge : edges) {
        		// 接下来逐一在图中添加边，直到某条边的两个节点属于同一个子集，此时函数union将返回false。添加这条边将导致图中出现环，对于树而言这条边就是多余的。
                if (!union(fathers, edge[0], edge[1])) {
                    return edge;
                }
            }
            return new int[2];
        }


        // 我们真正关心的是节点i的根节点是谁而不是它的父节点，因此可以在fathers[i]中存储它的根节点。
        // 当第1次找节点i的根节点时，还需要沿着指向父节点的边遍历直到找到根节点。一旦找到了它的根节点，就把根节点存放到fathers[i]中。
        // 函数findFather用来查找一个节点的根节点。一旦得知节点i的根节点，就记录到fathers[i]中，相当于压缩了路径。
        private int findFather(int[] fathers, int i) {
            // 当节点i的根节点不是自身时，递归找到节点i的父节点
            if (fathers[i] != i) {
                fathers[i] = findFather(fathers, fathers[i]);
            }
            return fathers[i];
        }


        // 函数union合并两个子集
        // 若两个子集的根节点相同，它们已经位于同一个子集中，则不用合并，返回false。
        // 若两个子集的根节点不同，那么它们位于不同的子集中，将一个子集的根节点的指向父节点的指针指向另一个子集的根节点，这就合并了两个子集时，函数union在合并两个子集时返回true
        private boolean union(int[] fathers, int i, int j) {
            int fatherOfI = findFather(fathers, i);
            int fatherOfJ = findFather(fathers, j);
            // 如果它们的根节点不同，那么它们位于不同的子集中，将一个子集的根节点的指向父节点的指针指向另一个子集的根节点，这就合并了两个子集时。
            if (fatherOfI != fatherOfJ) {
                fathers[fatherOfI] = fatherOfJ;
                return true;
            }
            return false;
        }



    }
//leetcode submit region end(Prohibit modification and deletion)

}
