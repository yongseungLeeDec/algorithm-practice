package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Jan_29 {
    // 어디서 루프가 생기지?
    public static void main(String[] args) {
        int[][] road = new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}};
        System.out.println(solution(6, road, 4));
    }

    public static int solution(int N, int[][] road, int K) {
        HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> map = getMap(road);
        HashSet<Integer> traversedTowns = new HashSet<>();
        HashSet<Integer> reachableTowns = new HashSet<>();

        travelMap(1, 0, K, traversedTowns, map, reachableTowns);

        return reachableTowns.size();
    }

    private static void travelMap(Integer currentTown,
                           int takenHours,
                           int K,
                           HashSet<Integer> traversedTowns,
                           HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> map,
                           HashSet<Integer> reachableTowns) {

        if (takenHours > K) {
            return;
        }

        reachableTowns.add(currentTown);
        traversedTowns.add(currentTown);

        HashMap<Integer, ArrayList<Integer>> connectedTowns = map.get(currentTown);
        for (Integer town : connectedTowns.keySet()) {
            ArrayList<Integer> roads = connectedTowns.get(town);

            for (Integer road : roads) {
                travelMap(town, takenHours + road, K, traversedTowns, map, reachableTowns);
            }
        }

        traversedTowns.remove(currentTown);
    }

    private static HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> getMap(int[][] road) {
        HashMap<Integer, HashMap<Integer, ArrayList<Integer>>> map = new HashMap<>();

        for (int[] r : road) {
            int sour = r[0];
            int dest = r[1];
            int dist = r[2];

            map.computeIfAbsent(sour, k -> new HashMap<>());
            map.computeIfAbsent(dest, k -> new HashMap<>());

            map.get(sour).computeIfAbsent(dest, k -> new ArrayList<>());
            map.get(dest).computeIfAbsent(sour, k -> new ArrayList<>());

            map.get(sour).get(dest).add(dist);
            map.get(dest).get(sour).add(dist);
        }

        return map;
    }

}
