package test;
public class Turret extends Enemy {

    private boolean tickerFire = true;

    //Constructor
    public Turret(TankMap map, int col, int row, int facing) {
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

    public void doTurn(){
        if(tickerFire){
            doShoot();
            tickerFire = false;
        } else{
            tickerFire = true;
        }
        tookTurn = true;
    }

    public void doMove(){
    }

    public void doShoot() {
        map.shoot(this, 1, 1);
    }
}