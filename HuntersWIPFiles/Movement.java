package Files;
public class Movement{

    //Moves 1 tile forward, returns true if move is valid, otherwise false
    public static boolean move(Enemy enemy, Enemy[][] grid, int facing, int col, int row){
        if(facing == 0){
            if(row > 0 && grid[col][row-1] == null){
                grid[col][row-1] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.row-1;
            } else return false;
        }
        if(facing == 1){
            if(col < grid.length-1 && grid[col+1][row] == null){
                grid[col+1][row] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.col+1;
            } else return false;
        }
        if(facing == 2){
            if(row < grid[0].length && grid[col][row+1] == null){
                grid[col][row+1] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.row+1;
            } else return false;
        }
        if(facing == 3){
            if(col > 0 && grid[col-1][row] == null){
                grid[col-1][row] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.col-1;
            } else return false;
        }
        return true;
    }

    //Spawn bullet 1 tile in front, returns true if valid, otherwise false
    public static boolean shoot(Enemy[][] grid, int col, int row, int facing, int speed, int damage){
        if(facing == 0){
            if(row > 0 && grid[col][row-1] == null){
                grid[col][row-1] = new Bullet(grid, col, row - 1, facing, speed, damage);
            } else return false;
        }
        if(facing == 1){
            if(col < grid.length-1 && grid[col+1][row] == null){
                grid[col+1][row] = new Bullet(grid, col + 1, row, facing, speed, damage);
            } else return false;
        }
        if(facing == 2){
            if(row < grid[0].length && grid[col][row+1] == null){
                grid[col][row+1] = new Bullet(grid, col, row + 1, facing, speed, damage);
            } else return false;
        }
        if(facing == 3){
            if(col > 0 && grid[col-1][row] == null){
                grid[col-1][row] = new Bullet(grid, col - 1, row, facing, speed, damage);
            } else return false;
        }
        return true;
    }

}