package template;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreorder {

    // 二叉树的前序遍历，即先遍历二叉树的根节点，再遍历二叉树的左子树，最后遍历二叉树的右子树。

    // 写法1：二叉树的前序遍历  递归解法   从上到下遍历
    public static void preOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val + "");//处理当前节点，也就是对当前节点的语句就在这个位置写
        preOrderRecur(node.left);//递归遍历左子树 。处理当前节点的左子节点。处理左子树
        preOrderRecur(node.right);//递归遍历右子树。处理当前节点的右子节点。处理右子树
    }



    // 写法2：二叉树的前序遍历  递归解法
    public List<Integer> preOrderTraversalRecur(TreeNode root){
        List<Integer> lists =new ArrayList<>();
        if(root==null){
            return lists;
        }
        preorderDfs(root, lists);
        return lists;
    }

    private void preorderDfs(TreeNode root, List<Integer> lists){
        if(root==null){
            return;
        }
        lists.add(root.val);
        preorderDfs(root.left,lists);
        preorderDfs(root.right,lists);
    }




    // 写法3： 二叉树的前序遍历 迭代写法1    统一记这一个
    // 从下到上扫描，从下到上每次形成一个由父节点，左子节点，右子节点构成的树结构。
    // 统一记忆这一个，这样就可以把3种遍历的迭代方式统一起来。
    // 建议统一记忆这一个迭代遍历，统一起来。从根节点开始，向左遍历找到最左侧的一个节点，然后再左中右，或者中左右，左右中来遍历。
    // 二叉树的前序遍历  迭代解法
    // 二叉树的前序遍历迭代解法的思路：前序遍历：访问二叉树节点顺序 根节点->左子树->右子树。

    public static void preOrderIter(TreeNode root) {
        if (root == null) {// 判空
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();//创建ArrayLists对象stack来模拟栈
        TreeNode node = root;//辅助遍历二叉树的指针node
        while (!stack.isEmpty() || node != null) {//栈不空或者当前节点不是空节点
            // 前序遍历，根节点->左子树->右子树，迭代执行，内层while是遍历完整的左子树
            // 当前节点node不空时，通过循环将每个节点当做父节点进行数据处理，然后不断将该节点【后续访问有节点，需要有根节点】入栈，并访问该节点的左子树。
            while (node != null) {
                System.out.println(node.val);// 处理当前节点【也就是父节点】，有关处理当前节点的方法就可以写在这里
                stack.push(node);//将当前节点压入栈，需要保留父节点这样才能方便遍历左子树和右子树
                node = node.left;//更新指针指向当前栈的左子树，遍历左子树
            }
            // 上面循环结束时，node最终指向刚开始时以node为根节点的左子树的最左侧叶子节点的左指针域【最左侧叶子节点的左指针域其实是空指针】
            node = stack.pop();//弹出栈顶元素，其实就是返回空指针的上一层的根节点。其实就是上面循环结束时空指针的父节点。
            node = node.right;// 通过当前节点【根节点】去访问当前节点右子树。
        }
    }



    // 写法5：
    public List<Integer> preorderTraversalIter(TreeNode root){
        List<Integer> lists=new ArrayList<>();
        if(root==null){
            return lists;
        }
        Deque<TreeNode> stack=new LinkedList<>();
        TreeNode node=root;
        while (node!=null||!stack.isEmpty()){
            while (node!=null){
                lists.add(node.val);
                stack.push(node);
                node=node.left;
            }
            node=stack.pop();
            node=node.right;
        }
        return lists;
    }





    // 写法4：二叉树的前序遍历 迭代写法2
    // 因为每次都先是根节点，所以可以从上到下扫描，从上到下每次形成一个由父节点，左子节点，右子节点构成的树结构。
    // 那么需要从栈中弹出父节点，由父节点可以得到左右子节点。所以首先压入父节点。栈是先进后出。

    public static void preOrderIter2(TreeNode root) {
        if (root == null) {// 判空
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();//模拟栈
        stack.push(root);// 首先压入父节点
        // 需要从栈中弹出父节点，由父节点可以得到左右子节点。所以首先压入父节点。栈是先进后出
        // 入栈顺序是：右节点->左节点，出栈顺序是：左节点->右节点
        while (!stack.isEmpty()) {//while循环其实就是通过迭代来实现递归调用那个过程
            // 处理当前节点
            TreeNode node = stack.pop();// 弹出当前节点【也就是父节点，任何一个节点都可以看做其子树的父节点】。node就是辅助节点帮助遍历二叉树,通过父节点去访问左右子节点
            System.out.println(node.val);// 处理当前节点【也就是父节点】，有关处理当前节点的方法就可以写在这里

            // 当前节点不空，处理当前节点的右节点
            if (node.right != null) {
                stack.push(node.right);
            }
            // 当前节点不空，处理当前节点的左节点
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }



}
