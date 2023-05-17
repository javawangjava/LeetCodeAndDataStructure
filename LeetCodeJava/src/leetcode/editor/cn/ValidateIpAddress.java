/**
 * 给定一个字符串 queryIP。如果是有效的 IPv4 地址，返回 "IPv4" ；如果是有效的 IPv6 地址，返回 "IPv6" ；如果不是上述类型的
 * IP 地址，返回 "Neither" 。
 * <p>
 * 有效的IPv4地址 是 “x1.x2.x3.x4” 形式的IP地址。 其中 0 <= xi <= 255 且 xi 不能包含 前导零。例如: “192.168
 * .1.1” 、 “192.168.1.0” 为有效IPv4地址， “192.168.01.1” 为无效IPv4地址; “192.168.1.00” 、 “192
 * .168@1.1” 为无效IPv4地址。
 * <p>
 * 一个有效的IPv6地址 是一个格式为“x1:x2:x3:x4:x5:x6:x7:x8” 的IP地址，其中:
 * <p>
 * <p>
 * 1 <= xi.length <= 4
 * xi 是一个 十六进制字符串 ，可以包含数字、小写英文字母( 'a' 到 'f' )和大写英文字母( 'A' 到 'F' )。
 * 在 xi 中允许前导零。
 * <p>
 * <p>
 * 例如 "2001:0db8:85a3:0000:0000:8a2e:0370:7334" 和 "2001:db8:85a3:0:0:8A2E:0370:733
 * 4" 是有效的 IPv6 地址，而 "2001:0db8:85a3::8A2E:037j:7334" 和 "02001:0db8:85a3:0000:0000:
 * 8a2e:0370:7334" 是无效的 IPv6 地址。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：queryIP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：queryIP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：queryIP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * queryIP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 * <p>
 * <p>
 * Related Topics 字符串 👍 228 👎 0
 */

package leetcode.editor.cn;

/**
 * 468
 * 验证IP地址
 *
 * @author wangweizhou
 * @date 2023-03-01 21:09:48
 */
public class ValidateIpAddress {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new ValidateIpAddress().new Solution();
        String str="172.16.254.1";
        String ans= solution.validIPAddress(str);
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 首先查找给定的字符串 queryIP 中是否包含符号 ‘.’。如果包含，那么我们需要判断其是否为 IPv4 地址；如果不包含，我们则判断其是否为 IPv6 地址。
        // 对于 IPv4 地址而言，它包含 4 个部分，用 ‘.’ 隔开。
        // 因此我们可以存储相邻两个 ‘.’ 出现的位置 last 和cur（当考虑首个部分时，last=−1；当考虑最后一个部分时，cur=n，其中 n 是字符串的长度），
        // 那么子串queryIP[last+1..cur−1] 就对应着一个部分。我们需要判断：

        // 1.它的长度是否在 [1,3] 之间（虽然这一步没有显式要求，但提前判断可以防止后续计算值时 32 位整数无法表示的情况）；
        // 2.它是否只包含数字；
        // 3.它的值是否在 [0,255] 之间；
        // 4.它是否不包含前导零。具体地，如果它的值为 0，那么该部分只能包含一个 0，即(cur−1)−(last+1)+1=1；
        // 如果它的值不为 0，那么该部分的第一个数字不能为 0，即 queryIP[last+1] 不为 0。


        // 对于 IPv6 地址而言，它包含 8 个部分，用 ‘:’ 隔开。同样地，我们可以存储相邻两个 ‘:’ 出现的位置 last 和cur，那么子串 queryIP[last+1..cur−1] 就对应着一个部分。我们需要判断：
        // 1.它的长度是否在 [1,4] 之间；
        // 2.它是否只包含数字，或者 a-f，或者 A-F；
        // 除了上述情况以外，如果我们无法找到对应数量的部分，那么给定的字符串也不是一个有效的 IP 地址。



        public String validIPAddress(String queryIP) {
            if (queryIP.indexOf('.') >= 0) {
                return isIpV4(queryIP) ? "IPv4" : "Neither";
            } else {
                return isIpV6(queryIP) ? "IPv6" : "Neither";
            }
        }


