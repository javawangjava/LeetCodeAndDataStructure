package template;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePostorder {

    // 在后序遍历中，先遍历左子树，再遍历右子树，最后遍历根节点。
    // 写法1：二叉树的后序遍历  递归解法   从下到上
    public static void postOrderRecur(TreeNode node) {
        if (node == null) {
            return;
        }
        postOrderRecur(node.left);//处理左子树
        postOrderRecur(node.right);//处理右子树
        System.out.println(node.val + "");// 处理当前节点
    }



    // 写法2
    public List<Integer> postorderTraversalRecur(TreeNode root){
        List<Integer> lists=new ArrayList<>();
        if(root==null){
            return lists;
        }
        postorderDfs(root,lists);
        return lists;
    }


    private void postorderDfs(TreeNode root,List<Integer> lists){
        if(root==null){
            return;
        }
        postorderDfs(root.left,lists);
        postorderDfs(root.right,lists);
        lists.add(root.val);
    }



    // 二叉树的后序遍历  迭代解法：
    // 树一般都是由父节点来标识的。但是访问顺序是：左子树->右子树->父节点。所以只能在进入迭代之后再压栈出栈。
    // 节点访问顺序：左子树->右子树->父节点。【最简单的只有两层的满二叉树来理解】。

    // 二叉树的后序遍历  迭代写法3

    // 解法1：迭代 写法1
    // step1:开辟一个辅助栈，用于记录要访问的子节点，创建一个pre指针表示上一个访问过的节点.
    // step2:从根节点开始，每次优先进入每棵的子树的最左边一个节点，我们将其不断加入栈中，用来保存父问题。
    // step3:弹出一个栈元素，看成该子树的根，判断这个根的右边有没有节点或是有没有被访问过，如果没有右节点或是被访问过了，可以访问这个根，并将前序节点标记为这个根。
    // step4:如果没有被访问，那这个根必须入栈，进入右子树继续访问，只有右子树结束了回到这里才能继续访问根。

    public static void postOrderIter(TreeNode root) {
        List<Integer> lists=new ArrayList<>();
        if(root==null){
            return;
        }
        Deque<TreeNode> stack=new LinkedList<>();
        TreeNode prev =null;// 记录上一个访问过的节点
        TreeNode node=root;
        while(node!=null||!stack.isEmpty()){
            while(node!=null){//通过遍历每次先找到子树最左边的节点
                stack.push(node);//当前节点入栈，保留左子树的父节点信息
                node=node.left;// 访问左子树
            }
            // 上面循环结束时，node最终指向刚开始时以node为根节点的左子树的最左侧叶子节点的左指针域【最左侧叶子节点的左指针域其实是空指针】
            // 因为后续遍历访问 右节点在父节点之前，所以在访问父节点之前要判断当前节点的右子树没有访问
            node =stack.pop();//弹出栈顶元素，注意这里是访问栈顶元素并弹出还是只是访问栈顶元素但是并没有弹出
            // 判断以该节点为根节点的右子树是否为空或者访问过
            //如果该元素的右子树不为空并且没有访问过，那么就要访问该节点的右子树，则该节点要入栈，保留右子树父节点的信息

            if(node.right==null||node.right==prev){// 当前节点没有右子树或者当前节点的右子树已经访问过了
                // 因为右子树为空或者已经访问过了，那么这时候访问完当前节点就要回去访问当前节点的父节点，所以最后的遍历指针node要置空。
                lists.add(node.val);//处理当前节点的措施;
                prev = node;// 记录上一个访问过的节点pre
                node=null;// 注意这里一定要有将node置空，这样内层循环while(node!=null)不会执行，程序继续执行才会访问node节点的父节点。
            }else {// 当前节点的右子节点非空并且没有被访问过
                stack.push(node);//如果没有被访问，那这个右子节点的父节点必须入栈，因为访问过右节点之后要返回访问根节点，所以要保留根节点
                node= node.right;//访问右边
            }

            // 下面这种写法没有上面的写法看起来更容易理解
            //if(node.right!=null&& node.right!= prev){// 当前节点的右子节点非空并且没有被访问过
            //    stack.push(node);//如果没有被访问，那这个右子节点的父节点必须入栈，进入右子树继续访问，只有右子树结束了回到这里才能继续访问根。
            //    node= node.right;//访问右边
            //}else{//如果该元素没有右子树或是右子树已经访问过。temp.right==null|| temp.right==pre
            //    lists.add(node.val);//处理当前节点的措施;
            //    prev = node;// 记录上一个访问过的节点pre
            //    node=null;// 注意这里一定要有将node置空，这样内层循环while(node!=null)不会执行，程序继续执行才会访问node节点的父节点。
            //}
        }
    }




    // 和中序遍历、前序遍历相比，后序遍历的迭代代码要稍微复杂一点。
    // 当达到某个节点时，如果之前还没有遍历过它的右子树就得前往它的右子节点，如果之前已经遍历过它的右子树那么就可以遍历这个节点。
    // 也就是说，此时要根据它的右子树此前有没有遍历过来确定是否应该遍历当前的节点。
    // 如果此前右子树已经遍历过，那么在右子树中最后一个遍历的节点应该是右子树的根节点，也就是当前节点的右子节点。
    // 可以记录遍历的前一个节点。如果一个节点存在右子节点并且右子节点正好是前一个被遍历的节点，那么它的右子树已经遍历过，现在是时候遍历当前的节点了。
    // 变量prev就是遍历过的前一个节点，它初始化为null。在准备遍历下一个节点之前，就把它指向当前遍历的节点。
    // 变量cur表示当前到达的节点。如果该节点有右子节点并且右子节点不是前一个遍历的节点，则表示它有右子树并且右子树还没有遍历过，
    // 按照后序遍历的顺序，应该先遍历它的右子树，因此把变量cur指向它的右子节点。
    // 如果变量cur指向的节点没有右子树或它的右子树已经遍历过，则按照后序遍历的顺序此时可以遍历这个节点，于是把它出栈并遍历它。
    // 接下来准备遍历下一个节点，于是把变量prev指向这个节点。
    // 下一个遍历的节点一定是它的父节点，而父节点之前已经存放到栈中，所以需要将变量cur重置为null，这样下一次就可以将它的父节点出栈并遍历。


    // // 二叉树的后序遍历  迭代写法4 注意这个上面的区别
    public List<Integer> postorderTraversalIter(TreeNode root){
        List<Integer> lists=new ArrayList<>();
        if(root==null){
            return lists;
        }
        Deque<TreeNode> stack=new LinkedList<>();
        TreeNode node=root;
        TreeNode prev=null;
        while (node!=null||!stack.isEmpty()){
            while (node!=null){
                stack.push(node);
                node=node.left;
            }
            node=stack.peek();// 注意这里peek（）只是访问栈顶元素，但是不会从栈中删除栈顶元素
            if(node.right==null||node.right==prev){// 当前节点没有右子树或者当前节点的右子树已经访问过了
                // 因为右子树为空或者已经访问过了，那么这时候访问完当前节点就要回去访问当前节点的父节点，所以最后的遍历指针node要置空。
                node=stack.pop();
                lists.add(node.val);//处理当前节点的措施;
                prev = node;// 记录上一个访问过的节点pre,访问过的节点必须是处理过节点数据的节点
                node=null;// 注意这里一定要有将node置空，这样内层循环while(node!=null)不会执行，程序继续执行才会访问node节点的父节点。
            }else {// 当前节点的右子节点非空并且没有被访问过
                node= node.right;// 访问右边，因为前面peek（）只是访问栈顶元素，但是不会从栈中删除栈顶元素，
                // 也就是当前节点node就在栈中，所以这里只需要转向右子节点就可以
            }

            //if(node.right!=null&&node.right!=prev){// 当前节点的右子树不空并且当前节点的右子树没有访问过
            //    node=node.right;// 转向右子树
            //}else {// 如果该元素没有右子树或是右子树已经访问过。node.right==null|| node.right==prev
            //    node=stack.pop();
            //    lists.add(node.val);
            //    prev=node;
            //    node=null;// 注意这里一定要有将node置空，这样内层循环while(node!=null)不会执行，程序继续执行才会访问node节点的父节点。
            //}
        }
        return lists;
    }





    // 二叉树的后序遍历  迭代解法2
    // 二叉树的后序遍历迭代解法的思路：

    // 这些顺序用最简单的只有两层的满二叉树来理解，要用栈来实现
    // 节点访问顺序：左子树->右子树->父节点。【最简单的只有两层的满二叉树来理解】。
    // 树一般都是由父节点来标识的。但是访问顺序是：左子树->右子树->父节点。所以只能在进入迭代之后再压栈出栈
    // 那么既然要先访问左子树，那就要将父节点压入栈，然后将父节点的左子节点压入栈。


    // 二叉树的后序遍历  迭代解法2  从上到下遍历
    // 1.用一个指针node标记当前退出的节点是什么。
    // 2.后序遍历的过程中在遍历完左子树跟右子树node都会回到根结点。所以当前不管是从左子树还是右子树回到根结点都不应该再操作了，应该退回上层。
    // 3.如果是从右边再返回根结点，应该回到上层。


    public static void postOrderIter2(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node=root;
        TreeNode pre=root;// pre记录上一个访问过的节点,注意这里的初始化为根节点
        stack.push(node);// 将根节点压入栈
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();// peek是当前栈顶元素，检索但不删除此列表的头部,因为要通过父节点访问左右子树
            // 检索左子树，栈顶节点的左子树不空，当前节点的左右子树不是上一次访问的节点，避免重复访问
            // 因为后续遍历是左右中的顺序，所以在遍历左子树的时候，要确定该节点的左右子树没有访问过。
            // 左子树是空，无法访问该节点的左子树了，左子树已经访问过了，避免重复访问，右子树已经访问过了，根据逻辑那么左子树肯定访问过了
            if (peek.left != null && peek.left != pre && peek.right != pre) {
                stack.push(peek.left);
            } else if (peek.right != null && peek.right != pre) {// 栈顶元素的右子树不空，栈顶元素的右子树不是上一次访问的节点，避免重复访问
                stack.push(peek.right);
            }else {// 该节点的左右子树为空【叶子节点】或者中间节点的左右子树已经访问过了，也就是该节点的左右子树已经访问过了，返回上一侧，处理父节点
                node=stack.pop();// 栈顶元素就是当前节点，处理完之后就是上一个已经处理过的节点
                System.out.print(node.val + " ");//处理当前节点的措施;
                pre=node;// 记录上一个访问过的节点pre
            }
        }
    }




    // 二叉树的后序遍历  迭代解法3   是借助第二个栈完成的，
    // 1.前序遍历的过程是 中左右。
    // 2.将前序遍历调整成中右左【调整前序遍历过程中左右子节点的压栈顺序，压栈顺序和弹栈顺序是相反的】。也就是压栈的过程中优先压入左子树，再压入右子树。
    // 3.然后再利用一个栈将中右左的顺序转换为左右中的顺序。将这个结果返回来，这里是利用栈的先进后出倒序打印

    public static void postOrderIter3(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> stack1 = new LinkedList<>();
        Deque<TreeNode> stack2 = new LinkedList<>();

        stack1.push(root); // 根节点先压入栈中
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();// 栈1弹出当前节点
            // 通过压栈和弹栈将中左右转换为中右左
            stack2.push(node);// 将栈1弹出的元素压入栈2，栈2压入的是栈1弹出的，那么最终栈2在最后弹出时只会与栈2压栈顺序相反。
            // 注意这里将前序遍历的的压栈顺序改变了。先压左子节点，再压右子节点
            if (node.left != null) {// 当前节点不空，处理当前节点的左节点
                stack1.push(node.left);
            }

            if (node.right != null) {// 当前节点不空，处理当前节点的右节点
                stack1.push(node.right);
            }
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }



}
