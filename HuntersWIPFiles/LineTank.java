package test;
public class LineTank extends Enemy {

    private boolean fireTicker = true;

    //Constructor
    //Moves in direction of initial moveFacing, and fires to its right
    public LineTank(TankMap map, int col, int row, int facing) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        if(facing == 3){
            this.fireFacing = 0;
        } else fireFacing = facing+1;
        this.health = 3;
        tookTurn = true;
        icon = 'L';
        isBullet = false;
    }

    public void doTurn(){
        if(fireTicker){
            doShoot();
        } else doMove();
        tookTurn = true;
    }

    public void doMove(){

        if(!map.move(this)) {
            if(moveFacing == 0){
                moveFacing = 2;
            } else if (moveFacing == 1){
                moveFacing = 3;
            } else if (moveFacing == 2){
                moveFacing = 0;
            } else moveFacing = 1;
            map.move(this);
        }
        fireTicker = true;
    }

    public void doShoot() {
        map.shoot(this, 1, 1);
        fireTicker = false;
    }
}