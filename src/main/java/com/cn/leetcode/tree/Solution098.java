package com.cn.leetcode.tree;

import java.util.Stack;

/**
 * @date 2021/4/12 22:24
 */
public class Solution098 {

    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        int last = 0;
        boolean init = false;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (!init) {
                    init = true;
                } else if (root.val <= last) {
                    return false;
                }
                last = root.val;
                root = root.right;
            }
        }
        return true;
    }

    public boolean isValidBST2(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

//    函数表示考虑以 root 为根的子树，
//    判断子树中所有节点的值是否都在 (l,r)(l,r) 的范围内（注意是开区间）。
//    如果 root 节点的值 val 不在 (l,r)(l,r) 的范围内说明不满足条件直接返回，
//    否则我们要继续递归调用检查它的左右子树是否满足，如果都满足才说明这是一棵二叉搜索树。
    private boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }

}
