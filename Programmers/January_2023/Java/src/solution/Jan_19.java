package solution;

import java.util.Arrays;
import java.util.HashSet;

public class Jan_19 {
    public int solution_p1(String s) {
        int sLength = s.length();
        char[] cStack = new char[sLength];
        int top = 0;

        for (int i = 0; i < sLength; i++) {
            char c = s.charAt(i);

            if (top == 0 || cStack[top - 1] != c) {
                cStack[top++] = c;
                continue;
            }

            top--;
        }

        if (top == 0) {
            return 1;
        }

        return 0;
    }

    public int[] solution_p2(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> spokenWords = new HashSet<>();
        String previousWord = words[0];
        spokenWords.add(previousWord);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];

            if (word.length() == 1
                    || previousWord.charAt(previousWord.length() - 1) != word.charAt(0)
                    || spokenWords.contains(word)) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }

            spokenWords.add(word);
            previousWord = word;
        }

        return answer;
    }

    /*
     뭔가 마지막 하나가 좀 깔끔하진 않네 (같아지는 사람용 보트를 추가하는 것...)
     https://school.programmers.co.kr/learn/courses/30/lessons/42885
     다른사람 풀이 참고해서 정리!
    */
    public int solution_p3(int[] people, int limit) {
        int boatCount = 0;
        Arrays.sort(people);

        int max = people.length - 1;
        int min = 0;
        int weight = people[max];

        while (min < max) {
            if (weight + people[min] > limit) {
                boatCount++;
                max--;
                weight = people[max];
            } else {
                weight += people[min];
                min++;
            }
        }

        return boatCount + 1;
    }
}
