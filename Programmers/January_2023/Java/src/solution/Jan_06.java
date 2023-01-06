package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Jan_06 {
    /*
    없는 숫자 더하기
    https://school.programmers.co.kr/learn/courses/30/lessons/86051
     */
    public int solution_problem1(int[] numbers) {
        int sum = 0;
        boolean[] checkArray = new boolean[10];

        for (int number : numbers) {
            checkArray[number] = true;
        }

        for (int i = 0; i < checkArray.length; ++i) {
            if (checkArray[i] == false) {
                sum += i;
            }
        }

        return sum;
    }

    /*
    부족한 금액 계산하기

     */
    public long solution_problem2(int price, int money, int count) {
        long requiredMoney = 0;
        long actualCount = 1;

        while (actualCount <= count) {
            requiredMoney += actualCount * price;
            actualCount++;
        }

        if (requiredMoney <= money) {
            return 0;
        }

        return requiredMoney - money;
    }

    /*
    숫자 문자열과 영단어 https://school.programmers.co.kr/learn/courses/30/lessons/81301
    이렇게 푸는 거 맞아? 너무 쉬운데...??
    만약 Java API 못 쓰게 했으면 꽤 빡셌겠지만...
     */
    public int solution_problem2(String s) {
        String number = s
                .replaceAll("zero", "0")
                .replaceAll("one", "1")
                .replaceAll("two", "2")
                .replaceAll("three", "3")
                .replaceAll("four", "4")
                .replaceAll("five", "5")
                .replaceAll("six", "6")
                .replaceAll("seven", "7")
                .replaceAll("eight", "8")
                .replaceAll("nine", "9");

        return Integer.parseInt(number);
    }

    public int solution_problem2_second(String s) {
        int index = 0;
        int sLen = s.length();
        StringBuilder numberBuilder = new StringBuilder();

        while (index < sLen) {
            char character = s.charAt(index);

            if ('0' <= character && character <= '9') {
                numberBuilder.append(character);
                index++;
                continue;
            }

            if (character == 'z') {
                numberBuilder.append('0');
                index += 4;
                continue;
            }

            if (character == 'o') {
                numberBuilder.append('1');
                index += 3;
                continue;
            }

            if (character == 't') {
                index++;
                character = s.charAt(index);
                if (character == 'w') {
                    numberBuilder.append('2');
                    index += 2;
                    continue;
                } else {
                    numberBuilder.append('3');
                    index += 4;
                    continue;
                }
            }

            if (character == 'f') {
                index++;
                character = s.charAt(index);
                if (character == 'o') {
                    numberBuilder.append('4');
                    index += 3;
                    continue;
                } else {
                    numberBuilder.append('5');
                    index += 3;
                    continue;
                }
            }

            if (character == 's') {
                index++;
                character = s.charAt(index);
                if (character == 'i') {
                    numberBuilder.append('6');
                    index += 2;
                    continue;
                } else {
                    numberBuilder.append('7');
                    index += 4;
                    continue;
                }
            }

            if (character == 'e') {
                numberBuilder.append('8');
                index += 5;
                continue;
            }

            if (character == 'n') {
                numberBuilder.append('9');
                index += 4;
            }
        }

        return Integer.parseInt(numberBuilder.toString());
    }

    /*
    약수의 개수와 덧셈 https://school.programmers.co.kr/learn/courses/30/lessons/77884
    다른 사람들 풀이가 있나..
     */
    public int solution_problem3(int left, int right) {
        int result = 0;
        int number = left;
        int numDivisors;

        while (number <= right) {
            numDivisors = 1;

            for (int num = 1; num <= number / 2; ++num) {
                if (number % num == 0) {
                    numDivisors++;
                }
            }

            if (numDivisors % 2 == 0) {
                result += number;
            } else {
                result -= number;
            }

            number++;
        }

        return result;
    }

    public int[] solution_problem4(int[] lottos, int[] win_nums) {
        boolean[] winNumTable = new boolean[46];

        for (int winNum : win_nums) {
            winNumTable[winNum] = true;
        }

        int confirmed = 0;
        int unreadable = 0;

        for (int num : lottos) {
            if (num == 0) {
                unreadable++;
                continue;
            }

            if (winNumTable[num]) {
                confirmed++;
            }
        }

        int maxWinCount = confirmed + unreadable;
        int minWinCount = confirmed;

        int highestRank = 1 <= maxWinCount ? (7 - maxWinCount) : 6;
        int lowestRank = 1 <= minWinCount ? (7 - minWinCount) : 6;

        return new int[]{highestRank, lowestRank};
    }

    public int[] solution_problem5(String today, String[] terms, String[] privacies) {
        int todayInDays = convertDateInDays(today);

        HashMap<Character, Integer> termValidityInDays = new HashMap<>();
        for (String term : terms) {
            termValidityInDays.put(term.charAt(0), Integer.parseInt(term.substring(2)) * 28);
        }

        ArrayList<Integer> privaciesToBeDeleted = new ArrayList<>();

        int customerNumber = 1;

        for (String privacy : privacies) {
            char term = privacy.charAt(11);
            int collectedDateInDays = convertDateInDays(privacy.substring(0, 10));
            int expiryDateInDays = collectedDateInDays + termValidityInDays.get(term);

            if (todayInDays > expiryDateInDays) {
                privaciesToBeDeleted.add(customerNumber);
            }

            customerNumber++;
        }

        int[] answer = new int[privaciesToBeDeleted.size()];

        int index = 0;

        for (Integer customerNum : privaciesToBeDeleted) {
            answer[index++] = customerNum;
        }

        return answer;
    }

    public int convertDateInDays(String date) {
        int dateInDays;
        String[] dateParsed = date.split("\\.");

        dateInDays = (Integer.parseInt(dateParsed[0]) % 2000) * (28 * 12);
        dateInDays += Integer.parseInt(dateParsed[1]) * 28;
        dateInDays += Integer.parseInt(dateParsed[2]);

        return dateInDays;
    }
}
