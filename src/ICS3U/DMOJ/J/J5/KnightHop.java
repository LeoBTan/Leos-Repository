package ICS3U.DMOJ.J.J5;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class KnightHop {

    static int[] possX = {2, 2, 1, 1, -1, -1, -2, -2};
    static int[] possY = {1, -1, 2, -2, 2, -2, 1, -1};

    public static class Position {
        int x, y, moves;
        public Position(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);


        int startX = scan.nextInt() - 1;
        int startY = scan.nextInt() - 1;
        int endX = scan.nextInt() - 1;
        int endY = scan.nextInt() - 1;

        System.out.println(bfs(startX, startY, endX, endY));
    }

    public static int bfs(int sX, int sY, int eX, int eY) {
        boolean[][] visited = new boolean[8][8];
        Queue<Position> queue = new LinkedList<>();

        queue.add(new Position(sX, sY, 0));
        visited[sX][sY] = true;

        while (!queue.isEmpty()) {
            Position currPos = queue.poll();

            if (currPos.x == eX && currPos.y == eY) {
                return currPos.moves;
            }

            for (int i = 0; i < 8; i++) {
                int nx = currPos.x + possX[i];
                int ny = currPos.y + possY[i];

                if (nx >= 0 && nx < 8 && ny >= 0 && ny < 8 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new Position(nx, ny, currPos.moves + 1));
                }
            }
        }

        return -1;
    }
}