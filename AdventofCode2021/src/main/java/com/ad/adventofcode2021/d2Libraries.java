/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ad.adventofcode2021;

/**
 *
 * @author ca-adragt
 */
class Parser {
    String getDirection(String reading) {
        return reading.split("\\s")[0];
    }
    
    Integer getChange(String reading) {
        return Integer.valueOf(reading.split("\\s")[1]);
    }
}


class Position {
    private static final int STARTDEPTH = 0;
    private static final int STARTDISTANCE = 0;
    private static final int STARTAIM = 0;

    private int depth;
    private int distance;
    private int aim;
    
    Position() {
        depth = STARTDEPTH;
        distance = STARTDISTANCE;
        aim = STARTAIM;
    }
    
    void increaseDistance(int moveDistance) {
        distance = distance + moveDistance;
    }
    
    void increaseDepth(int depthChange) {
        depth = depth + depthChange;
    }
    
    void decreaseDepth(int depthChange) {
        depth = depth - depthChange;
    }
    
    void increaseAim(int change) {
        aim = aim + change;
    }
    
    void decreaseAim(int change) {
        aim = aim - change;
    }
    
    int getAim() {
        return aim;
    }
    
    int getDepth() {
        return depth;
    }
    
    int getDistance() {
        return distance;
    }
}
