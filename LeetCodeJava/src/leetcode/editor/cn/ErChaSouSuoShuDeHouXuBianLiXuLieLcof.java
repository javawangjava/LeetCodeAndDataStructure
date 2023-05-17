/**
 * <p>输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回&nbsp;<code>true</code>，否则返回&nbsp;<code>false</code>。假设输入的数组的任意两个数字都互不相同。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p>参考以下这颗二叉搜索树：</p>
 *
 * <pre>     5
 * / \
 * 2   6
 * / \
 * 1   3</pre>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre><strong>输入: </strong>[1,6,3,2,5]
 * <strong>输出: </strong>false</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre><strong>输入: </strong>[1,3,2,6,5]
 * <strong>输出: </strong>true</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ol>
 * <li><code>数组长度 &lt;= 1000</code></li>
 * </ol>
 * <div><div>Related Topics</div><div><li>栈</li><li>树</li><li>二叉搜索树</li><li>递归</li><li>二叉树</li><li>单调栈</li></div
 * ></div><br><div><li>👍 616</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 剑指 Offer 33
 * 二叉搜索树的后序遍历序列
 *
 * @author wangweizhou
 * @date 2022-09-25 11:12:52
 */

public class ErChaSouSuoShuDeHouXuBianLiXuLieLcof {

