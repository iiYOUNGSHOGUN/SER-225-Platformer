package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.Cutscene2Map;
import SpriteFont.SpriteFont;

import java.awt.*;

// This is the class for the main menu screen
public class CutsceneScreen2 extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected SpriteFont narration;
    protected SpriteFont prompt;
    protected Map background;
    protected int keyPressTimer;
    protected KeyLocker keyLocker = new KeyLocker();

    public CutsceneScreen2(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        background = new Cutscene2Map();
        background.setAdjustCamera(false);
        //fun fact if you're trying to remember why \n isn't working: use the other draw command it has one that works with \n
        narration = new SpriteFont("Welcome, [currentSubject.getID]! From now,\nyour objective is to [ESCAPE] and reach the\n[Error: 'end' not found]!", 175, 24, "Helvetica Bold", 22, Color.white);
        prompt = new SpriteFont("Press SPACE to continue...", 490, 130, "Comic Sans", 15, Color.white);
        keyPressTimer = 0;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);


        
        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
           screenCoordinator.setGameState((GameState.CUTSCENE3));
           
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        narration.drawWithParsedNewLines(graphicsHandler, 3);
        prompt.draw(graphicsHandler);
    }
}
