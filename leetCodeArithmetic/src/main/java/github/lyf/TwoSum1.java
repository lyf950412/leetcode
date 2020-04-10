package github.lyf;

/**
 * @author lyf
 */
public class TwoSum1 {
    /**
     *把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
     * @param n
     * @return
     */
    public static double[] twoSum(int n) {
        double[] res=new double[5*n+1];
        int[][] dp=new int[n+1][6*n+1];
        for(int i=1;i<=6;i++) {
            dp[1][i]=1;
        }
        //第几个骰子
        for(int i=2;i<=n;i++){
            // n个骰子的和
            for(int j=i;j<=6*i;j++){
                //第n个骰子的点数
                for(int k=1;k<=6;k++) {
                    dp[i][j] += j>=k?dp[i-1][j-k]:0;
                }
            }
        }
        double total=Math.pow(6.0,n);
        for(int i=0;i<5*n+1;i++){
            res[i]=dp[n][i+n]/total;
        }
        return res;
    }
}
