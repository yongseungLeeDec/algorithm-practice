package solution;

import java.util.PriorityQueue;

public class Feb_23 {
    public static void main(String[] args) {
        System.out.println(getTargetNumberString(54, 3));
    }
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int r = 0; r < enemy.length; r++) {
            queue.add(enemy[r]);

            if (queue.size() > k) {
                n -= queue.poll();
            }

            if (n < 0) {
                return r;
            }
        }

        return enemy.length;
    }

    public int solution(int n, int k) {
        String number = getTargetNumberString(n, k);
        String[] nums = number.split("0+");

        int primeCount = 0;

        for (String num : nums) {
            if (isPrime(Long.parseLong(num))) {
                primeCount++;
            }
        }

        return primeCount;
    }

    private boolean isPrime(long num) {
        if (num == 1) {
            return false;
        }

        int sqrt = (int) Math.sqrt(num);

        int div = 2;
        while (div <= sqrt) {
            if (num % div == 0) {
                return false;
            }
            div++;
        }

        return true;
    }

    private static String getTargetNumberString(int n, int k) {
        StringBuilder nBaseK = new StringBuilder();

        while (n > 1) {
            nBaseK.append(n % k);
            n = (n / k);
        }

        if (n != 0) {
            nBaseK.append(n);
        }

        return nBaseK.reverse().toString();
    }
}
