package test;
public class SpinningTurret extends Enemy {

    private boolean tickerFire = true;

    //Constructor
    public SpinningTurret(TankMap map, int col, int row, int facing) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.facing = facing;
        this.health = 1;
        tookTurn = true;
        icon = 'T';
    }

    //Shoot 1 turn, rotate the next, ad infinitium
    public void doTurn(){
        if(tickerFire){
            doShoot();
            tickerFire = false;
        } else{
            doMove();
            tickerFire = true;
        }
        tookTurn = true;
    }

    //Rotate clockwise
    public void doMove(){
        if(facing < 3){
            facing++;
        } else facing = 0;
    }

    //Fire
    public void doShoot() {
        if (!map.shoot(this, 1, 1)) {
            doMove();
        }
    }
}