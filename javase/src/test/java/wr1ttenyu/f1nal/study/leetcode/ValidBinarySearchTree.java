package wr1ttenyu.f1nal.study.leetcode;

public class ValidBinarySearchTree {

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (root.left != null && root.left.val >= root.val) return false;
        if (root.right != null && root.right.val <= root.val) return false;
        return subLeftNodeValid(root.left, root.val) && subRightNodeValid(root.right, root.val, null);
    }

    private boolean subLeftNodeValid(TreeNode node, Integer maxValue) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.left != null && node.left.val >= node.val) return false;
        if (node.right != null && (node.right.val <= node.val || node.right.val >= maxValue)) return false;
        return subLeftNodeValid(node.left, maxValue) && subLeftNodeValid(node.right, maxValue);
    }

    private boolean subRightNodeValid(TreeNode node, Integer minValue, Integer maxValue) {
        if (node == null) return true;
        if (node.left == null && node.right == null) return true;
        if (node.left != null && (node.left.val >= node.val || node.left.val <= minValue)) return false;
        if (node.right != null && (node.right.val <= node.val || (maxValue != null && node.right.val >= maxValue)))
            return false;
        return subRightNodeValid(node.left, minValue, node.val) && subRightNodeValid(node.right, minValue, maxValue);
    }

    double last = -Double.MAX_VALUE;

    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (isValidBST2(root.left)) {
            if (last < root.val) {
                last = root.val;
                return isValidBST2(root.right);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode right = new TreeNode(1);
        root.right = right;
        ValidBinarySearchTree validBinarySearchTree = new ValidBinarySearchTree();
        validBinarySearchTree.isValidBST2(root);
    }
}

