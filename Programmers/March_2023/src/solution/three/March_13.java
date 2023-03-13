package solution.three;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class March_13 {

    public static void main(String[] args) {
        March_13 march13 = new March_13();
        System.out.println(march13.solution(2, new String[] {"N~F=0", "R~T>2"}));
    }

    private static final char[] FRIENDS = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private static final Map<Character, Boolean> VISITED_MAP = new HashMap<>();
    private static final Map<Character, Friend> FRIEND_MAP = new HashMap<>();
    private static final Set<String> ANSWER_SET = new HashSet<>(); // 중복이 생길 수 있다.

    public int solution(int n, String[] data) {
        for (char fr : FRIENDS) {
            FRIEND_MAP.put(fr, new Friend(fr));
            VISITED_MAP.put(fr, false);
        }

        for (String condition : data) {
            char[] cdn = condition.toCharArray();
            FRIEND_MAP.get(cdn[0]).conditions.add(new Condition(cdn[2], cdn[3], cdn[4] - '0'));
            FRIEND_MAP.get(cdn[2]).conditions.add(new Condition(cdn[0], cdn[3], cdn[4] - '0'));
        }

        for (char friend : FRIENDS) {
            dfs(0, friend);
        }

        return ANSWER_SET.size();
    }

    private static void dfs(int index, char symbol) {
        if (index == FRIENDS.length) {
            char[] arr = new char[FRIENDS.length];

            for (Friend fr : FRIEND_MAP.values()) {
                arr[fr.position] = fr.symbol;
            }

            ANSWER_SET.add(new String(arr));

            return;
        }

        for (int i = 0; i < FRIENDS.length; i++) {
            if (!VISITED_MAP.get(symbol)) {
                VISITED_MAP.put(symbol, true);
                FRIEND_MAP.get(symbol).position = index;

                if (FRIEND_MAP.get(symbol).isComplyingEveryCondition()) {
                    dfs(index + 1, FRIENDS[i]);
                }

                VISITED_MAP.put(symbol, false);
                FRIEND_MAP.get(symbol).position = null;
            }
        }
    }

    public static class Friend {
        final char symbol;
        Integer position = null;
        final HashSet<Condition> conditions = new HashSet<>();

        public Friend(char symbol) {
            this.symbol = symbol;
        }

        public boolean isComplyingEveryCondition() {
            for (Condition cd : this.conditions) {
                if (!cd.isComplying(this.position)) {
                    return false;
                }
            }

            return true;
        }

    }

    public static class Condition {
        final char other;
        final char operator;
        final int distance;


        public Condition(char other, char operator, int distance) {
            this.other = other;
            this.operator = operator;
            this.distance = distance;
        }

        public boolean isComplying(int position) {
            Integer otherPosition = FRIEND_MAP.get(other).position;

            if (otherPosition == null) {
                return true;
            }

            if (operator == '>') {
                return Math.abs(position - otherPosition) - 1 > distance;
            }

            if (operator == '<') {
                return Math.abs(position - otherPosition) - 1 < distance;
            }

            return Math.abs(position - otherPosition) - 1 == distance;
        }
    }
}
