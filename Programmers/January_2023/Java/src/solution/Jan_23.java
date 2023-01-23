package solution;

public class Jan_23 {
    public int[] solution(int n, long left, long right) {
        int[] arr = new int[n * n];
        int startIndex = 0;

        for (int col = 0; col < n; col++) {
            int count = 0;

            while (count < col + 1) {
                arr[startIndex + count] = col + 1;
                count++;
            }

            int rCount = 0;

            while (rCount < n - (col + 1)) {
                arr[startIndex + count] = count + 1;
                count++;
                rCount++;
            }

            startIndex += n;
        }

        int[] answerArr = new int[(int) (right - left + 1)];
        System.arraycopy(arr, (int) left, answerArr, 0, answerArr.length);

        return answerArr;
    }
}
