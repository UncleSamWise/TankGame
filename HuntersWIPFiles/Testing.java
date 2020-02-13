import Files.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Testing {

    public static void main(String[] args) {

        Enemy[][] grid = new Enemy[11][11];
        grid[5][5] = new SpinningTurret(grid, 5, 5, 0);
        grid[0][0] = new Wall(grid, 0, 0, 0);
        grid[10][0] = new Wall(grid, 10, 0, 0);
        grid[0][10] = new Wall(grid, 0, 10, 0);
        grid[10][10] = new Wall(grid, 10, 10, 0);

        doTurns(grid);
        render(grid);
        doTurns(grid);
        render(grid);
        doTurns(grid);
        render(grid);
        doTurns(grid);
        render(grid);
        doTurns(grid);
        render(grid);
        doTurns(grid);
        render(grid);
        doTurns(grid);
        render(grid);
        doTurns(grid);
        render(grid);




    }

    public static void doTurns(Enemy[][] grid){
        for(Enemy[] array : grid){
            for(Enemy out : array){
                if(out != null) {
                    out.doTurn();
                }
            }
        }
    }


    public static void render(Enemy[][] grid){
        for(Enemy[] array : grid){
            for(Enemy out : array){
                if(out != null) {
                    System.out.print(out);
                }
                else System.out.print(" ");
            }
            System.out.println();
        }
    }
}
