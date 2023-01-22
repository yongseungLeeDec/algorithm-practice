package solution;

public class Jan_22 {
    public int solution_p1(int n, int a, int b) {
        int matchCount = 0;

        while (a != b) {
            a = ((a - 1) / 2) + 1;
            b = ((b - 1) / 2) + 1;
            matchCount++;
        }

        return matchCount;
    }
}