        public boolean isIpV4(String queryIP) {
            //1、根据"."分割开；2、四段；3、每段0-255；4、无前导0；5、全是digit
            //加-1是防止出现空字符串无法计数 比如192.168.1.1. 后边多了一个点，不加-1会被忽略后边的空串
            String[] split = queryIP.split("\\.",-1);
            //个数不是4个
            if (split.length != 4) {
                return false;
            }
            for (String s : split) {
                //分割出来的每一段的每个长度不在 1-3之间
                if (s.length() > 3 || s.length() == 0) {
                    return false;
                }
                //有前导0 并且长度不为1
                if (s.charAt(0) == '0' && s.length() > 1) {
                    return false;
                }
                //计算数字
                int num = 0;
                for (int j = 0; j < s.length(); j++) {
                    char c = s.charAt(j);
                    if (!Character.isDigit(c)) {//不是数字
                        return false;
                    }
                    num = num * 10 + (c - '0');
                }
                //数字超过255
                if (num > 255) {
                    return false;
                }
            }
            return true;
        }



        public boolean isIpV6(String queryIP) {
            //1、根据":"分隔开；2、八段；3、1-4位；4、字母(abcdef)或者数字
            String[] split = queryIP.split(":",-1);
            //数量不是8个
            if (split.length != 8) {
                return false;
            }
            for (String s : split) {
                //每个串 长度不在1-4之间
                if (s.length() > 4 || s.length() == 0) {// ipv6每个字段是4个字符
                    return false;
                }
                for (int i = 0; i < s.length(); i++) {
                    char c = s.charAt(i);
                    //不是数字并且字母不在 a-f之间
                    if (!Character.isDigit(c) && !(Character.toLowerCase(c) >= 'a') || !(Character.toLowerCase(c) <= 'f')) {
                        return false;
                    }
                    //if (!(c >= '0' && c <= '9') && !(c >= 'a' && c <= 'f')) {
                    //    return false;
                    //}
                }
            }
            return true;
        }






        // 写法2：
        //// 首先查找给定的字符串 queryIP 中是否包含符号 ‘.’。如果包含，那么我们需要判断其是否为 IPv4 地址；如果不包含，我们则判断其是否为 IPv6 地址。
        //public String validIPAddress(String ip) {
        //    if (ip.indexOf(".") >= 0 && check4(ip)) {
        //        return "IPv4";
        //    }
        //    if (ip.indexOf(":") >= 0 && check6(ip)) {
        //        return "IPv6";
        //    }
        //    return "Neither";
        //}
        //
        //
        //private boolean check4(String ip) {
        //    int len = ip.length();// IP地址长度
        //    int cnt = 0;// IP地址分段数
        //    char[] cs = ip.toCharArray();
        //    for (int i = 0; i < len && cnt <= 3; ) {// i是遍历字符串ip的指针
        //        // 找到连续数字段【其实就是分割点之间的部分】，以 num 存储，ip地址的每一个小段都是由数字组成，每小段之间都是由”.“分割形成的
        //        int j = i;
        //        int num = 0;// 获得数字，以指针j遍历获得
        //        while (j < len && cs[j] >= '0' && cs[j] <= '9' && num <= 255) {
        //            num = num * 10 + (cs[j++] - '0');
        //        }
        //        // 非 item 字符之间没有 item
        //        if (i == j) {
        //            return false;
        //        }
        //        // 含前导零 或 数值大于 255。   j - i > 1 ：表示num至少是两位数。
        //        // (j - i > 1 && cs[i] == '0')：分隔符之间的数字至少两位数，且以0开始。
        //        // 上面循环结束，如果有数字的话，那么j指向数字后面的分隔符”.“。
        //        if ((j - i > 1 && cs[i] == '0') || (num > 255)) {
        //            return false;
        //        }
        //        i = j + 1;// 下一个分段的第一个元素的指针
        //        if (j == len) {
        //            continue;
        //        }
        //        // 存在除 . 以外的其他非数字字符
        //        if (cs[j] != '.') {
        //            return false;
        //        }
        //        cnt++;
        //    }
        //    // 恰好存在 3 个不位于两端的 .
        //    return cnt == 3 && cs[0] != '.' && cs[len - 1] != '.';
        //}
        //
        //
        //
        //private boolean check6(String ip) {
        //    int n = ip.length();
        //    int cnt = 0;
        //    char[] cs = ip.toCharArray();
        //    for (int i = 0; i < n && cnt <= 7; ) {
        //        int j = i;
        //        while (j < n && ((cs[j] >= 'a' && cs[j] <= 'f') || (cs[j] >= 'A' && cs[j] <= 'F') || (cs[j] >= '0'
        //        && cs[j] <= '9'))) {
        //            j++;
        //        }
        //        // 非 item 字符之间没有 item 或 长度超过 4
        //        if (i == j || j - i > 4) {
        //            return false;
        //        }
        //        i = j + 1;
        //        if (j == n) {
        //            continue;
        //        }
        //        // 存在除 : 以外的其他非数字字符
        //        if (cs[j] != ':') {
        //            return false;
        //        }
        //        cnt++;
        //    }
        //    // 恰好存在 7 个不位于两端的 :
        //    return cnt == 7 && cs[0] != ':' && cs[n - 1] != ':';
        //}





