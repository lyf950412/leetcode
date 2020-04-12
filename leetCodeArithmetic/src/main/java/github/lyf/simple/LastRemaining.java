package github.lyf.simple;

import java.util.ArrayList;

/**
 * @author lyf
 */
public class LastRemaining {
    /**
     *0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
     *
     * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
     * 示例 1：
     * 输入: n = 5, m = 3
     * 输出: 3
     * 示例 2：
     * 输入: n = 10, m = 17
     * 输出: 2
     * 限制：
     * 1 <= n <= 10^5
     * 1 <= m <= 10^6
     * @param n
     * @param m
     * @return
     */
    public static int lastRemaining(int n, int m) {
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;
        }
        return list.get(0);
    }
    public static int lastRemaining1(int n, int m) {
        int ans = 0;
        // 最后一轮剩下2个人，所以从2开始反推
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }
}
