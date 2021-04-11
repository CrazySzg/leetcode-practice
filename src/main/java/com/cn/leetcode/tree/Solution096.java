package com.cn.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 96. 不同的二叉搜索树
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-binary-search-trees
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/11 16:25
 */
public class Solution096 {

    private Map<Integer, Integer> memory = new HashMap<>();
    public int numTrees(int n) {
//        return getAns(1, n);
        int[] ans = new int[n + 1];
        ans[0] = 1;   //   空树也是一种，大意了..
        ans[1] = 1;
        // 和序列的内容无关，只和序列的长度有关
        for (int i = 2; i <= n; i++) {
            for (int j = 1;j <= i; j++) {
                ans[i] += ans[j - 1] * ans[i - j];
            }
        }
        return ans[n];
//  卡特兰数
//        long C = 1;
//        for (int i = 0; i < n; ++i) {
//            C = C * 2 * (2 * i + 1) / (i + 2);
//        }
//        return (int) C;

    }

    private int getAns(int start, int end) {
        int ans = 0;
        if (start > end) {
            return 1;
        }
        if (memory.containsKey(end - start)) {
            return memory.get(end - start);
        }
        for (int i = start; i <= end; i++) {
            int left = getAns(start, i - 1);
            int right = getAns(i + 1, end);
            ans += left * right;
        }
        memory.put(end - start, ans);
        return ans;
    }
}