        // 写法3
        //public String validIPAddress(String queryIP) {
        //	if (queryIP.indexOf('.') >= 0) {
        //		return isIpV4(queryIP) ? "IPv4" : "Neither";
        //	} else {
        //		return isIpV6(queryIP) ? "IPv6" : "Neither";
        //	}
        //}
        //
        //public boolean isIpV4(String queryIP) {
        //	//加-1是防止出现空字符串无法计数 比如192.168.1.1. 后边多了一个点，不加-1会被忽略后边的空串
        //	String[] split = queryIP.split("\\.",-1);
        //	//个数不是4个
        //	if (split.length != 4) {
        //		return false;
        //	}
        //	for (String s : split) {
        //		//每个长度不在 1-3之间
        //		if (s.length() > 3 || s.length() == 0) {
        //			return false;
        //		}
        //		//有前导0 并且长度不为1
        //		if (s.charAt(0) == '0' && s.length() != 1) {
        //			return false;
        //		}
        //		//计算数字
        //		int ans = 0;
        //		for (int j = 0; j < s.length(); j++) {
        //			char c = s.charAt(j);
        //			//不是数字
        //			if (!Character.isDigit(c)) {
        //				return false;
        //			}
        //			ans = ans * 10 + (c - '0');
        //		}
        //		//数字超过255
        //		if (ans > 255) {
        //			return false;
        //		}
        //	}
        //	return true;
        //}
        //
        //
        //public boolean isIpV6(String queryIP) {
        //	String[] split = queryIP.split(":",-1);
        //	//数量不是8个
        //	if (split.length != 8) {
        //		return false;
        //	}
        //	for (String s : split) {
        //		//每个串 长度不在1-4之间
        //		if (s.length() > 4 || s.length() == 0) {
        //			return false;
        //		}
        //		for (int i = 0; i < s.length(); i++) {
        //			char c = s.charAt(i);
        //			//不是数字并且字母不在 a-f之间
        //			if (!Character.isDigit(c) && !(Character.toLowerCase(c) >= 'a') || !(Character.toLowerCase(c) <=
        //			'f')) {
        //				return false;
        //			}
        //		}
        //	}
        //	return true;
        //}




        // 写法4
        //public String validIPAddress(String queryIP) {
        //	if (queryIP.indexOf('.') >= 0) {
        //		// IPv4
        //		int last = -1;
        //		for (int i = 0; i < 4; ++i) {
        //			int cur = (i == 3 ? queryIP.length() : queryIP.indexOf('.', last + 1));
        //			if (cur < 0) {
        //				return "Neither";
        //			}
        //			if (cur - last - 1 < 1 || cur - last - 1 > 3) {
        //				return "Neither";
        //			}
        //			int addr = 0;
        //			for (int j = last + 1; j < cur; ++j) {
        //				if (!Character.isDigit(queryIP.charAt(j))) {
        //					return "Neither";
        //				}
        //				addr = addr * 10 + (queryIP.charAt(j) - '0');
        //			}
        //			if (addr > 255) {
        //				return "Neither";
        //			}
        //			if (addr > 0 && queryIP.charAt(last + 1) == '0') {
        //				return "Neither";
        //			}
        //			if (addr == 0 && cur - last - 1 > 1) {
        //				return "Neither";
        //			}
        //			last = cur;
        //		}
        //		return "IPv4";
        //	} else {
        //		// IPv6
        //		int last = -1;
        //		for (int i = 0; i < 8; ++i) {
        //			int cur = (i == 7 ? queryIP.length() : queryIP.indexOf(':', last + 1));
        //			if (cur < 0) {
        //				return "Neither";
        //			}
        //			if (cur - last - 1 < 1 || cur - last - 1 > 4) {
        //				return "Neither";
        //			}
        //			for (int j = last + 1; j < cur; ++j) {
        //				if (!Character.isDigit(queryIP.charAt(j)) && !('a' <= Character.toLowerCase(queryIP.charAt(j)) && Character.toLowerCase(queryIP.charAt(j)) <= 'f')) {
        //					return "Neither";
        //				}
        //			}
        //			last = cur;
        //		}
        //		return "IPv6";
        //	}
        //}


    }
//leetcode submit region end(Prohibit modification and deletion)

}
