/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d7;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import static java.util.stream.Collectors.*;


/**
 *
 * @author ca-adragt
 */
class CrabCostCalculator {
    private List<Integer> allPositions;
    private Map<Integer, Integer> costMap = new HashMap<>();

    public CrabCostCalculator(List<Integer> allPositions) {
        this.allPositions = allPositions;
    }
    
    public void calcLinearMoveCost(int currentPosition) {
        List<Integer> otherPositions = allPositions.stream()
                .filter(e -> e != currentPosition)
                .collect(toList());
        int cost = 0;
        for (int position : otherPositions) {
            int distance = position - currentPosition;
            cost = cost + Math.abs(distance);
        }
        costMap.put(currentPosition, cost);
    }
    
    public void calcIncrMoveCost(int currentPosition) {
        List<Integer> otherPositions = allPositions.stream()
                .filter(e -> e != currentPosition)
                .collect(toList());
        int cost = 0;
        for (int position : otherPositions) {
            int distance = Math.abs(position - currentPosition);
            for (int moveCost = 1;moveCost <= distance; moveCost++) {
                cost = cost + moveCost;
            }
        }
        costMap.put(currentPosition, cost);
    }
    
    public int getLowestCost() {
        Collection<Integer> allCosts = costMap.values();
        return Collections.min(allCosts);
    }
}