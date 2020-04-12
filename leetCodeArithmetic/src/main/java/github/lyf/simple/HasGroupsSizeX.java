package github.lyf.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyf
 */
public class HasGroupsSizeX {
    /**
     * 给定一副牌，每张牌上都写着一个整数。
     * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
     * 每组都有 X 张牌。
     * 组内所有的牌上都写着相同的整数。
     * 仅当你可选的 X >= 2 时返回 true。
     * 示例 1：
     *
     * 输入：[1,2,3,4,4,3,2,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[3,3]，[4,4]
     * 示例 2：
     *
     * 输入：[1,1,1,2,2,2,3,3]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 3：
     *
     * 输入：[1]
     * 输出：false
     * 解释：没有满足要求的分组。
     * 示例 4：
     *
     * 输入：[1,1]
     * 输出：true
     * 解释：可行的分组是 [1,1]
     * 示例 5：
     *
     * 输入：[1,1,2,2,2,2]
     * 输出：true
     * 解释：可行的分组是 [1,1]，[2,2]，[2,2]
     *
     * 提示：
     *
     * 1 <= deck.length <= 10000
     * 0 <= deck[i] < 10000
     * @param deck
     * @return
     */
    public static boolean hasGroupsSizeX(int[] deck) {
        int n = deck.length;
        if(n<2) return false;
        /**
         * 先统计每个数字出现的个数，在算出数组长度所有因子（去除n）,即所有可能的分组，
         * 在循环判断每个分组包含的个数与数组中每个数字的个数是否存在因数关系，存在返回true
         */
        Map<Integer,Integer> map=new HashMap<>(16);
        for(int i:deck){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        List<Integer> list=new ArrayList<>();
        for(int i=1;i<n;i++){
            if(n%i==0){
                list.add(i);
            }
        }
        boolean flag=false;
        for(Integer tem:list){
            int gap=n/tem;
            boolean inflag=true;
            for(Integer el:map.keySet()){
                if(map.get(el)%gap!=0){
                    inflag=false;
                }
            }
            if(inflag==true){
                return inflag;
            }
        }
        return flag;
    }
    public static boolean hasGroupsSizeX1(int[] deck) {
        if(deck.length<2) return false;
        /**
         * 先统计每个数字出现的个数，求出次数之间的公共因子，判断是否大于等2
         */
        Map<Integer,Integer> map=new HashMap<>(16);
        for(int i:deck){
            map.put(i,map.getOrDefault(i,0)+1);
        }
        int dcg=0;
        for(Integer el:map.keySet()){
            if(dcg==0){
                dcg=map.get(el);
            }else {
                dcg=gcd(dcg,map.get(el));
            }
        }

        return dcg >= 2;
    }
    public static int gcd(int x, int y) {
        return x == 0 ? y : gcd(y%x, x);
    }
}
