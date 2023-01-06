package solution.jan.sixth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _1463 {
    public static void main(String[] args) throws IOException {
        int answer;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int input = Integer.parseInt(br.readLine());
            int[] resultCache = new int[1 + input];

            for (int i = 2; i <= input; ++i) {
                resultCache[i] = resultCache[i - 1] + 1;

                if (i % 2 == 0) {
                    resultCache[i] = Math.min(resultCache[i], resultCache[i / 2] + 1);
                }

                if (i % 3 == 0) {
                    resultCache[i] = Math.min(resultCache[i], resultCache[i / 3] + 1);
                }
            }

            answer = resultCache[input];
        }

        System.out.println(answer);
    }
}
