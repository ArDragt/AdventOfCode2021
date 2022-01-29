/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ad.adventofcode2021;

/**
 *
 * @author ca-adragt
 */

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

class InputDataReader {
    private String basePath;
    private String filePath;
    
    InputDataReader(String basePath, String filePath) {
        this.basePath = basePath;
        this.filePath = filePath;
    }
    
    List<String> readTextList() {
        Path dir = Paths.get(basePath);
        Path inputFilePath = dir.resolve(filePath);
        List<String> inputData = new ArrayList<>();
        
        try {
            inputData = Files.readAllLines(inputFilePath);
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        return inputData;
    }
}
