package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Jan_09 {
    /*
    문제 이름: 완주하지 못한 선수
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/42576
     */
    public String solution_problem1(String[] participant, String[] completion) {
        HashMap<String, Integer> nameOccurrences = new HashMap<>();

        for (String p : participant) {
            nameOccurrences.putIfAbsent(p, 0);
            nameOccurrences.put(p, nameOccurrences.get(p) + 1);
        }

        for (String c : completion) {
            nameOccurrences.put(c, nameOccurrences.get(c) - 1);
            if (nameOccurrences.get(c) == 0) {
                nameOccurrences.remove(c);
            }
        }

        return new ArrayList<>(nameOccurrences.keySet()).get(0);
    }

    public int solution_problem2(int n, int[] lost, int[] reserve) {
        int minimum = n - lost.length;
        int saved = 0;

        Arrays.sort(lost);
        Arrays.sort(reserve);

        int p = 0;
        int q = 0;

        while (p < lost.length && q < reserve.length) {
            int difference = lost[p] - reserve[q];

            if (difference == 0) {
                saved++;
                p++;
                q++;
                continue;
            }

            if (Math.abs(difference) >= 2) {
                if (lost[p] > reserve[q]) {
                    q++;
                } else {
                    p++;
                }
                continue;
            }

            if (difference < 0) {
                if (p == lost.length - 1 || lost[p + 1] != reserve[q]) {
                    saved++;
                    p++;
                    q++;
                } else {
                    p++;
                }
                continue;
            }

            p++;
            q++;
            saved++;
        }

        return minimum + saved;
    }

    public int solution_problem3(String dartResult) {
        int[] tryoutScores = new int[3];
        boolean[] effects = new boolean[3];

        int charIndex = -1;
        int tryoutCount = 0;
        boolean isStarEffectAppliedInFirstTryout = false;

        int tryoutScore = 0;

        while (tryoutCount < 3) {
            char c = dartResult.charAt(++charIndex);

            if ('0' <= c && c <= '9') {
                tryoutScore = (tryoutScore * 10) + (c - '0');
                continue;
            }

            switch (c) {
                case 'S':
                    tryoutScore = (int) Math.pow(tryoutScore, 1);
                    break;
                case 'D':
                    tryoutScore = (int) Math.pow(tryoutScore, 2);
                    break;
                case 'T':
                    tryoutScore = (int) Math.pow(tryoutScore, 3);
                    break;
                default:
                    break;
            }

            if (charIndex != dartResult.length() - 1) {
                if (dartResult.charAt(charIndex + 1) == '*') {
                    if (!isStarEffectAppliedInFirstTryout) {
                        tryoutScore *= 2;

                        if (tryoutCount == 0) {
                            isStarEffectAppliedInFirstTryout = true;
                        }
                    }

                    for (int i = 0; i < tryoutCount; i++) {
                        tryoutScores[i] *= 2;

                    }

                    effects[tryoutCount] = true;
                } else if (dartResult.charAt(charIndex + 1) == '#') {
                    tryoutScore *= -1;
                    effects[tryoutCount] = true;
                }
            }

            tryoutScores[tryoutCount] = tryoutScore;
            tryoutScore = 0;
            tryoutCount++;
        }

        int scoresSum = 0;

        for (int score : tryoutScores) {
            scoresSum += score;
        }

        return scoresSum;
    }
}
