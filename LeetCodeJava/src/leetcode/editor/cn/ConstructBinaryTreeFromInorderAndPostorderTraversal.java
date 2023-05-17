/**
 * <p>给定两个整数数组 <code>inorder</code> 和 <code>postorder</code> ，其中 <code>inorder</code> 是二叉树的中序遍历，
 * <code>postorder</code> 是同一棵树的后序遍历，请你构造并返回这颗&nbsp;<em>二叉树</em>&nbsp;。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1:</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" />
 * <pre>
 * <b>输入：</b>inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * <b>输出：</b>[3,9,20,null,null,15,7]
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <b>输入：</b>inorder = [-1], postorder = [-1]
 * <b>输出：</b>[-1]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= inorder.length &lt;= 3000</code></li>
 * <li><code>postorder.length == inorder.length</code></li>
 * <li><code>-3000 &lt;= inorder[i], postorder[i] &lt;= 3000</code></li>
 * <li><code>inorder</code>&nbsp;和&nbsp;<code>postorder</code>&nbsp;都由 <strong>不同</strong> 的值组成</li>
 * <li><code>postorder</code>&nbsp;中每一个值都在&nbsp;<code>inorder</code>&nbsp;中</li>
 * <li><code>inorder</code>&nbsp;<strong>保证</strong>是树的中序遍历</li>
 * <li><code>postorder</code>&nbsp;<strong>保证</strong>是树的后序遍历</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>数组</li><li>哈希表</li><li>分治</li><li>二叉树</li></div></div><br><div
 * ><li>👍 789</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 106
 * 从中序与后序遍历序列构造二叉树
 *
 * @author wangweizhou
 * @date 2022-07-11 10:08:21
 */

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        int[] inorder = {4, 2, 8, 5, 9, 1, 6, 10, 3, 7};
        int[] postorder = {4, 8, 9, 5, 2, 10, 6, 7, 3, 1};
        TreeNode root = solution.buildTree(inorder, postorder);

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

        // 中序遍历：左根右；后续遍历：左右根。
        //在后序遍历序列中,最后一个元素为树的根节点
        //在中序遍历序列中,根节点的左边为左子树，根节点的右边为右子树

        //树的还原过程描述
        //根据中序遍历和后续遍历的特性我们进行树的还原过程分析
        //1.首先在后序遍历序列中找到根节点(最后一个元素)，根据根节点建立二叉树的根节点；
        //2.在中序遍历中找到根节点在中序遍历数组中的位置；通过中序遍历知道左右子树的节点个数【中序遍历根节点左侧就是左子树的节点，中序遍历根节点右侧就是右子树的节点】，
        // 然后再根据左右子树的节点个数，将后序遍历数组分割成根节点和左右子树。
        //3.根据根节点的位置将中序遍历序列分为左子树和右子树；
        //4.根据根节点的位置确定左子树和右子树在中序数组和后续数组中的左右边界位置；
        //5.递归构造左子树和右子树；
        //6.返回根节点结束。


        // 若根节点在中序遍历数组中的位置inRoot，利用该位置inRoot将中序遍历数组和后序遍历数组分成左右两部分。
        // 中序遍历的左右子树[inLeft,inRoot-1],【inRoot】,[inRoot+1,inRight]
        // 后序遍历的左右子树[postLeft,postLeft+leftTreeSize-1],[postLeft+leftTreeSize,postRight-1]，【postRight】


        // [left,right]区间的长度：len=right-left+1; right=len-1+left;
        // 解法2：哈希表+中序遍历确定根节点位置分割左右子树重建子树
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if (postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0 || postorder
                    .length != inorder.length) { // 判空
                return null;
            }
            int len = inorder.length;// 中序和后序的长度一样
            // 利用哈希表快速确定中序遍历中根节点的位置，进而确定二叉树的左子树和右子树中节点的个数
            // 构造中序遍历的哈希映射：哈希表中键key是中序遍历数组中的元素，值value是该元素在中序遍历数组中对应的下标
            Map<Integer, Integer> inorderMap = new HashMap<>();
            for (int i = 0; i < len; i++) {
                inorderMap.put(inorder[i], i);
            }
            return buildTreeFunc(inorder, 0, len - 1, postorder, 0, len - 1, inorderMap);
        }



        // 利用后序和中序递归构造二叉树，设计的是双闭区间，前后对应即可
        private TreeNode buildTreeFunc(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft,
                                       int postRight, Map<Integer, Integer> inorderMap) {
            if (postLeft > postRight) {// 区间中没有元素
                return null;
            }
            int rootVal = postorder[postRight];// 后序遍历中的最后一个节点就是根节点
            TreeNode root = new TreeNode(rootVal);// 以后序遍历中的最后一个节点建立根节点
            int rootInoderIndex = inorderMap.get(rootVal);// 在中序遍历中定位根节点,获取根节点在中序遍历中的下标
            // 从中序遍历中得到左子树的节点数目，Root为父节点的左子树【inLeft，inRoot-1】，
            int leftLen = rootInoderIndex - inLeft;// [inLeft,inRoot)左子树不包括inRoot，所以这里没有+1

            // 递归地构造左子树，并连接到根节点。起始位置数学归纳法
            // 递归地构造右子树，并连接到根节点
            // 以中序遍历的根节点的位置inRoot将中序数组和后序数组分成左右两部分
            // 中序遍历的左右子树[inLeft,inRoot-1],【inRoot】,[inRoot+1,inRight]
            // 后序遍历的左右子树[postLeft,postLeft+leftTreeSize-1],[postLeft+leftTreeSize,postRight-1]，【postRight】

            // [left,right]区间的长度：len=right-left+1; right=len-1+left;

            root.left = buildTreeFunc(inorder, inLeft, rootInoderIndex - 1, postorder, postLeft, postLeft + leftLen - 1,
                    inorderMap);// (postorder的子区间的右边界：leftLen-1+postLeft；
            root.right = buildTreeFunc(inorder, rootInoderIndex + 1, inRight, postorder, postLeft + leftLen,
                    postRight - 1,
                    inorderMap);
            return root;
        }




        //// 解法1： 哈希表+中序遍历确定根节点位置分割左右子树重建子树
        //Map<Integer, Integer> inorderMap = new HashMap<>();
        //public TreeNode buildTree(int[] inorder, int[] postorder) {
        //    // 判空
        //    if (postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0 || postorder
        //    .length != inorder.length) {
        //        return null;
        //    }
        //    int len = inorder.length;// 中序和后序的长度一样
        //    // 构造哈希映射，键表示一个元素（节点的值），值表示其在中序遍历中的出现位置。
        //    for (int i = 0; i < len; i++) {
        //        inorderMap.put(inorder[i], i);
        //    }
        //    return myBuildTree(inorder, 0, len - 1, postorder, 0, len - 1);
        //}
        //
        //
        //// 利用后序和中序递归构造二叉树，设计的是双闭区间，前后对应即可
        //private TreeNode myBuildTree(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft,
        //                             int postRight) {
        //    if (postLeft > postRight || inLeft > inRight) {
        //        //if(inLeft>inRight){// 上下两个都可以
        //        return null;
        //    }
        //    int rootVal = postorder[postRight];// 后序遍历中的最后一个节点就是根节点
        //    TreeNode root = new TreeNode(rootVal);// 以后序遍历中的最后一个节点建立根节点
        //    int rootInorderIndex = inorderMap.get(rootVal);// 在中序遍历中定位根节点,获取根节点在中序遍历中的下标
        //    // 从中序遍历中得到左子树的节点数目，Root为父节点的左子树【inLeft，inRoot-1】，
        //    int leftLen = rootInorderIndex - inLeft;// [inLeft,inRoot)左子树不包括inRoot，所以这里没有+1
        //
        //    // 递归地构造左子树，并连接到根节点。起始位置数学归纳法
        //    // 递归地构造右子树，并连接到根节点
        //    // 以中序遍历的根节点的位置inRoot将中序数组和后序数组分成左右两部分
        //    // 中序遍历的左右子树[inLeft,inRoot-1],【inRoot】,[inRoot+1,inRight]
        //    // 后序遍历的左右子树[postLeft,postLeft+leftTreeSize-1],[postLeft+leftTreeSize,postRight-1]，【postRight】
        //    // [left,right]区间的长度：len=right-left+1; right=len-1+left;
        //    root.left = myBuildTree(inorder, inLeft, rootInorderIndex - 1, postorder, postLeft, postLeft + leftLen
        //    - 1);// (postorder的子区间的右边界：leftLen-1+postLeft；
        //    root.right = myBuildTree(inorder, rootInorderIndex + 1, inRight, postorder, postLeft + leftLen,
        //            postRight - 1);
        //    return root;
        //}





	/*
	// 解法1：写法2   不如第一种写法明了
	int[] inorder;// 中序遍历数组
	int[] postorder;// 后续遍历数组
	int postIndex;// 在 postorder 中的 index
	// 哈希表来保存中序遍历序列中,元素和索引的位置关系，键key保存中序遍历数组的值，值value保存中序遍历数组的下标
	HashMap<Integer, Integer> inOrderIndex = new HashMap<>();

	public TreeNode buildTree(int[] inorder, int[] postorder) {
		this.inorder = inorder;
		this.postorder = postorder;
		// 建立（元素，下标）键值对的哈希表
		for (int i = 0; i < inorder.length; i++) {
			inOrderIndex.put(inorder[i], i);// 将中序遍历数组元素值和下标保存在map中
		}
		// 选择后序遍历的最后一个节点作为根节点
		postIndex = postorder.length - 1;// 后序遍历数组下标，因为后续遍历数组的最后一个下标是树的根节点
		return buildTreeHelper(0, postorder.length - 1);
	}


	// 递归建立子树
	private TreeNode buildTreeHelper(int left, int right) {     // left, right：在 inorder 中的 index
		if (left > right) {// 没有节点来构造二叉树了，结束。
			return null;
		}

		int postRootVal = postorder[postIndex--];//选择postRootVal位置的元素作为当前子树根节点
		TreeNode root = new TreeNode(postRootVal);

		//根据root所在位置分成左右两颗子树
		int inorderRootIndex = inOrderIndex.get(postRootVal);
		// 因为采用了 postIndex 每次减一的方法，所以必须先递归生成右子树再递归生成做子树！！！
		root.right = buildTreeHelper(inorderRootIndex + 1, right);// 递归生成右子树
		root.left = buildTreeHelper(left, inorderRootIndex - 1);// 递归生成左子树
		return root;
	}
	*/





	/*
	// 解法1：写法3   不如第一种写法明了
	Map<Integer,Integer> inorderMap=new HashMap<>();
	int[] post;// 定义全局变量，保存后续遍历数组

    public TreeNode buildTree(int[] inorder, int[] postorder) {
		// 判空
		if(postorder==null||inorder==null||postorder.length==0||inorder.length==0||postorder.length!=inorder.length){
			return null;
		}
		for (int i = 0; i < inorder.length; i++) {
			inorderMap.put(inorder[i],i);//将中序遍历的节点值和索引值全部记录在哈希表中
		}
		post=postorder;
		TreeNode root=buildTree(0,inorder.length-1,0,post.length-1);
		return root;
    }



	public TreeNode buildTree(int inLeft, int inRight, int postLeft, int postRight){
		if(inRight < inLeft || postRight < postLeft){
			return null;
		}
		int postRootVal =post[postRight];//根据后序遍历结果，取得根节点
		TreeNode root =new TreeNode(postRootVal);// 以根节点建立二叉树
		int inorderRootIndex =inorderMap.get(postRootVal);//通过中序遍历取得后续遍历的下标，将中序遍历一分为二

		root.left=buildTree(inLeft, inorderRootIndex -1, postLeft, postLeft + inorderRootIndex - inLeft -1);
		root.right=buildTree(inorderRootIndex +1, inRight, postLeft + inorderRootIndex - inLeft,
				postRight -1);
		return root;//注意！返回的是新建的node！
	}
	*/


    }
//leetcode submit region end(Prohibit modification and deletion)

}


//class TreeNode {
//	int val;
//	TreeNode left;
//	TreeNode right;
//
//	TreeNode() {
//	}
//
//	TreeNode(int val) {
//		this.val = val;
//	}
//
//	TreeNode(int val, TreeNode left, TreeNode right) {
//		this.val = val;
//		this.left = left;
//		this.right = right;
//	}
//}