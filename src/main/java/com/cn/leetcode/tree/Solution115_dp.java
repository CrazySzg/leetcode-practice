package com.cn.leetcode.tree;

/**
 * 115. 不同的子序列
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * <p>
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * <p>
 * 题目数据保证答案符合 32 位带符号整数范围。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
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
 * <p>
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
 * ^  ^^
 * babgbag
 * ^^^
 *
 * @date 2021/4/17 22:19
 */
public class Solution115_dp {
//    题目大意：字符串s有很多个子序列(不一定连续)，这些子序列字符串中，字符串t出现了多少次？
//    解题思想：采用动态规划，建立dp二维数组，dp[i][j]表示s.substring(0,i)[sub_s]的所有子序列字符串中，字符串t.substring(0,j)(sub_t)出现了多少次。最后返回dp[len(s)][len(t)]即为字符串s所有子序列字符串中，字符串t出现了多少次。
//    那么怎么找状态转移方程呢？
//    我们发现当sub_s新增了一个长度后，如果新增的字符（当前sub_s尾字符），与sub_t尾字符不匹配，那么似乎在sub_s上多了个"没用"的字符，sub_s所有子序列字符串中sub_t出现的次数没有任何变化。因此在这种状态下：dp[i][j]=dp[i-1][j];
//    如果新加入sub_s的这个字符与sub_t尾字符相匹配时，在没有增加这个字符的情况下，sub_t出现的次数是dp[i-1][j], 现在增加了并且和sub_t尾字符相匹配，因此还要在这个基础上加上两者此前的状态下的次数，即dp[i-1][j-1]，因为相比此前，两者都多了同一个字符，与两者没有加上这个相同字符时情况是一样的。因此这种情况下总和是dp[i-1][j]+dp[i-1][j-1].
//    比如：
//    sub_s=abcc,sub_t=ac时，sum(abcc,ac)=sum(abc,ac)+sum(abc,a);再比如：
//    sum(abcd,abd)=sum(abc,abd)+sum(abc,ab).
//    可以类比组合公式:c(m,n)=c(m-1,n-1)+c(m-1,n)去理解。
//    发现当j>i时，sub_t的长度已经超长，所以不可能满足条件。第一列为什么要全部设为1？因为j=1时sub_t长度为1，就一个字符，所以只要出现sub_s有字符等于它时就至少存在一个。
//
//    作者：CYINGENOHALT
//    链接：https://leetcode-cn.com/problems/distinct-subsequences/solution/tu-jie-dong-tai-gui-hua-by-cyingenohalt-nnlc/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= t.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }
}
