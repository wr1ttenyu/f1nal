package wr1ttenyu.f1nal.study.designpattern.principle.singleresponsibility;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node21 = new TreeNode(2);
        TreeNode node22 = new TreeNode(2);
        TreeNode node321 = new TreeNode(3);
        TreeNode node322 = new TreeNode(3);

        node1.left = node21;
        node1.right = node22;
        node21.right = node321;
        node22.right = node322;

        System.out.println(isSymmetric(node1));

    }

    public static boolean isSymmetric(TreeNode root) {
        List<List<Integer>> nodeLevels = levelOrder(root);

        if(nodeLevels.size() == 0) return true;
        if(nodeLevels.size() == 1) return true;

        for(int k = 1; k < nodeLevels.size() - 1; k++) {
            List<Integer> level = nodeLevels.get(k);

            int loopNum = level.size() / 2;
            for(int i = 0; i < level.size() / 2; i++) {
                int j = level.size() - 1 - i;
                if(level.get(i) != level.get(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> nodeVals = new ArrayList<>(256);
        if (root != null) {
            List<Integer> rootNodeVals = new ArrayList<>(1);
            rootNodeVals.add(root.val);
            nodeVals.add(rootNodeVals);
            nodeVals.add(new ArrayList<>(2));
            handleNode(root, nodeVals, 1);
        }

        List<List<Integer>> nodeValsFinal = new ArrayList<>(256);
        for (List<Integer> childNodeVals : nodeVals) {
            if (childNodeVals.size() != 0) {
                nodeValsFinal.add(childNodeVals);
            }
        }

        return nodeValsFinal;
    }

    private static void handleNode(TreeNode root, List<List<Integer>> nodeVals, int i) {
        List<Integer> childNodeVals = nodeVals.get(i) != null ? nodeVals.get(i) : new ArrayList<>();

        if (root.left != null) childNodeVals.add(root.left.val);
        else childNodeVals.add(null);
        if (root.right != null)  childNodeVals.add(root.right.val);
        else childNodeVals.add(null);

        int j = ++i;
        List<Integer> next = null;
        if(nodeVals.size() <= j) {
            next = new ArrayList<>();
            nodeVals.add(j, next);
        }

        if (root.left != null) {
            handleNode(root.left, nodeVals, j);
        }
        if (root.right != null) {
            handleNode(root.right, nodeVals, j);
        }
    }
}