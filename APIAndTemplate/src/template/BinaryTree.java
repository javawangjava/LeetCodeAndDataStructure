package template;

import java.util.*;

public class BinaryTree {


    // 递归
    // 可以使用深度优先遍历的方式（本题前中后序都可以，无所谓，因为中节点也没有处理逻辑）来遍历二叉树
    //
    // 确定递归函数的参数和返回类型

    // 再来看返回值，递归函数什么时候需要返回值？什么时候不需要返回值？这里总结如下三点：
    // 1.如果需要搜索整棵二叉树且不用处理递归返回值，递归函数就不要返回值。（这种情况就是本文下半部分介绍的113.路径总和ii）
    // 2.如果需要搜索整棵二叉树且需要处理递归返回值，递归函数就需要返回值。 （这种情况我们在236. 二叉树的最近公共祖先中介绍）
    // 3.如果要搜索其中一条符合条件的路径，那么递归一定需要返回值，因为遇到符合条件的路径了就要及时返回。（本题的情况）



    // 在一棵非空的树中有一个根节点，这个节点下面可能有若干子节点，每个子节点下面还有其他的子节点。
    // 如果一个节点没有子节点，那么它就是一个叶节点。

    // 在二叉树中每个节点最多只有两个子节点，可以分别把它们称为左子节点和右子节点。
    // 二叉树的根节点没有父节点，一棵非空二叉树只有一个父节点【根节点】。二叉树的叶节点没有子节点。
    // 二叉树是一种典型的具有递归性质的数据结构。
    // 二叉树的根节点可能有子节点，子节点又是对应子树的根节点，它可能也有自己的子节点。这就类似于“子又生孙，孙又生子，子子孙孙无穷尽也”。


    // 二叉树的遍历分为两类，一类是深度优先遍历DFS：Depth First Search，一类是广度优先遍历BFS:。
    // 1.深度优先遍历 ：深度优先遍历有三种方式，前序（先根次序）、中序（中根次序）和后序（后根次序）遍历。
    // 2.广度遍历是按层次从上到下，从左到右的逐层访问。即我们平常所说的层次遍历。层序遍历也可以将从左向右调整为从右向左。

    // 标准的深度遍历不管是先序，中序，后序遍历，都是先遍历左子树，然后再遍历右子树。二叉树是有方向的，所以不需要标记已经访问过的节点。
    // 注意3种深度遍历的变形：可以变为先遍历右子树，然后再遍历左子树。
    // 标准的深度遍历：
    // 先序遍历：根节点 -> 左子树 -> 右子树    变形：根结点 -> 右子树 -> 左子树
    // 中序遍历：左子树 -> 根节点 -> 右子树    变形：右子树 -> 根节点 -> 左子树
    // 后续遍历：左子树 -> 右子树 -> 根节点    变形：右子树 -> 左子树 -> 根节点



    // 二叉树深度遍历迭代过程：进栈的一定是二叉树的数据节点，但是遍历指针可以遍历到空指针，也就是叶子节点的左右空指针域，表示遍历完了当前分支。
    // 本质上是在模拟递归，因为在递归的过程中使用了系统栈，所以在迭代的解法中常用Stack来模拟系统栈。
    // 用三层的二叉树来理解更好

    // 注意三种迭代遍历中遍历访问当前节点的时机。前序遍历一边顺着指向左子节点的指针移动一边遍历当前节点。
    // 中序遍历和后续遍历则顺着指向左子节点的指针移动时只将当前节点放入栈中，并不遍历遇到的节点。只有当到达最左子节点之后再从栈中取出节点遍历。
    // 后续遍历需要保留前一个遍历的节点，并根据前一个遍历的节点是否是当前节点的右子节点来决定是否可以遍历档当前的节点。


    // 下面比较中序遍历、前序遍历和后序遍历这3种不同遍历算法的代码。
    // 它们的递归代码都很简单，只需要调整代码的顺序就能写出对应算法的代码。
    // 它们的迭代代码也很类似，如它们都需要用到一个栈，而且代码的基本结构很相像，都有两个while循环并且它们的条件都一样。
    // 需要留意遍历当前节点的时机。前序遍历一边顺着指向左子节点的指针移动一边遍历当前的节点，而中序遍历和后序遍历则顺着指向左子节点的指针移动时只将节点放入栈中，并不遍历遇到的节点。
    // 只有当到达最左子节点之后再从栈中取出节点遍历。后序遍历最复杂，还需要保存前一个遍历的节点，并根据前一个遍历的节点是否为当前节点的右子节点来决定此时是否可以遍历当前的节点。



