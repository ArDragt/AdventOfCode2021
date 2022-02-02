package com.ad.adventofcode2021;

import java.util.*;

/**
 *
 * @author ca-adragt
 */
class OutputDigitParser {
    private final String row;
    private static final String SEPARATOR = "\\|";
    private static HashSet<Integer> targetCharLengths;
    
    public OutputDigitParser(String row) {
        this.row = row;
        targetCharLengths = new HashSet<>();
        targetCharLengths.add(2);
        targetCharLengths.add(3);
        targetCharLengths.add(4);
        targetCharLengths.add(7);
    }
    
    public long countDigits() {
        String output = row.split(SEPARATOR)[1].trim();
        List<String> digits = Arrays.asList(output.split("\\s+"));
        return digits.stream()
                .filter(e -> targetCharLengths.contains(e.length()))
                .count();
    }
}

class IoParser {
    //TODO: Figure this puzzle out!
    private static final String SEPARATOR = "\\|";
    private static final String SPACE = "\\s+";
    String data;

    IoParser(String data) {
        this.data = data;
    }

    List<String> getInputParts() {
        return Arrays.asList(data.split(SEPARATOR)[0].trim().split(SPACE));
    }

}
