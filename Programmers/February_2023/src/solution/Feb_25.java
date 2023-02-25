package solution;

import java.util.HashMap;

public class Feb_25 {
    public static void main(String[] args) {
        solution(new int[]{100, 180, 360, 100, 270});
    }

    public static long solution(int[] weights) {
        long pairCount = 0;

        HashMap<Integer, Long> weightDistribution = new HashMap<>();

        for (int w : weights) {
            weightDistribution.putIfAbsent(w, 0L);
            weightDistribution.put(w, weightDistribution.get(w) + 1);
        }

        long[] multiples = new long[4000 + 1];

        for (int weight : weights) {
            multiples[weight * 2] += 1;
            multiples[weight * 3] += 1;
            multiples[weight * 4] += 1;
        }

        for (Integer w : weightDistribution.keySet()) {
            long base = weightDistribution.get(w);

            if (base >= 2) {
                pairCount += base * (base - 1) / 2;
            }

            multiples[w * 2] -= base;
            pairCount += (multiples[w * 2]) * base;
            multiples[w * 3] -= base;
            pairCount += (multiples[w * 3]) * base;
            multiples[w * 4] -= base;
            pairCount += (multiples[w * 4]) * base;
        }

        return pairCount;
    }
}
