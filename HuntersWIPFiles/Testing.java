import test.*;

public class Testing {

    public static void main(String[] args) {

        TankMap theMap = new TankMap(8, 8);
        theMap.addEnemy(new SpinningTurret(theMap,4, 4, 0));
        theMap.addEnemy(new SpinningTurret(theMap, 0, 4, 0));
        theMap.render();
        for(int i = 0; i < 25; i++) {
            theMap.doTurns();
            theMap.render();
        }


    }

}
