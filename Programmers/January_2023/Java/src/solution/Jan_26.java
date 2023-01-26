package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Jan_26 {
    public String solution(String number, int k) {
        String answer = "";
        return answer;
    }

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
