package com.cn.leetcode.tree;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/4/17 11:26
 */
public class Solution105 {

    private Map<Integer, Integer> indexMap = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode buildTree(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (ps > pe || is > ie) {
            return null;
        }
        int rootVal = preorder[ps];
        TreeNode root = new TreeNode(rootVal);
        int index = indexMap.get(rootVal);
        root.left = buildTree(preorder, ps + 1, ps + index - is, inorder, is, index - 1);
        root.right = buildTree(preorder, ps + index - is + 1, pe, inorder, index + 1, ie);
        return root;
    }

}
