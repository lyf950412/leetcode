package github.lyf;

/**
 * @author lyf
 */
public class GameOfLife {
    /**
     *给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
     *
     * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
     * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
     * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
     * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
     * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
     *
     * 示例：
     *
     * 输入：
     * [
     *   [0,1,0],
     *   [0,0,1],
     *   [1,1,1],
     *   [0,0,0]
     * ]
     * 输出：
     * [
     *   [0,0,0],
     *   [1,0,1],
     *   [0,1,1],
     *   [0,1,0]
     * ]
     *  
     * 进阶：
     *
     * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
     * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
     * @param board
     */
    public static void gameOfLife(int[][] board) {
        int [][] newBoard=new int[board.length][board[0].length];
        for(int i=0;i<newBoard.length;i++){
            for(int j=0;j<newBoard[0].length;j++){
                newBoard[i][j]=board[i][j];
            }
        }
        /**
         * 复制一份原有数组，循环遍历新数组并改变原数组
         */
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{1,1},{1,-1},{-1,1},{-1,-1}};
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                int l=newBoard[i][j];
                int dead=0;
                int alave=0;
                for(int[] dir:dirs){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    if(x < 0 || x > board.length-1 || y < 0 || y > board[0].length-1) {
                        continue;
                    }
                    if(newBoard[x][y]==0) {
                        dead++;
                    }else {
                        alave++;
                    }
                }
                if(l==0){
                    if(alave==3){
                        board[i][j]=1;
                    }

                }else {
                    if(alave==2||alave==3){
                        board[i][j]=1;
                    }else if(alave<2){
                        board[i][j]=0;
                    }else if(alave>3){
                        board[i][j]=0;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        gameOfLife(new int[][]{
                {0,1,0},
                {0,0,1},
                {1,1,1},
                {0,0,0}
        });
    }
}
