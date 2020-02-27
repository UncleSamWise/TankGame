package test;

import java.util.Random;

public class RandomTank extends Enemy {

    private Random randomNum = new Random();

    //Constructor
    public RandomTank(TankMap map, int col, int row, int facing) {
        this.map = map;
        this.col = col;
        this.row = row;
        this.moveFacing = facing;
        this.fireFacing = facing;
        this.health = 5;
        tookTurn = true;
        icon = 'R';
        isBullet = false;
    }

    public void doTurn(){
        if(randomNum.nextBoolean()) doMove();
        else doShoot();
        tookTurn = true;
    }

    public void doMove(){
        moveFacing = randomNum.nextInt(4);
        if(!map.move(this)){
            moveFacing = randomNum.nextInt(4);
            map.move(this);
        }
    }

    public void doShoot() {
        fireFacing = randomNum.nextInt(4);
        if(!map.shoot(this, 1, 1)){
            fireFacing = randomNum.nextInt(4);
            map.shoot(this, 1, 1);
        }
    }
}