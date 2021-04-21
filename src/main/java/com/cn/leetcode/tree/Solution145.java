package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 *
 * @date 2021/4/21 22:56
 */
public class Solution145 {

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> vals = new ArrayList<>();
        List<Integer> left = postorderTraversal(root.left);
        List<Integer> right = postorderTraversal(root.right);
        if (!left.isEmpty()) {
            vals.addAll(left);
        }
        if (!right.isEmpty()) {
            vals.addAll(right);
        }
        vals.add(root.val);
        return vals;
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> vals = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = root;
        while (!stack.isEmpty()) {
            root = stack.peek();
            if (root.left != null && root.left != pre && root.right != pre) {
                pre = root;
                stack.push(root.left);
            } else if (root.right != null && root.right != pre) {
                pre = root;
                stack.push(root.right);
            } else {
                vals.add(root.val);
                pre = root;
                stack.pop();
            }
        }
        return vals;
    }
}
