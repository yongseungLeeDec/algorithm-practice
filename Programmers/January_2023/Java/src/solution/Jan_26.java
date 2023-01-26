package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Jan_26 {
    public String solution(String number, int k) {
        int numLen = number.length();

        boolean[] isDiscarded = new boolean[numLen];
        int discardCount = 0;
        int prevIndex = -1;
        int index;

        for (int i = 0; i < numLen - 1; ++i) {
            char digit = number.charAt(i);
            char nextDigit = number.charAt(i + 1);

            if (digit > nextDigit) {
                index = prevIndex + 1;

                while (discardCount < k && index < i) {
                    if (number.charAt(index) == digit) {
                        break;
                    }

                    isDiscarded[index] = true;
                    index++;
                    discardCount++;
                }

                while (discardCount < k && prevIndex >= 0) {
                    if (!isDiscarded[prevIndex] && number.charAt(prevIndex) < digit) {
                        isDiscarded[prevIndex] = true;
                        discardCount++;
                    }
                    prevIndex--;
                }

                prevIndex = i;
            }
        }

        index = prevIndex + 1;

        while (discardCount < k && index < numLen - 1) {
            isDiscarded[index] = true;
            index++;
            discardCount++;
        }

        char digit = number.charAt(numLen - 1);
        int altIndex = numLen - 1;

        while (discardCount < k && prevIndex >= 0) {
            if (!isDiscarded[prevIndex]) {
                if (number.charAt(prevIndex) < digit) {
                    isDiscarded[prevIndex] = true;
                    discardCount++;
                } else {
                    isDiscarded[altIndex] = true;
                    discardCount++;
                    altIndex = prevIndex;
                    digit = number.charAt(prevIndex);
                }
            }
            prevIndex--;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numLen; i++) {
            if (!isDiscarded[i]) {
                sb.append(number.charAt(i));
            }
        }

        return sb.toString();
    }


    // 3 3312 3 -> 4개 버린다면? 3 3 3개 남은 걸 어디서 버릴 것인가?
    // 321129

    public int[] solution_p2(String s) {
        String sr = s.replace("{", "");
        sr = sr.replace("}", "");

        StringTokenizer tokenizer = new StringTokenizer(sr, ",");
        HashMap<Integer, Integer> fields = new HashMap<>();

        while (tokenizer.hasMoreTokens()) {
            Integer number = Integer.parseInt(tokenizer.nextToken());

            fields.putIfAbsent(number, 0);
            fields.put(number, fields.get(number) + 1);
        }

        int[] answer = new int[fields.size()];

        for (Integer field : fields.keySet()) {
            int occurrence = fields.get(field);
            answer[occurrence - 1] = field;
        }

        return answer;
    }

    private static final int[][] OFFSETS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[] solution(String[] maps) {
        int[][] map = getMap(maps);
        int height = map.length;
        int width = map[0].length;
        ArrayList<Integer> stays = new ArrayList<>();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int stay = calculateStay(map, y, x);

                if (stay != 0) {
                    stays.add(stay);
                }
            }
        }

        if (stays.isEmpty()) {
            return new int[]{-1};
        }

        int[] staysArray = new int[stays.size()];
        int index = 0;

        for (Integer stay : stays) {
            staysArray[index++] = stay;
        }

        Arrays.sort(staysArray);

        return staysArray;
    }

    private int calculateStay(int[][] map, int y, int x) {
        if (y < 0 || map.length <= y || x < 0 || map[0].length < x) {
            return 0;
        }

        int stay = map[y][x];

        if (stay == -1) {
            return 0;
        }

        map[y][x] = -1;

        for (int[] offset : OFFSETS) {
            int altY = y + offset[0];
            int altX = x + offset[1];
            stay += calculateStay(map, altY, altX);
        }

        return stay;
    }

    private int[][] getMap(String[] maps) {
        int height = maps.length;
        int width = maps[0].length();
        int[][] map = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (maps[y].charAt(x) == 'X') {
                    map[y][x] = -1;
                } else {
                    map[y][x] = maps[y].charAt(x) - '0';
                }
            }
        }

        return map;
    }
}
