/**
 * <p>给你一个包含 <code>n</code> 个整数的数组 <code>nums</code>，判断 <code>nums</code> 中是否存在三个元素 <em>a，b，c ，</em>使得 <em>a + b + c
 * = </em>0 ？请你找出所有和为 <code>0</code> 且不重复的三元组。</p>
 *
 * <p><strong>注意：</strong>答案中不可以包含重复的三元组。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
 * <strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0]
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= nums.length <= 3000</code></li>
 * <li><code>-10<sup>5</sup> <= nums[i] <= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 4877</li><li>👎
 * 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15
 * 三数之和
 */

public class ThreeSum {

    public static void main(String[] args) {
        //测试代码
        Solution solution = new ThreeSum().new Solution();
        //int[] nums = {0, 0, 0};
        int[] nums = {-1, -1, 0, 1, 1, 2, 2, 2, -1, -4};
        //int[] nums = {-3, -2, -1, 0, 1, 2, 3, 4, 5};
        //int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> ans = solution.threeSum(nums);
        System.out.println(ans);
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> lists = new ArrayList<>();
            if (nums == null || nums.length < 3) {
                return lists;
            }
            int len = nums.length;
            Arrays.sort(nums);
            for (int first = 0; first < len - 2; first++) {
                if (nums[first] > 0) {
                    break;
                }
                if (nums[first] + nums[len - 2] + nums[len - 1] < 0) {
                    continue;
                }
                if (first > 0 && nums[first] == nums[first - 1]) {// 这里是当前数字对应的三数之和还没有计算，所以只能和前一个数进行比较剪枝，跳过与前一个位置相同的数字。
                    continue;
                }
                int second = first + 1;
                int third = len - 1;
                while (second < third) {
                    int sum = nums[first] + nums[second] + nums[third];
                    if (sum == 0) {
                        lists.add(Arrays.asList(nums[first], nums[second], nums[third]));
                        int tempSecond = nums[second];
                        while (second < third && nums[second] == tempSecond) {
                            second++;
                        }
                        int tempThird = nums[third];
                        while (second < third && tempThird == nums[third]) {
                            third--;
                        }
                    } else if (sum > 0) {
                        third--;
                    } else {
                        second++;
                    }
                }
            }
            return lists;
        }




        //public List<List<Integer>> threeSum(int[] nums) {
        //    List<List<Integer>> lists = new ArrayList<>();
        //    if (nums == null || nums.length < 3) {
        //        return lists;
        //    }
        //    int len = nums.length;
        //    Arrays.sort(nums);
        //    for (int first = 0; first < len - 2; first++) {
        //        if (nums[first] > 0) {
        //            break;
        //        }
        //        if (nums[first] + nums[len - 2] + nums[len - 1] < 0) {
        //            continue;
        //        }
        //        int second = first + 1;
        //        int third = len - 1;
        //        while (second < third) {
        //            int sum = nums[first] + nums[second] + nums[third];
        //            if (sum == 0) {
        //                lists.add(Arrays.asList(nums[first], nums[second], nums[third]));
        //                int tempSecond = nums[second];
        //                while (second < third && nums[second] == tempSecond) {
        //                    second++;
        //                }
        //                int tempThird = nums[third];
        //                while (second < third && tempThird == nums[third]) {
        //                    third--;
        //                }
        //            } else if (sum > 0) {
        //                third--;
        //            } else {
        //                second++;
        //            }
        //        }

        //        while (first < nums.length - 2 && nums[first] == nums[first + 1]) {//
        //        // 这里是当前数字对应的三数之和已经计算，所以可以和后一个数进行比较剪枝，跳过和当前数字相同的。
        //            first++;
        //        }
        //    }
        //    return lists;
        //}




