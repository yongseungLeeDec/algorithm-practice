package solution.three;

public class March_11 {
    public int solution(String[] board) {
        int oCount = 0;
        int xCount = 0;
        int pCount = 0;

        for (String row : board) {
            for (int sq = 0; sq < 3; sq++) {
                char ch = row.charAt(sq);
                if (ch == 'O') {
                    oCount++;
                } else if (ch == 'X') {
                    xCount++;
                }
            }
        }

        if (xCount > oCount || oCount - xCount >= 2) {
            return 0;
        }

        boolean oBingo = isBingoMade(board, 'O');
        boolean xBingo = isBingoMade(board, 'X');

        if (oBingo && xBingo) {
            return 0;
        }

        if (oBingo) {
            if (oCount == xCount) {
                return 0;
            }
        }

        if (xBingo) {
            if (xCount < oCount) {
                return 0;
            }
        }

        return 1;
    }

    public boolean isBingoMade(String[] board, char ch) {
        for (String row : board) {
            if (row.charAt(0) == ch
                    && row.charAt(1) == ch
                    && row.charAt(2) == ch) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0].charAt(col) == ch
                    && board[1].charAt(col) == ch
                    && board[2].charAt(col) == ch) {
                return true;
            }
        }

        if (board[0].charAt(0) == ch
                && board[1].charAt(1) == ch
                && board[2].charAt(2) == ch) {
            return true;
        }

        if (board[0].charAt(2) == ch
                && board[1].charAt(1) == ch
                && board[2].charAt(0) == ch) {
            return true;
        }

        return false;
    }
}
