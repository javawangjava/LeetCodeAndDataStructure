/**
 * <p>给你一个非负整数 <code>x</code> ，计算并返回&nbsp;<code>x</code>&nbsp;的 <strong>算术平方根</strong> 。</p>
 *
 * <p>由于返回类型是整数，结果只保留 <strong>整数部分 </strong>，小数部分将被 <strong>舍去 。</strong></p>
 *
 * <p><strong>注意：</strong>不允许使用任何内置指数函数和算符，例如 <code>pow(x, 0.5)</code> 或者 <code>x ** 0.5</code> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 4
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>x = 8
 * <strong>输出：</strong>2
 * <strong>解释：</strong>8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>二分查找</li></div></div><br><div><li>👍 1056</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

/**
 * 69
 * x 的平方根
 *
 * @author wangweizhou
 * @date 2022-07-01 01:30:09
 */

public class Sqrtx {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new Sqrtx().new Solution();
        System.out.println(solution.mySqrt(1));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 假设输入的非负整数为n。解决这个问题的直观方法是从0开始每次增加1，对于每个整数m，判断m^2是否小于或等于n。
        // 如果找到一个m，并且满足m^2≤n和（m+1）^2>n，那么m就是n的平方根。
        // 由数学常识可知，整数n的平方根一定小于或等于n。同时，除0之外的所有整数的平方根都大于或等于1。
        // 因此，整数n的平方根一定在从1到n的范围内，取这个范围内的中间数字m，并判断m^2是否小于或等于n。
        // 如果m^2≤n，那么接着判断（m+1）^2是否大于n。如果满足（m+1）^2>n，那么m就是n的平方根。
        // 如果m^2≤n并且（m+1）^2≤n，则n的平方根比m大，接下来搜索从m+1到n的范围。
        // 如果m^2>n，则n的平方根小于m，接下来搜索从1到m-1的范围。
        // 然后在相应的范围内重复这个过程，总是取出位于范围中间的m，计算m^2和（m+1）^2并与n比较，直到找到一个满足m^2≤n并且（m+1）^2>n的m。

        // 这种思路每次都取某个范围的中间值，如果中间值满足条件，则搜索结束；如果中间值不满足条件，则该中间值将下一轮搜索的范围缩小一半。
        // 当m^2<=n且(m+1)^2>n时m为平方根。其实也就是找m^2<=n中的最大数m。


        // 解法3 二分查找
        public int mySqrt(int n) {
            if (n < 0) {
                return -1;
            }
            if (n == 1 || n == 0) {//特殊值处理
                return n;
            }
            int left = 0;
            int right = n;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (mid <= n / mid) {
                    if ((mid + 1) > n / (mid + 1)) {
                        return mid;
                    }
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return 0;
        }




        //	方法1： 二分查找
        //  由于 x 平方根的整数部分 ans 是满足 k^2 ≤x 的最大 k 值，因此我们可以对 k 进行二分查找，从而得到答案。
        //  二分查找的下界为 0，上界可以粗略地设定为 x。
        //  在二分查找的每一步中，我们只需要比较中间元素 mid 的平方与 x 的大小关系，并通过比较的结果调整上下界的范围。

        //public int mySqrt(int x) {
        //	if (x < 0) {
        //		return Integer.MIN_VALUE;
        //	}
        //	if (x == 1 || x == 0) {//特殊值处理
        //		return x;
        //	}
        //    int left = 0;
        //    int right = x;
        //    int ans = -1;
        //    while (left <= right) {
        //        int mid = left + (right - left) / 2;//这种写法是为了避免越界
        //        //  int mid=(right+left)/2 ;
        //        if (mid<=x/mid) {
        //            ans = mid;// 就是保存上一个满足条件的值
        //            left = mid + 1;
        //        } else {
        //            right = mid - 1;
        //        }
        //    }
        //    return ans;
        //}





        // //解法2：二分法 Java除法向下取整
        //public int mySqrt(int x) {
        //	if(x<0){
        //		return Integer.MAX_VALUE;
        //	}
        //	if(x==1||x==0){//特殊值处理
        //		return x;
        //	}
        //    int left=0;
        //    int right=x;
        //	while(left<=right){
        //        int mid=(left+right)/2;
        //		if(mid<=x/mid){
        //			left=mid+1;
        //		}else{
        //			right=mid-1;
        //		}
        //	}
        //	return right;
        //}




        /*
        // 解法1： 二分查找 写法2  试出来的
        public int mySqrt(int x) {
            if (x < 0) {
                return Integer.MAX_VALUE;
            }
            int left=0;
            int right=x;
            while(left<=right){// 注意这里有等号，
                int mid=left+(right-left)/2;
                long product=(long)mid*mid;
                if(product==x){
                    return mid;
                }else if(product>x){// 乘积大了，
                    right=mid-1;
                }else{
                    left=mid+1;// 乘积小了，
                }
            }
            return right;
        }
        */



    }
//leetcode submit region end(Prohibit modification and deletion)

}
