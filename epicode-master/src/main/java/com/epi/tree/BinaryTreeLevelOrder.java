package com.epi.tree;

import java.util.*;

public class BinaryTreeLevelOrder {
    // @include
    public static List<List<Integer>> binaryTreeDepthOrder(
            BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> tree) {
        Queue<BinaryTreePrototypeTemplate.BinaryTreeNode<Integer>> currDepthNodes = new LinkedList<>();
        currDepthNodes.add(tree);
        List<List<Integer>> result = new ArrayList<>();

        while (!currDepthNodes.isEmpty()) {
            Queue<BinaryTreePrototypeTemplate.BinaryTreeNode<Integer>> nextDepthNodes = new LinkedList<>();
            List<Integer> thisLevel = new ArrayList<>();
            while (!currDepthNodes.isEmpty()) {
                BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> curr = currDepthNodes.poll();
                if (curr != null) {
                    thisLevel.add(curr.data);

                    // Defer the null checks to the null test above.
                    nextDepthNodes.add(curr.left);
                    nextDepthNodes.add(curr.right);
                }
            }

            if (!thisLevel.isEmpty()) {
                result.add(thisLevel);
            }
            currDepthNodes = nextDepthNodes;
        }
        return result;
    }
    // @exclude

    public static void main(String[] args) {
        //      3
        //    2   5
        //  1    4 6
        // 10
        // 13
        BinaryTreePrototypeTemplate.BinaryTreeNode<Integer> tree = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(3);
        tree.left = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(2);
        tree.left.left = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(1);
        tree.left.left.left = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(10);
        tree.left.left.left.right = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(13);
        tree.right = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(5);
        tree.right.left = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(4);
        tree.right.right = new BinaryTreePrototypeTemplate.BinaryTreeNode<>(6);
        List<List<Integer>> result = binaryTreeDepthOrder(tree);
        List<List<Integer>> goldenRes = Arrays.asList(
                Arrays.asList(3), Arrays.asList(2, 5), Arrays.asList(1, 4, 6),
                Arrays.asList(10), Arrays.asList(13));
        if (!goldenRes.equals(result)) {
            System.err.println("Failed on input " + tree);
            System.err.println("Expected " + goldenRes);
            System.err.println("Your code produced " + result);
            System.exit(-1);
        } else {
            System.out.println("You passed all tests.");
        }
    }
}
