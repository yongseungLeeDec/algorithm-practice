package solution;


import java.util.*;

public class Jan_29 {
    public int solution(int N, int[][] road, int K) {
        HashMap<Integer, HashMap<Integer, Integer>> map = getMap(road);
        HashMap<Integer, Integer> minDistances = initializeMinDistances(N);

        PriorityQueue<Node> visitingQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        visitingQueue.add(new Node(1, 0));

        while (!visitingQueue.isEmpty()) {
            Node town = visitingQueue.poll();
            Integer townNum = town.townNum;
            Integer distance = town.distance;

            if (minDistances.get(townNum) < distance) {
                continue;
            }

            HashMap<Integer, Integer> neighbors = map.get(townNum);

            for (Integer neighbor : neighbors.keySet()) {
                Integer newDist = distance + neighbors.get(neighbor);

                if (newDist < minDistances.get(neighbor)) {
                    minDistances.put(neighbor, newDist);
                    visitingQueue.add(new Node(neighbor, newDist));
                }

            }
        }

        return (int) minDistances.values().stream().filter(value -> value <= K).count();
    }

    private static HashMap<Integer, HashMap<Integer, Integer>> getMap(int[][] road) {
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();

        for (int[] r : road) {
            int sour = r[0];
            int dest = r[1];
            int dist = r[2];

            map.computeIfAbsent(sour, k -> new HashMap<>());
            map.computeIfAbsent(dest, k -> new HashMap<>());

            map.get(sour).putIfAbsent(dest, Integer.MAX_VALUE);
            map.get(dest).putIfAbsent(sour, Integer.MAX_VALUE);

            int currDist = map.get(sour).get(dest);
            int renewedDist = Math.min(currDist, dist);

            if (renewedDist < currDist) {
                map.get(sour).put(dest, renewedDist);
                map.get(dest).put(sour, renewedDist);
            }
        }

        return map;
    }

    private HashMap<Integer, Integer> initializeMinDistances(int numTowns) {
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
