package solution;

public class Feb_6 {
    public int[] solution(int[] numbers) {
        int[] answers = new int[numbers.length];
        int itr = numbers.length - 1;

        answers[itr] = -1;
        itr--;

        while (itr >= 0) {
            int num = numbers[itr];

            if (num < numbers[itr + 1]) {
                answers[itr] = numbers[itr + 1];
                itr--;
                continue;
            }

            int bigNumSearchItr = itr + 1;

            while (bigNumSearchItr < answers.length) {
                int val = answers[bigNumSearchItr];

                if (val == -1 || num < val) {
                    answers[itr] = val;
                    break;
                }

                bigNumSearchItr++;
            }

            itr--;
        }

        return answers;
    }
}
