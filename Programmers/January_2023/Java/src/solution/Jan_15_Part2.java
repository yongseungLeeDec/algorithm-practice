package solution;

import java.util.Arrays;

public class Jan_15_Part2 {
    /*
    체육복 문제... 내가 기필코 끝내고 만다 ㅋㅋㅋ
     */
    public int solution(int n, int[] lost, int[] reserve) {
//        int minimum = n - lost.length;
//        int saved = 0;
//
//        Arrays.sort(lost);
//        Arrays.sort(reserve);
//
//        int p = 0;
//        int q = 0;
//
//        while (p < lost.length && q < reserve.length) {
//            int difference = lost[p] - reserve[q];
//
//            if (difference == 0) {
//                saved++;
//                p++;
//                q++;
//                continue;
//            }
//
//            if (Math.abs(difference) >= 2) {
//                if (lost[p] > reserve[q]) {
//                    q++;
//                } else {
//                    p++;
//                }
//                continue;
//            }
//
//            if (difference < 0) {
//                if (p == lost.length - 1 || lost[p + 1] != reserve[q]) {
//                    saved++;
//                    p++;
//                    q++;
//                } else {
//                    p++;
//                }
//                continue;
//            }
//
//            p++;
//            q++;
//            saved++;
//        }
//
//        return minimum + saved;
        return -1;
    }

    public int solution_2(String[] babbling) {
        int pronounceableWordCount = 0;

        for (String word : babbling) {
            String modified = word
                    .replace("aya", "0")
                    .replace("ye", "1")
                    .replace("woo", "2")
                    .replace("ma", "3");

            int wordLength = modified.length();
            boolean isPronounceable = true;

            for (int i = 0; i < wordLength; i++) {
                if (!('0' <= word.charAt(i) && word.charAt(i) <= '4')) {
                    isPronounceable = false;
                    break;
                }
            }

            if (isPronounceable) {
                pronounceableWordCount++;
            }
        }

        return pronounceableWordCount;
    }

    /*
    다른 사람 풀이
     */
    public int solution_2_others(String[] babbling) {
        int answer = 0;

        for (int i = 0; i < babbling.length; i++) {
            babbling[i] = babbling[i].replace("aya", "1");
            babbling[i] = babbling[i].replace("woo", "1");
            babbling[i] = babbling[i].replace("ye", "1");
            babbling[i] = babbling[i].replace("ma", "1");
            babbling[i] = babbling[i].replace("1", "");
            if (babbling[i].isEmpty()) {
                answer = answer + 1;
            }
        }

        return answer;
    }

    /*
    fibonacci
     */
    public int solution_3(int n) {
        int[] sequence = new int[n + 1];
        sequence[1] = 1;

        for (int i = 2; i < sequence.length; i++) {
            sequence[i] = (sequence[i - 2] + sequence[i + 1]) % 1234567;
        }

        return sequence[n];
    }

    public String solution_4(String s) {
        String[] numberStrings = s.split("\\s+");
        Long[] numbers = new Long[numberStrings.length];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Long.parseLong(numberStrings[i]);
        }

        int maxIndex = 0;
        int minIndex = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > numbers[maxIndex]) {
                maxIndex = i;
                continue;
            }

            if (numbers[i] < numbers[minIndex]) {
                minIndex = i;
            }
        }

        return String.format("%d %d", numbers[minIndex], numbers[maxIndex]);
    }
}
