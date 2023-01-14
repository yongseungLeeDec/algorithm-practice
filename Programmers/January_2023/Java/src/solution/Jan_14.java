package solution;

import java.util.Arrays;

public class Jan_14 {
    public int solution_1(int[] d, int budget) {
        int numDepartmentsAccepted = 0;

        Arrays.sort(d);

        for (int requestedBudget : d) {
            if (requestedBudget > budget) {
                break;
            }
            budget -= requestedBudget;
            numDepartmentsAccepted++;
        }

        return numDepartmentsAccepted;
    }

    public int solution_2(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(n % 3);
            n /= 3;
        }

        String nBaseThree = sb.toString();
        int nLength = nBaseThree.length();
        int converted = 0;

        for (int i = 1; i <= nLength; i++) {
            converted += (int) Math.pow(3, i - 1) * (nBaseThree.charAt(nLength - i) - '0');
        }

        return converted;
    }

    public int solution_2_simplified(int n) {
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            sb.append(n % 3);
            n /= 3;
        }

        String nBaseThree = sb.toString();

        return Integer.parseInt(nBaseThree, 3);
    }

    public String solution_3(String s) {
        char[] words = s.toCharArray();
        int index = 0;
        int sLength = s.length();

        while (index < sLength) {
            if (s.charAt(index) != ' ') {
                int wordLength = getWordLength(words, index);
                convertWord(words, index, wordLength);
                index += wordLength;
            } else {
                index++;
            }
        }

        return new String(words);
    }

    private int getWordLength(char[] words, int wordStartIndex) {
        int wordLength = 0;
        int iterator = wordStartIndex;

        while (iterator < words.length) {
            if (words[iterator] == ' ') {
                break;
            }
            wordLength++;
            iterator++;
        }

        return wordLength;
    }

    private void convertWord(char[] words, int wordStartIndex, int wordLength) {
        int iterator = wordStartIndex;
        int wordEndIndex = wordStartIndex + wordLength;

        while (iterator < wordEndIndex) {
            words[iterator] = Character.toUpperCase(words[iterator]);
            iterator += 2;
        }

        iterator = wordStartIndex + 1;

        while (iterator < wordEndIndex) {
            words[iterator] = Character.toLowerCase(words[iterator]);
            iterator += 2;
        }
    }

    public String solution_3_others(String s) {
        StringBuilder answer = new StringBuilder();
        int cnt = 0;
        String[] array = s.split("");

        for (String ss : array) {
            cnt = ss.contains(" ") ? 0 : cnt + 1;
            answer.append(cnt % 2 == 0 ? ss.toLowerCase() : ss.toUpperCase());
        }
        return answer.toString();
    }

    public int[] solution_4(int n, int m) {
        return new int[] {getGreatestCommonDivisor(n, m), getLeastCommonMultiple(n, m)};
    }

    /*
    유클리드 호제법 한 번은 공부해 놓으면 좋을 듯...
     */
    private int getGreatestCommonDivisor(int n, int m) {
        int a = Math.max(n, m);
        int b = (a == n) ? m : n;
        int r = a % b;

        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }

        return r;
    }

    private int getLeastCommonMultiple(int n, int m) {
        return (n * m) / getGreatestCommonDivisor(n, m);
    }
}
