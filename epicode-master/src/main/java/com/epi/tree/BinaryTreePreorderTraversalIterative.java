package com.epi.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BinaryTreePreorderTraversalIterative {
    // @include
    public static List<Integer> preorderTraversal(BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreePrototypeTemplate.BinaryTreeNode<Integer>> path = new LinkedList<>();
        path.addFirst(tree);
        List<Integer> result = new ArrayList<>();
        while (!path.isEmpty()) {
            BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> curr = path.removeFirst();
            if (curr != null) {
                result.add(curr.data);
                path.addFirst(curr.right);
                path.addFirst(curr.left);
            }
        }
        return result;
    }
    // @exclude

    public static void main(String[] args) {
        // 3
        // 2 5
        // 1 4 6
        BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> tree = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(3);
        tree.left = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(2);
        tree.left.left = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(1);
        tree.right = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(5);
        tree.right.left = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(4);
        tree.right.right = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(6);
        List<Integer> res = preorderTraversal(tree);
        List<Integer> goldenRes = BinaryTreeUtils.generatePreorder(tree);
        assert (res.equals(goldenRes));
    }
}
