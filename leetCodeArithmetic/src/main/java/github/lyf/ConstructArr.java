package github.lyf;

/**
 * @author lyf
 */
public class ConstructArr {
    /**
     *给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
     * 示例:
     *
     * 输入: [1,2,3,4,5]
     * 输出: [120,60,40,30,24]
     * 提示：
     * 所有元素乘积之和不会溢出 32 位整数
     * a.length <= 100000
     * @param a
     * @return
     */
    public static int[] constructArr(int[] a) {
        int []b=new int[a.length];
        for(int i=0;i<b.length;i++){
            if(i>0){
                b[i]=multiply(a,0,i)*multiply(a,i+1,a.length);
            }else{
                b[i]=multiply(a,i+1,a.length-i);
            }
        }
        return b;
    }
    public static int multiply(int[] a,int start,int end){
        int res=1;
        for(int i=start;i<end;i++){
            res*=a[i];
        }
        return res;
    }

    public static int[] constructArr1(int[] a) {
        int [] left = new int [a.length];
        int [] right = new int [a.length];
        int [] ans = new int [a.length];
        int k = 1;
        for(int i = 0; i < a.length; i++){
            left[i] = k;
            k = k * a[i];
        }
        k = 1;
        for(int i = a.length - 1; i >= 0; i--){
            right[i] = k;
            k = k * a[i];
        }
        for(int i = 0; i < a.length; i++){
            ans[i] = left[i] * right[i];
        }
        return ans;
    }

}
