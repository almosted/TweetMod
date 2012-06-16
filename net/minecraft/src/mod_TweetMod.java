package net.minecraft.src;

import org.lwjgl.input.Keyboard;

public class mod_TweetMod extends BaseMod
{    
	public String version = "#TweetMod v1.1 by almosted, stornitz and "+TweetMod.username+"!";
	public KeyBinding keyTwitter = new KeyBinding("Twitter", Keyboard.KEY_B);
	
	public void keyboardEvent(KeyBinding keybinding)
	{
        for (; keyTwitter.isPressed(); ModLoader.getMinecraftInstance().displayGuiScreen(new GuiTwitter())) { }
	}

	public void load() 
	{
		ModLoader.addLocalization("Twitter", "Twitter");
		ModLoader.registerKey(this, this.keyTwitter, false);
		ModLoader.setInGUIHook(this, true, false);
	    System.out.println("#TweetMod loaded with success!");
	}
	
	public String getVersion()
	{
		return version;
	}
}
