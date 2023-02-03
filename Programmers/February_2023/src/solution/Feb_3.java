package solution;

public class Feb_3 {
    public int solution(int[] order) {
        int orderIndex = 0;
        int onBelt = 1;
        int[] stack = new int[order.length];
        int top = 0;
        int count = 0;

        while (onBelt <= order.length) {
            if (order[orderIndex] == onBelt) {
                count++;
                orderIndex++;
                onBelt++;
                continue;
            }

            if (top != 0 && stack[top - 1] == order[orderIndex]) {
                count++;
                orderIndex++;
                top--;
                continue;
            }

            stack[top] = onBelt;
            top++;
            onBelt++;
        }

        while (top > 0) {
            if (stack[top - 1] != order[orderIndex]) {
                break;
            }
            top--;
            orderIndex++;
            count++;
        }

        return count;
    }
}
