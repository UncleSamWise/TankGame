package Files;
//Bullet can be used for both enemy shots and player shots
public class Bullet extends Enemy{

    private int movespeed; // Tiles per tick
    private int damage; // Healthpoints damage

    public Bullet(Enemy[][] grid, int col, int row, int facing, int movespeed, int damage){
        this.grid = grid;
        this.col = col;
        this.row = row;
        this.facing = facing;
        this.movespeed = movespeed;
        this.damage = damage;
        this.health = 1;
        icon = 'B';
    }

    public void doTurn(){
        doMove();
    }

    public void doMove(){
        for(int i = 0; i < movespeed; i++){
            if(!Movement.move(this, grid, facing, col, row)){
                doShoot();
            }
        }
    }

    public void doShoot(){
        if(facing == 0){
            if(row > 0){
                grid[col][row-1].doHit(damage);
            }
        }
        if(facing == 1){
            if(col < grid.length-1){
                grid[col+1][row].doHit(damage);
            }
        }
        if(facing == 2){
            if(row < grid[0].length){
                grid[col][row+1].doHit(damage);
            }
        }
        if(facing == 3){
            if(col > 0){
                grid[col-1][row].doHit(damage);
            }
        }
        doDie();
    }
}