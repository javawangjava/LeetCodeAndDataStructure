/**
 * <p>0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。</p>
 *
 * <p>例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> n = 5, m = 3
 * <strong>输出:&nbsp;</strong>3
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> n = 10, m = 17
 * <strong>输出:&nbsp;</strong>2
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>限制：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n&nbsp;&lt;= 10^5</code></li>
 * <li><code>1 &lt;= m &lt;= 10^6</code></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>递归</li><li>数学</li></div></div><br><div><li>👍 716</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;

/**
 * 剑指 Offer 62
 * 圆圈中最后剩下的数字
 * @author wangweizhou
 * @date 2022-12-28 17:28:25
 */

public class YuanQuanZhongZuiHouShengXiaDeShuZiLcof {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new YuanQuanZhongZuiHouShengXiaDeShuZiLcof().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 解法1：模拟链表+取余模拟成环。
        // 假设当前删除的位置是 idx，下一个删除的数字的位置是 idx+m 。
        // 但是，由于把当前位置的数字删除了，后面的数字会前移一位，所以实际的下一个位置是 idx+m−1。【画图理解】
        // 由于数到末尾会从头继续数，所以最后取模一下，就是 (idx+m−1)(mod n)。

        // [0,n-1]的范围，m是要删除的第m个数
        public int lastRemaining(int n, int m) {
            if (n < 0) {
                return -1;
            }
            ArrayList<Integer> list = new ArrayList<>(n);// 创建容量为n的链表
            for (int i = 0; i < n; i++) {// 将0~n-1添加到链表中
                list.add(i);
            }
            // 假设当前删除的位置是 idx，下一个删除的数字的位置是 idx+m 。
            // 但是，由于把当前位置的数字删除了，后面的数字会前移一位，所以实际的下一个位置是 idx+m−1。【画图理解】
            int idx = 0;//
            while (n > 1) {
                // 这里取余就相当于模拟成环。
                idx = (idx + m - 1) % n;// 由于数到末尾会从头继续数，所以最后取模一下，就是 (idx+m−1)(mod n)。
                list.remove(idx);// 从链表中移出该位置的元素
                n--;// 移除了一个元素，那么链表长度（-1）
            }
            return list.get(0);
        }



        // 解法2：数学法 没看明白
        ///*
        // 思路：使用数学方法(先举例)
        // 你要知道最后的结果是3，带着结果去看问题
        //
        //    第一次，【0, 1, 2, 3, 4】，本轮要踢出2                                  看3
        //    (下一轮开始从3计数，为了方便读者看出规律，将开始计数的那一位移到开头)
        //    第二次，【3, 4, 0, 1】，本轮要踢出0                                     看1
        //    第三次，【1, 3, 4】，本轮要踢出4                                        看1
        //    第四次，【1, 3】 本轮要踢出1                                            看3
        //    第五次，【3】
        //    最后返回3
        //
        //    我们要使用的数学方法，就是从结果0号位置，反推最开始在哪
        //    你从第二次，向上看第一次
        //    你会发现，原来3在0的位置
        //            现在，3在(0 + 3) % 5
        //                    => +3 回到上次的位置
        //                    => %5 防止数组溢出，并且数组本来就是循环数组
        //
        //    f(n) = ( f(n - 1) + m ) % n
        //    解释意思：
        //        f(n) 表示上一次
        //        f(n - 1) 表示这次，因为我们要从这次回推上一次
        //        m 表示隔几个
        //        n表示上一次的数组长度
        //
        // */



        //public int lastRemaining(int n, int m) {
        //	int res = 0;// 最后只剩下一位，坐标肯定是0
        //	for (int i = 2; i <= n; i++) {
        //		res = (res + m) % i;
        //	}
        //	return res;
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
