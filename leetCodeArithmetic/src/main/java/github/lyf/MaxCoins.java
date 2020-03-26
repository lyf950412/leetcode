package github.lyf;

/**
 * @author lyf
 */
public class MaxCoins {
    /**
     *有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     *
     * 现在要求你戳破所有的气球。每当你戳破一个气球 i 时，你可以获得 nums[left] * nums[i] * nums[right] 个硬币。 这里的 left 和 right 代表和 i 相邻的两个气球的序号。注意当你戳破了气球 i 后，气球 left 和气球 right 就变成了相邻的气球。
     *
     * 求所能获得硬币的最大数量。
     *
     * 说明:
     *
     * 你可以假设 nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
     * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
     * 示例:
     *
     * 输入: [3,1,5,8]
     * 输出: 167
     * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
     *      coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
     * @param nums
     * @return
     */
    public static int maxCoins(int[] nums) {
        int length=nums.length;
        if(length==0) return 0;
        int [][] dp=new int[length][length];
        int coins=0;
        for(int i=length-1;i>=0;i--){
            for(int j=i;j<length;j++){
                for(int k=i;k<=j;k++){
                    coins = nums[k];
                    if (i-1 >= 0) {
                        coins *= nums[i-1];
                    }
                    if (j+1 < length ){
                        coins *= nums[j+1];
                    }
                    if (k-1 >= 0) {
                        coins += dp[i][k-1];
                    }
                    if (k+1 <length) {
                        coins += dp[k+1][j];
                    }
                    dp[i][j] = Math.max(dp[i][j], coins);
                }
            }
        }
        return dp[0][length-1];
    }

    /**
     * 定义方法 dp，使其返回开区间 (left, right) 中所能得到的最大金币数。
     * 对于基础情况 (即 left + 1 == right)，这时候区间内没有整数，也没有气球需要加进去，
     * 因此 dp[left][right] = 0。随后在区间中加入气球，把问题分割成左右两部分来处理，找到最大金币数。
     *
     * 在添加第 i 个气球后能得到的最大金币数为：
     *
     * nums[left] * nums[i] * nums[right] + dp(left, i) + dp(i, right)
     * 其中 nums[left] * nums[i] * nums[right] 为加入第 i 个气球所能得到的金币数，dp(left, i) + dp(i, right) 为左右两部分分别能得到的最大金币数。
     *
     * @param nums
     * @return
     */
    public static int maxCoins1(int[] nums) {
        int n = nums.length + 2;
        int[] new_nums = new int[n];

        for(int i = 0; i < nums.length; i++){
            new_nums[i+1] = nums[i];
        }

        new_nums[0] = new_nums[n - 1] = 1;

        int[][] dp = new int[n][n];

        for (int left = n-2; left > -1; left--) {
            for (int right = left+2; right < n; right++) {
                for (int i = left + 1; i < right; i++) {
                    dp[left][right] = Math.max(dp[left][right],
                            new_nums[left] * new_nums[i] * new_nums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
