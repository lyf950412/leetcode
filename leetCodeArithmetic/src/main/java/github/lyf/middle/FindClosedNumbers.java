package github.lyf.middle;

/**
 * @author lyf
 */
public class FindClosedNumbers {
    /**
     *下一个数。给定一个正整数，找出与其二进制表达式中1的个数相同且大小最接近的那两个数（一个略大，一个略小）。
     *
     * 示例1:
     *
     *  输入：num = 2（或者0b10）
     *  输出：[4, 1] 或者（[0b100, 0b1]）
     * 示例2:
     *
     *  输入：num = 1
     *  输出：[2, -1]
     * 提示:
     *
     * num的范围在[1, 2147483647]之间；
     * 如果找不到前一个或者后一个满足条件的正数，那么输出 -1。
     * @param num
     * @return
     */
    public int[] findClosedNumbers(int num) {
        int count=countNums(num);
        int [] ans=new int[]{-1,-1};
        for(int i=num+1;i<2147483647;i++){
            int i1 = countNums(i);
            if(i1==count){
                ans[0]=i;
                break;
            }
        }
        for(int i=num-1;i>=1;i--){
            int i1 = countNums(i);
            if(i1==count){
                ans[1]=i;
                break;
            }
        }
        return ans;
    }
    public int countNums(int num){
        int count=0;
        while (num>0){
            num=num & (num-1);
            count++;
        }
        return count;
    }
}
