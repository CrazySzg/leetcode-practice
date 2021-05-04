package com.cn.leetcode.tree;

import java.util.HashSet;
import java.util.Set;


/**
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * 输出: True
 *
 *
 * 案例 2:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 *
 * 输出: False
 */
public class Solution653 {

    private Set<Integer> set = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (findTarget(root.left, k)) {
            return true;
        }
        if (set.contains(k - root.val)) {
            return true;
        } else {
            set.add(root.val);
        }
        if (findTarget(root.right, k)) {
            return true;
        }
        return false;
    }
}
