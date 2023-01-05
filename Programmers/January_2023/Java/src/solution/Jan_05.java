package solution;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class Jan_05 {
    /*
    문제 이름: 콜라문제
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/132267
    --> 풀이시간, 11분 정도
     */
    public int solution_problem1(int a, int b, int n) {
        int accumulated = 0;

        while (true) {
            int numNewColas = (n / a) * b;
            accumulated += numNewColas;
            if (numNewColas == 0) {
                break;
            }

            int leftEmptyColas = n % a;
            n = numNewColas + leftEmptyColas;
        }

        return accumulated;
    }

    /*
    문제 이름: 삼총사
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/131705
     */
    public int solution_problem2(int[] number) {
        int trioCount = 0;

        for (int first = 0; first < number.length - 2; ++first) {
            for (int second = first + 1; second < number.length - 1; ++second) {
                for (int third = second + 1; third < number.length; ++third) {
                    if (number[first] + number[second] + number[third] == 0) {
                        trioCount++;
                    }
                }
            }
        }

        return trioCount;
    }

    /*
   문제 이름: 숫자 짝꿍
   문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/131128
   */
    public String solution_problem3(String X, String Y) {
        StringBuilder pairBuilder = new StringBuilder();

        char[] x = X.toCharArray();
        char[] y = Y.toCharArray();

        Arrays.sort(x);
        Arrays.sort(y);

        int xPointer = x.length - 1;
        int yPointer = y.length - 1;

        while (xPointer >= 0 && yPointer >= 0) {
            char xDigit = x[xPointer];
            char yDigit = y[yPointer];

            if (xDigit == yDigit) {
                pairBuilder.append(xDigit);
                xPointer--;
                yPointer--;
                continue;
            }

            if (xDigit < yDigit) {
                while (yPointer >= 0 && y[yPointer] > xDigit) {
                    yPointer--;
                }
                continue;
            }

            while (xPointer >= 0 && x[xPointer] > yDigit) {
                xPointer--;
            }
        }

        String pair = pairBuilder.toString();

        if (pair.equals("")) {
            return "-1";
        }

        if (pair.charAt(0) == '0') {
            return "0";
        }

        return pair;
    }

    /*
    문제 이름: 신고 결과 받기
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/92334?language=java
    ---> 더 빠른 방법은 무엇이 있으려나...
     */
    public int[] solution_problem4(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        HashMap<String, HashSet<String>> usersReportedBy = new HashMap<>();
        HashMap<String, Integer> acceptedReportCounts = new LinkedHashMap<>();

        for (String id : id_list) {
            acceptedReportCounts.put(id, 0);
        }

        for (String r : report) {
            String[] tokens = r.split(" ");

            String reporting = tokens[0];
            String reported = tokens[1];

            if (usersReportedBy.get(reported) == null) {
                usersReportedBy.put(reported, new HashSet<>());
            }
            usersReportedBy.get(reported).add(reporting);
        }

        for (String reportedUser : usersReportedBy.keySet()) {
            HashSet<String> users = usersReportedBy.get(reportedUser);

            if (users.size() >= k) {
                for (String user : users) {
                    acceptedReportCounts.put(user, acceptedReportCounts.get(user) + 1);
                }
            }
        }

        int index = 0;
        for (String user : acceptedReportCounts.keySet()) {
            answer[index] = acceptedReportCounts.get(user);
            index++;
        }

        return answer;
    }

    /*
    문제 이름: 나머지가 1이 되는 수 찾기
    문제 링크: https://school.programmers.co.kr/learn/courses/30/lessons/87389
     */
    public int solution_problem5(int n) {
        int smallestDivisor = 2;

        for (int i = 2; i <= n - 1; ++i) {
            if (n % i == 1) {
                smallestDivisor = i;
                break;
            }
        }

        return smallestDivisor;
    }
}
