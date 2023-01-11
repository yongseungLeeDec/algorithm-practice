package solution;

import java.util.Arrays;
import java.util.HashSet;

public class Jan_11 {
    /*
    싱겁긴.. 그래도 문제 잘 읽기 ㅠㅠ 착각했네 괜히
     */
    public int solution_problem1(int[] nums) {
        HashSet<Integer> kinds = new HashSet<>();

        for (int num : nums) {
            kinds.add(num);
        }

        int numTakes = nums.length / 2;

        return Math.min(numTakes, kinds.size());
    }

    public String solution_problem2(int a, int b) {
        String[] days = new String[]{"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int[] daysInMonth = new int[]{31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int nthDay = b;
        for (int i = 0; i < a - 1; ++i) {
            nthDay += daysInMonth[i];
        }

        return days[(5 + (nthDay - 1)) % 7];
    }

    public int[] solution_problem3(int[] numbers) {
        HashSet<Integer> sums = new HashSet<>();

        for (int first = 0; first < numbers.length; first++) {
            for (int second = first + 1; second < numbers.length; second++) {
                sums.add(numbers[first] + numbers[second]);
            }
        }

        int[] array = new int[sums.size()];

        int index = 0;
        for (Integer sum : sums) {
            array[index++] = sum;
        }

        Arrays.sort(array);

        return array;
    }

    public String[] solution_problem4(String[] strings, int n) {
        Arrays.sort(strings, (str0, str1) -> {
            char str0NthChar = str0.charAt(n);
            char str1NthChar = str0.charAt(n);

            if (str0NthChar == str1NthChar) {
                return str0.compareTo(str1);
            }

            return str0NthChar - str1NthChar;
        });

        return strings;
    }
}
