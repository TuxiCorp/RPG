package me.tuxicorp.Game;

import me.tuxicorp.Game.level.LevelGeneration;
import java.io.*;
import org.newdawn.slick.*;
import java.util.Random;

public class Main extends BasicGame { 
    
    public static int width = 800;
    public static int height = 608;
    public static boolean fullscreen = false;
    public static boolean showFPS = true;
    public static String title = "RPG";
    public static int fpslimit = 60;
    
    public static int mapWidth = 50;
    public static int mapHeight = 50;
    
    public static Image grass, stone, character, grassFlower, water;
    Random random = new Random();
    LevelGeneration lg = new LevelGeneration(mapWidth, mapHeight);
    
    private final int charPosX = width / 2;
    private final int charPosY = height / 2;
    
    public static int offsetX = 0;
    public static int offsetY = 0;
    public int offsetSpeed = 3;
    
    public Main(String title) {
        super(title);
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        lg.levelGenerator();
        grass = new Image("res/grass.png");
        stone = new Image("res/stone.png");
        character = new Image("res/character.png");
        grassFlower = new Image("res/grass_flower.png");
        water = new Image("res/water.png");
    }   
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input;
        input = gc.getInput();
        
        //Input
        if (input.isKeyDown(Input.KEY_UP)) {
            offsetY += offsetSpeed;
        }
        
        if (input.isKeyDown(Input.KEY_DOWN)) {
            offsetY -= offsetSpeed;
        }
        
        if (input.isKeyDown(Input.KEY_LEFT)) {
            offsetX += offsetSpeed;
        }
        
        if (input.isKeyDown(Input.KEY_RIGHT)) {
            offsetX -= offsetSpeed;
        }
        
        if (input.isKeyPressed(Input.KEY_R)) {
            lg.levelGenerator();
        }
        int tilePosX = offsetX / mapWidth;
        int tilePosY = offsetY / mapWidth;
        System.out.println((tilePosX + tilePosY * mapWidth) / 2);
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        lg.render(offsetX, offsetY);
        
        character.draw(charPosX, charPosY);
    }
    
    public static void main(String[] args) throws SlickException, IOException {
        AppGameContainer app = new AppGameContainer(new Main(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.setAlwaysRender(true);
        app.start();
    }
}