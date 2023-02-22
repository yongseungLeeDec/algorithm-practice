package solution;

public class Feb_22 {
    public String[] solution(String[] quiz) {
        String[] answer = new String[quiz.length];

        int index = 0;

        for (String exp : quiz) {
            String[] split = exp.split(" ");
            int op1 = Integer.parseInt(split[0]);
            String operator = split[1];
            int op2 = Integer.parseInt(split[2]);
            int res = Integer.parseInt(split[4]);

            switch (operator) {
                case "+":
                    answer[index] = (op1 + op2 == res) ? "O" : "X";
                    break;
                case "-":
                    answer[index] = (op1 - op2 == res) ? "O" : "X";
                    break;
                default:
                    break;
            }

            index++;
        }

        return answer;
    }
}
