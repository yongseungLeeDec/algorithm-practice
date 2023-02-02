package solution;

import java.util.HashMap;

public class Feb_2 {
    public long solution(int w, int h) {
        long numSquares = 0;
        double slope = (double) h / (double) w * -1;

        for (int x = 1; x <= w; x++) {
            numSquares += (int) (slope * x + w);
        }

        return numSquares * 2;
    }


    public int solution(String str1, String str2) {
        HashMap<String, Integer> str1Set = getStringSet(str1);
        HashMap<String, Integer> str2Set = getStringSet(str2);

        int interSize = 0;
        int unionSize = 0;

        for (String key : str2Set.keySet()) {
            int elCountStr1 = (str1Set.get(key) == null) ? 0 : str1Set.get(key);
            interSize += Math.min(elCountStr1, str2Set.get(key));
            unionSize += Math.max(elCountStr1, str2Set.get(key));
            if (elCountStr1 != 0) {
                str1Set.remove(key);
            }
        }

        for (String leftoverKey : str1Set.keySet()) {
            unionSize += str1Set.get(leftoverKey);
        }

        if (interSize == 0 || unionSize == 0) {
            return 65536;
        }

        return (int) (((double) interSize / (double) unionSize) * 65536);
    }

    private HashMap<String, Integer> getStringSet(String str) {
        HashMap<String, Integer> set = new HashMap<>();
        int strLen = str.length();

        for (int i = 0; i < strLen - 1; ++i) {
            if (Character.isAlphabetic(str.charAt(i)) && Character.isAlphabetic(str.charAt(i + 1))) {
                String substr = str.substring(i, i + 2).toLowerCase();
                set.putIfAbsent(substr, 0);
                set.put(substr, set.get(substr) + 1);
            }
        }

        return set;
    }
}
