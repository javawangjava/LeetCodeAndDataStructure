/**
 * <p>字典&nbsp;<code>wordList</code> 中从单词 <code>beginWord</code><em>&nbsp;</em>和 <code>endWord</code> 的 <strong>转换序列
 * </strong>是一个按下述规格形成的序列
 * <meta charset="UTF-8" />&nbsp;<code>beginWord -&gt; s<sub>1</sub>&nbsp;-&gt; s<sub>2</sub>&nbsp;-&gt; ... -&gt;
 * s<sub>k</sub></code>：</p>
 *
 * <ul>
 * <li>每一对相邻的单词只差一个字母。</li>
 * <li>
 * <meta charset="UTF-8" />&nbsp;对于&nbsp;<code>1 &lt;= i &lt;= k</code>&nbsp;时，每个
 * <meta charset="UTF-8" />&nbsp;<code>s<sub>i</sub></code>&nbsp;都在
 * <meta charset="UTF-8" />&nbsp;<code>wordList</code>&nbsp;中。注意， <code>beginWord</code><em>&nbsp;</em>不需要在
 * <meta charset="UTF-8" />&nbsp;<code>wordList</code>&nbsp;中。
 * <meta charset="UTF-8" /></li>
 * <li><code>s<sub>k</sub>&nbsp;== endWord</code></li>
 * </ul>
 *
 * <p>给你两个单词<em> </em><code>beginWord</code><em>&nbsp;</em>和 <code>endWord</code> 和一个字典 <code>wordList</code> ，返回
 * <em>从&nbsp;<code>beginWord</code> 到&nbsp;<code>endWord</code> 的 <strong>最短转换序列</strong> 中的
 * <strong>单词数目</strong></em> 。如果不存在这样的转换序列，返回 <code>0</code> 。</p> &nbsp;
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * <strong>输出：</strong>5
 * <strong>解释：</strong>一个最短转换序列是 "hit" -&gt; "hot" -&gt; "dot" -&gt; "dog" -&gt; "cog", 返回它的长度 5。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * <strong>输出：</strong>0
 * <strong>解释：</strong>endWord "cog" 不在字典中，所以无法进行转换。</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= beginWord.length &lt;= 10</code></li>
 * <li><code>endWord.length == beginWord.length</code></li>
 * <li><code>1 &lt;= wordList.length &lt;= 5000</code></li>
 * <li><code>wordList[i].length == beginWord.length</code></li>
 * <li><code>beginWord</code>、<code>endWord</code> 和 <code>wordList[i]</code> 由小写英文字母组成</li>
 * <li><code>beginWord != endWord</code></li>
 * <li><code>wordList</code> 中的所有字符串 <strong>互不相同</strong></li>
 * </ul>
 *
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍
 * 1173</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

/**
 * 127
 * 单词接龙
 * @author wangweizhou
 * @date 2022-12-12 17:14:19
 */

public class WordLadder {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new WordLadder().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


        // 这个问题是关于单词的演变的，所以每个单词就是图中的一个节点。
        // 如果两个单词能够相互演变（改变一个单词的一个字母能变成另一个单词），那么这两个单词之间有一条边相连。
        // 这个题目要求计算最短演变序列的长度，即求图中两个节点的最短距离。
        // 表示单词演变的图也是一个无权图，按照题目的要求，图中两个节点的距离是连通两个节点的路径经过的节点的数目。
        // 通常用广度优先搜索计算无权图中的最短路径，广度优先搜索通常需要用到队列。


