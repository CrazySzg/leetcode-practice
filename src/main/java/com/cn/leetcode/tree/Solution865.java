package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 865. 具有所有最深节点的最小子树
 * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
 * <p>
 * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
 * <p>
 * 一个节点的 子树 是该节点加上它的所有后代的集合。
 * <p>
 * 返回能满足 以该节点为根的子树中包含所有最深的节点 这一条件的具有最大深度的节点。
 * <p>
 * <p>
 * <p>
 * 注意：本题与力扣 1123 重复：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：
 * 我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
 * 示例 2：
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点。
 * 示例 3：
 * <p>
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点的数量介于 1 和 500 之间。
 * 0 <= Node.val <= 500
 * 每个节点的值都是独一无二的。
 *
 * @date 2021/5/15 18:11
 */
public class Solution865 {

    Map<TreeNode, TreeNode> map = new HashMap<>();
    int maxDepth = 0;
    List<TreeNode> ans = new ArrayList<>();

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root;
        }
        dfs(root, null, 1);
        if (ans.size() == 1) {
            return ans.get(0);
        } else {
            TreeNode p1 = map.get(ans.get(0));
            TreeNode p2 = map.get(ans.get(ans.size() - 1));
            do {
                if (p1 == p2) {
                    break;
                } else {
                    p1 = map.get(p1);
                    p2 = map.get(p2);
                }
            } while (true);
            return p1;
        }
    }

    private void dfs(TreeNode root, TreeNode parent, int depth) {
        if (root == null) {
            return;
        }
        map.put(root, parent);
        if (root.left == null && root.right == null) {
            if (depth > maxDepth) {
                maxDepth = depth;
                ans.clear();
                ans.add(root);
            } else if (depth == maxDepth) {
                ans.add(root);
            }
            return;
        }
        dfs(root.left, root, depth + 1);
        dfs(root.right, root, depth + 1);
    }
}
