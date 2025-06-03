package gameLaby.laby;

public class Mur implements Entites {
    private int x,y;

    @Override
    public boolean etrePresent(int x, int y) {
        return this.x == x && this.y == y;
    }
    public Mur(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
}
