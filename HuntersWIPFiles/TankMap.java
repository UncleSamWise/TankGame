package test;

public class TankMap {
    int columns;
    int rows;
    Enemy[][] grid;

    public TankMap(int col, int row){
        columns = col;
        rows = row;
        grid = new Enemy[col][row];
        //populate the grid with walls
        /*
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                if(maze[i][j] != 0){
                    this.addEnemy(new Wall(this, i, j, 0));
                }
            }
        }
        */
    }

    public Enemy getAtLoc(int col, int row){
        return grid[col][row];
    }

    public void addEnemy(Enemy enemy){
        grid[enemy.col][enemy.row] = enemy;
    }

    public void setAtLoc(Enemy enemy, int col, int row){
        grid[col][row] = enemy;
    }

    public void doTurns(){
        for(Enemy[] column : grid){
            for(Enemy current : column){
                if(current != null){
                    current.tookTurn = false;
                }
            }
        }
        for(Enemy[] column : grid){
            for(Enemy current : column){
                if(current != null && !current.tookTurn && current.isBullet){
                    current.doTurn();
                }
            }
        }
        for(Enemy[] column : grid){
            for(Enemy current : column){
                if(current != null && !current.tookTurn){
                    current.doTurn();
                }
            }
        }
    }

    //Moves 1 tile forward, returns true if move is valid, otherwise false
    public boolean move(Enemy enemy){
        int facing = enemy.facing;
        int row = enemy.row;
        int col = enemy.col;
        if(facing == 0){
            if(col > 0 && grid[col-1][row] == null){
                grid[col-1][row] = enemy;
                grid[col][row] = null;
                enemy.col = enemy.col-1;
            } else return false;
        }
        if(facing == 1){
            if(row < rows-1 && grid[col][row+1] == null){
                grid[col][row+1] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.row+1;
            } else return false;
        }
        if(facing == 2){
            if(col < columns - 1 && grid[col+1][row] == null){
                grid[col+1][row] = enemy;
                grid[col][row] = null;
                enemy.col = enemy.col+1;
            } else return false;
        }
        if(facing == 3){
            if(row > 0 && grid[col][row-1] == null){
                grid[col][row-1] = enemy;
                grid[col][row] = null;
                enemy.row = enemy.row-1;
            } else return false;
        }
        return true;
    }

    //Spawn bullet 1 tile in front, returns true if valid, otherwise false
    public boolean shoot(Enemy enemy, int damage, int speed){
        int facing = enemy.facing;
        int row = enemy.row;
        int col = enemy.col;
        if(facing == 0){
            if(col > 0 && grid[col-1][row] == null){
                addEnemy(new Bullet(this, col-1, row, facing, damage, speed));
            } else return false;
        }
        if(facing == 1){
            if(row < rows-1 && grid[col][row+1] == null){
                addEnemy(new Bullet(this, col, row+1, facing, damage, speed));
            } else return false;
        }
        if(facing == 2){
            if(col < columns-1 && grid[col+1][row] == null){
                addEnemy(new Bullet(this, col+1, row, facing, damage, speed));
            } else return false;
        }
        if(facing == 3){
            if(row > 0 && grid[col][row-1] == null){
                addEnemy(new Bullet(this, col, row-1, facing, damage, speed));
            } else return false;
        }
        return true;
    }

    public void render(){
        System.out.println("____________________________");
        for(int i = 0; i < grid.length; i++){
            for(int ii = 0; ii < grid[0].length; ii++){
                if(grid[i][ii] == null){
                    System.out.print(" ");
                } else {
                    System.out.print(grid[i][ii]);
                }
            }
            System.out.println();
        }
        System.out.println("____________________________");
    }

}
