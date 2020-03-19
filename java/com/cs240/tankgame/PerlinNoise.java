package com.cs240.tankgame;

import android.graphics.Bitmap;

import java.util.Random;

public class PerlinNoise {
    /** Source of entropy */
    private Random rand;

    /** Amount of roughness */
    private float roughness;

    /** Plasma fractal grid */
    private float[][] grid;


    /** Generate a noise source based upon the midpoint displacement fractal.
     *
     * @param rand The random number generator
     * @param roughness a roughness parameter
     * @param width the width of the grid
     * @param height the height of the grid
     */
    public PerlinNoise(Random rand, float roughness, int width, int height) {
        this.roughness = roughness / width;
        this.grid = new float[width][height];
        this.rand = (rand == null) ? new Random() : rand;
    }


    public void initialise() {
        int xh = grid.length - 1;
        int yh = grid[0].length - 1;

        // set the corner points
        grid[0][0] = rand.nextFloat() - 0.5f;
        grid[0][yh] = rand.nextFloat() - 0.5f;
        grid[xh][0] = rand.nextFloat() - 0.5f;
        grid[xh][yh] = rand.nextFloat() - 0.5f;

        // generate the fractal
        generate(0, 0, xh, yh);
    }


    // Add a suitable amount of random displacement to a point
    private float roughen(float v, int l, int h) {
        return v + roughness * (float) (rand.nextGaussian() * (h - l));
    }


    // generate the fractal
    private void generate(int xl, int yl, int xh, int yh) {
        int xm = (xl + xh) / 2;
        int ym = (yl + yh) / 2;
        if ((xl == xm) && (yl == ym)) return;

        grid[xm][yl] = 0.5f * (grid[xl][yl] + grid[xh][yl]);
        grid[xm][yh] = 0.5f * (grid[xl][yh] + grid[xh][yh]);
        grid[xl][ym] = 0.5f * (grid[xl][yl] + grid[xl][yh]);
        grid[xh][ym] = 0.5f * (grid[xh][yl] + grid[xh][yh]);

        float v = roughen(0.5f * (grid[xm][yl] + grid[xm][yh]), xl + yl, yh
                + xh);
        grid[xm][ym] = v;
        grid[xm][yl] = roughen(grid[xm][yl], xl, xh);
        grid[xm][yh] = roughen(grid[xm][yh], xl, xh);
        grid[xl][ym] = roughen(grid[xl][ym], yl, yh);
        grid[xh][ym] = roughen(grid[xh][ym], yl, yh);

        generate(xl, yl, xm, ym);
        generate(xm, yl, xh, ym);
        generate(xl, ym, xm, yh);
        generate(xm, ym, xh, yh);
    }


    /**
     * Print out
     */
    public void print() {
        for(int i = 0;i < grid.length;i++) {
            for(int j = 0;j < grid[0].length;j++) {
                
                /*if((i >= (grid.length / 2) - 1 && i <= (grid.length / 2) + 1) || (j >= (grid.length / 2) - 1 && j <= (grid.length / 2) + 1)) {
                  System.out.print("UU");
                }else */if(grid[i][j] > 0.2 && grid[i][j] < 0.5) {
                  System.out.print("++");
                }else if(grid[i][j] >= 0.5){
                  System.out.print("00");
                } else if(grid[i][j] <= 0.2 && grid[i][j] > -1.3){
                  System.out.print("||");
                }else{
                  System.out.print("WW");
                }
            }
            System.out.println();
        }
    }
//    public float[][] returnFinalNoise() {
//        boolean isContinue = true;
//        while(isContinue){
//            initialise();
//            boolean isBadWall = false;
//            for(int i = 0; i < grid.length; i++) {
//                for(int j = 0; j < grid[0].length; j++) {
//                    if((i >= (grid.length / 2) - 1 && i <= (grid.length / 2) + 1) /*|| (j >= (grid.length / 2) - 1 && j <= (grid.length / 2) + 1)*/)  {
//                        isBadWall = true;
//                        break;
//                    }
//                }
//                if(isBadWall) {
//                    break;
//                }
//            }
//            if(!isBadWall){
//                isContinue = false;
//            }
//        }
//        return grid;
//    }
    //convert float grid to new int[][] array
    //0 = floor, 1 = wall, 2 = wall variant
    public float[][] returnGrid(){
        return grid;
        /*int w = grid.length;
        int h = grid[0].length;
        Enemy[][] newGrid = new Enemy[w][h];

        for(int i = 0;i < grid.length;i++) {
            for(int j = 0;j < grid[0].length;j++) {

                /*if((i >= (grid.length / 2) - 1 && i <= (grid.length / 2) + 1) || (j >= (grid.length / 2) - 1 && j <= (grid.length / 2) + 1)) {
                  System.out.print("UU");
                }else
//                if(grid[i][j] > 0.2 && grid[i][j] < 0.5) {
//                    System.out.print("++");
//                }else
                if(grid[i][j] >= 0.5){
                    theMap.addEnemy(new Wall(theMap, i, j, bmp[1]));
                } else /*if(grid[i][j] <= 0.2 && grid[i][j] > -1.3){
                    //newGrid[i][j] = 0;
                }
//                else{
//                    System.out.print("WW");
//                }
            }
        }

        return newGrid:
         */

    }


    /**
     * Convert to a Boolean array
     * @return the boolean array
     */
    public boolean[][] toBooleans() {
        int w = grid.length;
        int h = grid[0].length;
        boolean[][] ret = new boolean[w][h];
        for(int i = 0;i < w;i++) {
            for(int j = 0;j < h;j++) {
                ret[i][j] = grid[i][j] < 0;
            }
        }
        return ret;
    }


    /** For testing */
//    public static void main(String[] args) {
//        PerlinNoise n = new PerlinNoise(null, 1.0f, 20, 20);
//        n.initialise();
//        int[][] arr = n.returnGrid();
//    }
}