package com.cn.leetcode.tree;

import java.util.*;

/**
 * 652. 寻找重复的子树
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * <p>
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * <p>
 * 示例 1：
 * <p>
 * 1
 * / \
 * 2   3
 * /   / \
 * 4   2   4
 * /
 * 4
 * 下面是两个重复的子树：
 * <p>
 * 2
 * /
 * 4
 * 和
 * <p>
 * 4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 */
public class Solution652 {

    private static Map<String, Integer> map = new HashMap<>();
    private static List<TreeNode> ans = new ArrayList<>();

    public static void main(String[] args) {
//        Integer[] nums = {1, 2, 3, 4, null, 2, 4, null, null, 4};
        Integer[] nums = {0, 0, 0, 0, null, null, 0, null, null, 0};
        TreeNode treeNode = ConstructTree.constructTree(nums);
        List<TreeNode> duplicateSubtrees = findDuplicateSubtrees(treeNode);
        System.out.println(duplicateSubtrees);
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        collect(root);
        return ans;
    }

    private static String collect(TreeNode root) {
        if (root == null) return "#";
        String serial = root.val + "," + collect(root.left) + "," + collect(root.right);
        map.put(serial, map.getOrDefault(serial, 0) + 1);
        if (map.get(serial) == 2) {
            ans.add(root);
        }
        return serial;
    }
}
