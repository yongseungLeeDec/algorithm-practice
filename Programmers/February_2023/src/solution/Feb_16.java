package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Feb_16 {
    public int solution(int[] cards) {
        ArrayList<Integer> clusterSizes = getClusterSizesAsSorted(cards);
        int numClusters = clusterSizes.size();

        if (numClusters < 2) {
            return 0;
        }

        return clusterSizes.get(numClusters - 1) * clusterSizes.get(numClusters - 2);
    }

    private ArrayList<Integer> getClusterSizesAsSorted(int[] cards) {
        ArrayList<Integer> clusterSizes = new ArrayList<>();
        HashSet<Integer> openedBoxNums = new HashSet<>();

        for (int i = 0; i < cards.length; i++) {
            if (openedBoxNums.contains(i + 1)) {
                continue;
            }

            int singleClusterSize = getSingleClusterSize(openedBoxNums, i + 1, cards);
            clusterSizes.add(singleClusterSize);
        }

        Collections.sort(clusterSizes);

        return clusterSizes;
    }

    private int getSingleClusterSize(HashSet<Integer> openedBoxNums, int startBoxNum, int[] cards) {
        int before = openedBoxNums.size();

        openedBoxNums.add(startBoxNum);

        int boxNum = cards[startBoxNum - 1];

        while (!openedBoxNums.contains(boxNum)) {
            openedBoxNums.add(boxNum);
            boxNum = cards[boxNum - 1];
        }

        return openedBoxNums.size() - before;
    }
}
