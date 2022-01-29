/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ad.adventofcode2021;

import java.util.List;

/**
 *
 * @author ca-adragt
 */
public class d2p1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d2input";
        
        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();
        
        Parser parser = new Parser();
        Position positionHandler = new Position();
        
        for (String reading : inputData) {
            String direction = parser.getDirection(reading);
            Integer change = parser.getChange(reading);
            
            switch (direction) {
                case "forward":
                    positionHandler.increaseDistance(change);
                    break;
                case "up":
                    positionHandler.decreaseDepth(change);
                    break;
                case "down":
                    positionHandler.increaseDepth(change);
                    break;
                default:
                    throw new AssertionError();
            }
        }
        
        System.out.println(positionHandler.getDepth()*positionHandler.getDistance());
    }
}
