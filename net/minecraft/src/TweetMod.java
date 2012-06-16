package net.minecraft.src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.client.Minecraft;

public class TweetMod 
{
	public static Minecraft mc = ModLoader.getMinecraftInstance();
	public static String username = mc.session.username;

	public static String pseudo = "";
	
	public static String msg = "";
    
    public static String timeline0 = "";
    public static String timeline1 = "";
    public static String timeline2 = "";
    public static String timeline3 = "";
    public static String timeline4 = "";
    public static String timeline5 = "";
    public static String timeline6 = "";
    public static String timeline7 = "";
    public static String timeline8 = "";
    public static String timeline9 = "";
    public static String timeline10 = "";
    public static String timeline11 = "";
    public static String timeline12 = "";
    public static String timeline13 = "";
    public static String timeline14 = "";
    public static String timeline15 = "";
    public static String timeline16 = "";
    public static String timeline17 = "";
    public static String timeline18 = "";
    public static String timeline19 = "";
    
    public static String mention0 = "";
    public static String mention1 = "";
    public static String mention2 = "";
    public static String mention3 = "";
    public static String mention4 = "";
    public static String mention5 = "";
    public static String mention6 = "";
    public static String mention7 = "";
    public static String mention8 = "";
    public static String mention9 = "";
    public static String mention10 = "";
    public static String mention11 = "";
    public static String mention12 = "";
    public static String mention13 = "";
    public static String mention14 = "";
    public static String mention15 = "";
    public static String mention16 = "";
    public static String mention17 = "";
    public static String mention18 = "";
    public static String mention19 = "";

    public static int infos = 0;
    
    public static String url = "http://tweet-mod.com/tweetmod/Minecraft.php";
    public static String at = "";
    public static String ats = "";
    public static String infosTT = Minecraft.getAppDir("minecraft")+"/TweetMod.txt";
    
    public static void tweetSend()
    {
    	
    }
    
    public static void tweet(String tweet)
    {
    	if(tweet != "")
    	{   
        	BufferedReader in = null;
    		try
    		{
    			InputStream ips = new FileInputStream(infosTT); 
    			InputStreamReader ipsr = new InputStreamReader(ips);
    			BufferedReader br = new BufferedReader(ipsr);
    			at=br.readLine();
    			ats=br.readLine();
    			br.close(); 
    		}		
    		catch (Exception e)
    		{
    			System.out.println(e.toString());
    		}

    		
        	url = url+"?pseudo="+username+"&at="+at+"&ats="+ats+"&tweet="+tweet;
        	url = url.replaceAll("@", "[dot]");
        	url = url.replaceAll(" &", " [and]");
        	url = url.replaceAll("& ", "[and] ");
        	url = url.replace("+", "[more]");
        	url = url.replaceAll("#", "[diez]");
        	url = url.replaceAll(" ", "%20");
        	
        	try
        	{
        		URL site = new URL(url);
        		site.openStream();
        	}
        	catch (IOException ex)
        	{
        		System.out.println("Erreur dans l'ouverture de l'URL : " + ex);
        	}
    	}
    }  
    
    public static void getTimelineTwitter()
    {
    	BufferedReader in = null;
		try
		{
			InputStream ips = new FileInputStream(infosTT); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			at=br.readLine();
			ats=br.readLine();
			br.close(); 
		}		
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
        try
        {
        	url = url+"?pseudo="+username+"&at="+at+"&ats="+ats+"&tweet=";
            URL site = new URL(url);
            in = new BufferedReader(new InputStreamReader(site.openStream()));
            pseudo = in.readLine();
            msg = in.readLine();
            timeline0 = in.readLine();
            timeline1 = in.readLine();
            timeline2 = in.readLine();
            timeline3 = in.readLine();
            timeline4 = in.readLine();
            timeline5 = in.readLine();
            timeline6 = in.readLine();
            timeline7 = in.readLine();
            timeline8 = in.readLine();
            timeline9 = in.readLine();
            timeline10 = in.readLine();
            timeline11 = in.readLine();
            timeline12 = in.readLine();
            timeline13 = in.readLine();
            timeline14 = in.readLine();
            timeline15 = in.readLine();
            timeline16 = in.readLine();
            timeline17 = in.readLine();
            timeline18 = in.readLine();
            timeline19 = in.readLine();
            in.close();
        }
        catch (IOException ex)
        {
            System.out.println("Erreur dans l'ouverture de l'URL : " + ex);
        }
        pseudo = betterView(pseudo);
        msg = betterView(msg);
        timeline0 = betterView(timeline0);
        timeline1 = betterView(timeline1);
        timeline2 = betterView(timeline2);
        timeline3 = betterView(timeline3);
        timeline4 = betterView(timeline4);
        timeline5 = betterView(timeline5);
        timeline6 = betterView(timeline6);
        timeline7 = betterView(timeline7);
        timeline8 = betterView(timeline8);
        timeline9 = betterView(timeline9);
        timeline10 = betterView(timeline10);
        timeline11 = betterView(timeline11);
        timeline12 = betterView(timeline12);
        timeline13 = betterView(timeline13);
        timeline14 = betterView(timeline14);
        timeline15 = betterView(timeline15);
        timeline16 = betterView(timeline16);
        timeline17 = betterView(timeline17);
        timeline18 = betterView(timeline18);
        timeline19 = betterView(timeline19);
    }
    
