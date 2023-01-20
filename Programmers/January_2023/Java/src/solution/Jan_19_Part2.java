package solution;

import java.util.Arrays;

public class Jan_19_Part2 {
    /*
        드디어 풀었다 얘들아 체육복 좀 잃어버리지마..
        복습 필수!!
        https://school.programmers.co.kr/learn/courses/30/lessons/42862#
     */
    public int solution_p1(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        Arrays.sort(reserve);

        int count = n - lost.length;
        int l = 0;
        int r = 0;

        while (l < lost.length && r < reserve.length) {
            if (reserve[r] == lost[l]) {
                count++;
                l++;
                r++;
                continue;
            }

            if (reserve[r] - lost[l] == -1) {
                if (r == lost.length - 1 || reserve[r + 1] != lost[l]) {
                    count++;
                    l++;
                }
                r++;
                continue;
            }

            if (reserve[r] - lost[l] == 1) {
                if (l == lost.length - 1 || reserve[r] != lost[l + 1]) {
                    count++;
                    r++;
                }
                l++;
                continue;
            }

            if (reserve[r] > lost[l]) {
                l++;
            } else {
                r++;
            }
        }

        return count;
    }
}
