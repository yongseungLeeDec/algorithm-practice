package solution;

import java.util.ArrayList;

public class Jan_24 {
    public int solution(int cacheSize, String[] cities) {
        ArrayList<String> cache = new ArrayList<>(cacheSize);
        int cacheHitCount = 0;
        int cacheMissCount = 0;

        for (String city : cities) {
            String cityLowerCase = city.toLowerCase();
            int index = getIndex(cache, cityLowerCase);

            if (index != -1) {
                cacheHitCount++;
                cache.remove(index);
                cache.add(cityLowerCase);
                continue;
            }

            cacheMissCount++;

            if (cacheSize != 0) { // caching 을 안 하는 경우를 고려할 것
                if (cache.size() == cacheSize) {
                    cache.remove(0);
                }
                cache.add(cityLowerCase);
            }
        }

        return cacheHitCount + cacheMissCount * 5;
    }

    public int getIndex(ArrayList<String> cache, String city) {
        return cache.lastIndexOf(city);
    }
}
