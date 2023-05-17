/**
 * <p>给你一个整数数组 <code>nums</code> ，返回<em> </em><code>nums[i] XOR nums[j]</code> 的最大运算结果，其中 <code>0 ≤ i ≤ j &lt;
 * n</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例 1：</strong></p>
 * </div>
 * </div>
 *
 * <pre>
 * <strong>输入：</strong>nums = [3,10,5,25,2,8]
 * <strong>输出：</strong>28
 * <strong>解释：</strong>最大运算结果是 5 XOR 25 = 28.</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * <strong>输出：</strong>127
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
 * <li><code>0 &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>位运算</li><li>字典树</li><li>数组</li><li>哈希表</li></div></div><br><div><li>👍
 * 515</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 421
 * 数组中两个数的最大异或值
 *
 * @author wangweizhou
 * @date 2023-01-10 21:13:50
 */

public class MaximumXorOfTwoNumbersInAnArray {

    public static void main(String[] args) {
        //测试代码
        //Solution solution = new MaximumXorOfTwoNumbersInAnArray().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法2：二进制的异或：二进制位相同为0，不同为1。
        // 整数的异或有一个特点，如果两个相同数位异或的结果是0，那么两个相反的数位异或的结果为1。
        // 如果想找到某个整数k和其他整数的最大异或值，那么尽量从高位开始找和k的数位不同的整数，所以下面建立前缀树是从高位开始建立的。
        // 因此，这个问题可以转化为查找的问题，而且还是按照整数的二进制数位进行查找的问题。需要将整数的每个数位都保存下来。
        // 前缀树可以实现这种思路，前缀树的每个节点对应整数的一个数位，路径对应一个整数。

        // 考虑如何基于前缀树的查找计算最大的异或值。从高位开始扫描整数num的每个数位。
        // 如果前缀树中存在某个整数的相同位置的数位和num的数位相反，则优先选择这个相反的数位，
        // 这是因为两个相反的数位异或的结果为1，比两个相同的数位异或的结果大。
        // 按照优先选择与整数num相反的数位的规则就能找出与num异或最大的整数。

        // xor = (xor << 1) + 1：(xor << 1) 左移一位，表示将前面的位与运算的结果左移，最左侧新的数位用“0”补足。
        // xor = (xor << 1) + 1：在将前面数位保存的前提下，将数位的最后一位设置位1。
        public int findMaximumXOR(int[] nums) {
            if(nums==null||nums.length==0){
                return 0;
            }
            TrieNode root = buildTrie(nums);// 根据数组nums建立前缀树
            int maxXor = 0;// 数组元素异或的最大值
            // 遍历数组的每一个元素，在前缀树中从最高位开始按照优先选择与整数num相反的数位的规则就能找出与num异或最大的整数。
            for (int num : nums) {
                TrieNode node = root;// 每次从前缀树的根节点开始查找
                int xor = 0;// 变量xor是当前整数num与其他整数异或的最大值，而变量max是数组中元素所有异或的最大值。
                // 从高位开始扫描整数num的每个数位。
                for (int i = 31; i >= 0; i--) {
                    // 这里异或是每次一个二进制的数位，那么遍历完整个二进制数位时才能获得两个十进制数的异或结果。
                    int bit = (num >> i) & 1;// 获取数字num的二进制的第i位是0还是1。位与运算：相同为1，不同为0。
                    // 变量bit为整数num的二进制中的第i个数位，如果前缀树中相应的位置【第i个节点】存在一个对应1-bit的节点，则优先选择这个节点。
                    // 按照优先选择与整数num相反的数位的规则就能找出与num异或最大的整数。这是因为两个相反的数位异或的结果为1，比两个相同的数位异或的结果大。
                    if (node.children[1 - bit] != null) {
                        // 将xor的第i个二进制位置设置为1，因为前（i-1）位已经异或获取了，那么这里只要更新第i位就可以。1的二进制形式只有最后一位是1，所以可以相加
                        xor = (xor << 1) + 1;
                        node = node.children[1 - bit];// 进入与num的第i位不同的数位
                    } else {
                        // (xor << 1) 左移一位，表示将前面的位与运算的结果左移，最左侧新的数位用“0”补足。
                        xor = xor << 1;// 将xor的第i个二进制位置设置为0，即
                        node = node.children[bit];
                    }
                }
                // 上面内层循环结束的时候，变量xor是当前整数num与其他整数异或的最大值，而变量max是数组中元素所有异或的最大值。
                maxXor = Math.max(maxXor, xor);// 更新数组中任意元素异或的最大值
            }
            return maxXor;
        }



        // 创建一棵能够保存整数的前缀树，这和保存字符串的前缀树类似。
        // 从左到右逐一取出整数的每个数位，并根据值0或1在必要的时候创建新的节点。
        // 前缀树的深度是32，前缀树每一个节点有两个子节点。
        private TrieNode buildTrie(int[] nums) {
            TrieNode root = new TrieNode();// 创建前缀树的根节点
            for (int num : nums) {// 遍历字符数组的每一个元素，将每一个元素插入前缀树中
                // 从数字的二进制高位上开始将当前数字的二进制形式插入前缀树中，这里就相当于208实现前缀树中遍历字符串的每一个字符，也就是前缀树的深度
                TrieNode node = root;// 每次插入前缀树中要从根节点开始进行遍历
                for (int i = 31; i >= 0; i--) {// 遍历当前数字num的每一个数位
                    // 每次将（num >> i）左移i位，那就就将第i位放在二进制的最左边一位。数字1只有最左边一位是1，这样逻辑与运算就可以确定二进制第i位是0还是1了。
                    int bit = (num >> i) & 1;// 获取数字num的第i位是0还是1。位与运算：相同为1，不同为0。
                    if (node.children[bit] == null) {// 如果当前节点node不存在节点值为[bit]的子节点【第bit个子节点】，则新建该孩子节点并指向它
                        node.children[bit] = new TrieNode();
                    }
                    node = node.children[bit];// 如果当前节点node的值为[bit]的孩子节点存在，则node指向该孩子节点。其实就是指针后移，沿着符合的路径前进。
                }
            }
            return root;
        }



        // 由于每个节点只有两个分别表示0和1的子节点，因此前缀树节点的数据结构可以定义为只有两个子节点
        // 由于整数都是32位，它们在前缀树中对应的路径的长度都是一样的，因此没有必要用一个布尔值字段标记最后一个数位。
        class TrieNode {
            public TrieNode[] children;
            public TrieNode() {
                children = new TrieNode[2];
            }
        }



        //// 解法1：暴力法  超时
        //// 如果找出数组中所有可能由两个数字组成的数对并求出它们的异或，通过比较就能得出最大的异或值。
        //public int findMaximumXOR(int[] nums) {
        //    if(nums==null||nums.length==0){
        //        return 0;
        //    }
        //    int len=nums.length;
        //    int max=0;
        //    for (int i = 0; i < len; i++) {
        //        for (int j = i; j <len ; j++) {
        //            int xor=nums[i]^nums[j];
        //            max=Math.max(max,xor);
        //        }
        //    }
        //    return max;
        //}


    }

//leetcode submit region end(Prohibit modification and deletion)

}
