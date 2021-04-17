package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 109. 有序链表转换二叉搜索树
 *
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 *
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * 示例:
 *
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 *
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 *
 *       0
 *      / \
 *    -3   9
 *    /   /
 *  -10  5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/4/17 13:37
 */
public class Solution109 {

    public TreeNode sortedListToBST(ListNode head) {
        List<ListNode> nodeList = new ArrayList<>();
        ListNode p = head;
        while (p != null) {
            nodeList.add(p);
            p = p.next;
        }
        return sortedListToBST(nodeList, 0, nodeList.size() - 1);
    }

    public TreeNode sortedListToBST(List<ListNode> nodeList, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        ListNode node = nodeList.get(mid);
        TreeNode root = new TreeNode(node.val);
        root.left = sortedListToBST(nodeList, left, mid - 1);
        root.right = sortedListToBST(nodeList, mid + 1, right);
        return root;
    }
}
