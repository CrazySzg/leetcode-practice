package com.cn.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * <p>
 * 找出路径和等于给定数值的路径总数。
 * <p>
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * <p>
 * 示例：
 * <p>
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * <p>
 * 10
 * /  \
 * 5   -3
 * / \    \
 * 3   2   11
 * / \   \
 * 3  -2   1
 * <p>
 * 返回 3。和等于 8 的路径有:
 * <p>
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 * @date 2021/4/24 22:50
 */
public class Solution437 {

    public static void main(String[] args) {
        Integer[] nums = {10, 5, -3, 3, 2, null, 11, 3, -2, null, 1};
        TreeNode root = ConstructTree.constructTree(nums);
        System.out.println(pathSum(root, 8));
    }

    public static int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }

        return calSum(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    public static int calSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int tmp = 0;
        sum -= root.val;
        if (sum == 0) {
            tmp = 1;
        }
        return tmp + calSum(root.left, sum) + calSum(root.right, sum);
    }


    // ===========================================================
    public int pathSum2(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     * @param node 树节点
     * @param prefixSumCount 前缀和Map
     * @param target 目标值
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
//        HashMap存的是什么
//        HashMap的key是前缀和， value是该前缀和的节点数量，记录数量是因为有出现复数路径的可能。
//
//        拿图说明：
//
//        下图树中，前缀和为1的节点有两个: 1, 0
//
//        所以路径和为2的路径数就有两条: 0 --> 2, 2
//
//             1
//            /
//           0
//          /
//         2
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        //        恢复状态的意义
        //        由于题目要求：路径方向必须是向下的（只能从父节点到子节点）
        //
        //        当我们讨论两个节点的前缀和差值时，有一个前提：
        //
        //        一个节点必须是另一个节点的祖先节点
        //
        //        换句话说，当我们把一个节点的前缀和信息更新到map里时，它应当只对其子节点们有效。
        //
        //        举个例子，下图中有两个值为2的节点（A, B)。
        //
        //                    0
        //                  /  \
        //                 A:2  B:2
        //                / \    \
        //               4   5    6
        //                / \   \
        //               7   8   9
        //        当我们遍历到最右方的节点6时，对于它来说，此时的前缀和为2的节点只该有B, 因为从A向下到不了节点6(A并不是节点6的祖先节点)。
        //
        //        如果我们不做状态恢复，当遍历右子树时，左子树中A的信息仍会保留在map中，那此时节点6就会认为A, B都是可追溯到的节点，从而产生错误。
        //
        //        状态恢复代码的作用就是： 在遍历完一个节点的所有子节点后，将其从map中除去。

        return res;
    }

}
