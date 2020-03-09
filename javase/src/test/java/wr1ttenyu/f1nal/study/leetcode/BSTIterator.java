package wr1ttenyu.f1nal.study.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BSTIterator {

    private LinkedList<Integer> nodeValues = new LinkedList();

    public BSTIterator(TreeNode root) {
        inorderTraversal(root);
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        return nodeValues.pop();
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return nodeValues.peek() != null;
    }

    private void inorderTraversal(TreeNode root) {
        if (root == null) return;
        if (root.left != null) inorderTraversal(root.left);
        nodeValues.add(root.val);
        if (root.right != null) inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode right = new TreeNode(6);
        root.right = right;
        BSTIterator bstIterator = new BSTIterator(root);
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
    }
}
