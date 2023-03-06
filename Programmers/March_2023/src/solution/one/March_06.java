package solution.one;

public class March_06 {
    public static int solution(int n, int m, int[] section) {
        int rolling_count = 0;
        int painted = section[0] - 1;

        for (int sec_num : section) {
            if (sec_num > painted) {
                rolling_count++;
                painted = (sec_num - 1) + m;
            }
        }

        return rolling_count;
    }
}
