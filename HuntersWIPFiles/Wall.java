public class Wall implements Enemy{
    //Simply does nothing
    private Enemy[][] grid;
    private int col;
    private int row;
    int health = 1000;
    int facing;

    public Wall(Enemy[][] grid, int col, int row, int facing) {
        this.grid = grid;
        this.col = col;
        this.row = row;
        this.facing = facing;
    }

    int getHealth() return health;
    void doTurn();
    void doMove();
    void doShoot();

    public void doHit(int damage){
        health -= damage;
        if(health <= 0) doDie();
    }

    private void doDie(){
        grid[col][row] = null;
    }

}