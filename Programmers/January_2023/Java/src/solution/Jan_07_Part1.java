package solution;

public class Jan_07_Part1 {
    static final char NULL_CHAR = '\0';

    /*

     */
    public String solution(String new_id) {
        int idLength = new_id.length();
        char[] id = new_id.toCharArray();

        convertAlphabetsToLowerCase(id);
        nullifyInvalidCharacters(id);
        reducePeriodSequenceToSinglePeriod(id);
        trimPeriods(id);

        if (isEmptyString(id)) {
            insertLowerCaseAtoZeroIndex(id);
        }

        reduceToFifteenCharactersAtMax(id);
        trimPeriods(id);

        String idString = buildId(id);

        if (idString.length() <= 2) {
            StringBuilder sb = new StringBuilder();
            sb.append(idString);
            if (idString.length() == 1) {
                sb.append(idString.charAt(0));
                sb.append(idString.charAt(0));
            } else {
                sb.append(idString.charAt(1));
            }
            return sb.toString();
        }

        return idString;
    }

    public char[] convertAlphabetsToLowerCase(char[] id) {
        for (int i = 0; i < id.length; ++i) {
            if ('A' <= id[i] && id[i] <= 'Z') {
                id[i] |= 0x20;
            }
        }
        return id;
    }

    public void nullifyInvalidCharacters(char[] id) {
        for (int i = 0; i < id.length; ++i) {
            char letter = id[i];

            if (('a' <= letter && letter <= 'z')
                    || ('0' <= letter && letter <= '9')
                    || letter == '-'
                    || letter == '_'
                    || letter == '.') {
                continue;
            }

            id[i] = NULL_CHAR;
        }
    }

    public void reducePeriodSequenceToSinglePeriod(char[] id) {
        for (int i = 0; i < id.length; i++) {
            if (id[i] == '.') {
                while (++i < id.length && (id[i] == NULL_CHAR || id[i] == '.')) {
                    id[i] = NULL_CHAR;
                }
            }
        }
    }

    public void trimPeriods(char[] id) {
        int firstNonNullCharIndex = 0;
        int lastNonNullCharIndex = 0;

        for (int i = 0; i < id.length; i++) {
            if (id[i] != NULL_CHAR) {
                firstNonNullCharIndex = i;
                break;
            }
        }

        for (int i = 1; i <= id.length; i++) {
            if (id[id.length - i] != NULL_CHAR) {
                lastNonNullCharIndex = i;
                break;
            }
        }

        if (id[firstNonNullCharIndex] == '.') {
            id[firstNonNullCharIndex] = NULL_CHAR;
        }

        if (id[lastNonNullCharIndex] == '.') {
            id[lastNonNullCharIndex] = NULL_CHAR;
        }
    }

    public boolean isEmptyString(char[] id) {
        for (char c : id) {
            if (c != NULL_CHAR) {
                return false;
            }
        }
        return true;
    }

    public void insertLowerCaseAtoZeroIndex(char[] id) {
        id[0] = 'a';
    }

    public void reduceToFifteenCharactersAtMax(char[] id) {
        int nonNullCount = 0;
        int i;

        for (i = 0; i < id.length; i++) {
            if (id[i] != NULL_CHAR) {
                nonNullCount++;
            }

            if (nonNullCount == 15) {
                break;
            }
        }

        if (nonNullCount == 15) {
            i++;
            while (i < id.length) {
                id[i] = NULL_CHAR;
                i++;
            }
        }
    }

    public String buildId(char[] id) {
        StringBuilder sb = new StringBuilder();
        for (char c : id) {
            if (c != NULL_CHAR) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
