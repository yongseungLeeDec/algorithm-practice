package solution;

import java.util.HashMap;
import java.util.HashSet;
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

    public int solution(int[] elements) {
        HashSet<Integer> sums = new HashSet<>();
        int length = 1;
        int[] cache = new int[elements.length];
//        System.arraycopy(elements, 0, cache, 0, elements.length); <== This shouldn't be here.

        while (length <= elements.length) {
            for (int i = 0; i < elements.length; i++) {
                int tailIndex = (i + (length - 1)) % elements.length;

                cache[i] += elements[tailIndex];
                sums.add(cache[i]);
            }

            length++;
        }

        return sums.size();
    }
}
