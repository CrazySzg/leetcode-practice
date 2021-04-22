package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 *
 * 输入:
 *
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 *
 * 输出: ["1->2->5", "1->3"]
 *
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * @date 2021/4/22 22:34
 */
public class Solution257 {

    private List<String> ans = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return ans;
        }
        if (root.left == null && root.right == null) {
            ans.add(root.val + "");
            return ans;
        }
        binaryTreePaths(root.left, "" + root.val);
        binaryTreePaths(root.right, "" + root.val);
        return ans;
    }

    public void binaryTreePaths(TreeNode root, String path) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            path = path + "->" + root.val;
            ans.add(path);
            return;
        }
        path = path + "->" + root.val;
        binaryTreePaths(root.left, path);
        binaryTreePaths(root.right, path);
    }
}
