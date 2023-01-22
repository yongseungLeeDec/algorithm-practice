package solution;

public class Jan_22 {
    public int solution_p1(int n, int a, int b) {
        int matchCount = 0;

        while (a != b) {
            a = ((a - 1) / 2) + 1;
            b = ((b - 1) / 2) + 1;
            matchCount++;
        }

        return matchCount;
    }

    public long solution_p2(int n) {
        long[] cache = new long[Math.max(n + 1, 3)];
        cache[1] = 1;
        cache[2] = 2;

        for (int i = 3; i <= n; i++) {
            cache[i] = (cache[i - 1] + cache[i - 2]) % 1234567L;
        }

        return cache[n];
    }

    public int solution_p3(int n) {
        int[] cache = new int[Math.max(n + 1, 3)];
        cache[1] = 1;
        cache[2] = 1;

        for (int i = 3; i <= n; ++i) {
            if (i % 2 == 0) {
                cache[i] = Math.min(cache[i - 1], cache[i / 2]);
                continue;
            }

            cache[i] = cache[i - 1] + 1;
        }

        return cache[n];
    }

    public int solution_p3_memory_efficient(int n) {
        int moveCount = 1;

        while (n > 1) {
            if (n % 2 == 0) {
                n /= 2;
                continue;
            }

            n -= 1;
            moveCount++;
        }

        return moveCount;
    }
}
