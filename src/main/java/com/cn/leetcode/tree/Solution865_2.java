package com.cn.leetcode.tree;

/**
 * 865. 具有所有最深节点的最小子树
 * 给定一个根为 root 的二叉树，每个节点的深度是 该节点到根的最短距离 。
 *
 * 如果一个节点在 整个树 的任意节点之间具有最大的深度，则该节点是 最深的 。
 *
 * 一个节点的 子树 是该节点加上它的所有后代的集合。
 *
 * 返回能满足 以该节点为根的子树中包含所有最深的节点 这一条件的具有最大深度的节点。
 *
 *
 *
 * 注意：本题与力扣 1123 重复：https://leetcode-cn.com/problems/lowest-common-ancestor-of-deepest-leaves/
 *
 *
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4]
 * 输出：[2,7,4]
 * 解释：
 * 我们返回值为 2 的节点，在图中用黄色标记。
 * 在图中用蓝色标记的是树的最深的节点。
 * 注意，节点 5、3 和 2 包含树中最深的节点，但节点 2 的子树最小，因此我们返回它。
 * 示例 2：
 *
 * 输入：root = [1]
 * 输出：[1]
 * 解释：根节点是树中最深的节点。
 * 示例 3：
 *
 * 输入：root = [0,1,3,null,2]
 * 输出：[2]
 * 解释：树中最深的节点为 2 ，有效子树为节点 2、1 和 0 的子树，但节点 2 的子树最小。
 *
 *
 * 提示：
 *
 * 树中节点的数量介于 1 和 500 之间。
 * 0 <= Node.val <= 500
 * 每个节点的值都是独一无二的。
 *
 * @date 2021/5/15 23:05
 */
public class Solution865_2 {

    // 思路：从每个树开始，获得当前节点的左右子树的最大深度
    // 深度相同，说明最深的节点在这个节点两边，那这个节点就是结果
    // 如果深度不相同，则去深度大的子树继续判断，最终就能得到结果
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        if (root == null) {
            return root;
        }

        // 获取当前节点的左右子树的最大深度
        int leftMaxDepth = getMaxDepth(root.left);
        int rightMaxDepth = getMaxDepth(root.right);

        // 如果两边最大深度相同，则这个节点就是结果
        if (leftMaxDepth == rightMaxDepth) {
            return root;
        }

        // 不相等，那就去深度大的子树那边继续找
        if (leftMaxDepth > rightMaxDepth){
            return subtreeWithAllDeepest(root.left);
        }

        return subtreeWithAllDeepest(root.right);
    }

    public int getMaxDepth(TreeNode root){
        if (root == null) {
            return 0;
        }

        return Math.max(getMaxDepth(root.left), getMaxDepth(root.right)) + 1;
    }
}
