package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Jan_18 {
    /*
    https://school.programmers.co.kr/learn/courses/30/lessons/120875
     */
    public int solution_1(int[][] dots) {
        Integer[][] dotsBoxed = new Integer[4][2];

        for (int i = 0; i < dots.length; ++i) {
            dotsBoxed[i][0] = dots[i][0];
            dotsBoxed[i][1] = dots[i][1];
        }

        Arrays.sort(dotsBoxed, Comparator.comparingInt(p -> p[0]));
        ArrayList<Integer[]> pointQueue = new ArrayList<>(3);

        for (int p = 1; p < dots.length; p++) {
            pointQueue.add(dotsBoxed[p]);
        }

        int numCase = 0;

        while (numCase < 3) {
            Integer[] point = pointQueue.get(0);
            pointQueue.remove(0);
            Integer[] point1 = pointQueue.get(0);
            Integer[] point2 = pointQueue.get(1);

            int slope1 = (point[1] - dotsBoxed[0][1]) / (point[0] - dotsBoxed[0][0]);
            int slope2 = (point2[1] - point1[1]) / (point2[0] - point1[0]);

            if (slope1 == slope2) {
                return 1;
            }

            pointQueue.add(point);
            numCase++;
        }

        return 0;
    }

    /*
    https://math.stackexchange.com/questions/1305375/what-is-the-next-number-having-the-same-number-of-bit-1s
    Bit hack을 쓰거나
    Integer. BitCount 라는 메소드를 쓰거나!
     */
    public int solution(int n) {
        int answer = n;
        int nCompared = n + 1;
        int nOneCount = getOneCount(n);

        while (nCompared < Integer.MAX_VALUE) {
            int num = nCompared;
            int numOneCount = getOneCount(num);

            if (nOneCount == numOneCount) {
                answer = num;
                break;
            }

            nCompared++;
        }

        return answer;
    }

    public int getOneCount(int num) {
        int count = 0;

        while (num > 0) {
            if ((num & 1) == 1) {
                count++;
            }

            num = num >> 1;
        }

        return count;
    }

    /*
    점화식을 만드는 능력!
    https://www.geeksforgeeks.org/count-ways-express-number-sum-consecutive-numbers/
    https://school.programmers.co.kr/learn/courses/30/lessons/12924
     */
    public int solution_3(int n) {
        int sequenceLength = 1;
        int count = 0;

        while (n > (sequenceLength * (sequenceLength - 1)) / 2) {
            double startNumber = (double) (n - (sequenceLength * (sequenceLength - 1)) / 2) / (double) sequenceLength;
            if (startNumber - (int) startNumber == 0) {
                count++;
            }

            sequenceLength++;
        }

        return count;
    }
}
