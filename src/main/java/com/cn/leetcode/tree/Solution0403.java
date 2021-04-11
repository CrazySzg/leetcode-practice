package com.cn.leetcode.tree;

import java.util.*;

/**
 * 面试题 04.03. 特定深度节点链表
 *
 * 给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
 *
 *  
 *
 * 示例：
 *
 * 输入：[1,2,3,4,5,null,7,8]
 *
 *         1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 *    /
 *   8
 *
 * 输出：[[1],[2,3],[4,5,7],[8]]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/list-of-depth-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/4/7 22:37
 */
public class Solution0403 {

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(1);
        TreeNode n1 = new TreeNode(2);
        TreeNode n2 = new TreeNode(3);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(0);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(8);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = null;
        n2.right = n6;
        n3.left = n7;
        ListNode[] listNodes = listOfDepth(n0);
        System.out.println(Arrays.toString(listNodes));
        System.out.println(inOrderTraversal(n0));
    }

    public static List<Integer> inOrderTraversal(TreeNode root) {
        // List<Integer> list = new ArrayList<>();
        // if (root == null) return new ArrayList<>();
        // List<Integer> l = inorderTraversal(root.left);
        // list.addAll(l);
        // list.add(root.val);
        // list.addAll(inorderTraversal(root.right));
        // return list;
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }

    public static ListNode[] listOfDepth(TreeNode tree) {
        if (tree == null) {
            return new ListNode[]{};
        }
        LinkedList<TreeNode> q1 = new LinkedList<>();
        LinkedList<TreeNode> q2 = new LinkedList<>();
        List<ListNode> listNodes = new ArrayList<>();
        q1.add(tree);
        while (!q1.isEmpty()) {
            TreeNode tn = q1.pollFirst();
            ListNode head = new ListNode(tn.val);
            ListNode p = head;
            if (tn.left != null) {
                q2.add(tn.left);
            }
            if (tn.right != null) {
                q2.add(tn.right);
            }
            for (; !q1.isEmpty(); ) {
                TreeNode t = q1.pollFirst();
                ListNode ln = new ListNode(t.val);
                p.next = ln;
                p = ln;
                if (t.left != null) {
                    q2.add(t.left);
                }
                if (t.right != null) {
                    q2.add(t.right);
                }
            }
            listNodes.add(head);
            q1 = q2;
            q2 = new LinkedList<>();
        }
        return listNodes.toArray(new ListNode[]{});
    }
}


