package solution;

public class Feb_02 {
    public long solution(int w, int h) {
        long numSquares = 0;
        double slope = (double) h / (double) w * -1;

        for (int x = 1; x <= w; x++) {
            numSquares += (int) (slope * x + w);
        }

        return numSquares * 2;
    }
}
