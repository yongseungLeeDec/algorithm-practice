package solution;

public class Jan_25 {
    public int solution(String s) {
        int sLength = s.length();
        int count = 0;

        for (int i = 0; i < sLength; ++i) {
            if (isValidParenthesesSet(i, s)) {
                count++;
            }
        }

        return count;
    }

    private boolean isValidParenthesesSet(int startIndex, String s) {
        int sLength = s.length();
        int visitedCharCount = 0;
        int index = startIndex;
        char[] stack = new char[sLength];
        int top = 0;

        while (visitedCharCount < sLength) {
            char c = s.charAt(index);

            if (c == '(' || c == '{' || c == '[') {
                stack[top] = c;
                top++;
            } else {
                if (top != 0 && ((c == ')' && stack[top - 1] == '(') || (c == '}' && stack[top - 1] == '{') || (c == ']' && stack[top - 1] == '['))) {
                    top--;
                } else {
                    return false;
                }
            }

            index = (index + 1) % sLength;
            visitedCharCount++;
        }

        return top == 0;
    }
}
