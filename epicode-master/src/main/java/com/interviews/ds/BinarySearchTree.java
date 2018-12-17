package com.interviews.ds;

import java.util.Deque;
import java.util.LinkedList;

public class BinarySearchTree {

    //**************************************************************************************
    // LCA
    //**************************************************************************************

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }

    //***************************************************************************************
    // Sorted Array to BST
    //***************************************************************************************

    public TreeNode sortedArrayToBST(int[] num) {

        if (num.length == 0) {
            return null;
        }
        TreeNode head = helperSortedArrayToBST(num, 0, num.length - 1);
        return head;
    }

    public TreeNode helperSortedArrayToBST(int[] num, int low, int high) {

        if (low > high) { // Done
            return null;
        }
        int mid = (low + high) / 2;
        TreeNode node = new TreeNode(num[mid]);
        node.left = helperSortedArrayToBST(num, low, mid - 1);
        node.right = helperSortedArrayToBST(num, mid + 1, high);
        return node;
    }

    //*****************************************************************************************
    // isValidBST
    //******************************************************************************************

    public boolean isValidBST(TreeNode root) {

        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {

        if (root == null) return true;
        if (root.val >= maxVal || root.val <= minVal) return false;

        return isValidBST(root.left, minVal, root.val)
                && isValidBST(root.right, root.val, maxVal);
    }

    //******************************************************************************************
    // iterator
    //******************************************************************************************

    /*******************************************************************************************

     Steps:

     Recursively find the node that has the same value as the key,
     while setting the left/right nodes equal to the returned subtree
     Once the node is found, have to handle the below 4 cases
     node doesn't have left or right - return null
     node only has left subtree- return the left subtree
     node only has right subtree- return the right subtree
     node has both left and right - find the minimum value in the right subtree,
     set that value to the currently found node, then recursively delete the
     minimum value in the right subtree

     */

    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) {
            return null;
        }

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }

        return root;
    }

    private TreeNode findMin(TreeNode node) {

        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    class BSTIterator {
        private Deque<TreeNode> stack = new LinkedList<TreeNode>();

        public BSTIterator(TreeNode root) {
            pushAll(root);
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        public int next() {
            TreeNode tmpNode = stack.pop();
            pushAll(tmpNode.right);
            return tmpNode.val;
        }

        private void pushAll(TreeNode node) {
            //for (; node != null; stack.push(node), node = node.left) ;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

    }


}
