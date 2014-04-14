package me.tuxicorp.Game.level;



import java.util.Random;
import me.tuxicorp.Game.Main;
import org.newdawn.slick.Image;

public class LevelGeneration {
    //Yo BRO
    Random random = new Random();
    private final int stoneDensity = 200;
    private final int flowerDensity = 400;
    private final int waterDensity = 1400;
    public int[] tiles;
    
    private final int mapWidth;
    private final int mapHeight;
    private float offsetX;
    private float offsetY;
    
    public LevelGeneration(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        tiles = new int[mapWidth * mapHeight];
    }
    
    public void levelGenerator() {
        int waterTileCount = 0;
        int[] waterTile = new int[mapWidth * mapHeight];
        
        for(int x = 0; x < mapWidth; x++) {
            for(int y = 0; y < mapHeight; y++) {
                int r = random.nextInt(1500);
                tiles[x + y * mapWidth] = r;
                //Yes
                if (tiles[x + y * mapWidth] == waterDensity) {
                    waterTile[waterTileCount] = x + y * mapWidth;
                    waterTileCount++;
                }
            }
        }
        
        //Water
        for (int i0 = 0; i0 < 50; i0++) {
            if (tiles[waterTile[i0]] == waterDensity) {
                Random random2 = new Random();
                Random random3 = new Random();
                int r2 = random2.nextInt(19);
                int r3 = random3.nextInt(25);
                if (r2 < 5) {
                    r2 = random2.nextInt(20);
                }

                if (r3 < 10) {
                    r3 = random3.nextInt(25);
                }

                int num = 0;

                for (int i = 0; i < r2; i++) {
                    for (int ii = 0; ii < r3; ii++) {
                        if (waterTile[i0] + num + i + ii < mapWidth * mapHeight) {
                            tiles[waterTile[i0] + num + i + ii] = waterDensity;
                        }
                    }
                    num += 25;
                }
            }
        }
        
        tiles[mapWidth * mapHeight/2] = 1500;
        tiles[mapWidth * mapHeight/2+1] = 1500;
        tiles[mapWidth * mapHeight/2+25] = 1500;
        tiles[mapWidth * mapHeight/2+26] = 1500;
    }
    
    public void render(float offsetX, float offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        
        for(int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if (tiles[x + y * mapWidth] <= stoneDensity) {
                    Main.stone.draw(x * 32 + offsetX, y * 32 + offsetY);
                } else if (tiles[x + y * mapWidth] > stoneDensity && tiles[x + y * mapWidth] < flowerDensity){
                    Main.grassFlower.draw(x * 32 + offsetX, y * 32 + offsetY);
                } else if (tiles[x + y * mapWidth] == waterDensity){
                    Main.water.draw(x * 32 + offsetX, y * 32 + offsetY);
                } else {
                    Main.grass.draw(x * 32 + offsetX, y * 32 + offsetY);
                }
            }
        }
    }
}
