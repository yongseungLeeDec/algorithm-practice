package solution;

import java.util.ArrayList;

public class Feb_20 {
    public static void main(String[] args) {
        String[] info = new String[]{
                "java backend junior pizza 150",
                "python frontend senior chicken 210",
                "python frontend senior chicken 150",
                "cpp backend senior pizza 260",
                "java backend junior chicken 80",
                "python backend senior chicken 50"
        };

        String[] query = new String[]{
                "java and backend and junior and pizza 100",
                "python and frontend and senior and chicken 200",
                "cpp and - and senior and pizza 250",
                "- and backend and senior and - 150",
                "- and - and - and chicken 100",
                "- and - and - and - 150"
        };

        solution(info, query);
    }

    public static int[] solution(String[] info, String[] query) {
        int[] numMatchingQueries = new int[query.length];

        ArrayList<ArrayList<Integer>> lists = new ArrayList<>(24);

        int count = 0;

        while (count < 24) {
            lists.add(new ArrayList<>());
            count++;
        }

        ArrayList<String[]> infoParsed = new ArrayList<>();

        for (String i : info) {
            String[] split = i.split(" ");
            infoParsed.add(split);
        }

        infoParsed.sort((o1, o2) -> {
            Integer score1 = Integer.parseInt(o1[4]);
            Integer score2 = Integer.parseInt(o2[4]);
            return score2 - score1;
        });

        for (String[] splitInfo : infoParsed) {
            putInfo(lists, splitInfo);
        }

        int queryNum = 0;
        for (String q : query) {
            String[] splitQuery = q.split(" ");
            numMatchingQueries[queryNum] = getNumApplicants(splitQuery, lists, 0, 0);
            queryNum++;
        }

        return numMatchingQueries;
    }

    private static int getNumApplicants(String[] query, ArrayList<ArrayList<Integer>> lists, int listIndex, int level) {
        if (level == 4) {
            return calculateNumMeetsConditions(lists.get(listIndex), Integer.parseInt(query[7]));
        }

        int num = 0;

        if (level == 0) {
            String language = query[0];
            switch (language) {
                case "cpp":
                    num += getNumApplicants(query, lists, (int) (listIndex + 0 * Math.pow(2, 3)), level + 1);
                    break;
                case "java":
                    num += getNumApplicants(query, lists, (int) (listIndex + 1 * Math.pow(2, 3)), level + 1);
                    break;
                case "python":
                    num += getNumApplicants(query, lists, (int) (listIndex + 2 * Math.pow(2, 3)), level + 1);
                    break;
                default:
                    num += getNumApplicants(query, lists, (int) (listIndex + 0 * Math.pow(2, 3)), level + 1);
                    num += getNumApplicants(query, lists, (int) (listIndex + (1 * Math.pow(2, 3))), level + 1);
                    num += getNumApplicants(query, lists, (int) (listIndex + (2 * Math.pow(2, 3))), level + 1);
            }
        }

        if (level == 1) {
            String sector = query[2];
            switch (sector) {
                case "frontend":
                    num += getNumApplicants(query, lists, (int) (listIndex + 0 * Math.pow(2, 2)), level + 1);
                    break;
                case "backend":
                    num += getNumApplicants(query, lists, (int) (listIndex + 1 * Math.pow(2, 2)), level + 1);
                    break;
                default:
                    num += getNumApplicants(query, lists, (int) (listIndex + 0 * Math.pow(2, 2)), level + 1);
                    num += getNumApplicants(query, lists, (int) (listIndex + 1 * Math.pow(2, 2)), level + 1);
            }
        }

        if (level == 2) {
            String experience = query[4];
            switch (experience) {
                case "junior":
                    num += getNumApplicants(query, lists, (int) (listIndex + 0 * Math.pow(2, 1)), level + 1);
                    break;
                case "senior":
                    num += getNumApplicants(query, lists, (int) (listIndex + 1 * Math.pow(2, 1)), level + 1);
                    break;
                default:
                    num += getNumApplicants(query, lists, (int) (listIndex + 0 * Math.pow(2, 1)), level + 1);
                    num += getNumApplicants(query, lists, (int) (listIndex + 1 * Math.pow(2, 1)), level + 1);
            }
        }

        if (level == 3) {
            String soulFood = query[6];
            switch (soulFood) {
                case "pizza":
                    num += getNumApplicants(query, lists, (int) (listIndex + 0 * Math.pow(2, 0)), level + 1);
                    break;
                case "chicken":
                    num += getNumApplicants(query, lists, (int) (listIndex + 1 * Math.pow(2, 0)), level + 1);
                    break;
                default:
                    num += getNumApplicants(query, lists, (int) (listIndex + 0 * Math.pow(2, 0)), level + 1);
                    num += getNumApplicants(query, lists, (int) (listIndex + 1 * Math.pow(2, 0)), level + 1);
            }
        }

        return num;
    }

    private static int calculateNumMeetsConditions(ArrayList<Integer> list, int minimalScore) {
        int count = 0;

        for (Integer score : list) {
            if (score < minimalScore) {
                break;
            }
            count++;
        }

        return count;
    }

    private static void putInfo(ArrayList<ArrayList<Integer>> lists, String[] info) {
        int indexValue = 0;

        String language = info[0];
        String sector = info[1];
        String experience = info[2];
        String soulFood = info[3];
        Integer testScore = Integer.parseInt(info[4]);

        switch (language) {
            case "cpp":
                indexValue += 0 * Math.pow(2, 3);
                break;
            case "java":
                indexValue += 1 * Math.pow(2, 3);
                break;
            default: // python
                indexValue += 2 * Math.pow(2, 3);
        }

        if (sector.equals("frontend")) {
            indexValue += 0 * Math.pow(2, 2);
        } else {
            indexValue += 1 * Math.pow(2, 2);
        }

        if (experience.equals("junior")) {
            indexValue += 0 * Math.pow(2, 1);
        } else {
            indexValue += 1 * Math.pow(2, 1);
        }

        if (soulFood.equals("pizza")) {
            indexValue += 0 * Math.pow(2, 0);
        } else {
            indexValue += 1 * Math.pow(2, 0);
        }

        lists.get(indexValue).add(testScore);
    }
}
