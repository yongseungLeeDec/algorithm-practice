package solution.jan.fifth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2839 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int totalAmount = Integer.parseInt(br.readLine());

            int numRow = Math.max(totalAmount, 8);

            int[][] cache = new int[numRow + 1][2];
            cache[3][0] = 1;
            cache[5][1] = 1;
            cache[6][0] = 2;

            for (int row = 8; row <= totalAmount; row++) {
                if ((row % 5) == 0) {
                    cache[row][1] = row / 5;
                    continue;
                }

                cache[row][0] = cache[row - 3][0] + cache[3][0];
                cache[row][1] = cache[row - 3][1] + cache[3][1];
            }

            int numBags = cache[totalAmount][0] + cache[totalAmount][1];

            if (numBags == 0) {
                System.out.println("-1");
            } else {
                System.out.println(numBags);
            }
        }
    }
}
