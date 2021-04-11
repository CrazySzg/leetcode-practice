package com.cn.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 *
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 *
 *
 * @date 2021/4/11 11:19
 */
public class Solution095 {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return getAns(1,n);
    }

    public List<TreeNode> getAns(int start, int end) {
        List<TreeNode> ans = new LinkedList<>();
        if (start > end) {
            ans.add(null);
            return ans;
        }
        for (int i = start; i <= end;i++) {
            List<TreeNode> left = getAns(start,i - 1);
            List<TreeNode> right = getAns(i + 1, end);

            for (TreeNode l: left) {
                for (TreeNode r: right) {
                    TreeNode n = new TreeNode(i);
                    n.left = l;
                    n.right = r;
                    ans.add(n);
                }
            }
        }
        return ans;
    }
}
