package solution;

import java.util.Arrays;

public class Jan_16 {
    /*
    https://school.programmers.co.kr/learn/courses/30/lessons/12951
    buffer 쓸 때는 (조건에 해당하지 않는 문자를) 초기화 안 하는 거 놓치지 않도록 조심하고
    분명히 더 간단한 로직이 있을 텐데 그건 복습하면서 좀 생각해보기...
     */
    public String solution_problem1(String s) {
        int index = 0;
        int sLength = s.length();
        char[] buffer = new char[sLength];

        while (index < sLength) {
            char c = s.charAt(index);

            if (c != ' ') {
                if (Character.isAlphabetic(c)) {
                    c = Character.toUpperCase(c);
                }

                buffer[index] = c;

                index++;

                while (index < sLength) {
                    char wc = s.charAt(index);
                    if (wc == ' ') {
                        buffer[index] = wc;
                        break;
                    }
                    buffer[index] = Character.toLowerCase(wc);
                    index++;
                }
            } else {
                buffer[index] = c;
            }

            index++;
        }

        return new String(buffer);
    }

    /*
    https://school.programmers.co.kr/learn/courses/30/lessons/12941
    문제의 의도가 뭐지...
     */
    public int solution_problem2(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int minimalSum = 0;

        for (int i = 0; i < A.length; i++) {
            minimalSum += (A[i] * B[A.length - 1 - i]);
        }

        return minimalSum;
    }

    /*
    https://school.programmers.co.kr/learn/courses/30/lessons/70129
    다른 사람들은 엄청 쉽게 풀었나 본데 궁금하구만
     */
    public int[] solution_problem3(String s) {
        int zeroCount = 0;
        int trimCount = 0;
        int index = 0;
        int sLength = s.length();

        if (s.equals("1")) {
            return new int[]{trimCount, zeroCount};
        }

        while (index < sLength) {
            if (s.charAt(index) == '0') {
                zeroCount++;
            }
            index++;
        }

        trimCount++;

        int numLength = sLength - zeroCount;

        while (numLength != 1) {
            int oneCount = 0;

            while (numLength > 0) {
                if ((numLength & 1) == 0) {
                    zeroCount++;
                } else {
                    oneCount++;
                }
                numLength = numLength >> 1;
            }

            numLength = oneCount;
            trimCount++;
        }

        return new int[]{trimCount, zeroCount};
    }
}
