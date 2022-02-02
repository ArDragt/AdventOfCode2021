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
public class d8p1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String filePath = "d8input";
        
        // Read input data
        InputDataReader reader = new InputDataReader(basePath, filePath);
        List<String> inputData = reader.readTextList();
        
        long total = 0;
        for (String data : inputData) {
            OutputDigitParser parser = new OutputDigitParser(data);
            total = total + parser.countDigits();
        }
        
        System.out.println(total);
    }
    
}
