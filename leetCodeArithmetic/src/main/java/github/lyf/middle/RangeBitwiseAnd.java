package github.lyf.middle;

/**
 * @author lyf
 */
public class RangeBitwiseAnd {
    /**
     *给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
     *
     * 示例 1: 
     *
     * 输入: [5,7]
     * 输出: 4
     * 示例 2:
     *
     * 输入: [0,1]
     * 输出: 0
     * @param m
     * @param n
     * @return
     */

    public int rangeBitwiseAnd(int m, int n) {
        /**
         * 当一个数+1时，总会有这么一个规律“某一位后的数字，全部被置为相反数”。举个例子：
         *             010111 + 1 = 011000，则010111 & 011000 = 010000。那么，x & (x+1) 后几位相反数的“与操作”，结果总为0。
         *     所以，当(m,m+1,...n-1,n)进行连续“与操作”时，会按照上述规律被抵消很大一部分，而只剩下n的前缀部分，最后只需将n归位。举个例子：
         *
         *     m = 5(0101), n = 7 (0111)。不停右移，得到n前缀部分为01，最后归位前缀得res=0100=4
         */
        int offset = 0;
        for (; m != n; ++offset) {
            m >>= 1;
            n >>= 1;
        }
        return n << offset;
    }
    public int rangeBitwiseAnd1(int m, int n) {
        while (m < n) n &= n - 1;
        return n;
    }
}
