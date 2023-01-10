package solution;

import java.util.ArrayList;

public class Jan_10 {
    public int[] solution(int N, int[] stages) {
        int[] numChallengingUsersPerStage = new int[N + 1];

        for (int stage : stages) {
            numChallengingUsersPerStage[stage - 1]++;
        }

        int[] numReachedUsersPerStage = new int[N];

        numReachedUsersPerStage[N - 1] = numChallengingUsersPerStage[N] + numChallengingUsersPerStage[N - 1];
        for (int i = N - 2; i >= 0; --i) {
            numReachedUsersPerStage[i] = numChallengingUsersPerStage[i] + numReachedUsersPerStage[i + 1];
        }

        double[] failureRates = new double[N];

        for (int i = 0; i < N; ++i) {
            failureRates[i] = (double) numChallengingUsersPerStage[i] / (double) numReachedUsersPerStage[i];
        }

        ArrayList<Integer> result = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            result.add(i + 1);
        }

        result.sort((num0, num1) -> {
            double diff = failureRates[num0 - 1] - failureRates[num1 - 1];
            if (diff > 0) {
                return -1;
            }

            if (diff < 0) {
                return 1;
            }

            return num0 - num1;
        });

        int[] resultInIntArray = new int[N];

        for (int i = 0; i < N; i++) {
            resultInIntArray[i] = result.get(i);
        }

        return resultInIntArray;
    }
}
