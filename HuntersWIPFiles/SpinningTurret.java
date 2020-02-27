package test;
public class SpinningTurret extends Enemy {

    private boolean tickerFire = true;

    //Constructor
    public SpinningTurret(TankMap map, int col, int row, int facing) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 1;
        tookTurn = true;
        icon = 'T';
        isBullet = false;
    }

    //Shoot 1 turn, rotate the next, ad infinitium
    public void doTurn(){
        if(tickerFire){
            tickerFire = false;
            doShoot();
        } else{
            doMove();
            tickerFire = true;
        }
        tookTurn = true;
    }

    //Rotate clockwise
    public void doMove(){
        if(fireFacing < 3){
            fireFacing++;
        } else fireFacing = 0;
    }

    //Fire
    public void doShoot() {
        if (!map.shoot(this, 1, 1)) {
            doMove();
            tickerFire = true;
        }
    }
}