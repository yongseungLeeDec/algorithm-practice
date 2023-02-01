package solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Feb_1 {
    public int solution(int n, int[][] wires) {
        HashMap<Integer, HashSet<Integer>> grid = new HashMap<>();

        for (int[] wire : wires) {
            int t1 = wire[0];
            int t2 = wire[1];

            grid.computeIfAbsent(t1, t -> new HashSet<>());
            grid.computeIfAbsent(t2, t -> new HashSet<>());

            grid.get(t1).add(t2);
            grid.get(t2).add(t1);
        }

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

//    int N, min;
//    int[][] map;
//    int[] vst;
//    int dfs(int n){
//        vst[n] = 1;
//        int child = 1;
//
//        for(int i = 1; i <= N; i++) {
//            if(vst[i] == 0 && map[n][i] == 1) {
//                child += dfs(i);
//            }
//        }
//        min = Math.min(min, Math.abs(child - (N - child)));
//        return child;
//    }
//    public int solution_smart(int n, int[][] wires) {
//        N = n;
//        min = n;
//        map = new int[n+1][n+1];
//        vst = new int[n+1];
//
//        for(int[] wire : wires) {
//            int a = wire[0], b = wire[1];
//            map[a][b] = map[b][a] = 1;
//        }
//
//        dfs(1);
//
//        return min;
//    }


}
