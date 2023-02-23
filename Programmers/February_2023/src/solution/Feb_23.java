package solution;

import java.util.PriorityQueue;

public class Feb_23 {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int r = 0; r < enemy.length; r++) {
            queue.add(enemy[r]);

            if (queue.size() > k) {
                n -= queue.poll();
            }

            if (n < 0) {
                return r;
            }
        }

        return enemy.length;
    }
}