        //// 解法2：循环+双指针+去重   所有不重复解
        //// 因为是组合去重，所以要第一个元素去重和第二个元素去重。三数之和是定值的话，前两个相同，那么第三个肯定是相同的。
        //// 数组排序的目的是方便去重
        //// 首先对数组进行排序，排序后固定一个数 nums[i]，再使用左右指针指向 nums[i]后面的两端，
        //// 数字分别为 nums[L]和 nums[R]，计算三个数的和 sum 判断是否满足为 0，满足则添加进结果集。
        //// 时间复杂度：O(n^2)n 为数组长度
        //
        //public List<List<Integer>> threeSum(int[] nums) {
        //    List<List<Integer>> lists = new ArrayList<>();
        //    if (nums == null || nums.length < 3) {// 数组为空或者数组长度小于3
        //        return lists;
        //    }
        //    Arrays.sort(nums);//数组重新排序,
        //    int length = nums.length;//数组长度
        //    for (int i = 0; i < length - 2; i++) {// 遍历确定三个元素中的第一个
        //        // 剪枝
        //        if (nums[i] > 0) {//排序后，若nums[i]>0，则后面的数字都大于0，其和不可能为0.所以循环结束
        //            break;
        //        }
        //        if (nums[i] + nums[length - 2] + nums[length - 1] < 0) {// nums[i]+nums[length-2]+nums[length-1]<0,
        //        则其他组合肯定小于0
        //            continue;
        //        }
        //        // 因为i是三个数中指定的第一个指针，判断是否跳过当前这一个，所以要将当前这一个和前一个进行比较，这样才能保证当前第一个元素出现的时候已经算过了，不会遗漏。
        //        // 为了保证不加⼊重复的 list,因为是有序的，所以如果第一个数和前⼀个元素相同，只需要继续后移就可以。
        //
        //        // 这里剪枝保证添加到结果集合中的第一个元素不重复,组合中的第一个元素去重
        //        if (i > 0 && nums[i] == nums[i - 1]) {//注意这里是（i-1）因为先要算一次，所以要保证下标大于0，然后看后面的左右指针能否使用nums[i]
        //            continue;//继续下一轮循环，就包含i自加和i范围判断
        //        }
        //
        //        // 后面就是双指针来找和等于（-nums[first])的组合
        //        int left = i + 1;// nums[i]后面最左侧的指针
        //        int right = length - 1;//nums[i]后面最右侧的指针
        //        while (left < right) {// 双指针遍历确定三个数中的后两个，left向右，right向左
        //            int sum = nums[i] + nums[left] + nums[right];
        //            if (sum == 0) {// 三数之和等于0添加到结果列表lists中之后再去重。三数之和等于0，若其中两数相同，则第三个数也相同。所以要去重。
        //                // static <T> List<T> asList(T... a) 返回由指定数组支持的固定大小的列表。
        //                lists.add(Arrays.asList(nums[i], nums[left], nums[right]));// 把第一次出现的放入List中
        //                // 下面这个加入lists中的语句和上面的作用相同
        //                //List<Integer> list = new ArrayList<>();
        //                //list.add(nums[i]);
        //                //list.add(nums[left]);
        //                //list.add(nums[right]);
        //                //lists.add(list);
        //
        //                // 定和组合：三个数中若两个数相同，则第三个数一定相同。这里要固定第一个元素，遍历后两个元素。
        //                // 所以left和right至少有一个一定要移动，这样才能保证不形成死循环
        //                // 当已经把第一次符合条件的一组元素放入了list后，后面遇到相同left和right元素，左指针要右移并且右指针要左移.先去重再算和
        //                // 因为第一个数相同，第二个数相同，和为0的话，那么第三个数一定相同。所以第一次加入之后，left和right一定都要移到下一个不同的数上面
        //                int leftTemp = nums[left];// 临时变量保存相同元素中的第一个,借助临时变量，left指针一定会移动到下一个不同于left的位置
        //                // 短路与先比较错的多的再比较对的多的，下面while循环结束时，left移动到下一个不同元素
        //                while (leftTemp == nums[left] && left < right) {// leftTemp==nums[left] 第一次是本身等于本身，所以left一定会右移
        //                    left++;
        //                }
        //                int rightTemp = nums[right];
        //                while (rightTemp == nums[right] && left < right) {// rightTemp ==nums[right]
        //                    right--;
        //                }
        //            } else if (sum > 0) {// 和要变小。sum!=0，则该组数据不会添加到结果列表中，所以是否剪枝是可选的
        //                // 可能是这里数据量比较小，去重反而使得操作步骤变多，时间边长
        //                right--;
        //                //while(left != right && nums[right] == nums[right+1]){
        //                //    right--;
        //                //}
        //            } else if (sum < 0) {// 和要变大
        //                left++;
        //                //while(left != right && nums[left] == nums[left-1]) {
        //                //    left++;
        //                //}
        //            }
        //        }
        //    }
        //    return lists;
        //}


