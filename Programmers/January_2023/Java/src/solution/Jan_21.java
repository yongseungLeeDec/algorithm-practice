package solution;

import java.util.Arrays;

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

    /*
     유클리드 호제법을 한 번은 공부해서 이해하자.

     */
    public int solution(int[] arr) {
        Arrays.sort(arr);
        int lcm = arr[0];
        for (int i = 1; i < arr.length; i++) {
            lcm = getLeastCommonMultiple(lcm, arr[i]);
        }
        return lcm;
    }

    private int getLeastCommonMultiple(int n, int m) {
        return (n * m) / getGreatestCommonDivisor(n, m);
    }

    private int getGreatestCommonDivisor(int n, int m) {
        int a = Math.max(n, m);
        int b = (a == n) ? m : n;
        int r = a % b;

        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }

        return a;
    }


}
