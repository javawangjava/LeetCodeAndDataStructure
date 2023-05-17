package nowcoder;


import java.util.Scanner;

public class HJ63 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            int n = sc.nextInt();
            String result = getFirstSubstr(str, n);
            System.out.println(result);
        }
    }



    private static String getFirstSubstr(String str, int n) {
        if (str == null || str.length() == 0 || n < 0) {
            return "";
        }
        if (n > str.length()) {
            return str;
        }
        int len = str.length();
        int count = 0;
        int maxRatio = 0;
        int left = 0;
        int right = 0;
        int[] ans = new int[2];
        char[] cs = str.toCharArray();
        while (right < n) {
            if (cs[right] == 'C' || cs[right] == 'G') {
                count++;
            }
            right++;
        }
        maxRatio = count;
        ans[0] = left;
        ans[1] = right;
        while (right < len) {
            if (cs[right] == 'C' || cs[right] == 'G') {
                if (cs[left] != 'C' && cs[left] != 'G') {
                    count++;
                }
            } else if (cs[right] != 'C' && cs[right] != 'G') {
                if (cs[left] == 'C' || cs[left] == 'G') {
                    count--;
                }
            }
            right++;
            left++;
            if (maxRatio < count) {
                maxRatio = count;
                ans[0] = left;
                ans[1] = right;
            }
        }
        String res = str.substring(ans[0], ans[1]);
        return res;
    }
}
