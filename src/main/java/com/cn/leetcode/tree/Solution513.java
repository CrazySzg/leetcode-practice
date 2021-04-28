package com.cn.leetcode.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 513. 找树左下角的值
 * 给定一个二叉树，在树的最后一行找到最左边的值。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * <p>
 * 2
 * / \
 * 1   3
 * <p>
 * 输出:
 * 1
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入:
 * <p>
 * 1
 * / \
 * 2   3
 * /   / \
 * 4   5   6
 * /
 * 7
 * <p>
 * 输出:
 * 7
 * <p>
 * 注意: 您可以假设树（即给定的根节点）不为 NULL。
 *
 * @date 2021/4/28 22:57
 */
public class Solution513 {

    public int findBottomLeftValue(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode leftest = root;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = true;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag) {
                    leftest = node;
                    flag = false;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return leftest.val;
    }
}
