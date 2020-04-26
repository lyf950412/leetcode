package github.lyf.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyf
 */
public class Permute {
    /**
     *给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        // 定义 boolean 数组标记已经拼接过的数。
        boolean[] visited = new boolean[nums.length];
        // 回溯遍历拼接 list。
        backtrack(ans, nums, new ArrayList<Integer>(), visited);
        return ans;
    }

    private void backtrack(List<List<Integer>> ans, int[] nums, ArrayList<Integer> curr, boolean[] visited) {
        // 拼接长度等于原数组长度，表示本轮拼接完成。
        if (curr.size() == nums.length) {
            // 将拼接好的 list 内容拷贝到新建的 list 中。
            ans.add(new ArrayList<>(curr));
            return;
        }
        // 遍历原数组进行回溯拼接。
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数已经拼接过，则直接遍历下一个数。
            if (visited[i]) {
                continue;
            }
            // 拼接当前数并标记为已拼接过。
            curr.add(nums[i]);
            visited[i] = true;
            // 继续递归拼接下一个数。
            backtrack(ans, nums, curr, visited);
            // 还原当前 list 的拼接状态，继续遍历下一个数。
            curr.remove(curr.size() - 1);
            visited[i] = false;
        }
    }
}