        ////	 解法3：数组升序排列+双指针 和解法二只有去重的方式不同
        //public List<List<Integer>> threeSum(int[] nums) {
        //    List<List<Integer>> lists = new ArrayList<>();
        //    if (nums == null || nums.length < 3) {// 数组为空或者数组长度小于3
        //        return lists;
        //    }
        //    Arrays.sort(nums);//数组重新排序,
        //    int length = nums.length;//数组长度
        //
        //    for (int i = 0; i < length - 2; i++) {
        //        // 剪枝
        //        if (nums[i] > 0) {//排序后，若nums[i]>0，则后面的数字都大于0，其和不可能为0.所以循环结束
        //            break;
        //        }
        //
        //        if (nums[i] + nums[length - 2] + nums[length - 1] < 0) {// nums[i]+nums[length-2]+nums[length-1]<0,
        //            // 则其他组合肯定小于0
        //            continue;
        //        }
        //
        //        // 因为i是指定的第一个指针，判断是否跳过当前这一个，所以要将当前这一个和前一个进行比较，这样才能保证当前第一个元素出现的时候已经算过了，不会遗漏。
        //        //为了保证不加⼊重复的 list,因为是有序的，所以如果第一个数和前⼀个元素相同，只需要继续后移就可以，
        //        if (i > 0 && nums[i] == nums[i - 1]) {//注意这里是（i-1）因为先要算一次，然后看后面的左右指针能否使用nums[i]
        //            continue;//继续下一轮循环，就包含i自加和i范围判断
        //        }
        //
        //        int left = i + 1;//nums[i]后面最左侧的指针
        //        int right = length - 1;//nums[i]后面最右侧的指针
        //
        //        while (left < right) {//双指针遍历，left向右，right向左
        //            int sum = nums[i] + nums[left] + nums[right];
        //            if (sum == 0) {//三数之和等于0之后再去重，不等于0时后续计算可能用到那些重复的数
        //                // static <T> List<T> asList(T... a) 返回由指定数组支持的固定大小的列表。
        //                lists.add(Arrays.asList(nums[i], nums[left], nums[right]));//把第一次出现的放入List中
        //
        //                // 下面这个加入lists中的语句和上面的作用相同
        //                //List<Integer> list = new ArrayList<>();
        //                //list.add(nums[i]);
        //                //list.add(nums[left]);
        //                //list.add(nums[right]);
        //                //lists.add(list);
        //
        //                // 这里注意和上一个的去重方式不同
        //                // left和right至少有一个一定要移动，这样才能保证不形成死循环
        //                // 已经把第一次符合条件的放入了list中，后面遇到相同元素，左指针要右移并且右指针要左移.先去重再算和
        //                // 因为第一个数相同，第二个数相同，和为0的话，那么第三个数一定相同。所以第一次加入之后，left和right一定都要移到下一个不同的数上面
        //
        //                //已经相等了，那么left指针直接跳过下一个相同的数，直接移动至下一个不同的数
        //                while (left < right && nums[left] == nums[left + 1]) {//循环结束left指向最后一个相同的数
        //                    left++;
        //                }
        //                left++;// 上面while循环结束的时候，left指向最后一个相同的数，所以这里要left++。
        //                while (left < right && nums[right] == nums[right - 1]) {
        //                    right--;
        //                }
        //                right--;
        //
        //            } else if (sum > 0) {// 和要变小。sum!=0，则该组数据不会添加到结果列表中，所以是否剪枝是可选的
        //                right--;
        //                // 可能是这里数据量比较小，去重反而使得操作步骤变多，时间边长
        //                //while(left != right && nums[right] == nums[right+1]){
        //                //    right--;
        //                //}
        //            } else if (sum < 0) {// 和要变大。sum!=0，则该组数据不会添加到结果列表中，所以是否剪枝是可选的
        //                left++;
        //                //while(left != right && nums[left] == nums[left-1]) {
        //                //    left++;
        //                //}
        //            }
        //        }
        //    }
        //    return lists;
        //}
        //





        /*
        // 解法1写法2：循环+双指针
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> lists=new ArrayList<>();
            if(nums==null||nums.length<3){// 数组为空或者数组长度小于3
                return lists;
            }
            Arrays.sort(nums);//数组重新排序,
            int length = nums.length;//数组长度
            int first=0;
            while (first < length - 2) {//遍历第一个需要固定的元素
                if(nums[first]>0){//排序后，若nums[i]>0，则后面的数字都大于0，其和不可能为0.所以循环结束
                    break;
                }
                // 排序后nums[length-2]，nums[length-1]是最大的两个数
                if(nums[first]+nums[length-2]+nums[length-1]<0){
                    first++;
                    continue;
                }
                twoSum(lists,nums, first);//在数组中找两个数的和是给定值（-nums[first]）
                //去重，这个写法有优点，借助一个临时变量，比每次比较相邻的两个元素有优点
                int temp=nums[first];
                while(first <length&&temp==nums[first]){
                    first++;
                }
            }
            return lists;
        }


        //在排序树组中找到和为给定值的两个数字的方法
        //在排序数组中从start开始寻找和为（-nums[start]）的两个数字
        private void twoSum(List<List<Integer>> lists,int[] nums,int start){
            int left=start+1;
            int right=nums.length-1;
            while(left<right){
                int sum=nums[start]+nums[left]+nums[right];
                if(sum==0){
                    lists.add(Arrays.asList(nums[start],nums[left],nums[right]));
                    int temp=nums[left];
                    while(nums[left]==temp&&left<right){
                        left++;
                    }
                } else if(sum<0){
                    left++;
                }else{
                    right--;
                }
            }
        }
        */


    }
//leetcode submit region end(Prohibit modification and deletion)

}


