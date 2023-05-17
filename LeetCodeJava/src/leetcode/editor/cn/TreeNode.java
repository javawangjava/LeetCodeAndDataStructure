package leetcode.editor.cn;

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
    //
    //// 手动创建二叉树
    //rootnode.left=node2;
    //rootnode.right=node3;
    //node2.left=node4;
    //node2.right=node5;
    //node3.left=node6;
    //node3.right=node7;
    //
    //Solution solution = new BinaryTreePreorderTraversal().new Solution();
    //List<Integer> list= solution.preorderTraversal(rootnode);
    //System.out.println(list);
    //

}


