package solution;

import java.lang.reflect.Array;
import java.util.*;

public class Jan_26 {
    public String solution(String number, int k) {
        String answer = "";
        return answer;
    }

    public int[] solution(String s) {
        String sr = s.replace("{", "");
        sr = sr.replace("}", "");
        StringTokenizer tokenizer = new StringTokenizer(sr, ",");
        HashMap<Integer, Integer> fields = new HashMap<>();

        while (tokenizer.hasMoreTokens()) {
            Integer number = Integer.parseInt(tokenizer.nextToken());
            if (fields.get(number) != null) {
                fields.put(number, 0);
            }
            fields.put(number, fields.get(number) + 1);
        }

        Integer[] answer = new Integer[fields.size()];
        int index = 0;

        for (Integer field : fields.keySet()) {
            answer[index++] = field;
        }

        Arrays.sort(answer, (o1, o2) -> fields.get(o2) - fields.get(o1));

        int[] realAnswer = new int[fields.size()];

        index = 0;

        for (Integer field : answer) {
            realAnswer[index++] = field;
        }

        return realAnswer;
    }
}
