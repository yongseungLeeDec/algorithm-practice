package solution.jan.seventh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class _1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numInputs = Integer.parseInt(br.readLine());
        ArrayList<Integer> inputs = new ArrayList<>(numInputs);
        int inputCount = 0;

        while (inputCount < numInputs) {
            inputs.add(Integer.parseInt(br.readLine()));
            inputCount++;
        }

        StringBuilder outputBuilder = new StringBuilder();

        int[][] cache = new int[40 + 1][2];

        cache[0][0] = 1;
        cache[0][1] = 0;
        cache[1][0] = 0;
        cache[1][1] = 1;

        for (Integer input : inputs) {
            for (int i = 2; i <= input; ++i) {
                cache[i][0] = cache[i - 1][0] + cache[i - 2][0];
                cache[i][1] = cache[i - 1][1] + cache[i - 2][1];
            }
            outputBuilder.append(cache[input][0]).append(" ").append(cache[input][1]).append(System.lineSeparator());
        }

        System.out.println(outputBuilder);
    }

}