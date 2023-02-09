package solution;

public class Feb_9 {
    public long solution(int k, int d) {
        long[] values = getCoordinateValues(k, d);
        long count = 0;

        for (long x : values) {
            count += getNumberOfPairYs(x, values, d);
        }

        return count;
    }

    private int getNumberOfPairYs(long x, long[] possibleYs, long distanceLimit) {
        int sta = 0;
        int end = possibleYs.length - 1;
        int mid;
        int indexMaxY = 0;
        long maxY = 0;

        while (sta <= end) {
            mid = (sta + end) / 2;

            long y = possibleYs[mid];

            double distance = calculateDistanceFromCenter(x, y);

            if (distance <= distanceLimit) {
                sta = mid + 1;

                if (y > maxY) { // <-- 이해해야 함...
                    maxY = y;
                    indexMaxY = mid;
                }

            } else {
                end = mid - 1;
            }
        }

        return indexMaxY + 1;
    }

    private long[] getCoordinateValues(int k, int d) {
        long[] values = new long[d / k + 1];

        for (int i = 1; i < values.length; i++) {
            values[i] = (long) k * i;
        }

        return values;
    }

    private double calculateDistanceFromCenter(long x, long y) {
        return Math.sqrt(x * x + y * y);
    }
}
