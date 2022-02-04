/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package d4;

/**
 *
 * @author ca-adragt
 */
import java.util.ArrayList;
import java.util.List;


class Tracker {
    private List<Board> boards = new ArrayList<>();
    
    public void addBoard(Board board) {
        boards.add(board);
    }
    
    public List<Board> getBoards() {
        return boards;
    }
}


class Board {
    private List<Cell> cells = new ArrayList<>();
    
    public boolean checkComplete(int row, int column) {
        long completeRequired = 5;
        
        long colCount = cells.stream()
                .filter(e -> e.getRow() == row && e.checkMarked())
                .count();
        boolean colMatch = colCount == completeRequired;
        
        long rowCount = cells.stream()
                .filter(e -> e.getColumn()== column && e.checkMarked())
                .count();
        boolean rowMatch = rowCount == completeRequired;
        return colMatch || rowMatch;
    }
    
    public void addCell(Cell cell) {
        cells.add(cell);
    }
    
    public boolean boardPopulated() {
        return cells.size() == 25;
    }
    
    public int getUnmarkedSum() {
        return cells.stream()
                .filter(e -> e.checkMarked() == false)
                .map(f -> f.getValue())
                .reduce(0, (subtotal, element) -> subtotal + element);
    }
    
    public Cell checkDrawMatch(int drawValue) {
        for (Cell cell : cells) {
            if (cell.getValue() == drawValue) {
                cell.mark();
                return cell;
            }
        }
        return new Cell(0, 0, 0);
    }
    
}


class Cell {
    private int row;
    private int column;
    private int value;
    private boolean marked;
    
    public Cell(int row, int column, int value) {
        this.row = row;
        this.column = column;
        this.value = value;
        marked = false;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public boolean checkMarked() {
        return marked;
    }
    
    public int getValue() {
        return value;
    }
 
    public void mark() {
        marked = true;
    }
}

class BingoParser {
    private int rowCounter = 0;
    
    public Board parseRow(Board board, String row) {
        rowCounter++;
        rowCounter = rowCounter == 6 ? 1 : rowCounter;
        row = row.trim();
        String[] values = row.split("\\s+");
        for (int i=0;i<values.length;i++) {
            Cell cell = new Cell(rowCounter, i+1, Integer.valueOf(values[i]));
            board.addCell(cell);
        }
        return board;
    }
    
}