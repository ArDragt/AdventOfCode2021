/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.ad.adventofcode2021;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author ca-adragt
 */
public class d1p2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d1input";
        
        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();
        
        List<Integer> groupReadings = new ArrayList();
        
        //Group readings into threes
        for (int i=0; i < inputData.size() - 2; i++) {
            Integer groupSum = Integer.valueOf(inputData.get(i)) +
                    Integer.valueOf(inputData.get(i + 1)) +
                    Integer.valueOf(inputData.get(i + 2));
            groupReadings.add(groupSum);
        }
        
        int increaseCounter = 0;
        
        for (int i = 0;i < groupReadings.size(); i++) {
            if (i == 0) {
            }
            else {
                int prevValue = Integer.valueOf(groupReadings.get(i-1));
                int curValue = Integer.valueOf(groupReadings.get(i));
                if (curValue > prevValue) {
                    increaseCounter++;
                }
            }
        }
        System.out.println(increaseCounter);
    }
}
