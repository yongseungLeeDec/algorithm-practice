package solution;

public class Feb_18 {
    public int solution(String[][] book_time) {
        int[] times = new int[24 * 60 + 10];

        for (String[] bt : book_time) {
            int startTimeValue = calculateTimeValue(bt[0]);
            int endTimeValue = calculateTimeValue(bt[1]) + 10;

            times[startTimeValue] += 1;
            times[endTimeValue] -= 1;
        }

        int max = 0;

        for (int i = 1; i < times.length; i++) {
            times[i] += times[i - 1];
            max = Math.max(times[i], max);
        }

        return max;
    }

    private int calculateTimeValue(String timeString) {
        int hour = Integer.parseInt(timeString.substring(0, 2));
        int mint = Integer.parseInt(timeString.substring(3, 5));

        return hour * 60 + mint;
    }
}
