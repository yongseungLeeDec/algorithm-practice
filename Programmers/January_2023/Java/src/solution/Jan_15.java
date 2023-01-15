package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Jan_15 {
    public String solution_1(String s) {
        char[] charArray = s.toCharArray();
        Character[] characterArray = new Character[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            characterArray[i] = charArray[i];
        }

        Arrays.sort(characterArray, (c0, c1) -> c1 - c0);

        StringBuilder sb = new StringBuilder();
        for (Character ch : characterArray) {
            sb.append(ch);
        }

        return sb.toString();
    }

    public String solution_1_more_use_of_java_api(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);
        StringBuilder sb = new StringBuilder(new String(charArray));
        sb.reverse();

        return sb.toString();
    }

    public String solution_2(int n) {
        StringBuilder sb = new StringBuilder();
        String[] subak = new String[]{"수", "박"};

        int count = 0;
        int offset = 0;

        while (count < n) {
            sb.append(subak[offset]);
            count++;
            offset ^= 1;
        }

        return sb.toString();
    }

    public String solution_3(String s) {
        int beginIndex = (s.length() - 1) / 2;
        int endIndex = beginIndex + 1;

        if (s.length() % 2 == 0) {
            endIndex += 1;
        }

        return s.substring(beginIndex, endIndex);
    }

    /*
        이 문제는 다른 사람 풀이 한 번 보기
    */
    public int[] solution_4(int[] arr) {
        if (arr.length == 1) {
            return new int[]{-1};
        }

        int minimalElementIndex = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < arr[minimalElementIndex]) {
                minimalElementIndex = i;
            }
        }

        int[] newArr = new int[arr.length - 1];

        int j;

        for (j = 0; j < minimalElementIndex; j++) {
            newArr[j] = arr[j];
        }

        j++;

        for (; j < arr.length; j++) {
            newArr[j - 1] = arr[j];
        }

        return newArr;
    }

    /*
    항상 StringBuilder가 훨씬 빠르구만...
     */
    public String solution_5(String phone_number) {
        int replacementEndIndex = phone_number.length() - 4;
        StringBuilder sb = new StringBuilder();

        int count = 0;

        while (count < replacementEndIndex) {
            sb.append("*");
            count++;
        }

        sb.append(phone_number.substring(replacementEndIndex));

        return sb.toString();
    }

    /*
    스트림을 써볼카
     */
    public int[] solution_6_stream(int[] arr, int divisor) {
        final int div = divisor;

        int[] answer = Arrays.stream(arr)
                .filter(e -> (e % div) == 0)
                .sorted()
                .toArray();

        if (answer.length == 0) {
            return new int[]{-1};
        }

        return answer;
    }

    public int[] solution_6_no_stream(int[] arr, int divisor) {
        ArrayList<Integer> elements = new ArrayList<>(arr.length);

        for (int n : arr) {
            if (n % divisor == 0) {
                elements.add(n);
            }
        }

        if (elements.size() == 0) {
            return new int[]{-1};
        }

        Collections.sort(elements);

        int[] answer = new int[elements.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = elements.get(i);
        }

        return answer;
    }

    /*
            int x = Arrays.asList(seoul).indexOf("Kim");
     */

    public String solution_7(String[] seoul) {
        int i;

        for (i = 0; i < seoul.length; i++) {
            if (seoul[i].equals("Kim")) {
                break;
            }
        }

        return String.format("김서방은 %d에 있다", i);
    }

    /*
    수를 곱하고 더하고 지지고 볶고 할 때에는 언제나 오버플로를 조심하자.

     */
    public int solution_8(int num) {
        long n = num;
        int repeatCount = 0;

        while (n != 1 && repeatCount <= 500) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n *= 3;
                n += 1;
            }

            repeatCount++;
        }

        if (n == 1) {
            return repeatCount;
        }

        return -1;
    }

    public long solution_9(int a, int b) {
        long max = Math.max(a, b);
        long min = (max == a) ? b : a;

        long numNumbers = max - min + 1;
        long sum = numNumbers * (numNumbers + 1) / 2;
        sum += (min - 1) * numNumbers;

        return sum;
    }

    /*
    얘도 복습 필요 --> 정수 자료형을 주의깊게 쓰지 않아서 생긴 문제. n에다가 캐스팅을 하는 게 아니라 n % 10에다가 캐스팅을 했어야지 바보야 ㅡㅡ
     */
    public long solution_10(long n) {
        ArrayList<Long> digits = new ArrayList<>(20);

        while (n > 0) {
            digits.add(n % 10);
            n /= 10;
        }

        Collections.sort(digits);

        long number = 0;
        int nThDigit = 1;
        int numDigits = digits.size();

        while (nThDigit <= numDigits) {
            long digitValue = digits.get(nThDigit - 1);
            number += (Math.pow(10, nThDigit - 1) * digitValue);
            nThDigit++;
        }

        return number; // 무엇이 틀렸을까~~
    }

    public boolean solution_11(int x) {
        int sumDigits = 0;
        int num = x;

        while (num > 0) {
            sumDigits += (num % 10);
            num /= 10;
        }

        return x % sumDigits == 0;
    }
}
