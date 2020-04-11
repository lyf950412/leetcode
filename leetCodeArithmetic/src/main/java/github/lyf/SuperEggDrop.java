package github.lyf;

/**
 * @author lyf
 */
public class SuperEggDrop {
    /**
     *你将获得 K 个鸡蛋，并可以使用一栋从 1 到 N  共有 N 层楼的建筑。
     *
     * 每个蛋的功能都是一样的，如果一个蛋碎了，你就不能再把它掉下去。
     *
     * 你知道存在楼层 F ，满足 0 <= F <= N 任何从高于 F 的楼层落下的鸡蛋都会碎，从 F 楼层或比它低的楼层落下的鸡蛋都不会破。
     *
     * 每次移动，你可以取一个鸡蛋（如果你有完整的鸡蛋）并把它从任一楼层 X 扔下（满足 1 <= X <= N）。
     *
     * 你的目标是确切地知道 F 的值是多少。
     * 无论 F 的初始值如何，你确定 F 的值的最小移动次数是多少？
     * 示例 1：
     *
     * 输入：K = 1, N = 2
     * 输出：2
     * 解释：
     * 鸡蛋从 1 楼掉落。如果它碎了，我们肯定知道 F = 0 。
     * 否则，鸡蛋从 2 楼掉落。如果它碎了，我们肯定知道 F = 1 。
     * 如果它没碎，那么我们肯定知道 F = 2 。
     * 因此，在最坏的情况下我们需要移动 2 次以确定 F 是多少。
     * 示例 2：
     * 输入：K = 2, N = 6
     * 输出：3
     * 示例 3：
     *
     * 输入：K = 3, N = 14
     * 输出：4
     * 提示：
     * 1 <= K <= 100
     * 1 <= N <= 10000
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop(int K, int N) {
        if(K==1||N<=2) return N;
        int [][] dp=new int[K][N+1];
        for(int i=0;i<K;i++){
            dp[i][0]=0;
            dp[i][1]=1;
            dp[i][2]=2;
        }
        for(int i=3;i<N;i++){
            dp[0][i]=i;
        }
        for( int e = 1; e < K; e++ ){
            int x = 1;
            for( int f = 3; f <= N; f++ ){
                // x取交汇处
                if( dp[ e - 1 ][ x - 1 ] < dp[ e ][ f - x ] ){
                    x++;
                }
                // f(K,N) = max(f(K-1,X-1), f(k,N-X)) + 1
                dp[ e ][ f ] = dp[ e - 1 ][ x - 1 ] + 1;
            }
        }
        return  dp[K-1][N];
    }
    /**
     *  动态规划的dp定义和子问题定义确定了dp递推公式，不同的角度可能有不同的dp解法
     *  并且存在时间复杂度、空间复杂度上的差异
     *  这种dp反正我是想不到的，题解中的大佬牛逼
     *  这里给出这种的dp角度的问题求解
     *
     *  1、无论你在哪层楼扔鸡蛋，鸡蛋只可能摔碎或者没摔碎，碎了的话就测楼下，没碎的话就测楼上。
     *  2、无论你上楼还是下楼，总的楼层数 = 楼上的楼层数 + 楼下的楼层数 + 1（当前这层楼）。
     *  根据这个特点，可以写出下面的状态转移方程：
     *  dp[k][m] = dp[k][m - 1] + dp[k - 1][m - 1] + 1
     *  dp[k][m - 1] 就是楼上的楼层数，因为鸡蛋个数 k 不变，也就是鸡蛋没碎，扔鸡蛋次数 m 减一；
     *  dp[k - 1][m - 1] 就是楼下的楼层数，因为鸡蛋个数 k 减一，也就是鸡蛋碎了，同时扔鸡蛋次数 m 减一。
     *  上述递推公式可以这样理解，一次扔鸡蛋至少能推测1层楼，剩余m-1次扔鸡蛋则分别可以推测dp[k-1][m-1]和dp[k][m-1]层楼
     *  dp[k-1][m-1]表示如果这次扔鸡蛋破了，那么只剩下k-1个鸡蛋和m-1次扔鸡蛋的机会可以探测到的最高楼层数
     *  dp[k][m-1]表示这次扔鸡蛋没有婆，还剩下k个鸡蛋和m-1次扔鸡蛋机会可以探测到的最高楼层数
     *  同时还有本身扔鸡蛋的这一层楼
     *  一共能够推测的楼层就是上述三者之和
     * @param K
     * @param N
     * @return
     */
    public int superEggDrop1(int K, int N) {
        int [][] dp=new int[K+1][N+1];
        int m = 0;
        while (dp[K][m] < N) {
            m++;
            for (int k = 1; k <= K; k++) {
                dp[k][m] = 1 + dp[k-1][m-1] + dp[k][m-1];
            }
        }
        return m;
    }
}
