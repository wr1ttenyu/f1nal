package wr1ttenyu.f1nal.study.leetcode;

public class IslandMaxArea {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        Solution solution = new Solution();
        int i = solution.maxAreaOfIsland(grid);
        System.out.println(i);
    }
}

class Solution {
    private int maxLength;
    private int maxWidth;

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int maxSize = 0;
        maxLength = grid[0].length;
        maxWidth = grid.length;
        for (int i = 0; i < maxWidth; i++) {
            for (int j = 0; j < maxLength; j++) {
                int ij = grid[i][j];
                if (ij == 1) {
                    maxSize = Math.max(maxSize, goEveryWhere(i, j, grid));
                }
            }
        }
        return maxSize;
    }

    private int goEveryWhere(int i, int j, int[][] grid) {
        grid[i][j] = 0;
        return 1 + goLeft(i, j, grid) + goRight(i, j, grid) + goUp(i, j, grid) + goDown(i, j, grid);
    }

    private int goLeft(int i, int j, int[][] grid) {
        if (j - 1 < 0) return 0;
        int left = j - 1;
        if (grid[i][left] == 1) {
            grid[i][left] = 0;
            return goEveryWhere(i, left, grid);
        } else return 0;
    }

    private int goRight(int i, int j, int[][] grid) {
        if (j + 1 > maxLength - 1) return 0;
        int right = j + 1;
        if (grid[i][right] == 1) {
            grid[i][right] = 0;
            return goEveryWhere(i, right, grid);
        } else return 0;
    }

    private int goUp(int i, int j, int[][] grid) {
        if (i - 1 < 0) return 0;
        int up = i - 1;
        if (grid[up][j] == 1) {
            grid[up][j] = 0;
            return goEveryWhere(up, j, grid);
        } else return 0;
    }

    private int goDown(int i, int j, int[][] grid) {
        if (i + 1 > maxWidth - 1) return 0;
        int down = i + 1;
        if (grid[down][j] == 1) {
            grid[down][j] = 0;
            return goEveryWhere(down, j, grid);
        } else return 0;
    }
}
