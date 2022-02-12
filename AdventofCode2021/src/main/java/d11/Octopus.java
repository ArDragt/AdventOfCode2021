package d11;

public class Octopus {
    final int row;
    final int col;
    private int value;
    private int flashCount = 0;
    private boolean flashed = false;
    private boolean flashProcessed = false;

    Octopus(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    int getValue() {return value;}

    long getFlashCount() {return flashCount;}

    boolean getFlashedState() {return flashed;}

    boolean getFlashProcessedState() {return flashProcessed;}

    void incrementValue() {
        if (value == 9 && !flashed && !flashProcessed) {
            value = 0;
            flashed = true;
            flashCount++;
        } else {
            value++;
        }
    }

    void setFlashProcessed() {flashProcessed = true;}

    void clearFlashed() {flashed = false;}

    void clearFlashProcessed() {flashProcessed = false;}
}