        //// 解法1：广度搜索+其实这个就是要自己建立邻接表  求最短长度本质就是使用两个队列实现层序遍历找出图的最小深度
        //// 层序遍历+找出所有的相邻节点+从哈希表中匹配单词列表中有的相邻节点
        //// 一个队列queue1中存放离起始节点距离为d的节点，当从这个队列中取出节点并访问的时候，与队列queue1中节点相邻的节点离起始节点的距离都是d+1，将这些相邻的节点存放到另一个队列queue2中。
        //// 当队列queue1中的所有节点都访问完毕时，再访问队列queue2中的节点，并将相邻的节点放入queue1中。
        //// 可以交替使用queue1和queue2这两个队列由近及远地从起始节点开始搜索所有节点。
        //// 首先将起始节点beginWord添加到队列queue1中。接下来每一步从队列queue1中取出一个节点word访问。
        //// 如果word就是目标节点，则搜索结束；否则找出所有与word相邻的节点并将相邻的节点放到队列queue2中。
        //// 当队列queue1中的所有节点都访问完毕时交换队列queue1和queue2，以便接下来访问原本存放在队列queue2中的节点。
        //// 每次交换队列queue1和queue2时都意味着距离初始节点距离为d的节点都访问完毕，接下来将访问距离为d+1的节点，因此距离值增加1。
        //// 上述代码将单词列表中还没有访问过的节点放入notVisited中，每当一个单词被访问过就从这个HashSet中删除。
        //// 如果一个节点不在notVisited之中，要么它不在单词列表之中，要么之前已经访问过，不管是哪种情况这个节点都可以忽略。

        //// 接下来考虑找出一个节点的相邻节点。按照这个题目的要求，相邻的节点对应的单词能相互演变，也就是将一个单词修改一个字母可以演变成另一个单词。


