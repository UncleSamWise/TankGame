package Files;

public class Wall extends Enemy{
    //Simply does nothing

    public Wall(Enemy[][] grid, int col, int row, int facing) {
        this.grid = grid;
        this.col = col;
        this.row = row;
        this.facing = facing;
        this.health = 1000;
        icon = 'W';
    }
    
}