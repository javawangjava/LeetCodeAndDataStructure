package template;

import java.util.*;

public class Graph {


    // 图的广度优先搜索通常需要一个队列。
    // 图的广度优先遍历可以看作以初始节点为中心，从内圈到外圈一圈一圈的遍历。

    // 队列中保存节点值为1且没有访问过的节点
    // 先将起始节点添加到队列中。接下来每步从队列中取出一个节点进行访问。对于这个题目而言，每访问一个节点，岛屿的面积增加1。
    // 接下来从上、下、左、右这4个方向判断相邻的节点是不是还有没有到达过的值为1的节点，如果有，就将其添加到队列中。
    // 重复这个过程，直到队列的长度为0，此时初始节点所在的子图搜索完毕。

    // 代码中队列的元素为矩阵中的坐标，每个坐标都包含行号和列号这两个值，用一个长度为2的数组表示。
    // 二维数组dirs表示在矩阵中向上、下、左、右这4个方向前进一步时坐标的变化。
    // 在矩阵中向上移动一步时行号减1而列号不变，所以坐标的改变值为（-1，0），其他方向的改变值类似。
    // 用当前坐标pos加上坐标的改变值就得到向不同方向前进一步之后的坐标。这样写代码的好处是容易用一个简洁的循环实现向4个不同方向前进。


