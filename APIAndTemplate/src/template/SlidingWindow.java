package template;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindow {

    //第一个模板：适用于需要使用 [变量] 记录的情况
    public int slidingWindow(int[] nums, int k) {
        //数组/字符串长度
        int n = nums.length;
        //双指针，表示当前遍历的区间[left, right]，闭区间
        int left = 0, right = 0;
        //定义变量统计 子数组/子区间 是否有效
        int sum = 0;
        //定义变量动态保存最大 求和/计数
        int res = 0;

        //右指针遍历到数组尾
        while (right < n) {
            //增加当前右指针对应的数值
            sum += nums[right];
            //当在该区间内 sum 超出定义范围
            while (sum > k&&right>=left) {
                //先将左指针指向的数值减去
                sum -= nums[left];
                //左指针右移
                left++;
            }
            //到 while 结束时，我们找到了一个符合题意要求的子数组/子串
            res = Math.max(res, right - left + 1);
            //移动右指针，去探索下一个区间
            right++;
        }
        return res;
    }



//    第二个模板：适用于需要用 [哈希表] 记录的情况

    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            //滑动窗口 + 两哈希，始终保证窗口长度，当长度超了，左指针准备右移
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> map = new HashMap<>();

            //创建 [双指针] 和 [有效数量]
            int left = 0, right = 0;
            int valid = 0;

            //
            for(Character c : s1.toCharArray()){
                need.put(c, need.getOrDefault(c,0)+1);
            }
            //
            while(right < s2.length()){
                char c = s2.charAt(right);

                if(need.containsKey(c)){
                    map.put(c,map.getOrDefault(c,0)+1);
                    if(need.get(c).equals(map.get(c))){
                        valid++;
                    }
                }

                //当凑齐了元素，还要判断长度
                while(right - left + 1 >= s1.length()){
                    if(valid == need.size()){
                        return true;
                    }
                    char d = s2.charAt(left);
                    if(need.containsKey(d)){
                        if(need.get(d).equals(map.get(d))){
                            valid--;
                        }
                        map.put(d,map.get(d)-1);
                    }
                    left++;
                }
                right++;
            }
            return false;
        }
    }


}
