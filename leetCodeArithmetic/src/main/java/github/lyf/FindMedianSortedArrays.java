package github.lyf;

/**
 * @author lyf
 */
public class FindMedianSortedArrays {
    /**
     *给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     *
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * 你可以假设 nums1 和 nums2 不会同时为空。
     *
     * 示例 1:
     *
     * nums1 = [1, 3]
     * nums2 = [2]
     *
     * 则中位数是 2.0
     * 示例 2:
     *
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     *
     * 则中位数是 (2 + 3)/2 = 2.5
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length;
        int length1 = nums2.length;
        if(length==0) return length1%2==0?(nums2[(length1/2)-1]+nums2[(length1/2)])/2.0:nums2[(length1/2)]/1.0;
        if(length1==0) return length%2==0?(nums1[(length/2)-1]+nums1[(length/2)])/2.0:nums1[(length/2)]/1.0;
        int [] newNums=new int[length+length1];
        //粗暴的合并排序
       // System.arraycopy(nums1,0,newNums,0,length);
        //System.arraycopy(nums2,0,newNums,length,length1);
       // Arrays.sort(newNums);
        int i=0,j=0,k=0;
        while (i<nums1.length&&j<nums2.length){
            if(nums1[i]<nums2[j]){
                newNums[k] = nums1[i];
                k++;
                i++;
            }
            else if(nums1[i]>nums2[j]){
                newNums[k] = nums2[j];
                k++;
                j++;
            }
            else{
                newNums[k] = nums1[i];
                k++;
                newNums[k] = nums2[j];
                k++;
                i++;j++;
            }
        }
        if(i==nums1.length){
            for(; j<nums2.length; j++){
                newNums[k] = nums2[j];
                k++;
            }
        } else if(j==nums2.length){
            for(; i<nums1.length; i++){
                newNums[k] = nums1[i];
                k++;
            }
        }
        return k%2==0?(newNums[k/2]+newNums[k/2-1]) / 2.0:newNums[k/2]*1.0;
    }
}
