package github.lyf;

/**
 * @author lyf
 * 机器人的运动范围
 */
public class MovingCount {
    /**
     *地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
     * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。
     * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。
     * 请问该机器人能够到达多少个格子？
     * 示例 1：
     * 输入：m = 2, n = 3, k = 1
     * 输出：3
     * 示例 1：
     * 输入：m = 3, n = 1, k = 0
     * 输出：1
     * 提示：
     * 1 <= n,m <= 100
     * 0 <= k <= 20
     * @param m
     * @param n
     * @param k
     * @return
     */
    public static int movingCount(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        return dfs(0, 0, m, n, k, visited);
    }

    private static int dfs(int i, int j, int m, int n, int k, boolean visited[][]) {
        if (i < 0 || i >= m || j < 0 || j >= n || (i/10 + i%10 + j/10 + j%10) > k || visited[i][j]) {
            return 0;
        }
        visited[i][j] = true;
        //因为起点为左上角（0,0） 向上和向左可以省略
        // dfs(i + 1, j, m, n, k, visited) + dfs(i - 1, j, m, n, k, visited) +
        // dfs(i, j + 1, m, n, k, visited) + dfs(i, j - 1, m, n, k, visited) + 1
        return dfs(i + 1, j, m, n, k, visited) +dfs(i, j + 1, m, n, k, visited)+1;
    }

}
