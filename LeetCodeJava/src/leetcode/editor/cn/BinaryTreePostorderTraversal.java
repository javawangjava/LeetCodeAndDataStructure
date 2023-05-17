/**
 * <p>给你一棵二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>后序遍历 </strong>。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/pre1.jpg" style="width: 127px; height: 200px;" />
 * <pre>
 * <strong>输入：</strong>root = [1,null,2,3]
 * <strong>输出：</strong>[3,2,1]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [1]
 * <strong>输出：</strong>[1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点的数目在范围 <code>[0, 100]</code> 内</li>
 * <li><code>-100 &lt;= Node.val &lt;= 100</code></li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>深度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍
 * 879</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 145
 * 二叉树的后序遍历
 *
 * @author wangweizhou
 * @date 2022-07-09 21:04:08
 */

public class BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new BinaryTreePostorderTraversal().new Solution();

        //创建需要的结点
        TreeNode rootnode = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        // 手动创建二叉树
        rootnode.left = node2;
        rootnode.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        List<Integer> list = solution.postorderTraversal(rootnode);
        System.out.println(list);

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

        ////解法1：迭代 写法2
        //
        //public List<Integer> postorderTraversal(TreeNode root) {
        //	List<Integer> lists =new ArrayList<>();
        //	if(root==null){
        //		return lists;
        //	}
        //	Deque<TreeNode> stack=new LinkedList<>();
        //	TreeNode pre=root;// 记录上一个访问过的节点,注意这里的初始化为根节点
        //	TreeNode node=root;//node记录上一个访问的节点
        //	stack.push(node);
        //	while(!stack.isEmpty()){
        //		// 注意这里没有弹出
        //		TreeNode peek = stack.peek();// peek是当前栈顶元素，检索但不删除此列表的头部,因为要通过父节点访问左右子树
        //		// 检索左子树，栈顶节点的左子树不空，当前节点的左右子树不是上一次访问的节点，避免重复访问
        //		// 因为后续遍历是左右中的顺序，所以在遍历左子树的时候，要确定该节点的左右子树没有访问过。
        //		// 左子树是空，无法访问该节点的左子树了，左子树已经访问过了，避免重复访问，右子树已经访问过了，根据逻辑那么左子树肯定访问过了
        //		if (peek.left != null && peek.left != pre && peek.right != pre) {
        //			stack.push(peek.left);
        //		} else if (peek.right != null && peek.right != pre) {// 栈顶元素的右子树不空，栈顶元素的右子树不是上一次访问的节点，避免重复访问
        //			stack.push(peek.right);
        //		}else {// 该节点的左右子树为空【叶子节点】或者中间节点的左右子树已经访问过了，也就是该节点的左右子树已经访问过了，返回上一侧，处理父节点
        //			node=stack.pop();// 栈顶元素就是当前节点，处理完之后就是上一个已经处理过的节点
        //			lists.add(node.val);//处理当前节点的措施;
        //			pre=node;// 记录上一个访问过的节点pre
        //		}
        //	}
        //	return lists;
        //}


        // 解法1：迭代 写法1
        // step1:开辟一个辅助栈，用于记录要访问的子节点，创建一个pre指针表示上一个访问过的节点.
        // step2:从根节点开始，每次优先进入每棵的子树的最左边一个节点，我们将其不断加入栈中，用来保存父问题。
        // step3:弹出一个栈元素，看成该子树的根，判断这个根的右边有没有节点或是有没有被访问过，
        //如果没有右节点或是被访问过了，可以访问这个根，并将前序节点标记为这个根。
        // step4:如果没有被访问，那这个根必须入栈，进入右子树继续访问，只有右子树结束了回到这里才能继续访问根。

        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> lists = new ArrayList<>();//添加遍历结果的数组
            if (root == null) {
                return lists;
            }
            Deque<TreeNode> stack = new LinkedList<>();
            TreeNode prev = null;// 记录上一个访问过的节点
            TreeNode node = root;
            while (node != null || !stack.isEmpty()) {
                while (node != null) {//通过遍历每次先找到子树最左边的节点
                    stack.push(node);//当前节点入栈，保留左子树的父节点信息
                    node = node.left;// 访问左子树
                }
                // 上面循环结束时，node最终指向刚开始时以node为根节点的左子树的最左侧叶子节点的左指针域【最左侧叶子节点的左指针域其实是空指针】
                // 因为后续遍历访问 右节点在父节点之前，所以在访问父节点之前要判断当前节点的右子树没有访问
                node = stack.pop();//弹出栈顶元素，
                // 判断以该节点为根节点的右子树是否为空或者访问过
                //如果该元素的右边不为空并且没有访问过，那么就要访问该节点的右子树，则该节点要入栈，保留右子树父节点的信息
                if(node.right==null||node.right==prev){// 当前节点没有右子树或者当前节点的右子树已经访问过了
                    // 因为右子树为空或者已经访问过了，那么这时候访问完当前节点就要回去访问当前节点的父节点，所以最后的遍历指针node要置空。
                    lists.add(node.val);//处理当前节点的措施;
                    prev = node;// 记录上一个访问过的节点pre
                    node=null;// 注意这里一定要有将node置空，这样内层循环while(node!=null)不会执行，程序继续执行才会访问node节点的父节点。
                }else {// 当前节点的右子节点非空并且没有被访问过
                    stack.push(node);//如果没有被访问，那这个右子节点的父节点必须入栈，因为访问过右节点之后要返回访问根节点，所以要保留根节点
                    node= node.right;//访问右边
                }
            }
            return lists;
        }




	/*
	// 递归三种不同的写法参考前序遍历的三种
	// 方法一：递归 写法1

    public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res=new ArrayList<>();//添加遍历结果的数组
		postOrderRecur(root,res);//递归后序遍历
		return res;
    }


	public static void postOrderRecur(TreeNode node,List<Integer> res){
		if(node==null){ //遇到空节点则返回
			return;
		}
		postOrderRecur(node.left,res);//先去左⼦树
		postOrderRecur(node.right,res);//再去右⼦树
		res.add(node.val);//最后访问根节点
	}

	*/




		/*

	// 方法一：递归 写法2
	List<Integer> lists = new ArrayList<>();//添加遍历结果的数组
	public List<Integer> postorderTraversal(TreeNode root) {
		if (root == null) {
			return lists;
		}
		postorderTraversal(root.left);
		postorderTraversal(root.right);
		lists.add(root.val);
		return lists;
	}

	*/


        // 方法二：迭代  借助双栈实现

/*
public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> res=new ArrayList<>();
		if(root==null){
			return res;
		}
		Deque<TreeNode> stack1=new LinkedList<>();
		Deque<TreeNode> stack2=new LinkedList<>();
		stack1.push(root);
		//
		while(!stack1.isEmpty()){
			TreeNode node=stack1.pop();
			stack2.push(node);
			if(node.left!=null){
				stack1.push(node.left);
			}
			if(node.right!=null){
				stack1.push(node.right);
			}
		}
		while(!stack2.isEmpty()){
			res.add(stack2.pop().val);
		}
		return res;
	}
	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}