    // 注意下面这个不能当作图的遍历的模板
    // 解法1：图的广度优先搜索，用队列实现     获得图的节点个数
    public int GraphBfs(int[][] grid, int i, int j) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rowLen = grid.length;// 行数
        int colLen = grid[0].length;// 列数
        // 创建了一个和输入矩阵相同大小的矩阵visited，它的作用是用一个布尔值标识矩阵中的每个值为1的格子是否已经到达过，用来确保每个格子只搜索一次。
        boolean[][] isVisited = new boolean[rowLen][colLen];
        Queue<int[]> queue = new LinkedList<>();
        // 代码中队列的元素为矩阵中的坐标，每个坐标都包含行号和列号这两个值，用一个长度为2的数组表示。
        queue.add(new int[]{i, j});// 将当前坐标加入到队列中，二维数组第一维是行，第二维是列。
        isVisited[i][j] = true;// 只要当前节点已经进入队列中，则将当前节点标识为已访问。如果是每次出队列时进行标记，可能会重复访问。
        // 二维数组dirs表示在矩阵中向上、下、左、右这4个方向前进一步时坐标的变化。
        // 在矩阵中向上移动一步时行号减1而列号不变，所以坐标的改变值为（-1，0），其他方向的改变值类似。
        // 用当前坐标pos加上坐标的改变值就得到向不同方向前进一步之后的坐标。
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int area = 0;
        while (!queue.isEmpty()) {
            int[] pos = queue.remove();// 队首元素出队，队首元素一直是某一个子图的根节点
            area++;// 每次从队列中弹出一个节点，则图的面积加1。处理当前节点
            for (int[] dir : dirs) {// 循环遍历当前位置的上下左右四个方向
                int row = pos[0] + dir[0];
                int col = pos[1] + dir[1];
                // 检查新坐标没有越界，并且下一个节点值为1且没有访问过，类比二叉树访问的左右子节点
                if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length
                        && grid[row][col] == 1 && !isVisited[row][col]) {
                    queue.add(new int[]{row, col});// 将当前坐标的相邻节点【新节点】加入到队列中
                    isVisited[row][col] = true;// 将当前节点相邻节点【新节点】标识为已访问
                }
            }
            // 类比二叉树的遍历，将四个方向分开，上面循环只是将四个方向的代码合并在一起写了。二叉树的左右子节点名字不一样，不能合并在一起写
            //int row=pos[0]+dirs[0][0];
            //int col=pos[1]+dirs[0][1];
            //if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
            // !isVisited[row][col]) {
            //    queue.add(new int[]{row, col});
            //    isVisited[row][col] = true;
            //}
            //
            //
            //row=pos[0]+dirs[1][0];
            //col=pos[1]+dirs[1][1];
            //if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
            // !isVisited[row][col]) {
            //    queue.add(new int[]{row, col});
            //    isVisited[row][col] = true;
            //}
            //
            //
            //row=pos[0]+dirs[2][0];
            //col=pos[1]+dirs[2][1];
            //if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
            // !isVisited[row][col]) {
            //    queue.add(new int[]{row, col});
            //    isVisited[row][col] = true;
            //}
            //
            //row=pos[0]+dirs[3][0];
            //col=pos[1]+dirs[3][1];
            //if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 1 &&
            // !isVisited[row][col]) {
            //    queue.add(new int[]{row, col});
            //    isVisited[row][col] = true;
            //}
        }
        return area;
    }




    // 图的广度遍历   这里是使用哈希表来表示某一个节点的邻接表
    // 在图中获取以num为起点的最长连续数值的长度。广度优先搜索借助队列来完成
    public int bfs1(Set<Integer> set, int num) {
        Queue<Integer> queue = new LinkedList<>();// 广度遍历是使用队列实现
        // 只要将元素加入到队列中时，就需要将该元素从已访问集合中移出，避免重复访问。其实类似于将元素标记为已访问。
        queue.offer(num);// 将当前元素添加到队列中
        set.remove(num);// 将当前元素从哈希表中移出，其实就是类似于已访问，不能再次访问已经遍历过的元素。这个和将该元素标记为已访问效果类似
        int count = 1;// 这里是每次进队的时候计数器加一
        while (!queue.isEmpty()) {
            int curr = queue.poll();// 队首元素出队，队首元素一直是某一个子图的根节点
            // 每当搜索到整数i时，就判断输入的整数中是否包含i-1和i+1，如果包含就将其添加到队列中以便接下来搜索。
            // 这个就是图的每一个节点的邻接表
            int[] neighbors = new int[]{curr - 1, curr + 1};// 题目要求时相邻连续的最长序列。所以这里要向相邻小的，或者相邻大的方向遍历。
            for (int neighbor : neighbors) {// 遍历与当前元素相邻的元素，变大变小两个方向。
                if (set.contains(neighbor)) {// 这一个就相当于判是否越界
                    // 只要将元素加入到队列中时，就需要将该元素从已访问集合中移出，避免重复访问。其实类似于将元素标记为已访问。
                    queue.offer(neighbor);
                    set.remove(neighbor);
                    count++;
                }
            }
        }
        return count;
    }





    public int bfs2(Set<Integer> set, int num) {
        Queue<Integer> queue = new LinkedList<>();// 广度遍历是使用队列实现
        // 只要将元素加入到队列中时，就需要将该元素从已访问集合中移出，避免重复访问。其实类似于将元素标记为已访问。
        queue.offer(num);// 将当前元素添加到队列中
        set.remove(num);// 将当前元素从哈希表中移出，其实就是类似于已访问，不能再次访问已经遍历过的元素。这个和将该元素标记为已访问效果类似
        int count = 0;
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            count++;// 这里是每次出队的时候加1
            // 每当搜索到整数i时，就判断输入的整数中是否包含i-1和i+1，如果包含就将其添加到队列中以便接下来搜索。
            // 这个就是图的每一个节点的邻接表
            int[] neighbors = new int[]{curr - 1, curr + 1};// 题目要求时相邻连续的最长序列。所以这里要向相邻小的，或者相邻大的方向遍历。
            for (int neighbor : neighbors) {// 遍历与当前元素相邻的元素
                if (set.contains(neighbor)) {// 这一个就相当于判越界
                    queue.offer(neighbor);
                    set.remove(neighbor);
                }
            }
        }
        return count;
    }





    // 解法2：图的深度遍历   用栈实现
    public int GraphDfs(int[][] grid, int i, int j) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rowLen = grid.length;// 行数
        int colLen = grid[0].length;// 列数
        // 创建了一个和输入矩阵相同大小的矩阵visited，它的作用是用一个布尔值标识矩阵中的每个值为1的格子是否已经到达过，用来确保每个格子只搜索一次。
        boolean[][] isVisited = new boolean[rowLen][colLen];
        Deque<int[]> stack = new LinkedList<>();// 用栈来实现
        stack.push(new int[]{i, j});// 将当前位置入栈，二维数组第一维是行，第二维是列。
        isVisited[i][j] = true;// 只要当前节点已经进入队列中，则将当前节点标识为已访问。如果是每次出队列时进行标记，可能会重复访问。
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int area = 0;
        while (!stack.isEmpty()) {
            int[] pos = stack.remove();// 将栈顶元素出栈
            area++;// 处理当前节点，将子图的面积加1或者说节点数加1
            for (int[] dir : dirs) {// 循环遍历与当前节点相邻的四个方向的节点
                int row = pos[0] + dir[0];
                int col = pos[1] + dir[1];
                if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length
                        && grid[row][col] == 1 && !isVisited[row][col]) {
                    stack.push(new int[]{row, col});// 将当前坐标的相邻节点【新节点】加入到栈中
                    isVisited[row][col] = true;// 将当前节点相邻节点【新节点】标识为已访问
                }
            }
        }
        return area;
    }





    // 递归的深度遍历
    public int graphDfs(int[][] grid, boolean[][] isVisited, int row, int col) {
        int area = 1;
        isVisited[row][col] = true;// 处理当前节点。只要当前节点已经进入队列中，则将当前节点标识为已访问。如果是每次出队列时进行标记，可能会重复访问。
        int rowLen = grid.length;
        int colLen = grid[0].length;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : dirs) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];
            if (newRow >= 0 && newRow < rowLen && newCol >= 0 && newCol < colLen && grid[newRow][newCol] == 1 && !isVisited[newRow][newCol]) {
                area += graphDfs(grid, isVisited, newRow, newCol);
            }
        }
        return area;
    }




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


}
