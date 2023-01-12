package solution;

import java.util.ArrayList;

public class Jan_12 {
    public boolean solution(int[][] key, int[][] lock) {
        int[][] board = new int[lock.length + 2 * (key.length - 1)][lock.length + 2 * (key.length - 1)];
        resetBoard(board, lock, key.length);

        int[][] keyRotated1 = getRotatedMatrix(key);
        int[][] keyRotated2 = getRotatedMatrix(keyRotated1);
        int[][] keyRotated3 = getRotatedMatrix(keyRotated2);
        ArrayList<int[][]> keys = new ArrayList<>();

        keys.add(key);
        keys.add(keyRotated1);
        keys.add(keyRotated2);
        keys.add(keyRotated3);

        int listIndex = 0;

        while (listIndex < 4) {
            int[][] sourceKey = keys.get(listIndex);

            for (int leftUpperRow = 0; leftUpperRow < (key.length - 1) + lock.length; leftUpperRow++) {
                for (int leftUpperCol = 0; leftUpperCol < (key.length - 1) + lock.length; leftUpperCol++) {
                    if (isLockBreakable(sourceKey, leftUpperRow, leftUpperCol, board, lock.length)) {
                        return true;
                    }
                    resetBoard(board, lock, key.length);
                }
            }
            listIndex++;
        }

        return false;
    }

    private void resetBoard(int[][] board, int[][] lock, int keyLength) {
        int lockStartRow = keyLength - 1;
        int lockStartCol = keyLength - 1;

        for (int i = 0; i < lock.length; i++) {
            for (int j = 0; j < lock.length; j++) {
                board[lockStartRow + i][lockStartCol + j] = lock[i][j];
            }
        }
    }

    private boolean isLockBreakable(int[][] key, int keyStartRow, int keyStartCol, int[][] board, int lockLength) {
        for (int i = 0; i < key.length; i++) {
            for (int j = 0; j < key.length; j++) {
                board[keyStartRow + i][keyStartCol + j] ^= key[i][j];
            }
        }

        for (int i = 0; i < lockLength; i++) {
            for (int j = 0; j < lockLength; j++) {
                if (board[key.length - 1 + i][key.length - 1 + j] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[][] getRotatedMatrix(int[][] squareMatrix) {
        int matrixSize = squareMatrix.length;
        int[][] rotatedMatrix = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                rotatedMatrix[j][matrixSize - i - 1] = squareMatrix[i][j];
            }
        }

        return rotatedMatrix;
    }
}
