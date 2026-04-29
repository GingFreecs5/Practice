import java.util.LinkedList;
import java.util.Queue;

public class Bfs {

    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == '1') { bfs(grid, i, j); count++; }
        return count;
    }

    private static void bfs(char[][] grid, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{i, j});
        grid[i][j] = '0';
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while (!q.isEmpty()) {
            int[] cell = q.poll();
            for (int[] d : dirs) {
                int r = cell[0]+d[0], c = cell[1]+d[1];
                if (r>=0 && r<grid.length && c>=0 && c<grid[0].length && grid[r][c]=='1') {
                    grid[r][c] = '0';
                    q.add(new int[]{r, c});
                }
            }
        }
    }


}
