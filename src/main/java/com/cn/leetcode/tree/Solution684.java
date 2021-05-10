package com.cn.leetcode.tree;

/**
 * 684. 冗余连接
 * 在本问题中, 树指的是一个连通且无环的无向图。
 *
 * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 *
 * 结果图是一个以边组成的二维数组。每一个边的元素是一对[u, v] ，满足 u < v，表示连接顶点u 和v的无向图的边。
 *
 * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
 *
 * 示例 1：
 *
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的无向图为:
 *   1
 *  / \
 * 2 - 3
 * 示例 2：
 *
 * 输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * 输出: [1,4]
 * 解释: 给定的无向图为:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 * 注意:
 *
 * 输入的二维数组大小在 3 到 1000。
 * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
 *
 * @date 2021/5/9 13:19
 */
public class Solution684 {

    public int[] findRedundantConnection(int[][] edges) {
        // 根据题意，数有N个节点，即有N-1条边，外加一条附加的边不属于树种已存在的边，即节点数等于入参中边的数量
        int nodeCounts = edges.length;
        //  以下初始化每个节点，索引代表节点值，数组中的值代表该节点的parent
        int[] parent = new int[nodeCounts + 1];
        // 节点值不重复，范围为1 ~ N,
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            if (!union(parent, node1, node2)) {
                return edges[i];
            }

        }
        return new int[]{0, 0};
    }

    // 如果两个节点的parent不相同，则将他们合并，否则说明两个节点的连线后形成了环
    public boolean union(int[] parent, int node1, int node2) {
        // 找到节点最原始的祖先
        int n1 = find(parent, node1);
        int n2 = find(parent, node2);
        if (n1 == n2) {
            return false;
        }
        // 将两个不同的连通分量进行连通
        parent[n1] = n2;
        return true;
    }

    public int find(int[] parent, int index) {
        while (parent[index] != index) {
            index = parent[index];
        }
        return index;
    }
}
