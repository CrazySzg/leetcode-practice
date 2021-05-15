package com.cn.leetcode.tree;

/**
 * @date 2021/5/13 22:36
 */
public class Solution701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        insertIntoBSTInternal(root, val);
        return root;
    }

    public void insertIntoBSTInternal(TreeNode root, int val) {
        if (root.left == null && root.right == null) {
            if (root.val > val) {
                root.left = new TreeNode(val);
            } else {
                root.right = new TreeNode(val);
            }
        } else if (root.val > val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            } else {
                insertIntoBSTInternal(root.left, val);
            }
        } else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            } else {
                insertIntoBSTInternal(root.right, val);
            }
        }
    }
}
