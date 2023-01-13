package solution;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Jan_13 {
    public int solution_1(int n) {
        int divisorSum = 0;
        int sqrt = (int) Math.sqrt(n);

        for (int div = 1; div <= sqrt; ++div) {
            if (n % div == 0) {
                divisorSum += div;
                divisorSum += (n / div);
            }
        }

        if (Math.pow(sqrt, 2) == n) {
            divisorSum -= sqrt;
        }

        return divisorSum;
    }

    /*
    형변환 주의...
     */
    public int[] solution_2(long n) {
        ArrayList<Integer> digits = new ArrayList<>(20);

        while (n > 0) {
            digits.add((int) (n % 10));
            n /= 10;
        }

        int[] array = new int[digits.size()];
        int index = 0;

        for (Integer digit : digits) {
            array[index] = digit;
            index++;
        }

        return array;
    }

    public long solution_3(long n) {
        long sqrt = (long) Math.sqrt(n);

        if (Math.pow(sqrt, 2) == n) {
            return (long) Math.pow(sqrt + 1, 2);
        }

        return -1;
    }

    boolean solution_4(String s) {
        int pCount = 0;
        int yCount = 0;
        int sLength = s.length();

        for (int i = 0; i < sLength; ++i) {
            char c = s.charAt(i);

            if (c == 'p' || c == 'P') {
                pCount++;
                continue;
            }

            if (c == 'y' || c == 'Y') {
                yCount++;
            }
        }

        return pCount == yCount;
    }

    /*
    보기엔 궤멋지긴 한데 그냥 for 문 돌리는게 훨씬 빠르네.. 왜일까? 새로운 개체를 생성하기 때문일까.
     */
    boolean solution_4_by_stream(String s) {
        return s.chars().filter(c -> 'P' == c || 'p' == c).count()
                == s.chars().filter(c -> 'Y' == c || 'y' == c).count();
    }

    public long[] solution_5(int x, int n) {
        long[] multiples = new long[n];
        int index = 0;
        long base = 0;

        while (index < n) {
            multiples[index] = base + x;
            base = multiples[index];
            index++;
        }

        return multiples;
    }

    public int solution_6(String s) {
        return Integer.parseInt(s);
    }

    public static void solution_7(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        StringBuilder sb = new StringBuilder((n + 1) * m);

        int rowCount = 0;

        while (rowCount < m) {
            sb.append("*".repeat(Math.max(0, n)));
            sb.append(System.lineSeparator());
            rowCount++;
        }

        System.out.println(sb);
    }

    /*
        Stream 을 이용하는 방법이 여기 또 있네
     */
    public static void solution_7_using_stream(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        IntStream.range(0, a).forEach(s -> sb.append("*"));
        IntStream.range(0, b).forEach(s -> System.out.println(sb));
    }

    /*
      쓸데없이 헤맸다.. 공백문자를 간과함 ㅠㅠ 문제 좀 제대로 읽고 시건방떨지 마라
      Character.isUpperCase, isLowerCase 쓸 것
      a~z, A~z를 아예 0~25의 범위로 끌어내린 뒤 나머지 연산 적용하고 a나 A를 더하는 것도 방법이다.. ㅠㅠ 그게 실수하지 않기 쉽겠다
      즉 0으로 시작하지 않는 링큐를 다룰 때에는 0으로 가져온 다음에 시작하는 오프셋만큼 나중에 더해주는 게
      실수를 줄이는 방법일 것 같다.
     */
    public String solution_8(String s, int n) {
        int sLen = s.length();
        StringBuilder sb = new StringBuilder(sLen);

        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);

            if ('A' <= c && c <= 'Z') {
                c = (char) (c + n);
                if (c > 'Z') {
                    c = (char) ((c % 'Z') + 'A' - 1);
                }
            } else if ('a' <= c && c <= 'z') {
                c = (char) (c + n);
                if (c > 'z') {
                    c = (char) ((c % 'z') + 'a' - 1);
                }
            }

            sb.append(c);
        }

        return sb.toString();
    }

    public String solution_8_better(String s, int n) {
        int sLen = s.length();
        StringBuilder sb = new StringBuilder(sLen);

        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);

            if (Character.isUpperCase(c)) {
                c = (char) (((c - 'A' + n) % 26) + 'A');
            } else if (Character.isLowerCase(c)) {
                c = (char) (((c - 'a' + n) % 26) + 'z');
            }

            sb.append(c);
        }

        return sb.toString();
    }
}
