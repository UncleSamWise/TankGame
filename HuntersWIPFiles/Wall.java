package test;

public class Wall extends Enemy{
    //Simply does nothing

    public Wall(TankMap map, int col, int row, int facing) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 99999;
        icon = 'W';
        tookTurn = true;
        isBullet = false;
    }

    public void doTurn() {
        health = 99999;
    }
}