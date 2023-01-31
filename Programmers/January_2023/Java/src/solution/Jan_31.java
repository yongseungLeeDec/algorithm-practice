package solution;

public class Jan_31 {
    /*
    https://school.programmers.co.kr/learn/courses/30/lessons/12905
    https://onlydev.tistory.com/65#:~:text=%EC%A6%89%202%EB%B2%88%EC%9D%98%20%EC%B5%9C%EC%86%9F%EA%B0%92%20%2B%201,%EC%A0%95%EC%82%AC%EA%B0%81%ED%98%95%EC%9D%84%20%EA%B5%AC%ED%95%A0%20%EC%88%98%20%EC%9E%88%EB%8B%A4.
     */
    public int solution(int[][] board) {
        int maxWidth = 0;

        int height = board.length;
        int width = board[0].length;

        for (int c = 0; c < width; c++) {
            if (board[0][c] == 1) {
                maxWidth = 1;
                break;
            }
        }

        if (maxWidth == 0) {
            for (int r = 0; r < height; r++) {
                if (board[r][0] == 1) {
                    maxWidth = 1;
                    break;
                }
            }
        }

        for (int r = 1; r < height; r++) {
            for (int c = 1; c < width; c++) {
                if (board[r][c] != 0) {
                    int leftUpper = board[r - 1][c - 1];
                    int left = board[r][c - 1];
                    int upper = board[r - 1][c];

                    int minimumAmongThree = Math.min(upper, Math.min(leftUpper, left));

                    board[r][c] = minimumAmongThree + 1;

                    if (board[r][c] > maxWidth) {
                        maxWidth = board[r][c];
                    }
                }
            }
        }

        return maxWidth * maxWidth;
    }
}
