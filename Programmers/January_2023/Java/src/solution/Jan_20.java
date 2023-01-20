package solution;

public class Jan_20 {
    /*
      타겟 넘버
      https://school.programmers.co.kr/learn/courses/30/lessons/43165
      DFS는 늘 헷갈리네 ㅎㅎㅎ
      얘도 다른 사람 풀이 보면서 정리!!
     */
    public int solution(int[] numbers, int target) {
        return getPossibleCombinationCounts(numbers, 0, target, 0);
    }

    private int getPossibleCombinationCounts(int[] numbers, int index, int target, int result) {
        if (index == numbers.length) {
            if (result == target) {
                return 1;
            }
            return 0;
        }

        int count = 0;

        count += getPossibleCombinationCounts(numbers, index + 1, target, result + numbers[index]);
        count += getPossibleCombinationCounts(numbers, index + 1, target, result - numbers[index]);

        return count;
    }
}
