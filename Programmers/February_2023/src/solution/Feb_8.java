package solution;

import java.util.ArrayList;

public class Feb_8 {
    public int[][] solution_descriptive(int n) {
        ArrayList<Integer> disks = new ArrayList<>();
        ArrayList<Integer> poles = new ArrayList<>();

        hanoi(1, 2, 3, n, disks, poles);

        int[][] answer = new int[disks.size()][2];

        for (int i = 0; i < answer.length; i++) {
            answer[i][0] = disks.get(i);
            answer[i][1] = poles.get(i);
        }

        return answer;
    }

    private void hanoi(int from, int inter, int to, int level, ArrayList<Integer> disks, ArrayList<Integer> poles) {
        if (level == 0) {
            return;
        }

        hanoi(from, to, inter, level - 1, disks, poles);
        disks.add(level);
        poles.add(to);
        hanoi(inter, from, to, level - 1, disks, poles);
    }

    public int[][] solution(int n) {
        int[] counts = new int[15];

        counts[0] = 1;

        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i - 1] * 2 + 1;
        }

        int[][] answer = new int[counts[n - 1]][2];

        hanoi(1, 2, 3, n, answer);

        return answer;
    }

    private static int COUNT = 0;

    private void hanoi(int from, int via, int to, int n, int[][] order) {
        if (n == 0) {
            return;
        }

        hanoi(from, to, via, n - 1, order);
        order[COUNT][0] = from;
        order[COUNT][1] = to;
        COUNT++;
        hanoi(via, from, to, n - 1, order);
    }
}
