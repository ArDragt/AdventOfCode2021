package d10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class ChunkParser {
    ChunkAnalysis checkCorruptRow(String dataRow) {
        final String LEFTBRACKETS = "{[(<";
        List<CharacterStatus> chunkCharacters = new ArrayList<>();
        BracketValues bracketValues = new BracketValues();

        for (int i=0;i<dataRow.length();i++) {
            char charValue = dataRow.charAt(i);
            if (LEFTBRACKETS.indexOf(charValue) != -1) {
                CharacterStatus characterStatus = new CharacterStatus(charValue, false);
                chunkCharacters.add(characterStatus);
            }
            else { // For this case it is a right bracket that needs a left match
                List<CharacterStatus> unmatched = chunkCharacters.stream()
                        .filter(e -> !e.matched)
                        .collect(Collectors.toList());
                CharacterStatus leftBracket = unmatched.get(unmatched.size() - 1);
                if (bracketValues.getBracketMatch(charValue) != leftBracket.value) {
                    int errorScore = bracketValues.getErrorScore(charValue);
                    ChunkAnalysis chunkAnalysis = new ChunkAnalysis(dataRow, unmatched, true, errorScore);
                    return chunkAnalysis;
                }
                else {
                    leftBracket.setMatched();
                }
            }
        }
        ChunkAnalysis chunkAnalysis = new ChunkAnalysis(dataRow, chunkCharacters, false, 0);
        return chunkAnalysis;
    }

}
