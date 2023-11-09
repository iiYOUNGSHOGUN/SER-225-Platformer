package Maps;

import java.util.ArrayList;

import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import NPCs.Walrus;
import NPCs.Shop;
import Tilesets.TestTileset;
import Tilesets.ForestTileset;
import Tilesets.LabTileset;
import Utils.Direction;
import Enemies.DinosaurEnemy;
import Enemies.DogEnemy;
import Enemies.Hunter;
import Enemies.PrisonGuardEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import GameObject.Coin;
import Tilesets.ForestTileset;

public class Lab_copy extends Map {

        public Lab_copy() {
                super("lab_copy.txt", new ForestTileset());
                this.playerStartPosition = getMapTile(1, 47).getLocation();
        }

        // Enahnced Map Tile Setup
        @Override
        public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
                ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

                HorizontalMovingPlatform hmp = new HorizontalMovingPlatform(
                                ImageLoader.load("GreenPlatform.png"),
                                getMapTile(37, 21).getLocation(),
                                getMapTile(34, 21).getLocation(),
                                TileType.JUMP_THROUGH_PLATFORM,
                                3,
                                new Rectangle(0, 6, 16, 4),
                                Direction.LEFT);
                enhancedMapTiles.add(hmp);

                EndLevelBox endLevelBox = new EndLevelBox(getMapTile(99, 12).getLocation());
                enhancedMapTiles.add(endLevelBox);

                return enhancedMapTiles;
        }

        @Override
        public ArrayList<Enemy> loadEnemies() {
                ArrayList<Enemy> enemies = new ArrayList<>();

                DogEnemy DogEnemy_1 = new DogEnemy(getMapTile(18,
                                9).getLocation().subtractY(25), Direction.LEFT);
                enemies.add(DogEnemy_1);

                PrisonGuardEnemy Guard_1 = new PrisonGuardEnemy(getMapTile(11, 45).getLocation().subtractY(75),
                                getMapTile(17, 45).getLocation().subtractY(75), Direction.RIGHT);
                enemies.add(Guard_1);

                PrisonGuardEnemy Guard_2 = new PrisonGuardEnemy(getMapTile(26, 42).getLocation().subtractY(75),
                                getMapTile(31, 42).getLocation().subtractY(75), Direction.RIGHT);
                enemies.add(Guard_2);

                PrisonGuardEnemy Guard_3 = new PrisonGuardEnemy(getMapTile(42, 44).getLocation().subtractY(75),
                                getMapTile(45, 44).getLocation().subtractY(75), Direction.RIGHT);
                enemies.add(Guard_3);

                PrisonGuardEnemy Guard_4 = new PrisonGuardEnemy(getMapTile(62, 43).getLocation().subtractY(75),
                                getMapTile(65, 43).getLocation().subtractY(75), Direction.RIGHT);
                enemies.add(Guard_4);

                PrisonGuardEnemy Guard_5 = new PrisonGuardEnemy(getMapTile(67, 43).getLocation().subtractY(75),
                                getMapTile(70, 43).getLocation().subtractY(75), Direction.RIGHT);
                enemies.add(Guard_5);

                Hunter Hunter_1 = new Hunter(getMapTile(30, 25).getLocation().subtractY(75),
                                getMapTile(38, 25).getLocation().subtractY(75), Direction.RIGHT);
                enemies.add(Hunter_1);

                Hunter Hunter_2 = new Hunter(getMapTile(50, 15).getLocation().subtractY(75),
                                getMapTile(53, 15).getLocation().subtractY(75), Direction.RIGHT);
                enemies.add(Hunter_2);

                Hunter Hunter_3 = new Hunter(getMapTile(86, 12).getLocation().subtractY(75),
                                getMapTile(99, 12).getLocation().subtractY(75), Direction.RIGHT);
                enemies.add(Hunter_3);

                /*
                 * DinosaurEnemy dinosaurEnemy = new DinosaurEnemy(getMapTile(19,
                 * 1).getLocation().addY(2), getMapTile(22, 1).getLocation().addY(2),
                 * Direction.RIGHT);
                 * enemies.add(dinosaurEnemy);
                 */

                return enemies;
        }

}
