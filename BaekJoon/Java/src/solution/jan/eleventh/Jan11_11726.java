package solution.jan.eleventh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jan11_11726 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] cache = new long[n + 1];
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i <= n; i++) {
            cache[i] = cache[i - 2] + cache[i - 1];
        }

        System.out.println(cache[n] % 10007);
    }
}
