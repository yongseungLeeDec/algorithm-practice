package solution;

import java.util.HashSet;

public class Jan_30 {
    /*
        더 빠른 방법도 있으려나? 흠... 우선 오늘은 여기서 마무리!
        https://school.programmers.co.kr/learn/courses/30/lessons/132265
     */
    public int solution(int[] topping) {
        HashSet<Integer> tops = new HashSet<>();

        int[] onLeftToppingNums = new int[topping.length];
        int[] onRightToppingNums = new int[topping.length];

        for (int i = 0; i < topping.length; i++) {
            tops.add(topping[i]);
            onLeftToppingNums[i] = tops.size();
        }

        tops.clear();

        for (int j = topping.length - 1; j >= 0; j--) {
            tops.add(topping[j]);
            onRightToppingNums[j] = tops.size();
        }

        int count = 0;

        for (int index = 0; index < topping.length - 1; index++) {
            if (onLeftToppingNums[index] == onRightToppingNums[index + 1]) {
                count++;
            }
        }

        return count;
    }

    public int solution_other(int[] topping) {
        int answer = 0;
        int[] left = new int[10001];
        int[] right = new int[10001];
        int ls = 0;
        int rs = 0;

        for (var i : topping) {
            right[i]++;
        }

        for (var i : right) {
            rs += i > 0 ? 1 : 0; // 개수를 셈
        }

        for (var i : topping) {
            right[i]--;
            if (right[i] == 0) {
                rs--; // <-- 이게 핵심 로직이다..
            }
            if (left[i] == 0) ls++;
            left[i]++;
            if (rs == ls) answer++;
        }

        return answer;
    }
}
