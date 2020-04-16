package github.lyf.middle;

/**
 * @author lyf
 */
public class UpdateMatrix {
    /**
     *给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     *
     * 两个相邻元素间的距离为 1 。
     *
     * 示例 1:
     * 输入:
     *
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 输出:
     *
     * 0 0 0
     * 0 1 0
     * 0 0 0
     * 示例 2:
     * 输入:
     *
     * 0 0 0
     * 0 1 0
     * 1 1 1
     * 输出:
     *
     * 0 0 0
     * 0 1 0
     * 1 2 1
     * 注意:
     *
     * 给定矩阵的元素个数不超过 10000。
     * 给定矩阵中至少有一个元素是 0。
     * 矩阵中的元素只在四个方向上相邻: 上、下、左、右
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        int[][] vector = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int row = matrix.length;
        int col = matrix[0].length;
        // 第一次遍历，正向遍历，根据相邻左元素和上元素得出当前元素的对应结果
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = row + col;
                }
                if (i > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j] + 1);
                }
                if (j > 0) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j - 1] + 1);
                }
            }
        }
        // 第二次遍历，反向遍历，根据相邻右元素和下元素及当前元素的结果得出最终结果
        for (int i = row - 1; i >= 0; i--) {
            for (int j = col - 1; j >= 0; j--) {
                if (i < row - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i + 1][j] + 1);
                }
                if (j < col - 1) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][j + 1] + 1);
                }
            }
        }
        return matrix;
    }
}
