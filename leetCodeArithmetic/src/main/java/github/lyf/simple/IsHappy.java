package github.lyf.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyf
 */
public class IsHappy {
    /**
     *编写一个算法来判断一个数 n 是不是快乐数。
     *
     * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
     * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
     *
     * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
     * 示例：
     *
     * 输入：19
     * 输出：true
     * 解释：
     * 12 + 92 = 82
     * 82 + 22 = 68
     * 62 + 82 = 100
     * 12 + 02 + 02 = 1
     * @param n
     * @return
     */
    public  boolean isHappy(int n) {
        /**
         * 用map存储每次生成的数，生成下一个数之后先用map
         * 判断是否存在这个数，如果存在说明出现循环则直接返回false，否则就继续执行循环
         */
        Map<Integer,Integer> map=new HashMap<>(16);
        int tmp=n;
        while (tmp !=1){
            if(map.get(tmp)!=null){
                return  false;
            }
            map.put(tmp,1);
            tmp=getNextNum(tmp);
        }
        return true;
    }
    public boolean isHappy1(int n) {
        /**
         * 如果给定的数字最后会一直循环重复，那么快的指针（值）一定会追上慢的指针（值），也就是
         *         两者一定会相等。如果没有循环重复，那么最后快慢指针也会相等，且都等于1。
         */
        int fast=n;
        int slow=n;
        do{
            slow=getNextNum(slow);
            fast=getNextNum(fast);
            fast=getNextNum(fast);
        }while(slow!=fast);
        if(fast==1) {
            return true;
        } else {
            return false;
        }
    }
    public  int getNextNum(int num){
        int sum=0;
        while (num>9){
           int  temp=num%10;
           sum += temp*temp;
           num=num/10;
        }
        return sum+num*num;
    }

}
