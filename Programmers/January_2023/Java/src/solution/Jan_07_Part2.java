package solution;

import java.util.ArrayList;

public class Jan_07_Part2 {
    public int solution(int[][] board, int[] moves) {
        int numRows = board.length;
        int numCols = board[0].length;

        int[] topRowIndices = new int[numCols];

        for (int col = 0; col < numCols; ++col) {
            int row;
            for (row = 0; row < numRows; ++row) {
                if (board[row][col] != 0) {
                    topRowIndices[col] = row;
                    break;
                }
            }

            if (row == numRows) {
                topRowIndices[col] = numRows;
            }
        }

        ArrayList<Integer> basket = new ArrayList<>();
        int poppedDollCount = 0;

        for (int i = 0; i < moves.length; i++) {
            int targetCol = moves[i] - 1;
            int dollNum;

            if (topRowIndices[targetCol] == numRows) {
                continue;
            }

            dollNum = board[topRowIndices[targetCol]][targetCol];
            topRowIndices[targetCol]++;

            if (basket.isEmpty()) {
                basket.add(dollNum);
                continue;
            }

            if (basket.get(basket.size() - 1) == dollNum) {
                basket.remove(basket.size() -1);
                poppedDollCount += 2;
                continue;
            }

            basket.add(dollNum);
        }

        return poppedDollCount;
    }
}
