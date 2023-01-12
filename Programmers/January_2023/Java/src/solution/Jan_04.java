package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Jan_04 {

    /*
    문제 이름: 기사단원의 무기
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/136798
    ---> 약수의 성질 이용하기
     */
    public int solution_problem1(int number, int limit, int power) {
        int ironCount = 0;

        for (int i = 1; i <= number; i++) {
            int attackStat = getNumDivisors(i);

            if (attackStat > limit) {
                ironCount += power;
            } else {
                ironCount += attackStat;
            }
        }

        return ironCount;
    }

    private int getNumDivisors(int number) {
        int numDivisors = 1;
        int sqrt = (int) Math.sqrt(number);
        for (int i = 1; i <= number / 2; ++i) {
            if (number % i == 0) {
                numDivisors++;
            }
        }

        return numDivisors;
    }

    public int solution_problem1_improved(int number, int limit, int power) {
        int ironCount = 0;
        int[] divisorCounts = new int[number + 1];

        for (int i = 1; i <= number; i++) {
            int maxQuotient = number / i;
            for (int j = 1; j <= maxQuotient; j++) {
                divisorCounts[i * j]++;
            }
        }

        for (int i = 1; i < divisorCounts.length; i++) {
            if (divisorCounts[i] > limit) {
                ironCount += power;
            } else {
                ironCount += divisorCounts[i];
            }
        }

        return ironCount;
    }

    /*
    문제 이름: 과일 장수
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/135808
    ---> 아주 평이한 문제
     */
    public int solution_problem2(int k, int m, int[] score) {
        Arrays.sort(score);
        int numBoxes = score.length / m;
        int sumProfit = 0;

        for (int boxNum = 1; boxNum <= numBoxes; ++boxNum) {
            sumProfit += (score[score.length - (boxNum * m)] * m);
        }

        return sumProfit;
    }

    /*
    문제 이름: 푸드 파이트 대회
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/134240
     */
    public String solution_problem3(int[] food) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < food.length; i++) {
            int repeat = food[i] / 2;
            int count = 0;
            while (count < repeat) {
                stringBuilder.append(i);
                count++;
            }
        }

        String leftSide = stringBuilder.toString();
        stringBuilder = new StringBuilder();
        stringBuilder.append(leftSide).append('0');

        int halfLength = leftSide.length();

        for (int i = halfLength - 1; i >= 0; --i) {
            stringBuilder.append(leftSide.charAt(i));
        }

        return stringBuilder.toString();
    }

    /*
    문제 이름: 햄버거 만들기
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/133502
     */
    public int solution_problem4(int[] ingredient) {
        int numBurgers = 0;
        ArrayList<Integer> progresses = new ArrayList<>();

        for (int i = 0; i < ingredient.length; ++i) {
            int ing = ingredient[i];

            if (progresses.isEmpty()) {
                if (ing == 1) {
                    progresses.add(1);
                }
                continue;
            }

            int lastProgress = progresses.get(progresses.size() - 1);

            if ((ing == 2 && lastProgress == 1) || (ing == 3 && lastProgress == 2)) {
                progresses.remove(progresses.size() - 1);
                progresses.add(lastProgress + 1);
                continue;
            }

            if ((ing == 1 && lastProgress == 3)) {
                progresses.remove(progresses.size() - 1);
                numBurgers++;
                continue;
            }

            if (ing == 1) {
                progresses.add(1);
            } else {
                progresses.clear();
            }
        }

        return numBurgers;
    }

    /*
    문제 이름: 옹알이 (2)
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/133499
     */
    public int solution_problem5(String[] babbling) {
        int babbledWordCount = 0;

        for (int i = 0; i < babbling.length; i++) {
            boolean isWordSpeakable = true;

            String word = babbling[i];

            word = word.replaceAll("aya", "1")
                    .replaceAll("ye", "2")
                    .replaceAll("woo", "3")
                    .replaceAll("ma", "4");

            char firstCharacter = word.charAt(0);

            if (firstCharacter >= '1' && firstCharacter <= '4') {
                for (int k = 1; k < word.length(); k++) {
                    char character = word.charAt(k);
                    if (character < '1' || character > '4' || character == word.charAt(k - 1)) {
                        isWordSpeakable = false;
                        break;
                    }
                }
            } else {
                continue;
            }

            if (isWordSpeakable) {
                babbledWordCount++;
            }
        }

        return babbledWordCount;
    }

    /*
    문제 이름: 성격 유형 검사하기
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/118666
     */
    public String solution_problem6(String[] survey, int[] choices) {
        char[] types = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};

        char[] personalityType = new char[4];
        HashMap<Character, Integer> scoresByType = new HashMap<>();
        initializeScoreByTypeMap(types, scoresByType);

        for (int i = 0; i < survey.length; i++) {
            char typeDisagree = survey[i].charAt(0);
            char typeAgree = survey[i].charAt(1);

            if (choices[i] < 4) {
                scoresByType.put(typeDisagree, scoresByType.get(typeDisagree) + (4 - choices[i]));
                continue;
            }

            if (choices[i] > 4) {
                scoresByType.put(typeAgree, scoresByType.get(typeAgree) + (choices[i] - 4));
            }
        }

        for (int i = 0; i < personalityType.length; ++i) {
            int left = scoresByType.get(types[i * 2]);
            int right = scoresByType.get(types[i * 2 + 1]);

            personalityType[i] = left >= right ? types[i * 2] : types[i * 2 + 1];
        }

        return new String(personalityType);
    }

    private void initializeScoreByTypeMap(char[] types, HashMap<Character, Integer> scoresByType) {
        for (char type : types) {
            scoresByType.put(type, 0);
        }
    }
}
