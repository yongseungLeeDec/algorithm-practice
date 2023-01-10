package solution;

import java.util.ArrayList;
import java.util.Random;

public class Jan_10 {
    public int[] solution_problem1(int N, int[] stages) {
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

    public int solution_problem2(int[] nums) {
        int primeSumCount = 0;
        for (int f = 0; f < nums.length - 2; f++) {
            for (int s = f + 1; s < nums.length - 1; s++) {
                for (int t = s + 1; t < nums.length; t++) {
                    if (isPrime(nums[f] + nums[s] + nums[t])) {
                        primeSumCount++;
                    }
                }
            }
        }

        return primeSumCount;
    }



    public int solution_problem3(int n) {
        int answer = 0;
        int[] cache = new int[n];

        for (int i = 1; i < cache.length; i++) {
            cache[i] = cache[i - 1];
            if (isPrime(i + 1)) {
                cache[i]++;
            }
        }

        return cache[n - 1];
    }

    private boolean isPrime(int sum) {
        if (sum == 2) {
            return true;
        }

        if (sum % 2 == 0) {
            return false;
        }

        int limit = (int) Math.sqrt(sum);

        for (int i = 2; i <= limit; ++i) {
            if (sum % i == 0) {
                return false;
            }
        }

        return true;
    }

}
