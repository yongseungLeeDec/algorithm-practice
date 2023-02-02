package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Feb_1 {
    public int solution(int n, int[][] wires) {
        HashMap<Integer, HashSet<Integer>> grid = getGrid(wires);
        int minGridSizeDiff = Integer.MAX_VALUE;
        int v1;
        int v2;

        HashSet<Integer> sGrid1 = new HashSet<>();
        HashSet<Integer> sGrid2 = new HashSet<>();

        for (int[] wire : wires) {
            v1 = wire[0];
            v2 = wire[1];

            grid.get(v1).remove(v2);
            grid.get(v2).remove(v1);

            configureGrid(grid, sGrid1, v1);
            configureGrid(grid, sGrid2, v2);

            int diff = Math.abs(sGrid1.size() - sGrid2.size());

            if (diff < minGridSizeDiff) {
                minGridSizeDiff = diff;
            }

            sGrid1.clear();
            sGrid2.clear();

            grid.get(v1).add(v2);
            grid.get(v2).add(v1);
        }

        return minGridSizeDiff;
    }

    private void configureGrid(HashMap<Integer, HashSet<Integer>> grid, HashSet<Integer> smallGrid, Integer start) {
        HashSet<Integer> visited = new HashSet<>();
        ArrayList<Integer> queue = new ArrayList<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            Integer tower = queue.remove(0);
            smallGrid.add(tower);
            visited.add(tower);

            HashSet<Integer> connectedTowers = grid.get(tower);

            for (Integer t: connectedTowers) {
                if (!visited.contains(t)) {
                    queue.add(t);
                }
            }
        }
    }

    /*
    Let's make a smart solution
     */
    private static int MIN = Integer.MAX_VALUE;
    public int solution_smart(int n, int[][] wires) {
        HashMap<Integer, HashSet<Integer>> grid = getGrid(wires);
        HashSet<Integer> traversed = new HashSet<>();
        traverseGrid(1, new HashSet<>(), grid);

        return MIN;
    }

    private int traverseGrid(int startTowerNumber, HashSet<Integer> traversed, HashMap<Integer, HashSet<Integer>> grid) {
        int numConnected = 1;
        traversed.add(startTowerNumber);
        HashSet<Integer> connectedTowers = grid.get(startTowerNumber);

        for (Integer tower : connectedTowers) {
            if (!traversed.contains(tower)) {
                numConnected += traverseGrid(tower, traversed, grid);
            }
        }

        MIN = Math.min(MIN, Math.abs(numConnected - (grid.size() - numConnected)));

        return numConnected;
    }

    private HashMap<Integer, HashSet<Integer>> getGrid(int[][] wires) {
        HashMap<Integer, HashSet<Integer>> grid = new HashMap<>();

        for (int[] wire : wires) {
            int t1 = wire[0];
            int t2 = wire[1];

            grid.computeIfAbsent(t1, t -> new HashSet<>());
            grid.computeIfAbsent(t2, t -> new HashSet<>());

            grid.get(t1).add(t2);
            grid.get(t2).add(t1);
        }

        return grid;
    }
}
