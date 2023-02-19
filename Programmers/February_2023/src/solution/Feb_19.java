package solution;

import java.util.Arrays;

public class Feb_19 {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        final int colFinal = col;

        Arrays.sort(data, (row1, row2) -> {
            if (row1[colFinal - 1] == row2[colFinal - 1]) {
                return row2[0] - row1[0];
            }
            return row1[colFinal - 1] - row2[colFinal - 1];
        });

        int s = 0;

        for (int r = row_begin - 1; r < row_end; r++) {
            int[] row = data[r];
            int sR = 0;
            for (int elem : row) {
                sR += elem % (r + 1);
            }

            s ^= sR;
        }

        return s;
    }
}
