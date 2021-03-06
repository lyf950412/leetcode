package github.lyf.simple;

/**
 * @author lyf
 */
public class NumRookCaptures {
    /**
     *在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。
     *
     * 车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。
     *
     * 返回车能够在一次移动中捕获到的卒的数量。
     *  board.length == board[i].length == 8
     * board[i][j] 可以是 'R'，'.'，'B' 或 'p'
     * 只有一个格子上存在 board[i][j] == 'R'
     *
     * @param board
     * @return
     */
    public static int numRookCaptures(char[][] board) {
        /**
         * 先找到白车R的位置，在根据上下左右四个方向依次去找p
         */
        int  [] rIndex=new int[2];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(board[i][j]=='R'){
                    rIndex[0]=i;
                    rIndex[1]=j;
                }
            }
        }
        int i=1;
        int num=0;
        while (i<=4){
            if(i==1){
                for(int r=rIndex[0]+1;r<8;r++){
                    if(board[r][rIndex[1]]=='B'){
                        break;
                    }else if(board[r][rIndex[1]]=='p'){
                        num++;
                        break;
                    }
                }
            }else if(i==2){
                for(int r=rIndex[0]-1;r>0;r--){
                    if(board[r][rIndex[1]]=='B'){
                        break;
                    }else if(board[r][rIndex[1]]=='p'){
                        num++;
                        break;
                    }
                }
            }else if(i==3){
                for(int r=rIndex[1]+1;r<8;r++){
                    if(board[rIndex[0]][r]=='B'){
                        break;
                    }else if(board[rIndex[0]][r]=='p'){
                        num++;
                        break;
                    }
                }
            }else if(i==4){
                for(int r=rIndex[1]-1;r>0;r--){
                    if(board[rIndex[0]][r]=='B'){
                        break;
                    }else if(board[rIndex[0]][r]=='p'){
                        num++;
                        break;
                    }
                }
            }
            i++;
        }
        return num;
    }

}
