/**
假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人
的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。 

 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 
j 个人的属性（queue[0] 是排在队列前面的人）。 

 

 
 

 示例 1： 

 
输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
解释：
编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 

 示例 2： 

 
输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 

 

 提示： 

 
 1 <= people.length <= 2000 
 0 <= hi <= 10⁶ 
 0 <= ki < people.length 
 题目数据确保队列可以被重建 
 

 Related Topics 贪心 树状数组 线段树 数组 排序 👍 1545 👎 0

*/

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 406
 * 根据身高重建队列
 * @author wangweizhou
 * @date 2023-02-27 22:01:48
 */
public class QueueReconstructionByHeight{
	 public static void main(String[] args) {
	 	 //测试代码
	 	 Solution solution = new QueueReconstructionByHeight().new Solution();
		 int[][] people={{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
		 solution.reconstructQueue(people);
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	// 方法二：从高到低考虑   高个子的人是看不到低个子人的

	// 我们也可以将每个人按照身高从大到小进行排序，处理身高相同的人使用的方法类似，即：按照 hi为第一关键字降序，ki为第二关键字升序进行排序。
	// 如果我们按照排完序后的顺序，依次将每个人放入队列中，那么当我们放入第 i 个人时：
	// 第 0,⋯,i−1 个人已经在队列中被安排了位置，他们只要站在第 i 个人的前面，就会对第 i 个人产生影响，因为他们都比第 i 个人高；
	// 而第 i+1,⋯,n−1 个人还没有被放入队列中，并且他们无论站在哪里，对第 i 个人都没有任何影响，因为他们都比第 i 个人矮。
	// 但我们可以发现，后面的人既然不会对第 i个人造成影响，我们可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置。
	// 也就是说，当我们放入第 i 个人时，只需要将其插入队列中，使得他的前面恰好有 ki 个人即可。


	public int[][] reconstructQueue(int[][] people) {
		if (people == null || people.length == 0) {
			return new int[0][];
		}
		// 将身高【第一维】按照降序排列；将比当前人高的人数【第二维】按照升序排列
		Arrays.sort(people, new Comparator<int[]>() {
			@Override
			public int compare(int[] person1, int[] person2) {
				if (person1[0] != person2[0]) {// 第一维不同，也就是身高不同，则身高按照降序排序
					return person2[0] - person1[0];
				} else {// 第一维相同，也就是身高相同，则将第二维按照升序排列
					return person1[1] - person2[1];
				}
			}
		});

		// 如果我们按照排完序后的顺序，依次将每个人放入队列中，那么当我们放入第 i 个人时：
		// 第 0,⋯,i−1 个人已经在队列中被安排了位置，他们只要站在第 i 个人的前面，就会对第 i 个人产生影响，因为他们都比第 i 个人高；
		// 而第 i+1,⋯,n−1 个人还没有被放入队列中，并且他们无论站在哪里，对第 i 个人都没有任何影响，因为他们都比第 i 个人矮。
		// 但我们可以发现，后面的人既然不会对第 i个人造成影响，我们可以采用「插空」的方法，依次给每一个人在当前的队列中选择一个插入的位置。
		// 也就是说，当我们放入第 i 个人时，只需要将其插入队列中，使得他的前面恰好有 ki 个人即可。
		//
		List<int[]> ans = new ArrayList<int[]>();
		for (int[] person : people) {// 数组已经排序了，身高由高到低，身高大于等于当前人的人数由低到高。
			ans.add(person[1], person);// 当我们放入第 i 个人时，只需要将其插入队列中，使得他的前面恰好有 ki 个人即可。
		}
		return ans.toArray(new int[ans.size()][]);
	}


}
//leetcode submit region end(Prohibit modification and deletion)

}
