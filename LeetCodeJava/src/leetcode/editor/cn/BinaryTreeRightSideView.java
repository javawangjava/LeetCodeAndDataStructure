/**
 * <p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <p><img src="https://assets.leetcode.com/uploads/2021/02/14/tree.jpg" style="width: 270px; " /></p>
 *
 * <pre>
 * <strong>输入:</strong> [1,2,3,null,5,null,4]
 * <strong>输出:</strong> [1,3,4]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> [1,null,3]
 * <strong>输出:</strong> [1,3]
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> []
 * <strong>输出:</strong> []
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li>二叉树的节点个数的范围是 <code>[0,100]</code></li>
 * <li><meta charset="UTF-8" /><code>-100 <= Node.val <= 100</code> </li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 731</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 199
 * 二叉树的右视图
 *
 * @author wangweizhou
 * @date 2022-07-27 20:56:10
 */

public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BinaryTreeRightSideView().new Solution();
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


		// 即二叉树的右侧视图。当站在二叉树的右侧时，看到的应该是每层最右边的一个节点，而每层的其他节点都被最右边的节点挡住。
		// 因此，二叉树的右侧视图其实就是从上到下每层最右边的节点。

		//// 解法1：BFS   层序遍历  写法1 从左向右遍历，每进入一层就将每一层最右边的节点加入队列
		public List<Integer> rightSideView(TreeNode root) {
			List<Integer> lists = new ArrayList<>();
			if (root == null) {
				return lists;
			}
			Deque<TreeNode> queue=new LinkedList<>();
			queue.add(root);
			while(queue.size()>0){
				// // 在这里加入和进入内层循环时通过if(i==levelSize-1)来控制是一样的
				//lists.add(queue.peekLast().val);
				int levelSize=queue.size();
				for (int i = 0; i < levelSize; i++) {
					TreeNode node=queue.poll();
					if(i==levelSize-1){// 当前元素是本层的最后一个元素。因为层序遍历是从左向右进入队列的，那么本层最右的元素就是本层最后一个元素。
						lists.add(node.val);
					}
					if(node.left!=null){
						queue.add(node.left);
					}
					if(node.right!=null){
						queue.add(node.right);
					}
				}
			}
			return lists;
		}





		//// 解法1：BFS   层序遍历  写法2 从右向左遍历，每进入一层就将每一层最右边【即每一层第一个】的节点加入队列
		//public List<Integer> rightSideView(TreeNode root) {
		//	List<Integer> lists = new ArrayList<>();
		//	if (root == null) {
		//		return lists;
		//	}
		//	Deque<TreeNode> queue=new LinkedList<>();
		//	queue.add(root);
		//	while(queue.size()>0){
		//		lists.add(queue.peek().val);// 在这里加入和进入内层循环时通过if(i==0)来控制是一样的
		//		int levelSize=queue.size();
		//		for (int i = 0; i < levelSize; i++) {
		//			TreeNode node=queue.poll();
		//			//if(i==0){
		//			//	lists.add(node.val);
		//			//}
		//			// 注意这里将层序遍历的从左向右调整为从右向左遍历
		//			if(node.right!=null){
		//				queue.add(node.right);
		//			}
		//			if(node.left!=null){
		//				queue.add(node.left);
		//			}
		//		}
		//	}
		//	return lists;
		//}




		//// 解法1：BFS   层序遍历  写法3
		//// 由于需要区分二叉树不同的层，因此在遍历时把不同层的节点放入不同的队列，也就是利用两个队列分别存放当前遍历的层和下一层的节点。
		//
		//// 变量node是当前遍历到的节点。当队列queue1被清空时（即queue1.isEmpty()为true时），当前这一层已经遍历完，变量node是这一层的最右边的节点，可以添加到右侧视图中。
		//// 当从上到下所有层的节点都遍历完之后，二叉树的右侧视图的所有节点就都已经找到。
		//public List<Integer> rightSideView(TreeNode root) {
		//	List<Integer> list=new ArrayList<>();
		//	if(root==null){
		//		return list;
		//	}
		//
		//	Queue<TreeNode> queue1=new LinkedList<>();
		//	Queue<TreeNode> queue2=new LinkedList<>();
		//	queue1.offer(root);
		//	while (!queue1.isEmpty()){
		//		TreeNode node=queue1.poll();
		//		if(node.left!=null){
		//			queue2.offer(node.left);
		//		}
		//
		//		if(node.right!=null){
		//			queue2.offer(node.right);
		//		}
		//		// 变量node是当前遍历到的节点。当队列queue1被清空时（即queue1.isEmpty()为true时），当前这一层已经遍历完，变量node是这一层的最右边的节点，可以添加到右侧视图中。
		//		if(queue1.isEmpty()){// 每一层为空，那么最后一个元素就是最右边的元素
		//			list.add(node.val);
		//			queue1=queue2;
		//			queue2=new LinkedList<>();
		//		}
		//	}
		//	return list;
		//}





        /*

        // 解法2：DFS  将先序遍历的  【根节点 -> 左子树 -> 右子树】   调整为  【根结点 -> 右子树 -> 左子树】
        // 由层序遍历可以知道每进入新的一层，则将右边第一个节点加入lists中，所以只能是先序遍历的变形【根结点 -> 右子树 -> 左子树】
        // 就可以保证每层都是最先访问最右边的节点的。

        List<Integer> lists = new ArrayList<>();
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(root, res,0); // 从根节点开始访问，根节点深度是0
            return res;
        }


        // 注意集合的下标从0开始，本题的根节点的深度也是从0开始的。
        // 只有第一次进入新的一层时，会更新深度【也就是deplists添加新的元素，新的一层的最大值】。
        // 当节点所在层已经遍历过之后，寻找这一层的最大数并更新
        private void dfs(TreeNode root, List<Integer> lists, int depth) {
            if (root == null) {
                return;
            }

            // 先访问当前节点，这样才能包装每新进入一层的第一个节点加入lists中
            // // lists.size()就是集合中已经添加的元素个数，也就是已经遍历过的层数，也就是已经到达过的深度
			if (depth == lists.size()) { // 本题的根节点的深度也是从0开始的。所以depth == lists.size()也就是进入新的一层
				lists.add(root.val);// 说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
			}
            dfs(root.right, lists, depth+1);//处理右子树，深度+1
            dfs(root.left, lists, depth+1);
        }

        */

    }
//leetcode submit region end(Prohibit modification and deletion)

}
