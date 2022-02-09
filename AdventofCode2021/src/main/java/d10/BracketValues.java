package d10;

import java.util.HashMap;
import java.util.Map;


class BracketValues {
    private Map<Character, Character> bracketLookup = new HashMap<>();

    BracketValues() {
        bracketLookup.put(')', '(');
        bracketLookup.put('}', '{');
        bracketLookup.put(']', '[');
        bracketLookup.put('>', '<');
    }

    char getBracketMatch(char rightBracket) {
        return bracketLookup.get(rightBracket);
    }

    int getErrorScore(char rightBracket) {
        int errorValue = switch (rightBracket) {
            case ')' -> 3;
            case ']' -> 57;
            case '}' -> 1197;
            case '>' -> 25137;
            default -> 0;
        };
        return errorValue;
    }
}
