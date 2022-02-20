package d13;

class Dot {
    int x;
    int y;

    Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Dot foldX(int xAxis) throws IndexOutOfBoundsException{
        Dot newDot = new Dot(x, y);
        if (x > xAxis) {
            int newX = xAxis - (x - xAxis );
            if (newX >= 0) {
                newDot.setX(newX);
            } else {
                throw new IndexOutOfBoundsException("Negative X value");
            }
        }
        return newDot;
    }

    Dot foldY(int yAxis) throws IndexOutOfBoundsException{
        Dot newDot = new Dot(x, y);
        if (y > yAxis) {
            int newY = yAxis - (y - yAxis);
            if (newY >= 0) {
                newDot.setY(newY);
            } else {
                throw new IndexOutOfBoundsException("Negative Y value");
            }
        }
        return newDot;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }
}
