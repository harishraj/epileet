package com.interviews.ds;

import java.util.*;
import java.util.LinkedList;

class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode parent;
    private int size = 0;

    public TreeNode(int d) {
        val = d;
        size = 1;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
        if (left != null) {
            left.parent = this;
        }
    }

    public void setRight(TreeNode right) {
        this.right = right;
        if (right != null) {
            right.parent = this;
        }
    }

}

public class BinaryTree {

    //************************************************************************************************************
    // Count Number of Nodes
    //************************************************************************************************************

    int maxValue;

    //************************************************************************************************************
    // max depth of the tree
    //************************************************************************************************************
    private TreeNode prev = null;

    //************************************************************************************************************
    // min depth of the tree
    //************************************************************************************************************

    public static int countTrees(int numKeys) {

        if (numKeys <= 1) {
            return (1);
        } else {
            // there will be one value at the root, with whatever remains
            // on the left and right each forming their own subtrees.
            // Iterate through all the values that could be the root...
            int sum = 0;
            int left, right, root;

            for (root = 1; root <= numKeys; root++) {
                left = countTrees(root - 1);
                right = countTrees(numKeys - root);

                // number of possible trees with this root == left*right
                sum += left * right;
            }

            return (sum);
        }
    }

    //************************************************************************************************************
    // Same Tree Recursive
    //************************************************************************************************************

    public static void main(String args[]) {

        TreeNode root = new TreeNode(6);

        TreeNode left1 = new TreeNode(8);
        TreeNode right1 = new TreeNode(10);

        TreeNode left2 = new TreeNode(11);
        TreeNode right2 = new TreeNode(1);

        root.setLeft(left1);

        left1.right = right2;

        root.left = left1;
        root.right = right1;

        BinaryTree binaryTree = new BinaryTree();
        int minRes = binaryTree.minDepth(root);
        int maxRes = binaryTree.maxDepth(root);

        System.out.println(minRes);
        System.out.println(maxRes);

    }

    //************************************************************************************************************
    // Symmetric  recursive
    //************************************************************************************************************

    public int countNumberOfNodes(TreeNode root) {

        if (root == null)
            return 0;
        return 1 + countNumberOfNodes(root.left) + countNumberOfNodes(root.right);
    }

