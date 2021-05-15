package com.cn.leetcode.tree;

import java.util.*;

/**
 * 863. 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点 root）， 一个目标结点 target ，和一个整数值 K 。
 * <p>
 * 返回到目标结点 target 距离为 K 的所有结点的值的列表。 答案可以以任何顺序返回。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * 输出：[7,4,1]
 * 解释：
 * 所求结点为与目标结点（值为 5）距离为 2 的结点，
 * 值分别为 7，4，以及 1
 * <p>
 * <p>
 * <p>
 * 注意，输入的 "root" 和 "target" 实际上是树上的结点。
 * 上面的输入仅仅是对这些对象进行了序列化描述。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 给定的树是非空的。
 * 树上的每个结点都具有唯一的值 0 <= node.val <= 500 。
 * 目标结点 target 是树上的结点。
 * 0 <= K <= 1000.
 *
 * @date 2021/5/15 17:47
 */
public class Solution863 {
    Map<Integer, Set<Integer>> map = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        if (k == 0) {
            ans.add(target.val);
            return ans;
        }
        dfs(root);
        return dfs2(map.get(target.val), target.val, 1, k);
    }

    private List<Integer> dfs2(Set<Integer> nebr, Integer parent, int cur, int k) {
        List<Integer> ans = new ArrayList<>();
        if (cur == k) {
            ans.addAll(nebr);
            return ans;
        }
        if (nebr == null || nebr.isEmpty()) {
            return ans;
        }
        for (Integer val : nebr) {
            Set<Integer> valList = map.get(val);
            valList.remove(parent);
            if (valList.isEmpty()) {
                continue;
            }
            List<Integer> res = dfs2(valList, val, cur + 1, k);
            ans.addAll(res);
        }
        return ans;
    }


    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            Set<Integer> l1 = this.getMapVal(root.left.val);
            Set<Integer> l2 = this.getMapVal(root.val);
            l1.add(root.val);
            l2.add(root.left.val);
        }
        if (root.right != null) {
            Set<Integer> l1 = this.getMapVal(root.right.val);
            Set<Integer> l2 = this.getMapVal(root.val);
            l1.add(root.val);
            l2.add(root.right.val);
        }
        dfs(root.left);
        dfs(root.right);
    }

    private Set<Integer> getMapVal(int val) {
        Set<Integer> set;
        if (map.get(val) == null) {
            set = new HashSet<>();
            map.put(val, set);
        } else {
            set = map.get(val);
        }
        return set;
    }
}
