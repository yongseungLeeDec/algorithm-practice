package solution;

import java.util.ArrayList;

public class Feb_27 {
    public static int[][] OFFSETS = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(String[] maps) {
        char[][] map = renderMap(maps);
        int[] startPoint = getPoint(map, 'S');

        int timeToLever = getTimeToDestination(map, startPoint, 'L');

        int[] leverPoint = getPoint(map, 'L');

        int timeToExit = getTimeToDestination(map, leverPoint, 'E');

        if (timeToLever == -1 || timeToExit == -1) {
            return -1;
        }

        return timeToLever + timeToExit;
    }

    private int getTimeToDestination(char[][] map, int[] startPoint, char destChar) {
        int numRow = map.length;
        int numCol = map[0].length;

        char[][] copy = new char[numRow][numCol];

        for (int r = 0; r < numRow; r++) {
            System.arraycopy(map[r], 0, copy[r], 0, numCol);
        }

        ArrayList<Integer> rowQueue = new ArrayList<>();
        ArrayList<Integer> colQueue = new ArrayList<>();
        ArrayList<Integer> distQueue = new ArrayList<>();

        rowQueue.add(startPoint[0]);
        colQueue.add(startPoint[1]);
        distQueue.add(0);

        copy[startPoint[0]][startPoint[1]] = 'X';

        while (rowQueue.size() != 0 && colQueue.size() != 0) {
            int curRow = rowQueue.get(0);
            int curCol = colQueue.get(0);
            int curDist = distQueue.get(0);

            rowQueue.remove(0);
            colQueue.remove(0);
            distQueue.remove(0);

            for (int[] offset : OFFSETS) {
                int nextY = offset[0] + curRow;
                int nextX = offset[1] + curCol;

                if (0 <= nextY && nextY < numRow && 0 <= nextX && nextX < numCol) {
                    if (copy[nextY][nextX] == destChar) {
                        return curDist + 1;
                    }
                    if (copy[nextY][nextX] != 'X') {
                        // 옆에 인접한 방문 가능 지점을 추가할 수 있기 때문에 무한히는 아니어도, 많이 늘어남
                        // 이래서 무한 루프까지는 아니더라도 시간이 어마어마하게 걸렸던 것.
                        copy[nextY][nextX] = 'X';
                        rowQueue.add(nextY);
                        colQueue.add(nextX);
                        distQueue.add(curDist + 1);
                    }
                }
            }
        }

        return -1;
    }

    private char[][] renderMap(String[] maps) {
        int numRow = maps.length;
        int numCol = maps[0].length();

        char[][] map = new char[numRow][numCol];

        for (int r = 0; r < numRow; r++) {
            for (int c = 0; c < numCol; c++) {
                map[r][c] = maps[r].charAt(c);
            }
        }

        return map;
    }

    private int[] getPoint(char[][] map, char targetChar) {
        int[] point = new int[2];

        int numRow = map.length;
        int numCol = map[0].length;

        for (int r = 0; r < numRow; r++) {
            for (int c = 0; c < numCol; c++) {
                if (map[r][c] == targetChar) {
                    point[0] = r;
                    point[1] = c;
                    break;
                }
            }
        }

        return point;
    }
}
