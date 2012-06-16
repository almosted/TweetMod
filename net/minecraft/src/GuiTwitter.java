package net.minecraft.src;

import java.lang.reflect.Method;
import java.util.*;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiTwitter extends GuiScreen
{
    private String field_50062_b;
    private int field_50063_c;
    private boolean field_50060_d;
    private String field_50061_e;
    private String field_50059_f;
    private int field_50067_h;
    private List field_50068_i;
    protected GuiTextField field_50064_a;
    private String field_50066_k;
	private int xSize = 270, ySize = 275;
	
    public GuiTwitter()
    {
        field_50062_b = "";
        field_50063_c = -1;
        field_50060_d = false;
        field_50061_e = "";
        field_50059_f = "";
        field_50067_h = 0;
        field_50068_i = new ArrayList();
        field_50066_k = "";
    }

    public void initGui()
    {
        Keyboard.enableRepeatEvents(true);
        field_50063_c = mc.ingameGUI.func_50013_c().size();
        int j = (width - xSize)/2;
        int k = (height - ySize)/2;
        field_50064_a = new GuiTextField(fontRenderer, 4, height - 12, width - 4, 12);
        field_50064_a.setMaxStringLength(140);
        field_50064_a.func_50027_a(false);
        field_50064_a.func_50033_b(true);
        field_50064_a.setText(field_50066_k);
        field_50064_a.func_50026_c(false);
        controlList.add(new GuiButton(0, width - 27, 2, 25, 20, "Exit"));
        controlList.add(new GuiButton(1, width - 79, 2, 50, 20, "Refresh"));
        if(TweetMod.infos == 0)
        {
        	controlList.add(new GuiButton(2, width - 131, 2, 50, 20, "Mentions"));
        	TweetMod.getTimelineTwitter();
        }
        else if(TweetMod.infos == 1)
        {
        	controlList.add(new GuiButton(2, width - 131, 2, 50, 20, "Timeline"));
        	TweetMod.getMentionTwitter();
        }
    }

    protected void actionPerformed(GuiButton par1GuiButton)
    {    
    	if (par1GuiButton.id == 0)
        {
        	mc.displayGuiScreen(null);
        }
    	if (par1GuiButton.id == 1)
        {
        	mc.displayGuiScreen(null);
        	mc.displayGuiScreen(new GuiTwitter());
        }
    	if (par1GuiButton.id == 2)
        {
            if(TweetMod.infos == 0)
            {
            	TweetMod.infos = 1;
            }
            else if(TweetMod.infos == 1)
            {
            	TweetMod.infos = 0;
            }
        	mc.displayGuiScreen(null);
        	mc.displayGuiScreen(new GuiTwitter());
        }
    }

    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
        mc.ingameGUI.func_50014_d();
    }

    public void updateScreen()
    {
        field_50064_a.updateCursorCounter();
    }
    
    protected void keyTyped(char par1, int par2)
    {

        field_50060_d = false;

        if (par2 == 1)
        {
            mc.displayGuiScreen(null);
        }
        else if (par2 == 28)
        {
            String s = field_50064_a.getText().trim();

            if (s.length() > 0 && !mc.lineIsCommand(s))
            {
            	TweetMod.tweet(s);
            	mc.displayGuiScreen(null);
            	mc.displayGuiScreen(new GuiTwitter());
            }

        }
        else if (par2 == 200)
        {
            func_50058_a(-1);
        }
        else if (par2 == 208)
        {
            func_50058_a(1);
        }
        else if (par2 == 201)
        {
            mc.ingameGUI.func_50011_a(19);
        }
        else if (par2 == 209)
        {
            mc.ingameGUI.func_50011_a(-19);
        }
        else
        {
            field_50064_a.func_50037_a(par1, par2);
        }
    }

    public void handleMouseInput()
    {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();

        if (i != 0)
        {
            if (i > 1)
            {
                i = 1;
            }

            if (i < -1)
            {
                i = -1;
            }

            if (!func_50049_m())
            {
                i *= 7;
            }

            mc.ingameGUI.func_50011_a(i);
        }
    }

    public void func_50058_a(int par1)
    {
        int i = field_50063_c + par1;
        int j = mc.ingameGUI.func_50013_c().size();

        if (i < 0)
        {
            i = 0;
        }

        if (i > j)
        {
            i = j;
        }

        if (i == field_50063_c)
        {
            return;
        }

        if (i == j)
        {
            field_50063_c = j;
            field_50064_a.setText(field_50062_b);
            return;
        }

        if (field_50063_c == j)
        {
            field_50062_b = field_50064_a.getText();
        }

        field_50064_a.setText((String)mc.ingameGUI.func_50013_c().get(i));
        field_50063_c = i;
    }
    
    protected void drawBackgroundImage()
    {
       int displayX = (width - xSize)/2;
       int displayY = (height - ySize)/2;
       int nk = mc.renderEngine.getTexture("/gui/twitter.png");
       mc.renderEngine.bindTexture(nk);
       drawTexturedModalRect(displayX + 15, height - 300, 0, 0, xSize, ySize);
    }

    public void drawScreen(int par1, int par2, float par3)
    {
        int j = (width - xSize) / 2;
        int k = (height - ySize) / 2;
        drawDefaultBackground();
        drawBackgroundImage();
        drawRect(0, height - 322, width, height, 0x80000000);
        drawRect(0, height - 306, width, height - 302, 0x80000000);
        drawRect(0, height - 306, width, height - 302, 0x80000000);
        drawRect(2, height - 14, width - 2, height - 2, 0x80000000);
        field_50064_a.drawTextBox();
        drawString(fontRenderer, "#TweetMod", 2, 2, 0x3366ff);
        drawCenteredString(fontRenderer, TweetMod.msg, width / 2, 2, 0xffffff);
        if (TweetMod.infos == 0)
        {
            drawString(fontRenderer, TweetMod.pseudo, 52, height - 320, 0xffffff);
            drawString(fontRenderer, "#TimeLine:", 2, height - 320, 0x3366ff);
            drawString(fontRenderer, TweetMod.timeline0, 2, height - 26, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline1, 2, height - 40, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline2, 2, height - 54, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline3, 2, height - 68, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline4, 2, height - 82, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline5, 2, height - 96, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline6, 2, height - 110, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline7, 2, height - 124, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline8, 2, height - 138, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline9, 2, height - 152, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline10, 2, height - 166, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline11, 2, height - 180, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline12, 2, height - 194, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline13, 2, height - 208, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline14, 2, height - 222, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline15, 2, height - 236, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline16, 2, height - 250, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline17, 2, height - 264, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline18, 2, height - 278, 0xffffff);
            drawString(fontRenderer, TweetMod.timeline19, 2, height - 292, 0xffffff);
        }
        else if (TweetMod.infos == 1)
        {
            drawString(fontRenderer, TweetMod.pseudo, 54, height - 320, 0xffffff);
            drawString(fontRenderer, "#Mentions:", 2, height - 320, 0x3366ff);
            drawString(fontRenderer, TweetMod.mention0, 2, height - 26, 0xffffff);
            drawString(fontRenderer, TweetMod.mention1, 2, height - 40, 0xffffff);
            drawString(fontRenderer, TweetMod.mention2, 2, height - 54, 0xffffff);
            drawString(fontRenderer, TweetMod.mention3, 2, height - 68, 0xffffff);
            drawString(fontRenderer, TweetMod.mention4, 2, height - 82, 0xffffff);
            drawString(fontRenderer, TweetMod.mention5, 2, height - 96, 0xffffff);
            drawString(fontRenderer, TweetMod.mention6, 2, height - 110, 0xffffff);
            drawString(fontRenderer, TweetMod.mention7, 2, height - 124, 0xffffff);
            drawString(fontRenderer, TweetMod.mention8, 2, height - 138, 0xffffff);
            drawString(fontRenderer, TweetMod.mention9, 2, height - 152, 0xffffff);
            drawString(fontRenderer, TweetMod.mention10, 2, height - 166, 0xffffff);
            drawString(fontRenderer, TweetMod.mention11, 2, height - 180, 0xffffff);
            drawString(fontRenderer, TweetMod.mention12, 2, height - 194, 0xffffff);
            drawString(fontRenderer, TweetMod.mention13, 2, height - 208, 0xffffff);
            drawString(fontRenderer, TweetMod.mention14, 2, height - 222, 0xffffff);
            drawString(fontRenderer, TweetMod.mention15, 2, height - 236, 0xffffff);
            drawString(fontRenderer, TweetMod.mention16, 2, height - 250, 0xffffff);
            drawString(fontRenderer, TweetMod.mention17, 2, height - 264, 0xffffff);
            drawString(fontRenderer, TweetMod.mention18, 2, height - 278, 0xffffff);
            drawString(fontRenderer, TweetMod.mention19, 2, height - 292, 0xffffff);
        }
        super.drawScreen(par1, par2, par3);
    }
}
