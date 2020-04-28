package github.lyf.middle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyf
 */
public class SingleNumbers {
    /**
     *一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。
     * 要求时间复杂度是O(n)，空间复杂度是O(1)。
     *
     *  
     *
     * 示例 1：
     *
     * 输入：nums = [4,1,4,6]
     * 输出：[1,6] 或 [6,1]
     * 示例 2：
     *
     * 输入：nums = [1,2,10,4,1,4,3,3]
     * 输出：[2,10] 或 [10,2]
     *  
     *
     * 限制：
     *
     * 2 <= nums <= 10000
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {
        List<Integer> res=new ArrayList<>();
        /**
         * 统计每个数出现的次数
         */
        Map<Integer,Integer> map=new HashMap<>(16);
        for(int num:nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        for(int key:map.keySet()){
            if(map.get(key)==1){
                res.add(key);
                if(res.size()==2){
                    break;
                }
            }
        }
        return new int[]{res.get(0),res.get(1)};
    }
    public int[] singleNumbers1(int[] nums) {
        /**
         *a^a=0
         * a^0=a
         * a^b^c=a^c^b
         * a&(-a)=最低位为1的二进制（从又到左）
         * 所有的异或结果得到sum=2^10=8
         * flag=-8&8=8
         * 可分为两组，一组为与flag相与等于1的[10]，另一组为0的[1,2,4,1,4,3,3]
         * 组内异或分别得到【10】【2】
         */
        int sum=0;
        //得到异或结果，即为不相同两个数的异或结果sum
        for(int num:nums) {
            sum^=num;
        }
        //得到sum的二进制的1的最低位
        int flag=(-sum)&sum;
        int result[]=new int[2];
        //分成两个组进行异或，每组异或后的结果就是不相同两个数的其中之一
        for(int num:nums){
            if((flag&num)==0)
                result[0]^=num;
            else{
                result[1]^=num;
            }
        }
        return result;
    }
}
