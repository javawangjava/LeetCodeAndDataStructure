/**
 * <p>给定一棵二叉树的根节点&nbsp;<code>root</code> ，请找出该二叉树中每一层的最大值。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode.com/uploads/2020/08/21/largest_e1.jpg" style="height: 172px; width:
 * 300px;" /></p>
 *
 * <pre>
 * <strong>输入: </strong>root = [1,3,2,5,3,null,9]
 * <strong>输出: </strong>[1,3,9]
 * </pre>
 *
 * <p><strong>示例2：</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>root = [1,2,3]
 * <strong>输出: </strong>[1,3]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>二叉树的节点个数的范围是 <code>[0,10<sup>4</sup>]</code></li>
 * <li><meta charset="UTF-8" /><code>-2<sup>31</sup>&nbsp;&lt;= Node.val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 274</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 515
 * 在每个树行中找最大值
 *
 * @author wangweizhou
 * @date 2022-09-04 20:45:06
 */

public class FindLargestValueInEachTreeRow {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new FindLargestValueInEachTreeRow().new Solution();

        TreeNode rootnode = new TreeNode(1);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(9);
        //TreeNode node7 = new TreeNode(7);


        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        //node3.right=node7;
        List<Integer> lists = solution.largestValues(rootnode);

    }

//leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    class Solution {


        //二叉树节点的深度：指从根节点到该节点的最长简单路径边的条数。从根节点开始。
        //二叉树节点的高度：指从该节点到叶子节点的最长简单路径边的条数。从叶子节点开始。
        //关于根节点的深度究竟是1 还是 0，不同的地方有不一样的标准。
        //leetcode的题目中都是以节点为一度，即根节点深度是1。
        //但维基百科上定义用边为一度，即根节点的深度是0，我们暂时以leetcode为准（毕竟要在这上面刷题


        // 由于要找出二叉树中每层的最大值，因此在遍历时需要知道每层什么时候开始、什么时候结束。
        // 如果不同层的节点同时位于队列之中，那么每次从队列之中取出节点来遍历时就需要知道这个节点位于哪一层。


        // 解法1：写法1  层序遍历，计数器实现 及时更新每一层的最大值  单独更新每一层的最大值
        public List<Integer> largestValues(TreeNode root) {
            List<Integer> lists = new ArrayList<>();
            if (root == null) {
                return lists;
            }
            Deque<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (queue.size() > 0) {
                int levelSize = queue.size();
                int levelMax = Integer.MIN_VALUE;
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.poll();
                    levelMax = Math.max(levelMax, node.val);
                    // 进入队列的必须是数据节点，所以要判空
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
                lists.add(levelMax);
            }
            return lists;
        }




        // 解法1：写法2 层序遍历，不同的队列存储不同的层

        // 另一个办法是把不同层的节点放入不同的队列中。需要注意的是，当遍历某一层时，会将位于下一层的子节点也插入队列中，也就是说，队列中会有位于两层的节点。
        // 可以用两个不同的队列queue1和queue2分别存放两层的节点，队列queue1中只放当前遍历层的节点，而队列queue2中只放下一层的节点。
        // 最开始时把二叉树的根节点放入队列queue1中。接下来每次从队列中取出一个节点遍历。由于队列queue1用来存放当前遍历层的节点，因此总是从队列queue1中取出节点用来遍历。
        // 如果当前遍历的节点有子节点，并且子节点位于下一层，则把子节点都放入队列queue2中。
        // 当队列queue1被清空时，当前层的所有节点都已经被遍历完。通过比较这一层所有节点的值，就能找出这一层所有节点的最大值。
        // 在开始遍历下一层之前，把队列queue1指向队列queue2，并将队列queue2重新初始化为空的队列。重复这个过程，直到所有节点都遍历完为止。


        //public List<Integer> largestValues(TreeNode root) {
        //    List<Integer> list = new ArrayList<>();
        //    if (root == null) {
        //        return list;
        //    }
        //    Queue<TreeNode> queue1 = new LinkedList<>();// 存储当前层
        //    Queue<TreeNode> queue2 = new LinkedList<>();// 存储当前层的下一层
        //    int maxLevelVal = Integer.MIN_VALUE;// 保存每一层的最大值
        //    TreeNode node = root;
        //    queue1.add(node);
        //
        //    while (!queue1.isEmpty()) {
        //        node = queue1.poll();// 栈顶元素出队
        //        maxVal = Math.max(maxVal, node.val);// 更新最大值
        //        if (node.left != null) {
        //            queue2.add(node.left);
        //        }
        //        if (node.right != null) {
        //            queue2.add(node.right);
        //        }
        //        // 当前层为空，换层处理的语句
        //        if (queue1.isEmpty()) {
        //            queue1 = queue2;// 队列1指向队列2
        //            queue2 = new LinkedList<>();// 队列2指向新建的队列
        //            list.add(maxVal);// 加过集合中加入当前层的最大值
        //            maxVal = Integer.MIN_VALUE;// 当前层的最大值重置
        //        }
        //    }
        //    return list;
        //}








        // 解决这个问题的一个办法是计数。需要注意的是，当遍历某一层的节点时，会将下一层的节点也放入队列中。
        // 因此，可以用两个变量分别记录两层节点的数目，变量current记录当前遍历这一层中位于队列之中节点的数目，变量next记录下一层中位于队列之中节点的数目。
        // 最开始把根节点插入队列中时，把变量current初始化为1。
        // 接下来逐个从队列中取出节点遍历。每当从队列中取出一个节点时，当前层的剩余节点就少了一个，因此变量current的数目减1。
        // 如果当前遍历的节点有子节点，那么将子节点插入队列中。由于子节点都位于当前遍历节点的下一层，因此在队列中添加一个子节点，变量next的数目将增加1。
        // 当变量current的数值变成0时，表示当前层的所有节点都已经遍历完。可以通过比较当前层的所有节点的值，找出这一层节点的最大值。
        // 接下来在开始遍历下一层节点之前，把变量current的值设为变量next的值，并把变量next重新初始化为0。重复这个过程，直到所有节点都遍历完为止。

        // 变量max初始化为最小的整数值。在遍历某一层的节点时，只要当前遍历的节点的值大于变量max，就更新变量max的值。
        // 当这一层所有的节点都遍历完时（即变量current的值变成0），变量max的值就是这一层中节点的最大值。在开始遍历下一层之前，重新把变量max的值初始化为最小的整数值。

        //// 解法1：写法3 层序遍历，计数器来标识不同的层
        //public List<Integer> largestValues(TreeNode root) {
        //    List<Integer> list = new ArrayList<>();
        //    if (root == null) {
        //        return list;
        //    }
        //    int currentCounts=0;
        //    int nextCounts=0;
        //    int maxLevelVal = Integer.MIN_VALUE;// 保存每一层的最大值
        //    Queue<TreeNode> queue=new LinkedList<>();
        //    TreeNode node=root;
        //    queue.add(node);
        //    currentCounts++;
        //    while (!queue.isEmpty()){
        //        node=queue.poll();
        //        currentCounts--;
        //        maxLevelVal=Math.max(maxLevelVal,node.val);
        //
        //        if(node.left!=null){
        //            queue.add(node.left);
        //            nextCounts++;
        //        }
        //        if(node.right!=null){
        //            queue.add(node.right);
        //            nextCounts++;
        //        }
        //        if(currentCounts==0){
        //            currentCounts=nextCounts;
        //            nextCounts=0;
        //            list.add(maxLevelVal);
        //            maxLevelVal = Integer.MIN_VALUE;// 重置每一层的最大值
        //        }
        //    }
        //    return list;
        //}






        //// 解法2： 前序递归深度遍历+记忆化 递归 写法1
        //// 注意集合的下标从0开始，根节点的深度也是从0开始的。
        //// 只有第一次进入新的一层时，会更新深度【也就是deplists添加新的元素，新的一层的最大值】。
        //// 当节点所在层已经遍历过之后，寻找这一层的最大数并更新
        //
        //public List<Integer> largestValues(TreeNode root) {
        //    // 只有第一次进入新的一层时，会更新深度【deplists添加新的元素，新的一层的最大值】中添加
        //    List<Integer> depthlists = new ArrayList<>();// depthlists用来记录每一层的最大数字
        //    if (root == null) {
        //        return depthlists;
        //    }
        //    dfs(root, 0, depthlists);//注意这里这里深度是0，
        //    return depthlists;
        //}
        //
        //
        //// 前序递归深度遍历+记忆化
        //// 注意前后的深度定义必须一致
        //private void dfs(TreeNode root, int depth, List<Integer> lists) {
        //    if (root == null) {
        //        return;
        //    }
        //    // lists.size()就是集合中已经添加的元素个数，也就是已经遍历过的层数，也就是已经到达过的深度
        //    if (depth < lists.size()) {// 当前深度小于lists中元素个数，也就是说没有进入新的一层,则更新该层的最大值。
        //        // 当前深度所对应的层已经有元素加入到lists中，只需要更新当前层的最大值
        //        lists.set(depth, Math.max(lists.get(depth), root.val));
        //    } else {// depth >= lists.size(),其实就是等于【没有办法大于】，等于的话说明记录了当前已经访问过的每一层的最大值，进入新的一层
        //        lists.add(root.val); // 当前层第一次到达，lists中新增一层
        //    }
        //    // 和上面的作用一样
        //    //// lists 中大小即元素个数其实就是第几层，或者深度。
        //    //if (depth == lists.size()){// 等于的话说明记录了当前已经访问过的每一层的最大值，进入新的一层
        //    //	lists.add(root.val); // 当前层第一次到达，lists中新增一层
        //    //}
        //    //else {//其实是depth<lists.size(),当前深度小于lists中元素个数，也就是说没有进入新的一层,则更新对应层的最大值。
        //    //	lists.set(depth, Math.max(lists.get(depth), root.val));
        //    //}
        //    dfs(root.left, depth + 1, lists);// 递归遍历左子树 。处理当前节点的左子节点。处理左子树
        //    dfs(root.right, depth + 1, lists);
        //
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
