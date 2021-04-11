package com.cn.leetcode.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 589. N 叉树的前序遍历
 *
 * 给定一个 N 叉树，返回其节点值的 前序遍历 。
 *
 * N 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 *  
 *
 * 进阶：
 *
 * 递归法很简单，你可以使用迭代法完成此题吗?
 *
 * 示例 1：
 *
 *
 *
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 * 示例 2：
 *
 *
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @date 2021/4/11 11:44
 */
public class Solution589 {

    // public List<Integer> preorder(Node root) {
    //     if (root == null) return new ArrayList<>();
    //     List<Integer> list = new ArrayList<>();
    //     list.add(root.val);
    //     for(Node n : root.children) {
    //         List<Integer> l = preorder(n);
    //         list.addAll(l);
    //     }
    //     return list;
    // }

    Deque<Node> dq = new LinkedList<>();
    List<Integer> res = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }

        dq.push(root);
        while (!dq.isEmpty()) {
            Node t = dq.pop();
            res.add(t.val);
            for (int i = t.children.size() - 1; i >= 0;i--) {
                dq.push(t.children.get(i));
            }
        }
        return res;
    }
}
