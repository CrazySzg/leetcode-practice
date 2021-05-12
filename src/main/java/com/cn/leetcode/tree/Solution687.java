package com.cn.leetcode.tree;

/**
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 5
 * / \
 * 4   5
 * / \   \
 * 1   1   5
 * 输出:
 * <p>
 * 2
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 4   5
 * / \   \
 * 4   4   5
 * 输出:
 * <p>
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 * <p>
 * 相似题目 Solution543
 *
 * @date 2021/5/11 22:36
 */
public class Solution687 {

    int longestPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathInternal(root);
        return longestPath;
    }

    private int longestUnivaluePathInternal(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftPath = longestUnivaluePathInternal(root.left);
        int rightPath = longestUnivaluePathInternal(root.right);
        int leftSidePath = 0;
        int rightSidePath = 0;
        if (root.left != null && root.left.val == root.val) {
            leftSidePath += 1 + leftPath;
        }
        if (root.right != null && root.right.val == root.val) {
            rightSidePath += 1 + rightPath;
        }
        longestPath = Math.max(longestPath, leftSidePath + rightSidePath);
        return Math.max(leftSidePath, rightSidePath);
    }
}
