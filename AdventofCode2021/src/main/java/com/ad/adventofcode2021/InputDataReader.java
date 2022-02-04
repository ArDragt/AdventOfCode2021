package com.ad.adventofcode2021;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ca-adragt
 */

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class InputDataReader {
    private String basePath;
    private String filePath;
    
    public InputDataReader(String basePath, String filePath) {
        this.basePath = basePath;
        this.filePath = filePath;
    }
    
    public List<String> readTextList() {
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
    
    public String readTextLine() {
        Path dir = Paths.get(basePath);
        Path inputFilePath = dir.resolve(filePath);
        String inputData = null;
        
        try {
            inputData = Files.readString(inputFilePath);
        }
        catch (IOException e) {
            System.out.println(e.toString());
        }
        return inputData;
    }
}
