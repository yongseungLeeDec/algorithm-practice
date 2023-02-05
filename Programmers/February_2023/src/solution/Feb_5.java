package solution;

public class Feb_5 {
    public int solution(int[][] lines) {
        int[] cache = new int[201];

        for (int[] line : lines) {
            int start = line[0] + 100;
            int end = line[1] + 100;

            for (int i = start; i < end; i++) {
                cache[i] += 1;
            }
        }

        int count = 0;

        for (int i : cache) {
            if (i >= 2) {
                count++;
            }
        }

        return count;
    }


}