    // 二叉树要区分不同的层：
    // 方法1:用两个变量来表示当前层和下一个层节点的数目；如果当前层的剩余节点数目变为0，那么就表示该层已经遍历完了，其实就是计数器实现。
    // 方法2：用两个队列分别存放当前层和下一层的节点。如果当前层的队列被清空，那么该层的所有节点就已经被遍历完了，开始遍历下一层。


    // 二叉树的层序遍历 ：BFS的进一步改造 核心就是每一层分开，可以使用计数器分层或者每一层数据单独存放。
    // 也就是研究数据节点组成，那么就要看实际的数据节点， 所有遍历进入队列的只能是数据节点，所以在向队列中添加元素的时候要注意判空。
    // 有些题目比如要判断二叉树对称等，要使得每个节点在指定的位置，那么就需要空节点占位。

    public static void bfsLevel1(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode node=root;
        Queue<TreeNode> queue = new ArrayDeque<>();// 用队列实现
        queue.add(node);
        while (queue.size() > 0) {// !queue.isEmpty()和queue.size() > 0在这里一个意思
            int levelSize = queue.size();//获取当前队列的长度，其实就是二叉树当前这一层的节点个数
            //将队列中的元素都拿出来(也就是获取这一层的节点)，放到临时List的level中
            for (int i = 0; i < levelSize; i++) {//  levelSize是为了控制分层
                node = queue.poll();//队首元素出队
                System.out.println(node.val + "");//处理当前节点，也就是对当前节点的语句就在这个位置写
                // 队首元素出队，如果该节点的左/右子树不为空，也放入队列中
                // 因为遍历进入队列的必须是数据节点，所以要判空。
                if (node.left != null) {// 遍历左子树，不添加空节点
                    queue.add(node.left);
                }
                if (node.right != null) {// 遍历右子树，不添加空节点
                    queue.add(node.right);
                }
            }
            //上一层已经处理完了，分层
            System.out.println("分层");//分层的处理标志，对于分层的处理写在这里
        }
    }




    // 层序遍历+使用计数器来计数
    public static void bfsLevel2(TreeNode root) {
        if (root == null) {
            return;
        }
        int currentCounts=0;// 当前遍历元素所在层的节点数目计数器
        int nextCounts=0;// 当前遍历元素所在层的下一层的节点数目计数器
        Queue<TreeNode> queue = new ArrayDeque<>();//用队列实现
        TreeNode node=root;// node 为二叉树遍历节点
        queue.add(node);
        currentCounts++;
        while (!queue.isEmpty()) {// !queue.isEmpty()和queue.size() > 0在这里一个意思
            node = queue.poll();//队首元素出队
            currentCounts--;//当前层元素个数减1
            System.out.println(node.val + "");//处理当前节点，也就是对当前节点的语句就在这个位置写
            if (node.left != null) {// 遍历左子树，不添加空节点
                queue.add(node.left);
                nextCounts++;
            }
            if (node.right != null) {// 遍历右子树，不添加空节点
                queue.add(node.right);
                nextCounts++;
            }

            if(currentCounts==0){// 当前层的节点数目等于0，换层处理
                //上一层已经处理完了，分层
                System.out.println("分层");//分层的处理标志，对于分层的处理写在这里
                currentCounts=nextCounts;
                nextCounts=0;
            }
        }
    }




    // 层序遍历 写法2 用不同的队列来存储不同的层
    public static void bfsLevel3(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue1 = new ArrayDeque<>();//用队列实现
        Queue<TreeNode> queue2 = new ArrayDeque<>();//用队列实现
        TreeNode node=root;
        queue1.add(node);
        while (!queue1.isEmpty()) {// !queue.isEmpty()和queue.size() > 0在这里一个意思
            node = queue1.poll();//队首元素出队
            System.out.println(node.val + "");//处理当前节点，也就是对当前节点的语句就在这个位置写
            // 队首元素出队，如果该节点的左/右子树不为空，放入队列2中
            // 因为遍历进入队列的必须是数据节点，所以要判空。
            if (node.left != null) {// 遍历左子树，不添加空节点
                queue2.add(node.left);
            }
            if (node.right != null) {// 遍历右子树，不添加空节点
                queue2.add(node.right);
            }

            // 队列1空了，也就是一层遍历完了，将队列2指向队列1，队列2指向新的队列
            if(queue1.isEmpty()){
                //上一层已经处理完了，分层
                System.out.println("分层");//分层的处理标志，对于分层的处理写在这里
                queue1=queue2;
                queue2=new LinkedList<>();
            }
        }
    }






