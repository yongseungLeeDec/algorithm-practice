package solution;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Feb_15 {
    public static int solution(String name) {
        int nameLength = name.length();
        int verticalOperationCount = getVerticalOperationCount(name);

        HashMap<Integer, Integer> seqInfo = getSequenceInfo(name);

        int horizontalOperationCount = nameLength - 1;

        for (Integer seqStart : seqInfo.keySet()) {
            Integer seqEnd = seqInfo.get(seqStart);

            int l = seqStart == 0 ? 0 : seqStart - 1; // 첫 번째 글자가 0인 경우는 코너 케이스라서, 잘 처리하지 않으면 Math.Min이 의도와 다르게 동작
            int r = nameLength - seqEnd;

            int requiredHorizontalMove = Math.min(2 * l + r, l + 2 * r); // 요게 핵심 로직임

            if (horizontalOperationCount > requiredHorizontalMove) {
                horizontalOperationCount = requiredHorizontalMove;
            }
        }

        return verticalOperationCount + horizontalOperationCount;
    }

    private static HashMap<Integer, Integer> getSequenceInfo(String name) {
        HashMap<Integer, Integer> seqInfo = new LinkedHashMap<>();
        int nameLength = name.length();

        boolean isSeqStarted = false;
        int seqStart = 0;

        for (int i = 0; i < nameLength; i++) {
            char c = name.charAt(i);

            if (isSeqStarted) {
                if (c != 'A') {
                    seqInfo.put(seqStart, i);
                    isSeqStarted = false;
                }
            } else {
                if (c == 'A') {
                    isSeqStarted = true;
                    seqStart = i;
                }
            }
        }

        if (isSeqStarted) {
            seqInfo.put(seqStart, nameLength);
        }

        return seqInfo;
    }

    private static int getVerticalOperationCount(String name) {
        int nameLength = name.length();
        int verticalOperationCount = 0;

        for (int i = 0; i < nameLength; ++i) {
            char c = name.charAt(i);
            if (c - 'A' < 13) {
                verticalOperationCount += c - 'A';
            } else {
                verticalOperationCount += 'Z' - c + 1;
            }
        }

        return verticalOperationCount;
    }
}
