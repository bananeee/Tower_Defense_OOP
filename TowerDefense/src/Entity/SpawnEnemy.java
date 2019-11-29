package Entity;

import Database.Config;
import Database.Data;
import Entity.EnemyPackage.BossEnemy;
import Entity.EnemyPackage.NormalEnemy;
import Entity.EnemyPackage.SmallerEnemy;
import Entity.EnemyPackage.TankerEnemy;

import java.util.Random;

public class SpawnEnemy extends GameEntity {

    int id = 0;

    public void onUpdate() {

        if (tickCount % 90 == 0) {
            if (id == 100)
                id = 0;

//            if (tickCount <= 1000) {
//
//                Random genarator = new Random();
//
//                int value = genarator.nextInt(4);
//
//                //System.out.println(value);
//                if (value <= 3)
//                    Data.enemiesList.add(new NormalEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));
//                else
//                        Data.enemiesList.add(new SmallerEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));
//            } else if (tickCount <= 2000) {
//
//                Random genarator = new Random();
//
//                int value = genarator.nextInt(7);
//
//                if (value <= 4)
//                    Data.enemiesList.add(new NormalEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));
//                else if (value <= 6)
//                Data.enemiesList.add(new SmallerEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));
//                else
//                Data.enemiesList.add(new TankerEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));
//            } else {
                Random genarator = new Random();

                int value = genarator.nextInt(10);

                if (value <= 3)
                    Data.enemiesList.add(new NormalEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));
                else if (value <= 5)
                Data.enemiesList.add(new SmallerEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));
                else if (value <= 7)
                Data.enemiesList.add(new TankerEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));
                else if (value <= 10)
                Data.enemiesList.add(new BossEnemy(Config.SPAWN_COORDINATE[0], Config.SPAWN_COORDINATE[1], id));

            //}
            id++;
        }
    }
}



