package test;

public class Wall extends Enemy{
    //Simply does nothing

    public Wall(TankMap map, int col, int row, int facing) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.facing = facing;
        this.health = 1000;
        icon = 'W';
        tookTurn = true;
    }
    
}