package solution;

public class Jan_25 {
    public int solution(String s) {
        int sLength = s.length();
        int count = 0;

        for (int i = 0; i < sLength; ++i) {
            if (isValidParenthesesSet(i, s)) {
                count++;
            }
        }

        return count;
    }

    private boolean isValidParenthesesSet(int startIndex, String s) {
        int sLength = s.length();
        int visitedCharCount = 0;
        int index = startIndex;
        char[] stack = new char[sLength];
        int top = 0;

        while (visitedCharCount < sLength) {
            char c = s.charAt(index);

            if (c == '(' || c == '{' || c == '[') {
                stack[top] = c;
                top++;
            } else {
                if (top != 0 && ((c == ')' && stack[top - 1] == '(') || (c == '}' && stack[top - 1] == '{') || (c == ']' && stack[top - 1] == '['))) {
                    top--;
                } else {
                    return false;
                }
            }

            index = (index + 1) % sLength;
            visitedCharCount++;
        }

        return top == 0;
    }

    /*

     */
    public int[][] solution_p2(int[][] arr1, int[][] arr2) {
        int m1numRows = arr1.length;
        int m2numCols = arr2[0].length;

        int[][] matrix = new int[m1numRows][m2numCols];

        for (int m1row = 0; m1row < m1numRows; m1row++) {
            for (int m2col = 0; m2col < m2numCols; m2col++) {
                int res = 0;
                for (int m2Row = 0; m2Row < arr2.length; m2Row++) {
                    res += arr1[m1row][m2Row] * arr2[m2Row][m2col];
                }
                matrix[m1row][m2col] = res;
            }
        }

        return matrix;
    }

    /*
    복습 좀 해~!
     */
    public int[] solution(int n, long left, long right) {
        int[] array = new int[(int) (right - left + 1)];
        int index = 0;

        long k = (left / n) + 1;
        long kRepeated = left % n;

        while (index < array.length) {
            while (index < array.length && kRepeated < k) {
                array[index++] = (int) k;
                kRepeated++;
            }

            /* 이 행이 문제였음 */
            long val = kRepeated + 1;

            while (index < array.length && val <= n) {
                array[index++] = (int) val;
                val++;
            }

            k += 1;
            kRepeated = 0;
        }

        return array;
    }
}
