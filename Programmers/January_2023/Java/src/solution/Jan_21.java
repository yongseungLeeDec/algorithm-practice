package solution;

public class Jan_21 {
    public int solution(int n) {
        if (n % 2 == 1) {
            return 0;
        }

        int[] cache = new int[n + 1];
        cache[2] = 3;
        int sum = 0;

        for (int i = 4; i <= n; i += 2) {
            cache[i] = (cache[i - 2] * 3 + (sum * 2 + 2)) % 1000000007;
            sum += cache[i - 2];
        }

        return cache[n];
    }
}
