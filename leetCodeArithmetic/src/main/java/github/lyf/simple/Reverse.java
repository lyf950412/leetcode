package github.lyf.simple;

/**
 * @author lyf
 */
public class Reverse {
    /**
     *给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *
     * 示例 1:
     *
     * 输入: 123
     * 输出: 321
     *  示例 2:
     *
     * 输入: -123
     * 输出: -321
     * 示例 3:
     *
     * 输入: 120
     * 输出: 21
     * 注意:
     * @param x
     * @return
     */
    public int reverse(int x) {
        boolean flag=false;
        if(x<0) flag=true;
        x = Math.abs(x);
        String s = String.valueOf(x);
        double res=0;
        for(int i=s.length()-1;i>=0;i--){
            double v = (s.charAt(i) - '0') * Math.pow(10, i);
            if(v>Integer.MAX_VALUE){
                 return 0;
             }
            if((res =res+v)>Integer.MAX_VALUE){
                return 0;
            }
        }
        if(flag && -(int) res<Integer.MIN_VALUE){
            return 0;
        }
        return (int)(flag? -res: res);
    }
    public  int reverse1(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;
            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }
}
