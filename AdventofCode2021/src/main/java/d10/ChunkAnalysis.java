package d10;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ChunkAnalysis {
    List<CharacterStatus> characterStatuses = new ArrayList<>();
    boolean isCorrupt;
    int errorScore;
    private long completionScore;

    ChunkAnalysis(String row, List<CharacterStatus> characterStatuses, boolean isCorrupt, int errorScore) {
        this.isCorrupt = isCorrupt;
        this.errorScore = errorScore;
        this.characterStatuses = characterStatuses;
    }

    void calcCompletionScore() {
        List<CharacterStatus> unmatched = characterStatuses.stream()
                .filter(e -> !e.matched).toList();
        long score = 0;
        final int multiplier = 5;
        for (int loc = unmatched.size() - 1;loc >= 0;loc--) {
            int charScore = switch (unmatched.get(loc).value) {
                case '(' -> 1;
                case '[' -> 2;
                case '{' -> 3;
                case '<' -> 4;
                default -> 0;
            };
            score = score * multiplier + charScore;
        }
        completionScore = score;
    }

    long getCompletionScore() {return completionScore;}
}