		// 127和752 类似可以一起看
        // 类比 二叉树使用两个队列来实现层序遍历  其实就是层序遍历获得图的深度
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        	Queue<String> queue1=new LinkedList<>();// 当前层。一个队列queue1中存放离起始节点距离为d的节点。
        	Queue<String> queue2=new LinkedList<>();// 下一层。与队列queue1中节点相邻的节点离起始节点的距离都是d+1，将这些相邻的节点存放到另一个队列queue2中。
        	// 将单词列表中还没有访问过的节点放入notVisited中，每当一个单词被访问过就从这个HashSet中删除。
        	Set<String> notVisited=new HashSet<>(wordList);// 建立的图是有向图
        	if(!notVisited.contains(endWord)){// 单词列表中没有目标字符串
        		return 0;
        	}
        	int length=1;// endWord那一层就结束了，初始值和后面对应就可以，这里相当于将根节点长度设置为1
        	queue1.add(beginWord);// 将起始节点beginWord添加到队列queue1中
        	while(!queue1.isEmpty()){
        		// 处理当前节点
        		String word=queue1.remove();// 每一步从队列queue1中取出一个节点word访问。
        		if(word.equals(endWord)){// 如果word就是目标节点，则搜索结束
        			return length;
        		}
        		// 遍历当前节点的相邻节点，【类似二叉树遍历当前节点的子节点】
        		List<String> neighbors=getNeighbors(word);// 找出所有与word相邻的节点。这里采用的是变化word中的一个字符获取所有的只有一个字符不同的单词集合。
        		// 遍历所有与word相邻的节点，类比二叉树的子节点
        		for (String neighbor:neighbors) {
        			// 遍历所有与word相邻且在单词列表中没有访问过的节点
        			if(notVisited.contains(neighbor)){
        				queue2.add(neighbor);
        				notVisited.remove(neighbor);
        			}
        		}
        		// 每次交换队列queue1和queue2时都意味着距离初始节点距离为d的节点都访问完毕，接下来将访问距离为d+1的节点，因此距离值增加1。
        		if(queue1.isEmpty()){
        			length++;// 每一层深度加1
        			queue1=queue2;
        			queue2=new LinkedList<>();
        		}
        	}
        	return 0;
        }



        // 找出一个节点word的所有相邻节点。其实这个就是要自己建立每一个节点的邻接表。
        // 按照这个题目的要求，相邻的节点对应的单词能相互演变，也就是将一个单词修改一个字母可以演变成另一个单词。
        // 这里是使用替换字符数组中的字符，然后再用更换字符后的字符数组来创建。找出当前字符串word的所有相邻单词。
        private List<String> getNeighbors(String word){
        	List<String> neighbors=new LinkedList<>();// 存储word的所有的相邻节点的集合neighbors
        	char[] charArr =word.toCharArray();// 将字符串转换为字符数组
        	// 相邻节点就是将当前单词变换成只有一个字符不同的单词。
        	// 所以遍历字符串的每一个字符并将该字符变化为与该字符不同的字符，
        	for (int i = 0; i < charArr.length; i++) {// 遍历当前单词的每一个字符
        		char old=charArr[i];
        		for (char ch='a';ch<='z';ch++) {// 将当前字符变换成不同的字符，并添加到neighbors
        			if(old!=ch){
        				charArr[i]=ch;
        				neighbors.add(new String(charArr));// 将相邻的节点添加到储存相邻节点的集合中
        			}
        		}
        		// 指向到这里已经找到了将charArr[i]变换成其他25个不同字符的相邻节点
        		charArr[i]=old;// 将该字符还原，这样下一个字符变化时就只有变换了一个字符
        	}
        	return neighbors;
        }




        //// 解法2：层序遍历 写法2 使用计数器   一个队列+计数器
        //public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //	Set<String> set=new HashSet<>(wordList);
        //	if(!set.contains(endWord)){
        //		return 0;
        //	}
        //	Queue<String> queue=new LinkedList<>();
        //	queue.offer(beginWord);
        //	int length=1;
        //	while (queue.size()>0){
        //		int size=queue.size();
        //		for (int i = 0; i < size; i++) {
        //			String curr=queue.poll();
        //			if(endWord.equals(curr)){
        //				return length;
        //			}
        //			List<String> neighbors=getNeighbors(curr);
        //			for (String word:neighbors){
        //				if(set.contains(word)){
        //					queue.add(word);
        //					set.remove(word);
        //				}
        //			}
        //		}
        //		length++;
        //	}
        //	return 0;
        //}
        //
        //
        //private List<String> getNeighbors(String word){
        //	List<String> list=new ArrayList<>();
        //	char[] charArr=word.toCharArray();
        //	int len=word.length();
        //	for (int i = 0; i < len; i++) {
        //		char old=charArr[i];
        //		for (char j='a';j<='z';j++) {
        //			if(old!=j){
        //				charArr[i]=j;
        //				list.add(new String(charArr));
        //			}
        //		}
        //		charArr[i]=old;
        //	}
        //	return list;
        //}




        //// 解法2：双向广度搜索
        //// 这个题目是关于单一起始节点、单一目标节点的最短距离问题。
        //// 前面的解法是从起始节点出发不断朝着目标节点的方向搜索，直到到达目标节点。
        //// 针对这类问题有一种常见的优化方法，即在从起始节点出发不断朝着目标节点的方向搜索的同时，也从目标节点出发不断朝着起始节点的方向搜索。
        // 这种双向搜索的方法能够缩小搜索空间，从而提高搜索的时间效率。
        //// 如果采用双向广度优先搜索，则分别从起始节点和目标节点出发不断搜索，直到在中间某个位置相遇，那么图中只有部分节点被搜索到。
        //
        //// 代码一共使用了3个HashSet，其中，set1和set2分别存放两个方向上当前需要访问的节点，set3用来存放与当前访问的节点相邻的节点。
        //// 之所以这里用的是HashSet而不是Queue，是因为需要判断从一个方向搜索到的节点在另一个方向是否已经访问过。只需要O（1）的时间就能判断HashSet中是否包含一个元素。
        //// 先将起始节点beginWord添加到set1中，将目标节点endWord添加到set2中。
        //// 接下来每次while循环都是从需要访问的节点数目少的方向搜索，这样做是为了缩小搜索的空间。
        //// 先确保set1中需要访问的节点数更少，接下来访问set1中的每个节点word。
        //// 如果某个与节点word相邻的节点neighbor在set2中，则说明两个不同方向的搜索相遇，已经找到了一条起始节点和目标节点之间的最短路径，此时路径的长度就是它们之间的最短距离，否则将节点neighbor
        // 添加到set3中。
        //// 当set1中所有的节点都访问完毕，接下来可能会访问set1的节点的相邻节点，即set3中的节点，因此将set1指向set3。
        //// 然后继续从set1和set2中选择一个节点数目少的方向进行新一轮的搜索。每轮搜索都意味着在起始节点和目标节点之间的最短路径上多前进了一步，因此变量length增加1

        //
        ////// 下面写法是采用从节点数少的set1开始访问，
        //public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //	// 将单词列表中还没有访问过的节点放入notVisited中，每当一个单词被访问过就从这个HashSet中删除。
        //	Set<String> notVisited=new HashSet<>(wordList);// 这个哈希表主要是用来匹配单词列表和相邻的节点
        //	if(!notVisited.contains(endWord)){// 单词列表中没有目标单词
        //		return 0;
        //	}
        //	// set1和set2分别存放两个方向上当前需要访问的节点，
        //	Set<String> set1=new HashSet<>();
        //	Set<String> set2=new HashSet<>();
        //
        //	// 先将起始节点beginWord添加到set1中，将目标节点endWord添加到set2中。
        //	int length=2;
        //	set1.add(beginWord);
        //	set2.add(endWord);
        //	notVisited.remove(endWord);// 访问过的要删除
        //
        //	// 其实下面的set123就相当于层序遍历的每一层
        //	while (!set1.isEmpty()&&!set2.isEmpty()){
        //		// 先确保set1中需要访问的节点数更少，每次while循环都是从需要访问的节点数目少的方向搜索
        //		if(set2.size()<set1.size()){
        //			Set<String> temp=set1;
        //			set1=set2;
        //			set2=temp;
        //		}
        //		// set3用来存放与当前访问的节点相邻的节点。其实就是用来存放下一层的节点。
        //		Set<String> set3=new HashSet<>();
        //		for (String word:set1) {// 访问set1中的每个节点word。循环都是从需要访问的节点数目少的方向搜索
        //			List<String> neighbors=getNeighbors(word);// 找出所有与word相邻的节点
        //			for (String neighbor:neighbors){
        //				// 如果某个与节点word相邻的节点neighbor在set2中，则说明两个不同方向的搜索相遇，
        //				// 已经找到了一条起始节点和目标节点之间的最短路径，此时路径的长度就是它们之间的最短距离，
        //				if(set2.contains(neighbor)){
        //					return length;
        //				}
        //				// 如果某个与节点word相邻的节点neighbor不在set2中而且还没有访问过，将节点neighbor添加到set3中。
        //				if(notVisited.contains(neighbor)){
        //					set3.add(neighbor);
        //					notVisited.remove(neighbor);
        //				}
        //			}
        //		}
        //		length++;// 每轮搜索都意味着在起始节点和目标节点之间的最短路径上多前进了一步，因此变量length增加1
        //		set1=set3;// 当set1中所有的节点都访问完毕，接下来可能会访问set1的节点的相邻节点，即set3中的节点，因此将set1指向set3。
        //	}
        //	return 0;
        //}
        //
        //
        //
        ////// 找出一个节点的相邻节点。按照这个题目的要求，相邻的节点对应的单词能相互演变，也就是将一个单词修改一个字母可以演变成另一个单词。
        //private List<String> getNeighbors(String word){
        //	List<String> neighbors=new LinkedList<>();// 存储word的所有的相邻节点的集合neighbors
        //	char[] charArr =word.toCharArray();
        //	// 相邻节点就是将单词的每一个字符变换成只有一个字符不同的单词
        //	for (int i = 0; i < charArr.length; i++) {// 遍历每一个字符
        //		char old=charArr[i];
        //		// 因为这里每一个字符可以变换成其他25个不同的其他字符，所以这里使用循环合并
        //		for (char ch='a';ch<='z';ch++) {// 将每一个字符变换成不同的字符，并添加到neighbors
        //			if(old!=ch){
        //				charArr[i]=ch;
        //				neighbors.add(new String(charArr));
        //			}
        //		}
        //		charArr[i]=old;// 将该字符还原，这样下一个字符变化时就只有变换了一个字符
        //	}
        //	return neighbors;
        //}
        //


    }
//leetcode submit region end(Prohibit modification and deletion)

}
