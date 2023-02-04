package solution;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Feb_4 {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> mapBySize = new HashMap<>();

        for (int size : tangerine) {
            mapBySize.putIfAbsent(size, 0);
            mapBySize.put(size, mapBySize.get(size) + 1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((count1, count2) -> count2 - count1);

        for (int size : mapBySize.keySet()) {
            maxHeap.add(mapBySize.get(size));
        }

        int kindCount = 0;

        while (k > 0) {
            k -= maxHeap.poll();
            kindCount++;
        }

        return kindCount;
    }
}
