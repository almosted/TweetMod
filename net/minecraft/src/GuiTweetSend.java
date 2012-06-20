package net.minecraft.src;

import java.awt.Image;

import javax.swing.ImageIcon;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class GuiTweetSend extends Gui
{
    /** Holds the instance of the game (Minecraft) */
    private Minecraft theGame;

    /** Holds the latest width scaled to fit the game window. */
    private int achievementWindowWidth;

    /** Holds the latest height scaled to fit the game window. */
    private int achievementWindowHeight;
    private static String tweetSend;
    private static String tweet;

    /** Holds the achievement that will be displayed on the GUI. */
    private Achievement theAchievement;
    private Image itemRender = new ImageIcon(this.getClass().getResource("/gui/twitter.png")).getImage();;
    private static long tweetTime;

    public GuiTweetSend(Minecraft par1Minecraft)
    {
        theGame = par1Minecraft;
    }

    /**
     * Queue a taken achievement to be displayed.
     */
    public static void queueTakenAchievement(String s)
    {
        tweetSend = "Tweet send!";
        tweet = s;
        tweetTime = System.currentTimeMillis();
        System.out.println(tweetSend + " " + tweet);
        //theAchievement = par1Achievement;
    }

    /**
     * Update the display of the achievement window to match the game window.
     */
    private void updateAchievementWindowScale()
    {
        GL11.glViewport(0, 0, theGame.displayWidth, theGame.displayHeight);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        achievementWindowWidth = theGame.displayWidth;
        achievementWindowHeight = theGame.displayHeight;
        ScaledResolution scaledresolution = new ScaledResolution(theGame.gameSettings, theGame.displayWidth, theGame.displayHeight);
        achievementWindowWidth = scaledresolution.getScaledWidth();
        achievementWindowHeight = scaledresolution.getScaledHeight();
        GL11.glClear(256);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, achievementWindowWidth, achievementWindowHeight, 0.0D, 1000D, 3000D);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000F);
    }

    /**
     * Updates the small achievement tooltip window, showing a queued achievement if is needed.
     */
    public void updateAchievementWindow()
    {
        double d = (double)(System.currentTimeMillis() - tweetTime) / 3000D;

        updateAchievementWindowScale();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        double d1 = d * 2D;

        if (d1 > 1.0D)
        {
            d1 = 2D - d1;
        }

        d1 *= 4D;
        d1 = 1.0D - d1;

        if (d1 < 0.0D)
        {
            d1 = 0.0D;
        }

        d1 *= d1;
        d1 *= d1;
        int i = achievementWindowWidth - 160;
        int j = 0 - (int)(d1 * 36D);
        int k = theGame.renderEngine.getTexture("/achievement/bg.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, k);
        GL11.glDisable(GL11.GL_LIGHTING);
        drawTexturedModalRect(i, j, 96, 202, 160, 32);
        theGame.fontRenderer.drawString(tweetSend, i + 30, j + 7, -256);
        theGame.fontRenderer.drawString(tweet, i + 30, j + 18, -1);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glEnable(GL11.GL_LIGHTING);
       //((Object) itemRender).renderItemIntoGUI(theGame.fontRenderer, theGame.renderEngine, theAchievement.theItemStack, i + 8, j + 8);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }
}
