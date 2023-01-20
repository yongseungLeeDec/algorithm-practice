package solution;

public class Jan_20 {
    /*
      타겟 넘버
      https://school.programmers.co.kr/learn/courses/30/lessons/43165
      DFS는 늘 헷갈리네 ㅎㅎㅎ
      얘도 다른 사람 풀이 보면서 정리!!
     */
    public int solution(int[] numbers, int target) {
        return getPossibleCombinationCounts(numbers, 0, target, 0);
    }

    private int getPossibleCombinationCounts(int[] numbers, int index, int target, int result) {
        if (index == numbers.length) {
            if (result == target) {
                return 1;
            }
            return 0;
        }

        int count = 0;

        count += getPossibleCombinationCounts(numbers, index + 1, target, result + numbers[index]);
        count += getPossibleCombinationCounts(numbers, index + 1, target, result - numbers[index]);

        return count;
    }

    /*
      실패.. 다음에 재도전 할게여 ㅠ_ㅠ
     */
    public int solution_happy_path(int[][] maps) {
        int squareCount = 1;
        int cY = 0;
        int cX = 0;
        int mapHeight = maps.length;
        int mapWidth = maps[0].length;

        while (cY != mapHeight - 1 || cX != mapWidth - 1) {
            maps[cY][cX] = Integer.MIN_VALUE;

            if (cY < mapHeight - 1 && maps[cY + 1][cX] == 1) {
                cY += 1;
                squareCount++;
                continue;
            }

            if (cX < mapWidth - 1 && maps[cY][cX + 1] == 1) {
                cX += 1;
                squareCount++;
                continue;
            }

            if (cY > 0 && maps[cY - 1][cX] == 1) {
                cY -= 1;
                squareCount++;
                continue;
            }

            if (cX > 0 && maps[cY][cX - 1] == 1) {
                cX -= 1;
                squareCount++;
                continue;
            }

            return -1;
        }

        return squareCount;
    }

    private int findPath_aborted(int[][] map, int currentY, int currentX, int pathLength) {
        int mapHeight = map.length;
        int mapWidth = map[0].length;

        if (currentY == mapHeight - 1 && currentX == mapWidth - 1) {
            return pathLength;
        }

        map[currentY][currentX] = Integer.MIN_VALUE;

        int result = Integer.MAX_VALUE;

        if (currentY < mapHeight - 1 && map[currentY + 1][currentX] == 1) {
            result = Math.min(result, findPath_aborted(map, currentY + 1, currentX, pathLength + 1));
        }

        if (currentX < mapWidth - 1 && map[currentY][currentX + 1] == 1) {
            result = Math.min(result, findPath_aborted(map, currentY, currentX + 1, pathLength + 1));
        }

        if (currentY > 0 && map[currentY - 1][currentX] == 1) {
            result = Math.min(result, findPath_aborted(map, currentY - 1, currentX, pathLength + 1));
        }

        if (currentX > 0 && map[currentY][currentX - 1] == 1) {
            result = Math.min(result, findPath_aborted(map, currentY, currentX - 1, pathLength + 1));
        }

        return result;
    }

    public int solution(int[][] maps) {
        return -1;
    }
}
