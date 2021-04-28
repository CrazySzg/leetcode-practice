package com.cn.leetcode.tree;

import java.util.Stack;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 *
 *
 *
 * 示例：
 *
 * 输入：
 *
 *    1
 *     \
 *      3
 *     /
 *    2
 *
 * 输出：
 * 1
 *
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 *
 * @date 2021/4/28 23:22
 */
public class Solution530 {

    public int getMinimumDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur;
        int minimumDifference = Integer.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre == null) {
                    pre = root;
                    root = root.right;
                    continue;
                } else {
                    cur = root;
                    int difference = cur.val - pre.val;
                    if (difference < minimumDifference) {
                        minimumDifference = difference;
                    }
                    pre = cur;
                }

                root = root.right;
            }
        }
        return minimumDifference;
    }
}
