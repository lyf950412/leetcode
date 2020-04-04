package github.lyf;

import java.util.Calendar;

/**
 * @author lyf
 */
public class Trap {
    /**
     *给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，
     * 下雨之后能接多少雨水。
     *
     * 示例:
     *
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        /**
         * 每次遍历所有高度减一
         * 统计2个柱条之间0的个数即为雨滴的个数，依次循环
         * 该方法超时
         */
        int max=0;
        for(int h:height){
            max=Math.max(max,h);
        }
        int ans=0;
        for(int i=max;i>0;i--){
            ans+=caculate(height,0);
        }
        return ans;
    }
    public static int  caculate(int [] height,int ans){
        int zero=0;
        boolean flag=false;
        for(int j=0;j<height.length;j++){
            if(height[j]!=0){
                height[j]--;
                if(zero>0){
                    ans+=zero;
                    zero=0;
                }else {
                    flag=true;
                }
            }else if(flag){
                zero++;
            }
        }
        return ans;
    }
    public int trap1(int[] height) {
        /**
         * 先找到最高柱条的位置
         * 分别计算左右两边的水滴相加
         */
        int index = 0;
        int result = 0;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > height[index]) {
                index = i;
            }
        }
        int left = 0;
        int right = height.length - 1;
        int max_left = 0;
        int max_right = 0;
        //最长墙的左边的水滴
        while (left < index) {
            if (max_left > height[left]) {
                result += max_left - height[left];
            } else if (max_left < height[left]) {
                max_left = height[left];
            }
            left++;
        }
        //最长墙的右边的水滴
        while (right > index) {
            if (max_right > height[right]) {
                result += max_right - height[right];
            } else if (max_right < height[right]) {
                max_right = height[right];
            }
            right--;
        }
        return result;

    }
}
