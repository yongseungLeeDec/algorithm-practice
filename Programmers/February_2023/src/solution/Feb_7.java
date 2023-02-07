package solution;

public class Feb_7 {

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
