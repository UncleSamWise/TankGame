package Files;
public class SpinningTurret  extends Enemy {

    private boolean tickerFire = true;

    //Constructor
    public SpinningTurret(Enemy[][] grid, int col, int row, int facing) {
        this.grid = grid;
        this.col = col;
        this.row = row;
        this.facing = facing;
        this.health = 1;
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
    }

    //Rotate clockwise
    public void doMove(){
        if(facing < 3){
            facing++;
        } else facing = 0;
    }

    //Fire
    public void doShoot() {
        if (!Movement.shoot(grid, col, row, facing, 1, 1)) {
            doMove();
        }
    }
}