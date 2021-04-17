package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 113. 路径总和 II
 *
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *  
 *
 * 示例 1：
 *
 *
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 *
 *
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 *
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/4/17 20:07
 */
public class Solution113 {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        Deque<List<Integer>> queueVal = new LinkedList<>();
        queue.add(root);
        List<Integer> rootVal = new ArrayList<>();
        rootVal.add(root.val);
        queueVal.add(rootVal);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                List<Integer> valList = queueVal.poll();
                if (node.left == null && node.right == null && sum(valList) == targetSum) {
                    ans.add(valList);
                }
                if (node.left != null) {
                    queue.add(node.left);
                    List<Integer> ll = new ArrayList<>(valList);
                    ll.add(node.left.val);
                    queueVal.add(ll);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    List<Integer> lr = new ArrayList<>(valList);
                    lr.add(node.right.val);
                    queueVal.add(lr);
                }
            }
        }
        return ans;
    }

    private int sum(List<Integer> list) {
        int sum = 0;
        for (Integer num : list) {
            sum += num;
        }
        return sum;
    }

    /* ------------------------------------------------------------------------ */

    private List<List<Integer>> ans = new ArrayList<>();
    private Deque<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum2(TreeNode root, int targetSum) {
        if (root == null) {
            return ans;
        }
        dfs(root, targetSum);
        return ans;
    }

    public void dfs(TreeNode root, int targetSum) {
        if (root == null) {
            return;
        }
        int val = root.val;
        path.offerLast(val);
        if (root.left == null && root.right == null && targetSum == val) {
            ans.add(new LinkedList(path));
        }
        dfs(root.left, targetSum - val);
        dfs(root.right, targetSum - val);
        path.pollLast();
    }
}