    // 二叉树的广度优先搜索
    // BFS 遍历使用队列数据结构： 这个没有添加空引用节点，也就是叶子节点的左右孩子
    public static void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();// 模拟队列，队列保证每一层是先进先出,
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll(); // 队首元素出队，队首元素一直是某个子树的父节点
            System.out.println(node.val + "");//处理当前节点，也就是对当前节点的语句就在这个位置写
            //队首元素出队，如果节点的左/右子树不为空，也放入队列中
            // 因为遍历进入队列的必须是数据节点，所以要判空.
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }




    // DFS 深度优先遍历：二叉树的深度优先遍历有三种方式，前序（先根次序）、中序（中根次序）和后序（后根次序）遍历。
    // 下面这个是深度遍历的前序遍历的递归形式
    public static void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val + "");//处理当前节点，也就是对当前节点的语句就在这个位置写
        dfs(node.left);// 处理左子树
        dfs(node.right);// 处理右子树
    }



    //  DFS 深度优先遍历  有基本数据类型的返回值
    //  存在性问题，有一个即可，所以递归左子树和右子树的结果只要有一个成立即可。
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {// 空结点
            return false;
        }
        // 处理当前节点
        if (root.left == null && root.right == null) {// 叶⼦结点，且路径和为sum
            return sum == root.val;// 当前的节点值是否等于剩余的sum
        }

        // 写法1：
        if(hasPathSum(root.left, sum - root.val)){// 递归遍历左子树，注意向下递归的时候减去当前节点值
            return true;
        }
        if(hasPathSum(root.right, sum - root.val)){
            return true;
        }
        return false;

        // 以下两种写法和上面return的作用一样，注意这里是【或】的处理，因为是存在性问题，所以只需要一个成立即可。
        // 注意前序遍历有返回值时左右子树的写法
        //return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);


        // 写法2：
        //递归进⼊⼦结点
        //boolean ans1=hasPathSum(root.left, sum - root.val);
        //boolean ans2=hasPathSum(root.right, sum - root.val);
        //return ans1||ans2;
    }




    // 判断二叉树是否左右对称
    // 方法二：层次遍历变形，调整节点的加入顺序
    // 将要验证的两个对称位置的节点先后加入队列中， 之后再验证两个位置的元素是否相等

    public boolean isSymmetric(TreeNode root) {
        if(root==null){// 空链表为对称的
            return true;
        }
        Queue<TreeNode> queue=new LinkedList<>(); //	用队列保存节点
        //  //将根节点的左右孩子放到队列中或者将根节点添加两次到队列中
        queue.add(root.left);
        queue.add(root.right);

        //queue.add(root);// 这里将根节点添加两次也可以，
        //queue.add(root);
        while(queue.size()>0){
            TreeNode left=queue.poll();
            TreeNode right=queue.poll();
            //递归的终止条件是两个节点都为空或者两个节点中有一个为空或者两个节点的值不相等
            if(left==null&&right==null){// 两个节点都为空
                continue;// 进入下一轮循环，空节点没办法添加左右子树
            }
            if(left==null||right==null||left.val!=right.val){// 两个节点中有一个为空或者两个节点的值不相等
                return false;// 循环有一次不对称则整个不对称
            }
            // 因为是验证对称二叉树，所以必须将空节点也加入队列中，这样利用空引用占位才能对称。则会将叶子节点的左右空子节点添加到队列中。
            // 因为是一个单队列，注意层次遍历节点的添加顺序，每次将两个对称位置的节点先后加入队列
            // 对称节点：【left.left，right.right】  【left.right，right.left 】
            queue.add(left.left);
            queue.add(right.right);
            // 将左节点的右孩子，右节点的左孩子放入队列
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }







    //  Morris解法
    //  Morris的整体思路就是：以某个根结点开始，找到它左子树的最右侧节点之后与这个根结点进行连接

    //  前序遍历  Morris
    //  在某个根结点创建连线的时候打印。因为我们是顺着左边的根节点来创建连线，且创建的过程只有一次。
    //  打印某些自身无法创建连线的节点，也就是叶子节点。

    public static void preOrderMorris(TreeNode root) {
        if (root == null) {
            return;
        }
        //
        TreeNode node = root;// 当前开始遍历的节点
        TreeNode leftNode = null;// 记录当前结点的左子树
        //
        while (node != null) {
            leftNode = node.left;
            if (leftNode != null) {
                // 找到当前左子树的最右侧节点，且这个节点应该在指向根结点之前，否则整个节点又回到了根结点。
                while (leftNode.right != null && leftNode.right != node) {
                    leftNode = leftNode.right;
                }
                // 这个时候如果最右侧这个节点的右指针没有指向根结点，创建连接然后往下一个左子树的根结点进行连接操作。
                if (leftNode.right == null) {
                    leftNode.right = node;
                    System.out.print(node.val + " ");
                    node = node.left;
                    continue;
                } else {// 当左子树的最右侧节点有指向根结点，此时说明我们已经回到了根结点并重复了之前的操作，同时在回到根结点的时候我们应该已经处理完 左子树的最右侧节点 了，把路断开。
                    leftNode.right = null;
                }
            } else {
                System.out.print(node.val + " ");
            }
            node = node.right;// 一直往右边走，参考图
        }
    }




    // 中序遍历  Morris
    // 从最左侧开始顺着右节点打印。也就是在将cu1切换到上层节点的时候。

    public static void inOrderMorris(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode node = head;
        TreeNode leftNode = null;
        while (node != null) {
            leftNode = node.left;
            //构建连接线
            if (leftNode != null) {
                while (leftNode.right != null && leftNode.right != node) {
                    leftNode = leftNode.right;
                }
                if (leftNode.right == null) {
                    leftNode.right = node;
                    node = node.left;
                    continue;
                } else {
                    leftNode.right = null;
                }
            }
            System.out.print(node.val + " ");
            node = node.right;
        }
    }



    // 后序遍历   后序Morris
    public static void postOrderMorris(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode node = root;//遍历树的指针变量
        TreeNode leftNode = null;//当前子树的最右节点
        while (node != null) {
            leftNode = node.left;
            if (leftNode != null) {
                while (leftNode.right != null && leftNode.right != node) {
                    leftNode = leftNode.right;
                }
                if (leftNode.right == null) {
                    leftNode.right = node;
                    node = node.left;
                    continue;
                } else {
                    leftNode.right = null;
                    postMorrisPrint(node.left);
                }
            }
            node = node.right;
        }
        postMorrisPrint(root);
    }



    //打印函数
    public static void postMorrisPrint(TreeNode head) {
        TreeNode reverseList = postMorrisReverseList(head);
        TreeNode cur = reverseList;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        postMorrisReverseList(reverseList);
    }


    //翻转单链表
    public static TreeNode postMorrisReverseList(TreeNode head) {
        TreeNode cur = head;
        TreeNode pre = null;
        while (cur != null) {
            TreeNode next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


}



class TreeNode {
    int val;
    TreeNode left;//
    TreeNode right;

    //构造器
    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node[val=" + val + "]";
    }


    ////创建需要的结点
    //TreeNode rootnode = new TreeNode(1);
    //TreeNode node2 = new TreeNode(2);
    //TreeNode node3 = new TreeNode(3);
    //TreeNode node4 = new TreeNode(4);
    //TreeNode node5 = new TreeNode(5);
    //TreeNode node6 = new TreeNode(6);
    //TreeNode node7 = new TreeNode(7);


    //// 手动创建二叉树
    //rootnode.left=node2;
    //rootnode.right=node3;
    //node2.left=node4;
    //node2.right=node5;
    //node3.left=node6;
    //node3.right=node7;


    //Solution solution = new BinaryTreePreorderTraversal().new Solution();
    //List<Integer> list= solution.preorderTraversal(rootnode);
    //System.out.println(list);
    //

}

