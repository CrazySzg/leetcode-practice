package com.cn.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 337. 打家劫舍 III
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * @date 2021/4/24 16:50
 */
public class Solution337 {

    public int rob(TreeNode root) {
        HashMap<TreeNode, Integer> memo = new HashMap<>();
        return robInternal(root, memo);
    }

    public int robInternal(TreeNode root, HashMap<TreeNode, Integer> memo) {
        if (root == null) {
            return 0;
        }
        if (memo.containsKey(root)) {
            return memo.get(root);
        }
        int money = root.val;

        if (root.left != null) {
            money += (robInternal(root.left.left, memo) + robInternal(root.left.right, memo));
        }
        if (root.right != null) {
            money += (robInternal(root.right.left, memo) + robInternal(root.right.right, memo));
        }
        int result = Math.max(money, robInternal(root.left, memo) + robInternal(root.right, memo));
        memo.put(root, result);
        return result;
    }

    // ==========================================================================

    // 表示选中当前节点时，获得的最大数额
    Map<TreeNode, Integer> f = new HashMap<>();
    // 表示不选中当前节点时，获得的最大数额
    Map<TreeNode, Integer> g = new HashMap<>();
    public int rob2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root.left);
        dfs(root.right);

        int r1 = root.val + g.getOrDefault(root.left, 0) + g.getOrDefault(root.right, 0);
        int r2 = Math.max(f.getOrDefault(root.left, 0), g.getOrDefault(root.left, 0)) + Math.max(f.getOrDefault(root.right, 0), g.getOrDefault(root.right, 0));
        f.put(root, r1);
        g.put(root, r2);
        return Math.max(r1, r2);
    }

    // ==========================================================================

    public int rob3(TreeNode root) {
        int[] res = robInternal(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robInternal(TreeNode root) {
        int[] res = new int[2];
        if (root == null) {
            return res;
        }
        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        int res1 = root.val + left[1] + right[1];
        int res2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return new int[]{res1, res2};
    }

}