    public static void getMentionTwitter()
    {
    	BufferedReader in = null;
		
		try
		{
			InputStream ips = new FileInputStream(infosTT); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			at=br.readLine();
			ats=br.readLine();
			br.close(); 
		}		
		catch (Exception e)
		{
			System.out.println(e.toString());
		}
        try
        {
        	url = url+"?pseudo="+username+"&at="+at+"&ats="+ats+"&tweet=";
            URL site = new URL(url);
            in = new BufferedReader(new InputStreamReader(site.openStream()));
            pseudo = in.readLine();
            for(int i = 0; i <= 21; i++)
            {
            	mention0 = in.readLine();
            }
            mention1 = in.readLine();
            mention2 = in.readLine();
            mention3 = in.readLine();
            mention4 = in.readLine();
            mention5 = in.readLine();
            mention6 = in.readLine();
            mention7 = in.readLine();
            mention8 = in.readLine();
            mention9 = in.readLine();
            mention10 = in.readLine();
            mention11 = in.readLine();
            mention12 = in.readLine();
            mention13 = in.readLine();
            mention14 = in.readLine();
            mention15 = in.readLine();
            mention16 = in.readLine();
            mention17 = in.readLine();
            mention18 = in.readLine();
            mention19 = in.readLine();
            in.close();
        }
        catch (IOException ex)
        {
            System.out.println("Erreur dans l'ouverture de l'URL : " + ex);
        }
        pseudo = betterView(pseudo);
        mention0 = betterView(mention0);
        mention1 = betterView(mention1);
        mention2 = betterView(mention2);
        mention3 = betterView(mention3);
        mention4 = betterView(mention4);
        mention5 = betterView(mention5);
        mention6 = betterView(mention6);
        mention7 = betterView(mention7);
        mention8 = betterView(mention8);
        mention9 = betterView(mention9);
        mention10 = betterView(mention10);
        mention11 = betterView(mention11);
        mention12 = betterView(mention12);
        mention13 = betterView(mention13);
        mention14 = betterView(mention14);
        mention15 = betterView(mention15);
        mention16 = betterView(mention16);
        mention17 = betterView(mention17);
        mention18 = betterView(mention18);
        mention19 = betterView(mention19);
    }
    
    public static String betterView(String s)
    {
        s = s.replaceAll("Ã"+"\u00A0", "à");
        s = s.replaceAll("Ã©", "é");
        s = s.replaceAll("Â°", "°");
        s = s.replaceAll("Ã‰", "E");
        s = s.replaceAll("Ã¨", "è");
        s = s.replaceAll("Ãª", "ê");
        s = s.replaceAll("Ã§", "ç");
        s = s.replaceAll("Ã»", "û");
        s = s.replaceAll("Ã¹", "ù");
        s = s.replaceAll("Ã‡", "Ç");
        s = s.replaceAll("Ã	", "à");
        s = s.replaceAll("Ã´", "ô");
        s = s.replaceAll("Ã€", "A");
        s = s.replaceAll("Ã®", "î");
        s = s.replaceAll("Ã¯", "ï");
        s = s.replaceAll("Ã¢", "â");
        s = s.replaceAll("â€¦", "...");
        s = s.replaceAll("â€™", "'");
        s = s.replaceAll("&lt;", "<");
        s = s.replaceAll("&gt;", ">");
        s = s.replaceAll("â€œ;", "\"");
        s = s.replaceAll("â€", "\"");
        s = s.replaceAll("â™¥ ", "<3");
        s = s.replace("</br>", "");
        return s;
    }
}
