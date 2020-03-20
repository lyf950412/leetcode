package github.lyf;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lyf
 */
public class GetLeastNumbers {
    /**
     *  最小的k个数
     *  输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
     *
     * 示例 1：
     *
     * 输入：arr = [3,2,1], k = 2
     * 输出：[1,2] 或者 [2,1]
     * 示例 2：
     *
     * 输入：arr = [0,1,2,1], k = 1
     * 输出：[0]
     * 限制：
     *
     * 0 <= k <= arr.length <= 10000
     * 0 <= arr[i] <= 10000
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
        List<Integer> collect1 =
                collect.stream().sorted().limit(k).collect(Collectors.toList());
        System.out.println(collect1);
        int [] res =collect1.stream().mapToInt(Integer::intValue).toArray();

        return res;
    }

    /**
     * 计数排序
     * @param arr
     * @param k
     * @return
     */
    public static int[] getLeastNumbers1(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num: arr) {
            counter[num]++;
        }
        // 根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }

    /**
     * 快速排序
     * @param arr
     * @param start
     * @param end
     */
    public static void quickSort(int[] arr,int start, int end) {
        if(start >= end)
            return;
        else {
            int temp = arr[start];
            int index = start + 1, low = start, high = end;
            while(index <= high) {
                if(temp > arr[index]) swap(arr,low++,index++);
                else if(temp < arr[index]) swap(arr,high--,index);
                else index++;
            }
            quickSort(arr,start, low - 1);
            quickSort(arr,high + 1, end);
        }
    }
    public static void swap(int [] arr,int t1, int t2){
        int temp;
        temp = arr[t1];
        arr[t1]= arr[t2];
        arr[t2] = temp;
    }
}
