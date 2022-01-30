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
public class d4p1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Get base path
        String basePath = System.getProperty("user.dir");
        String boardsFilePath = "d4boards";
        String drawsFilePath = "d4draws";
        
        // Read input data
        InputDataReader boardsReader = new InputDataReader(basePath, boardsFilePath);
        List<String> boardsData = boardsReader.readTextList();
        
        InputDataReader drawsReader = new InputDataReader(basePath, drawsFilePath);
        String drawsDataString = drawsReader.readTextList().get(0);
        String[] drawsData = drawsDataString.split(",");

        // Boards tracker
        Tracker tracker = new Tracker();
        BingoParser parser = new BingoParser();
        
        // Create first board
        Board board = new Board();
        
        // Populate boards
        for (String rowData : boardsData) {
            if (board.boardPopulated()) {
                tracker.addBoard(board);
                board = new Board();
            }
            if (rowData.isBlank()) {
                continue;
            }
            board = parser.parseRow(board, rowData);
        }
        
        // Go through draws, see which board finishes first
        boolean gameComplete = false;
        Board winningBoard = new Board();
        int finalValue = 0;
        for (String draw : drawsData) {
            int drawValue = Integer.valueOf(draw);
            for (Board playingBoard : tracker.getBoards()) {
                Cell matchCell = playingBoard.checkDrawMatch(drawValue);
                if (matchCell.getRow() > 0 && matchCell.getColumn() > 0) {
                    if (playingBoard.checkComplete(matchCell.getRow(), 
                            matchCell.getColumn())) {
                        gameComplete = true;
                        winningBoard = playingBoard;
                        finalValue = drawValue;
                        break;
                    }
                }
            }
            if (gameComplete) {
                break;
            }
        }
        // Sum all unmarked cells, and multiply by the last drawn value
        System.out.println(winningBoard.getUnmarkedSum()*finalValue);
    }
    
}
