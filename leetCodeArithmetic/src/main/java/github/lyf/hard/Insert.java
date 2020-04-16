package github.lyf.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author lyf
 */
public class Insert {
    /**
     * 插入区间
     给出一个无重叠的 ，按照区间起始端点排序的区间列表。

     在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。

     示例 1:

     输入: intervals = [[1,3],[6,9]], newInterval = [2,5]
     输出: [[1,5],[6,9]]
     示例 2:

     输入: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     输出: [[1,2],[3,10],[12,16]]
     解释: 这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
     * @param intervals
     * @param newInterval
     * @return
     */
    public  int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0], newEnd = newInterval[1];
        int idx = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<int[]>();

        // 先插入在newInterval左边的 区间
        while (idx < n && newStart > intervals[idx][0]) {
            output.add(intervals[idx++]);
        }

        // 在插入 newInterval  如果有重合区间就合并
        int[] interval = new int[2];
        if (output.isEmpty() || output.getLast()[1] < newStart) {
            output.add(newInterval);
        } else {
            // 如果有重叠，与最后一个区间合并
            interval = output.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            output.add(interval);
        }

        // 再添加剩余的区间，并在需要时与newInterval合并
        while (idx < n) {
            interval = intervals[idx++];
            int start = interval[0], end = interval[1];
            if (output.getLast()[1] < start) {
                output.add(interval);
            }else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], end);
                output.add(interval);
            }
        }
        return output.toArray(new int[output.size()][2]);
    }
    public  int[][] insert1(int[][] intervals, int[] newInterval) {
        int [][] asn=new int[intervals.length+1][2];
        System.arraycopy(intervals,0,asn,0,intervals.length);
        asn[intervals.length+1]=newInterval;
        int n = asn.length;
        Arrays.sort(asn, (o1, o2) -> o1[0] - o2[0]);

        // 遍历所有区间，如果当前的起点在上一个末尾之前，则合并
        //              如果在上一个区间末尾之后，则把上一个区间放到答案中去
        List<int[]> ans = new ArrayList<>();
        int[] pre = asn[0];
        for(int i = 1; i < n; i++) {
            if(asn[i][0] <= pre[1]) {
                pre[1] = Math.max(pre[1], asn[i][1]);
            } else {
                ans.add(pre);
                pre = asn[i];
            }
        }
        // 把最后一个放进去
        ans.add(pre);
        return ans.toArray(new int[ans.size()][2]);
    }
}
