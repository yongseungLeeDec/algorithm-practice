package solution;

import java.util.ArrayList;

public class Jan_28 {
    /*
     아마도 캐스팅 문제 -_- factorial은 늘 long으로 다룬다고 생각할 것
     시간복잡도는 아마도 O(n) ?
     */
    public int[] solution(int n, long k) {
        long[] factorials = getFactorials(n - 1);
        ArrayList<Integer> numbers = getNumbersInList(n);
        ArrayList<Integer> kThTuple = new ArrayList<>(n);

        int treeLevel = n - 1;
        long inGroupOrder = k - 1;
        int groupNum;

        while (kThTuple.size() < n - 1) {
            groupNum = (int) (inGroupOrder / factorials[treeLevel - 1]);
            inGroupOrder = inGroupOrder % factorials[treeLevel - 1];

            int picked = numbers.get(groupNum);
            kThTuple.add(picked);
            numbers.remove(groupNum);

            treeLevel--;
        }

        kThTuple.add(numbers.get(0));

        int[] answer = new int[n];
        int index = 0;

        for (Integer num : kThTuple) {
            answer[index++] = num;
        }

        // -_- Stream 으로 하니까 시간초과 남 어이없음.. 처음부터 배열에 담아도 되긴 함.
        // 그래도 코드 알아는 두기
        // int[] answer = kThTuple.stream().mapToInt(Integer::intValue).toArray();

        return answer;
    }

    private static long[] getFactorials(int n) {
        long[] factorials = new long[n];
        long base = 1;

        for (int i = 0; i < factorials.length; i++) {
            base *= (i + 1);
            factorials[i] = base;
        }

        return factorials;
    }

    private static ArrayList<Integer> getNumbersInList(int n) {
        ArrayList<Integer> numbers = new ArrayList<>(n);

        int count = 0;
        int element = 1;
        while (count < n) {
            numbers.add(element);
            element++;
            count++;
        }

        return numbers;
    }
}
