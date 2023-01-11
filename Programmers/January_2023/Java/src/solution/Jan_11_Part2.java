package solution;

import java.util.Arrays;

public class Jan_11_Part2 {
    /*
    문제 이름: [1차] 비밀지도
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/17681
    소요 시간: 11분
    교훈: 사소한 실수를 하지 말자 (비트연산자 |와 &, 비트연산자 쉬프트 관련
     */
    public String[] solution_problem1(int n, int[] arr1, int[] arr2) {
        String[] map = new String[n];

        char[] rowCache = new char[n];
        int rowCacheIndex = n - 1;

        int numRow = 0;
        while (numRow < n) {
            int overlaid = arr1[numRow] | arr2[numRow];

            while (rowCacheIndex >= 0) {
                if ((overlaid & 1) == 1) {
                    rowCache[rowCacheIndex] = '#';
                } else {
                    rowCache[rowCacheIndex] = ' ';
                }
                rowCacheIndex--;
                overlaid = overlaid >> 1;
            }
            rowCacheIndex = n - 1;

            map[numRow] = new String(rowCache);

            numRow++;
        }

        return map;
    }

    /*
    입국심사

    https://school.programmers.co.kr/learn/courses/30/lessons/43238

    다른 사람들 풀이 참고하지 않았으면 풀지 못해을 문제
    이분탐색이 내 의도대로 동작하도록 하기 위해 꼼꼼한 확인 필요... 그리고 많이 풀어봐야 하겠다. 최소 10문제 정도.
     */
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        long minTime = 1;
        long maxTime = (long) n * times[times.length - 1];
        long givenTime = 0;
        long answer = 0;

        while (minTime <= maxTime) {
            givenTime = (minTime + maxTime) / 2;
            long numPeopleProcessed = 0;

            for (int time : times) {
                numPeopleProcessed += givenTime / time;
            }

            if (numPeopleProcessed >= n) {
                answer = givenTime;
                maxTime = givenTime - 1;
            } else if (numPeopleProcessed < n) {
                minTime = givenTime + 1;
            }
        }

        return answer;
    }
}
