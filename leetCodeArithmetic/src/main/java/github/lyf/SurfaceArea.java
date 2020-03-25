package github.lyf;

/**
 * @author lyf
 */
public class SurfaceArea {
    /**
     * 在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。
     *
     * 每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。
     *
     * 请你返回最终形体的表面积。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：[[2]]
     * 输出：10
     * 示例 2：
     *
     * 输入：[[1,2],[3,4]]
     * 输出：34
     * 示例 3：
     *
     * 输入：[[1,0],[0,2]]
     * 输出：16
     * 示例 4：
     *
     * 输入：[[1,1,1],[1,0,1],[1,1,1]]
     * 输出：32
     * 示例 5：
     *
     * 输入：[[2,2,2],[2,1,2],[2,2,2]]
     * 输出：46
     *  
     *
     * 提示：
     *
     * 1 <= N <= 50
     * 0 <= grid[i][j] <= 50
     */
    public static int surfaceArea(int[][] grid) {
        int n = grid.length, area = 0,sum=0;
        /**
         * 先计算总共有多少立方体，算出总的立方体所有的表面积，
         * 在计算出有多少个面贴合了，
         * 用总的表面积减去2倍贴合面的数量，得出最后结果
         */
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 先把grid[i][j]赋值给level
                int level = grid[i][j];
                sum+=level;
                if (level > 0) {
                    // 当前ij贴合的表面
                    area += level-1;
                    //  i 与 i-1 相贴的表面
                    area += i > 0? Math.min(level, grid[i - 1][j]): 0;
                    //  j 与 j-1 相贴的面积
                    area += j > 0? Math.min(level, grid[i][j - 1]): 0;
                }
            }
        }
        return sum*6-area*2;
    }

}