    public static void main(String[] args) {
        // 测试代码
        Solution solution = new ErChaSouSuoShuDeHouXuBianLiXuLieLcof().new Solution();
        int[] postorder = {1, 2, 5, 10, 6, 9, 4, 3};
        boolean bool = solution.verifyPostorder(postorder);
        System.out.println(bool);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 这个参考根据后序遍历和中序遍历建立二叉树，或者根据前序遍历和中序遍历建立二叉树。
        // 本质就是根据二叉搜索树的后序遍历重建二叉树。
        // 因为二叉搜索树的中序遍历时递增的。
        // 可以先找到二叉搜索树的根节点，再基于根节点把整棵树的遍历序列拆分成左子树对应的子序列和右子树对应的子序列，接下来再递归地处理这两个子序列。


        // 解法1：在后序遍历得到的序列中，最后一个数字是树的根节点的值。
        // 数组中的数字可以根据根节点的值分为两部分：第一部分是左子树节点的值，它们都比根节点的值小；第二部分是右子树节点的值，它们都比根节点的值大。

        public boolean verifyPostorder(int[] postorder) {
            if (postorder == null || postorder.length == 0) {// 这里认为空引用和空树是后序遍历结果
                return true;
            }
            return verifyPostorderFunc(postorder, 0, postorder.length - 1);
        }



        // 解法1：写法2   这个不同的地方要根据二叉搜索树的后续遍历特点将后续遍历数组分割成左右子树
        // [start,end]建立二叉搜索树   返回值表示[start,end]能否建立二叉树
        private boolean verifyPostorderFunc(int[] postorder, int start, int end) {
            if (postorder == null || postorder.length == 0) {// 判空
                return true;
            }
            if (start > end) {// 区间中没有元素
                return false;
            }

            // 在后序遍历得到的序列中，最后一个数字是搜索二叉树的根节点的值。
            int rootVal = postorder[end];// 后序遍历中的最后一个节点就是根节点

            // 下面获取二叉搜索树的左子树的节点，搜索二叉树的左子树的节点的值应该全部小于根节点的值
            // 在二叉搜索树中左子树节点的值小于根节点的值
            int index1 = start;// index1 是区间[start,end]的左子区间的遍历指针
            while (index1 < end) {// 数组没有越界
                if (postorder[index1] < rootVal) {// 二叉搜索树的左子树的节点值都小于根节点值，遍历指针后移
                    index1++;
                } else {// postorder[index1] >= root.// 二叉搜索树中左子树的节点值都小于根节点值。所以当节点值大于根节点值时，就说明左子树已经遍历完了
                    break;// 注意这里是break,跳出该循环，这里只是根据根节点找出所有的左子树的节点
                }
            }

            // 下面是获取二叉搜索树的右子树，二叉搜索树的右子树的节点值应该全部大于根节点的值，
            // 上面循序结束时，index1指向第一个大于根节点值的节点，也就是右子树的第一个节点
            // 在二叉搜索树中右子树节点的值大于根节点的值
            int index2 = index1;// index2 是区间[start,end]的右子区间的遍历指针
            while (index2 < end) {
                if (postorder[index2] > rootVal) { // 前面已经找到了左子树中的所有节点，那么进入右子树之后，右子树的所有节点都大于根节点。遍历指针后移
                    index2++;
                } else {// postorder[index2] =< rootVal.如果出现小于根节点值的节点，说明该节点不是右子树中的节点，那么就不能根据该数组重建搜索二叉树
                    // 因为前面已经找出了所有的左子树的节点，那么剩余的节点就是右子树的节点。
                    // 这时候如果有小于根节点的数字，那么就不符合二叉搜索树的后序遍历，没有办法重建搜索二叉树。
                    return false;
                }
            }


            // 左子树[start,index1-1];右子树[index1,end-1];根节点[end]。那么这时候index1就是左右子树的分界点。
            // 既然要建立二叉树，那么(start<=index1-1)，则start+1<=index1,start<index1
            // (index1<=end-1),则index1+1<=end，index1<end。
            // 判断左子树是不是二叉搜索树
            boolean left = true;// 注意这里设定是可以建立子树，当不能建立子树的时候，才可以变为false。
            if (start<index1) {// 当前根节点存在左子树,判断左子树是否是二叉搜索树
                left = verifyPostorderFunc(postorder, start, index1 - 1);
            }

            // 判断右子树是不是二叉搜索树
            boolean right = true;
            if (index1 < end - 1) {// 当前根节点存在右子树,判断右子树是否是二叉搜索树
                right = verifyPostorderFunc(postorder, index1, end - 1);
            }
            return (left && right);// 因为这个需要左右子树都可以建立二叉搜索树，所以这里是逻辑与。
        }





        //// verifyPostorderFunc：判断[start,end]是否是搜索二叉树的子序列
        //// 解法1：写法1 [start,end]这里处理成闭区间
        //// 判断闭区间[start,end]是否是二叉树的后序遍历序列。左子树[start,index1-1];右子树[index1,end-1];根节点[end]。
        //public boolean verifyPostorderFunc(int[] postorder, int start, int end) {
        //    if (postorder == null || postorder.length ==0) {// 判空
        //        return true;
        //    }
        //
        //    // 在后序遍历得到的序列中，最后一个数字是二叉树的根节点的值。
        //    int root = postorder[end];// 数组[start,end]的根节点
        //
        //    // 下面是搜索二叉搜索树的左子树，搜索二叉树的左子树的节点的值应该全部小于根节点的值
        //    // 在二叉搜索树中左子树节点的值小于根节点的值
        //    int index1 = start;
        //    while (index1 < end ) {// 数组没有越界
        //        // 下面>或者>=都可以，二叉搜索树中没有数据域相等的节点
        //        //if (postorder[index1] >= root) {// 左子树的节点值都小于根节点值，当节点值大于根节点值时，就说明左子树已经遍历完了
        //        if (postorder[index1] > root) {// 二叉搜索树中左子树的节点值都小于根节点值。所以当节点值大于根节点值时，就说明左子树已经遍历完了
        //            break;
        //        }
        //        // 因为上面结束会跳出该循环，所以下面就是postorder[index1] < root。所以遍历指针后移
        //        index1++;
        //    }
        //
        //    // 下面是遍历搜索二叉树的右子树，搜索二叉树的右子树的节点值应该全部大于根节点的值，
        //    // 上面循序结束时，index1指向第一个大于根节点值的节点，也就是右子树的第一个节点
        //    // 在二叉搜索树中右子树节点的值大于根节点的值
        //    int index2 = index1;
        //    while (index2 < end ) {
        //
        //        // 前面已经找到了左子树中的所有节点，那么进入右子树之后，右子树的所有节点都大于根节点。如果出现小于根节点值的节点，说明不是右子树
        //        if (postorder[index2] < root) {
        //            return false;
        //        }
        //        // 应为上面是在遇到比根节点值小的节点就结束方法，所以下面就是postorder[index2] > root,所以遍历指针右移
        //        index2++;
        //    }
        //
        //    // 左子树[start,index1-1];右子树[index1,end-1];根节点[end]。
        //    // 判断左子树是不是二叉搜索树
        //    boolean left = true;
        //    if (index1 > start) {// 当前根节点存在左子树
        //        left = verifyPostorderFunc(postorder, start, index1 - 1);
        //    }
        //
        //    // 判断右子树是不是二叉搜索树
        //    boolean right = true;
        //    if (index1 < end - 1) {// 当前根节点存在右子树
        //        right = verifyPostorderFunc(postorder, index1 , end-1);
        //    }
        //    return (left && right);
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
