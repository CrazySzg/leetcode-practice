package com.cn.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 115. 不同的子序列
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 *
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 题目数据保证答案符合 32 位带符号整数范围。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2：
 *
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 *
 * @date 2021/4/17 22:18
 */
public class Solution115 {

    private Map<String, Integer> cache = new HashMap<>();
    public int numDistinct(String s, String t) {
        if (s.equals(t)) {
            return 1;
        }
        return numDistinct(s, 0, t, 0);
    }

    public int numDistinct(String s, Integer i, String t, Integer j) {
        if (t.length() == j) {
            return 1;
        }
        int ans = 0;
        for (Integer start = i; start < s.length(); start++) {
            if (s.substring(start).length() < t.substring(j).length()) {
                break;
            }
            if (s.charAt(start) == t.charAt(j)) {
                String key = start.toString() + "_" + j.toString();
                if (cache.get(key) != null) {
                    ans += cache.get(key);
                } else {
                    int num =  numDistinct(s, start + 1, t, j + 1);
                    cache.put(key, num);
                    ans += num;
                }
            }
        }
        return ans;
    }
}
