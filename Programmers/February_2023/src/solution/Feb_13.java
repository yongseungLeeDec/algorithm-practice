package solution;

import java.util.ArrayList;
import java.util.Arrays;

public class Feb_13 {

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;

        Arrays.sort(arrayA);
        Arrays.sort(arrayB);

        ArrayList<Integer> divsOfLeastA = getDivisors(arrayA[0]);
        ArrayList<Integer> divsOfLeastB = getDivisors(arrayB[0]);

        ArrayList<Integer> aCandidates = getCandidates(arrayA, divsOfLeastA);
        ArrayList<Integer> bCandidates = getCandidates(arrayB, divsOfLeastB);

        for (Integer aCand : aCandidates) {
            boolean isAnswer = true;

            for (int b : arrayB) {
                if (b % aCand == 0) {
                    isAnswer = false;
                    break;
                }
            }

            if (isAnswer && aCand > answer) {
                answer = aCand;
            }
        }

        for (Integer bCand : bCandidates) {
            boolean isAnswer = true;

            for (int a : arrayA) {
                if (a % bCand == 0) {
                    isAnswer = false;
                    break;
                }
            }

            if (isAnswer && bCand > answer) {
                answer = bCand;
            }
        }

        return answer;
    }

    private ArrayList<Integer> getCandidates(int[] array, ArrayList<Integer> divs) {
        ArrayList<Integer> candidates = new ArrayList<>();

        for (Integer div : divs) {
            boolean isCandidate = true;

            for (int el : array) {
                if (el % div != 0) {
                    isCandidate = false;
                    break;
                }
            }

            if (isCandidate) {
                candidates.add(div);
            }
        }

        return candidates;
    }

    public ArrayList<Integer> getDivisors(int num) {
        ArrayList<Integer> divisors = new ArrayList<>();

        for (int i = 1; i <= num / 2; ++i) {
            if (num % i == 0) {
                divisors.add(i);
            }
        }

        divisors.add(num);

        return divisors;
    }

    public static int euclid(int a, int b) {
        int r = a % b;

        while (r > 0) {
            a = b;
            b = r;
            r = a % b;
        }

        return b;
    }
}
