package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 508. 出现次数最多的子树元素和
 * 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 *
 * 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 *
 *
 *
 * 示例 1：
 * 输入:
 *
 *   5
 *  /  \
 * 2   -3
 * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
 *
 * 示例 2：
 * 输入：
 *
 *   5
 *  /  \
 * 2   -5
 * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
 *
 *
 *
 * 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
 *
 * @date 2021/4/28 22:48
 */
public class Solution508 {

    Map<Integer, Integer> map = new HashMap<>();
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Integer rootVal = findFrequentTreeSumInternal(root);
        map.put(rootVal, map.getOrDefault(rootVal, 0) + 1);
        List<Integer> ans = new ArrayList<>();
        int frequentlySum = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > frequentlySum) {
                ans.clear();
                frequentlySum = entry.getValue();
                ans.add(entry.getKey());
            } else if(entry.getValue() == frequentlySum) {
                ans.add(entry.getKey());
            }
        }
        int[] a = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            a[i] = ans.get(i);
        }
        return a;
    }

    public Integer findFrequentTreeSumInternal(TreeNode root) {
        if (root == null) {
            return null;
        }
        Integer left = findFrequentTreeSumInternal(root.left);
        Integer right = findFrequentTreeSumInternal(root.right);

        if (left != null) {
            map.put(left, map.getOrDefault(left, 0) + 1);
        } else {
            // 表明left是叶子节点
            left = 0;
        }
        if (right != null) {
            map.put(right, map.getOrDefault(right, 0) + 1);
        } else {
            // 表明left是叶子节点
            right = 0;
        }
        return left + right + root.val;
    }
}
