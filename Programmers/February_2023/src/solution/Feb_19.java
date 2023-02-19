package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

    public static int[] solution(long begin, long end) {
        int[] answer = new int[(int) (end - begin + 1)];

        int k = (int) begin;

        for (int i = 0; i < answer.length; i++) {
            answer[i] = getDivisor(k);
            k++;
        }

        return answer;
    }

    private static int getDivisor(int num) {
        if (num == 1) {
            return 0;
        }

        if (num <= 20000000 && (num & 1) == 0) {
            return num >> 1;
        }

        ArrayList<Integer> divs = new ArrayList<>();
        double sqrt = Math.sqrt(num);

        for (int div = 1; div <= sqrt; div++) {
            if (num % div == 0) {
                divs.add(div);
                if (div != sqrt) {
                    divs.add(num / div);
                }
            }
        }

        Collections.sort(divs);

        int index = 0;
        int size = divs.size();
        int max = 1;

        while (index < size - 1 && divs.get(index) <= 10000000) {
            max = divs.get(index);
            index++;
        }

        return max;
    }
}
