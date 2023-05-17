/**
<p>请实现两个函数，分别用来序列化和反序列化二叉树。</p>

<p>你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。</p>

<p><strong>提示：</strong>输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅&nbsp;<a href="https://support.leetcode-cn.com/hc/kb/article/1567641/">LeetCode 序列化二叉树的格式</a>。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg" style="width: 442px; height: 324px;" />
<pre>
<strong>输入：</strong>root = [1,2,3,null,null,4,5]
<strong>输出：</strong>[1,2,3,null,null,4,5]
</pre>

<p>&nbsp;</p>

<p>注意：本题与主站 297 题相同：<a href="https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/">https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/</a></p>
<div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>设计</li><li>字符串</li><li>二叉树</li></div></div><br><div><li>👍 350</li><li>👎 0</li></div>
*/

package leetcode.editor.cn;

import java.util.*;

/**
 * 剑指 Offer 37
 * 序列化二叉树
 * @author wangweizhou
 * @date 2022-09-28 22:28:13
 */
public class XuLieHuaErChaShuLcof{
	 public static void main(String[] args) {
	 	 //测试代码
         Codec solution = new XuLieHuaErChaShuLcof().new Codec();
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // 题目要求的序列化和反序列化是可逆操作。因此，序列化的字符串应携带完整的二叉树信息。
    // 为完整表示二叉树，考虑将叶节点下的 null 也记录。在此基础上，对于列表中任意某节点 node ，其左子节点 node.left 和右子节点 node.right 在序列中的位置都是唯一确定的。

    // Encodes a tree to a single string.
    // 类似层序遍历
    public String serialize(TreeNode root) {
        if(root == null) {// 若 root 为空，则直接返回空列表 "[]" ；
            return "[]";
        }
        StringBuilder res = new StringBuilder("[");// 可变字符串记录序列化结果
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();// 节点出队，记为 node ；
            if(node != null) {// 若 node 不为空
                res.append(node.val + ",");//  打印字符串 node.val
                queue.add(node.left);// 将左、右子节点加入 queue ；
                queue.add(node.right);
            } else {// （若 node 为空）：打印字符串 "null" ；
                res.append("null,");
            }
        }
        // 当 queue 为空时跳出；
        res.deleteCharAt(res.length() - 1);// 删除最后一个逗号
        res.append("]");// 末尾添加中括号
        return res.toString();
    }



    // Decodes your encoded data to tree.
    // 反序列化，层序遍历，根左右
    public TreeNode deserialize(String data) {
        if(data.equals("[]")) {// 空树
            return null;
        }
        // 字符串去掉首尾两端的中括号，然后再用“，”分割
        String[] vals = data.substring(1, data.length() - 1).split(",");
        // 执行到这里字符串数组中只有节点的数据域和“null”
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));// 创建根节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;// 字符串数组vals的遍历指针
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();// 节点出队，记为 node ；
            if(!vals[i].equals("null")) {// 节点不空，创建当前节点的左子节点，将左子节点入队
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;

            if(!vals[i].equals("null")) {// 节点不空，创建当前节点的右子节点，将右子节点入队
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }



}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)

}
