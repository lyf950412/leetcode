package github.lyf;

/**
 * @author lyf
 */
public class MaxDepthAfterSplit {
    /**
     *有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。
     * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。
     * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和 B，并使这两个字符串的深度最小。
     * 不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。
     * A 或 B 中的元素在原字符串中可以不连续。
     * A.length + B.length = seq.length
     * max(depth(A), depth(B)) 的可能取值最小。
     * 划分方案用一个长度为 seq.length 的答案数组 answer 表示，编码规则如下：
     *
     * answer[i] = 0，seq[i] 分给 A 。
     * answer[i] = 1，seq[i] 分给 B 。
     * 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。
     * 示例 1：
     *
     * 输入：seq = "(()())"
     * 输出：[0,1,1,1,1,0]
     * 示例 2：
     *
     * 输入：seq = "()(())()"
     * 输出：[0,0,0,1,1,0,1,1]
     *  
     *
     * 提示：
     *
     * 1 <= text.size <= 10000
     * @param seq
     * @return
     */
    public int[] maxDepthAfterSplit(String seq) {
        /**
         *维护一个栈 s，从左至右遍历括号字符串中的每一个字符：
         * 如果当前字符是 (，就把 ( 压入栈中，此时这个 ( 的嵌套深度为栈的高度；
         * 如果当前字符是 )，此时这个 ) 的嵌套深度为栈的高度，随后再从栈中弹出一个 (。
         * 下面给出了括号序列 (()(())()) 在每一个字符处的嵌套深度：
         * 括号序列   ( ( ) ( ( ) ) ( ) )
         * 下标编号   0 1 2 3 4 5 6 7 8 9
         * 嵌套深度   1 2 2 2 3 3 2 2 2 1
         * 知道如何计算嵌套深度，问题就很简单了：只要在遍历过程中，我们保证栈内一半的括号属于序列 A，一半的括号属于序列 B，那么就能保证拆分后最大的嵌套深度最小，是当前最大嵌套深度的一半。要实现这样的对半分配，我们只需要把奇数层的 ( 分配给 A，偶数层的 ( 分配给 B 即可。对于上面的例子，我们将嵌套深度为 1 和 3 的所有括号 (()) 分配给 A，嵌套深度为 2 的所有括号 ()()() 分配给 B。
         *
         * 此外，由于在这个问题中，栈中只会存放 (，因此我们不需要维护一个真正的栈，只需要用一个变量模拟记录栈的大小。
         */
        int []ans = new int[seq.length()];
        int d = 0;
        for(int i=0;i<seq.length();i++){
            if (seq.charAt(i) == '(') {
                d += 1;
                ans[i]=d%2;
            }
            if (seq.charAt(i) == ')'){
                ans[i]=d%2;
                d -= 1;
            }
        }
        return ans;
    }
}
