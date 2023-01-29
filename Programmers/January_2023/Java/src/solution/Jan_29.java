package solution;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Jan_29 {
    public int solution(int N, int[][] road, int K) {
        HashMap<Integer, PriorityQueue<Node>> map = getMap(road);
        HashSet<Integer> traversedTowns = new HashSet<>();
        HashMap<Integer, Integer> minimalDistances = initializeMinimalDistanceMapping(N);

        travelMap(1, 0, minimalDistances, traversedTowns, map);

        return (int) minimalDistances.values().stream().filter(value -> value <= K).count();
    }

    private void travelMap(Integer currentTown,
                           int takenHours,
                           HashMap<Integer, Integer> minimalDistances,
                           HashSet<Integer> traversedTowns,
                           HashMap<Integer, PriorityQueue<Node>> map) {

        if (minimalDistances.get(currentTown) < takenHours) {
            return;
        }

        traversedTowns.add(currentTown);
        minimalDistances.put(currentTown, takenHours);

        PriorityQueue<Node> connections = map.get(currentTown);

        while (!connections.isEmpty()) {
            Node connection = connections.poll();
            if (!traversedTowns.contains(connection.townNum)) {
                travelMap(connection.townNum, takenHours + connection.distance, minimalDistances, traversedTowns, map);
            }
        }

        traversedTowns.remove(currentTown);
    }

    public HashMap<Integer, PriorityQueue<Node>> getMap(int[][] road) {
        HashMap<Integer, PriorityQueue<Node>> map = new HashMap<>();

        for (int[] r : road) {
            int sour = r[0];
            int dest = r[1];
            int dist = r[2];

            map.computeIfAbsent(sour, k -> new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.distance)));
            map.computeIfAbsent(dest, k -> new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.distance)));

            map.get(sour).add(new Node(dest, dist));
            map.get(dest).add(new Node(sour, dist));
        }

        return map;
    }

    private HashMap<Integer, Integer> initializeMinimalDistanceMapping(int numTowns) {
        HashMap<Integer, Integer> mapping = new HashMap<>(numTowns);
        mapping.put(1, 0);

        int townNum = 2;

        while (townNum <= numTowns) {
            mapping.put(townNum, Integer.MAX_VALUE);
            townNum++;
        }

        return mapping;
    }

    public static class Node {
        Integer townNum;
        Integer distance;

        public Node(Integer townNum, Integer distance) {
            this.townNum = townNum;
            this.distance = distance;
        }


    }

}
