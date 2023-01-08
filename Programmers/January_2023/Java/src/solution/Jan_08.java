package solution;

public class Jan_08 {
    public String solution_problem1(int[] numbers, String hand) {
        StringBuilder answerBuilder = new StringBuilder();
        int lThumbAt = 10;
        int rThumbAt = 12;

        for (int num : numbers) {
            if (num == 0) {
                num = 11;
            }

            int numRow = (num - 1) / 3;
            int numCol = (num % 3 + 2) % 3;

            if (numCol == 0) {
                answerBuilder.append("L");
                lThumbAt = num;
                continue;
            }

            if (numCol == 2) {
                answerBuilder.append("R");
                rThumbAt = num;
                continue;
            }

            int lThumbDistance = Math.abs((lThumbAt - 1) / 3 - numRow) + Math.abs(((lThumbAt % 3 + 2) % 3) - numCol);
            int rThumbDistance = Math.abs((rThumbAt - 1) / 3 - numRow) + Math.abs(((rThumbAt % 3 + 2) % 3) - numCol);

            if (rThumbDistance < lThumbDistance || ((rThumbDistance == lThumbDistance) && hand.equals("right"))) {
                answerBuilder.append("R");
                rThumbAt = num;
                continue;
            }

            answerBuilder.append("L");
            lThumbAt = num;
        }

        return answerBuilder.toString();
    }
}
