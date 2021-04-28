package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * 515. 在每个树行中找最大值
 * 您需要在二叉树的每一行中找到最大的值。
 *
 * 示例：
 *
 * 输入:
 *
 *           1
 *          / \
 *         3   2
 *        / \   \
 *       5   3   9
 *
 * 输出: [1, 3, 9]
 * @date 2021/4/28 23:03
 */
public class Solution515 {

    public List<Integer> largestValues(TreeNode root) {
        Deque<TreeNode> queue = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int maxVal = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val > maxVal) {
                    maxVal = node.val;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ans.add(maxVal);
        }
        return ans;
    }

}
