package solution;

public class Feb_7 {
    public int solution_dp(int x, int y, int n) {
        int[] cache = new int[y + 1];

        for (int i = 0; i < x; i++) {
            cache[i] = Integer.MAX_VALUE;
        }

        for (int num = x + 1; num <= y; num++) {
            int subNval = (num - n >= 0) ? num - n : -1;
            int div2val = (num % 2 == 0) ? num / 2 : -1;
            int div3val = (num % 3 == 0) ? num / 3 : -1;

            int prevOpCnt1 = (subNval >= 0) ? cache[subNval] : Integer.MAX_VALUE;
            int prevOpCnt2 = (div2val >= 0) ? cache[div2val] : Integer.MAX_VALUE;
            int prevOpCnt3 = (div3val >= 0) ? cache[div3val] : Integer.MAX_VALUE;

            int minOpCnt = Math.min(prevOpCnt1, prevOpCnt2);
            minOpCnt = Math.min(minOpCnt, prevOpCnt3);

            if (minOpCnt != Integer.MAX_VALUE) {
                cache[num] = minOpCnt + 1;
            } else {
                cache[num] = Integer.MAX_VALUE;
            }
        }

        return cache[y] == Integer.MAX_VALUE ? -1 : cache[y];
    }

    public static int MIN_OP_CNT = Integer.MAX_VALUE;

    public int solution(int x, int y, int n) {
        operateRecursively(x, y, n, 0);

        if (MIN_OP_CNT == Integer.MAX_VALUE) {
            return -1;
        }

        return MIN_OP_CNT;
    }

    private void operateRecursively(int x, int y, int n, int opCnt) {
        if (x == y) {
            MIN_OP_CNT = opCnt;
        }

        if (x > y || opCnt >= MIN_OP_CNT) {
            return;
        }

        operateRecursively(x * 2, y, n, opCnt + 1);
        operateRecursively(x * 3, y, n, opCnt + 1);
        operateRecursively(x + n, y, n, opCnt + 1);
    }

}
