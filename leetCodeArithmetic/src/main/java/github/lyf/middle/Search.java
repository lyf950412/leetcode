package github.lyf.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyf
 */
public class Search {
    /**
     *假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     *
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     *
     * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
     *
     * 你可以假设数组中不存在重复的元素。
     *
     * 你的算法时间复杂度必须是 O(log n) 级别。
     *
     * 示例 1:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 0
     * 输出: 4
     * 示例 2:
     *
     * 输入: nums = [4,5,6,7,0,1,2], target = 3
     * 输出: -1
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int length = nums.length;
        Map<Integer,Integer> index=new HashMap<>(16);
        for(int i=0;i<length;i++){
            index.put(nums[i],i);
        }
        return index.getOrDefault(target,-1);
    }
    public int search1(int[] nums, int target) {
        /**
         * 思路：二分搜索
         */
        int len = nums.length;
        int left = 0, right = len-1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] < nums[right]){
                if(nums[mid] < target && target <= nums[right]) {
                    left = mid+1;
                } else {
                    right = mid-1;
                }
            } else{
                if(nums[left] <= target && target < nums[mid]) {
                    right = mid-1;
                } else {
                    left = mid+1;
                }
            }
        }
        return -1;
    }
}
