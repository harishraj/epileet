package com.epi.tree;

import java.util.*;

public class BinaryTreePostorderTraversalIterativeAlternative {
    // @include
    public static List<Integer> postorderTraversal(BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> tree) {
        List<Integer> sequence = invertedPreorderTraversal(tree);
        Collections.reverse(sequence);
        return sequence;
    }

    private static List<Integer> invertedPreorderTraversal(
            BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> tree) {
        Deque<BinaryTreePrototypeTemplate.BinaryTreeNode<Integer>> path = new LinkedList<>();
        path.addFirst(tree);
        List<Integer> result = new ArrayList<>();
        while (!path.isEmpty()) {
            BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> curr = path.removeFirst();
            if (curr == null) {
                continue;
            }
            result.add(curr.data);
            path.addFirst(curr.left);
            path.addFirst(curr.right);
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
        List<Integer> res = postorderTraversal(tree);
        List<Integer> goldenRes = BinaryTreeUtils.generatePostorder(tree);
        assert (res.size() == goldenRes.size()
                && Arrays.deepEquals(res.toArray(), goldenRes.toArray()));
    }
}
