package test;
public class LineTank extends Enemy {


    private boolean left = true;
    private boolean fireTicker = true;

    //Constructor
    //Moves in direction of initial facing (shoots to the right first, though)
    public LineTank(TankMap map, int col, int row, int facing) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.facing = facing;
        this.health = 3;
        tookTurn = true;
        icon = 'V';
        isBullet = false;
    }

    public void doTurn(){
        if(fireTicker){
            doShoot();
        } else doMove();
        tookTurn = true;
    }

    public void doMove(){
        if(left){
            if(facing == 0){
                facing = 3;
            } else facing--;
        } else{
            if(facing == 3){
                facing = 0;
            } else facing++;
        }
        if(!map.move(this)) {
            left = !left;
            if(facing == 0){
                facing = 2;
            } else if (facing == 1){
                facing = 3;
            } else if (facing == 2){
                facing = 0;
            } else facing = 1;
            map.move(this);
        }
        fireTicker = true;
    }

    public void doShoot() {
        if(left){
            if(facing == 3){
                facing = 0;
            } else facing++;
        } else{
            if(facing == 0){
                facing = 3;
            } else facing--;
        }
        map.shoot(this, 1, 1);
        fireTicker = false;
    }
}