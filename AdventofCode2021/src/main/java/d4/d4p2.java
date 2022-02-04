/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package d4;

import com.ad.adventofcode2021.InputDataReader;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author ca-adragt
 */
public class d4p2 {

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
        Tracker completedTracker = new Tracker();
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
        
        // Go through draws, see which board finishes last
        boolean gameComplete = false;
        Board winningBoard = new Board();
        int finalValue = 0;
        List<Integer> completeDraws = new ArrayList<>();
        
        for (String draw : drawsData) {
            int drawValue = Integer.valueOf(draw);
            ListIterator<Board> boardsListIterator = tracker.getBoards()
                    .listIterator();
            while (boardsListIterator.hasNext()) {
                Board playingBoard = boardsListIterator.next();
                Cell matchCell = playingBoard.checkDrawMatch(drawValue);
                if (matchCell.getRow() > 0 && matchCell.getColumn() > 0) {
                    if (playingBoard.checkComplete(matchCell.getRow(), 
                            matchCell.getColumn())) {
                        completedTracker.addBoard(playingBoard);
                        completeDraws.add(drawValue);
                        boardsListIterator.remove();
                    }
                }
            }
        }
        
        // Get last board that completed
        List<Board> completedBoards = completedTracker.getBoards();
        Board lastCompleteBoard = completedBoards.get(completedBoards.size() - 1);
        int lastCompleteDraw = completeDraws.get(completeDraws.size() - 1);
        
        // Sum all unmarked cells, and multiply by the last drawn value
        System.out.println(lastCompleteBoard.getUnmarkedSum()*lastCompleteDraw);
    }
    
}
