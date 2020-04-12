package github.lyf.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyf
 */
public class LongestPalindrome {
    /**
     * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
     *
     * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
     * 注意:
     * 假设字符串的长度不会超过 1010。
     *  输入:
     *      "abccccdd"
     * 输出:
     *       7
     * 解释:
     * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        if(s.length()==1) return 1;
        Map<Character,Integer> map=new HashMap(16);
        int res=0;
        int leng=s.length();
        for(int i=0;i<leng;i++){
            char c = s.charAt(i);
            if(map.containsKey(c)){
                map.put(c,map.get(c)+1);
            }else {
                map.put(c,1);
            }
        }
        for(Map.Entry<Character,Integer> entry:map.entrySet()){
            res += (entry.getValue()/2)*2;
        }
        if(res<s.length()){
            res=res+1;
        }
        return res;
    }
}
