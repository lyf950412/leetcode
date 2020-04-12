package github.lyf.middle;

/**
 * @author lyf
 */
public class SortArray {
    /**
     *给你一个整数数组 nums，请你将该数组升序排列。
     * 示例 1：
     * 输入：nums = [5,2,3,1]
     * 输出：[1,2,3,5]
     * 示例 2：
     * 输入：nums = [5,1,1,2,0,0]
     * 输出：[0,0,1,1,2,5]
     * 提示：
     * 1 <= nums.length <= 50000
     * -50000 <= nums[i] <= 50000
     * @param nums
     * @return
     */
    static int [] newNums;
    public static int[] sortArray(int[] nums) {
        newNums=nums;
        quicksort(0,nums.length-1);
        return newNums;
    }
    public static int[] sortArray1(int[] nums) {
        int max = -50001, min = 50001;
        for (int num: nums) {
            max = Math.max(num, max);
            min = Math.min(num, min);
        }


        int[] counter = new int[max - min + 1];
        for (int num: nums) {
            counter[num - min]++;
        }

        int idx = 0;
        for (int num = min; num <= max; num++) {
            int cnt = counter[num - min];
            while (cnt-- > 0) {
                nums[idx++] = num;
            }
        }
        return nums;
    }
   public static void quicksort(int left, int right) {
        int i, j, t, temp;
        if(left > right) return;
       //temp中存的就是基准数
        temp = newNums[left];
        i = left;
        j = right;
       //顺序很重要，要先从右边开始找
        while(i != j) {
            while(newNums[j] >= temp && i < j) {
                j--;
            }
            //再找右边的
            while(newNums[i] <= temp && i < j) {
                i++;
            }
            //交换两个数在数组中的位置
            if(i < j) {
                t = newNums[i];
                newNums[i] = newNums[j];
                newNums[j] = t;
            }
        }
        //最终将基准数归位
       newNums[left] = newNums[i];
       newNums[i] = temp;
       //继续处理左边的，这里是一个递归的过程
        quicksort(left, i-1);
       //继续处理右边的 ，这里是一个递归的过程
        quicksort(i+1, right);
    }
}
