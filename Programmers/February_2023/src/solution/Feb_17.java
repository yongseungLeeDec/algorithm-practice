package solution;

import java.util.HashMap;

public class Feb_17 {
    public int solution(int n) {
        HashMap<Integer, Integer> colRowMap = new HashMap<>();
        return chooseNextPosition(n, 1, Integer.MAX_VALUE, colRowMap);
    }

    private int chooseNextPosition(int n, int row, int upperRowColNum, HashMap<Integer, Integer> colRowMap) {
        if (row > n) {
            return 1;
        }

        int posCount = 0;
        int colNum = 1;

        while (colNum <= n) {
            if (Math.abs(upperRowColNum - colNum) >= 2
                    && colRowMap.get(colNum) == null
                    && hasNoQueenOnItsDiagonals(colRowMap, colNum, row)) {
                colRowMap.put(colNum, row);
                posCount += chooseNextPosition(n, row + 1, colNum, colRowMap);
                colRowMap.remove(colNum);
            }

            colNum++;
        }

        return posCount;
    }

    private boolean hasNoQueenOnItsDiagonals(HashMap<Integer, Integer> colRowMap, int colNum, int rowNum) {
        for (Integer col : colRowMap.keySet()) {
            if (Math.abs(col - colNum) == Math.abs(colRowMap.get(col) - rowNum)) {
                return false;
            }
        }
        return true;
    }
}
