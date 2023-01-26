package solution;

public class Jan_27 {
    /*
    https://school.programmers.co.kr/learn/courses/30/lessons/42883
     */
    public String solution_try1(String number, int k) {
        int discardCount = 0;
        int discardLimit = k;

        while (discardCount < k) {
            int prevLength = number.length();
            number = getShortenedNumber(number, discardLimit);
            int discarded = prevLength - number.length();

            if (discarded == 0) {
                number = number.substring(0, number.length() - discardLimit);
                break;
            }

            discardCount += discarded;
            discardLimit -= discarded;
        }

        return number;
    }

    public String getShortenedNumber(String number, int discardLimit) {
        int numLen = number.length();
        boolean[] isDiscarded = new boolean[numLen];

        number = number + ".";

        int prevIndex = -1;
        int discardCount = 0;

        for (int index = 0; index < numLen; index++) {
            char digit = number.charAt(index);
            char nextDigit = number.charAt(index + 1);

            if (digit > nextDigit) {
                int iterateIndex = prevIndex + 1;
                while (discardCount < discardLimit && number.charAt(iterateIndex) < digit) {
                    if (prevIndex != -1 && digit > number.charAt(prevIndex)) {
                        isDiscarded[prevIndex] = true;
                        discardCount++;
                        continue;
                    }
                    isDiscarded[iterateIndex] = true;
                    iterateIndex++;
                    discardCount++;
                }
                prevIndex = index;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numLen; i++) {
            if (!isDiscarded[i]) {
                sb.append(number.charAt(i));
            }
        }

        return sb.toString();
    }
}
