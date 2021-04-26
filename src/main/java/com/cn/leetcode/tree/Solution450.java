package com.cn.leetcode.tree;

/**
 * 450. 删除二叉搜索树中的节点
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 *
 * 一般来说，删除节点可分为两个步骤：
 *
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 *
 * 示例:
 *
 * root = [5,3,6,2,4,null,7]
 * key = 3
 *
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * 给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 *
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 *
 *     5
 *    / \
 *   4   6
 *  /     \
 * 2       7
 *
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 *
 *     5
 *    / \
 *   2   6
 *    \   \
 *     4   7
 *
 *
 * @date 2021/4/26 23:42
 */
public class Solution450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        return deleteInternal(root, key);
    }

    // 注意函数签名的设计，巧妙地使得递归过程中父节点可以改变子节点的引用
    private TreeNode deleteInternal(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            // delete
            if (root.left != null) {
                // 找到左子树最右边的节点
                TreeNode left = root.left;
                while (left.right != null) {
                    left = left.right;
                }
                root.val = left.val;
                root.left = deleteInternal(root.left, root.val);
            } else if (root.right != null) {
                // 找到右子树最左边的节点
                TreeNode right = root.right;
                while (right.left != null) {
                    right = right.left;
                }
                root.val = right.val;
                root.right = deleteInternal(root.right, root.val);
            } else {
                root = null;
            }
        } else if (root.val > key) {
            root.left = deleteInternal(root.left, key);
        } else {
            root.right = deleteInternal(root.right, key);
        }
        return root;
    }

}
