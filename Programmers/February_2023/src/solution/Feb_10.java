package solution;

import java.util.ArrayList;
import java.util.HashSet;

public class Feb_10 {
    public int solution(String[][] relation) {
        int numCandidateKeys = 0;

        int numCols = relation[0].length;
        int numRows = relation.length;

        HashSet<String> set = new HashSet<>();
        ArrayList<Integer> nonUniqueCols = new ArrayList<>();

        for (int c = 0; c < numCols; c++) {
            for (int r = 0; r < numRows; r++) {
                set.add(relation[r][c]);
            }

            if (set.size() < numRows) {
                nonUniqueCols.add(c);
            }

            set.clear();
        }

        numCandidateKeys += numCols - nonUniqueCols.size();

        if (!nonUniqueCols.isEmpty()) {
            int index = 0;
            int firstCol = nonUniqueCols.get(index);
            index++;

            ArrayList<String> compositeStrings = new ArrayList<>();

            for (int r = 0; r < numRows; r++) {
                compositeStrings.add(relation[r][firstCol]);
            }

            HashSet<String> compositeStringSet = new HashSet<>();

            while (index < nonUniqueCols.size()) {
                int lastCol = nonUniqueCols.get(index);

                for (int r = 0; r < numRows; r++) {
                    String compositeString = compositeStrings.get(r) + relation[r][lastCol];
                    compositeStrings.remove(r);
                    compositeStrings.add(r, compositeString);
                }

                compositeStringSet.addAll(compositeStrings);

                if (compositeStringSet.size() == numRows) {
                    numCandidateKeys++;
                    index = index + 1;
                    compositeStrings.clear();

                    if (index < nonUniqueCols.size()) {
                        firstCol = nonUniqueCols.get(index);
                        for (int r = 0; r < numRows; r++) {
                            compositeStrings.add(relation[r][firstCol]);
                        }
                    }
                }

                compositeStringSet.clear();
                index++;
            }
        }

        return numCandidateKeys;
    }
}