    public int maxDepth(TreeNode root) {

        if (root == null)
            return 0;
        else
            return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //************************************************************************************************************
    // is Balanced Binary Tree
    //************************************************************************************************************

    public int minDepth(TreeNode root) {

        if (root == null)
            return 0;

        if (root.left == null && root.right == null)
            return 1;
        else
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }

    //************************************************************************************************************
    // get Max Path sum
    //************************************************************************************************************

    public boolean isSameTreeRecursive(TreeNode p, TreeNode q) {

        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val == q.val)
            return isSameTreeRecursive(p.left, q.left) && isSameTreeRecursive(p.right, q.right);
        return false;
    }

    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetricHelp(root.left, root.right);
    }

    private boolean isSymmetricHelp(TreeNode left, TreeNode right) {

        if (left == null || right == null)
            return left == right;

        if (left.val != right.val)
            return false;

        return isSymmetricHelp(left.left, right.right) && isSymmetricHelp(left.right, right.left);
    }

    //************************************************************************************************************
    // is Binary Search Tree
    //************************************************************************************************************

    public Boolean isBalanced(TreeNode root) {

        if (root == null) return true;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
        maxPathDown(root);
        return maxValue;
    }

    //************************************************************************************************************
    // Mirror trees
    //************************************************************************************************************

    private int maxPathDown(TreeNode node) {

        if (node == null) return 0;

        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));

        maxValue = Math.max(maxValue, left + right + node.val);
        return Math.max(left, right) + node.val;

    }

    //************************************************************************************************************
    // LCA
    //************************************************************************************************************

    public boolean isBST(TreeNode root) {
        return (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE));
    }

    //************************************************************************************************************
    // invert tree - Recursive
    //************************************************************************************************************

    private boolean isBST(TreeNode node, int min, int max) {
        if (node == null) {
            return (true);
        } else {
            // left should be in range  min...node.data
            boolean leftOk = isBST(node.left, min, node.val);

            // if the left is not ok, bail out
            if (!leftOk) return (false);

            // right should be in range node.data+1..max
            boolean rightOk = isBST(node.right, node.val + 1, max);

            return (rightOk);
        }
    }

    //************************************************************************************************************
    // double Tree
    //************************************************************************************************************

    private void mirror(TreeNode node) {
        if (node != null) {
            // do the sub-trees
            mirror(node.left);
            mirror(node.right);

            // swap the left/right pointers
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
    }

    //************************************************************************************************************
    // PreOrder - root, left, right
    //************************************************************************************************************

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root == null || root == p || root == q) return root;

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null)
            return right;
        else if (right == null)
            return left;
        else
            return root;
        //return left == null ? right : right == null ? left : root;
    }

    //************************************************************************************************************
    // inOrder - left, root, right
    //************************************************************************************************************

    public TreeNode invertTreeRecursive(TreeNode root) {

        if (root == null) {
            return null;
        }

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = invertTreeRecursive(right);
        root.right = invertTreeRecursive(left);
        return root;
    }

    //************************************************************************************************************
    // PostOrder - left, right, root
    //************************************************************************************************************

    private void doubleTree(TreeNode node) {
        TreeNode oldLeft;

        if (node == null) return;

        // do the subtrees
        doubleTree(node.left);
        doubleTree(node.right);

        // duplicate this node to its left
        oldLeft = node.left;
        node.left = new TreeNode(node.val);
        node.left.left = oldLeft;
    }

    //************************************************************************************************************
    // Level Order - Use Queue
    //************************************************************************************************************

    public void preOrder(TreeNode node) {
        if (node != null) {
            System.out.println(node.val);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    //************************************************************************************************************
    // Serialize/ DeSerialize  recursive
    //************************************************************************************************************

    public void inOrder(TreeNode node) {
        if (node != null) {

            inOrder(node.left);
            System.out.println(node.val);
            inOrder(node.right);
        }
    }

    public void postOrder(TreeNode node) {

        if (node != null) {

            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.val);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        Deque<TreeNode> queue = new LinkedList<TreeNode>();
        List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

        if (root == null) return wrapList;

        queue.offer(root);

        while (!queue.isEmpty()) {

            int levelNum = queue.size();
            List<Integer> subList = new LinkedList<Integer>();

            for (int i = 0; i < levelNum; i++) {

                if (queue.peek().left != null) queue.offer(queue.peek().left);
                if (queue.peek().right != null) queue.offer(queue.peek().right);
                subList.add(queue.poll().val);
            }

            wrapList.add(subList);
        }

        return wrapList;
    }

    public String serialize(TreeNode root) {

        return serial(new StringBuilder(), root).toString();
    }

    //************************************************************************************************************
    // hasPathSum
    //************************************************************************************************************

    private StringBuilder serial(StringBuilder str, TreeNode root) {
        if (root == null) return str.append("#");
        str.append(root.val).append(",");
        serial(str, root.left).append(","); // note execution order
        return serial(str, root.right);
    }

    //************************************************************************************************************
    // kth Smallest
    // https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    //************************************************************************************************************

    public TreeNode deserialize(String data) {

        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    //************************************************************************************************************
    // Given a binary tree, prints out all of its root-to-leaf
    // paths, one per line. Uses a recursive helper to do the work.
    //************************************************************************************************************

    private TreeNode deserial(LinkedList<String> queue) {
        String val = queue.poll(); // return null if empty
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserial(queue);
        root.right = deserial(queue);
        return root;
    }

    public boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) return false;

        if (root.left == null && root.right == null && sum - root.val == 0) return true;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    //************************************************************************************************************
    // Flatten Binary Tree to Linked List
    //************************************************************************************************************

    public int kthSmallest(TreeNode root, int k) {

        int count = countNumberOfNodes(root.left);
        if (k <= count) {
            return kthSmallest(root.left, k);
        } else if (k > count + 1) {
            return kthSmallest(root.right, k - 1 - count); // 1 is counted as current node
        }

        return root.val;
    }

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> result = new ArrayList<String>();
        if (root != null) searchBT(root, "", result);
        return result;
    }

    //************************************************************************************************************
    // Given a binary tree where all the right nodes are either leaf nodes with a sibling
    // (a left node that shares the same parent node) or empty, flip it upside down and turn it into a tree
    // where the original right nodes turned into left leaf nodes. Return the new root.
    //************************************************************************************************************

    private void searchBT(TreeNode root, String path, List<String> result) {

        if (root.left == null && root.right == null) result.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", result);
        if (root.right != null) searchBT(root.right, path + root.val + "->", result);
    }

    //************************************************************************************************************
    // count structurally unique binary search trees possible
    //  Strategy: consider that each value could be the root.
    //  Recursively find the size of the left and right subtrees.
    //************************************************************************************************************

    public void flatten(TreeNode root) {

        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }

    //************************************************************************************************************
    // Same Tree Non-recursive - using DFS (Stack) - pop & push
    //************************************************************************************************************

    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || root.left == null && root.right == null)
            return root;

        TreeNode newRoot = upsideDownBinaryTree(root.left);

        root.left.left = root.right;
        root.left.right = root;

        root.left = null;
        root.right = null;

        return newRoot;
    }

    //************************************************************************************************************
    // DFS - uses Stack - Pop & Push
    //************************************************************************************************************

    public boolean isSameTree(TreeNode p, TreeNode q) {

        Deque<TreeNode> stack_p = new LinkedList<>();
        Deque<TreeNode> stack_q = new LinkedList<>();

        if (p != null) stack_p.push(p);
        if (q != null) stack_q.push(q);

        while (!stack_p.isEmpty() && !stack_q.isEmpty()) {

            TreeNode pn = stack_p.pop();
            TreeNode qn = stack_q.pop();

            if (pn.val != qn.val) return false;

            if (pn.right != null) stack_p.push(pn.right);
            if (qn.right != null) stack_q.push(qn.right);
            if (stack_p.size() != stack_q.size()) return false;

            if (pn.left != null) stack_p.push(pn.left);
            if (qn.left != null) stack_q.push(qn.left);
            if (stack_p.size() != stack_q.size()) return false;
        }

        return stack_p.size() == stack_q.size();
    }

    //************************************************************************************************************
    // BFS - uses Queue - Poll & Pop
    //************************************************************************************************************

    public TreeNode invertTreeDFS(TreeNode root) {

        if (root == null) {
            return null;
        }

        final Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            TreeNode node = stack.pop();
            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return root;
    }

    //************************************************************************************************************
    // Symmetric non-recursive
    //************************************************************************************************************

    public TreeNode invertTree(TreeNode root) {

        if (root == null) {
            return null;
        }

        final Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();

            TreeNode left = node.left;
            node.left = node.right;
            node.right = left;

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    //************************************************************************************************************
    // Helper
    //************************************************************************************************************

    public boolean isSymmetricIterative(TreeNode root) {

        if (root == null) return true;

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode left, right;

        if (root.left != null) {
            if (root.right == null) return false;
            stack.push(root.left);
            stack.push(root.right);
        } else if (root.right != null) {
            return false;
        }

        while (!stack.isEmpty()) {
            if (stack.size() % 2 != 0) return false;
            right = stack.pop();
            left = stack.pop();
            if (right.val != left.val) return false;

            if (left.left != null) {
                if (right.right == null) return false;
                stack.push(left.left);
                stack.push(right.right);
            } else if (right.right != null) {
                return false;
            }

            if (left.right != null) {
                if (right.left == null) return false;
                stack.push(left.right);
                stack.push(right.left);
            } else if (right.left != null) {
                return false;
            }
        }

        return true;
    }

    public void printPaths(TreeNode root) {
        int[] path = new int[1000];
        printPaths(root, path, 0);
    }

    /**
     * Recursive printPaths helper -- given a node, and an array containing
     * the path from the root node up to but not including this node,
     * prints out all the root-leaf paths.
     */
    private void printPaths(TreeNode node, int[] path, int pathLen) {
        if (node == null) return;

        // append this node to the path array
        path[pathLen] = node.val;
        pathLen++;

        // it's a leaf, so print the path that led to here
        if (node.left == null && node.right == null) {
            printArray(path, pathLen);
        } else {
            // otherwise try both subtrees
            printPaths(node.left, path, pathLen);
            printPaths(node.right, path, pathLen);
        }
    }

    //************************************************************************************************************
    // main method
    //************************************************************************************************************

    /**
     * Utility that prints ints from an array on one line.
     */
    private void printArray(int[] ints, int len) {
        int i;
        for (i = 0; i < len; i++) {
            System.out.print(ints[i] + " ");
        }
        System.out.println();
    }

}