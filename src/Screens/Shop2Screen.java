package Screens;

import Engine.*;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.Shop2ScreenMap;
import SpriteFont.SpriteFont;
import Level.Player;
import java.awt.*;

// This is the class for the shop screen
public class Shop2Screen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont speedUp;
    protected SpriteFont exit;
    protected SpriteFont speedUpText;
    protected boolean speedUpPurchased;
    protected SpriteFont healthUp;
    protected SpriteFont healthUpText;
    protected boolean healthUpPurchased;
    protected SpriteFont shield;
    protected SpriteFont shieldText;
    protected boolean shieldPurchased;
    protected SpriteFont exitText;
    protected SpriteFont purchasedText;
    protected SpriteFont coinCount;
    protected SpriteFont notEnough;
    protected int coins;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();
    protected Player player;
    protected PlayLevelScreen playLevelScreen;
    



    public Shop2Screen(PlayLevelScreen playLevelScreen) {
        this.playLevelScreen = playLevelScreen;
        initialize();
    }


    @Override
    public void initialize() {
        //player for access coin count
        this.player = playLevelScreen.player;
        //text and icons for shop items
        speedUp = new SpriteFont("Speed up (30)", 97, 280, "Comic Sans", 25, new Color(0, 0, 0));
        speedUp.setOutlineColor(Color.black);
        speedUp.setOutlineThickness(3);

        healthUp = new SpriteFont("Health up (20)", 310, 280, "Comic Sans", 25, new Color(0, 0, 0));
        healthUp.setOutlineColor(Color.black);
        healthUp.setOutlineThickness(3);
        //marks if item is purchased so  prevents doubles
        speedUpPurchased = false;
        healthUpPurchased = false;
        //items not yet in shop
        shield = new SpriteFont("Shield (25)", 533, 280, "Comic Sans", 25, new Color(0, 0, 0));
        shield.setOutlineColor(Color.black);
        shield.setOutlineThickness(3);
        //leave screen text
        exit = new SpriteFont("Leave", 675, 300, "Comic Sans", 25, new Color(0, 0, 0));
        exit.setOutlineColor(Color.black);
        exit.setOutlineThickness(3);
        //max's text about the item
        speedUpText = new SpriteFont("\"Energy drink.\" Why is that in quotations?\n[Increases Speed]", 180, 400, "Comic Sans", 25, new Color(255, 255, 255));
        healthUpText = new SpriteFont("\"Health drink.\" That's not suspicious at all.\n[+1 Life]", 180, 400, "Comic Sans", 25, new Color(255, 255, 255));
        shieldText = new SpriteFont("\"Protective drink.\" This is going to give me\nradiation poisoning isn't it?\n[+1 Shield]", 180, 400, "Comic Sans", 25, new Color(255, 255, 255));
        exitText = new SpriteFont("I don't want anything.", 180, 400, "Comic Sans", 25, new Color(255, 255, 255));
        purchasedText = new SpriteFont("I guess this is mine now.", 180, 400, "Comic Sans", 25, new Color(255, 255, 255));
        speedUpText.setOutlineColor(Color.black);
        speedUpText.setOutlineThickness(3);
        notEnough = new SpriteFont("Don't have enough coins.", 180, 400, "Comic Sans", 25, new Color(255,255,255));
        //coint counter in the shop
        coins = player.getCoins();
        coinCount = new SpriteFont(("Coins: " + coins), 20, 20, "Comic Sans", 30, new Color(0,0,0));

        background = new Shop2ScreenMap();
        background.setAdjustCamera(false);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.D) &&  keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.A) &&  keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        coinCount.setText("Coins: " + player.getCoins());

        // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 3) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 2;
        }

        // sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            if(speedUpPurchased == false) {
            speedUp.setColor(new Color(255, 255, 255));
            }
            else {
                speedUp.setColor(new Color (255, 0, 0));
            }
            exit.setColor(new Color(0, 0, 0));
            healthUp.setColor(new Color(0,0,0));
            shield.setColor(new Color(0,0,0));
            pointerLocationX = 470;
            pointerLocationY = 130;
        } else if (currentMenuItemHovered == 1) {
            speedUp.setColor(new Color(0, 0, 0));
            if(healthUpPurchased == false) {
            healthUp.setColor(new Color(255, 255, 255));
            }
            else {
                healthUp.setColor(new Color (255, 0, 0));
            }
            shield.setColor(new Color(0,0,0));
            exit.setColor(new Color(0, 0, 0));
            pointerLocationX = 470;
            pointerLocationY = 230;
        } else if (currentMenuItemHovered == 2) {
            speedUp.setColor(new Color(0,0,0));
            healthUp.setColor(new Color(0,0,0));
            if(shieldPurchased == false) {
            shield.setColor(new Color(255, 255, 255));
            }
            else {
                shield.setColor(new Color (255, 0, 0));
            }
            exit.setColor(new Color(0, 0, 0));
        } else if (currentMenuItemHovered == 3) {
            speedUp.setColor(new Color(0,0,0));
            healthUp.setColor(new Color(0,0,0));
            shield.setColor(new Color(0,0,0));
            exit.setColor(new Color(255,255,255));
        }

        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE) && keyPressTimer == 0) {
            keyPressTimer = 14;
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                if (this.player.getCoins() >= 30 && !speedUpPurchased) {
                    player.removeCoins(30);
                    player.increaseSpeed(2.2f); // Increase speed
                    speedUpPurchased = true;
                }
                  
                //check for correct amt coins
                //remove coins
                //apply effect
                //mark purchased

               
               
                // Inside ShopScreen.java

            } else if (menuItemSelected == 1) {
                int costOfExtraLife = 20;      
                    if(this.player.getCoins() >= costOfExtraLife && !this.healthUpPurchased) {
                        this.player.removeCoins(costOfExtraLife); 
                        this.player.addExtraLife(); 
                        this.healthUpPurchased = true;
                    }            

                }
                else if (menuItemSelected == 2) {
                    int costOfShield = 25;
                    if (this.player.getCoins() >= costOfShield && !this.shieldPurchased) {
                        this.player.removeCoins(costOfShield);
                        this.player.activateShield(); // Activate shield
                        this.shieldPurchased = true;
                    }
                }


            } else if (menuItemSelected == 3) {
                playLevelScreen.goToCutscene2(player);
            }
        }
    

    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        speedUp.draw(graphicsHandler);
        healthUp.draw(graphicsHandler);
        shield.draw(graphicsHandler);
        //item4.draw(graphicsHandler);
        exit.draw(graphicsHandler);
        coinCount.draw(graphicsHandler);
        
        //the text for each item only appears when the item is hovered 
        menuItemSelected = currentMenuItemHovered;
        //if speed up hovered
        if(menuItemSelected == 0) {
            //message when not enough coins
            if(this.player.getCoins() < 30 && speedUpPurchased == false) {
                notEnough.draw(graphicsHandler);
            }
            //description of item
            else if(this.player.getCoins() >= 30 && speedUpPurchased == false) {
                speedUpText.drawWithParsedNewLines(graphicsHandler, 2);
            }
            //purchased text
            else if(speedUpPurchased == true) {
                purchasedText.draw(graphicsHandler);
            }
        }
        else if(menuItemSelected == 1) {
            //message when not enough coins
            if(this.player.getCoins() < 20 && healthUpPurchased == false) {
                notEnough.draw(graphicsHandler);
            }
            //description of item
            else if(this.player.getCoins() >= 20 && healthUpPurchased == false) {
                healthUpText.drawWithParsedNewLines(graphicsHandler, 2);
            }
            //purchased text
            else if(healthUpPurchased == true) {
                purchasedText.draw(graphicsHandler);
            }
        }
        else if(menuItemSelected == 2) {
            //message when not enough coins
            if(this.player.getCoins() < 25 && shieldPurchased == false) {
                notEnough.draw(graphicsHandler);
            }
            //description of item
            else if(this.player.getCoins() >= 25 && shieldPurchased == false) {
                shieldText.drawWithParsedNewLines(graphicsHandler, 2);
            }
            //purchased text
            else if(shieldPurchased == true) {
                purchasedText.draw(graphicsHandler);
            }
            
        }
        else if(menuItemSelected == 3) {
            exitText.draw(graphicsHandler);
        }
    }
}