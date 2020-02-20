import test.*;

public class Testing {

    public static void main(String[] args) {

        TankMap theMap = new TankMap(5, 5);
        theMap.addEnemy(new SpinningTurret(theMap,2, 0, 1));
        theMap.addEnemy(new SpinningTurret(theMap,0, 2, 2));
        theMap.addEnemy(new SpinningTurret(theMap,2, 4, 3));
        theMap.addEnemy(new SpinningTurret(theMap,4, 2, 0));
        theMap.render();
        for(int i = 0; i < 25; i++) {
            theMap.doTurns();
            theMap.render();
        }


    }

}
