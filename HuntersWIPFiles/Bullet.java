package test;
//Bullet can be used for both enemy shots and player shots
public class Bullet extends Enemy{

    private int speed; // Tiles per tick
    private int damage; // Healthpoints damage

    public Bullet(TankMap map, int col, int row, int facing, int damage, int speed){
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.speed = speed;
        this.damage = damage;
        this.health = 1;
        isBullet = true;
        tookTurn = true;
        icon = 'b';
    }

    public void doTurn(){
        doMove();
        tookTurn = true;
    }

    public void doMove(){
        for(int i = 0; i < speed; i++){
            if(!map.move(this)){
                doShoot();
            }
        }
    }

    public void doShoot(){
        if(moveFacing == 0){
            if(col > 0){
                map.getAtLoc(col-1, row).doHit(damage);
            }
        }
        if(moveFacing == 1){
            if(row < map.rows-1 ){
                map.getAtLoc(col, row+1).doHit(damage);
            }
        }
        if(moveFacing == 2){
            if(col < map.columns-1){
                map.getAtLoc(col+1, row).doHit(damage);
            }
        }
        if(moveFacing == 3){
            if(row > 0){
                map.getAtLoc(col, row-1).doHit(damage);
            }
        }
        doDie();
    }
}