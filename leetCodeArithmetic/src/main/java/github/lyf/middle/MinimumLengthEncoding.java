package github.lyf.middle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author lyf
 */
public class MinimumLengthEncoding {
    /**
     *给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。
     *
     * 例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。
     *
     * 对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。
     *
     * 那么成功对给定单词列表进行编码的最小字符串长度是多少呢？
     *
     *  
     *
     * 示例：
     *
     * 输入: words = ["time", "me", "bell"]
     * 输出: 10
     * 说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
     *  
     *
     * 提示：
     *
     * 1 <= words.length <= 2000
     * 1 <= words[i].length <= 7
     * 每个单词都是小写字母 。
     *
     * @param words
     * @return
     */
    public static int minimumLengthEncoding(String[] words) {
        //降序排序
        Arrays.sort(words, (String word1, String word2) -> word2.length() - word1.length());
        StringBuffer sb = new StringBuffer();
        sb.append(words[0] + "#");
        /**
         * 循环判断后面的字符串是否为前面已经拼接字符串的子串
         */
        for(int i = 1; i < words.length; i++){
            //题目说「直到 "#" 结束」，所以要加#，不然存在包含但不符合的情况
            if(sb.indexOf(words[i] + "#") == -1){
                sb.append(words[i] + "#");
            }
        }
        return sb.length();
    }

    /**
     *由数据范围可知一个单词最多含有 7 个后缀，
     * 所以我们可以枚举单词所有的后缀。对于每个后缀，
     * 如果其存在 words 列表中，我们就将其从列表中删除。
     * 为了高效删除，我们将 words 用哈希集合（HashSet）来存储。
     *
     * @param words
     * @return
     */
    public int minimumLengthEncoding1(String[] words) {
        Set<String> good = new HashSet(Arrays.asList(words));
        for (String word: words) {
            for (int k = 1; k < word.length(); ++k)
                good.remove(word.substring(k));
        }

        int ans = 0;
        for (String word: good)
            ans += word.length() + 1;
        return ans;
    }

}
