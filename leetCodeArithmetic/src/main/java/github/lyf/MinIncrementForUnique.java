package github.lyf;

import java.util.*;

/**
 * @author lyf
 */
public class MinIncrementForUnique {
    /**
     *  给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。
     *
     * 返回使 A 中的每个值都是唯一的最少操作次数。
     *
     * 示例 1:
     *
     * 输入：[1,2,2]
     * 输出：1
     * 解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
     * 示例 2:
     *
     * 输入：[3,2,1,2,1,7]
     * 输出：6
     * 解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
     * 可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。
     * 提示：
     *
     * 0 <= A.length <= 40000
     * 0 <= A[i] < 40000
     * @param A   计数暴力题解
     * @return
     */
    public static int minIncrementForUnique(int[] A) {
        if(A.length==0) return 0;
        //给每个数字计数，只需将每个位置上的数字都变为1即可   长度80000是最坏的情况
        int [] c=new int[80000];
        int min=A[0];
        for(int a:A ){
            c[a] ++;
            min=Math.min(min,a);
        }
        int length = c.length;
        int move=0;
        /**
         * 先从最小的下标开始循环，  判断当前下标的数出现次数是否大于2，大于2就开始内层循环，并将当前下标的数字减一，
         *  从当前下标加1的位置开始进行内层循环，直到找到下标位置的数字为0，将其置为1，并计算当前下标与之外层循环的下标之差，
         *  代表此次所需移动的次数
         */
        for(int i=min;i<=length;i++){
            while (c[i]!=1&&c[i]!=0){
                c[i]--;
                inner:for(int j=i+1;j<=length;j++){
                    if(c[j]==0){
                        c[j]++;
                        move +=j-i;
                        break inner;
                    }
                }
            }
        }
       return  move;
    }
    public static int minIncrementForUnique1(int[] A) {
        if(A.length==0) return 0;
        // 先排序
        Arrays.sort(A);
        int move = 0;
        // 遍历数组，若当前元素小于等于它的前一个元素，则将其变为前一个数+1
        for (int i = 1; i < A.length; i++) {
            if (A[i] <= A[i - 1]) {
                int pre = A[i];
                A[i] = A[i - 1] + 1;
                move += A[i] - pre;
            }
        }
        return move;
    }
}
