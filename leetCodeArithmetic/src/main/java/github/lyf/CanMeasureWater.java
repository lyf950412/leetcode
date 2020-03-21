package github.lyf;

import java.util.*;

/**
 * @author lyf
 */
public class CanMeasureWater {
    /**
     * 水壶问题
     *  有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
     *
     * 如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
     *
     * 你允许：
     *
     * 装满任意一个水壶
     * 清空任意一个水壶
     * 从一个水壶向另外一个水壶倒水，直到装满或者倒空
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean canMeasureWater(int x, int y, int z) {
        if (z > x + y) return false;
        if (x == z || y == z || x + y == z) return true;

        // 保存搜索过的情况，防止无止境的搜索下去
        Set<List<Integer>> set = new HashSet<>();

        // 保存每次操作后，x，y中剩余的水的容量
        LinkedList<List<Integer>> res = new LinkedList<>();

        // 初始时，x y中均没有水
        List<Integer> list = Arrays.asList(0, 0);
        set.add(list);
        res.add(list);

        while (!res.isEmpty()) {
            List<Integer> poll = res.poll();
            int remain_x = poll.get(0);
            int remain_y = poll.get(1);
            if (remain_x == z || remain_y == z || remain_x + remain_y == z) {
                return true;
            }

            // 给x加满水
            List<Integer> p1 = Arrays.asList(x, remain_y);
            if (!set.contains(p1)) {
                set.add(p1);
                res.add(p1);
            }

            // 给y加满水
            List<Integer> p2 = Arrays.asList(remain_x, y);
            if (!set.contains(p2)) {
                set.add(p2);
                res.add(p2);
            }


            // 清空x的水
            List<Integer> p3 = Arrays.asList(0, remain_y);
            if (!set.contains(p3)) {
                set.add(p3);
                res.add(p3);
            }

            // 清空y的水
            List<Integer> p4 = Arrays.asList(remain_x, 0);
            if (!set.contains(p4)) {
                set.add(p4);
                res.add(p4);
            }

            // x向y倒水
            int tmp_x = (remain_x + remain_y) <= y ? 0 : remain_x + remain_y - y;
            int tmp_y = (remain_x + remain_y) < y ? remain_x + remain_y : y;
            List<Integer> p5 = Arrays.asList(tmp_x, tmp_y);
            if (!set.contains(p5)) {
                set.add(p5);
                res.add(p5);
            }


            // y向x倒水
            tmp_y = (remain_x + remain_y) <= x ? 0 : remain_x + remain_y - x;
            tmp_x = (remain_x + remain_y) < x ? remain_x + remain_y : x;
            List<Integer> p6 = Arrays.asList(tmp_x, tmp_y);
            if (!set.contains(p6)) {
                set.add(p6);
                res.add(p6);
            }

        }
        return false;
    }

    /**
     * 求最大公约数  mx+ny=z  保证z是 x、y的最大公约数的倍数
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static boolean canMeasureWater1(int x, int y, int z){
        if(z==0) return true;
        if(x==0||y==0) return false;
        if(x+y<z) return false;
        if(x+y==z) return true;
        int min = Math.min(x, y);
        int max=0;
        for(int i=1;i<=min;i++){
            if(x%i==0&&y%i==0){
                max=Math.max(i,max);
            }
        }
        if(z%max==0){
            return true;
        }
        return false;
    }
}
