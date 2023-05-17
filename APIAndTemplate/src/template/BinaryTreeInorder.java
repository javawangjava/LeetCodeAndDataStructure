package template;

import java.util.*;

public class BinaryTreeInorder {


    // 二叉树的中序遍历：如果按照中序遍历的顺序，则先遍历二叉树的左子树，然后遍历二叉树的根节点，最后遍历二叉树的右子树。
    // 写法1：二叉树的中序遍历  递归解法   从下到上
    public static void inOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        inOrderRecur(node.left);//处理左子树
        System.out.println(node.val + "");// 处理当前节点
        inOrderRecur(node.right);//处理右子树
    }



    // 写法2：二叉树的中序遍历的递归代码
    // 因此定义了一个递归函数dfs（dfs是Depth First Search的缩写，即深度优先搜索）。
    // 这个函数按照中序遍历的定义，如果输入的二叉树的根节点不为空，则先遍历它的左子树，然后遍历根节点，最后遍历右子树。

    public List<Integer> inorderTraversalRecur(TreeNode root) {
        List<Integer> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        inorderDfs(root, lists);
        return lists;
    }

    private void inorderDfs(TreeNode root, List<Integer> lists) {
        if (root == null) {
            return;
        }
        inorderDfs(root.left, lists);
        lists.add(root.val);
        inorderDfs(root.right, lists);
    }




    // 二叉树的中序遍历  迭代写法
    // 二叉树的中序遍历迭代解法的思路：节点访问顺序：左子树->父节点->右子树。【最简单的只有两层的满二叉树来理解】。
    // 既然要先访问左子树，那就要将父节点压入栈，然后将父节点的左子节点压入栈。

    // 把递归代码改写成迭代的代码通常需要用到栈，改写中序遍历的代码也不例外。二叉树的遍历总是从根节点开始的，但当第1次到达根
    // 节点时，并不是马上遍历根节点，而是顺着指向左子节点的指针向下直到叶节点，也就是找到第1个真正被遍历的节点。
    // 为了在一个节点被遍历之后能够接着回去遍历它的父节点，可以在顺着指向左子节点的指针遍历二叉树时把遇到的每个节点都添加到一个栈中。
    // 当一个节点被遍历了之后，就可以从栈中得到它的父节点。
    // 变量cur表示当前遍历的节点。如果该节点有左子节点，按照中序遍历的顺序，应该先遍历它的左子树。
    // 于是顺着指向左子节点的指针一直向下移动，并将沿途遇到的每个节点都添加到栈stack之中。
    // 第2个while循环结束之后，最左子节点（顺着指向左子节点的指针到达的最远的节点）位于栈顶，将它从栈顶出栈并遍历。
    // 按照中序遍历的顺序，在遍历一个节点之后再遍历它的右子树，因此把变量cur指向它的右子节点，开始下一轮的遍历，直到所有节点都遍历完为止。


    // 最简单的【只有两层的】满二叉树来顺序：
    // 1.将当前节点压入栈，通过循环将当前节点的左子节点压入栈。那么栈顶元素就是当前子树最左的一个节点，
    // 【尽可能的将这个节点的左子树压入Stack，此时栈顶的元素是最左侧的元素，其目的是找到一个最小单位的子树(也就是最左侧的一个节点)，
    //  并且在寻找的过程中记录了来源，才能返回上层,同时在返回上层的时候已经处理完毕左子树了。】
    // 2.从栈中第一次弹出栈顶元素【其实就是最小单位的左子树【最小单位的树就是一个节点】，也就是最左侧的一个节点】，对栈顶元素进行处理。
    // 3.从栈中第二次弹出栈顶元素【其实就是最小单位的左子树的父节点，】，对栈顶元素进行处理。
    // 【当处理完最小单位的子树时，返回到上层处理了中间节点。（如果把整个左中右的遍历都理解成子树的话，就是处理完 左子树->中间(就是一个节点)->右子树）】
    // 4.当弹出父节点后判断父节点的右子节点【右子树】是否为空，遍历压入右子节点。
    // 2-4就是左中右顺序。
    // 5.重复2-4就可以完成对子树的遍历。


    // 写法3： 二叉树的中序遍历  迭代写法是从下到上遍历，
    public static void inOrderIter(TreeNode root) {
        if (root == null) {// 终止的条件为碰到空节点。
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();//创建ArrayLists对象stack来模拟栈
        TreeNode node = root;//
        // 外层while循环就是迭代遍历以curr为父节点的子树的
        while (!stack.isEmpty() || node != null) {
            // 因为中序遍历都是以每个节点左子节点优先访问，所以在循环遍历时将当前节点不断入栈，并不断访问该节点的左子树
            // 内层while循环就是通过循环将以node为根节点的左子树的最左的一个节点压入栈。【这是一个循环过程】
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            // 上面循环结束时，node最终指向刚开始时以node为根节点的左子树的最左侧叶子节点的左指针域【最左侧叶子节点的左指针域其实是空指针】
            node = stack.pop();//弹出栈顶元素，其实就是返回上一层的根节点。其实就是上面循环结束时空指针的父节点。
            System.out.println(node.val + "");// 处理当前节点
            node = node.right;// 通过当前节点【根节点】去访问当前节点右子树。
        }
    }




    // 写法4： 二叉树的中序遍历 加入到集合中
    public List<Integer> inorderTraversalIter(TreeNode root) {
        List<Integer> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            lists.add(node.val);
            node = node.right;
        }
        return lists;
    }


}
