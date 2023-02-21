package solution;

import java.util.ArrayList;

public class Feb_21 {
    public double[] solution(int k, int[][] ranges) {
        double[] areas = new double[ranges.length];

        ArrayList<Long> seq = getCollatzSequence(k);
        int endX = seq.size() - 1;

        int rangeNum = 0;

        for (int[] range : ranges) {
            int start = range[0];
            int end = endX + range[1];

            if (start > end) {
                areas[rangeNum] = -1;
            } else {
                areas[rangeNum] = calculateArea(seq, start, end);
            }

            rangeNum++;
        }

        return areas;
    }

    public double calculateArea(ArrayList<Long> seq, int start, int end) {
        double area = 0;

        int p1 = start;
        int p2 = start + 1;

        while (p2 < end) {
            double squareArea = Math.min(seq.get(p1), seq.get(p2));
            double triangleArea = (double) Math.abs(seq.get(p1) - seq.get(p2)) / 2;

            area += squareArea;
            area += triangleArea;

            p1++;
            p2++;
        }

        return area;
    }

    public ArrayList<Long> getCollatzSequence(long k) {
        ArrayList<Long> seq = new ArrayList<>();

        while (k != 1) {
            seq.add(k);
            if ((k & 1) == 0) {
                k = k >> 1;
            } else {
                k *= 3;
                k += 1;
            }
        }

        seq.add(k);

        return seq;
    }
}
