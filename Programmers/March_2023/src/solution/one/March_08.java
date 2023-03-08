package solution.one;

import java.util.ArrayList;

public class March_08 {
    public String[] solution(int[][] line) {
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;

        int rowNum = 0;

        ArrayList<Long[]> intersections = new ArrayList<>();

        for (int[] l : line) {
            long a = l[0];
            long b = l[1];
            long e = l[2];

            for (int i = rowNum + 1; i < line.length; i++) {
                long c = line[i][0];
                long d = line[i][1];
                long f = line[i][2];

                long divisor = a * d - b * c;

                if (divisor == 0) {
                    continue;
                }

                double y = (double) (e * c - a * f) / (double) divisor;
                double x = (double) (b * f - e * d) / (double) divisor;

                if (x % 1 == 0 && y % 1 == 0) {
                    intersections.add(new Long[]{(long) y, (long) x});

                    minY = (long) Math.min(y, minY);
                    maxY = (long) Math.max(y, maxY);
                    minX = (long) Math.min(x, minX);
                    maxX = (long) Math.max(x, maxX);
                }
            }

            rowNum++;
        }

        long width = maxX - minX + 1;
        long height = maxY - minY + 1;

        char[][] base = new char[(int) height][(int) width];

        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                base[r][c] = '.';
            }
        }

        for (Long[] is : intersections) {
            int translatedY = (int) (maxY - is[0]);
            int translatedX = (int) (is[1] - minX);
            base[translatedY][translatedX] = '*';
        }

        String[] answer = new String[(int) height];

        for (int r = 0; r < height; r++) {
            answer[r] = new String(base[r]);
        }

        return answer;
    }
}
