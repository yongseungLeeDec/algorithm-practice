package solution;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Jan_03 {

    /*
    문제 이름: 가장 가까운 같은 글자
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/142086
     */
    public int[] solution_problem1(String s) {
        int stringLength = s.length();
        int[] answer = new int[stringLength];

        int[] lastAppearedIndexes = new int[26];
        Arrays.fill(lastAppearedIndexes, Integer.MIN_VALUE);

        for (int i = 0; i < stringLength; ++i) {
            char currentChar = s.charAt(i);
            int alphabetIndex = (int) currentChar - 'a';

            if (lastAppearedIndexes[alphabetIndex] == Integer.MIN_VALUE) {
                answer[i] = -1;
            } else {
                answer[i] = i - lastAppearedIndexes[alphabetIndex];
            }

            lastAppearedIndexes[alphabetIndex] = i;
        }

        return answer;
    }

    /*
    문제 이름: 문자열 나누기
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/140108
    */
    public int solution_problem2(String s) {
        int strLen = s.length();
        int numTokens = 0;
        char firstChar = s.charAt(0);
        int sameCharCounts = 1;
        int diffCharCounts = 0;

        boolean startAgain = false;

        for (int i = 1; i < strLen; ++i) {
            if (startAgain) {
                firstChar = s.charAt(i);
                sameCharCounts = 1;
                diffCharCounts = 0;
                startAgain = false;
                continue;
            }

            if (s.charAt(i) == firstChar) {
                sameCharCounts++;
            } else {
                diffCharCounts++;
            }

            if (sameCharCounts == diffCharCounts) {
                numTokens++;
                startAgain = true;
            }
        }

        if (!startAgain) {
            numTokens++;
        }

        return numTokens;
    }

    public int solution_problem2_second(String s) {
        int sLen = s.length();
        int sameCharCount = 0;
        int diffCharCount = 0;
        int numTokens = 0;
        char startChar = ' '; // 불가피한 초기화
        boolean isStartOfSubS = true;

        for (int i = 0; i < sLen; ++i) {
            if (isStartOfSubS) {
                startChar = s.charAt(i);
                sameCharCount++;
                numTokens++;
                isStartOfSubS = false;
                continue;
            }

            if (s.charAt(i) == startChar) {
                sameCharCount++;
            } else {
                diffCharCount++;
            }

            if (sameCharCount == diffCharCount) {
                isStartOfSubS = true;
            }
        }

        return numTokens;
    }

    /*
    문제 이름: 명예의 전당 (1)
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/138477
     */
    public int[] solution_problem3(int k, int[] score) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        int[] dailyMinScores = new int[score.length];

        int count = 0;

        while (count < k && count < score.length) {
            pq.add(score[count]);
            dailyMinScores[count] = pq.peek();
            count++;
        }

        for (int i = k; i < score.length; i++) {
            if (pq.peek() < score[i]) {
                pq.poll();
                pq.add(score[i]);
            }
            dailyMinScores[i] = pq.peek();
        }

        return dailyMinScores;
    }
}
