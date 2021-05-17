package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 894. 所有可能的满二叉树
 * 满二叉树是一类二叉树，其中每个结点恰好有 0 或 2 个子结点。
 * <p>
 * 返回包含 N 个结点的所有可能满二叉树的列表。 答案的每个元素都是一个可能树的根结点。
 * <p>
 * 答案中每个树的每个结点都必须有 node.val=0。
 * <p>
 * 你可以按任何顺序返回树的最终列表。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：7
 * 输出：[[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
 * 解释：
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= N <= 20
 *
 * @date 2021/5/17 22:56
 */
public class Solution894___ {

    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> ans = new ArrayList<>();
        create(n, ans);
        return ans;
    }

    private void create(int n, List<TreeNode> ans) {
        if (n == 1) {
            TreeNode node = new TreeNode(0);
            ans.add(node);
        } else {
            for (int i = 1; i < n - 1; i += 2) {
                List<TreeNode> l = new ArrayList<>();
                List<TreeNode> r = new ArrayList<>();
                create(i, l);
                create(n - i - 1, r);
                for (TreeNode left : l) {
                    for (TreeNode right : r) {
                        TreeNode node = new TreeNode(0);
                        node.left = left;
                        node.right = right;
                        ans.add(node);
                    }
                }
            }
        }
    }
}
